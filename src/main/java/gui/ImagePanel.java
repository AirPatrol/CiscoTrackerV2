/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Daan
 */
public class ImagePanel extends JPanel {

    private Point pnt;

    public ImagePanel() {
        pnt = null;
    }

    public void setPoint(Point p) {
        pnt = p;
        this.repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.repaint();
        g.setColor(Color.BLUE);
        g.fillOval(pnt.x - 5, pnt.y - 5, 10, 10);
    }
}
