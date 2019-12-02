import javax.swing.*;

public class PasswordManager{
    public static void main(String[] args) {
        debugUI();
    }

    private static void debugUI(){
        AppWindow window = new AppWindow();
        SwingUtilities.invokeLater(window);
    }

}

class AppWindow implements Runnable{

    /**
     * runs entire UI of application.
     */
    public void run(){
        createWindow();
        System.out.println("Hello");
    }

    /**
     * Creates blank window
     * @pre-condition: none
     * @post-condition: new empty JFrame window is created and displayed.
     */
    void createWindow(){
        // Window size
        final int WINDOW_HEIGHT = 500;
        final int WINDOW_WIDTH = 500;

        // Generate JFrame from swing
        JFrame AppWindow = new JFrame();
        AppWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        AppWindow.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        AppWindow.setVisible(true);
    }
}