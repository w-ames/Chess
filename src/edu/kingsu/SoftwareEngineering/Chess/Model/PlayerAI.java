package edu.kingsu.SoftwareEngineering.Chess.Model;

import edu.kingsu.SoftwareEngineering.Chess.Model.Moves.*;

/**
 * This class represents an AI player in a game of chess.
 */
public class PlayerAI extends Player {

    /**
     * Creates a new AI player.
     * @param chessGame the chess game which this player is a part of
     * @param isWhite <code>true</code> if this player is the white player,
     *  <code>false</code> if this player is black
     * @param interval the amount of time allotted on this players clock
     * @param increment the amount of time the players clock gains upon making
     *  moves
     * @param aiDepth the depth at which this player searches for moves
     */
    public PlayerAI(ChessGame chessGame, boolean isWhite, int interval, int increment, int aiDepth) {
        super(chessGame, isWhite, false, interval, increment, aiDepth);
    }

    /**
     * {@inheritDoc}
     */
    public void run() {
        resetTimer();
        while (true) {
            synchronized (getChessGame()) {
                try {
                    while (getChessGame().getPlayerTurn() != this) {
                        getChessGame().wait();
                    }
                    if (getAIDepth() <= 0) {
                        setAIThread(ChessAI.randomMove(getChessGame().getBoard(), isWhite()));
                    } else {
                        setAIThread(ChessAI.bestMove(getChessGame().getBoard(), getAIDepth(), isWhite()));
                    }
                    // getAIThread().start();
                    Move aiMove = getAIThread().getResult();
                    getChessGame().performMove(aiMove, isHuman()); // notifies all, and by the time it does so the turn is not this player's
                } catch(InterruptedException e){
                    resetAIThread();
                    break;
                }
            }
        }
    }
}
