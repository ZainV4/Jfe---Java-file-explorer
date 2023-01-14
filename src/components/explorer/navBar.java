package components.explorer;
import java.awt.ComponentOrientation;
import java.awt.*;
import javax.swing.*;  

// class navBar extends JMenuBar which makes it abel for me to use this keyWord instant of 
// JMenuBar navbar = new JMenuBar() == navbar extends JMenuBar
public class navBar extends JMenuBar {
    static JButton SearchBtn;
    static JButton OnePathToBackBtn;
    static JButton OnePathToFontBtn;
    static JButton sortBtn;
    static JButton CreateBtn;
    /*
     * This function will display a navigation bar on top of the Explorer frame
     * {Constructor}
     * status: not done
     */
    public navBar() {
        // icons for main functions
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

        // main buttons with icons
        SearchBtn = new JButton(searchIcon);        
        OnePathToBackBtn = new JButton(leftArrowIcon);
        OnePathToFontBtn = new JButton(rightArrowIcon);
        sortBtn = new JButton(sortIcon);
        CreateBtn = new JButton(CreateIcon);

        // Search bar and Search btn (ActionListener)
        SearchBtn.addActionListener(e -> {
            myDirectory.searchBarFunction(t1.getText(), myExplorerFrame.panel);
            OnePathToFontBtn.setEnabled(false);
            OnePathToBackBtn.setEnabled(false);
        });

        // One path back btn (ActionListener)
        OnePathToBackBtn.addActionListener(e -> {
            myDirectory.OnePathToBackBtnFuntion(myExplorerFrame.panel, myDirectory.openNextPath);
        });

        // NOT COMPLETE (ActionListener)
        OnePathToFontBtn.addActionListener(e -> {
            //myDirectory.OnePathToFrontBtnFuntion(myExplorerFrame.panel, myDirectory.pathLol);
        });


        // Sets FocusPainted to false
        SearchBtn.setFocusPainted(false);
        OnePathToBackBtn.setFocusPainted(false);
        OnePathToFontBtn.setFocusPainted(false);
        sortBtn.setFocusPainted(false);
        CreateBtn.setFocusPainted(false);
        
        // Alignes the text from left to right
        this.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

        // add items to menu bar
        this.add(SearchBtn);
        this.add(label);
        this.add(t1);
        this.add(OnePathToBackBtn);
        this.add(OnePathToFontBtn);
        this.add(sortBtn);
        this.add(CreateBtn);
    }
}
