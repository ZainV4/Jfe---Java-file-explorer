package components.menu;
import javax.swing.*;
import java.awt.*;

public class extensionsBtn extends JButton{
    /***
     *  Button which makes extension frame visible
     * {Constructor}
     */
    public extensionsBtn() {
        // Button text
        this.setText("<html><h3>Extensions</h3></html>");
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
