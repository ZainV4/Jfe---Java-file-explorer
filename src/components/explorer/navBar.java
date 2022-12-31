package components.explorer;
import java.awt.TextComponent;
import java.awt.Dimension;
import java.awt.ComponentOrientation;
import java.awt.*;

import javax.swing.*;  

public class navBar extends JMenuBar {
    /*
     * this function will display a navigation bar on top of the Explorer frame
     * {Constructor}
     * status: not done
     */
    public navBar() {
        JTextField t1 = new JTextField();
        JLabel label = new JLabel("   Path finder:   ");
        Font font = new Font("Verdana", Font.PLAIN,17);
        label.setFont(font);
        JButton btn = new JButton("Click me");
        JButton btn1 = new JButton("Click me");
        JButton btn2 = new JButton("Click me");
        JButton btn3 = new JButton("Click me");
        JButton btn4 = new JButton("Click me");
        //
        btn.setFocusPainted(false);
        btn1.setFocusPainted(false);
        btn2.setFocusPainted(false);
        btn3.setFocusPainted(false);
        btn4.setFocusPainted(false);
        //
        
        this.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        this.add(Box.createHorizontalGlue());
        // add menu to menu bar
        this.add(btn4);
        this.add(label);
        this.add(t1);
        this.add(btn);
        this.add(btn1);
        this.add(btn2);
        this.add(btn3);
        
    }
}
