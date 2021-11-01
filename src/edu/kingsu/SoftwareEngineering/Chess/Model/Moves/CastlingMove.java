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
     * Returns the row from which a second rook piece will move via this {@link CastlingMove}.
     * @return the row from which a second rook piece will move
     */
    public int getRowFromCastle() { return rowFromCastle; }
    /**
     * Returns the col from which a second rook piece will move via this {@link CastlingMove}.
     * @return the col from which a second rook piece will move
     */
    public int getColFromCastle() { return colFromCastle; }
    /**
     * Returns the row to which a second rook piece will move via this {@link CastlingMove}.
     * @return the row to which a second rook piece will move
     */
    public int getRowToCastle() { return rowToCastle; }
    /**
     * Returns the col to which a second rook piece will move via this {@link CastlingMove}.
     * @return the col to which a second rook piece will move
     */
    public int getColToCastle() { return colToCastle; }

    /**
     * {@inheritDoc}
     */
    @Override
    public void perform(Board board) {
        Piece movingKing = board.getPiece(getRowFrom(), getColFrom());
        Piece movingRook = board.getPiece(rowFromCastle, colFromCastle);
        if (movingKing.getPieceType() == PieceType.KING) {
            ((King)movingKing).doneCastling();
        }
        if (movingRook.getPieceType() == PieceType.ROOK) {
            ((Rook)movingRook).doneCastling();
        }
        super.perform(board);
        board.setPiece(rowToCastle, colToCastle, movingRook);
        board.setPiece(rowFromCastle, colFromCastle, null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MoveType getType() {
        return MoveType.CASTLING;
    }
}
