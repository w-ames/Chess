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
import java.awt.Insets;
import java.awt.Font;

/**
 * Represents the view of notifications that communicates information about
 * gameplay to the user.
 */
public class ChessGameMessagesView extends ChessGameView {

    private JPanel notificationDisplayPanel = new JPanel();
    JLabel addNotification = new JLabel();

    /**
     * Constructs the notification JPanel.
     */
    public ChessGameMessagesView() {
        this.setBackground(Color.WHITE);
        this.setOpaque(true);
        this.setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, new Color(191, 191, 191)));

        // Add title and panel where notifications display panel.
        this.setLayout(new GridBagLayout());
        GridBagConstraints gb = new GridBagConstraints();
        gb.fill = GridBagConstraints.BOTH;
        gb.anchor = GridBagConstraints.CENTER;
        gb.gridy = 0;
        gb.gridx = 0;
        gb.weightx = 1;
        gb.weighty = 0.25;
        gb.gridheight = 1;
        gb.gridwidth = 1;
        JLabel notificationsLabel = new JLabel("Notifications");
        notificationsLabel.setHorizontalAlignment(SwingConstants.CENTER);
        notificationsLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        notificationsLabel.setForeground(new Color(16, 46, 60));
        notificationDisplayPanel.setOpaque(true);
        this.add(notificationsLabel, gb);

        // Add the first notification to the notification display panel.
        notificationDisplayPanel.setBackground(new Color(40, 40, 40));
        addToNotifications("To begin, select a piece...");

        // Add the notifications display panel to this component.
        gb.gridy = 3;
        gb.gridx = 0;
        gb.weightx = 1;
        gb.weighty = 0.75;
        gb.gridheight = 3;
        gb.gridwidth = 1;
        this.add(notificationDisplayPanel, gb);
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
    // public void update(){

    // }

    /**
     * Add a notification to the notifications screen.
     * 
     * @param add The String notification to be added.
     */
    public void addToNotifications(String add) {

        addNotification.setText(add);

        // This removes the last notification if there was one.
        notificationDisplayPanel.remove(addNotification);

        // Styles and adds the new notification to the notification display panel.
        addNotification.setFont(new Font("Monospaced", Font.PLAIN, 20));
        addNotification.setForeground(new Color(0, 204, 0));
        notificationDisplayPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbForInsideNotificationsPanel = new GridBagConstraints();
        gbForInsideNotificationsPanel.anchor = GridBagConstraints.NORTHWEST;
        gbForInsideNotificationsPanel.weightx = 1;
        gbForInsideNotificationsPanel.weighty = 1;
        notificationDisplayPanel.add(addNotification, gbForInsideNotificationsPanel);
        notificationDisplayPanel.repaint();
    }
}