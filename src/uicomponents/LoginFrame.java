package uicomponents;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class LoginFrame extends JFrame{
    private JPanel rootPanel;
    private JButton loginButton;
    private JTextField textField1;
    private JPasswordField passwordField1;

    /**
     * Constructor class: creates login window
     */
    public LoginFrame(){
        //set GUi according to form
        add(rootPanel);

        //size and visibility settings
        setSize(400,300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);

        // set event listener for button
        loginButton.addActionListener(new ButtonListener());
    }

    class ButtonListener extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            // Get information from fields
            String username = textField1.getText();
            String password = String.valueOf(passwordField1.getPassword());
            verifyLogin(username, password);
        }
    }

    void verifyLogin(String username, String password){
        //TODO: add logic to verify login
        if(username.contentEquals(password)){
            System.out.println("yes");
        } else {
            System.out.println("no");
        }

    }

}