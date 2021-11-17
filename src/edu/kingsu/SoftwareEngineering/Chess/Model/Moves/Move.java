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
     * Returns the row from which a piece will move via this {@link Move}.
     * @return the row from which a piece will move
     */
    public int getRowFrom() { return rowFrom; }
    /**
     * Returns the col from which a piece will move via this {@link Move}.
     * @return the col from which a piece will move
     */
    public int getColFrom() { return colFrom; }
    /**
     * Returns the row to which a piece will move via this {@link Move}.
     * @return the row to which a piece will move
     */
    public int getRowTo() { return rowTo; }
    /**
     * Returns the col to which a piece will move via this {@link Move}.
     * @return the col to which a piece will move
     */
    public int getColTo() { return colTo; }

    /**
     * Performs the move action defined in this class on a given {@link Board}.
     * @param board the board on which to perform the move action
     * @see Board
     */
    public void perform(Board board) {
        Piece movingPiece = board.getPiece(rowFrom, colFrom);
        if (movingPiece != null) {
            if (movingPiece.getPieceType() == PieceType.PAWN) {
                ((Pawn)movingPiece).doneDoubleMove();
            } else if (movingPiece.getPieceType() == PieceType.KING) {
                ((King)movingPiece).doneCastling();
            } else if (movingPiece.getPieceType() == PieceType.ROOK) {
                ((Rook)movingPiece).doneCastling();
            }
        }
        board.setPiece(rowTo, colTo, movingPiece);
        board.setPiece(rowFrom, colFrom, null);
        board.setEnPassantable();
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
     * Returns a boolean representing whether this move has the
     * same destination as another {@link Move}.
     * @param otherMove the move whose destination will be compared
     *  with that of this instance
     * @return <code>true</code> if the moves have the same target
     *  destination row and column, <code>false</code> otherwise
     */
    @Deprecated
    public boolean hasSameDestination(Move otherMove) {
        return rowTo == otherMove.rowTo && colTo == otherMove.colTo;
    }

    /**
     * Returns a boolean representing whether or not this move
     * targets a given location.
     * @param r the target row
     * @param c the target column
     * @return <code>true</code> if this move targets the given
     *  row and column, otherwise <code>false</code>
     */
    public boolean hasDestination(int r, int c) {
        return rowTo == r && colTo == c;
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
