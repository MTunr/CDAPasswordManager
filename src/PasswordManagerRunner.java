import javax.swing.*;

//Runs password manager
public class PasswordManagerRunner {
    public static void main(String[] args) {
        PasswordManager passwordManager = new PasswordManager();
        SwingUtilities.invokeLater(passwordManager);
    }
}