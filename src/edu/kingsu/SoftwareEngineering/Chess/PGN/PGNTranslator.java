package edu.kingsu.SoftwareEngineering.Chess.PGN;

import edu.kingsu.SoftwareEngineering.Chess.Model.*;
import edu.kingsu.SoftwareEngineering.Chess.Model.Moves.*;
import edu.kingsu.SoftwareEngineering.Chess.Model.Pieces.*;

public class PGNTranslator{

    String translateMoveToPGN(Move move, Board board) throws IllegalArgumentException{
        //make copy of the board and perform the move on it
        Board newBoard= new Board(board);
        move.perform(newBoard);

        //get the player's colour (true if white)
        boolean isWhite= movedPiece.isWhite();

        //special case: move is castle
        MoveType moveType= move.getType();  //must modify for other stuff (e.g. check)
        if(moveType == MoveType.CASTLING){
            if(move.getColTo() == ColumnLetter.B){
                if(board.getCheckmate(isWhite)) return "O-O-O#";
                else if(board.getCheck(isWhite)) return "O-O-O+";
                else return "O-O-O";
            }

            if(move.getColTo() == ColumnLetter.G){
                if(board.getCheckmate(isWhite)) return "O-O#";
                else if(board.getCheck(isWhite)) return "O-O+";
                else return "O-O";
            }

            throw new IllegalArgumentException("Castling move to an illegal column");
        }

        //strings representing each component of the algebraic move
        String piece= "";
        String disambFile= "";  //files are columns
        String disambRank= "";  //ranks are rows
        String capture= "";
        String destFile= "";
        String destRank= "";
        String pawnPromo= "";
        String enPassant= "";
        String check= "";
        String checkmate= "";

        //get the piece that was moved
        int originCol= move.getColFrom();
        int originRow= move.getRowFrom();

        if(originCol < 0 || originCol > 7) throw new IllegalArgumentException("Illegal origin column");
        if(originRow < 0 || originRow > 7) throw new IllegalArgumentException("Illegal origin row");

        Piece movedPiece= board.getPiece(originRow, originCol);
        if(movedPiece == null) throw new IllegalArgumentException("No piece at origin square");

        PieceType pieceType= movedPiece.getPieceType();
        piece= convertPieceTypeToString(pieceType);

        //get the square the piece moved to
        int destRow= move.rowTo();
        int destCol= move.colTo();

        if(destRow < 0 || destRow > 7) throw new IllegalArgumentException("Illegal destination row");
        if(destCol < 0 || destCol > 7) throw new IllegalArgumentException("Illegal destination column");

        destRank= "" + (destRow + 1);
        destFile= convertColLetterToString(ColumnLetter.values()[destCol])

        //disambiguate if necessary
        //************************************************************************* */
        //TO-DO





        //************************************************************************* */

        //find out if a capture occurred
        Piece destPiece= board.getPiece(destRow, move.getColTo());
        if(destPiece != null) capture= "x";

        //find out if a pawn got promoted
        if(moveType == MoveType.PAWN_PROMOTION)
            pawnPromo= convertPieceTypeToString(move.getPromotionClass());
        

        //check if en passant occurred
        if(moveType == MoveType.EN_PASSANT)
            enPassant= " e.p.";

        //find out if opponent's king is in check/checkmate
        if(board.getCheckmate(isWhite)) checkmate= "#";
        else if(board.getCheck(isWhite)) check= "+";


        return piece + disambFile + disambRank + capture + destFile + destRank +
            pawnPromo + enPassant + check + checkmate;
    }

    Move translatePGNToMove(String pgn, Board board){

        return null;
    }

    private static String convertPieceTypeToString(PieceType pieceType){
        if(pieceType == PieceType.KING) return "K";
        else if(pieceType == PieceType.QUEEN) return "Q";
        else if(pieceType == PieceType.ROOK) return "R";
        else if(pieceType == PieceType.BISHOP) return "B";
        else if(pieceType == PieceType.KNIGHT) return "N";
        else if(pieceType == PieceType.PAWN) return "";
        else throw new IllegalArgumentException("Illegal parameter");
    }

    private static String convertColLetterToString(ColumnLetter col){
        if(col == ColumnLetter.A) return "a";
        else if(col == ColumnLetter.B) return "b";
        else if(col == ColumnLetter.C) return "c";
        else if(col == ColumnLetter.D) return "d";
        else if(col == ColumnLetter.E) return "e";
        else if(col == ColumnLetter.F) return "f";
        else if(col == ColumnLetter.G) return "g";
        else if(col == ColumnLetter.H) return "h";
        else throw new IllegalArgumentException("Illegal parameter");
    }


}