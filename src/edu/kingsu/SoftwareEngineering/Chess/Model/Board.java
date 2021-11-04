package edu.kingsu.SoftwareEngineering.Chess.Model;

import java.util.ArrayList;
import java.util.List;

import edu.kingsu.SoftwareEngineering.Chess.Model.Moves.*;
import edu.kingsu.SoftwareEngineering.Chess.Model.Pieces.*;

/**
 * This class represents the board of play in a game of
 * Chess, holding a set of Pieces*** as well as logic
 * for calculating their moves in the context of this
 * board.
 */
public class Board {

    private int enPassantableRow;
    private int enPassantableCol;
    private int[] whiteKingSquare;
    private int[] blackKingSquare;
    private Piece[][] squares;

    private static final int[][] pawnOffsets = {
        {1, 0},
        {2, 0},
        {1, -1},
        {1, 1},
    };
    private static final int[][] knightOffsets = {
        {-2, -1},
        {-2, 1},
        {2, -1},
        {2, 1},
        {-1, -2},
        {1, -2},
        {-1, 2},
        {1, 2}
    };
    private static final int[][] kingOffsets = {
        {-1, -1},
        {-1, 0},
        {-1, 1},
        {0, -1},
        {0, 1},
        {1, -1},
        {1, 0},
        {1, 1},
    };

    public static final int ROWS = 8;
    public static final int COLS = 8;
    private static final char[][] initialBoard = {
        {'R','N','B','Q','K','B','N','R'},
        {'P','P','P','P','P','P','P','P'},
        {' ',' ',' ',' ',' ',' ',' ',' '},
        {' ',' ',' ',' ',' ',' ',' ',' '},
        {' ',' ',' ',' ',' ',' ',' ',' '},
        {' ',' ',' ',' ',' ',' ',' ',' '},
        {'p','p','p','p','p','p','p','p'},
        {'r','n','b','q','k','n','n','r'}
    };

    public Board() {
        initializeBoard();
    }

    public Board(Board oBoard) {
        for (int i=0; i<ROWS; i++) {
            for (int j=0; j<COLS; j++) {
                Piece piece = null;
                Piece oPiece = oBoard.squares[i][j];
                if (oPiece != null) {
                    piece = oPiece.copyPiece();
                }
                squares[i][j] = piece;
            }
        }
        enPassantableRow = oBoard.enPassantableRow;
        enPassantableCol = oBoard.enPassantableCol;
    }

    public void initializeBoard() {
        initializeBoard(initialBoard);
    }
    public void initializeBoard(char[][] boardMap) {
        whiteKingSquare = new int[] {-1, -1};
        blackKingSquare = new int[] {-1, -1};
        enPassantableRow = -1;
        enPassantableCol = -1;
        for (int i=0; i<ROWS; i++) {
            for (int j=0; j<COLS; j++) {
                char c = boardMap[i][j];
                char cl = Character.toLowerCase(c);
                boolean whitePiece = c == cl;
                Piece newPiece = null;
                switch (cl) {
                    case 'p':
                        newPiece = new Pawn(whitePiece);
                        break;
                    case 'n':
                        newPiece = new Knight(whitePiece);
                        break;
                    case 'b':
                        newPiece = new Bishop(whitePiece);
                        break;
                    case 'r':
                        newPiece = new Rook(whitePiece);
                        break;
                    case 'q':
                        newPiece = new Queen(whitePiece);
                        break;
                    case 'k':
                        newPiece = new King(whitePiece);
                        if (whitePiece) {
                            whiteKingSquare[0] = i;
                            whiteKingSquare[1] = j;
                        } else {
                            blackKingSquare[0] = i;
                            blackKingSquare[1] = j;
                        }
                        break;
                    default:
                }
                squares[i][j] = newPiece;
            }
        }
    }

    public char[][] getChars() {
        char[][] chars = new char[ROWS][COLS];
        for (int i=0; i<ROWS; i++) {
            for (int j=0; j<COLS; j++) {
                char c = ' ';
                if (squares[i][j] != null) {
                    switch (squares[i][j].getPieceType()) {
                        case PAWN:
                            c = 'p';
                            break;
                        case KNIGHT:
                            c = 'n';
                            break;
                        case BISHOP:
                            c = 'b';
                            break;
                        case ROOK:
                            c = 'r';
                            break;
                        case QUEEN:
                            c = 'q';
                            break;
                        case KING:
                            c = 'k';
                            break;
                    }
                    c = squares[i][j].isWhite() ? c : Character.toUpperCase(c);
                } else {
                    c = ' ';
                }
                chars[i][j] = c;
            }
        }
        return chars;
    }

