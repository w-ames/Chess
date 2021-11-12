package edu.kingsu.SoftwareEngineering.Chess.GUI;

import java.awt.Color;
import javax.swing.*;

import org.w3c.dom.Text;

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
import edu.kingsu.SoftwareEngineering.Chess.Model.*;

/**
 * Represents the view of notifications that communicates information about
 * gameplay to the user.
 * 
 * @author Chelsie Bajic
 * @since 10/2021
 */
public class ChessGameMessagesView extends ChessGameView {

    private JPanel notificationDisplayPanel = new JPanel();
    JLabel addNotification = new JLabel();
    JLabel addTurnUpdateNotification = new JLabel();
    boolean turn = true;

    /**
     * Constructs the notifications JPanel.
     */
    public ChessGameMessagesView() {
        this.setBackground(Color.WHITE);
        this.setOpaque(true);
        this.setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, new Color(191, 191, 191)));

        // Add title and panel where notifications display panel.
        this.setLayout(new GridBagLayout());
        GridBagConstraints gb = new GridBagConstraints();
        gb.anchor = GridBagConstraints.CENTER;
        gb.gridy = 0;
        gb.gridx = 0;
        gb.weightx = 0;
        gb.weighty = 0;
        gb.gridheight = 1;
        gb.gridwidth = 1;
        JLabel notificationsLabel = new JLabel("Notifications");
        notificationsLabel.setHorizontalAlignment(SwingConstants.CENTER);
        notificationsLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        notificationsLabel.setForeground(new Color(16, 46, 60));
        notificationDisplayPanel.setOpaque(true);
        notificationDisplayPanel.setMinimumSize(new Dimension(300, 100));
        notificationDisplayPanel.setPreferredSize(new Dimension(300, 100));
        notificationDisplayPanel.setMaximumSize(new Dimension(300, 100));
        this.add(notificationsLabel, gb);

        // Add the first notification to the notification display panel.
        notificationDisplayPanel.setBackground(new Color(40, 40, 40));
        addToNotifications("To begin, select a piece...", "firstMessage");

        // Add the notifications display panel to this component.
        gb.fill = GridBagConstraints.BOTH;
        gb.gridy = 3;
        gb.gridx = 0;
        gb.weightx = 1;
        gb.weighty = 0.75;
        gb.gridheight = 3;
        gb.gridwidth = 1;
        gb.insets = new Insets(5, 5, 5, 5);
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

    /**
     * Add a notification to the notifications screen.
     * 
     * @param add The String notification to be added.
     */
    public void addToNotifications(String add, String origin) {

        addNotification.setText(add);
        String turnString = "";

        // Check who's turn it is
        if (turn) {
            turnString = "white's ";
        } else {
            turnString = "black's ";
        }

        addTurnUpdateNotification.setText("It is " + turnString + "turn!");

        // This removes the last notification if there was one.
        notificationDisplayPanel.remove(addNotification);

        // Styles the notifications
        addNotification.setFont(new Font("Monospaced", Font.PLAIN, 15));
        addTurnUpdateNotification.setFont(new Font("Monospaced", Font.PLAIN, 15));
        addNotification.setForeground(new Color(0, 204, 0));
        addTurnUpdateNotification.setForeground(new Color(0, 204, 0));

        addNotification.setMinimumSize(new Dimension(600, 50));
        addNotification.setMaximumSize(new Dimension(600, 50));
        addNotification.setPreferredSize(new Dimension(600, 50));

        addTurnUpdateNotification.setMinimumSize(new Dimension(600, 50));
        addTurnUpdateNotification.setMaximumSize(new Dimension(600, 50));
        addTurnUpdateNotification.setPreferredSize(new Dimension(600, 50));
        JLabel spacer = new JLabel(" ");

        notificationDisplayPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbForInsideNotificationsPanel = new GridBagConstraints();

        gbForInsideNotificationsPanel.gridx = 0;
        gbForInsideNotificationsPanel.gridy = 0;
        gbForInsideNotificationsPanel.weightx = 1;
        gbForInsideNotificationsPanel.weighty = 1;
        gbForInsideNotificationsPanel.anchor = GridBagConstraints.NORTHWEST;
        gbForInsideNotificationsPanel.fill = GridBagConstraints.BOTH;

        notificationDisplayPanel.add(addNotification, gbForInsideNotificationsPanel);

        gbForInsideNotificationsPanel.gridx = 0;
        gbForInsideNotificationsPanel.weightx = 1;
        // gbForInsideNotificationsPanel.weighty = 1;
        gbForInsideNotificationsPanel.anchor = GridBagConstraints.NORTHWEST;
        gbForInsideNotificationsPanel.fill = GridBagConstraints.BOTH;
        // if (origin.equals("fromUpdate")) {
        notificationDisplayPanel.remove(addTurnUpdateNotification);
        // notificationDisplayPanel.remove(spacer);

        // gbForInsideNotificationsPanel.gridy = 1;
        // notificationDisplayPanel.add(spacer, gbForInsideNotificationsPanel);

        gbForInsideNotificationsPanel.gridy = 1;
        notificationDisplayPanel.add(addTurnUpdateNotification, gbForInsideNotificationsPanel);

        // }

        notificationDisplayPanel.repaint();
    }

    // Mandatory override to inherit from ChessGameView.
    public void addListeners() {

    }

    public void update() {
        ChessGame chessGame = getChessGame();

        // True == white, false == black
        turn = chessGame.getPlayerTurn().isWhite();
        this.addToNotifications("", "fromUpdate");
    }

}