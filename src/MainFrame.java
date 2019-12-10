import javax.swing.*;
import java.awt.event.ActionEvent;

public class MainFrame extends JFrame{
    private JTable table1;
    private JPanel rootPanel;
    private JTextField searchField;
    private JButton searchButton;
    private JButton logoutButton;
    private JButton addButton;

    private PasswordManager passwordManager;
    /**
     * Constructor
     * @param caller reference to caller object (PasswordManager).
     */
    public MainFrame(PasswordManager caller){
        //assign reference to caller
        this.passwordManager = caller;

        //set gui according to form
        add(rootPanel);

        // default window size and visibility settings
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(700,600);
        setVisible(true);
        setTitle("CDA Password Manager");

        //set frame to be at center of screen
        setLocationRelativeTo(null);

        //set listeners for buttons
        addButton.addActionListener(new AddListener(this));
        searchButton.addActionListener(new SearchListener());
        logoutButton.addActionListener(new LogoutListener());
    }

    // ----- Functional Methods ----- //
    //TODO: implement
    void addAccount(String serviceName, String username, String Password){
        // TODO : implement method to update table, procedually adding buttons and details.
    }

    // ----- Listener classes ----- //

    /**
     * Listener for the add button. creates a new frame to input account details
     */
    static class AddListener extends AbstractAction{

        // Set reference to main frame (for button)
        MainFrame mainFrame;
        public AddListener(MainFrame caller){
            mainFrame = caller;
        }

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            // Opens new AddFrame to add account
            AddFrame addFrame = new AddFrame(mainFrame);
        }
    }

    /**
     * Listener for Logout button. creates dialog window to confirm exit.
     */
    class LogoutListener extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            // create dialog window.
            int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure you want to log out?", "confirm Logout", JOptionPane.YES_NO_OPTION);
            // Logout if yes button is pressed. otherwise, do nothing.
            if (dialogResult == JOptionPane.YES_OPTION){
                passwordManager.logout();
            }
        }
    }

    //TODO: implement
    class SearchListener extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
        }
    }

}
