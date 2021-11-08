package edu.kingsu.SoftwareEngineering.Chess.GUI;

import java.awt.Color;
import javax.swing.*;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Font;
import javax.swing.border.*;
import java.awt.event.*;
import java.awt.event.MouseEvent;
import java.awt.Cursor;

/**
 * Styling of custom buttons for Java Chess.
 */
public class CustomButton extends JButton implements MouseListener {

    /**
     * Constructs custom buttons for Java Chess, specifies the border styling, the
     * text to be displayed on the button, and adds mouse listener.
     * 
     * @param buttonText Text to be displayed on the button.
     */
    public CustomButton(String buttonText) {
        this.setOpaque(true);
        this.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, new Color(64, 64, 64)));

        JLabel text = new JLabel(buttonText);
        text.setFont(new Font("Arial", Font.PLAIN, 20));
        text.setForeground(new Color(242, 242, 242));
        this.setText(buttonText);
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbForButton = new GridBagConstraints();
        gbForButton.fill = GridBagConstraints.CENTER;
        this.add(text, gbForButton);
        addMouseListener(this);

    }

    /**
     * Over rides JPanel's paintComponent to allow for gradient color.
     * 
     * @param g Graphics object to build gradient on.
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        int w = getWidth();
        int h = getHeight();
        GradientPaint gp = new GradientPaint(0, 0, new Color(64, 64, 64), 0, h, new Color(152, 152, 152));
        g2d.setPaint(gp);
        g2d.fillRect(0, 0, w, h);
        g2d.dispose();
    }

    /**
     * Changes the border of the button when the cursor hovers over it to provide a
     * hint that it is clickable.
     */
    @Override
    public void mouseEntered(MouseEvent e) {
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, new Color(96, 96, 96)));
    }

    /**
     * Changes the button's border back to defult when the cursor exits its
     * boundaries.
     */
    @Override
    public void mouseExited(MouseEvent e) {
        this.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, new Color(64, 64, 64)));
    }

    /**
     * Changes the border of the button when the button is clicked to help signal to
     * the user that the button has been clicked.
     */
    @Override
    public void mousePressed(MouseEvent e) {
        this.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, new Color(152, 152, 152)));
    }

    /**
     * Changes the button's border back to defult after the mouse click has been
     * completed.
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        this.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, new Color(64, 64, 64)));

    }

    public void mouseClicked(MouseEvent e) {

    }
}