    @Deprecated
    public Piece[][] getPieces() {
        return squares;
    }

    public Piece getPiece(int r, int c) {
        return squares[r][c];
    }

    public void setPiece(int r, int c, Piece p) {
        if (isOnBoard(r, c)) {
            squares[r][c] = p;
            if (p.getPieceType() == PieceType.KING) {
                updateKingLocation(p.isWhite(), r, c);
            }
        }
    }

    private void updateKingLocation(boolean whiteKing, int r, int c) {
        if (whiteKing) {
            whiteKingSquare[0] = r;
            whiteKingSquare[1] = c;
        } else {
            blackKingSquare[0] = r;
            blackKingSquare[1] = c;
        }
    }

    public boolean getEnPassantable() {
        return enPassantableRow != -1 && enPassantableCol != -1;
    }

    public void setEnPassantable(int r, int c) {
        enPassantableRow = r;
        enPassantableCol = c;
    }
    public void setEnPassantable() {
        enPassantableRow = -1;
        enPassantableCol = -1;
    }

    public List<Move> getMoves(int fromRow, int fromCol) {
        if (!isOnBoard(fromRow, fromCol) || squares[fromRow][fromCol] == null) {
            return null;
        }
        List<Move> movesList = getMovesUnpruned(fromRow, fromCol);
        return pruneMoves(squares[fromRow][fromCol].isWhite(), movesList);
    }

    public List<Move> getMovesUnpruned(int fromRow, int fromCol) {
        if (!isOnBoard(fromRow, fromCol)) {
            return null;
        }
        ArrayList<Move> moveList = new ArrayList<Move>();
        Piece movingPiece = squares[fromRow][fromCol];
        if (movingPiece == null) {
            return moveList;
        }
        boolean movingWhite = movingPiece.isWhite();
        switch (movingPiece.getPieceType()) {
            case PAWN:
                Pawn movingPawn = (Pawn)movingPiece;
                int forwardMult = movingWhite ? -1 : 1;
                for (int[] offset : pawnOffsets) {
                    int toRow = fromRow+forwardMult*offset[0];
                    int toCol = fromCol+forwardMult*offset[1];
                    switch (validPawnMove(fromRow, fromCol, toRow, toCol)) {
                        case NORMAL:
                            moveList.add(new Move(fromRow, fromCol, toRow, toCol));
                            break;
                        case PAWN_DOUBLE:
                            moveList.add(new PawnDoubleMove(fromRow, fromCol, toRow, toCol));
                            break;
                        case PAWN_PROMOTION:
                            moveList.add(new PawnPromotionMove(fromRow, fromCol, toRow, toCol, PieceType.KNIGHT));
                            moveList.add(new PawnPromotionMove(fromRow, fromCol, toRow, toCol, PieceType.BISHOP));
                            moveList.add(new PawnPromotionMove(fromRow, fromCol, toRow, toCol, PieceType.ROOK));
                            moveList.add(new PawnPromotionMove(fromRow, fromCol, toRow, toCol, PieceType.QUEEN));
                            break;
                        case EN_PASSANT:
                            moveList.add(new EnPassantMove(fromRow, fromCol, toRow, toCol, toRow-forwardMult*1, toCol));
                            break;
                        default:
                            break;
                    }
                }
                break;
            case KNIGHT:
                Knight movingKnight = (Knight)movingPiece;
                for (int[] offset : knightOffsets) {
                    int toRow = fromRow+offset[0];
                    int toCol = fromCol+offset[1];
                    if (validDestination(movingWhite, toRow, toCol)) {
                        moveList.add(new Move(fromRow, fromCol, toRow, toCol));
                    }
                }
                break;
            case BISHOP:
                Bishop movingBishop = (Bishop)movingPiece;
                moveList.addAll(createNormalMoveList(fromRow, fromCol, getRaySquares(movingWhite, fromRow, fromCol, 1, -1)));
                moveList.addAll(createNormalMoveList(fromRow, fromCol, getRaySquares(movingWhite, fromRow, fromCol, 1, 1)));
                moveList.addAll(createNormalMoveList(fromRow, fromCol, getRaySquares(movingWhite, fromRow, fromCol, -1, -1)));
                moveList.addAll(createNormalMoveList(fromRow, fromCol, getRaySquares(movingWhite, fromRow, fromCol, -1, 1)));
                break;
            case ROOK:
                moveList.addAll(createNormalMoveList(fromRow, fromCol, getRaySquares(movingWhite, fromRow, fromCol, 1, 0)));
                moveList.addAll(createNormalMoveList(fromRow, fromCol, getRaySquares(movingWhite, fromRow, fromCol, -1, 0)));
                moveList.addAll(createNormalMoveList(fromRow, fromCol, getRaySquares(movingWhite, fromRow, fromCol, 0, -1)));
                moveList.addAll(createNormalMoveList(fromRow, fromCol, getRaySquares(movingWhite, fromRow, fromCol, 0, 1)));
                Rook movingRook = (Rook)movingPiece;
                break;
            case QUEEN:
                Queen movingQueen = (Queen)movingPiece;
                moveList.addAll(createNormalMoveList(fromRow, fromCol, getRaySquares(movingWhite, fromRow, fromCol, 1, -1)));
                moveList.addAll(createNormalMoveList(fromRow, fromCol, getRaySquares(movingWhite, fromRow, fromCol, 1, 0)));
                moveList.addAll(createNormalMoveList(fromRow, fromCol, getRaySquares(movingWhite, fromRow, fromCol, 1, 1)));
                moveList.addAll(createNormalMoveList(fromRow, fromCol, getRaySquares(movingWhite, fromRow, fromCol, -1, -1)));
                moveList.addAll(createNormalMoveList(fromRow, fromCol, getRaySquares(movingWhite, fromRow, fromCol, -1, 0)));
                moveList.addAll(createNormalMoveList(fromRow, fromCol, getRaySquares(movingWhite, fromRow, fromCol, -1, 1)));
                moveList.addAll(createNormalMoveList(fromRow, fromCol, getRaySquares(movingWhite, fromRow, fromCol, 0, -1)));
                moveList.addAll(createNormalMoveList(fromRow, fromCol, getRaySquares(movingWhite, fromRow, fromCol, 0, 1)));
                ;
                break;
            case KING:
                King movingKing = (King)movingPiece;
                for (int[] offset : kingOffsets) {
                    int toRow = fromRow+offset[0];
                    int toCol = fromCol+offset[1];
                    if (validDestination(movingWhite, toRow, toCol)) {
                        moveList.add(new Move(fromRow, fromCol, toRow, toCol));
                    }
                }
                break;
            default:
                break;
        }
        return moveList;
    }

