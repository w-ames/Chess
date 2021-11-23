package edu.kingsu.SoftwareEngineering.Chess.PGN;

import edu.kingsu.SoftwareEngineering.Chess.Model.*;
import edu.kingsu.SoftwareEngineering.Chess.Model.Moves.*;
import edu.kingsu.SoftwareEngineering.Chess.Model.Pieces.*;
import java.util.List;
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 * This class provides static methods for the translation of Move objects to Strings
 * in chess algebraic notation, and vice versa.
 */
public class PGNTranslator{
    /**
     * Translates a Move object to a String representation of the move, which is
     * algebraic notation, and can be used in a PGN file.
     * @param move  the Move object to translate
     * @param board the board on which the Move will be made. The board must be
     *  in a state before the Move has been performed
     * @return the String representation of the move (in algebraic notation)
     * @throws IllegalStateException if the Move is invalid
     */
    public static String translateMoveToPGN(Move move, Board board) throws IllegalStateException{
        //make copy of the board and perform the move on it
        Board newBoard= new Board(board);
        move.perform(newBoard);

        //get the piece that was moved
        int originCol= move.getColFrom();
        int originRow= move.getRowFrom();

        if(originCol < 0 || originCol > 7) throw new IllegalStateException("Illegal origin column");
        if(originRow < 0 || originRow > 7) throw new IllegalStateException("Illegal origin row");

        Piece movedPiece= board.getPiece(originRow, originCol);
        if(movedPiece == null) throw new IllegalStateException("No piece at origin square");

        //get the player's colour (true if white)
        boolean isWhite= movedPiece.isWhite();

        //special case: move is castle
        MoveType moveType= move.getType();
        if(moveType == MoveType.CASTLING){
            if(move.getColTo()  == ColumnLetter.C.ordinal()){
                if(newBoard.getCheckmate(isWhite)) return "O-O-O#";
                else if(newBoard.getCheck(isWhite)) return "O-O-O+";
                else return "O-O-O";
            }else if(move.getColTo() == ColumnLetter.G.ordinal()){
                if(newBoard.getCheckmate(isWhite)) return "O-O#";
                else if(newBoard.getCheck(isWhite)) return "O-O+";
                else return "O-O";
            }else throw new IllegalStateException("Castling move to an illegal column");
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

        PieceType pieceType= movedPiece.getPieceType();
        piece= convertPieceTypeToString(pieceType);

        //get the square the piece moved to
        int destRow= move.getRowTo();
        int destCol= move.getColTo();

        if(destRow < 0 || destRow > 7) throw new IllegalStateException("Illegal destination row");
        if(destCol < 0 || destCol > 7) throw new IllegalStateException("Illegal destination column");

        destRank= "" + convertRowToRank(destRow);
        destFile= convertColLetterToString(ColumnLetter.values()[destCol]);

        //disambiguate

        //get a list of all squares containing pieces which can attack the same square
        List<List<Integer>> attackSameSquare= board.getAttackers(!isWhite, destRow, destCol);

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

            if(samePieceType.get(i).get(1) == originCol){                                                     //not entering here!!!!!!!!!!!!!Why not?????????
                //if the piece is in the same column as the piece which was moved or another disambiguation piece,
                //disambiguate the row
                disambRank= "" + convertRowToRank(originRow);
            }else{
                //disambiguate file
                disambFile= convertColLetterToString(ColumnLetter.values()[originCol]);
            }
        }

        //find out if a capture occurred
        Piece destPiece= board.getPiece(destRow, move.getColTo());
        if(destPiece != null) capture= "x";

        //find out if a pawn got promoted
        if(moveType == MoveType.PAWN_PROMOTION)
            pawnPromo= "=" + convertPieceTypeToString(((PawnPromotionMove)move).getPromotionType());
        
        //check if en passant occurred
        if(moveType == MoveType.EN_PASSANT){
            enPassant= " e.p.";
            capture= "x";
        }

        //find out if opponent's king is in check/checkmate
        if(newBoard.getCheckmate(isWhite)) checkmate= "#";
        else if(newBoard.getCheck(isWhite)) check= "+";

        return piece + disambFile + disambRank + capture + destFile + destRank +
            pawnPromo + enPassant + check + checkmate;
    }

