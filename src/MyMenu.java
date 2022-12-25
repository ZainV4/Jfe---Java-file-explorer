import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

// import my Own class
import components.menu.exitBtn;
import components.menu.getStartedBtn;
import components.menu.githubBtn;
import components.menu.extensionsBtn;
import components.menu.bottemImgs;

import java.awt.Color;

// class myFrame extends JPanel which makes it abel for me to use this keyWord instant of JPanel
// JFrame frame = new JPanel() == myPanel extends JPanel
public class MyMenu extends JPanel {
    // constructor
    public MyMenu() {
        Color myBackgroundColor = new Color(93, 137, 178);


        // makes a border which is perfectly centered
        setBorder(new EmptyBorder(10, 10, 10, 10));

        // layout managers with it you can use  rows and columns to align
        setLayout(new GridBagLayout());


        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.NORTH;

        JLabel myTitle = new JLabel("<html><h1><strong><i><center>JFE</center></i></strong></h1></br><h2><strong><i>your personal file manager</i></strong></h2><hr></html>");
        myTitle.setForeground(Color.BLACK);
        myTitle.setFont(new Font("Anton", Font.PLAIN, 18));
        // Menu Title
        this.add(myTitle, gbc);
        // set item into center
        gbc.anchor = GridBagConstraints.CENTER;
        // filles the item to the right size
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // JPanel of Buttons which will contain the menu buttons
        JPanel buttons = new JPanel(new GridBagLayout());
        buttons.setBackground(myBackgroundColor);
        // Buttons with the gbc features
        buttons.add(new getStartedBtn(), gbc);      
        buttons.add(new githubBtn(), gbc);
        buttons.add(new extensionsBtn(), gbc);
        buttons.add(new exitBtn(), gbc);

        // Specifies how to distribute extra vertical space with in the items/objects 
        gbc.weighty = .25;

        this.add(buttons, gbc);
        this.add(new bottemImgs());
        this.setBackground(myBackgroundColor);
    }
}
