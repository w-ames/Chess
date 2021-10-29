package edu.kingsu.SoftwareEngineering.Chess.PGN;

import edu.kingsu.SoftwareEngineering.Chess.Model.*;
import edu.kingsu.SoftwareEngineering.Chess.Model.Moves.*;
import edu.kingsu.SoftwareEngineering.Chess.Model.Pieces.*;

public class PGNTranslator{

    String translateMoveToPGN(Move move, Board board) throws IllegalArgumentException{
        //special case: move is castle
        MoveType moveType= move.getType();
        if(moveType == CASTLING){
            if(move.getColTo() == ColumnLetter.B) return "O-O-O";
            return "O-O";
        }

        //strings representing each component of the algebraic move
        String piece= "";
        String disambFile= "";  //files are columns
        String disambRank= "";  //ranks are rows
        String capture= "";
        String destFile= "";
        String destRank= "";
        String pawnPromo= "";
        String check= "";
        String checkmate= "";

        //get the piece that was moved
        int originCol= move.getColFrom();
        int originRow= move.getRowFrom();

        Piece movedPiece= board.getPiece(originRow, originCol);
        if(movedPiece == null) throw new IllegalArgumentException("No piece at origin square");
        PieceType pieceType= movedPiece.getPieceType();

        if(pieceType == PieceType.KING) piece= "K";
        else if(pieceType == PieceType.QUEEN) piece= "Q";
        else if(pieceType == PieceType.ROOK) piece= "R";
        else if(pieceType == PieceType.BISHOP) piece= "B";
        else if(pieceType == PieceType.KNIGHT) piece= "N";

        //get the square the piece moved to
        int destRow= move.getRowTo();
        ColumnLetter destCol= move.getColTo();

        if(destRow < 0 || destRow > 7) throw new IllegalArgumentException("Illegal row");

        destRank= "" + (destRow + 1);

        if(destCol == ColumnLetter.A) destFile= "a";
        else if(destCol == ColumnLetter.B) destFile= "b";
        else if(destCol == ColumnLetter.C) destFile= "c";
        else if(destCol == ColumnLetter.D) destFile= "d";
        else if(destCol == ColumnLetter.E) destFile= "e";
        else if(destCol == ColumnLetter.F) destFile= "f";
        else if(destCol == ColumnLetter.G) destFile= "g";
        else if(destCol == ColumnLetter.H) destFile= "h";
        else throw new IllegalArgumentException("Illegal column");

        //disambiguate if necessary
        //************************************************************************* */
        //TO-DO





        //************************************************************************* */

        //find out if a capture occurred
        Piece destPiece= board.getPiece(destRow, move.getColTo());
        if(destPiece != null) capture= "x";

        return null;
    }

    Move translatePGNToMove(String pgn, Board board){

        return null;
    }


}