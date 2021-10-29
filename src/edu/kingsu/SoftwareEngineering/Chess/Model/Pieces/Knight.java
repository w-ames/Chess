package edu.kingsu.SoftwareEngineering.Chess.Model.Pieces;

public class Knight extends Piece {
    public Knight(boolean isWhite) {
        super(isWhite);
    }

    @Override
    public PieceType getPieceType() {
        return PieceType.KNIGHT;
    }
}
