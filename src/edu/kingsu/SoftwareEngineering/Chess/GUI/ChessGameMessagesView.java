package edu.kingsu.SoftwareEngineering.Chess.GUI;

import java.awt.Color;
import javax.swing.*;
import javax.swing.text.DefaultCaret;

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
import javax.swing.text.*;
import javax.swing.JViewport;
import java.awt.Rectangle;
import edu.kingsu.SoftwareEngineering.Chess.Model.*;

/**
 * Represents the view of notifications that communicates information about
 * gameplay to the user.
 * 
 * @author Chelsie Bajic
 * @since 11/2021
 */
public class ChessGameMessagesView extends ChessGameView {

    private JTextArea notificaionDisplayArea = new JTextArea();
    private boolean turn = true;
    private boolean notificationsOnOff;
    private boolean turnNotificationsSwitch = true; 

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
        notificaionDisplayArea.setOpaque(true);
        this.add(notificationsLabel, gb);

        // Make background of notification display black, and font green to look like
        // terminal.
        notificaionDisplayArea.setBackground(new Color(232, 232, 232));
        notificaionDisplayArea.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, new Color(224, 224, 224)));
        notificaionDisplayArea.setFont(new Font("Arial", Font.PLAIN, 20));
        notificaionDisplayArea.setForeground(new Color(16, 46, 60));// Font color
        notificaionDisplayArea.setEditable(false);

        // Add the notifications display panel to this component.
        gb.fill = GridBagConstraints.BOTH;
        gb.gridy = 3;
        gb.gridx = 0;
        gb.weightx = 1;
        gb.weighty = 0.75;
        gb.gridheight = 3;
        gb.gridwidth = 1;
        gb.insets = new Insets(5, 5, 5, 5);

        JScrollPane scrollNotifications = new JScrollPane(notificaionDisplayArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollNotifications.setMaximumSize(new Dimension(10, 100));
        scrollNotifications.setPreferredSize(new Dimension(10, 100));
        scrollNotifications.setMinimumSize(new Dimension(10, 100));
        DefaultCaret caret = (DefaultCaret) notificaionDisplayArea.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        this.add(scrollNotifications, gb);

        notificationsOnOff = true;
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
     * Adds a notification to the notifications screen
     * 
     * @param add String containing the notificaiton to add.
     * 
     */
    public void addToNotifications(String add) {
        if (notificationsOnOff == true) {
            notificaionDisplayArea.append(add + "\n");
        }

    }

    /**
     * Prints a notification stating who's turn it is.
     * 
     * @param turn true = white, false = black
     */
    public void addTurnNotification(boolean turn) {


        if (notificationsOnOff == true) {

            String turnString = "";

            // Check who's turn it is
            if (turn) {
                turnString = "white's ";
            } else {
                turnString = "black's ";
            }

            String turnNotification = "It is " + turnString + "turn!" + "\n";

            notificaionDisplayArea.append(turnNotification);
        }

    }

    /** 
     * Clears all notifications.
     */
    public void clearNotifications() {
        notificaionDisplayArea.setText(null);
    }

    // Mandatory override to inherit from ChessGameView.
    public void addListeners() {

    }

    /**
     * Updates information about the current state of the game.
     */
    public void update() {
      
        if(turnNotificationsSwitch == true){
            ChessGame chessGame = getChessGame();

            // True == white, false == black
            turn = chessGame.getPlayerTurn().isWhite();
    
            addTurnNotification(turn);
        }
    }

    public void turnNotificationSwitch(boolean turnNotificationsSwitch){
      
        this.turnNotificationsSwitch = turnNotificationsSwitch; 
    }

    /**
     * Turns off notifications
     * 
     * @param notificationsSwitch True = on, false = off.
     */
    public void turnNotificationsOnOff(boolean notificationsSwitch) {
        notificationsOnOff = notificationsSwitch;
    }

}