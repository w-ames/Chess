package edu.kingsu.SoftwareEngineering.Chess.Model;

import edu.kingsu.SoftwareEngineering.Chess.Model.Moves.*;

public class PlayerAI extends Player {

    public PlayerAI(ChessGame chessGame, boolean isWhite, int interval, int increment) {
        super(chessGame, isWhite, false, interval, increment);
    }

    public void run() {
        resetTimer();
        while (true) {
            synchronized (getChessGame()) {
                try {
                    while (getChessGame().getPlayerTurn() != this) {
                        getChessGame().wait();
                    }
                    setAIThread(ChessAI.randomMove(getChessGame().getBoard(), isWhite()));
                    // setAIThread(ChessAI.bestMove(getChessGame().getBoard(), 4, isWhite()));
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
