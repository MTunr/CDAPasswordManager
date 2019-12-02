import javax.swing.*;

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
    }
}

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