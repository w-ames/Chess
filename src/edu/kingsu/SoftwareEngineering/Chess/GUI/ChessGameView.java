package edu.kingsu.SoftwareEngineering.Chess.GUI;

import javax.swing.JPanel;
import edu.kingsu.SoftwareEngineering.Chess.Model.*;

/**
 * Abstract class for views.
 * 
 * @author Nikolas Haugrud
 * @author Chelsie Bajic
 * @since 10/2021
 */
public abstract class ChessGameView extends JPanel {

    ChessGame chessGame;

    /**
     * Returns the chessGame model.
     * 
     * @return the chessGame model.
     */
    public ChessGame getChessGame() {
        return chessGame;
    }

    /**
     * Sets this chessGame to the chessGame used as an argument to call this
     * function.
     * 
     * @param chessGame The chessGame object to set this chessGame to.
     */
    public void setChessGame(ChessGame chessGame) {
        this.chessGame = chessGame;
        addListeners();
    }

    /**
     * Update the view using data from the model.
     */
    public abstract void update();

    /**
     * To be used to add action listeners to the views.
     */
    public abstract void addListeners();

}