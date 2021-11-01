package edu.kingsu.SoftwareEngineering.Chess.Model.Moves;

import edu.kingsu.SoftwareEngineering.Chess.Model.*;
import edu.kingsu.SoftwareEngineering.Chess.Model.Pieces.*;

/**
 * Represents a potential move in a game of chess.
 * @see Board
 * @see Piece
 */
public class Move {

    private int rowFrom;
    private int colFrom;
    private int rowTo;
    private int colTo;

    /**
     * Creates a new {@link Move}.
     * @param rowFrom the row from which a piece should be move from
     * @param colFrom the column from which a piece should be move from
     * @param rowTo the row to which a piece should be move to
     * @param colTo the column to which a piece should be move to
     */
    public Move(int rowFrom, int colFrom, int rowTo, int colTo) {
        this.rowFrom = rowFrom;
        this.colFrom = colFrom;
        this.rowTo = rowTo;
        this.colTo = colTo;
    }

    /**
     * Performs the move action defined in this class on a given {@link Board}.
     * @param board the board on which to perform the move action
     * @see Board
     */
    public void perform(Board board) {
        return;
    }

    /**
     * Returns the {@link MoveType} enumeration value
     * corresponding to a particular instance of a
     * {@link Move}.
     * @return the {@link MoveType} enumeration value
     *  corresponding to this particular instance of {@link Move}.
     *  @see PieceType
     */
    public MoveType getType() {
        return MoveType.NORMAL;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return rowFrom*1+colFrom*8+rowTo*64+colTo*512;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Move) {
            Move m = (Move)obj;
            return rowFrom == m.rowFrom && colFrom == m.colFrom && rowTo == m.rowTo && colTo == m.colTo;
        } else {
            return false;
        }
    }
}
