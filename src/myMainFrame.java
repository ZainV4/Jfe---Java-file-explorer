import javax.swing.*;
import java.awt.Color;
import javax.swing.JFrame;


// class myFrame extends JFrame which makes it abel for me to use this keyWord instant of JFrame
// JFrame frame = new JFrame() == myFrame extends JFrame
public class myMainFrame extends JFrame {
    /***
     * This is a starting Frame which containes the menu
     * {constructor}
     */
    public myMainFrame () {

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

        // close/exit the app/window
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // frame resizing is not possible anymore
        this.setResizable(false);

        // Sets the size of the frame (x-dimension & y-dimension)
        this.setSize(800, 600);

        // does add the Menu
        this.add(new myMenu(this));
        // Making the frame visible
        this.setVisible(true);

    }
}
