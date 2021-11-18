package edu.kingsu.SoftwareEngineering.Chess.Model;

import java.util.ArrayList;
import java.util.List;

import edu.kingsu.SoftwareEngineering.Chess.Model.Moves.*;
import edu.kingsu.SoftwareEngineering.Chess.Model.Pieces.*;

/**
 * This class represents the board of play in a game of
 * Chess, holding a set of pieces as well as logic
 * for calculating their moves in the context of this
 * board. Pieces are stored in a two dimensional array, and so
 * pieces are refered to by row and column number in the array,
 * with the first two rows initially storing the black pieces,
 * and the last two rows initially storing the white pieces.
 * @See Piece
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

    /**
     * The number of rows on the chess board
     */
    public static final int ROWS = 8;
    /**
     * The number of columns on the chess board
     */
    public static final int COLS = 8;
    private static final char[][] initialBoard = {
        {'R','N','B','Q','K','B','N','R'},
        {'P','P','P','P','P','P','P','P'},
        {' ',' ',' ',' ',' ',' ',' ',' '},
        {' ',' ',' ',' ',' ',' ',' ',' '},
        {' ',' ',' ',' ',' ',' ',' ',' '},
        {' ',' ',' ',' ',' ',' ',' ',' '},
        {'p','p','p','p','p','p','p','p'},
        {'r','n','b','q','k','b','n','r'}
    };

    /**
     * Creates and initializes a new board instance.
     */
    public Board() {
        initializeBoard();
    }

    /**
     * Creates and initializes a new board instance to copy the given board
     * instance.
     * @param oBoard the board for which the newly constructed instance will
     *  be a deep-copy of
     */
    public Board(Board oBoard) {
        squares = new Piece[ROWS][COLS];
        whiteKingSquare = new int[] {-1, -1};
        blackKingSquare = new int[] {-1, -1};
        enPassantableRow = -1;
        enPassantableCol = -1;
        for (int i=0; i<ROWS; i++) {
            for (int j=0; j<COLS; j++) {
                Piece piece = null;
                Piece oPiece = oBoard.squares[i][j];
                if (oPiece != null) {
                    piece = oPiece.copyPiece();
                }
                setPiece(i, j, piece);
            }
        }
        enPassantableRow = oBoard.enPassantableRow;
        enPassantableCol = oBoard.enPassantableCol;
    }

    /**
     * Initializes the board with start-of-game chess piece placement.
     */
    public void initializeBoard() {
        initializeBoard(initialBoard);
    }
    /**
     * Initializes the board with chess piece placement mapped according
     * to the given character array. Pieces in their standard starting position
     * will be assumed to still be able to perform their special moves
     * (double-moves for pawns on the second and seventh rank for the correct
     * color, and kings &amp; rooks in their standard starting locations).
     * @param boardMap a two-dimensional array with {@value ROWS} rows
     *  and {@value COLS} columns, in which the lowercase characters
     *  p, n, b, r, q, k, will map to a white pawn, knight, bishop, rook,
     *  queen, and king at the corresponding array position on the board.
     *  The uppercase counterparts P, N, B, R, Q, and K will similarly map
     *  to black pieces. Unknown characters are mapped to blank squares.
     */
    public void initializeBoard(char[][] boardMap) {
        // Assumes special moves available if in initial position
        squares = new Piece[ROWS][COLS];
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
                        Pawn newPawn = new Pawn(whitePiece);
                        if (!(whitePiece && i==ROWS-2 || !whitePiece && i==1)) {
                            newPawn.doneDoubleMove();
                        }
                        newPiece = newPawn;
                        break;
                    case 'n':
                        newPiece = new Knight(whitePiece);
                        break;
                    case 'b':
                        newPiece = new Bishop(whitePiece);
                        break;
                    case 'r':
                        Rook newRook = new Rook(whitePiece);
                        if (!((j==0 || j==COLS-1) && (whitePiece && i==ROWS-1 || !whitePiece && i==0))) {
                            newRook.doneCastling();
                        }
                        newPiece = newRook;
                        break;
                    case 'q':
                        newPiece = new Queen(whitePiece);
                        break;
                    case 'k':
                        King newKing = new King(whitePiece);
                        if (!((j==4) && (whitePiece && i==ROWS-1 || !whitePiece && i==0 ))) {
                            newKing.doneCastling();
                        }
                        newPiece = newKing;
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

    /**
     * Returns a character array signifying the placement of all chess pieces
     * on the board, with uppercase characters being black and lowercase
     * characters being white. p, n, b, r, q, and k map to pawns, knights,
     * bishops, rooks, queens, and kings respectively, and empty spaces are
     * signified by the space character.
     */
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

    /**
     * Returns a piece at a given location on the board.
     * @param r the row at which to find the desired board square of inquiry
     * @param c the column at which to find the desired board square of inquiry
     * @return the {@link Piece} instance that is at the given location on the
     *  board. <code>null</code> is returned if the given location is invalid,
     *  or the location is empty.
     */
    public Piece getPiece(int r, int c) {
        if (isOnBoard(r, c)) {
            return squares[r][c];
        }
        return null;
    }

    /**
     * Sets the piece held at a certain location on the board.
     * @param r the row of the board at which to set a piece
     * @param c the column of the board at which to set a piece
     * @param p the piece to place on the board at the desired location
     */
    public void setPiece(int r, int c, Piece p) {
        if (isOnBoard(r, c)) {
            squares[r][c] = p;
            if (p != null && p.getPieceType() == PieceType.KING) {
                updateKingLocation(p.isWhite(), r, c);
            }
        }
    }

    /**
     * Updates the location of the given king on this board.
     * @param whiteKing a boolean that is <code>true</code> if the position of
     *  the white king is being updated, and <code>false</code> if the position
     *  of the black king is being updated.
     * @param r the new row position of the given king
     * @param c the new column position of the given king
     */
    private void updateKingLocation(boolean whiteKing, int r, int c) {
        if (whiteKing) {
            whiteKingSquare[0] = r;
            whiteKingSquare[1] = c;
        } else {
            blackKingSquare[0] = r;
            blackKingSquare[1] = c;
        }
    }

    /**
     * Returns a boolean signifying whether there is an enpassantable location
     * on this board.
     * @return <code>true</code> if there is an square currently avalailable
     *  for an en passant move, and <code>false</code> otherwise
     * @see setEnPassantable
     */
    public boolean getEnPassantable() {
        return enPassantableRow != -1 && enPassantableCol != -1;
    }

    /**
     * Sets the given location as being available for a pawn to diagonally
     * able to be moved to for an en passant move despite there not being
     * an actual piece currently at the given location.
     * @param r the row to which a pawn will be able to en passant
     * @param c the column to which a pawn will be able to en passant
     * @see getEnPassantable
     */
    public void setEnPassantable(int r, int c) {
        enPassantableRow = r;
        enPassantableCol = c;
    }
    /**
     * Resets the en passant-ability of the board such that no en passant
     * moves will be possible.
     * @see getEnPassantable
     */
    public void setEnPassantable() {
        enPassantableRow = -1;
        enPassantableCol = -1;
    }

    /**
     * Returns the full list of moves possible on this board in which the
     * starting location is the given row and column.
     * @param fromRow the row from which moves in the list of originate
     * @param fromCol the column from which moves in the list of originate
     * @return a list of {@link Move} instances which may be performed on this
     *  board, all having input originating row and column
     */
    public List<Move> getMoves(int fromRow, int fromCol) {
        if (!isOnBoard(fromRow, fromCol) || squares[fromRow][fromCol] == null) {
            // spots off the board and empty spots have no moves
            return new ArrayList<Move>();
        }
        // all moves that could potentially work, might lead to self-check
        List<Move> movesList = getMovesUnpruned(fromRow, fromCol);
        // all moves that actually work
        return pruneMoves(squares[fromRow][fromCol].isWhite(), movesList);
    }

    /**
     * Returns the full list of moves possible from the given location,
     * without pruning of moves that put the moving side (white or black)
     * in check/checkmate.
     * @param fromRow the row from which all the unpruned moves calculated
     *  will originate from
     * @param fromCol the column from which all the unpruned moves calculated
     *  will originate from
     * @return a list of all moves starting from the given location, including
     *  moves that self-check
     */
    private List<Move> getMovesUnpruned(int fromRow, int fromCol) {
        if (!isOnBoard(fromRow, fromCol)) {
            // handle starting location off the board
            return null;
        }
        ArrayList<Move> moveList = new ArrayList<Move>();
        Piece movingPiece = squares[fromRow][fromCol];
        if (movingPiece == null) {
            // handle starting location with no piece
            return moveList;
        }
        boolean movingWhite = movingPiece.isWhite();
        switch (movingPiece.getPieceType()) {
            case PAWN:
                Pawn movingPawn = (Pawn)movingPiece;
                int forwardMult = movingWhite ? -1 : 1;
                for (int[] offset : pawnOffsets) {
                    int toRow = fromRow+forwardMult*offset[0];
                    int toCol = fromCol+offset[1];
                    MoveType pawnMoveType = validPawnMove(fromRow, fromCol, toRow, toCol);
                    if (pawnMoveType != null) {
                        switch (pawnMoveType) {
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
                Rook movingRook = (Rook)movingPiece;
                moveList.addAll(createNormalMoveList(fromRow, fromCol, getRaySquares(movingWhite, fromRow, fromCol, 1, 0)));
                moveList.addAll(createNormalMoveList(fromRow, fromCol, getRaySquares(movingWhite, fromRow, fromCol, -1, 0)));
                moveList.addAll(createNormalMoveList(fromRow, fromCol, getRaySquares(movingWhite, fromRow, fromCol, 0, -1)));
                moveList.addAll(createNormalMoveList(fromRow, fromCol, getRaySquares(movingWhite, fromRow, fromCol, 0, 1)));
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
                boolean castleLeft = false;
                boolean castleRight = false;
                for (int[] offset : kingOffsets) {
                    int toRow = fromRow+offset[0];
                    int toCol = fromCol+offset[1];
                    if (validDestination(movingWhite, toRow, toCol)) {
                        moveList.add(new Move(fromRow, fromCol, toRow, toCol));
                        if (!movingKing.isDoneCastling() && toRow==fromRow) {
                            if (toCol-fromCol==-1) {
                                castleLeft = true;
                            } else if (toCol-fromCol==1) {
                                castleRight = true;
                            }
                        }
                    }
                }
                if (castleLeft && isOnBoard(fromRow, fromCol-2) && squares[fromRow][fromCol-2] == null) {
                    Piece lRook = squares[fromRow][0];
                    if (lRook!=null && lRook.getPieceType()==PieceType.ROOK && !((Rook)lRook).isDoneCastling() && squares[fromRow][1]==null) {
                        moveList.add(new CastlingMove(fromRow, fromCol, fromRow, fromCol-2, fromRow, 0, fromRow, fromCol-1));
                    }
                }
                if (castleRight && isOnBoard(fromRow, fromCol+2) && squares[fromRow][fromCol+2] == null) {
                    Piece rRook = squares[fromRow][COLS-1];
                    if (rRook!=null && rRook.getPieceType()==PieceType.ROOK && !((Rook)rRook).isDoneCastling()) {
                        moveList.add(new CastlingMove(fromRow, fromCol, fromRow, fromCol+2, fromRow, COLS-1, fromRow, fromCol+1));
                    }
                }
                break;
            default:
                break;
        }
        // System.err.println("Moves for piece "+movingPiece.getPieceType().name()+" at ["+fromRow+","+fromCol+"] : "+moveList.size());
        return moveList;
    }

    /**
     * Returns the given list of moves, but with moves resulting in self-check
     * for the given side (white or black) being pruned out.
     * @param forWhite <code>true</code> if the input list of moves is for the
     *  white side, and <code>false</code> if the input list of moves is for the
     *  black side
     * @param moves the list of moves which should be pruned to remove
     *  self-checks
     * @return the pruned-version of the input list of moves such that no
     *  move has a self-check for the given side
     */
    private List<Move> pruneMoves(boolean forWhite, List<Move> moves) {
        ArrayList<Move> prunedMoves = new ArrayList<Move>();
        ArrayList<Move> extraCastleChecks = new ArrayList<Move>();
        Board aCopy;
        for (Move move : moves) {
            aCopy = new Board(this);
            move.perform(aCopy);
            if (!aCopy.getCheck(!forWhite)) {
                // there is not check for the other side if we do this move
                if (move.getType() == MoveType.CASTLING) {
                    extraCastleChecks.add(move);
                } else {
                    prunedMoves.add(move);
                }
            }
        }
        for (Move castleMove : extraCastleChecks) {
            if (prunedMoves.contains(new Move(castleMove.getRowFrom(), castleMove.getColFrom(), castleMove.getRowTo(), (castleMove.getColFrom()+castleMove.getColTo())/2))) {
                prunedMoves.add(castleMove);
            }
        }
        return prunedMoves;
    }

    /**
     * Retrieves a list of all possible moves on the board for the given side
     * (white or black).
     * @param forWhite <code>true</code> if all moves are to be calculated for
     *  the white side, and <code>false</code> if all moves are to be
     *  calculated for the black side
     * @return the full list of all moves possible on the board for the given
     *  side
     */
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

    /**
     * Retrives a list of 2-lists containing the row and column of all locations
     * on the board where the given side has a piece positioned to attack the
     * input location.
     * @param blackAttackers <code>true</code> if attackers to the input position
     *  should be black, and <code>false</code> if attackers to the input
     *  position should be white
     * @param toRow the input position row that is attacked by locations in the
     *  attacker list
     * @param toCol the input position column that is attacked by locations in
     *  the attacker list
     * @return a list containing 2-lists of integers (lists with two integers
     *  in them) in which the first element of a 2-list is a row location of
     *  an attacking piece, and the second element is the column location.
     *  All attacker locations contain a piece of the given side (white or
     *  black)
     */
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

    /**
     * Returns a boolean indicating whether the given location is attacked
     * by the given square. Pinned pieces are still treated as having their
     * full range of moves for the purposes of this method.
     * @param fromSquare an integer 2-list in which the first element is the
     *  board row and the second element is the board column of potential
     *  attacker square
     * @param toRow the row to which potential attackers are checked whether
     *  they can attack using their normal range of moving ability
     * @param toCol the column to which potential attackers are checked whether
     *  they can attack using their normal range of moving ability
     * @return <code>true</code> if the given square 2-list is a location with
     *  a piece that can attack the location given
     */
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

    /**
     * Returns a boolean indicating the check status for the given side
     * (white or black).
     * @param forWhite <code>true</code> if the board is to be queried for
     *  check that is in favor of white, <code>false</code> if in favor of
     *  black
     * @return <code>true</code> if the board has a check status that is in
     *  favor of the given side
     */
    public boolean getCheck(boolean forWhite) {
        int[] kingSquare = forWhite ? blackKingSquare : whiteKingSquare;
        return !getAttackers(!forWhite, kingSquare[0], kingSquare[1]).isEmpty();
    }

    /**
     * Returns a boolean indicating the checkmate status for the given side
     * (white or black).
     * @param forWhite <code>true</code> if the board is to be queried for
     *  checkmate that is in favor of white, <code>false</code> if in favor of
     *  black
     * @return <code>true</code> if the board has a checkmate status that is in
     *  favor of the given side
     */
    public boolean getCheckmate(boolean forWhite) {
        return getCheck(forWhite) && getAllMoves(!forWhite).isEmpty();
    }

    /**
     * Returns a boolean indicating whether a give board location should have
     * a colored square.
     * @param r the row of the queried location
     * @param c the column of the queried location
     * @return <code>true</code> if the board location should be colored,
     *  <code>false</code> otherwise
     */
    public boolean isColoredSquare(int r, int c) {
        return (r+c)%2 != 0; // if sum of row and column is odd, it is colored
    }

    /**
     * Returns a boolean indicating whether a give location is on the board.
     * @param r the row of the queried location
     * @param c the column of the queried location
     * @return <code>true</code> if the given location is on the board,
     *  <code>false</code> otherwise
     */
    private boolean isOnBoard(int r, int c) {
        return 0 <= r && r < ROWS && 0 <= c && c < COLS;
    }

    /**
     * Returns a list of integer 2-lists (first element row, second column)
     * for potentially valid move locations starting from the given location
     * and travelling with the given increment.
     * @param whiteRay <code>true</code> if the ray of moves is for the white
     *  side, <code>false</code> if black
     * @param startRow the row from which the ray of moves begins
     * @param startCol the column from which the ray of moves begins
     * @param rowIncrement the value by which to increment the row in order to
     *  get the next position in the ray
     * @param colIncrement the value by which to increment the column in order to
     *  get the next position in the ray
     * @return a list of integer 2-lists for all the potentially valid move
     *  locations in the given ray direction for the given side
     */
    private List<List<Integer>> getRaySquares(boolean whiteRay, int startRow, int startCol, int rowIncrement, int colIncrement) {
        return getRaySquares(whiteRay, startRow, startCol, rowIncrement, colIncrement, false);
    }
    /**
     * Returns a list of integer 2-lists (first element row, second column)
     * for potentially valid move locations starting from the given location
     * and travelling with the given increment.
     * @param whiteRay <code>true</code> if the ray of moves is for the white
     *  side, <code>false</code> if black
     * @param startRow the row from which the ray of moves begins
     * @param startCol the column from which the ray of moves begins
     * @param rowIncrement the value by which to increment the row in order to
     *  get the next position in the ray
     * @param colIncrement the value by which to increment the column in order to
     *  get the next position in the ray
     * @param capturesOnly <code>true</code> if only a ray move location that
     *  is a capture should be included in the return list, <code>false</code>
     *  otherwise
     * @return a list of integer 2-lists for all the potentially valid move
     *  locations in the given ray direction for the given side
     */
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

    /**
     * Creates a list of moves from the given location to all of the given
     * destination locations in the given list.
     * @param fromRow the row from which the ray moves will originate
     * @param fromCol the column from which the ray moves will originate
     * @param destinations a list of integer 2-lists (first element destination
     *  row, second element destination column) that is the list of all
     *  destinations used in moves in the return list
     * @return a list of moves from the input location to all of the target
     *  destinations
     */
    private List<Move> createNormalMoveList(int fromRow, int fromCol, List<List<Integer>> destinations) {
        ArrayList<Move> moveList = new ArrayList<Move>();
        for (List<Integer> dest : destinations) {
            moveList.add(new Move(fromRow, fromCol, dest.get(0), dest.get(1)));
        }
        return moveList;
    }

    /**
     * Returns the move type for a pawn moving from a given location to another
     * location.
     * @param fromRow the row from which the pawn moves
     * @param fromCol the column from which the pawn moves
     * @param toRow the row to which the pawn moves
     * @param toCol the column to which the pawn moves
     * @return The move type matching the proposed pawn move from a given
     *  location to another give location
     */
    private MoveType validPawnMove(int fromRow, int fromCol, int toRow, int toCol) {
        if (!isOnBoard(toRow, toCol)) {
            return null;
        }
        // System.err.println("from: "+fromRow+","+fromCol+" to: "+toRow+","+toCol);
        Pawn pawn = (Pawn)squares[fromRow][fromCol];
        int promotionRank;
        if (pawn.isWhite()) {
            promotionRank = 0;
        } else {
            promotionRank = ROWS-1;
        }
        // does not check that 'from' and 'to' are compatible
        // if (toRow == promotionRank) { // promotion move
        //     return MoveType.PAWN_PROMOTION;
        // }
        if (fromCol == toCol && squares[toRow][toCol] == null) { // same column moves
            if (Math.abs(fromRow-toRow) == 2) { // double push
                if (pawn.isDoneDoubleMove() || squares[(fromRow+toRow)/2][toCol] != null) {
                    return null;
                } else {
                    return MoveType.PAWN_DOUBLE;
                }
            } else { // single push
                if (toRow != promotionRank) {
                    return MoveType.NORMAL;
                } else {
                    return MoveType.PAWN_PROMOTION;
                }
            }
        }
        if (Math.abs(fromCol-toCol) == 1 && squares[toRow][toCol] != null && squares[toRow][toCol].isWhite() != pawn.isWhite()) { // diagonal captures
            if (toRow != promotionRank) {
                return MoveType.NORMAL;
            } else {
                return MoveType.PAWN_PROMOTION;
            }
        }
        if (Math.abs(fromCol-toCol) == 1 && toRow == enPassantableRow && toCol == enPassantableCol) { // en passant
            return MoveType.EN_PASSANT;
        }
        return null;
    }

    /**
     * Returns a boolean indicating whether the given destination is valid for
     * the given side (white or black).
     * @param whiteMoving <code>true</code> if the querying side is white (as
     *  black-owned tiles can then be captured, and <code>false</code> if the
     *  querying side is black
     * @param toRow the row to be queried whether it is a valid destination
     * @param toCol the column to be queried whether it is a valid destination
     * @return <code>true</code> if the given tile is a valid destination for
     *  the given side
     */
    private boolean validDestination(boolean whiteMoving, int toRow, int toCol) {
        return isOnBoard(toRow, toCol) && (squares[toRow][toCol] == null || squares[toRow][toCol].isWhite() != whiteMoving);
    }

}
