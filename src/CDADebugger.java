/**
 * Debugging class for CDA
 */

import java.io.IOException;
import java.lang.*;


public class CDADebugger {
    public static void main(String[] args) {
        CDA e = new CDA(1,"haha");
        String encryptedString = e.encrypt("ServiceName\nPassword\n\nRecoveryKeys\n");
        String decryptedString = e.decrypt(encryptedString);
        System.out.println("Encrypted: " + encryptedString);
        System.out.println("Decrypted: " + decryptedString);

        // Parse Test
        System.out.println(AccountParser.parse(decryptedString));

        // Test file encryption/decryption
        CDAFileHandler cfh = new CDAFileHandler(1, "haha", "");

        // Encrypt with the text "plaintext"
        try {
            cfh.encryptFile("plaintext");
        } catch (IOException a){
            a.printStackTrace();
        }

        // Decrypt
        try {
            System.out.println(cfh.decryptFile());
        } catch (IOException a){
            a.printStackTrace();
        }

        // Sample file name generation
        System.out.println("File name: " + LoginHandler.findFile("tunr","monash"));
    }
}