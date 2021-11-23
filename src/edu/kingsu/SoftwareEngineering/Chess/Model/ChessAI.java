package edu.kingsu.SoftwareEngineering.Chess.Model;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import edu.kingsu.SoftwareEngineering.Chess.Model.Moves.*;
import edu.kingsu.SoftwareEngineering.Chess.Model.Pieces.*;

/**
 * This class contains methods relating to automatically determining moves
 * with varying levels of artificial intelligence.
 */
public class ChessAI {
    // https://www.freecodecamp.org/news/simple-chess-ai-step-by-step-1d55a9266977/
    private static final int[][] PAWN_WEIGHTS = {
        {  0,  0,  0,  0,  0,  0,  0,  0},
        { 50, 50, 50, 50, 50, 50, 50, 50},
        { 10, 10, 20, 30, 30, 20, 10, 10},
        {  5,  5, 10, 25, 25, 10,  5,  5},
        {  0,  0,  0, 20, 20,  0,  0,  0},
        {  5,- 5,-10,  0,  0,-10,- 5,  5},
        {  5, 10, 10,-20,-20, 10, 10,  5},
        {  0,  0,  0,  0,  0,  0,  0,  0}
    };
    private static final int[][] KNIGHT_WEIGHTS = {
        {-50,-40,-30,-30,-30,-30,-40,-50},
        {-40,-20,  0,  0,  0,  0,-20,-40},
        {-30,  0, 10, 15, 15, 10,  0,-30},
        {-30,  5, 15, 20, 20, 15,  5,-30},
        {-30,  0, 15, 20, 20, 15,  0,-30},
        {-30,  5, 10, 15, 15, 10,  5,-30},
        {-40,-20,  0,  5,  5,  0,-20,-40},
        {-50,-40,-30,-30,-30,-30,-40,-50}
    };
    private static final int[][] BISHOP_WEIGHTS = {
        {-20,-10,-10,-10,-10,-10,-10,-20},
        {-10,  0,  0,  0,  0,  0,  0,-10},
        {-10,  0,  5, 10, 10,  5,  0,-10},
        {-10,  5,  5, 10, 10,  5,  5,-10},
        {-10,  0, 10, 10, 10, 10,  0,-10},
        {-10, 10, 10, 10, 10, 10, 10,-10},
        {-10,  5,  0,  0,  0,  0,  5,-10},
        {-20,-10,-10,-10,-10,-10,-10,-20}
    };
    private static final int[][] ROOK_WEIGHTS = {
        {  0,  0,  0,  0,  0,  0,  0,  0},
        {  5, 10, 10, 10, 10, 10, 10,  5},
        {- 5,  0,  0,  0,  0,  0,  0,- 5},
        {- 5,  0,  0,  0,  0,  0,  0,- 5},
        {- 5,  0,  0,  0,  0,  0,  0,- 5},
        {- 5,  0,  0,  0,  0,  0,  0,- 5},
        {- 5,  0,  0,  0,  0,  0,  0,- 5},
        {  0,  0,  0,  5,  5,  0,  0,  0}
    };
    private static final int[][] QUEEN_WEIGHTS = {
        {-20,-10,-10,- 5,- 5,-10,-10,-20},
        {-10,  0,  0,  0,  0,  0,  0,-10},
        {-10,  0,  5,  5,  5,  5,  0,-10},
        {- 5,  0,  5,  5,  5,  5,  0,- 5},
        {  0,  0,  5,  5,  5,  5,  0,- 5},
        {-10,  5,  5,  5,  5,  5,  0,-10},
        {-10,  0,  5,  0,  0,  0,  0,-10},
        {-20,-10,-10,- 5,- 5,-10,-10,-20}
    };
    private static final int[][] KING_WEIGHTS = {
        {-30,-40,-40,-50,-50,-40,-40,-30},
        {-30,-40,-40,-50,-50,-40,-40,-30},
        {-30,-40,-40,-50,-50,-40,-40,-30},
        {-30,-40,-40,-50,-50,-40,-40,-30},
        {-20,-30,-30,-40,-40,-30,-30,-20},
        {-10,-20,-20,-20,-20,-20,-20,-10},
        { 20, 20,  0,  0,  0,  0, 20, 20},
        { 20, 30, 10,  0,  0, 10, 30, 20}
    };

    private static final int PAWN = 100;
    private static final int KNIGHT = 300;
    private static final int BISHOP = 300;
    private static final int ROOK = 500;
    private static final int QUEEN = 900;
    private static final int KING = 9000;

    /**
     * Calculates the best move on the given board for the given side using the
     * given minimax depth of search.
     * @param board the board on which to search for moves
     * @param depth the depth at which to search the board using minimax
     *  algorithm
     * @param forWhite <code>true</code> if searching for a move for the white
     *  side, <code>false</code> if searching for black
     * @return a thread that when ran will calculate the desired best move
     *  considering the inputs given
     */
    public static ChessAIThread bestMove(Board board, int depth, boolean forWhite) {
        return new ChessAIThread() {
            public Move calculateMove() throws InterruptedException {
                try {
                    return ChessAI.minimaxMove(board, depth, forWhite);
                } catch(NullPointerException e){
                    throw new InterruptedException();
                }
            }
        };
    }

    /**
     * Calculates a random move on the give board for the given side.
     * @param board the board on which to find a random move
     * @param forWhite <code>true</code> if searching for a move for white,
     *  <code>false</code> if searching for a move for black
     * @return a thread that when ran will calculate a random move considering
     *  the inputs given */
    public static ChessAIThread randomMove(Board board, boolean forWhite) {
        return new ChessAIThread() {
            public Move calculateMove() {
                List<Move> moves = board.getAllMoves(forWhite);
                return moves.get(ThreadLocalRandom.current().nextInt(moves.size()));
            }
        };
    }

