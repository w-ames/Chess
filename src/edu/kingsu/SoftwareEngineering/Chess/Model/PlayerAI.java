package edu.kingsu.SoftwareEngineering.Chess.Model;

import edu.kingsu.SoftwareEngineering.Chess.Model.Moves.*;
import java.time.*;

/**
 * This class represents an AI player in a game of chess.
 */
public class PlayerAI extends Player {

    private static final long MIN_MOVE_TIME = 1000; // in millis

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
    public PlayerAI(ChessGame chessGame, boolean isWhite, int interval, int aiDepth) {
        super(chessGame, isWhite, false, interval, aiDepth);
    }

    /**
     * Creates a new AI player from an existing player's values.
     * @param oldPlayer the player whose values should be used in creating this
     *  new instance.
     * @param newDepth the AI search depth of this new AI player
     */
    public PlayerAI(Player oldPlayer, int newDepth) {
        super(oldPlayer, newDepth);
    }

    /**
     * {@inheritDoc}
     */
    public void run() {
        // resetTimer();
        pauseTimer();
        while (true) {
            synchronized (getChessGame()) {
                try {
                    while (getChessGame().getPlayerTurn() != this) {
                        // System.err.println("NOT AI TURN-WAIT");
                        pauseTimer();
                        getChessGame().notify();
                        getChessGame().wait();
                    }
                    resumeTimer();
                    Instant moveCalcStartInstant = Instant.now();
                    // System.err.println("ABOUT TO SET THREAD IN AI RUN");
                    if (getAIDepth() <= 0) {
                        setAIThread(ChessAI.randomMove(getChessGame().getBoard(), isWhite()));
                    } else {
                        setAIThread(ChessAI.bestMove(getChessGame().getBoard(), getAIDepth(), isWhite()));
                    }
                    // getAIThread().start();
                    Move aiMove = getAIThread().getResult();
                    Instant moveCalcEndInstant = Instant.now();
                    long delta = Duration.between(moveCalcStartInstant, moveCalcEndInstant).toMillis();
                    if (MIN_MOVE_TIME-delta > 0) {
                        Thread.sleep(MIN_MOVE_TIME-delta);
                    }
                    getChessGame().performMove(aiMove, isHuman()); // notifies all, and by the time it does so the turn is not this player's
                    // System.err.println("AI ENDS TURN");
                } catch(InterruptedException e){
                    // System.err.println("RESET AI THREAD?");
                    resetAIThread();
                    pauseTimer();
                    break;
                }
            }
        }
    }

}
