package edu.kingsu.SoftwareEngineering.Chess.Model.Pieces;

/**
 * This class represents a bishop in a game of chess.
 */
public class Bishop extends Piece {

    /**
     * Creates a new {@link Bishop} instance.
     * @param isWhite a boolean which signifies the piece is white if <code>true</code>, black if <code>false</code>
     */
    public Bishop(boolean isWhite) {
        super(isWhite);
    }

    @Override
    public Piece copyPiece() {
        return new Bishop(isWhite());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PieceType getPieceType() {
        return PieceType.BISHOP;
    }
}
