package components.explorer;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.awt.*;


public class myExplorerFrame extends JFrame {

    JList<myDirectory> list = new JList<>();
    DefaultListModel<myDirectory> model = new DefaultListModel<>();
    File[] drives = File.listRoots();

    JLabel label = new JLabel();
    JPanel panel = new JPanel();
    JSplitPane splitPane = new JSplitPane();
    public myExplorerFrame () {
        // using the Color class to create my own color
        Color myBackgroundColor = new Color(93, 137, 178);

        // Puts a title to the frame
        this.setTitle("JFE");

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
        ///////////////////////////////////////////////////////////////////
        list.setModel(model);
        if (drives != null && drives.length > 0) {
            for (File aDrive : drives) {
                model.addElement(new myDirectory(aDrive));
            }
        }

        list.getSelectionModel().addListSelectionListener(e -> {
            myDirectory p = list.getSelectedValue();
            String contents[] = p.getFile().list();
            label.setText("Name: " + p.getFile());
            panel.removeAll();
            panel.updateUI();
            for(int i=0; i<contents.length; i++) {
                JButton btn =  new JButton(contents[i]);
                btn.setFocusPainted(false);
                btn.setMaximumSize(new Dimension(1870, 50));
                btn.setMinimumSize(new Dimension(1870, 50));
                panel.add(btn);
            }
        });

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(myBackgroundColor);
        splitPane.setLeftComponent(new JScrollPane(list));
        splitPane.setDividerLocation(220);
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setBounds(50, 30, 300, 50);
        splitPane.setRightComponent(scrollPane);
        this.add(splitPane);


        //////////////////////////////////////////////////////

        // frame resizing is possible
        this.setResizable(true);

        // Sets the size of the frame (x-dimension & y-dimension)
        this.setSize(1000, 1000);

        // by opening the first time the frame it will be Extended
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setJMenuBar(new navBar());


        // makes frame to the color of the variable myBackgroundColor
        this.getContentPane().setBackground(myBackgroundColor);

        

        // Making the frame visible
        this.setVisible(true);
    }

}
class myDirectory {
    File file;
    
    public myDirectory(File file) {
        this.file = file;
    }

    // get
    public File getFile() {
        return file;
    }
    // set
    public void setFile(File file) {
        this.file = file;
    }
    // change file into string
    @Override
    public String toString() {
        return file.toString();
    }

}
