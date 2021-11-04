package edu.kingsu.SoftwareEngineering.Chess.Model.Pieces;

/**
 * This class represents a knight in a game of chess.
 */
public class Knight extends Piece {

    /**
     * Creates a new {@link Knight} instance.
     * @param isWhite a boolean which signifies the piece is white if <code>true</code>, black if <code>false</code>
     */
    public Knight(boolean isWhite) {
        super(isWhite);
    }

    @Override
    public Piece copyPiece() {
        return new Knight(isWhite());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PieceType getPieceType() {
        return PieceType.KNIGHT;
    }
}
