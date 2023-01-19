package components.explorer;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Color;

public class emreTitle extends JFrame {
	JLabel title = new JLabel();
	JLabel light_title = new JLabel();
	Color blue = new Color(43, 220, 20);
    public emreTitle() {
        JButton b=new JButton("Next");  
        b.addActionListener(e -> {
            new myExplorerFrame();
            this.setVisible(false);
        });
        b.setBounds(100,100,100,100);  
        this.setTitle("Emre + Zain");
        this.add(b);  
        this.setSize(400,400);  
        this.setLayout(null);  
        this.setVisible(true);   
    }
}
