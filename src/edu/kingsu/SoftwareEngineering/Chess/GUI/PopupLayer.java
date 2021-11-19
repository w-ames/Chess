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
import edu.kingsu.SoftwareEngineering.Chess.GUI.*;
import edu.kingsu.SoftwareEngineering.Chess.Model.*;
import edu.kingsu.SoftwareEngineering.Chess.Model.Pieces.*;

/**
 * Popuplayer is used to create both the end game options popup screen, and the
 * pawn promotion popup screen.
 * 
 * @author Chelsie Bajic
 * @since 10/2021
 */
public class PopupLayer extends JPanel {

    private ButtonContainer display = new ButtonContainer();
    private CustomButton endGameViewBoardButton = new CustomButton("View Board");
    private CustomButton rematchButton = new CustomButton("Rematch");
    private CustomButton newGameButton = new CustomButton("New Game");
    private CustomButton mainMenuButton = new CustomButton("Main Menu");

    private CustomButton chooseQueen = new CustomButton("Queen");
    private CustomButton chooseKnight = new CustomButton("Knight");
    private CustomButton chooseRook = new CustomButton("Rook");
    private CustomButton chooseBishop = new CustomButton("Bishop");

    private ChessGamePromotionController chooseQueenListener = new ChessGamePromotionController(PieceType.QUEEN);
    private ChessGamePromotionController chooseKnightListener = new ChessGamePromotionController(PieceType.KNIGHT);
    private ChessGamePromotionController chooseRookListener = new ChessGamePromotionController(PieceType.ROOK);
    private ChessGamePromotionController chooseBishopListener = new ChessGamePromotionController(PieceType.BISHOP);

    private ChessPanel chessPanel;
    private ChessGame chessGame;

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

        chooseQueenListener.setChessGame(chessGame);
        chooseKnightListener.setChessGame(chessGame);
        chooseBishopListener.setChessGame(chessGame);
        chooseRookListener.setChessGame(chessGame);

        chooseQueenListener.setChessPanel(chessPanel);
        chooseKnightListener.setChessPanel(chessPanel);
        chooseRookListener.setChessPanel(chessPanel);
        chooseBishopListener.setChessPanel(chessPanel);

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
        // display.add(pawnPromotionLabel, gb);

        // Add choose queen button.
        gb.fill = GridBagConstraints.NONE;
        gb.gridx = 1;
        gb.gridy = 0;
        gb.weightx = 0.5;
        gb.gridwidth = 1;
        gb.insets = new Insets(5, 5, 15, 5);
        display.add(chooseQueen, gb);

        // Add choose rook button.
        gb.fill = GridBagConstraints.NONE;
        gb.gridx = 1;
        gb.gridy = 1;
        gb.weightx = 0.5;
        gb.gridwidth = 1;
        gb.insets = new Insets(5, 5, 15, 5);
        display.add(chooseRook, gb);

        // Add choose bishop button.
        gb.fill = GridBagConstraints.NONE;
        gb.gridx = 2;
        gb.gridy = 0;
        gb.weightx = 0.5;
        gb.gridwidth = 1;
        gb.insets = new Insets(5, 5, 15, 5);
        display.add(chooseBishop, gb);

        // Add choose knight button.
        gb.fill = GridBagConstraints.NONE;
        gb.anchor = GridBagConstraints.CENTER;
        gb.gridx = 2;
        gb.gridy = 1;
        gb.weightx = 0.5;
        gb.gridwidth = 1;
        gb.insets = new Insets(5, 5, 5, 5);
        display.add(chooseKnight, gb);

        assignChessGameAndChessPanelToControllers();

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

        assignChessGameAndChessPanelToControllers();
    }

    public void setChessGame(ChessGame chessGame) {
        this.chessGame = chessGame;
    }

    public void setChessPanel(ChessPanel chessPanel) {
        this.chessPanel = chessPanel;
    }

    private void assignChessGameAndChessPanelToControllers() {
        chooseQueenListener.setChessGame(chessGame);
        chooseKnightListener.setChessGame(chessGame);
        chooseBishopListener.setChessGame(chessGame);
        chooseRookListener.setChessGame(chessGame);

        chooseQueenListener.setChessPanel(chessPanel);
        chooseKnightListener.setChessPanel(chessPanel);
        chooseRookListener.setChessPanel(chessPanel);
        chooseBishopListener.setChessPanel(chessPanel);
    }

    /**
     * Displays the pawn promotion popup screen.
     */
    public void showPawnPromotionScreen(int fromRow, int fromCol, int toRow, int toCol) {
        chooseQueenListener.setMovement(fromRow, fromCol, toRow, toCol);
        chooseBishopListener.setMovement(fromRow, fromCol, toRow, toCol);
        chooseKnightListener.setMovement(fromRow, fromCol, toRow, toCol);
        chooseRookListener.setMovement(fromRow, fromCol, toRow, toCol);
    }

    /**
     * Adds mouse click funtionality to pawn promotion screen button and end game
     * options screen buttons.
     */
    // @Override
    // public void mouseReleased(MouseEvent e) {

    // JButton copyButton = (JButton) e.getSource();
    // String name = copyButton.getText();

    // // This if/else block specifies how each button behaves.
    // if (name.equals("Queen")) {

    // System.err.println("Queen");

    // // User input upon submit can be found in the variable:
    // userPawnPromotionChoice.
    // // ******
    // // Put code to use pawn promotion choice here
    // // *****

    // this.setVisible(false);
    // } else if (name.equals("Rook")) {

    // } else if (name.equals("Bishop")) {

    // } else if (name.equals("Knight")) {

    // } else if (name.equals("View Board")) {

    // // Hide end game options.
    // this.setVisible(false);

    // } else if (name.equals("Rematch")) {

    // // Call rematch here

    // } else if (name.equals("New Game")) {

    // // Call new game here

    // } else if (name.equals("Main Menu")) {

    // // Call back to main menu here
    // }
    // }

}