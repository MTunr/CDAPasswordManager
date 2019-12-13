import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class AccountParser {
    public static final int ACCOUNT_BLOCK_SIZE = 4;

    public AccountParser(){
        throw new InstantiationError();
    }

    /**
     * Parses the decrypted text into an ArrayList of Accounts
     * @param decryptedAccountText Unparsed but decrypted accounts
     * @return ArrayList An ArrayList of Accounts (Parsed)
     */
    public static ArrayList<Account> parse(String decryptedAccountText){
        ArrayList<Account> parsedAccounts = new ArrayList<>();
        String[] semiParsed = decryptedAccountText.split("\n");
        String currentServiceName = "",
                currentPassword = "",
                currentDescription = "",
                currentRecoveryKeys = "";

        int accountItemTracker = 0;
        for (String item: semiParsed){
            if (accountItemTracker % ACCOUNT_BLOCK_SIZE == 0){ // Set service name
                currentServiceName = item;
            }
            else if (accountItemTracker % ACCOUNT_BLOCK_SIZE == 1){  // Set password
                currentPassword = item;
            }
            else if (accountItemTracker % ACCOUNT_BLOCK_SIZE == 2){ // Set description
                currentDescription = item;
            }
            else if (accountItemTracker % ACCOUNT_BLOCK_SIZE == 3){ // Set recovery keys
                // TODO: Have a way of storing multiple recovery keys
                currentRecoveryKeys = item;

                // When all of the details for an account are collected.
                parsedAccounts.add(
                        new Account(currentServiceName,
                                currentPassword,
                                currentDescription,
                                new ArrayList<>(Arrays.asList(currentRecoveryKeys.split(" "))))
                );

                // Reset variables after parsing an account from the list of accounts.
                currentServiceName = currentPassword = currentDescription = currentRecoveryKeys = "";
            }

            accountItemTracker++; // Go to next item in the account
        }

        return parsedAccounts;
    }

    /**
     * Un-parses the accounts and formats it back into a string that can be stored/read
     * @param accounts ArrayList of Account to format.
     */
    public static String unparse(ArrayList<Account> accounts){
        String formatted = "";
        for (Account current: accounts){
            formatted += current.getServiceName() + "\n" +
                        current.getPassword() + "\n" +
                        current.getDescription() + "\n" +
                        current.getRecoveryKeys() + "\n";
        }
        return formatted;
    }

}
