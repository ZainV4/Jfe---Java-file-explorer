package components.menu;
import javax.swing.*;
import java.awt.*;

public class exitBtn extends JButton {
    /***
     * Button which exits the application
     * @param frame // Main Jframe
     */
    public exitBtn(JFrame frame) {
        // Button text
        this.setText("<html><h3>Exit</h3></html>");
        // Background color 
        this.setBackground(Color.black.brighter());
        // Text color
        this.setForeground(Color.LIGHT_GRAY.brighter());
        // Size of button
        this.setPreferredSize(new Dimension(170, 50));
        // removes white box around the text
        this.setFocusPainted(false);

        // on click event (does exits the application)
        this.addActionListener(e -> {
            frame.dispose();
        });
    }
}
