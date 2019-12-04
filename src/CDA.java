/*
 * Date: 2/12/2019
 * This class implements the CDA algorithm
 * CDA is a very slow string only encryption algorithm
 * that takes in a string and returns another string
 *
 * Update: 4/12/2019
 * For now CDA only supports unicode of 0 - 1000
 *
 * Author: Jheng Jheng Ch'ng
 */
import java.lang.*;
import java.math.BigInteger;
import java.util.Random;

public class CDA {
    private static final int DEFAULT_ITERATIONS = 64;
    private static final int DEFAULT_KEY_HASH_LENGTH = 30;
    private static final int MAX_CHARACTER_SET_SIZE = 1000;
    private int iterations;
    private Random rand;

    /**
     * Constructor for the CDA algorithm.
     *
     * @param iterations bit size for the CDA instance
     */
    public CDA(int iterations) {
        this.iterations = iterations;
    }

    /**
     * Default constructor for when a specified number does not exist
     */
    public CDA() {
        this.iterations = DEFAULT_ITERATIONS;
    }

    /**
     * Encrypts the plaintext
     *
     * @param plainText The given plaintext as a string
     * @param key       The key to use to encrypt the plaintext
     * @return The encrypted plaintext as a string
     */
    public String encrypt(String plainText, String key) {
        String output = "";

        // Hashing the key
        BigInteger hashedKey = keyHasher(key);

        /*
        long is not able to hold a value as big as BigInteger,
        so java shrinks it to a smaller container
         */
        long shrunkHashedKey = hashedKey.longValue();

        // Instantiating Random with the long hashed key;
        rand = new Random(shrunkHashedKey);

        /*
        Substitution happens here.
        For each character substituted, a new seed for will be set using the last 10 characters of the encrypted text
        if available. This would ensure the avalanche affect.
        TODO: Backwards avalanche?
         */
        for (int currentIndex = 0; currentIndex < plainText.length(); currentIndex++){
            output += offsetChar(plainText.charAt(currentIndex),rand.nextInt(MAX_CHARACTER_SET_SIZE));
            rand.setSeed(keyHasher(output.substring(currentIndex < 10 ? 0 : currentIndex - 10)+shrunkHashedKey).longValue());
        }

        return output;
    }

    /**
     * Decrypts the cipher text
     *
     * @param cipherText The given cipher text as a string
     * @param key        The key used to encrypt the plaintext
     * @return The decrypted cipher text as a string
     */
    public String decrypt(String cipherText, String key) {
        return cipherText;
    }

    /**
     * Offsets a given character by n characters
     * @param a The character to offset from
     * @param offset Offset length
     * @return offset character
     */
    public char offsetChar(char a, long offset){
        return (char) (a + offset);
    }



    /**
     * This method hashes a given key into a fixed length string.
     *
     * @return an Integer of a fixed length.
     */
    public BigInteger keyHasher(String key) {
        if (key.equals("")){
            key = " ";
        }

        BigInteger hash = BigInteger.valueOf(1);

        // This could be a bad approach! Expanding keys by duplicating them may cause
        // different keys to collide
        // Removed to prevent collisions //
//        int oddCounter = 0;
//        while (key.length() < DEFAULT_KEY_HASH_LENGTH) {
//            key += (oddCounter % 2 == 0) ? reverse(key) : key;
//            oddCounter++;
//        }

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
    public BigInteger shortenBigInteger(BigInteger integer, int length) {
        return integer.mod(BigInteger.valueOf(10).pow(length));
    }

    /** TODO: Move to a string manipulation class
     * Reverses a given string
     * @param aString The string to reverse
     * @return The reversed string
     */
    public String reverse(String aString) {
        String reversed = "";

        for (int i = 0; i < aString.length(); i++) {
            reversed = aString.charAt(i) + reversed;
        }

        return reversed;
    }
}