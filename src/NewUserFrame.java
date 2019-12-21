import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

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
        setSize(400,250);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null); //set frame to be at center of screen

        // Setup button listeners
        cancelButton.addActionListener(new CancelListener());
        createButton.addActionListener(new ConfirmListener());

        // remove X button
        setUndecorated(true);
        setVisible(true);
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
     * if inputs are invalid, user is given a warning text depending on the input.
     */
    void confirm(){
        // valid input procedure
        ArrayList<validity> verifyResult = verifyInput();

        if (verifyResult.contains(validity.VALID_INPUT)){
            // get information from fields
            String username = usernameField.getText();
            String password = String.valueOf(passwordField.getPassword());
            // call PassMan's user creation method
            passwordManager.newUser(username, password);
            // Kill frame
            loginFrame.setEnabled(true);

            //create confirmation dialog
            String message = "New user " + username + " created.";
            JOptionPane.showMessageDialog(null, message);
            dispose();
        } else {
            // set text in warning label
            String message = "<html>";
           if (verifyResult.contains(validity.EMPTY_FIELD)) {
               message = message.concat("Please fill in all the fields.<br/>");
           }
           if (verifyResult.contains(validity.PASSWORD_MISMATCH)) {
               message = message.concat("Passwords do not match.<br/>");
           }
           if (verifyResult.contains(validity.INVALID_PASSWORD)) {
               message = message.concat("Password must be at least 10 characters.<br/>");
           }
           if (verifyResult.contains(validity.INVALID_USERNAME)) {
               message = message.concat("Username may only contain [A-Z], [a-z], [0-9] and [_ @ - .] <br/>");
           }
           message = message.concat("</html>");
           warningLabel.setText(message);
        }
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
