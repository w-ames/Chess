package edu.kingsu.SoftwareEngineering.Chess.GUI;

import java.awt.*;
import java.awt.event.*;
import edu.kingsu.SoftwareEngineering.Chess.Model.*;

/**
 * ChessGameAlgebraicController holds the algebraic controller of the chess game
 * model.
 * 
 * @author
 * @since 10/2021
 */
public class ChessGameAlgebraicController implements ActionListener {

    private ChessGame chessGame;
    private ChessGameAlgebraicView view;

    public ChessGameAlgebraicController(ChessGameAlgebraicView view, ChessGame chessGame) {
        this.view = view;
        this.chessGame = chessGame;
    }

    // Submit the algebraic move
    public void actionPerformed(ActionEvent e) {

    }

}