package edu.kingsu.SoftwareEngineering.Chess.Model;

import java.util.List;
import java.util.Map;
import java.util.Timer;

import edu.kingsu.SoftwareEngineering.Chess.Model.Moves.*;

public class ChessGame {
    private Map<String, String> tagPairs;
    private Player whitePlayer;
    private Player blackPlayer;
    private Board board;
    private int playerInterval;
    private int playerIncrement;
    private Player playerTurn;
    private List<Move> moveHistory;
    private List<String> algebraicHistory;
    private int moveNo;
    // private List<ChessGameView> views;

    // Overall game time
    private int interval;
    private Timer timer;

    public ChessGame() {
    }

    public void initialize() {
    }

    public int getPlayerInterval() { return playerInterval; }

    public int getPlayerIncrement() { return playerIncrement; }

    public Map<String, String> tagPairMap() { return tagPairs; }

    public Board getBoard() { return board; }

    public Player getPlayerTurn() { return playerTurn; }

    public List<Move> getMoveHistory() { return moveHistory; }

    public List<String> getAlgebraicHistory() { return algebraicHistory; }

    public GameState getState() { return null; }

    // public void registerView(ChessGameView view) {
    // }

    public void notifyViews() {
    }

    public boolean performMove(Move move) {
        return true;
    }

    public boolean performMove(String movePgn) {
        return true;
    }

    private boolean validateMove(Move move) {
        return false;
    }

    public void start() {
    }

    public void gameOver() {
    }

    private void setInterval() {
    }

    public boolean undo() {
        return true;
    }

    public boolean redo() {
        return true;
    }

    public void resetTimers() {
    }
}
