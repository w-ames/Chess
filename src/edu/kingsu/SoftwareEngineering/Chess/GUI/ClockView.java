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
    JLabel playerColor = new JLabel();
    JLabel updateTime = new JLabel();
    GridBagConstraints gbpn = new GridBagConstraints();
    GridBagConstraints gbpt = new GridBagConstraints();
    Font pFont = new Font("Arial", Font.PLAIN, 25);
    Font tFont = new Font("Arial", Font.PLAIN, 35);
    Color clockColor = new Color(16, 46, 60);

    /**
     * Constructs ClockView objects and specifices their border, sets them to opaque
     * (visible).
     */
    public ClockView() {
        this.setLayout(new GridBagLayout());
        gbpn.fill = GridBagConstraints.VERTICAL;
        gbpn.gridy = 0;
        gbpn.weightx = 1;
        gbpn.weighty = 1;
        gbpn.anchor = GridBagConstraints.NORTHWEST;
        gbpt.fill = GridBagConstraints.VERTICAL;
        gbpt.gridx = 0;
        gbpt.gridy = 0;
        gbpt.weightx = 1;
        gbpt.weighty = 1;
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
     * @param name              The name to add to this instance of the chess clock.
     * @param playerColorString The color of the player to be displayed on this
     *                          clock view.
     */
    void addPlayerName(String name, String playerColorString) {
        // this.setLayout(new GridBagLayout());
        gbpn.gridx = 0;

        playerColor.setText(playerColorString);
        playerColor.setFont(pFont);
        playerColor.setForeground(clockColor);

        this.add(playerColor, gbpn);

        playername.setText(name);
        playername.setFont(pFont);
        playername.setForeground(clockColor);

        gbpn.gridx = 2;
        this.add(playername, gbpn);

    }

    /**
     * Updates the time for instances representing a player's chess clock.
     * 
     * @param time The time to be updated to.
     */
    public void updatePlayerTime(String time) {
        updateTime.setText(time);
        updateTime.setFont(pFont);
        updateTime.setForeground(clockColor);
        this.add(updateTime);
    }

    /**
     * Updates the time for instances representing the total game time clock.
     * 
     * @param time The time to be updated to.
     */
    public void updateTotalGameTime(String time) {
        // this.setLayout(new GridBagLayout());
        updateTime.setText(time);
        updateTime.setFont(tFont);
        updateTime.setForeground(clockColor);
        this.add(updateTime);
    }
}
