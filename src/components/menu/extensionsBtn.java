package components.menu;
import javax.swing.*;
import java.awt.*;

public class extensionsBtn extends JButton{
    public extensionsBtn() {
        //ImageIcon icon = new ImageIcon("Img\\icons8-folder-50.png");
        this.setText("<html><h3>Extensions</h3></html>");
        this.setBackground(Color.black.brighter());
        this.setForeground(Color.LIGHT_GRAY.brighter());
        this.setPreferredSize(new Dimension(170, 50));
        this.setFocusPainted(false);
    }
}