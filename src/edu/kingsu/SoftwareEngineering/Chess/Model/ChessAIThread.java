package edu.kingsu.SoftwareEngineering.Chess.Model;

import edu.kingsu.SoftwareEngineering.Chess.Model.Moves.*;

public abstract class ChessAIThread extends Thread {
    private Move result;

    public Move getResult() { return result; } // thread should be joined before trying this

    public abstract Move calculateMove();

    public void run() {
        result = calculateMove();
    }
}
