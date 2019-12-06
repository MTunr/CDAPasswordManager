/**
 * Debugging class for CDA
 */

import org.w3c.dom.ls.LSOutput;

import java.util.Random;
import java.lang.Math;
import java.lang.*;


public class CDADebugger {
    public static void main(String[] args) {
        CDA e = new CDA(1,"haha");
        String encryptedString = e.encrypt("admin123".repeat(10));
        String decryptedString = e.decrypt(encryptedString);
        System.out.println("Encrypted: " + encryptedString);
        System.out.println("Decrypted: " + decryptedString);
    }
}