    /**
     * Translates a String in algebraic notation (used in PGN files)
     * to a Move object.
     * @param pgn the String representation of the move
     * @param board the board on which the move is to be performed
     * @param playerIsWhite <code>true</code> if the player making the move is
     *  white, <code>false</code> if the player is black
     * @return the Move object which matches the move indicated by the pgn String.
     * @throws IllegalArgumentException if the move doesn't match the format of an algebraic move, or 
     *  if the move is invalid on the given board
     * @throws IllegalStateException if the move matches both the form of a normal move and a castling move
     */
    public static Move translatePGNToMove(String pgn, Board board, boolean playerIsWhite) throws IllegalArgumentException, IllegalStateException{
        //use Pattern named-capturing groups to capture the pgn in a series of variables
        String regex=
            "^(?<piece>[PKQRBN]?)(?<disambFile>[a-h]?)(?<disambRank>[1-8]?)" +
            "(?<capture>x?)(?<destFile>[a-h])(?<destRank>[1-8])=?(?<pawnPromo>[QRBN]?)" +
            "(?<enPassant>(\\s?e\\.?p\\.?)?)(?<check>\\+?)(?<checkmate>[#\\+]?)" +
            "\\s*[\\!\\?]*$|^(?<castle>O-?O((-?O)?)?)(?<castleCheck>\\+?)" +
            "(?<castleCheckmate>[#\\+]?)\\s*[\\!\\?]*$";

        //this pattern DOES NOT take into account the number of a move, more than
        //one move (e.g. a white move and a black move), a comment {in curly braces}
        //or a comment after a ;, a branch (in parentheses), or any whitespace before or after a move

        //this pattern DOES take into account any number of ! and ? separated from
        //the move by a space
        
        Pattern pattern= Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher= pattern.matcher(pgn);

        //throw an error if the form didn't match
        if(!matcher.find()) throw new IllegalArgumentException("The move provided did not match the form of a PGN move");
        
        //put the capture groups into their own strings
        String piece= matcher.group("piece");
        //if piece is "p" or "P", convert it to an empty string for later logic
        if(piece != null && piece.equalsIgnoreCase("p")) piece= "";

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

        if(piece == null && disambFile == null && disambRank == null && capture == null &&
        destFile == null && destRank == null && pawnPromo == null && enPassant == null &&
        check == null && checkmate == null && castle != null && castleCheck != null &&
        castleCheckmate != null){
            //if the form was castling, do castling stuff

            Move move;
            if(castle.equalsIgnoreCase("O-O") || castle.equalsIgnoreCase("OO")){
                if(playerIsWhite){
                    move = getMoveWithMoveType(board, 7, 4, 7, 6, pawnPromo);
                }else{
                    move = getMoveWithMoveType(board, 0, 4, 0, 6, pawnPromo);
                }
            }else if(castle.equalsIgnoreCase("O-O-O") || castle.equalsIgnoreCase("OOO")){
                if(playerIsWhite){
                    move = getMoveWithMoveType(board, 7, 4, 7, 2, pawnPromo);
                }else{
                    move = getMoveWithMoveType(board, 0, 4, 0, 2, pawnPromo);
                }
            }else throw new IllegalArgumentException("Incorrect castling move format");

            return move;
        }else if(piece != null && disambFile != null && disambRank != null && capture != null &&
        destFile != null && destRank != null && pawnPromo != null && enPassant != null &&
        check != null && checkmate != null && castle == null && castleCheck == null &&
        castleCheckmate == null){
            //if the form was normal, do normal stuff

            //convert string destFile to int toCol
            int toCol= -1;
            for(ColumnLetter colLet: ColumnLetter.values()){
                if(colLet.toString().equalsIgnoreCase(destFile)){
                    toCol= colLet.ordinal();
                }
            }
            if(toCol == -1) throw new IllegalArgumentException("Illegal destination file");

            //get a list of all squares containing pieces which can move to the destination square
            List<List<Integer>> attackers= board.getAttackers(!playerIsWhite, convertRankToRow(destRank), toCol);

            //if(true) throw new IllegalArgumentException("attackers.size()=" + attackers.size());
            //refine to a list containing only attackers of the same piece type
            List<List<Integer>> samePieceType= new ArrayList<List<Integer>>();

            for(int i=0; i < attackers.size(); i++){
                int row= attackers.get(i).get(0);
                int col= attackers.get(i).get(1);
                PieceType pieceType= board.getPiece(row, col).getPieceType();

                //if the piece at this square is the same piece type as the piece which was moved,
                //add it to the refined list
                if(convertPieceTypeToString(pieceType).equalsIgnoreCase(piece)){
                    samePieceType.add(attackers.get(i));
                }
            }

            //use the disambiguation to further refine this list to a single element, if necessary
            List<List<Integer>> matchDisamb= new ArrayList<List<Integer>>();

            //convert string disambFile to int fromCol
            int fromCol= -1;
            for(ColumnLetter colLet: ColumnLetter.values()){
                if(colLet.toString().equalsIgnoreCase(disambFile)){
                    fromCol= colLet.ordinal();
                }
            }

            for(int i=0; i < samePieceType.size(); i++){
                if((fromCol != -1 && fromCol == samePieceType.get(i).get(1)) || fromCol == -1){ //if (the disamb col is not blank and the disamb col matches this square's col) OR the disamb col is blank
                    if((!disambRank.equals("") && convertRankToRow(disambRank) == samePieceType.get(i).get(0)) || disambRank.equals(""))    //if (the disamb row is not blank and the disamb row matches this square's row) OR the disamb row is blank
                        matchDisamb.add(samePieceType.get(i));  //add this square to matchDisamb
                }    
            }

            if(matchDisamb.size() == 0) throw new IllegalArgumentException("No piece matches this move");
            else if(matchDisamb.size() > 1) throw new IllegalArgumentException("Ambiguous move");

            List<Integer> originSquare= matchDisamb.get(0);
            // get the move from the calculated list of moves, since the calculated list of
            // moves includes the correct move type
            Move translatedMove = getMoveWithMoveType(board, originSquare.get(0), originSquare.get(1), convertRankToRow(destRank), toCol, pawnPromo);
            return translatedMove;

        }else throw new IllegalStateException("Cannot perform normal move and castling move at same time");
    }

