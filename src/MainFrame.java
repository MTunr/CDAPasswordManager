import javax.swing.*;
import java.awt.event.ActionEvent;

public class MainFrame extends JFrame{
    private JTable table1;
    private JPanel rootPanel;
    private JTextField searchField;
    private JButton searchButton;
    private JButton logoutButton;
    private JButton addButton;
    private JButton deleteButton;

    private PasswordManager passwordManager;
    private AddFrame addFrame;
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
    class AddListener extends AbstractAction{

        // create Frame reference for the add window.
        MainFrame mainFrame;
        public AddListener(MainFrame caller){
            mainFrame = caller;
        }

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            // Opens new AddFrame to add account
            addFrame = new AddFrame(mainFrame);
        }
    }

    class LogoutListener extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            int confirmResult = JOptionPane.showConfirmDialog(null, "Are you sure you want to log out?");
            if (confirmResult == JOptionPane.YES_OPTION){
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
