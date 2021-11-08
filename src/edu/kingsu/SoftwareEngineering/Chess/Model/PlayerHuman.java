package edu.kingsu.SoftwareEngineering.Chess.Model;

public class PlayerHuman extends Player {

    public PlayerHuman(ChessGame chessGame, boolean isWhite, int interval, int increment) {
        super(chessGame, isWhite, true, interval, increment);
    }

    public void run() {
        resumeTimer();
        while (true) {
            synchronized (getChessGame()) {
                try {
                    while (getChessGame().getPlayerTurn() != this) {
                        getChessGame().wait();
                    }
                    setAIThread(ChessAI.randomMove(getChessGame().getBoard(), isWhite()));
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
