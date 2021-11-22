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
import javax.swing.border.EmptyBorder;

/**
 * For displaying information about time/chess clock.
 * 
 * @author Chelsie Bajic
 * @since 10/2021
 */
public class ClockView extends JPanel {

    JLabel playername = new JLabel();
    JLabel updateTime = new JLabel();

    /**
     * Constructs ClockView objects and specifices their border, sets them to opaque
     * (visible).
     */
    public ClockView() {
        this.setOpaque(true);
        this.setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, new Color(191, 191, 191)));
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
        GradientPaint gp = new GradientPaint(0, 0, new Color(153, 153, 153), 0, h, new Color(242, 242, 242));
        g2d.setPaint(gp);
        g2d.fillRect(0, 0, w, h);
        g2d.dispose();

    }

    /**
     * Allows names to be added to representations of the chess clock.
     * 
     * @param name The name to add to this instance of the chess clock.
     * @author Chelsie Bajic
     */
    void addPlayerName(String name) {
        this.setLayout(new GridBagLayout());
        GridBagConstraints gb = new GridBagConstraints();
        gb.fill = GridBagConstraints.VERTICAL;
        gb.gridx = 0;
        gb.gridy = 0;
        gb.weightx = 1;
        gb.weighty = 1;
        gb.anchor = GridBagConstraints.NORTHWEST;
        playername.setText("   " + name);
        playername.setFont(new Font("Arial", Font.PLAIN, 25));
        playername.setForeground(new Color(16, 46, 60));
        this.add(playername, gb);
    }

    /**
     * Updates the time for instances representing a player's chess clock.
     * 
     * @param time The time to be updated to.
     */
    void updatePlayerTime(String time) {
        GridBagConstraints gb = new GridBagConstraints();
        gb.fill = GridBagConstraints.VERTICAL;
        gb.gridx = 0;
        gb.gridy = 0;
        gb.weightx = 1;
        gb.weighty = 1;
        updateTime.setText(time);
        updateTime.setFont(new Font("Arial", Font.PLAIN, 25));
        updateTime.setForeground(new Color(16, 46, 60));
        this.add(updateTime);
    }

    /**
     * Updates the time for instances representing the total game time clock.
     * 
     * @param time The time to be updated to.
     */
    void updateTotalGameTime(String time) {
        this.setLayout(new GridBagLayout());
        updateTime.setText(time);
        updateTime.setFont(new Font("Arial", Font.PLAIN, 35));
        updateTime.setForeground(new Color(16, 46, 60));
        this.add(updateTime);
    }
}