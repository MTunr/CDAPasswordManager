/**
 * This class implements the CDA algorithm
 * CDA is a very slow string only encryption algorithm
 * that takes in a string and returns another string
 *
 *
 *
 * @author Jheng Ch'ng
 *
 * Date: 2/12/2019
 */
public class CDA {
    private static final int DEFAULT_SEED = 68867;
    private int seed;

    /**
     * Constructor for the CDA algorithm.
     * @param seed Seed for the CDA instance
     */
    public CDA(int seed){
        this.seed = seed;
    }

    /**
     * Default constructor for when a seed does not exist.
     */
    public CDA(){
        this.seed = DEFAULT_SEED;
    }

    /**
     * Encrypts the plaintext
     * @param plainText The given plaintext as a string
     * @param key The key to use to encrypt the plaintext
     * @return The encrypted plaintext as a string
     */
    public String encrypt(String plainText, String key){
        return plainText;
    }

    /**
     * Decrypts the cipher text
     * @param cipherText The given cipher text as a string
     * @param key The key used to encrypt the plaintext
     * @return The decrypted cipher text as a string
     */
    public String decrypt(String cipherText, String key) {
        return cipherText;
    }


}
