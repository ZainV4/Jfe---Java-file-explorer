package components.explorer;
import java.awt.ComponentOrientation;
import java.awt.*;
import javax.swing.*;  

// class navBar extends JMenuBar which makes it abel for me to use this keyWord instant of 
// JMenuBar navbar = new JMenuBar() == navbar extends JMenuBar
public class navBar extends JMenuBar {
    String path;
    /*
     * This function will display a navigation bar on top of the Explorer frame
     * {Constructor}
     * status: not done
     */
    public navBar() {
        ImageIcon leftArrowIcon = new ImageIcon("Img\\navbarIcons\\iconmonstr-arrow-left-alt-filled-16.png");
        ImageIcon rightArrowIcon = new ImageIcon("Img\\navbarIcons\\iconmonstr-arrow-right-alt-filled-16.png");
        ImageIcon searchIcon = new ImageIcon("Img\\navbarIcons\\iconmonstr-folder-29-16.png");
        ImageIcon sortIcon = new ImageIcon("Img\\navbarIcons\\iconmonstr-sort-27-16.png");
        ImageIcon CreateIcon = new ImageIcon("Img\\navbarIcons\\iconmonstr-text-28-16.png");

        // Input for searchBar
        JTextField t1 = new JTextField();

        // Title text for t1
        JLabel label = new JLabel("   Path finder:   ");

        // Font and size  for the label
        Font font = new Font("Verdana", Font.PLAIN,17);

        // Adds font to the label
        label.setFont(font);

        // Random buttons
        JButton SearchBtn = new JButton(searchIcon);
        SearchBtn.addActionListener(e -> {
            //setPath(t1.getText());
            myDirectory.searchBarFunction(t1.getText(), myExplorerFrame.panel);
        });
        
        
        JButton OnePathToBackBtn = new JButton(leftArrowIcon);
        JButton OnePathTofontBtn = new JButton(rightArrowIcon);
        JButton sortBtn = new JButton(sortIcon);
        JButton CreateBtn = new JButton(CreateIcon);


        // Sets FocusPainted to false
        SearchBtn.setFocusPainted(false);
        OnePathToBackBtn.setFocusPainted(false);
        OnePathTofontBtn.setFocusPainted(false);
        sortBtn.setFocusPainted(false);
        CreateBtn.setFocusPainted(false);
        
        // Alignes the text from left to right
        this.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

        // add items to menu bar
        this.add(SearchBtn);
        this.add(label);
        this.add(t1);
        this.add(OnePathToBackBtn);
        this.add(OnePathTofontBtn);
        this.add(sortBtn);
        this.add(CreateBtn);
    }

    // Setter
    public void setPath(String path) {
        this.path = path;
    }

    //getter
    public String getPath() {
        return path;
    }
}
