package uicomponents;

import javax.swing.*;

public class LoginFrame extends JFrame{
    private JPanel rootPanel;
    private JButton loginButton;
    private JTextField textField1;
    private JPasswordField passwordField1;

    public LoginFrame(){
        //set GUi according to form
        add(rootPanel);

        //size and visibility settings
        setSize(400,300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
}
