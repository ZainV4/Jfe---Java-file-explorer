package components.menu;
import javax.swing.*;
import java.awt.*;

public class getStartedBtn extends JButton {
    /***
     * Button which does make the Jfe visible
     * {Constructor}
     */
    public getStartedBtn () {
        // Button text
        this.setText("<html><h2>Get Started</h2></html>");
        // Background color 
        this.setBackground(Color.black.brighter());
        // Text color
        this.setForeground(Color.LIGHT_GRAY.brighter());
        // Size of button
        this.setPreferredSize(new Dimension(170, 50));
        // removes white box around the text
        this.setFocusPainted(false);
    }
}
