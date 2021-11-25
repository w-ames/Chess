package edu.kingsu.SoftwareEngineering.Chess.GUI;

import java.awt.*;
import java.awt.event.*;
import edu.kingsu.SoftwareEngineering.Chess.Model.*;
import edu.kingsu.SoftwareEngineering.Chess.Model.Pieces.*;

/**
 * ChessGameGUIController holds the graphical controller for the chessGame
 * model.
 * 
 * @author Nikolas Haugrud
 * @author Chelsie Bajic
 * @since 10/2021
 */
public class ChessGameGUIController implements ActionListener {

    ChessGameGUIView guiView;
    ChessGame chessGame;
    int row;
    int col;

    /**
     * Build the ChessGameGUIController object.
     * 
     * @param guiView   The graphical view.
     * @param chessGame The chess game model.
     * @param row       The row of the square for this controller.
     * @param col       The column of the square for this controller
     */
    public ChessGameGUIController(ChessGameGUIView guiView, ChessGame chessGame, int row, int col) {
        this.guiView = guiView;
        this.chessGame = chessGame;
        this.row = row;
        this.col = col;
    }

    /**
     * Allows moves to be performed on the graphical board.
     */
    public void actionPerformed(ActionEvent e) {

        boolean isWhiteTurn = chessGame.getPlayerTurn().isWhite();
        boolean isHumanTurn = chessGame.getPlayerTurn().isHuman();
        int selectedRow = guiView.getSelectedRow();
        int selectedCol = guiView.getSelectedCol();
        Piece thisPiece = chessGame.getBoard().getPiece(row, col);
        boolean thisPieceIsPlayerColor = (thisPiece != null) ? thisPiece.isWhite() == isWhiteTurn : false;
        Piece selectedPiece = chessGame.getBoard().getPiece(selectedRow, selectedCol);
        boolean selectedPieceIsPlayerColor = (selectedPiece != null) ? selectedPiece.isWhite() == isWhiteTurn : false;

        if (isHumanTurn) {
            if (thisPieceIsPlayerColor) {
                if (row == selectedRow && col == selectedCol) {
                    guiView.setSelected(-1, -1);
                } else {
                    guiView.setSelected(row, col);
                }
            } else if (selectedPieceIsPlayerColor) {
                if (chessGame.checkPawnPromotion(selectedRow, selectedCol, row, col)) {
                    guiView.showPawnPromotionScreen(selectedRow, selectedCol, row, col);
                } else {
                    chessGame.performMove(selectedRow, selectedCol, row, col, true);
                }
                guiView.setSelected(-1, -1);
            }
        }
        guiView.update();
    }

}
