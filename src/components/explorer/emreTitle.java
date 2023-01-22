package components.explorer;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

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
