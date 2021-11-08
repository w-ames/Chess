package edu.kingsu.SoftwareEngineering.Chess.GUI;

import java.awt.*;
import java.awt.event.*;
import edu.kingsu.SoftwareEngineering.Chess.Model.*;

public class ChessGameGUIController implements ActionListener {

    ChessGameGUIView guiView;
    ChessGame chessGame;

    // Add a constructor to give reference to the view
    // and a reference ot the row and column that it is

    public ChessGameGUIController(ChessGameGUIView guiView, ChessGame chessGame) {
        this.guiView = guiView;
        this.chessGame = chessGame;

    }

    public void actionPerformed(ActionEvent e) {
    }

}