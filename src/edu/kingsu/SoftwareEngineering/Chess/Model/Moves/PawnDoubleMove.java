package edu.kingsu.SoftwareEngineering.Chess.Model.Moves;

import edu.kingsu.SoftwareEngineering.Chess.Model.*;
import edu.kingsu.SoftwareEngineering.Chess.Model.Pieces.*;

/**
 * Represents a two-space {@link Pawn} move in a game of chess.
 * @see Board
 * @see Piece
 * @see Pawn
 */
public class PawnDoubleMove extends Move {

    /**
     * Creates a new {@link PawnDoubleMove}.
     * @param rowFrom the row from which a piece should be move from
     * @param colFrom the column from which a piece should be move from
     * @param rowTo the row to which a piece should be move to
     * @param colTo the column to which a piece should be move to
     */
    public PawnDoubleMove(int rowFrom, int colFrom, int rowTo, int colTo) {
        super(rowFrom, colFrom, rowTo, colTo);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void perform(Board board) {
        super.perform(board);
        int middleRow = (getRowFrom()+getRowTo())/2;
        board.setEnPassantable(middleRow, getColTo());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MoveType getType() {
        return MoveType.PAWN_DOUBLE;
    }
}
