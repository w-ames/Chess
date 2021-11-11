package edu.kingsu.SoftwareEngineering.Chess.Model;

import edu.kingsu.SoftwareEngineering.Chess.Model.Moves.*;

public abstract class ChessAIThread extends Thread {
    private Move result;

    public Move getResult() {
        try {
            join();
            return result;
        } catch(InterruptedException e){
            return null;
        }
    }

    public abstract Move calculateMove();

    public void run() {
        result = calculateMove();
    }
}