    /**
     * A minimax algorithm with alpha beta pruning that returns the best
     * move on a board for a side given a depth of search.
     * @param board the board on which to search for a move
     * @param depth the depth at which to search for the move
     * @param forWhite <code>true</code> if searching for a move for white,
     *  <code>false</code> if searching for a move for black
     * @return the best move on the board considering the inputs given
     */
    private static Move minimaxMove(Board board, int depth, boolean forWhite) throws InterruptedException {
        if (Thread.interrupted()) {
            throw new InterruptedException();
        }
        if (depth == 0) {
            return null;
        }
        // if () { // add checkmate consideration return here
        // }
        // white maximizes
        Board workingBoard;
        int value;
        int a = Integer.MIN_VALUE;
        int b = Integer.MAX_VALUE;
        Move mmMove = null;
        if (forWhite) {
            value = Integer.MIN_VALUE;
            for (Move m : board.getAllMoves(forWhite)) {
                workingBoard = new Board(board);
                m.perform(workingBoard);
                int futureResult = minimax(workingBoard, depth-1, a, b, false);
                if (futureResult > value) {
                    value = futureResult;
                    mmMove = m;
                }
                if (value >= b) {
                    break;
                }
                a = Math.max(a, value);
            }
            return mmMove;
        } else {
            value = Integer.MAX_VALUE;
            for (Move m : board.getAllMoves(forWhite)) {
                workingBoard = new Board(board);
                m.perform(workingBoard);
                int futureResult = minimax(workingBoard, depth-1, a, b, true);
                if (futureResult < value) {
                    value = futureResult;
                    mmMove = m;
                }
                if (value <= a) {
                    break;
                }
                b = Math.min(b, value);
            }
            return mmMove;
        }
    }

    /**
     * A minimax algorithm with alpha beta pruning that returns the score of the
     * best move on a board for a side given a depth of search.
     * @param board the board on which to search for a move
     * @param depth the depth at which to search for the move
     * @param a the alpha beta pruning alpha cutoff value
     * @param b the alpha beta pruning beta cutoff value
     * @param forWhite <code>true</code> if searching for a move for white,
     *  <code>false</code> if searching for a move for black
     * @return the score of the best move on the board considering the inputs
     *  given
     */
    private static int minimax(Board board, int depth, int a, int b, boolean forWhite) throws InterruptedException {
        if (Thread.interrupted()) {
            throw new InterruptedException();
        }
        if (depth == 0) {
            return scoreBoard(board, forWhite);
        }
        // if () { // add checkmate consideration return here
        // }
        // white maximizes
        Board workingBoard;
        int value;
        if (forWhite) {
            value = Integer.MIN_VALUE;
            for (Move m : board.getAllMoves(forWhite)) {
                workingBoard = new Board(board);
                m.perform(workingBoard);
                value = Math.max(value, minimax(workingBoard, depth-1, a, b, false));
                if (value >= b) {
                    break;
                }
                a = Math.max(a, value);
            }
            return value;
        } else {
            value = Integer.MAX_VALUE;
            for (Move m : board.getAllMoves(forWhite)) {
                workingBoard = new Board(board);
                m.perform(workingBoard);
                value = Math.min(value, minimax(workingBoard, depth-1, a, b, true));
                if (value <= a) {
                    break;
                }
                b = Math.min(b, value);
            }
            return value;
        }
    }

    /**
     * Calculates a board heuristic value for the state of the game considering
     * the presence of pieces on the board. A large positive value means the
     * board indicates white has the upper hand, and a large negative value
     * means that black has the upper hand.
     * @param board the board to calculate a value for
     * @param forWhite <code>true</code> if the board should be scored from the
     *  perspective of white, <code>false</code> if from the persepective of
     *  black
     * @return an integer heuristic indicating the state of the game, positive
     *  values favoring white, and negative values favoring black
     */
    private static int scoreBoard(Board board, boolean forWhite) {
        int score = 0;
        for (int i=0; i<board.ROWS; i++) {
            for (int j=0; j<board.COLS; j++) {
                Piece p = board.getPiece(i, j);
                if (p != null) {
                    PieceType pt = p.getPieceType();
                    int mult = p.isWhite() ? 1 : -1;
                    int positionValI = mult == 1 ? i : Board.ROWS-1-i;
                    int positionValJ = mult == 1 ? j : Board.COLS-1-j;
                    if (pt == PieceType.PAWN) {
                        score += mult * PAWN + mult * PAWN_WEIGHTS[positionValI][positionValJ];
                    } else if (pt == PieceType.KNIGHT) {
                        score += mult * KNIGHT + mult * KNIGHT_WEIGHTS[positionValI][positionValJ];
                    } else if (pt == PieceType.BISHOP) {
                        score += mult * BISHOP + mult * BISHOP_WEIGHTS[positionValI][positionValJ];
                    } else if (pt == PieceType.ROOK) {
                        score += mult * ROOK + mult * ROOK_WEIGHTS[positionValI][positionValJ];
                    } else if (pt == PieceType.QUEEN) {
                        score += mult * QUEEN + mult * QUEEN_WEIGHTS[positionValI][positionValJ];
                    } else if (pt == PieceType.KING) {
                        score += mult * KING + mult * KING_WEIGHTS[positionValI][positionValJ];
                    }
                }
            }
        }
        return score;
    }

}
