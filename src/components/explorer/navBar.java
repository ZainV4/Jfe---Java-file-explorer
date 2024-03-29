package components.explorer;
import java.awt.ComponentOrientation;
import java.io.File;
import java.text.Collator;
import java.util.Arrays;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;  
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JComboBox;
// class navBar extends JMenuBar which makes it abel for me to use this keyWord instant of 
// JMenuBar navbar = new JMenuBar() == navbar extends JMenuBar
public class navBar extends JMenuBar {
    // starts search function (btn)
    static JButton searchBtn;
    // goes one path backward (btn)
    static JButton onePathBackwardBtn;
    // goes one path forward (btn)
    static JButton onePathForwardtBtn;
    // sorts items alphabetical (btn)
    static JButton alphabeticalSortBtn;
    // sorts items unalphabetical (btn)
    static JButton unalphabeticalSortBtn;
    // opens create frame (btn)
    static JButton createBtn;
    // refreshs content (btn)
    static JButton updatePanelBtn;
    // checks if directory is a folder
    Boolean isFolder = true;
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
        ImageIcon alphabeticalSortIcon = new ImageIcon("Img\\navbarIcons\\iconmonstr-sort-14-16.png");
        ImageIcon unalphabeticalSortIcon = new ImageIcon("Img\\navbarIcons\\iconmonstr-sort-16-16.png");
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

        alphabeticalSortBtn = new JButton(alphabeticalSortIcon);
        alphabeticalSortBtn.setToolTipText("sorts all files and folders in a aplhabetical order");

        unalphabeticalSortBtn = new JButton(unalphabeticalSortIcon);
        unalphabeticalSortBtn.setToolTipText("sorts all files and folders in a unaplhabetical order");

        createBtn = new JButton(createIcon);
        createBtn.setToolTipText("opens a new window which shows the create function");

        updatePanelBtn = new JButton(updatePanelIcon);
        updatePanelBtn.setToolTipText("updates the panel which shows the content of directory");


        // Search bar and Search button (ActionListener)
        searchBtn.addActionListener(e -> {
            myDirectory.searchBarFunction(t1.getText(), myExplorerFrame.panel);
            onePathForwardtBtn.setEnabled(false);
            onePathBackwardBtn.setEnabled(false);
        });
        // One path backward button (ActionListener)
        onePathBackwardBtn.addActionListener(e -> {
            backwardsFunction();
        });
        // one path forward button (ActionListener)
        onePathForwardtBtn.addActionListener(e -> {
            forwardsFunction();
        });
        // creating file or folder button (ActionListener)
        createBtn.addActionListener(e -> {
            createFunction();;
        });
        // sort content alphabetically (ActionListener)
        alphabeticalSortBtn.addActionListener(e -> {
            alphabeticalSortFunction();

        });
        // sort content unalphabetically (ActionListener)
        unalphabeticalSortBtn.addActionListener(e -> {
            unalphabeticalSortFunction();

        });
        // update content inside panel button (ActionListener)
        updatePanelBtn.addActionListener(e -> {
            myExplorerFrame.panel.removeAll();
            myExplorerFrame.panel.updateUI();
            ImageIcon dirIcon = new ImageIcon("Img\\explorerIcons\\iconmonstr-folder-20-16.png");
        
            // myDirectory will be equal to the selected dir / files
            try {
                // String ==> File
                File dir = new File(myDirectory.openNextPath);
                // It will list all directories in p
                String contents[] = dir.list();
                // reset the panel if p is selected
                myExplorerFrame.panel.removeAll();
                myExplorerFrame.panel.updateUI();
                // loops content array
                for(int i=0; i<contents.length; i++) {
                    // sets btn name content
                    JButton btn =  new JButton(contents[i]);
                    // sets icon 
                    btn.setIcon(dirIcon);
                    // align btn text to the left
                    btn.setHorizontalAlignment(SwingConstants.LEFT);
                    // sets font
                    btn.setFont(new Font("Arial", Font.PLAIN, 25));
                    // removes focus 
                    btn.setFocusPainted(false);
                    // size of button
                    btn.setMaximumSize(new Dimension(1870, 50));
                    // size of button
                    btn.setMinimumSize(new Dimension(1870, 50));
                    myDirectory.openNextFile(btn, myExplorerFrame.panel, myDirectory.openNextPath);
                    // add button to the panel
                    myExplorerFrame.panel.add(btn);
                }
            }catch(Exception ex) {
                // frame for dialog
                JFrame frame = new JFrame();
                // dialog which tells the user that the path, he/she is trying to open does not exist
                JOptionPane.showMessageDialog( frame, "Hmm... I don't think this path exist", "Do you understand?", JOptionPane.OK_OPTION);
            }
            myExplorerFrame.panel.updateUI();
        });


