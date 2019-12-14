/*
 * Date: 2/12/2019
 * This class implements the CDA algorithm
 * CDA is a very slow string only encryption algorithm
 * that takes in a string and returns another string
 *
 * Update: 4/12/2019
 * For now CDA only supports unicode of 0 - 10000
 *
 * TODO:    Backwards avalanche
 *          Valid decryption
 *
 * Author: Jheng Jheng Ch'ng
 */
import java.io.UnsupportedEncodingException;
import java.lang.*;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;


/**
 * CDA Implementation (C***k D**k A**-c**********g)
 * A very slow cryptographic algorithm. Re
 *
 * Author: Jarret Jheng Ch'ng
 */
public class CDA {
    private static final int DEFAULT_ITERATIONS = 64;
    private static final int DEFAULT_KEY_HASH_LENGTH = 30;
    private static final int MAX_CHARACTER_SET_SIZE = 10000;
    private int iterations;
    private BigInteger hashedKey;
    private String key;
    private Random rand;
    long shrunkHashedKey;

    /**
     * Constructor for the CDA algorithm.
     *
     * @param iterations bit size for the CDA instance
     */
    public CDA(int iterations, String key) {
        this.key = key;

        // Hashing the key
        hashedKey = keyHasher(key);

        /*
        long is not able to hold a value as big as BigInteger,
        so java shrinks it to a smaller container
         */
        shrunkHashedKey = hashedKey.longValue();

        this.iterations = iterations;
    }

    /**
     * Default constructor for when a specified number does not exist
     */
    public CDA() {
        hashedKey = BigInteger.valueOf(69420);
        shrunkHashedKey = hashedKey.longValue();
        this.iterations = DEFAULT_ITERATIONS;
    }

    /**
     * Encrypts the plaintext
     *
     * @param plainText The given plaintext as a string
     * @return The encrypted plaintext as a string
     */
    public String encrypt(String plainText){
        String md5 = shortMD5(plainText);
        return encrypt(plainText + md5, true);
    }

