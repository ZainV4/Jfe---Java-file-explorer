package components.explorer;
import javax.swing.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.awt.*;

// class myFrame extends JFrame which makes it abel for me to use this keyWord instant of JFrame
// JFrame frame = new JFrame() == myFrame extends JFrame
public class myExplorerFrame extends JFrame {
    // Jlist of the class myDirectory
    JList<myDirectory> list = new JList<>();
    // Model for list (JList) / follows the MVC system
    DefaultListModel<myDirectory> model = new DefaultListModel<>();
    // All drives (root directories)
    File[] drives = File.listRoots();
    // Name of the directories or files
    JLabel label = new JLabel();
    // panel which contains all the directories and files
    static JPanel panel = new JPanel();
    // JSplitPanel which splits root directories and children directories / files
    JSplitPane splitPane = new JSplitPane();
    // icon for the buttons
    ImageIcon dirIcon = new ImageIcon("Img\\explorerIcons\\iconmonstr-folder-20-16.png");
    /*
    * frame which shows the explorer
    * {Constructor}
    * status: not done
    */
    public myExplorerFrame () {
        // Puts a title to the frame
        this.setTitle("Explorer");

        // Icon try and catch block
        try {
            // changes The png file into an Icon & searches the filename
            ImageIcon image = new ImageIcon("Img\\icons8-folder-50.png");
            // Sets new Icon
            this.setIconImage(image.getImage());
        } catch(Exception ex){
            System.out.println(ex);
        }
        
    
        // do nothing if user tries to exit
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        // event listener
        this.addWindowListener(new WindowAdapter() {
            //if user tries to exit ConfirmDialog will appear
            public void windowClosing(WindowEvent e) {
                JFrame frame = new JFrame();
                // write your code here
                if (JOptionPane.showConfirmDialog( frame, "Are you sure you want to quit?", 
                "Please confirm", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE) == JOptionPane.YES_OPTION) 
                {
                    System.exit(0);
                }
            }
        });

        // list gets the model added
        list.setModel(model);

        // checks if there is any root directories
        if (drives != null && drives.length > 0) {
            // loops the drives
            for (File aDrive : drives) {
                // aDrive will be added to the model
                model.addElement(new myDirectory(aDrive));
            }
        } else {
            new JLabel("no root directories");
        }

        // it will update the files or directories if a change occurs if list model is selected
        list.getSelectionModel().addListSelectionListener(e -> {
            // myDirectory will be equal to the selected dir / files
            myDirectory p = list.getSelectedValue();
            myDirectory.openNextPath = p.toString();
            // It will list all directories in p
            String contents[] = p.getFile().list();
            // reset the panel if p is selected
            panel.removeAll();
            panel.updateUI();
            // loops content array
            for(int i=0; i<contents.length; i++) {
                JButton btn =  new JButton(contents[i]);
                btn.setFocusPainted(false);
                btn.setMaximumSize(new Dimension(1870, 50));
                btn.setMinimumSize(new Dimension(1870, 50));
                btn.setIcon(dirIcon);
                btn.setHorizontalAlignment(SwingConstants.LEFT);
                btn.setFont(new Font("Arial", Font.PLAIN, 25));
                // calls openNextFile();
                p.openNextFile(btn, panel, p.toString());
                // btn will be added to the panel   
                panel.add(btn);
            }
        });

        // BoxLayout which does align everyting to the Y_Axis
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Color for background of the panel
        panel.setBackground(Color.WHITE);

        // Roots dir to the left of the splitpanel
        splitPane.setLeftComponent(new JScrollPane(list));

        // location of the divider
        splitPane.setDividerLocation(220);

        // Jscrollpanel to panel
        JScrollPane scrollPane = new JScrollPane(panel);

        // horizontal scroll bar only if needed 
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        // Virtical scroll bar always
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        // scrollPane to the right to splitPane
        splitPane.setRightComponent(scrollPane);

        // add splitPane to the Explorer Frame
        this.add(splitPane);

        // frame resizing is not possible anymore
        this.setResizable(false);

        // Sets the size of the frame (x-dimension & y-dimension)
        this.setSize(900, 700);

        // by opening the first time the frame it will be Extended
        //this.setExtendedState(JFrame.MAXIMIZED_BOTH);

        // add navBar to the frame
        navBar nb = new navBar();
        
        this.setJMenuBar(nb);

        // makes frame to the color of the variable myBackgroundColor
        this.getContentPane().setBackground(Color.WHITE);

        // Making the frame visible
        this.setVisible(true);
    }

}
class  myDirectory {
    //
    File file;
    //
    static JButton btn;
    //
    static String openNextPath;
    //
    static String pathLol;

    /***
     * The file in the class is = to the parametter
     * {constructor}
     * @param path
     */
    public myDirectory(File path) {
        this.file = path;

    }

    // Getter
    public File getFile() {
        return file;
    }
    // Setter
    public void setFile(File file) {
        this.file = file;
    }
    // Change file into string
    @Override
    public String toString() {
        return file.toString();
    }

