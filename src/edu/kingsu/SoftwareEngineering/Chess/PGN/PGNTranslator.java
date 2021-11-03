package edu.kingsu.SoftwareEngineering.Chess.PGN;

import edu.kingsu.SoftwareEngineering.Chess.Model.*;
import edu.kingsu.SoftwareEngineering.Chess.Model.Moves.*;
import edu.kingsu.SoftwareEngineering.Chess.Model.Pieces.*;
import java.util.List;
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class PGNTranslator{

    public static String translateMoveToPGN(Move move, Board board) throws IllegalArgumentException{
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

            throw new IllegalStateException("Castling move to an illegal column");
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

        if(originCol < 0 || originCol > 7) throw new IllegalStateException("Illegal origin column");
        if(originRow < 0 || originRow > 7) throw new IllegalStateException("Illegal origin row");

        Piece movedPiece= board.getPiece(originRow, originCol);
        if(movedPiece == null) throw new IllegalStateException("No piece at origin square");

        PieceType pieceType= movedPiece.getPieceType();
        piece= convertPieceTypeToString(pieceType);

        //get the square the piece moved to
        int destRow= move.rowTo();
        int destCol= move.colTo();

        if(destRow < 0 || destRow > 7) throw new IllegalStateException("Illegal destination row");
        if(destCol < 0 || destCol > 7) throw new IllegalStateException("Illegal destination column");

        destRank= "" + (destRow + 1);
        destFile= convertColLetterToString(ColumnLetter.values()[destCol]);

        //disambiguate

        //get a list of all squares containing pieces which can attack the same square
        List<List<Integer>> attackSameSquare= board.getAttackers(destRow, destCol);

        //distill this list to only include pieces of the same PieceType, excluding the same piece
        List<List<Integer>> samePieceType= new ArrayList<List<Integer>>();

        for(int i=0; i < attackSameSquare.size(); i++){
            int row= attackSameSquare.get(i).get(0);
            int col= attackSameSquare.get(i).get(1);
            if(!(row == originRow && col == originCol) && board.getPiece(row, col).getPieceType() == pieceType) //if not same exact piece that just moved but the piece type is the same
                samePieceType.add(attackSameSquare.get(i));
        }

        //disambiguate from these pieces
        for(int i=0; i < samePieceType.size(); i++){
            if(samePieceType.get(i).get(1) == originCol){
                //if the piece is in the same column as the piece which was moved,
                //disambiguate the column
                disambFile= convertColLetterToString(ColumnLetter.values()[originCol]);
            }
            if(samePieceType.get(i).get(0) == originRow){
                //if the piece is in the same row as the piece which was moved,
                //disambiguate the row
                disambRank= "" + (originRow + 1);
            }
        }

        //find out if a capture occurred
        Piece destPiece= board.getPiece(destRow, move.getColTo());
        if(destPiece != null) capture= "x";

        //find out if a pawn got promoted
        if(moveType == MoveType.PAWN_PROMOTION)
            pawnPromo= "=" + convertPieceTypeToString(move.getPromotionType());
        

        //check if en passant occurred
        if(moveType == MoveType.EN_PASSANT)
            enPassant= " e.p.";

        //find out if opponent's king is in check/checkmate
        if(board.getCheckmate(isWhite)) checkmate= "#";
        else if(board.getCheck(isWhite)) check= "+";


        return piece + disambFile + disambRank + capture + destFile + destRank +
            pawnPromo + enPassant + check + checkmate;
    }

    public static Move translatePGNToMove(String pgn, Board board){
        //use Pattern named-capturing groups to capture the pgn in a series of variables
        String regex=
            "(?<piece>[PKQRBN]?)(?<disambFile>[a-h]?)(?<disambRank>[1-8]?)" +
            "(?<capture>x?)(?<destFile>[a-h])(?<destRank>[1-8])=?(?<pawnPromo>[QRBN]?)" +
            "(?<enPassant>(\\s?e\\.?p\\.?)?)(?<check>\\+?)(?<checkmate>[#\\+]?)" +
            "\\s*[\\!\\?]+?|(?<castle>O-?O(-?O)?)(?<castleCheck>\\+?)" +
            "(?<castleCheckmate>[#\\+]?)\\s*[\\!\\?]+?";

        //this pattern DOES NOT take into account the number of a move, more than
        //one move (e.g. a white move and a black move), a comment {in curly braces},
        //a branch (in parentheses), or any whitespace before or after a move

        //this pattern DOES take into account any number of ! and ? separated from
        //the move by a space
        
        Pattern pattern= Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher= pattern.matcher(pgn);

        //throw an error if the form didn't match
        if(!matcher.find()) throw new IllegalStateException("The move provided did not match the form of a PGN move");
        
        //put the capture groups into their own strings
        String piece= matcher.group("piece");
        String disambFile= matcher.group("disambFile");
        String disambRank= matcher.group("disambRank");
        String capture= matcher.group("capture");
        String destFile= matcher.group("destFile");
        String destRank= matcher.group("destRank");
        String pawnPromo= matcher.group("pawnPromo");
        String enPassant= matcher.group("enPassant");
        String check= matcher.group("check");
        String checkmate= matcher.group("checkmate");
        String castle= matcher.group("castle");
        String castleCheck= matcher.group("castleCheck");
        String castleCheckmate= matcher.group("castleCheckmate");

        //if the form was castling, do castling stuff
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

    /*preliminary test code
    import java.util.regex.Pattern;
    import java.util.regex.Matcher;

    public class Main
    {
        public static void main(String[] args) {
            String pgn= "Qxe7";
            System.out.println(translatePGNToMove(pgn));
        }
        
        public static String translatePGNToMove(String pgn){
            String regex=
                "(?<piece>[PKQRBN]?)(?<disambFile>[a-h]?)(?<disambRank>[1-8]?)" +
                "(?<capture>x?)(?<destFile>[a-h])(?<destRank>[1-8])=?(?<pawnPromo>[QRBN]?)" +
                "(?<enPassant>(\\s?e\\.?p\\.?)?)(?<check>\\+?)(?<checkmate>[#\\+]?)" +
                "(?<expression>\\s*[\\!\\?]+?)|(?<castle>O-?O(-?O)?)(?<castleCheck>\\+?)" +
                "(?<castleCheckmate>[#\\+]?)(?<castleExpression>\\s*[\\!\\?]+?)";
                
            Pattern pattern= Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
            Matcher matcher= pattern.matcher(pgn);
            
            if(matcher.find()){
                String str= "";
                str += "Piece=" + matcher.group("piece") + "\n";
                str += "DisambFile=" + matcher.group("disambFile") + "\n";
                str += "DisambRank=" + matcher.group("disambRank") + "\n";
                str += "Capture=" + matcher.group("capture") + "\n";
                str += "DestFile=" + matcher.group("destFile") + "\n";
                str += "DestRank=" + matcher.group("destRank") + "\n";
                str += "PawnPromo=" + matcher.group("pawnPromo") + "\n";
                str += "EnPassant=" + matcher.group("enPassant") + "\n";
                str += "Check=" + matcher.group("check") + "\n";
                str += "Checkmate=" + matcher.group("checkmate") + "\n";
                str += "Castle=" + matcher.group("castle") + "\n";
                str += "CastleCheck=" + matcher.group("castleCheck") + "\n";
                str += "CastleCheckmate=" + matcher.group("castleCheckmate") + "\n";
                return str;
            }
            return null;
        }
    }*/
}