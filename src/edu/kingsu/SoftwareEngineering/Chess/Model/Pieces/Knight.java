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

    /**
     * Creates a new {@link Knight} instance.
     * @param color a string representing the color of the piece.
     */
    public Knight(String color) {
        super(color);
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
