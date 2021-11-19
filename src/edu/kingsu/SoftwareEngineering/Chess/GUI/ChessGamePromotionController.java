package edu.kingsu.SoftwareEngineering.Chess.GUI;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import edu.kingsu.SoftwareEngineering.Chess.Model.*;
import edu.kingsu.SoftwareEngineering.Chess.Model.Pieces.*;

/**
 * ChessGamePromotionController holds the Promotion controller of the chess game
 * model.
 * 
 * @author
 * @since 10/2021
 */
public class ChessGamePromotionController implements ActionListener {

    private ChessPanel chessPanel;
    private ChessGame chessGame;
    private ChessGameGUIView view;

    private int fromRow;
    private int fromCol;
    private int toRow;
    private int toCol;
    private PieceType pieceType;

    // public ChessGamePromotionController(ChessGamePromotionView view, ChessGame
    // chessGame, PieceType pieceType) {
    // this.view = view;
    // this.chessGame = chessGame;

    public ChessGamePromotionController(PieceType pieceType) {
        this.pieceType = pieceType;

        fromRow = -1;
        fromCol = -1;
        toRow = -1;
        toCol = -1;
    }

    // Submit the Promotion move
    public void actionPerformed(ActionEvent e) {

        chessGame.performMove(fromRow, fromCol, toRow, toCol, true, pieceType);

        fromRow = -1;
        fromCol = -1;
        toRow = -1;
        toCol = -1;

        chessPanel.hidePawnPromotionScreen();

    }

    public void setMovement(int fromRow, int fromCol, int toRow, int toCol) {

        this.fromRow = fromRow;
        this.fromCol = fromCol;
        this.toRow = toRow;
        this.toCol = toCol;

    }

    public void setChessGame(ChessGame chessGame) {
        this.chessGame = chessGame;
    }

    public void setChessPanel(ChessPanel chessPanel) {
        this.chessPanel = chessPanel;
    }

}
