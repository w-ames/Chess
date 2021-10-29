package edu.kingsu.SoftwareEngineering.Chess.Model.Pieces;

/**
 * This class represents a pawn in a game of chess.
 */
public class Pawn extends Piece {

    /**
     * Creates a new {@link Pawn} instance.
     * @param isWhite a boolean which signifies the piece is white if <code>true</code>, black if <code>false</code>
     */
    public Pawn(boolean isWhite) {
        super(isWhite);
    }

    /**
     * {@inheritDoc}
     */
    public PieceType getPieceType() {
        return PieceType.PAWN;
    }

}
