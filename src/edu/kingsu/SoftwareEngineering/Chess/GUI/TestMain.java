package edu.kingsu.SoftwareEngineering.Chess.GUI;
import javax.swing.*;

public class TestMain {

    public static void main(String[] args) {
        ApplicationFrame testFrame = new ApplicationFrame();
        ChessPanel chessPanel = new ChessPanel();

        testFrame.add(chessPanel);
    }
}