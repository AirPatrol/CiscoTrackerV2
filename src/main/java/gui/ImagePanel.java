/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 *
 * @author Daan
 */
public class ImagePanel extends JPanel {

    private BufferedImage img;
    private ArrayList<Point> points;
    private ArrayList<Color> colors;

    public ImagePanel() {
        points = new ArrayList<>();
        colors = new ArrayList<>();
        for (int i = 0; i < 25; i++){
            colors.add(new Color((int)(Math.random() * 0x1000000)));
        }
        try {
            img = ImageIO.read(new File("C:\\Users\\Daan\\Google Drive\\ICS proftaak\\ziekelijk mooie kaart.png"));
        } catch (IOException ex) {
            Logger.getLogger(ImagePanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setPoints(ArrayList<Point> p) {
        points = p;
        this.repaint();
    }
    
    public Color getColor(int index){
        return colors.get(index);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.repaint();
        g.drawImage(img, 0, 0, null);
        int i = 0;
        for (Point p : points){
            g.setColor(colors.get(i));
            g.fillOval(p.x - 5, p.y - 5, 10, 10);
            i++;
        }
    }
}
