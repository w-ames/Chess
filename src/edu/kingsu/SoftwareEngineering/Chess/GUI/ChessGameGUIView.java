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
import java.awt.Font;
import java.awt.image.BufferedImage;
import edu.kingsu.SoftwareEngineering.Chess.Model.*;

/**
 * ChessGameGUIView class holds the graphical view of the game board.
 * 
 * @author Chelsie Bajic
 * @author Nikolas Haugrud
 * @since 10/2021
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
     * Puts the squares on the graphical representation of the chess board. Loads
     * the chess piece icons into their respective variables. Paints the inital
     * chess board with pieces in the appropriate places. builds and adds the rank
     * and file labeling around the board.
     */
    public ChessGameGUIView() {

        selectedCol = -1;
        selectedRow = -1;

        putSquaresOnBoard();
        loadPieceIconsIntoIconVariables();
        paintBoard();
        buildRankAndFileBorder();

    }

    /**
     * Instanciates the squares and puts them into the 2D array that holds the
     * represntation of the board. Also teaches them their location on the board and
     * determines which color they will be.
     */
    public void putSquaresOnBoard() {

        String location;
        squareHolderArray = new Square[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (((i + j) % 2 == 0)) {

                    // Add white squares.

                    location = translateNumberLocationToFileLetter(j);
                    location += translateNumbericalCoordToAlgebraic(i);
                    Square newSquare = new Square(location, true);

                    // For 900 x 900 board size.
                    if (currentFrameWidth > 1750 || currentFrameHeight > 1100) {

                        newSquare.setMinimumSize(new Dimension(80, 80));
                        newSquare.setPreferredSize(new Dimension(80, 80));
                        newSquare.setMaximumSize(new Dimension(80, 80));

                    } else { // For 360 x 360 board size

                        newSquare.setMinimumSize(new Dimension(45, 45));
                        newSquare.setPreferredSize(new Dimension(45, 45));
                        newSquare.setMaximumSize(new Dimension(45, 45));
                    }

                    squareHolderArray[i][j] = newSquare;

                } else { // Add black squares.

                    location = translateNumberLocationToFileLetter(j);
                    location += translateNumbericalCoordToAlgebraic(i);
                    Square newSquare = new Square(location, false);

                    // For 900 x 900 board size.
                    if (currentFrameWidth > 1750 || currentFrameHeight > 1100) {

                        newSquare.setMinimumSize(new Dimension(80, 80));
                        newSquare.setPreferredSize(new Dimension(80, 80));
                        newSquare.setMaximumSize(new Dimension(80, 80));

                    } else { // For 360 x 360 board size

                        newSquare.setMinimumSize(new Dimension(45, 45));
                        newSquare.setPreferredSize(new Dimension(45, 45));
                        newSquare.setMaximumSize(new Dimension(45, 45));
                    }

                    squareHolderArray[i][j] = newSquare;
                }
            }
        }
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
     * Paints the current state of the board. (The current version held
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

        // Dynamic board resizing when frame is resized by user.
        if (currentFrameWidth > 1750 || currentFrameHeight > 1100) {
            boardHolder.setMinimumSize(new Dimension(900, 900));
            boardHolder.setPreferredSize(new Dimension(900, 900));
            boardHolder.setMaximumSize(new Dimension(900, 900));
        } else {
            boardHolder.setMinimumSize(new Dimension(440, 440));
            boardHolder.setPreferredSize(new Dimension(440, 440));
            boardHolder.setMaximumSize(new Dimension(440, 440));
        }

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                gbForBoard.gridy = i;
                gbForBoard.gridx = j;
                boardHolder.add(squareHolderArray[i][j], gbForBoard);
            }
        }
    }

    /**
     * To look at the current size of the application frame, used for board resizing.
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

        // For 900 x 900 board size.
        if (currentFrameWidth > 1750 || currentFrameHeight > 1100) {

            paintBoard(); // Makes sure board is correct size for current frame.

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

            // Else if application frame size is smaller than 1750 x 1100, load the large
            // pieces for the 360 x 360 board size.
        } else {

            paintBoard(); // Makes sure board is correct size for current frame.

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
                        putPieceImageOnSquare(smallBlackKnightIcon, i, j);
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

        // For 900 x 900 board size.
        int largeKingSize = 120;
        int largeQueenSize = 100;
        int largeKnightSize = 90;
        int largeBishopSize = 95;
        int largeRookSize = 80;
        int largePawnSize = 70;

        // For 440 x 440 board size.
        int smallKingSize = 68;
        int smallQueenSize = 59;
        int smallKnightSize = 50;
        int smallBishopSize = 50;
        int smallRookSize = 45;
        int smallPawnSize = 40;

        // Put the images of the pieces into the image icons.

        // Large sizes.
        largeWhitePawnIcon = openPieceImageFile("piece_images/white_pawn.png", largePawnSize);
        largeBlackPawnIcon = openPieceImageFile("piece_images/black_pawn.png", largePawnSize);

        largeWhiteRookIcon = openPieceImageFile("piece_images/white_rook.png", largeRookSize);
        largeBlackRookIcon = openPieceImageFile("piece_images/black_rook.png", largeRookSize);

        largeWhiteKnightIcon = openPieceImageFile("piece_images/white_knight.png", largeKnightSize);
        largeBlackKnightIcon = openPieceImageFile("piece_images/black_knight.png", largeKnightSize);

        largeWhiteBishopIcon = openPieceImageFile("piece_images/white_bishop.png", largeBishopSize);
        largeBlackBishopIcon = openPieceImageFile("piece_images/black_bishop.png", largeBishopSize);

        largeWhiteQueenIcon = openPieceImageFile("piece_images/white_queen.png", largeQueenSize);
        largeBlackQueenIcon = openPieceImageFile("piece_images/black_queen.png", largeQueenSize);

        largeWhiteKingIcon = openPieceImageFile("piece_images/white_king.png", largeKingSize);
        largeBlackKingIcon = openPieceImageFile("piece_images/black_king.png", largeKingSize);

        // Small sizes.
        smallWhitePawnIcon = openPieceImageFile("piece_images/white_pawn.png", smallPawnSize);
        smallBlackPawnIcon = openPieceImageFile("piece_images/black_pawn.png", smallPawnSize);

        smallWhiteRookIcon = openPieceImageFile("piece_images/white_rook.png", smallRookSize);
        smallBlackRookIcon = openPieceImageFile("piece_images/black_rook.png", smallRookSize);

        smallWhiteKnightIcon = openPieceImageFile("piece_images/white_knight.png", smallKnightSize);
        smallBlackKnightIcon = openPieceImageFile("piece_images/black_knight.png", smallKnightSize);

        smallWhiteBishopIcon = openPieceImageFile("piece_images/white_bishop.png", smallBishopSize);
        smallBlackBishopIcon = openPieceImageFile("piece_images/black_bishop.png", smallBishopSize);

        smallWhiteQueenIcon = openPieceImageFile("piece_images/white_queen.png", smallQueenSize);
        smallBlackQueenIcon = openPieceImageFile("piece_images/black_queen.png", smallQueenSize);

        smallWhiteKingIcon = openPieceImageFile("piece_images/white_king.png", smallKingSize);
        smallBlackKingIcon = openPieceImageFile("piece_images/black_king.png", smallKingSize);

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
            BufferedImage bufferedImage = ImageIO.read(getClass().getClassLoader().getResource(filePath));
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

    /**
     * Builds and inserts the rank and file chess board border. 
     */
    public void buildRankAndFileBorder() {

        Color borderColor = new Color(230, 235, 237);
        Color borderBorderColor = new Color(199, 208, 212); // Color of the border's boarder

        this.setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, borderBorderColor));
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbForThis = new GridBagConstraints();
        gbForThis.fill = GridBagConstraints.CENTER;
        JPanel rankFileAndBoardContainer = new JPanel();
        rankFileAndBoardContainer.setLayout(new GridBagLayout());
        GridBagConstraints containergb = new GridBagConstraints();

        //Add a filler square to the top left of the board border corner. 
        JPanel topLeftBoardFillerSquare = new JPanel();
        topLeftBoardFillerSquare.setBackground(borderColor);
        topLeftBoardFillerSquare.setOpaque(true);
        topLeftBoardFillerSquare.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, borderBorderColor));
        containergb.gridx = 0;
        containergb.gridy = 0;
        containergb.fill = GridBagConstraints.BOTH;
        containergb.gridwidth = 1;
        containergb.gridheight = 1;
        containergb.weighty = 1;
        containergb.weightx = 1;
        rankFileAndBoardContainer.add(topLeftBoardFillerSquare, containergb);

        // Build left rank board border.
        JPanel leftRankHolder = new JPanel();
        leftRankHolder.setLayout(new GridBagLayout());
        GridBagConstraints rfgb = new GridBagConstraints();
        rfgb.fill = GridBagConstraints.BOTH;
        rfgb.gridx = 0;
        rfgb.weighty = 1;
        int j = 0;
        for (int i = 8; i > 0; i--) {
            String rankString = String.valueOf(i);
            JLabel rankLabel = new JLabel(rankString);
            rankLabel.setFont(new Font("Arial", Font.PLAIN, 20));
            rankLabel.setForeground(new Color(84, 133, 156));
            JPanel rankSquare = new JPanel();
            rankSquare.setBackground(borderColor);
            rankSquare.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, borderColor));
            rankSquare.setLayout(new GridBagLayout());
            GridBagConstraints gridBag = new GridBagConstraints();
            gridBag.fill = GridBagConstraints.BOTH;
            gridBag.gridx = 0;
            gridBag.gridy = 0;
            rankSquare.add(rankLabel, gridBag);
            rfgb.gridy = j;
            j++;

            // For 900 x 900 board size.
            if (currentFrameWidth > 1750 || currentFrameHeight > 1100) {

                rankSquare.setMinimumSize(new Dimension(80, 80));
                rankSquare.setPreferredSize(new Dimension(80, 80));
                rankSquare.setMaximumSize(new Dimension(80, 80));

            } else { // For 440 x 440 board size

                rankSquare.setMinimumSize(new Dimension(50, 50));
                rankSquare.setPreferredSize(new Dimension(50, 50));
                rankSquare.setMaximumSize(new Dimension(50, 50));
            }
            leftRankHolder.add(rankSquare, rfgb);
        }
        // Add the left rank border to the rank/file & board container.
        containergb.gridx = 0;
        containergb.gridy = 1;
        containergb.fill = GridBagConstraints.BOTH;
        containergb.gridwidth = 1;
        containergb.gridheight = 8;
        containergb.weighty = 1;
        containergb.weightx = 1;
        leftRankHolder.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, borderBorderColor));
        rankFileAndBoardContainer.add(leftRankHolder, containergb);

        // Build top file board border.
        JPanel topFileHolder = new JPanel();
        topFileHolder.setLayout(new GridBagLayout());
        GridBagConstraints rfgb1 = new GridBagConstraints();
        rfgb1.fill = GridBagConstraints.BOTH;
        rfgb1.gridy = 0;
        rfgb1.weightx = 1;
        j = 0;

        for (int i = 8; i > 0; i--) {
            String rankString = translateNumberCoordinateToLetter(i);
            JLabel rankLabel = new JLabel(rankString);
            JPanel rankSquare = new JPanel();
            rankLabel.setFont(new Font("Arial", Font.PLAIN, 20));
            rankLabel.setForeground(new Color(84, 133, 156));
            rankSquare.setBackground(borderColor);
            rankSquare.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, borderColor));
            rankSquare.setLayout(new GridBagLayout());
            GridBagConstraints gridBag = new GridBagConstraints();
            gridBag.fill = GridBagConstraints.BOTH;
            gridBag.gridx = 0;
            gridBag.gridy = 0;
            rankSquare.add(rankLabel, gridBag);
            rfgb1.gridx = j;
            j++;

            // For 900 x 900 board size.
            if (currentFrameWidth > 1750 || currentFrameHeight > 1100) {

                rankSquare.setMinimumSize(new Dimension(80, 80));
                rankSquare.setPreferredSize(new Dimension(80, 80));
                rankSquare.setMaximumSize(new Dimension(80, 80));

            } else { // For 440 x 440 board size

                rankSquare.setMinimumSize(new Dimension(45, 45));
                rankSquare.setPreferredSize(new Dimension(45, 45));
                rankSquare.setMaximumSize(new Dimension(45, 45));
            }
            topFileHolder.add(rankSquare, rfgb1);
        }

        // Add the top file border to the rank/file & board container.
        containergb.gridx = 1;
        containergb.gridy = 0;
        containergb.fill = GridBagConstraints.BOTH;
        containergb.gridwidth = 8;
        containergb.gridheight = 1;
        topFileHolder.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, borderBorderColor));
        rankFileAndBoardContainer.add(topFileHolder, containergb);

        //Add a filler square to the top left of the board border corner. 
        JPanel topRightBoardFillerSquare = new JPanel();
        topRightBoardFillerSquare.setBackground(borderColor);
        topRightBoardFillerSquare.setOpaque(true);
        topRightBoardFillerSquare.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, borderBorderColor));
        containergb.gridx = 9;
        containergb.gridy = 0;
        containergb.fill = GridBagConstraints.BOTH;
        containergb.gridwidth = 1;
        containergb.gridheight = 1;
        containergb.weighty = 1;
        containergb.weightx = 1;
        rankFileAndBoardContainer.add(topRightBoardFillerSquare, containergb);

        // Build right rank board border.
        JPanel rightRankHolder = new JPanel();
        rightRankHolder.setLayout(new GridBagLayout());
        GridBagConstraints rfgb2 = new GridBagConstraints();
        rfgb2.fill = GridBagConstraints.BOTH;
        rfgb2.gridx = 0;
        rfgb2.weighty = 1;
        j = 0;
        for (int i = 8; i > 0; i--) {
            String rankString = String.valueOf(i);
            JLabel rankLabel = new JLabel(rankString);
            JPanel rankSquare = new JPanel();
            rankLabel.setFont(new Font("Arial", Font.PLAIN, 20));
            rankLabel.setForeground(new Color(84, 133, 156));
            rankSquare.setBackground(borderColor);
            rankSquare.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, borderColor));
            rankSquare.setLayout(new GridBagLayout());
            GridBagConstraints gridBag = new GridBagConstraints();
            gridBag.fill = GridBagConstraints.BOTH;
            gridBag.gridx = 0;
            gridBag.gridy = 0;
            rankSquare.add(rankLabel, gridBag);
            rfgb2.gridy = j;
            j++;

            // For 900 x 900 board size.
            if (currentFrameWidth > 1750 || currentFrameHeight > 1100) {

                rankSquare.setMinimumSize(new Dimension(80, 80));
                rankSquare.setPreferredSize(new Dimension(80, 80));
                rankSquare.setMaximumSize(new Dimension(80, 80));

            } else { // For 440 x 440 board size

                rankSquare.setMinimumSize(new Dimension(45, 45));
                rankSquare.setPreferredSize(new Dimension(45, 45));
                rankSquare.setMaximumSize(new Dimension(45, 45));
            }
            rightRankHolder.add(rankSquare, rfgb2);
        }

        // Add the right rank border to the rank/file & board container.
        containergb.gridx = 9;
        containergb.gridy = 1;
        containergb.gridwidth = 1;
        containergb.gridheight = 8;
        containergb.fill = GridBagConstraints.BOTH;
        rightRankHolder.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, borderBorderColor));
        rankFileAndBoardContainer.add(rightRankHolder, containergb);

        containergb.gridx = 1;
        containergb.gridy = 1;
        containergb.gridwidth = 8;
        containergb.gridheight = 8;
        rankFileAndBoardContainer.add(boardHolder, containergb);

        //Add a filler square to the bottom right of the board border corner. 
        JPanel bottomRightBoardFillerSquare = new JPanel();
        bottomRightBoardFillerSquare.setBackground(borderColor);
        bottomRightBoardFillerSquare.setOpaque(true);
        bottomRightBoardFillerSquare.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, borderBorderColor));
        containergb.gridx = 9;
        containergb.gridy = 9;
        containergb.fill = GridBagConstraints.BOTH;
        containergb.gridwidth = 1;
        containergb.gridheight = 1;
        containergb.weighty = 1;
        containergb.weightx = 1;
        rankFileAndBoardContainer.add(bottomRightBoardFillerSquare, containergb);

        // Build bottom file board border.
        JPanel bottomFileHolder = new JPanel();
        bottomFileHolder.setLayout(new GridBagLayout());
        GridBagConstraints rfgb4 = new GridBagConstraints();
        rfgb4.fill = GridBagConstraints.BOTH;
        rfgb4.gridy = 0;
        rfgb4.weightx = 1;
        j = 0;
        for (int i = 8; i > 0; i--) {
            String rankString = translateNumberCoordinateToLetter(i);
            JLabel rankLabel = new JLabel(rankString);
            JPanel rankSquare = new JPanel();
            rankLabel.setFont(new Font("Arial", Font.PLAIN, 20));
            rankLabel.setForeground(new Color(84, 133, 156));
            rankSquare.setBackground(borderColor);
            rankSquare.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, borderColor));
            rankSquare.setLayout(new GridBagLayout());
            GridBagConstraints gridBag = new GridBagConstraints();
            gridBag.fill = GridBagConstraints.BOTH;
            gridBag.gridx = 0;
            gridBag.gridy = 0;
            rankSquare.add(rankLabel, gridBag);
            rfgb1.gridx = j;
            j++;

            // For 900 x 900 board size.
            if (currentFrameWidth > 1750 || currentFrameHeight > 1100) {

                rankSquare.setMinimumSize(new Dimension(80, 80));
                rankSquare.setPreferredSize(new Dimension(80, 80));
                rankSquare.setMaximumSize(new Dimension(80, 80));

            } else { // For 440 x 440 board size

                rankSquare.setMinimumSize(new Dimension(45, 45));
                rankSquare.setPreferredSize(new Dimension(45, 45));
                rankSquare.setMaximumSize(new Dimension(45, 45));
            }
            bottomFileHolder.add(rankSquare, rfgb4);
        }

        // Add the bottom file border to the rank/file & board container.
        containergb.gridx = 1;
        containergb.gridy = 9;
        containergb.fill = GridBagConstraints.BOTH;
        containergb.gridwidth = 8;
        containergb.gridheight = 1;
        bottomFileHolder.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, borderBorderColor));
        rankFileAndBoardContainer.add(bottomFileHolder, containergb);

         //Add a filler square to the bottom left of the board border corner. 
         JPanel bottomLeftBoardFillerSquare = new JPanel();
         bottomLeftBoardFillerSquare.setBackground(borderColor);
         bottomLeftBoardFillerSquare.setOpaque(true);
         bottomLeftBoardFillerSquare.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, borderBorderColor));
         containergb.gridx = 0;
         containergb.gridy = 9;
         containergb.fill = GridBagConstraints.BOTH;
         containergb.gridwidth = 1;
         containergb.gridheight = 1;
         containergb.weighty = 1;
         containergb.weightx = 1;
         rankFileAndBoardContainer.add(bottomLeftBoardFillerSquare, containergb);

        rankFileAndBoardContainer.setOpaque(false);
        this.add(rankFileAndBoardContainer, gbForThis);
    }

    /**
     * Translate numbers to letters for building the file border.
     * 
     * @param number x axis number associcated with the square location
     * @return file letter
     */
    public String translateNumberCoordinateToLetter(int number) {
        String output = "";

        if (number == 8) {
            return output = "a";
        } else if (number == 7) {
            return output = "b";
        } else if (number == 6) {
            return output = "c";
        } else if (number == 5) {
            return output = "d";
        } else if (number == 4) {
            return output = "e";
        } else if (number == 3) {
            return output = "f";
        } else if (number == 2) {
            return output = "g";
        } else if (number == 1) {
            return output = "h";
        }

        return output;
    }

    /**
     * Translate numbers to letters for teching square it's location.
     * 
     * @param number x axis number associcated with the square location
     * @return file letter
     */
    public String translateNumberLocationToFileLetter(int number) {
        String output = "";

        if (number == 0) {
            return output = "a";
        } else if (number == 1) {
            return output = "b";
        } else if (number == 2) {
            return output = "c";
        } else if (number == 3) {
            return output = "d";
        } else if (number == 4) {
            return output = "e";
        } else if (number == 5) {
            return output = "f";
        } else if (number == 6) {
            return output = "g";
        } else if (number == 7) {
            return output = "h";
        }

        return output;
    }

    /**
     * Translate board coordinates to algebraic coordinate.
     * 
     * @param number y axis number associcated with the square location
     * @return rank number
     */
    public String translateNumbericalCoordToAlgebraic(int number) {
        String output = "";

        if (number == 0) {
            return output = "8";
        } else if (number == 1) {
            return output = "7";
        } else if (number == 2) {
            return output = "6";
        } else if (number == 3) {
            return output = "5";
        } else if (number == 4) {
            return output = "4";
        } else if (number == 5) {
            return output = "3";
        } else if (number == 6) {
            return output = "2";
        } else if (number == 7) {
            return output = "1";
        }

        return output;
    }
}
