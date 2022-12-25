package components.menu;

import javax.swing.JButton;
import javax.swing.*;
import java.awt.*;
import java.net.URI;
import java.net.URL;

public class githubBtn extends JButton {
    public githubBtn() {
        //ImageIcon icon = new ImageIcon("Img\\icons8-folder-50.png");
        this.setText("<html><h3>Github</h3></html>");
        this.setBackground(Color.black.brighter());
        this.setForeground(Color.LIGHT_GRAY.brighter());
        this.setPreferredSize(new Dimension(170, 50));
        this.setFocusPainted(false);


        this.addActionListener(e -> {
            try {
                Desktop.getDesktop().browse(new URI("https://github.com/ZainV4/Jfe---Java-file-explorer"));
            } catch (Exception u) {
                System.out.println(u);
            }
        });
    }
}
