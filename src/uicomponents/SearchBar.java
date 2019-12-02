package uicomponents;

import javax.swing.*;
import java.awt.*;

/**
 * Search bar component to add on top of logged in window.
 * override search method to add functionality.
 */
public class SearchBar extends JPanel{

    public SearchBar(){
        JTextField searchField = new JTextField(15);
        JButton searchButton = new JButton("Search");

        add(searchField);
        add(searchButton);
    }

    /**
     * method to search when button is clicked. override to add functionality.
     */
    public void search(){};
}
