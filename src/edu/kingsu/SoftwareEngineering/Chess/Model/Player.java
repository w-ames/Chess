package edu.kingsu.SoftwareEngineering.Chess.Model;

import java.util.Timer;
import java.util.TimerTask;

import edu.kingsu.SoftwareEngineering.Chess.GUI.ClockView;
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
    // private int increment;
    private Timer timer;
    private final Object TIMER_LOCK = new Object();
    private ClockView clock;
    private boolean timerRunning;
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
    public Player(ChessGame chessGame, boolean isWhite, boolean isHuman, int interval, int aiDepth) {
        this.chessGame = chessGame;
        this.isWhite = isWhite;
        this.isHuman = isHuman;
        this.interval = interval;
        // this.increment = increment;
        this.aiDepth = aiDepth;
        // this.timer = new Timer();
        timerRunning = false;
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
            // notifyAll();
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
        // System.err.println("WANT TO SET THREAD FOR: "+this);
        synchronized (this) {
            // System.err.println("IN PLAYER LOCK FOR: "+this);
            if (t != null) {
                t.start();
            }
            aiThread = t;
            notifyAll();
            // System.err.println("WE NOTIFIED "+this);
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
        // System.err.println("1Resuming timer for: "+this);
        if (timerRunning || chessGame.getPlayerIncrement() < 0) {
            return;
        }
        timer = new Timer();
        // System.err.println("STARTING: "+isWhite());
        // System.err.println("2Resuming timer for: "+this);
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                // Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
                setInterval();
            }
        }, ChessGame.SECOND, ChessGame.SECOND);
        timerRunning = true;
    }

    private void setInterval() {
        // System.err.println("CLOCK TICK");
        int newTime;
        synchronized(TIMER_LOCK) {
            --interval;
            if (interval <= 0) {
                pauseTimer();
                chessGame.timeOutGame(this);
            }
            newTime = interval;
        }
        clock.updatePlayerTime(""+newTime);
    }

    public void incrementTimer() {
        if (chessGame.getPlayerIncrement() > 0) {
            synchronized(TIMER_LOCK) {
                interval += chessGame.getPlayerIncrement()-1;
                setInterval();
            }
        }
    }

    public void resetTimer() {
        pauseTimer();
        synchronized(TIMER_LOCK) {
            interval = chessGame.getPlayerInterval();
        }
        clock.updatePlayerTime(""+interval);
        // resumeTimer();
    }

    public void pauseTimer() {
        // System.err.println("1Stopping timer for: "+this);
        if (timerRunning) {
            // System.err.println("2Stopping timer for: "+this);
            // System.err.println("PAUSING: "+isWhite());
            timer.cancel();
            timerRunning = false;
        }
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

    public void registerPlayerClock(ClockView clock) {
        this.clock = clock;
    }

}
