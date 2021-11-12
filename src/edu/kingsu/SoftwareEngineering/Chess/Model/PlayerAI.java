package edu.kingsu.SoftwareEngineering.Chess.Model;

import edu.kingsu.SoftwareEngineering.Chess.Model.Moves.*;

public class PlayerAI extends Player {

    public PlayerAI(ChessGame chessGame, boolean isWhite, int interval, int increment, int aiDepth) {
        super(chessGame, isWhite, false, interval, increment, aiDepth);
    }

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
                    getAIThread().start();
                    Move aiMove = getAIThread().getResult();
                    getChessGame().performMove(aiMove, isHuman()); // notifies all, and by the time it does so the turn is not this player's
                } catch(InterruptedException e){
                    if (getAIThread() != null) {
                        getAIThread().interrupt();
                    }
                    break;
                }
            }
        }
    }
}
