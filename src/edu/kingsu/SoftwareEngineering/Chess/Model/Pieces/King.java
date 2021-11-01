package edu.kingsu.SoftwareEngineering.Chess.Model.Pieces;

/**
 * This class represents a king in a game of chess.
 */
public class King extends Piece {

    private boolean doneCastling;

    /**
     * Creates a new {@link King} instance.
     * @param isWhite a boolean which signifies the piece is white if <code>true</code>, black if <code>false</code>
     */
    public King(boolean isWhite) {
        super(isWhite);
        this.doneCastling = false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PieceType getPieceType() {
        return PieceType.KING;
    }

    /**
     * Returns a boolean representing whether this piece is finished castling.
     * @return <code>true</code> if this piece is done castling, and <code>false</code> if it is still able to castle
     */
    public boolean isDoneCastling() {
        return doneCastling;
    }

    /**
     * Sets the flag for this {@link King} to be finished performing castling moves
     */
    public void doneCastling() {
        doneCastling = true;
    }
}
