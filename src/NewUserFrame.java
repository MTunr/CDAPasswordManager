import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class NewUserFrame extends JFrame{
    private JPanel rootPanel;
    private JButton createButton;
    private JButton cancelButton;
    private JPasswordField passwordField;
    private JPasswordField confirmPassField;
    private JTextField usernameField;
    private JLabel warningLabel;

    private PasswordManager passwordManager;
    private LoginFrame loginFrame;

    /**
     * Constructor
     * @param pmReference a reference to the main Password Manager app.
     * @param lfReference a reference to the login window. used to enable/disable interaction.
     */
    public NewUserFrame(PasswordManager pmReference, LoginFrame lfReference){
        // Link .java to form file.
        this.add(rootPanel);

        // assign references to Password Manager and Login Frame.
        passwordManager = pmReference;
        loginFrame = lfReference;

        // disable login window. prevents bad interaction flow.
        loginFrame.setEnabled(false);

        //size and visibility settings
        setSize(400,300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
        setLocationRelativeTo(null); //set frame to be at center of screen

        // Setup button listeners
        cancelButton.addActionListener(new CancelListener());
        createButton.addActionListener(new ConfirmListener());
    }

    /**
     * Confirms if user's details are valid and can be saved.
     * username can be alphanumeric + [- _ @ and .] characters, min 3 chars.
     * passwords can be anything. min 10 chars.
     * @return validity of user's input
     */
    ArrayList<validity> verifyInput(){
        // Declare output var.
        ArrayList<validity> output = new ArrayList<validity>();

        // Get info from fields
        String username = usernameField.getText();
        String pass1 = String.valueOf(passwordField.getPassword());
        String pass2 = String.valueOf(confirmPassField.getPassword());

        // check for empty fields
        if (username.equals("") || pass1.equals("") || pass2.equals("")){
            output.add(validity.EMPTY_FIELD);
        }

        // check for username validity
        String patternString = "([a-zA-Z]|[0-9]|@|_|-|\\.)*";
        if (! username.matches(patternString))
            output.add(validity.INVALID_USERNAME);

        // Check if passwords match
        if (!pass1.equals(pass2))
            output.add(validity.PASSWORD_MISMATCH);

        // Check if password is long enough
        if (pass1.length() < 10)
            output.add(validity.INVALID_PASSWORD);

        // if there are no errors, output is valid
        if (!(
            output.contains(validity.EMPTY_FIELD)|
            output.contains(validity.PASSWORD_MISMATCH)|
            output.contains(validity.INVALID_PASSWORD)|
            output.contains(validity.INVALID_USERNAME))
        ){
            output.add(validity.VALID_INPUT);
        }

        //  --return--
        return output;
    }

    enum validity{
        VALID_INPUT,
        EMPTY_FIELD,
        PASSWORD_MISMATCH,
        INVALID_PASSWORD,
        INVALID_USERNAME
    }

    /**
     * runs when confirm button is clicked.
     * tells password manager to create new acocunt if inputs are valid.
     * if inputs are invalid, user is given a warning text.
     */
    void confirm(){
        // valid input procedure
        if (verifyInput().contains(validity.VALID_INPUT)){
            // get information from fields
            String username = usernameField.getText();
            String password = String.valueOf(passwordField.getPassword());
            // call PassMan's user creation method
            passwordManager.newUser(username, password);
            // Kill frame
            loginFrame.setEnabled(true);
            dispose();
        }
        //TODO: deal with output.
    }

    void cancel(){
        loginFrame.setEnabled(true);
        dispose(); // kill frame
    }

    // ----- Listener Classes ----- //
    /**
     * Confirm button listener. calls confirm();
     */
    class ConfirmListener extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            confirm();
        }
    }

    /**
     * Cancel Button Listener. calls cancel();
     */
    class CancelListener extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            cancel();
        }
    }
}