    public List<Move> pruneMoves(boolean forWhite, List<Move> moves) {
        ArrayList<Move> prunedMoves = new ArrayList<Move>();
        Board aCopy;
        for (Move move : moves) {
            aCopy = new Board(this);
            move.perform(aCopy);
            if (!aCopy.getCheck(!forWhite)) {
                // there is not check for the other side if we do this move
                prunedMoves.add(move);
            }
        }
        return prunedMoves;
    }

    public List<Move> getAllMoves(boolean forWhite) {
        ArrayList<Move> allMoves = new ArrayList<Move>();
        for (int i=0; i<ROWS; i++) {
            for (int j=0; j<COLS; j++) {
                if (squares[i][j] != null && squares[i][j].isWhite() == forWhite) {
                    allMoves.addAll(getMoves(i, j));
                }
            }
        }
        return allMoves;
    }

    public List<List<Integer>> getAttackers(boolean blackAttackers, int toRow, int toCol) {
        ArrayList<List<Integer>> attackers = new ArrayList<List<Integer>>();
        // List<Integer> possibleAttacker;
        // possibleAttacker = getRaySquares(blackAttackers, toRow, toCol, 1, 0, true).get(0); // N
        // possibleAttacker = getRaySquares(blackAttackers, toRow, toCol, 1, 1, true).get(0); // NE
        // possibleAttacker = getRaySquares(blackAttackers, toRow, toCol, 0, 1, true).get(0); // E
        // possibleAttacker = getRaySquares(blackAttackers, toRow, toCol, -1, 1, true).get(0); // SE
        // possibleAttacker = getRaySquares(blackAttackers, toRow, toCol, -1, 0, true).get(0); // S
        // possibleAttacker = getRaySquares(blackAttackers, toRow, toCol, -1, -1, true).get(0); // SW
        // possibleAttacker = getRaySquares(blackAttackers, toRow, toCol, 0, -1, true).get(0); // W
        // possibleAttacker = getRaySquares(blackAttackers, toRow, toCol, 1, -1, true).get(0); // NW
        for (int i=0; i<ROWS; i++) {
            for (int j=0; j<COLS; j++) {
                if (squares[i][j] != null && squares[i][j].isWhite() != blackAttackers) {
                    ArrayList<Integer> location = new ArrayList<Integer>();
                    location.add(i);
                    location.add(j);
                    if (squareAttacks(location, toRow, toCol)) {
                        attackers.add(location);
                    }
                }
            }
        }
        return attackers;
    }

