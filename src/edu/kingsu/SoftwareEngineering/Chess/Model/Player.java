package edu.kingsu.SoftwareEngineering.Chess.Model;

import java.util.Timer;

import edu.kingsu.SoftwareEngineering.Chess.Model.Moves.*;

public abstract class Player implements Runnable {
    private final Object lock;
    private boolean isWhite;
    private boolean isHuman;
    private ChessAIThread aiThread;
    private ChessGame chessGame;
    private boolean stopped;
    private int interval;
    private int increment;
    private Timer timer;

    public Player(ChessGame chessGame, boolean isWhite, boolean isHuman, int interval, int increment) {
        this.lock = new Object();
        this.chessGame = chessGame;
        this.isWhite = isWhite;
        this.isHuman = isHuman;
        this.interval = interval;
        this.increment = increment;
    }

    public Object getLock() { return lock; }

    public ChessGame getChessGame() { return chessGame; }

    public ChessAIThread getAIThread() { return aiThread; }

    public void setAIThread(ChessAIThread t) { aiThread = t; }

    public Move getAIThreadMove() {
        Move aiMove = null;
        while (aiMove == null) {
            try {
                getAIThread().join();
                aiMove = getAIThread().getResult();
            } catch(InterruptedException e){
            }
        }
        return aiMove;
    }

    public boolean isWhite() {
        return isWhite;
    }

    public boolean isHuman() {
        return isHuman;
    }

    public abstract void run();

    public void stop() {
        try {
            aiThread.join();
        } catch(InterruptedException e){
            // e.printStackTrace();
        }
    }

    public void resumeTimer() {
    }

    private void setInterval() {
    }

    public void resetTimer() {
    }

    public void safeWait() {
        try {
            wait();
        } catch(InterruptedException e){
        }
    }

}
