import javax.swing.*;
import java.awt.event.ActionEvent;

public class AddFrame extends JFrame{
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
    public AddFrame(MainFrame caller){

        //set reference to main app.
        this.mainFrame = caller;

        //Disable mainFrame until user adds/cancels
        mainFrame.setEnabled(false);

        //set gui according to form
        add(rootPanel);

        //size and visibility settings
        setSize(300,150);


        //set frame to be at center of screen
        setLocationRelativeTo(null);

        // Set button listeners
        cancelButton.addActionListener(new CancelListener());
        addButton.addActionListener(new AddListener());

        setUndecorated(true);
        setVisible(true);
    }

    // ----- Listener classes ----- //
    class CancelListener extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            // closes frame, but not application.
            mainFrame.setEnabled(true);
            AddFrame.super.dispose();
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
            mainFrame.setEnabled(true);
            mainFrame.addAccount(serviceName, username, password);
            AddFrame.super.dispose();
        }
    }

}