    /**
     * Gets the MD5 digest of the input string. Integrity check.
     * @param input String to get the digest of
     * @return 10 Character MD5 of input
     */
    public static String shortMD5(String input){
        String md5 = null;

        // Referenced from: https://stackoverflow.com/questions/5470219/get-md5-string-from-message-digest
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] md5sum = md.digest(input.getBytes());
            md5 = String.format("%032X", new BigInteger(1, md5sum));
        } catch (NoSuchAlgorithmException e){
            e.getStackTrace();
        }

        return md5.substring(0,10);
    }

    /**
     * Overloaded
     * Encrypts the plaintext.
     *
     * @param plainText The given plaintext as a string
     * @param iterate Whether to repeatedly encrypt
     * @return The encrypted plaintext as a string
     */
    private String encrypt(String plainText, boolean iterate) {
        BigInteger hashedSubstring;

        // encrypted string is stored here
        String output = "";

        // reinitialise random
        rand = new Random(shrunkHashedKey);

        /*
        Substitution happens here.
        For each character substituted, a new seed for will be set using the last 10 characters of the encrypted text
        if available. This would ensure the avalanche affect.
         */
        for (int currentIndex = 0; currentIndex < plainText.length(); currentIndex++) {
            // Get the next random integer
            int offset = rand.nextInt(MAX_CHARACTER_SET_SIZE);

            // Offset character by the random integer
            output += offsetChar(plainText.charAt(currentIndex), offset, false);

            // Check if the substring is less than 10, if it is, use current index
            String cryptoSubstring = output.substring(currentIndex < 10 ? 0 : currentIndex - 10);

            // Hash the substring
            hashedSubstring = keyHasher(cryptoSubstring);

            // Subtract hashed key from hashed substring
            long seed = (hashedSubstring.subtract(hashedKey)).longValue();

            // Uncomment for verbose
//            System.out.println(hashedEncryption + " " + substring + " " + seed + " " + hashedKey);

            // Set a new seed for the rng by using the hashed key as the seed
            rand.setSeed(seed);
        }

        if (iterate) {
            return repeat(output, iterations,true);
        } else {
            return output;
        }
    }

    /**
     * Decrypts/Encrypts n times
     * Re-encrypts/decrypts the already encrypted/decrypted text slowing things down.
     * @param incompleteCipher Not a plaintext and may not be the final cipher text.
     * @param n Iterations left before stopping
     * @param encryptMode Repeatedly decrypt/encrypt
     * @return cipher/plain text that may or may not be the final version of it.
     */
    private String repeat(String incompleteCipher, int n, boolean encryptMode){
        String currentCipher = incompleteCipher;

        while (n > 0) {
            if (encryptMode){
                currentCipher = encrypt(currentCipher,false);
            } else {
                currentCipher = decrypt(currentCipher, false);
            }
            n--;
        }

        return currentCipher;
    }

    /**
     * Decrypts cipher text. Only returns contents if valid, else blank
     * @param cipherText The given cipher text as a string
     * @return Plaintext
     */
    public String decrypt(String cipherText){
        String decrypted = decrypt(cipherText,true);
        String decryptedMD5 = decrypted.substring(cipherText.length()-10);
        String contents = decrypted.substring(0,cipherText.length()-10);
        String actualMD5 = shortMD5(contents);

        return decryptedMD5.equals(actualMD5) ? contents : "";
    }

    /**
     * Overloaded
     * Decrypts the cipher text
     *
     * @param cipherText The given cipher text as a string
     * @param iterate Whether to repeatedly decrypt
     * @return The decrypted cipher text as a string
     */
    private String decrypt(String cipherText, boolean iterate) {
        BigInteger hashedSubstring;

        // decrypted string is stored here
        String output = "";

        // reinitialise random
        rand = new Random(shrunkHashedKey);

        /*
        Un-substitution happens here.
         */
        for (int currentIndex = 0; currentIndex < cipherText.length(); currentIndex++){
            // Get the next random integer, this should have the same number as the encryption
            int offset = rand.nextInt(MAX_CHARACTER_SET_SIZE);

            // Remove the offset by setting negative to true
            output += offsetChar(cipherText.charAt(currentIndex),offset,true);

            // Check if the substring is less than 10, if it is, use the current index else subtract 10 from the current
            // This should allow the substring to have a maximum length of 10 characters
            String substring = cipherText.substring(currentIndex < 10 ? 0 : currentIndex - 10, currentIndex+1);

            // Hash the substring
            hashedSubstring = keyHasher(substring);

            // Subtract the hashed key from hashed substring
            long seed = (hashedSubstring.subtract(hashedKey)).longValue();

            // Uncomment for verbose
//            System.out.println(hashedSubstring + " " + substring + " " + seed + " " + hashedKey + " " + offset);

            // Set a new seed for the rng by using the hashed key as the seed
            rand.setSeed(seed);
        }

        if (iterate){
            return repeat(output,iterations,false);
        } else {
            return output;
        }

    }

    /**
     * Offsets a given character by n characters
     * @param a The character to offset from
     * @param offset Offset length
     * @param negative negative offset instead
     * @return offset character
     */
    private char offsetChar(char a, long offset, boolean negative){
        // add if negative is false
        return (char) ((negative ? (a - offset) : (a + offset)));
    }


    /**
     * This method hashes a given key into a fixed length string.
     *
     * @return an Integer of a fixed length.
     */
    private BigInteger keyHasher(String key) {
        if (key.equals("")){
            key = " ";
        }

        BigInteger hash = BigInteger.valueOf(1);

        // Go through each character and calculate the power of index
        for (int i = 0; i < key.length(); i++) {
            hash = hash.add(BigInteger.valueOf(key.charAt(i)).pow(i+1));
        }

        return shortenBigInteger(hash, DEFAULT_KEY_HASH_LENGTH);
    }

    /**
     * Takes in an integer and takes the last n "characters" of it
     *
     * @param integer The original BigInteger
     * @param length  How many characters to take
     * @return The shortened integer
     */
    private BigInteger shortenBigInteger(BigInteger integer, int length) {
        return integer.mod(BigInteger.valueOf(10).pow(length));
    }

    /** TODO: Move to a string manipulation class
     * Reverses a given string
     * @param aString The string to reverse
     * @return The reversed string
     */
    private String reverse(String aString) {
        String reversed = "";

        for (int i = 0; i < aString.length(); i++) {
            reversed = aString.charAt(i) + reversed;
        }

        return reversed;
    }
}