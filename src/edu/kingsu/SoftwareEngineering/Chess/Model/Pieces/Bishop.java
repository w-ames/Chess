package edu.kingsu.SoftwareEngineering.Chess.Model.Pieces;

public class Bishop extends Piece {
    public Bishop(boolean isWhite) {
        super(isWhite);
    }

    @Override
    public PieceType getPieceType() {
        return PieceType.BISHOP;
    }
}
