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
     */
    public PlayerHuman(ChessGame chessGame, boolean isWhite, int interval) {
        super(chessGame, isWhite, true, interval, Player.MAX_AI_DEPTH);
    }

    /**
     * Creates a new human player from an existing player's values.
     * @param oldPlayer the player whose values should be used in creating this
     *  new instance.
     */
    public PlayerHuman(Player oldPlayer) {
        super(oldPlayer, -1);
    }

    /**
     * {@inheritDoc}
     */
    public void run() {
        // resumeTimer();
        pauseTimer();
        while (true) {
            synchronized (getChessGame()) {
                try {
                    while (getChessGame().getPlayerTurn() != this) {
                        // System.err.println("NOT HUMAN TURN-WAIT");
                        pauseTimer();
                        getChessGame().notify();
                        getChessGame().wait();
                    }
                    resumeTimer();
                    // System.err.println("ABOUT TO SET THREAD IN HUMAN RUN");
                    if (getAIDepth() <= 0) {
                        setAIThread(ChessAI.randomMove(getChessGame().getBoard(), isWhite()));
                    } else {
                        setAIThread(ChessAI.bestMove(getChessGame().getBoard(), getAIDepth(), isWhite()));
                    }
                    // getAIThread().start();
                    // while (getChessGame().getPlayerTurn() == this) {
                        // System.err.println("HUMAN TURN-WAIT");
                        // getChessGame().notify();
                        getChessGame().wait();
                    // }
                    // System.err.println("HUMAN ENDS TURN");
                } catch(InterruptedException e) {
                    // System.err.println("RESET AI THREAD?");
                    resetAIThread();
                    pauseTimer();
                    break;
                }
            }
        }
    }

}
