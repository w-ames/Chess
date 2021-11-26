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
 * @author Gregory Cal
 * @since 11/2021
 */
public class PopupLayer extends JPanel {

    private ButtonContainer display = new ButtonContainer();
    private CustomButton endGameViewBoardButton = new CustomButton("View Board");
    private CustomButton rematchButton = new CustomButton("Rematch");
    private CustomButton newGameButton = new CustomButton("New Game");
    private CustomButton mainMenuButton = new CustomButton("Main Menu");

    // image
    // ImageIcon whiteQueenIcon = new
    // ImageIcon("./src/assets/piece_images/HQ/white_queen.png");
    private ImageIcon whiteQueenIcon = ChessGameGUIView.openPieceImageFile("piece_images/HQ/HQWhiteQueen.png", 65);
    private ImageIcon blackQueenIcon = ChessGameGUIView.openPieceImageFile("piece_images/HQ/HQBlackQueen.png", 65);
    private ImageIcon whiteKnightIcon = ChessGameGUIView.openPieceImageFile("piece_images/HQ/HQWhiteKnight.png", 65);
    private ImageIcon blackKnightIcon = ChessGameGUIView.openPieceImageFile("piece_images/HQ/HQBlackKnight.png", 65);
    private ImageIcon whiteRookIcon = ChessGameGUIView.openPieceImageFile("piece_images/HQ/HQWhiteRook.png", 65);
    private ImageIcon blackRookIcon = ChessGameGUIView.openPieceImageFile("piece_images/HQ/HQBlackRook.png", 65);
    private ImageIcon whiteBishopIcon = ChessGameGUIView.openPieceImageFile("piece_images/HQ/HQWhiteBishop.png", 65);
    private ImageIcon blackBishopIcon = ChessGameGUIView.openPieceImageFile("piece_images/HQ/HQBlackBishop.png", 65);

    private CustomButton chooseQueen = new CustomButton(whiteQueenIcon, blackQueenIcon);
    private CustomButton chooseKnight = new CustomButton(whiteKnightIcon, blackKnightIcon);
    private CustomButton chooseRook = new CustomButton(whiteRookIcon, blackRookIcon);
    private CustomButton chooseBishop = new CustomButton(whiteBishopIcon, blackBishopIcon);

    private ChessGamePromotionController chooseQueenListener = new ChessGamePromotionController(PieceType.QUEEN);
    private ChessGamePromotionController chooseKnightListener = new ChessGamePromotionController(PieceType.KNIGHT);
    private ChessGamePromotionController chooseRookListener = new ChessGamePromotionController(PieceType.ROOK);
    private ChessGamePromotionController chooseBishopListener = new ChessGamePromotionController(PieceType.BISHOP);

    private ChessPanel chessPanel;
    private ChessGame chessGame;

    JLabel endGameLabel = new JLabel("Game Over!");
    JLabel pawnPromotionLabel = new JLabel("Choose Pawn Promotion");

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
        pawnPromotionLabel.setFont(new Font("Arial", Font.PLAIN, 35));
        pawnPromotionLabel.setForeground(new Color(16, 46, 60));
        pawnPromotionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        display.setLayout(new GridBagLayout());
        GridBagConstraints gb = new GridBagConstraints();
        gb.anchor = GridBagConstraints.NORTH;
        gb.fill = GridBagConstraints.BOTH;
        gb.gridx = 0;
        gb.gridy = 0;
        gb.weightx = 1;
        gb.weighty = 0.25;
        gb.gridheight = 1;
        display.add(pawnPromotionLabel, gb);

        JPanel invisibleButtonContainer = new JPanel();
        invisibleButtonContainer.setOpaque(false);
        invisibleButtonContainer.setLayout(new GridBagLayout());
        GridBagConstraints buttongb = new GridBagConstraints();

        // Add choose queen button.
        buttongb.fill = GridBagConstraints.BOTH;
        buttongb.gridx = 0;
        buttongb.gridy = 0;
        buttongb.weightx = 0.5;
        buttongb.weighty = 1;
        buttongb.gridwidth = 1;
        buttongb.insets = new Insets(50, 50, 50, 50);
        invisibleButtonContainer.add(chooseQueen, buttongb);

