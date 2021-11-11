package edu.kingsu.SoftwareEngineering.Chess.Model.Pieces;

/**
 * This class represents a queen in a game of chess.
 */
public class Queen extends Piece {

    /**
     * Creates a new {@link Queen} instance.
     * @param isWhite a boolean which signifies the piece is white if <code>true</code>, black if <code>false</code>
     */
    public Queen(boolean isWhite) {
        super(isWhite);
    }

    /**
     * Creates a new {@link Queen} instance.
     * @param color a string representing the color of the piece.
     */
    public Queen(String color) {
        super(color);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Piece copyPiece() {
        return new Queen(isWhite());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PieceType getPieceType() {
        return PieceType.QUEEN;
    }
}
