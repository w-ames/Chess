package edu.kingsu.SoftwareEngineering.Chess.Model;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import edu.kingsu.SoftwareEngineering.Chess.Model.Moves.*;

public class ChessAI {
    private static int[][] pawnWeights;
    private static int[][] knightWeights;
    private static int[][] bishopWeights;
    private static int[][] rookWeights;
    private static int[][] queenWeights;
    private static int[][] kingWeights;

    public static ChessAIThread bestMove(Board board, int depth, boolean forWhite) {
        return null;
    }

    public static ChessAIThread randomMove(Board board, boolean forWhite) {
        return new ChessAIThread() {
            public Move calculateMove() {
                List<Move> moves = board.getAllMoves(forWhite);
                return moves.get(ThreadLocalRandom.current().nextInt(moves.size()));
            }
        };
    }

    private static Move minimax(Board board, int depth, int a, int b, boolean forWhite) {
        return null;
    }

    private static int scoreBoard(Board board, boolean forWhite) {
        return -1;
    }
}
