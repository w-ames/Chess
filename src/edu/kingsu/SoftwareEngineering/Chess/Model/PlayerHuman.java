package edu.kingsu.SoftwareEngineering.Chess.Model;

public class PlayerHuman extends Player {

    public PlayerHuman(ChessGame chessGame, boolean isWhite, int interval, int increment) {
        super(chessGame, isWhite, true, interval, increment, Player.MAX_AI_DEPTH);
    }

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
                    getAIThread().start();
                    while (getChessGame().getPlayerTurn() == this) {
                        getChessGame().wait();
                    }
                } catch(InterruptedException e) {
                    if (getAIThread() != null) {
                        getAIThread().interrupt();
                    }
                    break;
                }
            }
        }
    }

}
