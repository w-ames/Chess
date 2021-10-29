package edu.kingsu.SoftwareEngineering.Chess;

import edu.kingsu.SoftwareEngineering.Chess.GUI.ApplicationFrame;

public class Launcher {
    public static void main(String[] args) {
        ApplicationFrame  frame = new ApplicationFrame();
        ChessPanel chessPanel = new ChessPanel();
        frame.add(chessPanel); 
        
    }
}
