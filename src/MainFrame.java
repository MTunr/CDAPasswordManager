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

    public MainFrame(PasswordManager caller){
        //assign reference to caller
        this.passwordManager = caller;

        // default window size and visibility settings
        add(rootPanel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500,700);
        setVisible(true);

        //set listeners for buttons
        addButton.addActionListener(new AddListener());
        deleteButton.addActionListener(new DeleteListener());
        searchButton.addActionListener(new SearchListener());
        logoutButton.addActionListener(new LogoutListener());
    }

    // ----- Listener classes ----- //
    class AddListener extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
        }
    }
    class DeleteListener extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent actionEvent) {

        }
    }
    class SearchListener extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
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
}
