package edu.kingsu.SoftwareEngineering.Chess.Model.Moves;

import edu.kingsu.SoftwareEngineering.Chess.Model.*;
import edu.kingsu.SoftwareEngineering.Chess.Model.Pieces.*;

/**
 * Represents a en passant {@link Pawn} move in a game of chess.
 * @see Board
 * @see Piece
 * @see Pawn
 */
public class EnPassantMove extends Move {

    private int rowPassant;
    private int colPassant;

    /**
     * Creates a new {@link EnPassantMove}.
     * @param rowFrom the row from which a piece should be move from
     * @param colFrom the column from which a piece should be move from
     * @param rowTo the row to which a piece should be move to
     * @param colTo the column to which a piece should be move to
     * @param rowPassant the row containing the captured {@link Pawn} via en passant
     * @param colPassant the column containing the captured {@link Pawn} via en passant
     * @see Pawn
     */
    public EnPassantMove(int rowFrom, int colFrom, int rowTo, int colTo, int rowPassant, int colPassant) {
        super(rowFrom, colFrom, rowTo, colTo);
        this.rowPassant = rowPassant;
        this.colPassant = colPassant;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void perform(Board board) {
        super.perform(board);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MoveType getType() {
        return MoveType.EN_PASSANT;
    }
}
