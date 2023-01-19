package components.explorer;
import java.awt.ComponentOrientation;
import java.io.File;
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
    Boolean isFolder;
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

        // main buttons with icons & ToolTip
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

    /***
     * Creates Files and folders inside 
     * 
     */
    public void createFunction() {
        // array of Strings for JComboBox 
        String[] Options = { "Folder", "File" };

        // frame which contains function
        JFrame frame = new JFrame("The Creating Window");  
        // main panel
        JPanel CreatePanel = new JPanel();
        // give the panel a BoxLayout
        CreatePanel.setLayout(new BoxLayout(CreatePanel, BoxLayout.Y_AXIS));

        // title for path holder
        JLabel sourceFieldContext = new JLabel("{Path}");
        // sets font & size for the path title(sourceFieldContext)
        sourceFieldContext.setFont(new Font("Anton", Font.BOLD, 20));
        // alignment path holder(sourceFieldContext) to center
        sourceFieldContext.setAlignmentX(JLabel.CENTER_ALIGNMENT);

        // path holder conatains selected path
        JTextField sourceAddressField = new JTextField(myDirectory.openNextPath);
        // set background color
        sourceAddressField.setBackground(Color.WHITE);
        // set to not Editable
        sourceAddressField.setEditable(false);
        // set border & set border to black and make the thinkness to 2
        sourceAddressField.setBorder(new LineBorder(Color.BLACK, 2));
        // center object
        sourceAddressField.setHorizontalAlignment(JTextField.CENTER);
        // sets font & size for sourceAddressField
        sourceAddressField.setFont(new Font("Anton", Font.BOLD, 20));

        // creates scrollBar
        JScrollBar scrollBar = new JScrollBar(JScrollBar.HORIZONTAL);
        // Create  model for sourceAddressField
        BoundedRangeModel brm = sourceAddressField.getHorizontalVisibility();
        
        // place holder
        JLabel nothing2 = new JLabel(" ");
        // sets font & size for place holder
        nothing2.setFont(new Font("Anton", Font.BOLD, 17));
        // hintText for newDirectoryField
        JLabel hintText = new JLabel("Choose a new name");
        // center hintText
        hintText.setAlignmentX(JLabel.CENTER_ALIGNMENT);


        // place holder
        JLabel nothing = new JLabel(" ");
        // user input new name of path
        JTextField newDirectoryField = new JTextField();
        // set border & set border to black and make the thinkness to 2
        newDirectoryField.setBorder(new LineBorder(Color.BLACK, 2));
        // sets font & size for sourceAddressField
        newDirectoryField.setFont(new Font("Anton", Font.BOLD, 20));


        // option box with {Folder, File}
        JComboBox<String> optionsBox = new JComboBox<String>(Options);
        String getItem = (String)optionsBox.getSelectedItem();
        
        if(getItem.equals(Options[0])) {
            isFolder = true;
        } else {
            isFolder = false;
        }
        optionsBox.setFont(new Font("Anton", Font.BOLD, 20));

        // panel with contains create and cancel buttens
        JPanel buttons = new JPanel(new GridBagLayout());
        // Cancel & Create button [and font & 17]
        JButton createBtn = new JButton("CREATE");
        createBtn.setFont(new Font("Anton", Font.BOLD, 17));
        createBtn.addActionListener(e -> {
            if(isFolder == true) {
                try {
                    File myObj = new File(newDirectoryField.getText());
                    if (myObj.mkdir()) {
                    System.out.println("File created: " + myObj.getName());
                    } else {
                    System.out.println("File already exists.");
                    }
                } catch (Exception ex) {
                    // frame for dialog
                    JFrame dialog = new JFrame();
                    // dialog which tells the user that the path, he/she is trying to open does not exist
                    JOptionPane.showMessageDialog(dialog, e, "Do you understand?", JOptionPane.OK_OPTION);
                }
            } else {
                try {
                    File myObj = new File(newDirectoryField.getText());

                    if (myObj.createNewFile()) {
                    System.out.println("Folder created: " + myObj.getName());
                    } else {
                    System.out.println("Folder already exists.");
                    }
                } catch (Exception ex) {
                    // frame for dialog
                    JFrame dialog = new JFrame();
                    // dialog which tells the user that the path, he/she is trying to open does not exist
                    JOptionPane.showMessageDialog(dialog, e, "Do you understand?", JOptionPane.OK_OPTION);
                }
            }
            });
        JButton cancelBtn = new JButton("CANCEL");
        cancelBtn.setFont(new Font("Anton", Font.BOLD, 17));

        // remove Paint focus around the button
        createBtn.setFocusPainted(false);
        cancelBtn.setFocusPainted(false);

        // adding everything togehter
        buttons.add(createBtn);
        buttons.add(cancelBtn);


        CreatePanel.setBorder(BorderFactory.createTitledBorder("Let's Create"));

        
        // adding everything togther
        scrollBar.setModel(brm);
        CreatePanel.add(sourceFieldContext);
        CreatePanel.add(nothing2);
        CreatePanel.add(sourceAddressField);
        CreatePanel.add(scrollBar);
        CreatePanel.add(nothing);
        CreatePanel.add(hintText);
        CreatePanel.add(newDirectoryField);
        CreatePanel.add(optionsBox);
        CreatePanel.add(buttons);

        // frame resizing is not possible anymore
        frame.setResizable(false);
        frame.add(CreatePanel, BorderLayout.NORTH);
        frame.setSize(500, 300);  
        frame.setLocationRelativeTo(null);  
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);  
    }
}
