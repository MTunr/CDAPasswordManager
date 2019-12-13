import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.*;


/**
 * Encrypts/decrypts a file.
 * Author: Jarret Jheng Ch'ng
 */
public class CDAFileHandler extends CDA {

    public static final String CDAX_PATH = System.getProperty("user.dir") + "/src/cdaxFiles/";
    public static final String CDAX_EXTENSION_NAME = ".cdax";

    private String fileName;
    private String path;

    /**
     * Constructor
     * @param iterations Number of iterations to use for encryption
     * @param key Password
     * @param fileName Name of the file to encrypt/decrypt
     */
    public CDAFileHandler(int iterations, String key, String fileName) {
        super(iterations, key);
        this.fileName = checkAndFixFileName(fileName,CDAX_EXTENSION_NAME);
        this.path = CDAX_PATH + this.fileName;
    }

    /**
     * Empty constructor, calls super constructor
     */
    public CDAFileHandler(){
        super();
    }

    /**
     * Decrypts the file and dumps out the contents
     * @return Decrypted contents
     * @throws IOException
     */
    public String decryptFile() throws IOException {
        String path = CDAX_PATH + fileName;

        String data = new String(Files.readAllBytes(Paths.get(path)));

        return decrypt(data);
    }

    /**
     * Encrypts the file using the given plaintext, creates a file if it doesn't already exist.
     * @param plainText Plaintext to use to encrypt
     * @throws IOException When there is an issue writing
     */
    public void encryptFile(String plainText) throws IOException {
        // Gets an instance of the path
        File file = new File(path);

        FileWriter fr;
        fr = new FileWriter(file);

        // Write plaintext into the file
        fr.write(encrypt(plainText));

        // Close the file
        fr.close();
    }

    /**
     * Encrypts the contents of the file.
     * @throws IOException
     */
    public void encryptFile() throws IOException {
        encryptFile(new String(Files.readAllBytes(Paths.get(path))));
    }

    /**
     * Checks if the given filename has a correct extension, fixes it if it doesn't.
     * @param fileName The filename to fcheck
     * @param extensionName The extension to compare/add
     * @return The proper filename
     */
    public String checkAndFixFileName(String fileName, String extensionName){

        if (fileName.length() < 1) {
            // Default name if the filename is blank.
            fileName = System.nanoTime() + extensionName;
        }
        else {
            // Check if the filename has an extension, if it doesn't put one. Also checks if the filename has the correct filename and fixes it if it doesn't.
            fileName = fileName.substring(0, fileName.lastIndexOf('.') == -1 ?
                    fileName.length() :
                    fileName.lastIndexOf('.')) + extensionName;
        }

        return fileName;
    }

}
