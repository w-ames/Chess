package edu.kingsu.SoftwareEngineering.Chess.Model;

import edu.kingsu.SoftwareEngineering.Chess.Model.Moves.*;

public class PlayerAI extends Player {

    public PlayerAI(ChessGame chessGame, boolean isWhite, int interval, int increment) {
        super(chessGame, isWhite, false, interval, increment);
    }

    public void run() {
        resetTimer();
        safeWait();
        while (true) {
            setAIThread(ChessAI.randomMove(getChessGame().getBoard(), isWhite()));
            Move aiMove = getAIThreadMove();
            getChessGame().performMove(aiMove);
            safeWait();
        }
    }
}
