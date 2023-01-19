package components.menuComponents.buttons;

import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.image.*;
import java.io.*;
import javax.swing.*;


// class bottemImgs extends JButton which makes it abel for me to use this keyWord instant of 
// JButton button = new JButton() == bottemImags extends JButton
public class bottemImgs extends JPanel {
    public bottemImgs() {
        // try & catch block for error handling
        try {
            // Search for first image
            BufferedImage image = ImageIO.read(new File("Img\\menuIcons\\iconmonstr-cv-1-64.png"));
            // Search for second image
            BufferedImage image2 = ImageIO.read(new File("Img\\menuIcons\\iconmonstr-panorama-filled-64 (1).png"));
            // search for third image
            BufferedImage image3 = ImageIO.read(new File("Img\\menuIcons\\iconmonstr-layer-multiple-filled-64.png"));
            // creates a label which has the frist image in it
            JLabel label = new JLabel(new ImageIcon(image));
            // creates a label which has the second image in it
            JLabel label2 = new JLabel(new ImageIcon(image2));
            // creates a label which has the third image in it
            JLabel label3 = new JLabel(new ImageIcon(image3));
            // removes background
            this.setOpaque(false);
            // add all images to the menu
            this.add(label);
            this.add(label2);
            this.add(label3);
        } catch (IOException e) {
            this.add(new JLabel("Program is missing images. " +
             "Are you sure you downloaded everything?")); // prints error inside terminal
        }
    }
}