    private boolean squareAttacks(List<Integer> fromSquare, int toRow, int toCol) {
        int fromRow = fromSquare.get(0);
        int fromCol = fromSquare.get(1);
        for (Move m : getMovesUnpruned(fromRow, fromCol)) {
            if (m.hasDestination(toRow, toCol)) {
                return true;
            }
        }
        return false;
    }

    public boolean getCheck(boolean forWhite) {
        int[] kingSquare = forWhite ? blackKingSquare : whiteKingSquare;
        return !getAttackers(!forWhite, kingSquare[0], kingSquare[1]).isEmpty();
    }

    public boolean getCheckmate(boolean forWhite) {
        return getAllMoves(!forWhite).isEmpty();
    }

    public boolean isColoredSquare(int r, int c) {
        return (r+c)%2 == 1; // if sum of row and column is odd, it is colored
    }

    private boolean isOnBoard(int r, int c) {
        return 0 <= r && r < ROWS && 0 <= c && c < COLS;
    }

    private List<List<Integer>> getRaySquares(boolean whiteRay, int startRow, int startCol, int rowIncrement, int colIncrement) {
        return getRaySquares(whiteRay, startRow, startCol, rowIncrement, colIncrement, false);
    }
    private List<List<Integer>> getRaySquares(boolean whiteRay, int startRow, int startCol, int rowIncrement, int colIncrement, boolean capturesOnly) {
        ArrayList<List<Integer>> raySquares = new ArrayList<List<Integer>>();
        int curRow = startRow+rowIncrement;
        int curCol = startCol+colIncrement;
        boolean rayBlocked = false;
        Piece rayLocation = null;
        while (!rayBlocked && isOnBoard(curRow, curCol)) { // while the ray is not blocked and is on the board
            rayLocation = squares[curRow][curCol];
            ArrayList<Integer> rayLocationList = new ArrayList<Integer>();
            rayLocationList.add(curRow);
            rayLocationList.add(curCol);
            if (rayLocation == null) { // location is empty
                if (!capturesOnly) {
                    raySquares.add(rayLocationList);
                }
            } else if (rayLocation.isWhite() != whiteRay) { // location has opposite color
                raySquares.add(rayLocationList);
                rayBlocked = true;
            } else { // location has our color
                rayBlocked = true;
            }
            curRow += rowIncrement;
            curCol += colIncrement;
        }
        return raySquares;
    }

    private List<Move> createNormalMoveList(int fromRow, int fromCol, List<List<Integer>> destinations) {
        ArrayList<Move> moveList = new ArrayList<Move>();
        for (List<Integer> dest : destinations) {
            moveList.add(new Move(fromRow, fromCol, dest.get(0), dest.get(1)));
        }
        return moveList;
    }

    private MoveType validPawnMove(int fromRow, int fromCol, int toRow, int toCol) {
        Pawn pawn = (Pawn)squares[fromRow][fromCol];
        if (!isOnBoard(toRow, toCol)) {
            return null;
        }
        int promotionRank;
        if (pawn.isWhite()) {
            promotionRank = 0;
        } else {
            promotionRank = ROWS-1;
        }
        // does not check that 'from' and 'to' are compatible
        if (toCol == promotionRank) { // promotion move
            return MoveType.PAWN_PROMOTION;
        }
        if (fromCol == toCol && squares[toRow][toCol] == null) { // same column moves
            if (Math.abs(fromRow-toRow) == 2) { // double push
                return MoveType.PAWN_DOUBLE;
            } else { // single push
                return MoveType.NORMAL;
            }
        }
        if (Math.abs(fromCol-toCol) == 1 && squares[toRow][toCol] != null && squares[toRow][toCol].isWhite() != pawn.isWhite()) { // diagonal captures
            return MoveType.NORMAL;
        }
        if (toRow == enPassantableRow && toCol == enPassantableCol) { // en passant
            return MoveType.EN_PASSANT;
        }
        return null;
    }

    private boolean validDestination(boolean whiteMoving, int toRow, int toCol) {
        return isOnBoard(toRow, toCol) && (squares[toRow][toCol] == null || squares[toRow][toCol].isWhite() != whiteMoving);
    }

}
