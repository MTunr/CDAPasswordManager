import javax.swing.*;

public class MainFrame extends JFrame{
    private JTable table1;
    private JPanel rootPanel;
    private JTextField searchField;
    private JButton searchButton;
    private JButton logoutButton;
    private JButton addButton;
    private JButton deleteButton;

    public MainFrame(){
        add(rootPanel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500,700);
        setVisible(true);
    }


}
