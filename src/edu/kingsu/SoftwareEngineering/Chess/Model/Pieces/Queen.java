package edu.kingsu.SoftwareEngineering.Chess.Model.Pieces;

public class Queen extends Piece {
    public Queen(boolean isWhite) {
        super(isWhite);
    }

    @Override
    public PieceType getPieceType() {
        return PieceType.QUEEN;
    }
}
