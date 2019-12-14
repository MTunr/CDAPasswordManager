import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.util.ArrayList;


/**
 * Main window of CDA Password Manager
 */
public class MainFrame extends JFrame{
    private JTable accountsTable;
    private JPanel rootPanel;
    private JButton logoutButton;
    private JButton editButton;
    private JButton hidePasswordsButton;
    private JButton addButton;
    private JButton deleteSelectedButton;

    private PasswordManager passwordManager;
    private ArrayList<Account> accountList;

    private boolean isEditing = false;

    /**
     * Constructor
     * @param caller reference to caller object (PasswordManager).
     */
    public MainFrame(PasswordManager caller, ArrayList<Account> accountsInput){
        //set references to PasswordManager elements
        accountList = accountsInput;
        passwordManager = caller;

        // take form data to frame
        add(rootPanel);

        // default window size and visibility settings
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(700,600);
        setResizable(false);
        setVisible(true);
        setTitle("CDA Password Manager");
        addButton.setVisible(false);
        deleteSelectedButton.setVisible(false);
        setLocationRelativeTo(null); //set frame to be at center of screen

        //set listeners for buttons
        logoutButton.addActionListener(new LogoutListener());
        editButton.addActionListener(new EditListener());
        addButton.addActionListener(new AddListener(this));

        //setup table
        String[] columnNames = {"Service", "Username", "Password", "Recovery Keys"};
        setupTable(columnNames);
        //
        System.out.println(accountList);
    }

    /**
     * Sets up table with column names and row data
     * @param columnNames text on column headers
     */
    void setupTable(Object[] columnNames /*, account list*/){
        // Initial table prevents cell editing
        DefaultTableModel tableModel = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        accountsTable.setModel(tableModel);
        accountsTable.setRowSelectionAllowed(false);
        accountsTable.setCellSelectionEnabled(false);

        //Setup columns
        for (Object columnName : columnNames) {
            tableModel.addColumn(columnName);
        }

        //TODO: get data from accounts list
        for(int i = 0; i < accountList.size(); i++){
            Object[] rowData = {
                    accountList.get(i).getServiceName(),
                    accountList.get(i).getServiceName(),
                    accountList.get(i).getServiceName(),
                    accountList.get(i).getServiceName()
            };
            tableModel.addRow(rowData);
        }
    }

    /**
     * Sets the frame to edit mode.
     * Edit mode enables adding and deleting rows.
     */
    void changeEditMode() {
        if (isEditing){
            isEditing = false;
            addButton.setVisible(false);
            deleteSelectedButton.setVisible(false);
            logoutButton.setVisible(true);
            hidePasswordsButton.setVisible(true);
            editButton.setText("Edit");
            super.setTitle("CDA Password Manager");
        } else {
            isEditing = true;
            addButton.setVisible(true);
            deleteSelectedButton.setVisible(true);
            logoutButton.setVisible(false);
            hidePasswordsButton.setVisible(false);
            editButton.setText("Finish Editing");

            super.setTitle("CDA Password Manager - Edit Mode");
        }
    }

    /**
     * Logs the user out, saves the account data into accountList.
     * PasswordManager will handle encrypting.
     */
    void logout(){
        // create dialog window.
        int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure you want to log out?", "confirm Logout", JOptionPane.YES_NO_OPTION);
        // Logout if yes button is pressed. otherwise, do nothing.
        if (dialogResult == JOptionPane.YES_OPTION){
            // parse accountList here
            for (int i = 0; i < accountsTable.getRowCount(); i++){
                //TODO: convert table contents to accountList.
            }
            passwordManager.logout();
        }
    }

    /**
     * Adds new account to the list
     * @param serviceName
     * @param username
     * @param password
     */
    void addAccount(String serviceName, String username, String password){
    }

    /**
     * Deletes account selected by the user
     */
    void deleteSelected(){
    }

    // ----- Button Listener Classes ----- //
    /**
     * Listener for Delete Selected Button.
     * Deletes rows selected by User.
     */
    class DeleteListener extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            deleteSelected();
        }
    }

    /**
     * Listener for Add Button.
     * pops a new AddFrame to input details
     */
    class AddListener extends AbstractAction{
        MainFrame mainFrame;
        public AddListener(MainFrame caller){
            mainFrame = caller; // assign reference to caller MainFrame
        }
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            AddFrame addFrame = new AddFrame(mainFrame);
        }
    }

    /**
     * Listener for Logout button. creates dialog window to confirm exit.
     */
    class LogoutListener extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            logout();
        }
    }

    /**
     * Listener for Edit button. Changes the frame to edit mode.
     */
    class EditListener extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            changeEditMode();
        }
    }
}