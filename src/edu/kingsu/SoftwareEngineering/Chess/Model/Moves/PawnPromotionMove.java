package edu.kingsu.SoftwareEngineering.Chess.Model.Moves;

import edu.kingsu.SoftwareEngineering.Chess.Model.*;
import edu.kingsu.SoftwareEngineering.Chess.Model.Pieces.*;

/**
 * Represents a promoting-move for a {@link Pawn} in a game of chess.
 * @see Board
 * @see Piece
 * @see Pawn
 */
public class PawnPromotionMove extends Move {

    private PieceType promotionType;

    /**
     * Creates a new {@link PawnPromotionMove}.
     * @param rowFrom the row from which a piece should be move from
     * @param colFrom the column from which a piece should be move from
     * @param rowTo the row to which a piece should be move to
     * @param colTo the column to which a piece should be move to
     */
    public PawnPromotionMove(int rowFrom, int colFrom, int rowTo, int colTo, PieceType promotionType) {
        super(rowFrom, colFrom, rowTo, colTo);
        this.promotionType = promotionType;
    }

    /**
     * Gives the {@link PieceType} enumeration value to which a the moving {@link Pawn} is promoted.
     * @return the piece type to which the pawn is promoted
     */
    public PieceType getPromotionType() {
        return promotionType;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void perform(Board board) {
        boolean whiteIsPerforming = board.getPiece(getRowFrom(), getColFrom()).isWhite();
        super.perform(board);
        Piece promotedPiece = null;
        switch (promotionType) {
            case KNIGHT:
                promotedPiece = new Knight(whiteIsPerforming);
                break;
            case BISHOP:
                promotedPiece = new Bishop(whiteIsPerforming);
                break;
            case ROOK:
                promotedPiece = new Rook(whiteIsPerforming);
                break;
            case QUEEN:
                promotedPiece = new Queen(whiteIsPerforming);
                break;
            default:
                break;
        }
        board.setPiece(getRowTo(), getColTo(), promotedPiece);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MoveType getType() {
        return MoveType.PAWN_PROMOTION;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        PieceType[] pta = PieceType.values();
        int i;
        for (i=0; i<pta.length; i++) {
            if (pta[i] == promotionType) {
                break;
            }
        }
        return super.hashCode()+i*4096;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof PawnPromotionMove) {
            PawnPromotionMove m = (PawnPromotionMove)obj;
            return super.equals(obj) && promotionType == m.promotionType;
        } else {
            return false;
        }
    }
}
