/**
 * Entire application runs on App.
 * class is invoked by main to run on a single thread.
 */
public class PasswordManager implements Runnable{

    // Frame (window) class references
    LoginFrame loginFrame;
    MainFrame mainFrame;

    // Runs the app. no documentation needed.
    // App is very asynchronous, so the run method is basically a constructor.
    public void run(){
        // Creates new login window
        login();
    }

    /**
     * Creates a new login window
     */
    void login(){
        this.loginFrame = new LoginFrame(this);
    }

    /**
     * Destroys main window, creates new login window
     */
    void logout(){
        mainFrame.dispose();
        this.loginFrame = new LoginFrame(this);
    }

    /**
     * verify if user exists. decrypts and start app if user and pass is correct.
     * moves to main window if successful, tell login frame to display error otherwise.
     * @param username username obtained from usernameField
     * @param password password obtained from passwordField
     */
    public void verifyLogin(String username, String password){
        // TODO: add login logic
        // debug code: logs in if user == pass
        boolean loginValid = false;
        if (username.contentEquals(password)){
            loginValid = true;
        }

        // Hide login window if correct. if not, display incorrect login.
        if (loginValid){
            loadMain();
            // delete login window.
            loginFrame.dispose();
        } else {
            loginFrame.displayWrongLogin();
        }
    }

    /**
     * Loads main application with valid login details
     */
    void loadMain(){
        //TODO: add file reading and CDA to this method (to load main window)
        this.mainFrame = new MainFrame(this);
    }
}