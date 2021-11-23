package edu.kingsu.SoftwareEngineering.Chess.Model;

import java.util.Timer;

import edu.kingsu.SoftwareEngineering.Chess.Model.Moves.*;

/**
 * This class represents an player in a game of chess.
 */
public abstract class Player implements Runnable {
    private boolean isWhite;
    private boolean isHuman;
    private ChessAIThread aiThread;
    private ChessGame chessGame;
    private int interval;
    private int increment;
    private Timer timer;
    /**
     * The maximum depth to be used when searching for moves via {@link ChessAI}
     */
    public static final int MAX_AI_DEPTH = 4;
    private int aiDepth;

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
    public Player(ChessGame chessGame, boolean isWhite, boolean isHuman, int interval, int increment, int aiDepth) {
        this.chessGame = chessGame;
        this.isWhite = isWhite;
        this.isHuman = isHuman;
        this.interval = interval;
        this.increment = increment;
        this.aiDepth = aiDepth;
    }

    /**
     * Returns the chess game associated with this player of chess.
     * @return the chess game associated with this player of chess
     */
    public ChessGame getChessGame() { return chessGame; }

    /**
     * Returns the AI thread used by this player to calculate a move.
     * @return the AI thread used by this player to calculate a move
     */
    public ChessAIThread getAIThread() {
        synchronized (this) {
            return aiThread;
        }
    }

    /**
     * Returns the AI thread used by this player to calculate a move,
     * only if it has started executing.
     * @return the AI thread used by this player to calculate a move
     *  if it has started executing, otherwise <code>null</code>
     */
    // public ChessAIThread getStartedAIThread() { return aiThread!=null && aiThread.getState()!=Thread.State.NEW ? aiThread : null; }

    /**
     * Sets the AI thread used by this player to calculate a move.
     * @param t the AI thread used by this player to calculate a move
     */
    public void setAIThread(ChessAIThread t) {
        synchronized (this) {
            if (t != null) {
                t.start();
            }
            aiThread = t;
            // notifyAll();
        }
    }

    public void resetAIThread() {
        // TODO test
        synchronized (this) {
            if (aiThread != null) {
                aiThread.interrupt();
                aiThread = null;
            }
            // notifyAll();
        }
    }

    // public Move getAIThreadMove() throws InterruptedException {
    //     Move aiMove = null;
    //     while (aiMove == null) {
    //         getAIThread().join();
    //         aiMove = getAIThread().getResult();
    //     }
    //     return aiMove;
    // }

    /**
     * Returns a boolean indicating whether this player is the white player
     * or black player.
     * @return <code>true</code> if the player is white, <code>false</code> if
     *  black
     */
    public boolean isWhite() {
        return isWhite;
    }

    /**
     * Returns a boolean indicating whether this player is human or AI.
     * @return <code>true</code> if the player is human, <code>false</code> if
     *  AI
     */
    public boolean isHuman() {
        return isHuman;
    }

    /**
     * Simulates the player acting out their part of the game including
     * running timers, beginning move calculations, waiting for the next turn.
     */
    public abstract void run();

    public void resumeTimer() {
    }

    private void setInterval() {
    }

    public void resetTimer() {
    }

    /**
     * Returns the depth at which this player can search for moves.
     * @return the depth at which this player can search for moves
     */
    public int getAIDepth() { return aiDepth; }

    /**
     * Sets the depth at which this player can search for moves.
     * @param depth the new depth at which this player can search for moves
     */
    public void setAIDepth(int depth) { aiDepth = depth; }

}
