package components.menu;

import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.swing.*;

public class bottemImgs extends JPanel {
    public bottemImgs() {
        try {
            BufferedImage image = ImageIO.read(new File("Img\\iconmonstr-cv-1-64.png"));
            BufferedImage image2 = ImageIO.read(new File("Img\\iconmonstr-panorama-filled-64 (1).png"));
            BufferedImage image3 = ImageIO.read(new File("Img\\iconmonstr-layer-multiple-filled-64.png"));
            JLabel label = new JLabel(new ImageIcon(image));
            JLabel label2 = new JLabel(new ImageIcon(image2));
            JLabel label3 = new JLabel(new ImageIcon(image3));
            this.setOpaque(false);
            this.add(label);
            this.add(label2);
            this.add(label3);
        } catch (IOException e) {

        }
    }
}
