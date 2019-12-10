import java.util.ArrayList;

public class DecryptAndParse {

    public DecryptAndParse(){
        throw new InstantiationError();
    }

    public static ArrayList<Account> decryptAndParse(String encryptedAccountsText, String key, int iterations){
        CDA cda = new CDA(iterations, key);
        ArrayList<Account> parsedAccounts;

        String decryptedAccountText = cda.decrypt(encryptedAccountsText);


    }

    private static Account parseAccountText(){
        
    }

}
