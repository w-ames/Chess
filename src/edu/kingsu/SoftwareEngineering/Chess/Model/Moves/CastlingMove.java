package edu.kingsu.SoftwareEngineering.Chess.Model.Moves;

import edu.kingsu.SoftwareEngineering.Chess.Model.*;
import edu.kingsu.SoftwareEngineering.Chess.Model.Pieces.*;

/**
 * Represents a potential castling-move in a game of chess.
 * @see Board
 * @see Piece
 * @see King
 * @see Rook
 */
public class CastlingMove extends Move {

    private int rowFromCastle;
    private int colFromCastle;
    private int rowToCastle;
    private int colToCastle;

    /**
     * Creates a new {@link CastlingMove}.
     * @param rowFrom the row from which a piece should be move from
     * @param colFrom the column from which a piece should be move from
     * @param rowTo the row to which a piece should be move to
     * @param colTo the column to which a piece should be move to
     * @param rowFromCastle the row from which a corresponding rook should be move from
     * @param colFromCastle the column from which a corresponding rook should be move from
     * @param rowToCastle the row to which a corresponding rook should be move to
     * @param colToCastle the column to which a corresponding rook should be move to
     */
    public CastlingMove(int rowFrom, int colFrom, int rowTo, int colTo, int rowFromCastle, int colFromCastle, int rowToCastle, int colToCastle) {
        super(rowFrom, colFrom, rowTo, colTo);
        this.rowFromCastle = rowFromCastle;
        this.colFromCastle = colFromCastle;
        this.rowToCastle = rowToCastle;
        this.colToCastle = colToCastle;
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
        return MoveType.CASTLING;
    }
}
