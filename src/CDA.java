/*
 * Date: 2/12/2019
 * This class implements the CDA algorithm
 * CDA is a very slow string only encryption algorithm
 * that takes in a string and returns another string
 *
 * Author: Jheng Jheng Ch'ng
 */
import java.lang.*;
import java.lang.Math;
import java.math.BigInteger;

public class CDA {
    private static final int DEFAULT_SEED = 68867;
    private static final int DEFAULT_KEY_HASH_LENGTH = 10;
    private int seed;

    /**
     * Constructor for the CDA algorithm.
     *
     * @param seed Seed for the CDA instance
     */
    public CDA(int seed) {
        this.seed = seed;
    }

    /**
     * Default constructor for when a seed does not exist.
     */
    public CDA() {
        this.seed = DEFAULT_SEED;
    }

    /**
     * Encrypts the plaintext
     *
     * @param plainText The given plaintext as a string
     * @param key       The key to use to encrypt the plaintext
     * @return The encrypted plaintext as a string
     */
    public String encrypt(String plainText, String key) {
        return plainText;
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
     * This class expands a given key into a fixed length string.
     *
     * @return an Integer of a fixed length.
     */
    public BigInteger keyExpander(String key) {
        BigInteger hash = BigInteger.valueOf(1);

        int oddCounter = 0;
        while (key.length() < DEFAULT_KEY_HASH_LENGTH) {
            key += (oddCounter % 2 == 0) ? reverse(key) : key;
            oddCounter++;
        }

        for (int i = 0; i < key.length(); i++) {
            hash = hash.add(BigInteger.valueOf(key.charAt(i)).pow(i+1));
//            System.out.println(hash);
        }

        return hash;
//        return shortenBigInteger(hash, DEFAULT_KEY_HASH_LENGTH);
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
     * @return THe reversed string
     */
    public String reverse(String aString) {
        String reversed = "";

        for (int i = 0; i < aString.length(); i++) {
            reversed = aString.charAt(i) + reversed;
        }

        return reversed;
    }
}