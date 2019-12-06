/**
 * Entire application runs on App.
 * class is invoked by main to run on a single thread.
 */
public class PasswordManager implements Runnable{

    // Runs the app. no documentation needed.
    // App is very asynchronous, so the run method is basically a constructor.
    public void run(){
        // Creates new login window
        LoginFrame loginFrame = new LoginFrame(this);
    }

    /**
     * verify if user exists. decrypts and start app if user and pass is correct.
     * moves to main window if successful, tell login frame to display error otherwise.
     * @param username
     * @param password
     */
    public void verifyLogin(String username, String password){

        //

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