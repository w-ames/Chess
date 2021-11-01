package edu.kingsu.SoftwareEngineering.Chess.Model.Pieces;

/**
 * This class represents a king in a game of chess.
 */
public class King extends Piece {

    /**
     * Creates a new {@link King} instance.
     * @param isWhite a boolean which signifies the piece is white if <code>true</code>, black if <code>false</code>
     */
    public King(boolean isWhite) {
        super(isWhite);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PieceType getPieceType() {
        return PieceType.KING;
    }
}
