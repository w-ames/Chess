package edu.kingsu.SoftwareEngineering.Chess.Model;

import java.util.Timer;

import edu.kingsu.SoftwareEngineering.Chess.Model.Moves.*;

public abstract class Player implements Runnable {
    private boolean isWhite;
    private boolean isHuman;
    private ChessAIThread aiThread;
    private ChessGame chessGame;
    private int interval;
    private int increment;
    private Timer timer;

    public Player(ChessGame chessGame, boolean isWhite, boolean isHuman, int interval, int increment) {
        this.chessGame = chessGame;
        this.isWhite = isWhite;
        this.isHuman = isHuman;
        this.interval = interval;
        this.increment = increment;
    }

    public ChessGame getChessGame() { return chessGame; }

    public ChessAIThread getAIThread() { return aiThread; }

    public void setAIThread(ChessAIThread t) { aiThread = t; }

    // public Move getAIThreadMove() throws InterruptedException {
    //     Move aiMove = null;
    //     while (aiMove == null) {
    //         getAIThread().join();
    //         aiMove = getAIThread().getResult();
    //     }
    //     return aiMove;
    // }

    public boolean isWhite() {
        return isWhite;
    }

    public boolean isHuman() {
        return isHuman;
    }

    public abstract void run();

    public void resumeTimer() {
    }

    private void setInterval() {
    }

    public void resetTimer() {
    }

}
