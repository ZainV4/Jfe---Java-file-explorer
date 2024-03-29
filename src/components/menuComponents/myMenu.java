package components.menuComponents;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import components.menuComponents.buttons.bottemImgs;
import components.menuComponents.buttons.exitBtn;
import components.menuComponents.buttons.getStartedBtn;
import components.menuComponents.buttons.githubBtn;

import java.awt.Color;

// class myMenu extends JPanel which makes it abel for me to use this keyWord instant of 
// JPanel panel = new JPanel() == myMenu extends JPanel
public class myMenu extends JPanel {
     /***
      * This Method add a menu to the Jframe
      * {Constructor}
      * @param frame
      */
    public myMenu(JFrame frame) {
        // Background color for the frame & JPanel
        Color myBackgroundColor = new Color(93, 137, 178);


        // Makes a border which is perfectly centered
        setBorder(new EmptyBorder(10, 10, 10, 10));

        // Layout managers with it you can use  rows and columns to align
        setLayout(new GridBagLayout());

        // Every item/object is set to default
        GridBagConstraints gbc = new GridBagConstraints();

        // Sets every column in a center vertical line
        gbc.gridwidth = GridBagConstraints.REMAINDER;


        // Title with HTML
        JLabel myTitle = new JLabel("<html><h1><strong><i><center>JFE</center></i></strong></h1></br><h2><strong><i>your personal file manager</i></strong></h2><hr></html>");
        // Title text is set to black
        myTitle.setForeground(Color.BLACK);
        // Sets font and size of title
        myTitle.setFont(new Font("Anton", Font.PLAIN, 18));
        // Adds the menu title
        this.add(myTitle, gbc);
        // Set item into center
        gbc.anchor = GridBagConstraints.CENTER;
        // Filles the item to the right size
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // JPanel of Buttons which will contain the menu buttons
        JPanel buttons = new JPanel(new GridBagLayout());
        // Sets the background color of JPanel {buttons}
        buttons.setBackground(myBackgroundColor);
        // Buttons with the gbc features
        buttons.add(new getStartedBtn(frame), gbc);      
        buttons.add(new githubBtn(), gbc);
        //buttons.add(new extensionsBtn(), gbc);
        buttons.add(new exitBtn(frame), gbc);

        // Specifies how to distribute extra vertical space with in the items/objects 
        gbc.weighty = .25;

        // Adds the JPanel buttons to the menu 
        this.add(buttons, gbc);
        // Adds the class bottemImgs (which contains the three pictures on the bottem of menu)
        this.add(new bottemImgs());
        // Sets the background color of Menu
        this.setBackground(myBackgroundColor);
    }
}
