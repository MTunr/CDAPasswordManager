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
     * @param caller reference to object that created this class
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

        // set event listener for button
        loginButton.addActionListener(new ButtonListener());
    }

    class ButtonListener extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            // Get information from fields
            String username = textField1.getText();
            String password = String.valueOf(passwordField1.getPassword());
            passwordManager.verifyLogin(username, password);
        }
    }

    /**
     * Displays an error message if login is invalid
     */
    public void displayWrongLogin(){
        invalidLabel.setText("Invalid login details");
    }
}