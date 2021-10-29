package edu.kingsu.SoftwareEngineering.Chess.GUI;

import java.awt.Color;
import javax.swing.*;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;

public class ChessGameGUIView extends ChessGameView {

    private int selectedRow;
    private int selectedCol;
    private JPanel board = new JPanel();
    private Square squareHolderArray[][];

    public ChessGameGUIView() {

        this.setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, new Color(191, 191, 191)));
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbForThis = new GridBagConstraints();
        gbForThis.fill = GridBagConstraints.CENTER;

        // This puts the board squares into the 2D array that holds the represntation of
        // the board along with teaching them their location.
        String location;
        squareHolderArray = new Square[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (((i + j) % 2 == 0)) {
                    location = String.valueOf(i) + ",";
                    location += String.valueOf(j);
                    squareHolderArray[i][j] = new Square(location, true);
                } else {
                    location = String.valueOf(i) + ",";
                    location += String.valueOf(j);
                    squareHolderArray[i][j] = new Square(location, false);
                }
            }
        }
        paintBoard();
        this.add(board, gbForThis);
    }

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

    public void highlightSquare(int row, int col, Color color) {

    }

    public void update() {

    }

    // public addController(ChessGameGUIController gameController){}

    public void paintBoard() {

        // board.setBorder(BorderFactory.createMatteBorder(10, 10, 10, 10, new Color(37,
        // 108, 141)));
        board.setLayout(new GridBagLayout());
        GridBagConstraints gbForBoard = new GridBagConstraints();
        gbForBoard.fill = GridBagConstraints.BOTH;
        gbForBoard.gridwidth = 1;
        gbForBoard.gridheight = 1;
        gbForBoard.weightx = 1;
        gbForBoard.weighty = 1;
        board.setMinimumSize(new Dimension(400, 400));
        board.setPreferredSize(new Dimension(650, 650));
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                gbForBoard.gridy = i;
                gbForBoard.gridx = j;
                board.add(squareHolderArray[i][j], gbForBoard);
            }
        }
    }
}