    /**
     * Retrieves the Move matching the expected MoveType given the board, original square, and destination of
     * the moved piece.
     * @param board the board on which the move is to be made
     * @param startRow the original row the moved piece is on (values 0 through 7 are valid, corresponding to ranks 8 through 1 respectively)
     * @param startCol the original column the moved piece is on (values 0 through 7 are valid, corresponding to files A through H respectively)
     * @param endRow the destination row the moved piece will go to (values 0 through 7 are valid, corresponding to ranks 8 through 1 respectively)
     * @param endCol the destination column the moved piece will go to (values 0 through 7 are valid, corresponding to files A through H respectively)
     * @param pawnPromo the letter corresponding to the piece a pawn should be promoted to (Q, R, B, and N are valid), or <code>null</code>
     *  if the move does not result in pawn promotion.
     * @return the Move which matches the expected MoveType of the given move
     */
    private static Move getMoveWithMoveType(Board board, int startRow, int startCol, int endRow, int endCol, String pawnPromo) {
        Move translatedMove = null;
        List<Move> movesList = board.getMoves(startRow, startCol);
        for (int i=0; i < movesList.size(); i++) {
            Move move= movesList.get(i);
            if (move.hasDestination(endRow, endCol)) {
                if(pawnPromo != null && !pawnPromo.equals("")){
                    if(convertPieceTypeToString(((PawnPromotionMove)move).getPromotionType()).equalsIgnoreCase(pawnPromo)){
                        translatedMove = move;
                        break;
                    }
                }else{
                    translatedMove = move;
                    break;
                }
            }
        }
        return translatedMove;
    }

    /**
     * Returns the single letter String corresponding to the given PieceType.
     * @param pieceType the piece type to be converted to a String
     * @return the String shorthand representation of the given PieceType
     */
    private static String convertPieceTypeToString(PieceType pieceType){
        if(pieceType == PieceType.KING) return "K";
        else if(pieceType == PieceType.QUEEN) return "Q";
        else if(pieceType == PieceType.ROOK) return "R";
        else if(pieceType == PieceType.BISHOP) return "B";
        else if(pieceType == PieceType.KNIGHT) return "N";
        else if(pieceType == PieceType.PAWN) return "";
        else throw new IllegalArgumentException("Illegal parameter");
    }

    /**
     * Returns the String representation of the given ColumnLetter
     * @param col the ColumnLetter to be converted to a String
     * @return the String representation of the given ColumnLetter, as a lowercase letter
     */
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

    /**
     * Returns the numeric rank corresponding to the given row
     * @param row the row number to be converted to a chess rank
     * @return the corresponding rank of the given row. For example, if the given row
     *  is 0, the rank will be 8. If the given row is 7, the rank will be 1.
     */
    private static int convertRowToRank(int row){
        return 8 - row;
    }

    /**
     * Returns the row corresponding to the given String rank
     * @param rank the String chess rank to be converted to a row
     * @return the corresponding row of the given rank. For example, if the given rank
     *  is 1, the row will be 7. If the given rank is 8, the row will be 0.
     */
    private static int convertRankToRow(String rank){
        return 8 - Integer.parseInt(rank);
    }
}