package edu.kingsu.SoftwareEngineering.Chess.Model;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import edu.kingsu.SoftwareEngineering.Chess.Model.Moves.*;
import edu.kingsu.SoftwareEngineering.Chess.Model.Pieces.*;

public class ChessAI {
    // https://www.freecodecamp.org/news/simple-chess-ai-step-by-step-1d55a9266977/
    private static final int[][] PAWN_WEIGHTS = {
        {}
    };
    private static final int[][] KNIGHT_WEIGHTS = {
        {}
    };
    private static final int[][] BISHOP_WEIGHTS = {
        {}
    };
    private static final int[][] ROOK_WEIGHTS = {
        {}
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

    public static ChessAIThread bestMove(Board board, int depth, boolean forWhite) {
        return new ChessAIThread() {
            public Move calculateMove() {
                return ChessAI.minimaxMove(board, depth, forWhite);
            }
        };
    }

    public static ChessAIThread randomMove(Board board, boolean forWhite) {
        return new ChessAIThread() {
            public Move calculateMove() {
                List<Move> moves = board.getAllMoves(forWhite);
                return moves.get(ThreadLocalRandom.current().nextInt(moves.size()));
            }
        };
    }

    private static Move minimaxMove(Board board, int depth, boolean forWhite) {
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

    private static int minimax(Board board, int depth, int a, int b, boolean forWhite) {
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

    private static int scoreBoard(Board board, boolean forWhite) {
        int score = 0;
        for (int i=0; i<board.ROWS; i++) {
            for (int j=0; j<board.COLS; j++) {
                Piece p = board.getPiece(i, j);
                if (p != null) {
                    PieceType pt = p.getPieceType();
                    int mult = p.isWhite() ? 1 : -1;
                    if (pt == PieceType.PAWN) {
                        score += mult * PAWN;
                    } else if (pt == PieceType.KNIGHT) {
                        score += mult * KNIGHT;
                    } else if (pt == PieceType.BISHOP) {
                        score += mult * BISHOP;
                    } else if (pt == PieceType.ROOK) {
                        score += mult * ROOK;
                    } else if (pt == PieceType.QUEEN) {
                        score += mult * QUEEN;
                    } else if (pt == PieceType.KING) {
                        score += mult * KING;
                    }
                }
            }
        }
        return score;
    }

}
