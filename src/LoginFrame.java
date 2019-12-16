import javax.swing.*;
import java.awt.event.ActionEvent;

public class LoginFrame extends JFrame{
    private JPanel rootPanel;
    private JButton loginButton;
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JLabel invalidLabel;
    private JButton newUserButton;

    private PasswordManager passwordManager;
    private NewUserFrame newUserFrame;

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
        newUserButton.addActionListener(new NewUserListener());
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

    /**
     * opens new user frame to deal with creating new user
     */
    void newUser(){
        newUserFrame = new NewUserFrame(passwordManager, this);
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

    /**
     * new user button listener. calls newUser();
     */
    class NewUserListener extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            newUser();
        }
    }
}