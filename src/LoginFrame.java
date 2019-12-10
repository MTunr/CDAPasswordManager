import javax.swing.*;
import java.awt.event.ActionEvent;

public class LoginFrame extends JFrame{
    private JPanel rootPanel;
    private JButton loginButton;
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JLabel invalidLabel;

    private PasswordManager passwordManager;

    /**
     * Constructor class: creates login window
     * @param caller reference caller object (PasswordManager).
     */
    public LoginFrame(PasswordManager caller){

        //reference to caller
        this.passwordManager = caller;

        //set GUi according to form
        add(rootPanel);

        //size and visibility settings
        setSize(400,300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);

        //set frame to be at center of screen
        setLocationRelativeTo(null);

        // Button settings
        getRootPane().setDefaultButton(loginButton); // set ENTER key to 'click' the login button
        loginButton.addActionListener(new LoginListener()); // set listener.
    }

    /**
     * Displays an error message if login is invalid
     */
    public void displayWrongLogin(){
        invalidLabel.setText("Invalid login details");
    }

    /**
     * Logs the user in
     */
    public void login(){
        String username = textField1.getText();
        String password = String.valueOf(passwordField1.getPassword());
        passwordManager.verifyLogin(username, password);
    }

    // ----- Listeners ----- //

    /**
     * Login button listener. calls login().
     */
    class LoginListener extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            login();
        }
    }
}