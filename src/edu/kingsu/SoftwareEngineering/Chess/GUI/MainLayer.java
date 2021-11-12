package edu.kingsu.SoftwareEngineering.Chess.GUI;

import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.*;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 * MainLayer creates a JPanel that provides the background color of the
 * application's style theme. (Blue gradient)
 * 
 * @author Chelsie Bajic
 * @since 10/2021
 */
public class MainLayer extends JPanel {

    /**
     * Overrides JPanel's paintComponent to allow for gradient paint.
     * 
     * @param g Graphics object to build gradient on.
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        int w = getWidth();
        int h = getHeight();
        GradientPaint gp = new GradientPaint(0, 0, new Color(16, 46, 60), 0, h, new Color(37, 108, 141));

        g2d.setPaint(gp);
        g2d.fillRect(0, 0, w, h);

        g2d.dispose();
    }

}