package edu.kingsu.SoftwareEngineering.Chess.Model;

/**
 * This class represents an human player in a game of chess.
 */
public class PlayerHuman extends Player {

    /**
     * Creates a new human player.
     * @param chessGame the chess game which this player is a part of
     * @param isWhite <code>true</code> if this player is the white player,
     *  <code>false</code> if this player is black
     * @param interval the amount of time allotted on this players clock
     * @param increment the amount of time the players clock gains upon making
     *  moves
     */
    public PlayerHuman(ChessGame chessGame, boolean isWhite, int interval, int increment) {
        super(chessGame, isWhite, true, interval, increment, Player.MAX_AI_DEPTH);
    }

    /**
     * {@inheritDoc}
     */
    public void run() {
        resumeTimer();
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
                    while (getChessGame().getPlayerTurn() == this) {
                        getChessGame().wait();
                    }
                } catch(InterruptedException e) {
                    resetAIThread();
                    break;
                }
            }
        }
    }

}