        // Sets FocusPainted to false
        searchBtn.setFocusPainted(false);
        onePathBackwardBtn.setFocusPainted(false);
        onePathForwardtBtn.setFocusPainted(false);
        alphabeticalSortBtn.setFocusPainted(false);
        unalphabeticalSortBtn.setFocusPainted(false);
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
        this.add(alphabeticalSortBtn);
        this.add(unalphabeticalSortBtn);
        this.add(createBtn);
        this.add(updatePanelBtn);
    }

    /***
     * sorts string array in alphabetical order or ascending order
     * 
     */
    public void alphabeticalSortFunction() {
        // clears and updates panel
        myExplorerFrame.panel.removeAll();
        myExplorerFrame.panel.updateUI();
        // icon for buttons
        ImageIcon dirIcon = new ImageIcon("Img\\explorerIcons\\iconmonstr-folder-20-16.png");
        // String ==> File
        File dir = new File(myDirectory.openNextPath);
        // It will list all directories in p
        String contents[] = dir.list();  
        Arrays.sort(contents, Collator.getInstance());  
        // reset the panel if p is selected
        myExplorerFrame.panel.removeAll();
        myExplorerFrame.panel.updateUI();
        // loops content array
        for(int i=0; i<contents.length; i++) {
            // sets btn name content
            JButton btn =  new JButton(contents[i]);
            // sets icon 
            btn.setIcon(dirIcon);
            // align btn text to the left
            btn.setHorizontalAlignment(SwingConstants.LEFT);
            // sets font
            btn.setFont(new Font("Arial", Font.PLAIN, 25));
            // removes focus 
            btn.setFocusPainted(false);
            // size of button
            btn.setMaximumSize(new Dimension(1870, 50));
            // size of button
            btn.setMinimumSize(new Dimension(1870, 50));
            myDirectory.openNextFile(btn, myExplorerFrame.panel, myDirectory.openNextPath);
            // add button to the panel
            myExplorerFrame.panel.add(btn);
        }
    }

    /***
     * sorts string array in unalphabetical order or ascending order
     * 
     */
    public void unalphabeticalSortFunction() {
        // clears and updates panel
        myExplorerFrame.panel.removeAll();
        myExplorerFrame.panel.updateUI();
        // icon for buttons
        ImageIcon dirIcon = new ImageIcon("Img\\explorerIcons\\iconmonstr-folder-20-16.png");
        // String ==> File
        File dir = new File(myDirectory.openNextPath);
        // It will list all directories in p
        String contents[] = dir.list();
        Arrays.sort(contents, Collator.getInstance().reversed());     
        // reset the panel if p is selected
        myExplorerFrame.panel.removeAll();
        myExplorerFrame.panel.updateUI();
        // loops content array
        for(int i=0; i<contents.length; i++) {
            // sets btn name content
            JButton btn =  new JButton(contents[i]);
            // sets icon 
            btn.setIcon(dirIcon);
            // align btn text to the left
            btn.setHorizontalAlignment(SwingConstants.LEFT);
            // sets font
            btn.setFont(new Font("Arial", Font.PLAIN, 25));
            // removes focus 
            btn.setFocusPainted(false);
            // size of button
            btn.setMaximumSize(new Dimension(1870, 50));
            // size of button
            btn.setMinimumSize(new Dimension(1870, 50));
            myDirectory.openNextFile(btn, myExplorerFrame.panel, myDirectory.openNextPath);
            // add button to the panel
            myExplorerFrame.panel.add(btn);
        }
    }
    /***
     * opens a frame which creates folders or files 
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
        // checks option
        optionsBox.addActionListener(event -> {
            if (optionsBox.getSelectedItem().equals("Folder")) {
                isFolder = true;
            } else if (optionsBox.getSelectedItem().equals("File")) {
                isFolder = false;
            }
        });
        // sets font for optionBox content
        optionsBox.setFont(new Font("Anton", Font.BOLD, 20));

        // panel with contains create and cancel buttens
        JPanel buttons = new JPanel(new GridBagLayout());
        // Create button 
        JButton createFolderOrFileBtn = new JButton("CREATE");
        // sets font for createBtn 
        createFolderOrFileBtn.setFont(new Font("Anton", Font.BOLD, 17));

        createFolderOrFileBtn.addActionListener(e -> {
            if(isFolder == true) {
                try {
                    File myObj = new File(myDirectory.openNextPath+"\\"+newDirectoryField.getText());
                    if (myObj.mkdir() && myObj != null) {
                        // frame for dialog
                        JFrame create = new JFrame();
                        // dialog which tells the user that the path, he/she is trying to open does not exist
                        JOptionPane.showMessageDialog(create,"Folder create: "+ myObj.getName(),"Folder dialog" , JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        // frame for dialog
                        JFrame create = new JFrame();
                        // dialog which tells the user that the path, he/she is trying to open does exist
                        JOptionPane.showMessageDialog(create,"Folder already exists "+ myObj.getName(),"Folder dialog" , JOptionPane.INFORMATION_MESSAGE);
                    }
                } catch (Exception ex) {
                    // frame for dialog
                    JFrame dialog = new JFrame();
                    // dialog which tells the error
                    JOptionPane.showMessageDialog(dialog, "Do you understand?", "Folder dialog", JOptionPane.OK_OPTION);
                }
            } else if (isFolder == false) {
                try {
                    File myObj = new File(myDirectory.openNextPath+"\\"+newDirectoryField.getText());
                    if (myObj.createNewFile()) {
                        // frame for dialog
                        JFrame dialog = new JFrame();
                        // dialog which tells the error
                        JOptionPane.showMessageDialog(dialog, "File create: "+ myObj.getName(), "Folder dialog", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        // frame for dialog
                        JFrame dialog = new JFrame();
                        // dialog which tells the error
                        JOptionPane.showMessageDialog(dialog, "Folder already exists: "+ myObj.getName(), "Folder dialog", JOptionPane.INFORMATION_MESSAGE);
                    }
                } catch (Exception ex) {
                    // frame for dialog
                    JFrame dialog = new JFrame();
                    System.out.print(ex);
                    // dialog which tells the error
                    JOptionPane.showMessageDialog(dialog, "OH somthing went wrong", "Folder dialog", JOptionPane.OK_OPTION);

                }
            }
            frame.dispose();
        });
        // cancel button for Create-frame button
        JButton cancelFolderOrFileBtn = new JButton("CANCEL");
        // cancelFolderOrFileBtn (ActionListener)
        cancelFolderOrFileBtn.addActionListener(e -> {
            frame.dispose();
        });
        // sets font for cancelFolderOrFileBtn
        cancelFolderOrFileBtn.setFont(new Font("Anton", Font.BOLD, 17));

        // remove Paint focus around the button
        createFolderOrFileBtn.setFocusPainted(false);
        cancelFolderOrFileBtn.setFocusPainted(false);

        // adding everything togehter
        buttons.add(createFolderOrFileBtn);
        buttons.add(cancelFolderOrFileBtn);


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

    public static void backwardsFunction() {
        try {
            // icon for directory
            ImageIcon dirIcon = new ImageIcon("Img\\explorerIcons\\iconmonstr-folder-20-16.png");
            // if only one item left disable/enabled buttons
            if(myDirectory.clickedPathsBackward.size() <= 1 ) {
                //Enables back and font btns if panel btn is clicked
                navBar.onePathBackwardBtn.setEnabled(false);
                navBar.onePathForwardtBtn.setEnabled(true);
            }
            System.out.println(myDirectory.openNextPath);
            myDirectory.clickedPathsForward.push(myDirectory.openNextPath);
            myDirectory.clickedPathsBackward.pop();
            myDirectory.openNextPath = myDirectory.clickedPathsBackward.lastElement();
            // String ==> File
            File dir = new File(myDirectory.openNextPath);
            // It will list all directories in p
            String contents[] = dir.list();
            // reset the panel if p is selected
            myExplorerFrame.panel.removeAll();
            myExplorerFrame.panel.updateUI();
            // loops content array
            for(int i=0; i<contents.length; i++) {
                // sets btn name content
                JButton btn =  new JButton(contents[i]);
                // sets icon 
                btn.setIcon(dirIcon);
                // align btn text to the left
                btn.setHorizontalAlignment(SwingConstants.LEFT);
                // sets font
                btn.setFont(new Font("Arial", Font.PLAIN, 25));
                // removes focus 
                btn.setFocusPainted(false);
                // size of button
                btn.setMaximumSize(new Dimension(1870, 50));
                // size of button
                btn.setMinimumSize(new Dimension(1870, 50));
                myDirectory.openNextFile(btn, myExplorerFrame.panel, myDirectory.openNextPath);
                // add button to the panel
                myExplorerFrame.panel.add(btn);
            }
        } catch (Exception ex) {
            System.out.print(ex);
        }
    }

    public static void forwardsFunction() {
        // prints last element inside console
        System.out.println(myDirectory.clickedPathsForward.lastElement());
        try {
            // icon for directory
            ImageIcon dirIcon = new ImageIcon("Img\\explorerIcons\\iconmonstr-folder-20-16.png");
            // if only one item left disable/enabled buttons
            if(myDirectory.clickedPathsForward.size() <= 1 ) {
                //Enables back and font btns if panel btn is clicked
                navBar.onePathBackwardBtn.setEnabled(true);
                navBar.onePathForwardtBtn.setEnabled(false);
            }
            // insert new item inside clickedPathBackward
            myDirectory.clickedPathsBackward.push(myDirectory.openNextPath);
            // remove last element form stack
            myDirectory.clickedPathsForward.pop();
            // assigns last element to the variable openNextPath
            myDirectory.openNextPath = myDirectory.clickedPathsForward.lastElement();
            // String ==> File
            File dir = new File(myDirectory.openNextPath);
            // It will list all directories in p
            String contents[] = dir.list();
            // reset the panel if p is selected
            myExplorerFrame.panel.removeAll();
            myExplorerFrame.panel.updateUI();
            // loops content array
            for(int i=0; i<contents.length; i++) {
                // sets btn name content
                JButton btn =  new JButton(contents[i]);
                // sets icon 
                btn.setIcon(dirIcon);
                // align btn text to the left
                btn.setHorizontalAlignment(SwingConstants.LEFT);
                // sets font
                btn.setFont(new Font("Arial", Font.PLAIN, 25));
                // removes focus 
                btn.setFocusPainted(false);
                // size of button
                btn.setMaximumSize(new Dimension(1870, 50));
                // size of button
                btn.setMinimumSize(new Dimension(1870, 50));
                myDirectory.openNextFile(btn, myExplorerFrame.panel, myDirectory.openNextPath);
                // add button to the panel
                myExplorerFrame.panel.add(btn);
                
            }
        } catch (Exception ex) {
    
        }
        
    }
}
