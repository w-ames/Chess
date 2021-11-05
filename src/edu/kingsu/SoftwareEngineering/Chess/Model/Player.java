package edu.kingsu.SoftwareEngineering.Chess.Model;

import java.util.Timer;

public abstract class Player implements Runnable {
    private boolean isWhite;
    private boolean isHuman;
    private ChessAIThread aiThread;
    private ChessGame chessGame;
    private boolean stopped;
    private int interval;
    private int increment;
    private Timer timer;

    public Player(boolean isWhite, boolean isHuman, int interval, int increment) {
        this.isWhite = isWhite;
        this.isHuman = isHuman;
        this.interval = interval;
        this.increment = increment;
    }

    public ChessAIThread getAIThread() { return aiThread; }

    public boolean isWhite() {
        return isWhite;
    }

    public boolean isHuman() {
        return isHuman;
    }

    public abstract void run();

    public void stop() {
    }

    private void setInterval() {
    }

    public void resetTimer() {
    }
}
