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
    JPanel panel = new JPanel();
    // JSplitPanel which splits root directories and children directories / files
    JSplitPane splitPane = new JSplitPane();
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

        // frame resizing is possible
        this.setResizable(true);

        // Sets the size of the frame (x-dimension & y-dimension)
        this.setSize(1000, 1000);

        // by opening the first time the frame it will be Extended
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);

        // add navBar to the frame
        navBar nb = new navBar();
        
        this.setJMenuBar(nb);

        // makes frame to the color of the variable myBackgroundColor
        this.getContentPane().setBackground(Color.WHITE);

        // Making the frame visible
        this.setVisible(true);
    }

}
class myDirectory {
    File file;
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
    // change file into string
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
    public void openNextFile(JButton btn, JPanel panel, String p) {
        Desktop desktop = Desktop.getDesktop();  
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // clear and update panel after click
                panel.removeAll();
                panel.updateUI();
                // if the source (onclicked item) is btn
                if(e.getSource() == btn) {
                    // The path
                    String path = p+ "\\" + ((JButton) e.getSource()).getText();
                    //  string ==> File
                    File directoryPath = new File(path);
                    if(directoryPath.isDirectory()) {
                        // Array of all the children files
                        String contents[] = directoryPath.list();
                        // looped the array contents
                        for(int i=0; i<contents.length; i++) {
                            JButton btn =  new JButton(contents[i]);
                            btn.setFocusPainted(false);
                            // size of button
                            btn.setMaximumSize(new Dimension(1870, 50));
                            // size of button
                            btn.setMinimumSize(new Dimension(1870, 50));
                            openNextFile(btn, panel, path);
                            // add button to the panel
                            panel.add(btn);
                        }
                    } else {
                        if(Desktop.isDesktopSupported()) {
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
}


