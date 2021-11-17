package edu.kingsu.SoftwareEngineering.Chess.GUI;

import java.awt.Color;
import javax.swing.*;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
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
import java.awt.event.MouseListener;
import java.lang.String;
import java.awt.Dimension;

/**
 * Popuplayer is used to create both the end game options popup screen, and the
 * pawn promotion popup screen.
 * 
 * @author Chelsie Bajic
 * @since 10/2021
 */
public class PopupLayer extends JPanel implements MouseListener {

    private ButtonContainer display = new ButtonContainer();
    private CustomButton endGameViewBoardButton = new CustomButton("View Board");
    private CustomButton rematchButton = new CustomButton("Rematch");
    private CustomButton newGameButton = new CustomButton("New Game");
    private CustomButton mainMenuButton = new CustomButton("Main Menu");
    private CustomButton submitPawnPromotion = new CustomButton("Submit");
    private JComboBox<String> choosePawnPromotion;

    /**
     * Builds the popup screen and adds a mouse listener to all buttons.
     */
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

        // Add mouse listener to the submit button for pawn promotion screen.
        submitPawnPromotion.addMouseListener(this);
        rematchButton.addMouseListener(this);
        newGameButton.addMouseListener(this);
        mainMenuButton.addMouseListener(this);
        endGameViewBoardButton.addMouseListener(this);
    }

    /**
     * Makes this PopupLayer object into a pawn promotion popup screen.
     */
    public void makeIntoPawnPromotionScreen() {
        JLabel pawnPromotionLabel = new JLabel("Choose Pawn Promotion");
        pawnPromotionLabel.setFont(new Font("Arial", Font.PLAIN, 35));
        pawnPromotionLabel.setForeground(new Color(16, 46, 60));
        display.setLayout(new GridBagLayout());
        GridBagConstraints gb = new GridBagConstraints();
        gb.anchor = GridBagConstraints.NORTH;
        gb.gridx = 0;
        gb.gridy = 0;
        gb.gridwidth = 2;
        gb.insets = new Insets(10, 0, 15, 0);
        display.add(pawnPromotionLabel, gb);

        // Customize JComboBox.
        String[] pawnPromotionOptions = new String[] { "Queen", "King", "Rook", "Bishop", "Knight", "Pawn" };
        choosePawnPromotion = new JComboBox<String>(pawnPromotionOptions);

        // Add pawn promotion JComboBox.
        gb.fill = GridBagConstraints.NONE;
        gb.gridx = 0;
        gb.gridy = 1;
        gb.weightx = 0.5;
        gb.gridwidth = 1;
        gb.insets = new Insets(5, 5, 15, 5);
        display.add(choosePawnPromotion, gb);

        // Add pawn promotion submit button.
        gb.fill = GridBagConstraints.NONE;
        gb.anchor = GridBagConstraints.CENTER;
        gb.gridx = 0;
        gb.gridy = 2;
        gb.weightx = 0.5;
        gb.gridwidth = 1;
        gb.insets = new Insets(5, 5, 5, 5);
        display.add(submitPawnPromotion, gb);

    }

    /**
     * Makes this PopupLayer object into an End Game Options screen.
     * 
     * @param endGameMessage
     */
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
        display.add(endGameViewBoardButton, gb);
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

    /**
     * Adds mouse click funtionality to pawn promotion screen button and end game
     * options screen buttons.
     */
    @Override
    public void mouseReleased(MouseEvent e) {

        JButton copyButton = (JButton) e.getSource();
        String name = copyButton.getText();

        // This if/else block specifies how each button behaves.
        if (name.equals("Submit")) {

            String userPawnPromotionChoice = String.valueOf(choosePawnPromotion.getSelectedItem());

            // User input upon submit can be found in the variable: userPawnPromotionChoice.
            // ******
            // Put code to use pawn promotion choice here
            // *****

            if (userPawnPromotionChoice.equals("Queen")) {

            } else if (userPawnPromotionChoice.equals("King")) {

            } else if (userPawnPromotionChoice.equals("Rook")) {

            } else if (userPawnPromotionChoice.equals("Knight")) {

            } else if (userPawnPromotionChoice.equals("Bishop")) {

            } else if (userPawnPromotionChoice.equals("Pawn")) {

            }

            this.setVisible(false);

        } else if (name.equals("View Board")) {

            // Hide end game options.
            this.setVisible(false);

        } else if (name.equals("Rematch")) {

            // Call rematch here

        } else if (name.equals("New Game")) {

            // Call new game here

        } else if (name.equals("Main Menu")) {

            // Call back to main menu here
        }
    }

    // The following are required to implement MouseListener but not used.
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }
}