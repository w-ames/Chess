package edu.kingsu.SoftwareEngineering.Chess.Model.Pieces;

/**
 * This class represents a chess piece in a game of chess.
 */
public abstract class Piece {

    /**
     * Whether this piece is white or black (signified by <code>true</code> and <code>false</code>, respectively)
     */
    private boolean isWhite;

    /**
     * Initializes a new {@link Piece} instance.
     * @param isWhite a boolean which signifies the piece is white if <code>true</code>, black if <code>false</code>
     */
    public Piece(boolean isWhite) {
        this.isWhite = isWhite;
    }

    /**
     * Creates a new {@link Piece} instance.
     * @param color a string representing the color of the piece.
     */
    public Piece(String color) {
        this(color.equalsIgnoreCase("white"));
    }

    public abstract Piece copyPiece();

    /**
     * Returns a boolean signifying if this piece is white or black.
     * @return <code>true</code> if this piece is white, and <code>false</code> if this piece is black
     */
    public boolean isWhite() {
        return this.isWhite;
    }

    /**
     * Returns the {@link PieceType} enumeration value
     * corresponding to a particular instance of a
     * {@link Piece}.
     * @return the {@link PieceType} enumeration value
     *  corresponding to this particular instance of {@link Piece}.
     *  @see PieceType
     */
    public abstract PieceType getPieceType();

}
