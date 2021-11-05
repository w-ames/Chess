package edu.kingsu.SoftwareEngineering.Chess.Model;

import edu.kingsu.SoftwareEngineering.Chess.Model.Moves.*;

public abstract class ChessAIThread extends Thread {
    private Move result;

    public Move getResult() { return result; } // add join...

    public abstract Move calculateMove();

    public void run() {
    }
}
