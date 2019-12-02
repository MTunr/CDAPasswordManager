import uicomponents.SearchBar;
import javax.swing.*;
import java.awt.*;

/**
 * Main class
 */
public class PasswordManager{
    public static void main(String[] args) {
        App passwordManagerApp = new App();
        SwingUtilities.invokeLater(passwordManagerApp);
    }
}

/**
 * Entire application runs on App.
 * class is invoked by main to run, that's it.
 */
class App implements Runnable{
    /**
     * runs entire UI of application.
     */
    public void run(){
        final int WINDOW_HEIGHT = 500;
        final int WINDOW_WIDTH = 500;
        // Create new empty window
        MainFrame appWindow = new MainFrame(WINDOW_WIDTH, WINDOW_HEIGHT);
        setMainScreen(appWindow);
    }

    // Functionality methods
    void login(){}
    void logout(){}
    void addAccount(){}
    void deleteAccount(){}
    void confirmDelete(){}

    // UI generation methods
    void setLoginScreen(){}
    void popupDeleteWindow(){}
    void popupAddWindow(){}
    void popupConfirmWindow(){}

    //TODO: refactor, recode and basically re-write this entire method using uicomponents.
    void setMainScreen(MainFrame appWindow){
        appWindow.setLayout(new BorderLayout());
        SearchBar mainSearchBar = new SearchBar();
        appWindow.add(mainSearchBar, BorderLayout.PAGE_START);
    }
}

//TODO: move this to uicomponents.
/**
 * Default frame class to generate windows.
 */
class MainFrame extends JFrame{
    /**
     * Frame constructor class
     * @param windowWidth width of window in pixels
     * @param windowHeight height of window in pixels
     */
    public MainFrame(int windowWidth, int windowHeight){
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(windowWidth, windowHeight);
        setVisible(true);
    }
}