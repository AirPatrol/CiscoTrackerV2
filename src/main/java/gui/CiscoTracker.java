/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.List;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import oauth.RequestsManager;
import position.PersonHistory;
import position.PositionManager;

/**
 *
 * @author Daan
 */
public class CiscoTracker extends javax.swing.JFrame {

    /**
     * Creates new form CiscoTracker
     */
    private ImagePanel p;
    private ArrayList<String> accessTokens;
    private RequestsManager m;
    private PositionManager pm;
    private JPanel namePanel;
    private JTextField tfAddToken;

    public CiscoTracker() {
        //this.addImage();
        this.addImagePanel();
        this.addNamePanel();
        this.addTfAddToken();
        this.accessTokens = new ArrayList<>();
        accessTokens.add("a28abe9bcc68acb5d3e2acb1695b7d81");
        this.m = new RequestsManager();
        this.pm = new PositionManager();
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1200, 500));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public ImagePanel getImagePanel() {
        return p;
    }

    public ArrayList<PersonHistory> getHistory() {
        ArrayList<PersonHistory> histories = new ArrayList<>();
        for (String s : accessTokens) {
            try {
                histories.add(new PersonHistory(m.getName(s), m.getHistory(s)));
            } catch (IOException ex) {
                Logger.getLogger(CiscoTracker.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return histories;
    }

    public void DrawHistory(ArrayList<PersonHistory> histories) {

        for (int i = 0; i < 10; i++) {
            ArrayList<Point> pointsToDraw = new ArrayList<>();
            int j = 0;
            namePanel.removeAll();
            for (PersonHistory hist : histories) {
                double x = hist.getMovementHistory().get(i).getX();
                double y = hist.getMovementHistory().get(i).getY();
                pointsToDraw.add(pm.translateToImageXY(x, y));

                this.addNamesToPanel(hist.getName() + ", " + hist.getMovementHistory().get(i).getLocatedDateTime(), j * 20, p.getColor(j));
                j++;
            }

            p.setPoints(pointsToDraw);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(CiscoTracker.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        this.DrawHistory(this.getHistory());
    }

    private void addNamesToPanel(String text, int posY, Color c) {
        JLabel lbl = new JLabel();
        lbl.setText(text);
        lbl.setLocation(0, posY);
        lbl.setForeground(c);

        namePanel.add(lbl);
        namePanel.revalidate();
    }

    private void addImagePanel() {
        this.p = new ImagePanel();
        p.setSize(800, 330);
        p.setBackground(Color.WHITE);
        add(p);
    }

    private void addNamePanel() {
        namePanel = new JPanel();
        namePanel.setLocation(new Point(800, 20));
        namePanel.setSize(400, 500);
        this.add(namePanel);
    }

    private void addTfAddToken() {
        tfAddToken = new JTextField(10);
        tfAddToken.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newToken = tfAddToken.getText();
                tfAddToken.setText("");
                if (newToken.length() == 32) {
                    accessTokens.add(newToken);
                }
            }
        });
        tfAddToken.setLocation(new Point(0, 340));
        tfAddToken.setSize(225, 25);
        this.add(tfAddToken);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
