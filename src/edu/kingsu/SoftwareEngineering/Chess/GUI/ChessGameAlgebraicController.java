package edu.kingsu.SoftwareEngineering.Chess.GUI;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import edu.kingsu.SoftwareEngineering.Chess.Model.*;

/**
 * ChessGameAlgebraicController holds the algebraic controller of the chess game
 * model.
 * 
 * @author Gregory Cal
 * @since 11/2021
 */
public class ChessGameAlgebraicController implements ActionListener {

    private ChessGame chessGame;
    private ChessGameAlgebraicView view;

    public ChessGameAlgebraicController(ChessGameAlgebraicView view, ChessGame chessGame) {
        this.view = view;
        this.chessGame = chessGame;
    }

    /**
     * Submit the algebraic move that is inside the textField
     */
    public void actionPerformed(ActionEvent e) {
        JTextField textField = (JTextField)e.getSource();
        chessGame.performMove(textField.getText().trim(), true);
        textField.setText("");
    }

}
