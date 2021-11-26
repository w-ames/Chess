package edu.kingsu.SoftwareEngineering.Chess.GUI;

import java.awt.Color;
import javax.swing.*;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MoveAction;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.List;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Font;
import java.awt.Insets;
import javax.swing.text.DefaultCaret;
import java.awt.FlowLayout;

/**
 * ChessGameAlgebraicView class holds the graphical view of the game board.
 * 
 * @author Gregory Cal
 */
public class ChessGameAlgebraicView extends ChessGameView {

    private JEditorPane algebraicDisplayPanel = new JEditorPane();
    // private JTextArea algebraicDisplayPanel = new JTextArea();
    private JTextField algebricInputPanel = new JTextField();
    private GridBagConstraints dp = new GridBagConstraints();
    private int counter = 0;
    private int moveCounter = 0;

    /**
     * Draws the algebraic view panel to be added to ChessPanel.
     * 
     */
    public ChessGameAlgebraicView() {
        algebraicDisplayPanel.setLayout(new FlowLayout());
        algebraicDisplayPanel.setContentType("text/html");
        this.setBackground(Color.WHITE);
        this.setOpaque(true);
        this.setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, new Color(191, 191, 191)));

        // Adding algebraicDisplayPanel to the ChessGameAlgebraicView panel.
        this.setLayout(new GridBagLayout());

        algebraicDisplayPanel.setBackground(new Color(232, 232, 232));
        algebraicDisplayPanel.setOpaque(true);
        algebraicDisplayPanel.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, new Color(224, 224, 224)));
        // algebraicDisplayPanel.setFont(new Font("Arial", Font.PLAIN, 40));

        GridBagConstraints gb = new GridBagConstraints();
        gb.fill = GridBagConstraints.BOTH;
        gb.gridy = 0;
        gb.gridx = 0;
        gb.weightx = 1;
        gb.weighty = 0.95;
        gb.gridwidth = 2;
        gb.insets = new Insets(10, 10, 5, 10);
        JScrollPane scrollNotifications = new JScrollPane(algebraicDisplayPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollNotifications.setMaximumSize(new Dimension(10, 100));
        scrollNotifications.setPreferredSize(new Dimension(10, 100));
        scrollNotifications.setMinimumSize(new Dimension(10, 100));
        DefaultCaret caret = (DefaultCaret) algebraicDisplayPanel.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        this.add(scrollNotifications, gb);

        // Adding label to the algraic move input JTextField.
        JLabel algebraicMoveInputLabel = new JLabel("Enter Algebraic Move: ");
        algebraicMoveInputLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        algebraicMoveInputLabel.setForeground(new Color(16, 46, 60));
        gb.gridy = 1;
        gb.gridx = 0;
        gb.weightx = 1;
        gb.weighty = 0.025;
        gb.gridwidth = 1;
        gb.insets = new Insets(5, 5, 1, 7);
        this.add(algebraicMoveInputLabel, gb);

        // Adding algebraicInputPanel to the ChessGameAlgebraicView panel.
        // algebricInputPanel.setMinimumSize(new Dimension(20, 100));
        gb.fill = GridBagConstraints.BOTH;
        gb.gridy = 2;
        gb.gridx = 0;
        gb.weightx = 0.75;
        gb.weighty = 0.025;
        gb.gridwidth = 1;
        gb.insets = new Insets(0, 5, 5, 5);
        this.add(algebricInputPanel, gb);

    }

    /**
     * Overrides the JPanel paint component to allow for gradient paint.
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        int w = getWidth();
        int h = getHeight();
        GradientPaint gp = new GradientPaint(0, 0, new Color(153, 153, 153), 0, h, new Color(242, 242, 242));
        g2d.setPaint(gp);
        g2d.fillRect(0, 0, w, h);
        g2d.dispose();
    }

    /**
     * handles move validation and submits valid moves, or displays to the user that
     * their move was invalid.
     */
    public void submitAlgebraicMove() {
        String userInputMove = algebricInputPanel.getText();
    }

    @Override
    public void update() {
        algebraicDisplayPanel.setText("");// Remove all
        algebraicDisplayPanel.setEditable(false);
        List<String> pgnMoves = getChessGame().getAlgebraicHistory();
        int lastMoveIndex = getChessGame().latestMoveIndex();
        counter = 0;
        moveCounter = 0;
        String moveString = "";
        String unicodeMove = "";
        char moveChar = ' ';
        boolean isWhite = true;
        String test = "";

        moveString += "<style>";
        moveString += "table, {";
        // moveString += "border: 1px solid black;";
        moveString += "border-radius: 10px;";
        moveString += "border-style: groove;";
        moveString += "}";
        moveString += "th, td {";
        moveString += "font-size: 1.1em;";
        moveString += "border-radius: 10px;";
        // moveString += "width: 75px;";
        moveString += "border-color: #404040;";
        moveString += "font-family:Roboto;";
        moveString += "font-color: #404040;";
        moveString += "background-color: #C0C0C0;";
        moveString += "}";
        moveString += "</style> <table width=\"100%\"><tr><th width=\"20%\">#</th><th width =\"40%\">White</th><th width=\"40%\">Black</th></tr>";
        // <font style=\"font-family:\'Roboto\'\" size = \"6\">
        for (int i = 0; i < pgnMoves.size(); i++) {

            if (i % 2 == 0) {
                moveCounter++;
                moveString += "<tr><td width = \"20%\">" + (Integer.toString(moveCounter) + ".</td>");

            }
            if (i == lastMoveIndex) {
                moveString += "<td width = \"40%\"><b>" + addChessUni(pgnMoves.get(i), isWhite) + "</b></td>";
                if (isWhite == true) {
                    isWhite = false;
                } else if (isWhite == false) {
                    isWhite = true;
                }

            } else {
                moveString += "<td width = \"40%\" >" + addChessUni(pgnMoves.get(i), isWhite) + "</td>";

                if (isWhite == true) {
                    isWhite = false;
                } else if (isWhite == false) {
                    isWhite = true;
                }
            }
            if (i % 2 != 0) {
                moveString += ("</tr>");
            }

        }

        moveString += "</font></table>";
        algebraicDisplayPanel.setText(moveString);

    }

    @Override
    public void addListeners() {
        algebricInputPanel.addActionListener(new ChessGameAlgebraicController(this, getChessGame()));
    }

    public String addChessUni(String move, boolean isWhite) {
        String whiteKing = "&#9812;";
        String whiteQueen = "&#9813;";
        String whiteKnight = "&#9816;";
        String whiteRook = "&#9814;";
        String whiteBishop = "&#9815;";
        String whitePawn = "&#9817;";
        String blackKing = "&#9818;";
        String blackQueen = "&#9819;";
        String blackKnight = "&#9822;";
        String blackRook = "&#9820;";
        String blackPawn = "&#9823;";
        String blackBishop = "&#9821;";

        String newmove = "";

        char c = move.charAt(0);

        if (isWhite == true) {
            if (c == 'K') {
                newmove = whiteKing + move.substring(1);
                return newmove;
            } else if (c == 'Q') {
                newmove = whiteQueen + move.substring(1);
                return newmove;
            } else if (c == 'N') {
                newmove = whiteKnight + move.substring(1);
                return newmove;
            } else if (c == 'R') {
                newmove = whiteRook + move.substring(1);
                return newmove;
            } else if (c == 'B') {
                newmove = whiteBishop + move.substring(1);
                return newmove;
            } else if (c == 'O') {
                return whiteKing + move;
            } else
                return whitePawn + move;
        } else if (isWhite == false) {
            if (c == 'K') {
                newmove = blackKing + move.substring(1);
                return newmove;
            } else if (c == 'Q') {
                newmove = blackQueen + move.substring(1);
                return newmove;
            } else if (c == 'N') {
                newmove = blackKnight + move.substring(1);
                return newmove;
            } else if (c == 'R') {
                newmove = blackRook + move.substring(1);
                return newmove;
            } else if (c == 'B') {
                newmove = blackBishop + move.substring(1);
                return newmove;
            } else if (c == 'O') {
                return blackKing + move;
            } else
                return blackPawn + move;
        }
        return newmove + "error, please look at the source code and fix me";
    }

}
