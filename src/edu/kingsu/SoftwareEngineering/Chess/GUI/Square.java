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
import java.awt.Font;
import javax.swing.border.EmptyBorder;
import java.awt.event.*;
import java.awt.event.MouseEvent;
import java.awt.Cursor;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.awt.Image;

/**
 * Creates board squares that are a specified color (black or white), and are
 * aware of their location.
 * 
 * @author Chelsie Bajic
 * @since 10/2021
 */
public class Square extends JButton implements MouseListener {

    private String location; // The location of this square within the 2D array (squareHolderArray[][]).
    boolean color; // White = true, Black = false

    /**
     * The constructor for Square class. Adds mouse listener to each square and
     * determines what color it is going to be based on its position on the board.
     * 
     * @param location The location of this square within the 2D array that holds
     *                 the board representation (squareHolderArray[][], found in the
     *                 ChessGameGUIView class).
     * @param color    The color of this square. true == White, false == Balck.
     */
    public Square(String location, boolean color) {

        addMouseListener(this);// For square border highlight upon hover.
        this.setMinimumSize(new Dimension(70, 70));
        this.setMaximumSize(new Dimension(70, 70));
        this.color = color;
        this.location = location;

        if (color == false) {
            // Black squares.
            this.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, new Color(144, 144, 144)));
            this.setBackground(new Color(152, 152, 152));
            this.setOpaque(true);
        } else {
            // White squares.
            this.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, new Color(232, 232, 232)));
            this.setBackground(new Color(248, 248, 248));
            this.setOpaque(true);
        }

    }

    /**
     * Changes the border color to blue, to highlight it when the mouse hovers over
     * it.
     */
    public void mouseEntered(MouseEvent e) {
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, new Color(49, 209, 245)));
    }

    /**
     * To get the location of the square on the board.
     * 
     * @return the location of the square on the board.
     */
    public String getSquareLocation() {
        return location;
    }

    /**
     * When the mouse leaves the square, changes it back to its original border
     * color.
     */
    public void mouseExited(MouseEvent e) {
        if (color == false) {
            this.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, new Color(144, 144, 144)));
        } else {
            this.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, new Color(232, 232, 232)));
        }

    }

    public void mousePressed(MouseEvent e) {

    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseClicked(MouseEvent e) {

    }

}