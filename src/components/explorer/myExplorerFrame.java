package components.explorer;
import javax.swing.*;
import java.awt.Color;
import javax.swing.JFrame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JOptionPane;


public class myExplorerFrame extends JFrame {
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


        // makes frame to the color of the variable myBackgroundColor
        this.getContentPane().setBackground(myBackgroundColor);

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

        // frame resizing is possible
        this.setResizable(true);

        // Sets the size of the frame (x-dimension & y-dimension)
        this.setSize(1000, 1000);

        // by opening the first time the frame it will be Extended
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);

        this.setJMenuBar(new navBar());

        // Making the frame visible
        this.setVisible(true);

    }

}
