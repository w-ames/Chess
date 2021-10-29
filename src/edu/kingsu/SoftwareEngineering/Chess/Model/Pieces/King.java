package edu.kingsu.SoftwareEngineering.Chess.Model.Pieces;

public class King extends Piece {
    public King(boolean isWhite) {
        super(isWhite);
    }

    @Override
    public PieceType getPieceType() {
        return PieceType.KING;
    }
}
