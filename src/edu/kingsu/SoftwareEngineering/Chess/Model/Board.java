package edu.kingsu.SoftwareEngineering.Chess.Model;

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
    private Piece[][] squares;

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
    }

    public void initializeBoard() {
    }

    public Piece[][] getPieces() {
        return squares;
    }

    public Piece getPiece(int r, int c) {
        return squares[r][c];
    }

    public void setPiece(int r, int c, Piece p) {
        squares[r][c] = p;
    }

    public List<Move> getMoves(int fromRow, int fromCol) {
        return null;
    }

    public List<Move> getAllMoves(boolean forWhite) {
        return null;
    }

    public List<List<Integer>> getAttackers(int toRow, int toCol) {
        return null;
    }

    public boolean getCheck(boolean forWhite) {
        return false;
    }

    public boolean getCheckmate(boolean forWhite) {
        return false;
    }
}