    /***
     * Takes care of the onclick (ActionListener) procces
     * @param btn
     * @param panel
     * @param p
     */
    public static void openNextFile(JButton btn, JPanel panel, String p) {
        // icon for path btn
        ImageIcon dirIcon = new ImageIcon("Img\\explorerIcons\\iconmonstr-folder-20-16.png");
        // desktop class for opening the application / file
        Desktop desktop = Desktop.getDesktop();  
        // updates path & does open folder / file (ActionListener)
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Enables back and font btns if panel btn is clicked
                navBar.onePathBackwardBtn.setEnabled(true);
                navBar.onePathForwardtBtn.setEnabled(true);
                // clear and update panel after click
                panel.removeAll();
                panel.updateUI();
                // if the source (onclicked item) is btn
                if(e.getSource() == btn) {
                    // The path
                    // onlick on btn update openNextPath
                    openNextPath = p+ "\\" + ((JButton) e.getSource()).getText();
                    //  String ==> File
                    File directoryPath = new File(openNextPath);
                    if(directoryPath.isDirectory()) {
                        // Array of all the children files
                        String contents[] = directoryPath.list();
                        // looped the array contents
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
                            openNextFile(btn, panel, openNextPath);
                            // add button to the panel
                            panel.add(btn);
                        }
                    } else {
                        // if system has a Desktop
                        if(Desktop.isDesktopSupported()) {
                            // try opening it
                            try {
                                desktop.open(directoryPath);
                            } catch (Exception ex){
                                System.out.println(ex);
                            }
                        } else {
                            System.out.println("Desktop is not supported by this Platform");
                        }
                    }
                }
            }
        });
    }
    /***
     * takes care of the search bar function (searching for directories)
     * @param path
     * @param panel
     */
    public static void searchBarFunction(String path, JPanel panel) {
        ImageIcon dirIcon = new ImageIcon("Img\\explorerIcons\\iconmonstr-folder-20-16.png");
        
        // myDirectory will be equal to the selected dir / files
        try {
            // onlick on btn update openNextPath
            openNextPath = path;
            // String ==> File
            File dir = new File(openNextPath);
            // It will list all directories in p
            String contents[] = dir.list();
            // reset the panel if p is selected
            panel.removeAll();
            panel.updateUI();
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
                openNextFile(btn, panel, openNextPath);
                // add button to the panel
                panel.add(btn);
            }
        }catch(Exception e) {
            // frame for dialog
            JFrame frame = new JFrame();
            // dialog which tells the user that the path, he/she is trying to open does not exist
            JOptionPane.showMessageDialog( frame, "Hmm... I don't think this path exist", 
            "Do you understand?", JOptionPane.OK_OPTION);

        }
    
    }
    /***
     * takes care of the one-path-back function (does go one one time back to the past directory by each click)
     * @param panel
     * @param path
     */
    public static void OnePathToBackBtnFuntion(JPanel panel, String path) {
        ImageIcon dirIcon = new ImageIcon("Img\\explorerIcons\\iconmonstr-folder-20-16.png");

        try {
            // onlick on btn update openNextPath
            openNextPath = path;
            //StringBuffer (basiclly String which can be modified)
            StringBuffer sb= new StringBuffer(openNextPath);  
            // loop with does go less(path.length) by 1 each run
            for(int i = path.length()-1; i>0; i--) {
                // checks if path is really a Directory
                if (new File(path).isDirectory()) { 
                    // if path length is three or less, enable back-btn
                    if(!(path.length() <= 3)) {
                        // if path doesn't contain '\' remove last character
                        if(!(path.charAt(i) == '\\')) { 
                            // deletes last character
                            sb.deleteCharAt(sb.length()-1); 
                        } else {
                            // deletes last character
                            sb.deleteCharAt(sb.length()-1); 
                            break;
                        }
                    } else {
                        // disables btn if path length is less then or three
                        navBar.onePathBackwardBtn.setEnabled(false);
                    }
                } else {
                    // disables btn if path does not exist
                    navBar.onePathForwardtBtn.setEnabled(false);
                    break;
                }
            }
            // assigned sb as string to openNextPath
            openNextPath = sb.toString();
            // String ==> file
            File dir = new File(openNextPath);
            // It will list all directories in p
            String contents[] = dir.list();
            // reset the panel if p is selected
            panel.removeAll();
            panel.updateUI();
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
                openNextFile(btn, panel, openNextPath);
                // add button to the panel
                panel.add(btn);
            }
        }catch(Exception e) {
            // disables back-btn if path does not exist
            navBar.onePathBackwardBtn.setEnabled(false);
            JFrame frame = new JFrame();
            // write your code here
            JOptionPane.showMessageDialog( frame, e, 
            "Do you understand?", JOptionPane.OK_OPTION);

        }

    }
/* 
    public static void OnePathToFrontBtnFuntion(JPanel panel, String thePath) {
        ImageIcon dirIcon = new ImageIcon("Img\\explorerIcons\\iconmonstr-folder-20-16.png");

        try {
            System.out.println(thePath);  

            File dir = new File(thePath);
            // It will list all directories in p
            String contents[] = dir.list();
            // reset the panel if p is selected
            panel.removeAll();
            panel.updateUI();
            // loops content array
            for(int i=0; i<contents.length; i++) {
                btn =  new JButton(contents[i]);
                btn.setFocusPainted(false);
                btn.setMaximumSize(new Dimension(1870, 50));
                btn.setMinimumSize(new Dimension(1870, 50));
                btn.setIcon(dirIcon);
                btn.setHorizontalAlignment(SwingConstants.LEFT);
                btn.setFont(new Font("Arial", Font.PLAIN, 25));
                // calls openNextFile();
                openNextFile(btn, panel, thePath);
                // btn will be added to the panel   
                panel.add(btn);
            }
        }catch(Exception e) {
            JFrame frame = new JFrame();
            // write your code here
            JOptionPane.showMessageDialog( frame, "Hmm... I don't think this path exist", 
            "Do you understand?", JOptionPane.OK_OPTION);
        }
    }
    */
}


