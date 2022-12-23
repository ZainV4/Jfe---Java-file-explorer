import javax.swing.*;
import java.awt.*;
import java.util.Objects;

// class myLabel extends JLabel which makes it abel for me to use this keyWord instant of JLabel
// JFrame frame = new JFrame() == myFrame extends JFrame
public class myLabel extends JLabel {
    // constructor
    public myLabel() {
        // changes The png file into an Icon & searches the filename
        ImageIcon image = new ImageIcon("Img\\icons8-folder-50.png");
        // sets image to the class
        this.setIcon(image);
        // Outputs Text with html
        this.setText("<html><center>JFE</center><h1>your personal file manager</h1></html>");
        // Sets horizontal text position
        this.setHorizontalTextPosition(JLabel.CENTER);
        // Sets Vertical text position
        this.setVerticalTextPosition(JLabel.TOP);
        // Sets Text Color
        this.setForeground(Color.BLACK);
        // Sets Font Style
        this.setFont(new Font(Font.MONOSPACED,Font.BOLD,28));
        // Sets the gap between the Icon and the color
        this.setIconTextGap(20);
        // Sets Vertical position
        this.setVerticalAlignment(JLabel.TOP);
        // Sets horizontal position
        this.setHorizontalAlignment(JLabel.CENTER);
    }
}
