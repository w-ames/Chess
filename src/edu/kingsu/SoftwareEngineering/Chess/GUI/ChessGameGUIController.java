package edu.kingsu.SoftwareEngineering.Chess.GUI;

import java.awt.*;
import java.awt.event.*;
import edu.kingsu.SoftwareEngineering.Chess.Model.*;

/**
 * ChessGameGUIController holds the graphical controller for the chessGame
 * model.
 * 
 * @author Nikolas Haugrud
 * @author Chelsie Bajic
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

        int selectedRow = guiView.getSelectedRow();
        int selectedCol = guiView.getSelectedCol();

        if (selectedRow == -1 || selectedCol == -1) {
            guiView.setSelectedRow(row);
            guiView.setSelectedCol(col);

        } else {
            chessGame.performMove(selectedRow, selectedCol, row, col, true);
            guiView.setSelectedRow(-1);
            guiView.setSelectedCol(-1);
        }
    }
}