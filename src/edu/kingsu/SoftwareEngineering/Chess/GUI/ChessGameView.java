package edu.kingsu.SoftwareEngineering.Chess.GUI;

import javax.swing.JPanel;
import edu.kingsu.SoftwareEngineering.Chess.Model.*;

public abstract class ChessGameView extends JPanel {

    ChessGame chessGame;

    // public ChessGameView(ChessGame chessGame) {
    // this.chessGame = chessGame;
    // }

    public ChessGame getChessGame() {
        return chessGame;
    }

    public void setChessGame(ChessGame chessGame) {
        this.chessGame = chessGame;
        addListeners();
    }

    public abstract void update();

    public abstract void addListeners();

}