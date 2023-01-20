package components.explorer;
import java.awt.ComponentOrientation;
import java.io.File;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;  
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import java.awt.FlowLayout;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
// class navBar extends JMenuBar which makes it abel for me to use this keyWord instant of 
// JMenuBar navbar = new JMenuBar() == navbar extends JMenuBar
public class navBar extends JMenuBar {
    static JButton searchBtn;
    static JButton onePathBackwardBtn;
    static JButton onePathForwardtBtn;
    static JButton sortBtn;
    static JButton createBtn;
    static JButton updatePanelBtn;
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

        optionsBox.addActionListener(event -> {
            if (optionsBox.getSelectedItem().equals("Folder")) {
                isFolder = true;
            } else if (optionsBox.getSelectedItem().equals("File")) {
                isFolder = false;
            }
        });
        optionsBox.setFont(new Font("Anton", Font.BOLD, 20));

        // panel with contains create and cancel buttens
        JPanel buttons = new JPanel(new GridBagLayout());
        // Cancel & Create button [and font & 17]
        JButton createBtn = new JButton("CREATE");
        createBtn.setFont(new Font("Anton", Font.BOLD, 17));
        createBtn.addActionListener(e -> {
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
        JButton cancelBtn = new JButton("CANCEL");
        cancelBtn.addActionListener(e -> {
            frame.dispose();
        });
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
