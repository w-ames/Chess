package edu.kingsu.SoftwareEngineering.Chess.GUI;

import java.awt.Color;
import javax.swing.*;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
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

    private int currentFrameHeight;
    private int currentFrameWidth;

    // Pieces for large screen sizes.
    private ImageIcon largeWhitePawnIcon;
    private ImageIcon largeBlackPawnIcon;

    private ImageIcon largeWhiteRookIcon;
    private ImageIcon largeBlackRookIcon;

    private ImageIcon largeWhiteKnightIcon;
    private ImageIcon largeBlackKnightIcon;

    private ImageIcon largeWhiteBishopIcon;
    private ImageIcon largeBlackBishopIcon;

    private ImageIcon largeWhiteQueenIcon;
    private ImageIcon largeBlackQueenIcon;

    private ImageIcon largeWhiteKingIcon;
    private ImageIcon largeBlackKingIcon;

    // Pieces for small screen sizes.
    private ImageIcon smallWhitePawnIcon;
    private ImageIcon smallBlackPawnIcon;

    private ImageIcon smallWhiteRookIcon;
    private ImageIcon smallBlackRookIcon;

    private ImageIcon smallWhiteKnightIcon;
    private ImageIcon smallBlackKnightIcon;

    private ImageIcon smallWhiteBishopIcon;
    private ImageIcon smallBlackBishopIcon;

    private ImageIcon smallWhiteQueenIcon;
    private ImageIcon smallBlackQueenIcon;

    private ImageIcon smallWhiteKingIcon;
    private ImageIcon smallBlackKingIcon;

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
        // represntation of the board. Also teaches them their location on the board and
        // determines which color they will be.
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
        loadPieceIconsIntoIconVariables();
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

        // Allows
        boardHolder.setMinimumSize(new Dimension(350, 350));
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
     * To look at the current size of the application frame
     * 
     * @param container
     */
    public void setContainerSize(int width, int height) {
        this.currentFrameHeight = height;
        this.currentFrameWidth = width;
    }

    /**
     * Updates the 2D array representation of the board (squareHolderArray[][]).
     * Loads small piece image icons for small frame sizes or large piece image
     * icons for large frame sizes.
     *
     */
    @Override
    public void update() {

        char[][] pieces = getChessGame().getBoardChars();

        // For 350 x 350 board size.
        if (currentFrameWidth < 1000 || currentFrameHeight < 1000) {

            // Lower case == white, upper case == black.
            for (int i = 0; i < pieces.length; i++) {
                for (int j = 0; j < pieces[0].length; j++) {

                    switch (pieces[i][j]) {
                    case 'P':
                        // Add black pawns.
                        putPieceImageOnSquare(smallBlackPawnIcon, i, j);
                        break;

                    case 'p':
                        // Add white pawns.
                        putPieceImageOnSquare(smallWhitePawnIcon, i, j);
                        break;

                    case 'K':
                        // Add black king.
                        putPieceImageOnSquare(smallBlackKingIcon, i, j);
                        break;

                    case 'k':
                        // Add white king.
                        putPieceImageOnSquare(smallWhiteKingIcon, i, j);
                        break;

                    case 'Q':
                        // Add black queen.
                        putPieceImageOnSquare(smallBlackQueenIcon, i, j);
                        break;

                    case 'q':
                        // Add white queen.
                        putPieceImageOnSquare(smallWhiteQueenIcon, i, j);
                        break;

                    case 'R':
                        // Add black rook.
                        putPieceImageOnSquare(smallBlackRookIcon, i, j);
                        break;

                    case 'r':
                        // Add white rook.
                        putPieceImageOnSquare(smallWhiteRookIcon, i, j);
                        break;

                    case 'B':
                        // Add black bishop.
                        putPieceImageOnSquare(smallBlackBishopIcon, i, j);
                        break;

                    case 'b':
                        // Add white bishop.
                        putPieceImageOnSquare(smallWhiteBishopIcon, i, j);
                        break;

                    case 'N':
                        // Add black knight.
                        putPieceImageOnSquare(smallBlackKingIcon, i, j);
                        break;

                    case 'n':
                        // Add white knight.
                        putPieceImageOnSquare(smallWhiteKnightIcon, i, j);
                        break;

                    case ' ':
                        // Update empty square.
                        this.getSquares(i, j).setIcon(null);
                        break;
                    }
                }
            }
            // Else if application frame size is larger than 1000 x 1000, load the large
            // pieces for the 650 x 650 board size.
        } else {

            for (int i = 0; i < pieces.length; i++) {
                for (int j = 0; j < pieces[0].length; j++) {

                    switch (pieces[i][j]) {
                    case 'P':
                        // Add black pawns.
                        putPieceImageOnSquare(largeBlackPawnIcon, i, j);
                        break;

                    case 'p':
                        // Add white pawns.
                        putPieceImageOnSquare(largeWhitePawnIcon, i, j);
                        break;

                    case 'K':
                        // Add black king.
                        putPieceImageOnSquare(largeBlackKingIcon, i, j);
                        break;

                    case 'k':
                        // Add white king.
                        putPieceImageOnSquare(largeWhiteKingIcon, i, j);
                        break;

                    case 'Q':
                        // Add black queen.
                        putPieceImageOnSquare(largeBlackQueenIcon, i, j);
                        break;

                    case 'q':
                        // Add white queen.
                        putPieceImageOnSquare(largeWhiteQueenIcon, i, j);
                        break;

                    case 'R':
                        // Add black rook.
                        putPieceImageOnSquare(largeBlackRookIcon, i, j);
                        break;

                    case 'r':
                        // Add white rook.
                        putPieceImageOnSquare(largeWhiteRookIcon, i, j);
                        break;

                    case 'B':
                        // Add black bishop.
                        putPieceImageOnSquare(largeBlackBishopIcon, i, j);
                        break;

                    case 'b':
                        // Add white bishop.
                        putPieceImageOnSquare(largeWhiteBishopIcon, i, j);
                        break;

                    case 'N':
                        // Add black knight.
                        putPieceImageOnSquare(largeBlackKnightIcon, i, j);
                        break;

                    case 'n':
                        // Add white knight.
                        putPieceImageOnSquare(largeWhiteKnightIcon, i, j);
                        break;

                    case ' ':
                        // Update empty square.
                        this.getSquares(i, j).setIcon(null);
                        break;
                    }
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

    /**
     * Puts the image icons of the pieces on to the squares.
     * 
     * @param pieceImageIcon The piece image icon to put on the specified square.
     * @param i              The row of the square.
     * @param j              The column of the square.
     */
    public void putPieceImageOnSquare(ImageIcon pieceImageIcon, int i, int j) {
        this.getSquares(i, j).setIcon(pieceImageIcon);
    }

    /**
     * Load the piece image files into their respective ImageIcon variables.
     */
    public void loadPieceIconsIntoIconVariables() {

        // For 650 x 650 board size.
        int largeKingSize = 110;
        int largeQueenSize = 90;
        int largeKnightSize = 80;
        int largeBishopSize = 85;
        int largeRookSize = 70;
        int largePawnSize = 60;

        // For 350 x 350 board size.
        int smallKingSize = 58;
        int smallQueenSize = 49;
        int smallKnightSize = 40;
        int smallBishopSize = 40;
        int smallRookSize = 35;
        int smallPawnSize = 30;

        // Put the images of the pieces into the image icons.

        // Large sizes.
        largeWhitePawnIcon = openPieceImageFile("src/assets/piece_images/white_pawn.png", largePawnSize);
        largeBlackPawnIcon = openPieceImageFile("src/assets/piece_images/black_pawn.png", largePawnSize);

        largeWhiteRookIcon = openPieceImageFile("src/assets/piece_images/white_rook.png", largeRookSize);
        largeBlackRookIcon = openPieceImageFile("src/assets/piece_images/black_rook.png", largeRookSize);

        largeWhiteKnightIcon = openPieceImageFile("src/assets/piece_images/white_knight.png", largeKnightSize);
        largeBlackKnightIcon = openPieceImageFile("src/assets/piece_images/black_knight.png", largeKnightSize);

        largeWhiteBishopIcon = openPieceImageFile("src/assets/piece_images/white_bishop.png", largeBishopSize);
        largeBlackBishopIcon = openPieceImageFile("src/assets/piece_images/black_bishop.png", largeBishopSize);

        largeWhiteQueenIcon = openPieceImageFile("src/assets/piece_images/white_queen.png", largeQueenSize);
        largeBlackQueenIcon = openPieceImageFile("src/assets/piece_images/black_queen.png", largeQueenSize);

        largeWhiteKingIcon = openPieceImageFile("src/assets/piece_images/white_king.png", largeKingSize);
        largeBlackKingIcon = openPieceImageFile("src/assets/piece_images/black_king.png", largeKingSize);

        // Small sizes.
        smallWhitePawnIcon = openPieceImageFile("src/assets/piece_images/white_pawn.png", smallPawnSize);
        smallBlackPawnIcon = openPieceImageFile("src/assets/piece_images/black_pawn.png", smallPawnSize);

        smallWhiteRookIcon = openPieceImageFile("src/assets/piece_images/white_rook.png", smallRookSize);
        smallBlackRookIcon = openPieceImageFile("src/assets/piece_images/black_rook.png", smallRookSize);

        smallWhiteKnightIcon = openPieceImageFile("src/assets/piece_images/white_knight.png", smallKnightSize);
        smallBlackKnightIcon = openPieceImageFile("src/assets/piece_images/black_knight.png", smallKnightSize);

        smallWhiteBishopIcon = openPieceImageFile("src/assets/piece_images/white_bishop.png", smallBishopSize);
        smallBlackBishopIcon = openPieceImageFile("src/assets/piece_images/black_bishop.png", smallBishopSize);

        smallWhiteQueenIcon = openPieceImageFile("src/assets/piece_images/white_queen.png", smallQueenSize);
        smallBlackQueenIcon = openPieceImageFile("src/assets/piece_images/black_queen.png", smallQueenSize);

        smallWhiteKingIcon = openPieceImageFile("src/assets/piece_images/white_king.png", smallKingSize);
        smallBlackKingIcon = openPieceImageFile("src/assets/piece_images/black_king.png", smallKingSize);

    }

    /**
     * Open the image files containing the representations of the pieces.
     * 
     * @param filePath The file path to the piece image.
     * @param size     The size that the image is to be.
     * @return The ImageIcon representation of the piece.
     */
    public ImageIcon openPieceImageFile(String filePath, int size) {
        try {
            BufferedImage bufferedImage = ImageIO.read(new File(filePath));
            Image pieceImage = bufferedImage.getScaledInstance(size, size, Image.SCALE_SMOOTH);
            ImageIcon pieceImageIcon = new ImageIcon(pieceImage);
            return pieceImageIcon;

        } catch (Exception e) {
            System.err.println(e);
        }
        return smallBlackBishopIcon; // I don't know how to make it compile without doing this?
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
