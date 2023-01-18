package components.menuComponents;

import javax.swing.JButton;
import javax.swing.*;
import java.awt.*;
import java.net.URI;
import java.net.URL;

// class githubBtn extends JButton which makes it abel for me to use this keyWord instant of 
// JButton button = new JButton() == githubBtn extends JButton
public class githubBtn extends JButton {
    /***
     * Button which does send user to the github website
     * {Constructor}
     */
    public githubBtn() {
        // Button text
        this.setText("<html><h3>Github</h3></html>");
        // Background color 
        this.setBackground(Color.BLACK.brighter());
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
