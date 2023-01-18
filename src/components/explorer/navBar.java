package components.explorer;
import java.awt.ComponentOrientation;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;  

// class navBar extends JMenuBar which makes it abel for me to use this keyWord instant of 
// JMenuBar navbar = new JMenuBar() == navbar extends JMenuBar
public class navBar extends JMenuBar {
    static JButton searchBtn;
    static JButton onePathBackwardBtn;
    static JButton onePathForwardtBtn;
    static JButton sortBtn;
    static JButton createBtn;
    static JButton updatePanelBtn;
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
        ImageIcon createIcon = new ImageIcon("Img\\navbarIcons\\iconmonstr-text-28-16.png");
        ImageIcon updatePanelIcon = new ImageIcon("Img\\navbarIcons\\iconmonstr-refresh-lined-16.png");

        // Input for searchBar
        JTextField t1 = new JTextField();

        // Title text for t1
        JLabel label = new JLabel("   Path finder:   ");

        // Font and size  for the label
        Font font = new Font("Verdana", Font.PLAIN,17);

        // Adds font to the label
        label.setFont(font);

        // main buttons with icons
        searchBtn = new JButton(searchIcon);  
        searchBtn.setToolTipText("start the search function");  
            
        onePathBackwardBtn = new JButton(leftArrowIcon);
        onePathBackwardBtn.setToolTipText("goes one path backwards");

        onePathForwardtBtn = new JButton(rightArrowIcon);
        onePathForwardtBtn.setToolTipText("goes one path forwards");

        sortBtn = new JButton(sortIcon);
        sortBtn.setToolTipText("opens a new window which shows sort options");

        createBtn = new JButton(createIcon);
        createBtn.setToolTipText("opens a new window which shows the create function");

        updatePanelBtn = new JButton(updatePanelIcon);
        updatePanelBtn.setToolTipText("updates the panel which shows the content of directory");
        // Search bar and Search btn (ActionListener)
        searchBtn.addActionListener(e -> {
            myDirectory.searchBarFunction(t1.getText(), myExplorerFrame.panel);
            onePathForwardtBtn.setEnabled(false);
            onePathBackwardBtn.setEnabled(false);
        });

        // One path back btn (ActionListener)
        onePathBackwardBtn.addActionListener(e -> {
            myDirectory.OnePathToBackBtnFuntion(myExplorerFrame.panel, myDirectory.openNextPath);
        });

        // NOT COMPLETE (ActionListener)
        onePathForwardtBtn.addActionListener(e -> {
            //myDirectory.OnePathToFrontBtnFuntion(myExplorerFrame.panel, myDirectory.pathLol);
        });

        createBtn.addActionListener(e -> {
            createFunction();;
        });

        updatePanelBtn.addActionListener(e -> {
            myExplorerFrame.panel.updateUI();
        });


        // Sets FocusPainted to false
        searchBtn.setFocusPainted(false);
        onePathBackwardBtn.setFocusPainted(false);
        onePathForwardtBtn.setFocusPainted(false);
        sortBtn.setFocusPainted(false);
        createBtn.setFocusPainted(false);
        updatePanelBtn.setFocusPainted(false);
        
        // Alignes the text from left to right
        this.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

        // add items to menu bar
        this.add(searchBtn);
        this.add(label);
        this.add(t1);
        this.add(onePathBackwardBtn);
        this.add(onePathForwardtBtn);
        this.add(sortBtn);
        this.add(createBtn);
        this.add(updatePanelBtn);
    }
    public void createFunction() {
        String[] Options = { "Folder", "File" };

        JFrame frame = new JFrame("The Creating Window");  
        JPanel panel = new JPanel();
        // give the panel a BoxLayout
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        // Contains the Address where the object is going to be created
        JLabel sourceFieldContext = new JLabel("{Path}");
        sourceFieldContext.setFont(new Font("Anton", Font.BOLD, 20));
        sourceFieldContext.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        JTextField sourceAddressField = new JTextField(myDirectory.openNextPath);
        sourceAddressField.setBackground(Color.WHITE);
        sourceAddressField.setEditable(false);
        sourceAddressField.setBorder(new LineBorder(Color.BLACK, 2));
        sourceAddressField.setHorizontalAlignment(JTextField.CENTER);
        sourceAddressField.setFont(new Font("Anton", Font.BOLD, 20));
        

        // Contains name of the new created object 
        JLabel nothing = new JLabel(" ");
        JTextField newDirectoryField = new JTextField();
        newDirectoryField.setBorder(new LineBorder(Color.BLACK, 2));
        newDirectoryField.setFont(new Font("Anton", Font.BOLD, 20));
        
        JScrollBar scrollBar = new JScrollBar(JScrollBar.HORIZONTAL);
        BoundedRangeModel brm = sourceAddressField.getHorizontalVisibility();

        JLabel nothing2 = new JLabel(" ");
        nothing2.setFont(new Font("Anton", Font.BOLD, 17));
        JLabel hintText = new JLabel("Choose a new name");
        hintText.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        // panel with contains create and cancel buttens
        JPanel buttons = new JPanel(new GridBagLayout());
        // Cancel & Create button
        JButton createBtn = new JButton("CREATE");
        createBtn.setFont(new Font("Anton", Font.BOLD, 17));
        JButton cancelBtn = new JButton("CANCEL");
        cancelBtn.setFont(new Font("Anton", Font.BOLD, 17));

        // remove Paint focus around the button
        createBtn.setFocusPainted(false);
        cancelBtn.setFocusPainted(false);

        // adding everything togehter
        buttons.add(createBtn);
        buttons.add(cancelBtn);

        // option box with {Folder, File}
        JComboBox optionsBox = new JComboBox(Options);
        optionsBox.setFont(new Font("Anton", Font.BOLD, 20));


        panel.setBorder(BorderFactory.createTitledBorder("Let's Create"));

        
        // adding everything togther
        scrollBar.setModel(brm);
        panel.add(sourceFieldContext);
        panel.add(nothing2);
        panel.add(sourceAddressField);
        panel.add(scrollBar);
        panel.add(nothing);
        panel.add(hintText);
        panel.add(newDirectoryField);
        panel.add(optionsBox);
        panel.add(buttons);

        // frame resizing is not possible anymore
        frame.setResizable(false);
        frame.add(panel, BorderLayout.NORTH);
        frame.setSize(500, 300);  
        frame.setLocationRelativeTo(null);  
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);  
    }
}
