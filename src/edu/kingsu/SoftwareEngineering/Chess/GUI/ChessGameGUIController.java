package edu.kingsu.SoftwareEngineering.Chess.GUI;

import java.awt.*;
import java.awt.event.*;
import edu.kingsu.SoftwareEngineering.Chess.Model.*;

public class ChessGameGUIController implements ActionListener {

    ChessGameGUIView guiView;
    ChessGame chessGame;
    int row;
    int col;

    // Add a constructor to give reference to the view
    // and a reference ot the row and column that it is

    public ChessGameGUIController(ChessGameGUIView guiView, ChessGame chessGame, int row, int col) {
        this.guiView = guiView;
        this.chessGame = chessGame;
        this.row = row;
        this.col = col;
    }

    public void actionPerformed(ActionEvent e) {
        int selectedRow = guiView.getSelectedRow();
        int selectedCol = guiView.getSelectedCol();

        System.err.println("1");
        if (selectedRow == -1 || selectedCol == -1) {
            System.err.println("2");
            guiView.setSelectedRow(row);
            guiView.setSelectedCol(col);

        } else {
            chessGame.performMove(selectedRow, selectedCol, row, col, true);
            System.err.println("3");
            guiView.setSelectedRow(-1);
            guiView.setSelectedCol(-1);
        }
    }

}