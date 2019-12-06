import uicomponents.LoginFrame;
import uicomponents.MainFrame;
import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Entire application runs on App.
 * class is invoked by main to run on a single thread.
 */
public class PasswordManager implements Runnable{

    /**
     * main method. Deals with all execution and UI controls.
     */
    public void run(){
        login();
    }

    // Functionality methods
    void login() {
        LoginFrame loginFrame = new LoginFrame();
    }

    /*
    void logout(){}
    void addAccount(){}
    void deleteAccount(){}
    void confirmDelete(){}

    // UI generation methods
    void setLoginScreen(){}
    void setDeleteWindow(){}
    void setAddWindow(){}
    void setConfirmWindow(){}
    void setMainWindow(){}
     */
}