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
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.awt.Image;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.image.BufferedImage;
import edu.kingsu.SoftwareEngineering.Chess.Model.*;

/**
 * ChessGameGUIView class holds the graphical view of the game board.
 * 
 * @author Chelsie Bajic
 * @author Nikolas Haugrud
 */
public class ChessGameGUIView extends ChessGameView {

    private int selectedRow;
    private int selectedCol;
    private JPanel boardHolder = new JPanel();
    private Square squareHolderArray[][];

    /**
     * Constructs the initial graphical representation of the game board (And the
     * panel behind it). Constructs each square and adds them to a 8x8 2D array,
     * then calls the paintBoard() function to insert the contents of board (the 2D
     * array) onto itself for GUI display.
     */
    public ChessGameGUIView() {

        selectedCol = -1;
        selectedRow = -1;

        this.setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, new Color(191, 191, 191)));
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbForThis = new GridBagConstraints();
        gbForThis.fill = GridBagConstraints.CENTER;

        // Instanciates the squares and puts them into the 2D array that holds the
        // represntation of also teaches them their location on the board and determines
        // which color they will be.
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
        this.add(boardHolder, gbForThis);
    }

    /**
     * Over rides JPanel's paintComponent to allow for gradient color.
     * 
     * @param g Graphics object to build gradient on.
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

    public void highlightSquare(int row, int col, Color color) {

    }

    /**
     * paintBoard() paints the current state of the board. (The current version held
     * in the 2D array board).
     */
    public void paintBoard() {
        boardHolder.setLayout(new GridBagLayout());
        GridBagConstraints gbForBoard = new GridBagConstraints();
        gbForBoard.fill = GridBagConstraints.BOTH;
        gbForBoard.gridwidth = 1;
        gbForBoard.gridheight = 1;
        gbForBoard.weightx = 1;
        gbForBoard.weighty = 1;
        boardHolder.setMinimumSize(new Dimension(650, 650));
        boardHolder.setPreferredSize(new Dimension(650, 650));
        boardHolder.setMaximumSize(new Dimension(650, 650));

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                gbForBoard.gridy = i;
                gbForBoard.gridx = j;
                boardHolder.add(squareHolderArray[i][j], gbForBoard);
            }
        }
    }

    /**
     * Updates the 2D array representation of the board (squareHolderArray[][]). The
     * board can then be repainted using paintBoard() to reflect the current state
     * of the game.
     */
    @Override
    public void update() {
        // add boolean arguments for tutorial options on or off.

        int kingSize = 110;
        int queenSize = 90;
        int knightSize = 80;
        int bishopSize = 85;
        int rookSize = 70;
        int pawnSize = 60;

        char[][] pieces = getChessGame().getBoardChars();

        // Lower case == white, upper case == black.
        for (int i = 0; i < pieces.length; i++) {
            for (int j = 0; j < pieces[0].length; j++) {

                switch (pieces[i][j]) {
                case 'P':
                    // Add black pawns.
                    putPieceImageOnSquare("src/assets/piece_images/black_pawn.png", pawnSize, i, j);
                    break;

                case 'p':
                    // Add white pawns.
                    putPieceImageOnSquare("src/assets/piece_images/white_pawn.png", pawnSize, i, j);
                    break;

                case 'K':
                    // Add black king.
                    putPieceImageOnSquare("src/assets/piece_images/black_king.png", kingSize, i, j);
                    break;

                case 'k':
                    // Add white king.
                    putPieceImageOnSquare("src/assets/piece_images/white_king.png", kingSize, i, j);
                    break;

                case 'Q':
                    // Add black queen.
                    putPieceImageOnSquare("src/assets/piece_images/black_queen.png", queenSize, i, j);
                    break;

                case 'q':
                    // Add white queen.
                    putPieceImageOnSquare("src/assets/piece_images/white_queen.png", queenSize, i, j);
                    break;

                case 'R':
                    // Add black rook.
                    putPieceImageOnSquare("src/assets/piece_images/black_rook.png", rookSize, i, j);
                    break;

                case 'r':
                    // Add white rook.
                    putPieceImageOnSquare("src/assets/piece_images/white_rook.png", rookSize, i, j);
                    break;

                case 'B':
                    // Add black bishop.
                    putPieceImageOnSquare("src/assets/piece_images/black_bishop.png", bishopSize, i, j);
                    break;

                case 'b':
                    // Add white bishop.
                    putPieceImageOnSquare("src/assets/piece_images/white_bishop.png", bishopSize, i, j);
                    break;

                case 'N':
                    // Add black knight.
                    putPieceImageOnSquare("src/assets/piece_images/black_knight.png", knightSize, i, j);
                    break;

                case 'n':
                    // Add white knight.
                    putPieceImageOnSquare("src/assets/piece_images/white_knight.png", knightSize, i, j);
                    break;

                case ' ':
                    // Update empty square.
                    this.getSquares(i, j).setIcon(null);
                    break;
                }

            }
        }
    }

    /**
     * Returns the square at the requested coordinate.
     * 
     * @param i x axis location
     * @param j y axis location
     * @return the square at the specified coordinate.
     */
    public Square getSquares(int i, int j) {
        return squareHolderArray[i][j];
    }

    public void putPieceImageOnSquare(String filePath, int size, int i, int j) {
        try {
            BufferedImage bufferedImage = ImageIO.read(new File(filePath));
            Image pieceImage = bufferedImage.getScaledInstance(size, size, Image.SCALE_SMOOTH);
            ImageIcon pieceImageIcon = new ImageIcon(pieceImage);
            this.getSquares(i, j).setIcon(pieceImageIcon);
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    /**
     * Returns the selected row.
     * 
     * @return The selected row.
     */
    public int getSelectedRow() {
        return selectedRow;
    }

    /**
     * Returns the selected column.
     * 
     * @return The selected column.
     */
    public int getSelectedCol() {
        return selectedCol;
    }

    /**
     * Sets the selected row.
     * 
     * @param The row to be the "selected row".
     */
    public void setSelectedRow(int row) {
        selectedRow = row;
    }

    /**
     * Sets the selected column.
     * 
     * @param col The row to be the "selected column".
     */
    public void setSelectedCol(int col) {
        selectedCol = col;
    }

    /**
     * Adds action listeners to the board squares.
     */
    @Override
    public void addListeners() {
        for (int i = 0; i < squareHolderArray.length; i++) {
            for (int j = 0; j < squareHolderArray.length; j++) {
                squareHolderArray[i][j].addActionListener(new ChessGameGUIController(this, getChessGame(), i, j));
            }
        }
    }

}
