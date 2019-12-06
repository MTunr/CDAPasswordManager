import javax.swing.*;
import java.awt.event.ActionEvent;

public class AddWindow extends JWindow{
    private JButton addButton;
    private JButton cancelButton;
    private JTextField serviceField;
    private JTextField usernameField;
    private JTextField passwordField;
    private JPanel rootPanel;

    private MainFrame mainFrame;

    /**
     * Constructor
     * @param caller reference to MainFrame caller
     */
    public AddWindow(MainFrame caller){
        //set reference to main app.
        this.mainFrame = caller;

        //set gui according to form
        add(rootPanel);

        //size and visibility settings
        setSize(400,250);
        setVisible(true);

        //set frame to be at center of screen
        setLocationRelativeTo(null);

        // Set button listeners
        cancelButton.addActionListener(new CancelListener());
        addButton.addActionListener(new AddListener());
    }

    // ----- Listener classes ----- //
    class CancelListener extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            // closes frame, but not application.
            AddWindow.super.dispose();
        }
    }

    class AddListener extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            // Get information from text fields
            String serviceName = serviceField.getText();
            String username = usernameField.getText();
            String password = passwordField.getText();

            // tell mainframe to add account and dispose of self.
            mainFrame.addAccount(serviceName, username, password);
            AddWindow.super.dispose();
        }
    }

}
