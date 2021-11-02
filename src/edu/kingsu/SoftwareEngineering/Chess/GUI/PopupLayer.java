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
import java.awt.event.*;
import java.awt.event.MouseEvent;

public class PopupLayer extends JPanel {

    private ButtonContainer display = new ButtonContainer();
    private CustomButton viewBoardButton = new CustomButton("View Board");
    private CustomButton rematchButton = new CustomButton("Rematch");
    private CustomButton newGameButton = new CustomButton("New Game");
    private CustomButton mainMenuButton = new CustomButton("Main Menu");

    public PopupLayer() {
        this.setBackground(new Color(16, 46, 60));
        this.setLayout(new GridBagLayout());
        GridBagConstraints gb = new GridBagConstraints();
        gb.fill = GridBagConstraints.BOTH;
        gb.gridx = 0;
        gb.gridy = 0;
        gb.weightx = 1;
        gb.weighty = 1;
        gb.insets = new Insets(20, 20, 20, 20);
        this.add(display, gb);
        addMouseListener(this.viewBoardButton);
    }

    public void makeIntoPawnPromotionScreen() {
        display.add(new JLabel("Pawn Promotion Screen"));
    }

    public void makeIntoEndGameOptionsScreen(String endGameMessage) {
        JLabel endGameLabel = new JLabel(endGameMessage);
        endGameLabel.setFont(new Font("Arial", Font.PLAIN, 50));
        endGameLabel.setForeground(new Color(16, 46, 60));
        display.setLayout(new GridBagLayout());
        GridBagConstraints gb = new GridBagConstraints();
        gb.anchor = GridBagConstraints.NORTH;
        gb.gridx = 0;
        gb.gridy = 0;
        gb.gridwidth = 2;
        gb.insets = new Insets(15, 0, 0, 0);
        display.add(endGameLabel, gb);

        // Add end game option buttons to display.
        gb.fill = GridBagConstraints.HORIZONTAL;
        gb.gridx = 0;
        gb.gridy = 1;
        gb.weightx = 0.5;
        gb.gridwidth = 1;
        gb.insets = new Insets(5, 5, 5, 5);
        display.add(viewBoardButton, gb);
        gb.gridx = 1;
        gb.gridy = 1;
        gb.weightx = 0.5;
        gb.gridwidth = 1;
        display.add(rematchButton, gb);
        gb.gridx = 0;
        gb.gridy = 2;
        gb.weightx = 0.5;
        gb.gridwidth = 1;
        display.add(newGameButton, gb);
        gb.gridx = 1;
        gb.gridy = 2;
        gb.weightx = 0.5;
        gb.gridwidth = 1;
        display.add(mainMenuButton, gb);
    }

}