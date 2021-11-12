package edu.kingsu.SoftwareEngineering.Chess.Model;

import edu.kingsu.SoftwareEngineering.Chess.Model.Moves.*;

/**
 * A thread instance specifically for calculating board moves that
 * may later be retrieved.
 */
public abstract class ChessAIThread extends Thread {
    private Move result;

    /**
     * Returns the result of calculating a move.
     * @return the result of calculating a move.
     */
    public Move getResult() {
        try {
            join();
            return result;
        } catch(InterruptedException e){
            return null;
        }
    }

    /**
     * Calculates a move result for this thread.
     * @return the move result for this thread
     */
    public abstract Move calculateMove();

    /**
     * {@inheritDoc}
     */
    public void run() {
        result = calculateMove();
    }
}
