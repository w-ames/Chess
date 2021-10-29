package edu.kingsu.SoftwareEngineering.Chess.Model.Pieces;

public class Rook extends Piece {
    public Rook(boolean isWhite) {
        super(isWhite);
    }

    @Override
    public PieceType getPieceType() {
        return PieceType.ROOK;
    }
}