        // Add choose rook button.
        buttongb.gridx = 1;
        buttongb.gridy = 0;
        buttongb.weightx = 0.5;
        buttongb.gridwidth = 1;
        invisibleButtonContainer.add(chooseRook, buttongb);

        // Add choose bishop button.
        buttongb.gridx = 0;
        buttongb.gridy = 1;
        buttongb.weightx = 0.5;
        buttongb.gridwidth = 1;
        invisibleButtonContainer.add(chooseBishop, buttongb);

        // Add choose knight button.
        buttongb.gridx = 1;
        buttongb.gridy = 1;
        buttongb.weightx = 0.5;
        buttongb.gridwidth = 1;
        invisibleButtonContainer.add(chooseKnight, buttongb);

        gb.gridy = 1;
        gb.gridheight = 2;
        gb.weighty = 0.75;
        display.add(invisibleButtonContainer, gb);

        assignChessGameAndChessPanelToControllers();

    }

    /**
     * Makes this PopupLayer object into an End Game Options screen.
     * 
     * @param endGameMessage
     */
    public void makeIntoEndGameOptionsScreen(String endGameMessage) {
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
        gb.fill = GridBagConstraints.BOTH;
        gb.gridx = 0;
        gb.gridy = 1;
        gb.weightx = 0.5;
        gb.gridwidth = 1;
        gb.insets = new Insets(5, 5, 5, 5);
        display.add(endGameViewBoardButton, gb);
        for (ActionListener al : endGameViewBoardButton.getActionListeners()) {
            endGameViewBoardButton.removeActionListener(al);
        }
        endGameViewBoardButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                chessPanel.hideEndGameOptions();
            }
        });

        gb.gridx = 1;
        gb.gridy = 1;
        gb.weightx = 0.5;
        gb.gridwidth = 1;
        display.add(rematchButton, gb);
        for (ActionListener al : rematchButton.getActionListeners()) {
            rematchButton.removeActionListener(al);
        }
        rematchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                chessGame.rematch();
                chessPanel.hideEndGameOptionsForRematch();
                chessPanel.clearNotifications();
            }
        });

        gb.gridx = 0;
        gb.gridy = 2;
        gb.weightx = 0.5;
        gb.gridwidth = 1;
        display.add(newGameButton, gb);
        for (ActionListener al : newGameButton.getActionListeners()) {
            newGameButton.removeActionListener(al);
        }
        newGameButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                chessGame.stop();
                chessPanel.getApplicationFrame().show("gamesetup");
                chessPanel.hideEndGameOptionsForRematch();
                chessPanel.hideMenuBar();
                chessPanel.clearNotifications();
            }
        });

        gb.gridx = 1;
        gb.gridy = 2;
        gb.weightx = 0.5;
        gb.gridwidth = 1;
        display.add(mainMenuButton, gb);
        mainMenuButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                chessGame.stop();
                chessPanel.getApplicationFrame().show("menu");
                chessPanel.hideMenuBar();
                chessPanel.hideEndGameOptionsForRematch();
                chessPanel.clearNotifications();
            }
        });

        assignChessGameAndChessPanelToControllers();
    }
    /**
     * Sets the chessGame
     * @param chessGame sets this as the  chessGame
     */
    public void setChessGame(ChessGame chessGame) {
        this.chessGame = chessGame;
    }
    /**
     * Sets the chessPanel
     * @param chessPanel sets this as the chessPanel
     */
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

        for (ActionListener al : chooseQueen.getActionListeners()) {
            chooseQueen.removeActionListener(al);
        }
        chooseQueen.addActionListener(chooseQueenListener);
        for (ActionListener al : chooseRook.getActionListeners()) {
            chooseRook.removeActionListener(al);
        }
        chooseRook.addActionListener(chooseRookListener);
        for (ActionListener al : chooseBishop.getActionListeners()) {
            chooseBishop.removeActionListener(al);
        }
        chooseBishop.addActionListener(chooseBishopListener);
        for (ActionListener al : chooseKnight.getActionListeners()) {
            chooseKnight.removeActionListener(al);
        }
        chooseKnight.addActionListener(chooseKnightListener);
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

}
