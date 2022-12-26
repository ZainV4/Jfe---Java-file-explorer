package components.menu;

import javax.swing.JButton;
import javax.swing.*;
import java.awt.*;
import java.net.URI;
import java.net.URL;

public class githubBtn extends JButton {
    /***
     * Button which does send user to the github website
     * {Constructor}
     */
    public githubBtn() {
        // Button text
        this.setText("<html><h3>Github</h3></html>");
        // Background color 
        this.setBackground(Color.black.brighter());
        // Text color
        this.setForeground(Color.LIGHT_GRAY.brighter());
        // Size of button
        this.setPreferredSize(new Dimension(170, 50));
        // removes white box around the text
        this.setFocusPainted(false);


        // on click event (does send user to the github page)
        this.addActionListener(e -> {
            try {
                Desktop.getDesktop().browse(new URI("https://github.com/ZainV4/Jfe---Java-file-explorer"));
            } catch (Exception u) {
                System.out.println(u);
            }
        });
    }
}
