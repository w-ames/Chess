package edu.kingsu.SoftwareEngineering.Chess.GUI;

import java.awt.*;
import java.awt.event.*;
import java.io.*;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import edu.kingsu.SoftwareEngineering.Chess.Model.*;
import edu.kingsu.SoftwareEngineering.Chess.PGN.PGNFile;

public class ChessGameLoadController implements ActionListener{
    private ChessGame chessGame;
    private ApplicationFrame appFrame;
    private GameSetUp setUp;

    public ChessGameLoadController(ChessGame chessGame, ApplicationFrame appFrame, GameSetUp setUp){
        this.chessGame= chessGame;
        this.appFrame= appFrame;
        this.setUp= setUp;
    }

    /**
     * Displays a file chooser to the user so they can choose a PGN file to load.
     */
    public void actionPerformed(ActionEvent e){
        doAction();
    }

    /**
     * Displays a file chooser to the user so they can choose a PGN file to load. This method
     * is called internally by actionPerformed, but may also be called externally.
     */
    public void doAction(){
        JFileChooser chooser= new JFileChooser(new File(System.getProperty("user.dir")));
        FileNameExtensionFilter filter= new FileNameExtensionFilter("PGN files", "pgn");
        chooser.setFileFilter(filter);
        chooser.setDialogTitle("Load Chess Game");

        Container container = appFrame;

        int userSelection= chooser.showOpenDialog(container);
        if(userSelection == JFileChooser.APPROVE_OPTION){
            File selectedFile= chooser.getSelectedFile();
            try{
                PGNFile pgnFile= new PGNFile(selectedFile);
                if (chessGame == null) {
                    setUp.setPGNFile(pgnFile);
                    setUp.addLoadedFileName(selectedFile.getName());
                } else {
                    chessGame.loadPGNFile(pgnFile);
                }
            }catch(FileNotFoundException ex){
                System.err.println("Could not find the designated file");
                ex.printStackTrace();
            }catch(IllegalArgumentException ex){
                System.err.println("File could not be used as a PGN file");
                ex.printStackTrace();
            }
        }
    }

    public void setChessGame(ChessGame chessGame) {
        this.chessGame = chessGame;
    }
}
