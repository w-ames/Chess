package edu.kingsu.SoftwareEngineering.Chess.Model;

public class PlayerHuman extends Player {

    public PlayerHuman(ChessGame chessGame, boolean isWhite, int interval, int increment) {
        super(chessGame, isWhite, true, interval, increment);
    }

    public void run() {
        resumeTimer();
        while (true) {
            setAIThread(ChessAI.randomMove(getChessGame().getBoard(), isWhite()));
            safeWait();
        }
    }

}
