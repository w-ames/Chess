package edu.kingsu.SoftwareEngineering.Chess.GUI;

import java.awt.*;
import java.awt.event.*;
import java.io.*;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import edu.kingsu.SoftwareEngineering.Chess.Model.*;
import edu.kingsu.SoftwareEngineering.Chess.PGN.PGNFile;


/**
 * ChessGameSaveController that holds all the save game controller of the chess game
 * 
 */
public class ChessGameSaveController implements ActionListener{
    private ChessGame chessGame;
    private ApplicationFrame appFrame;

    public ChessGameSaveController(ChessGame chessGame, ApplicationFrame appFrame){
        this.chessGame= chessGame;
        this.appFrame= appFrame;
    }

    public void actionPerformed(ActionEvent e){
        doAction();
    }

    public int doAction(){
        if (chessGame == null) {
            return -1;
        }
        JFileChooser chooser= new JFileChooser(new File(System.getProperty("user.dir")));
        FileNameExtensionFilter filter= new FileNameExtensionFilter("PGN files", "pgn");
        chooser.setFileFilter(filter);
        chooser.setDialogTitle("Save Chess Game");

        int userSelection= chooser.showSaveDialog(appFrame);
        if(userSelection == JFileChooser.APPROVE_OPTION){
            File fileToSave= chooser.getSelectedFile();
            String filePath= fileToSave.getPath();
            fileToSave= new File(filePath + ".pgn");
            // try{
            //     PGNFile pgnRepresentation= new PGNFile(chessGame.getTagPairMap(), chessGame.getAlgebraicHistory(), chessGame.getResult());  //ChessGame needs to store the result
            // }catch(FileNotFoundException ex){
            //     System.err.println("Could not find the designated file");
            //     ex.printStackTrace();
            // }catch(IllegalArgumentException ex){
            //     System.err.println("File could not be used as a PGN file");
            //     ex.printStackTrace();
            // }
            //String pgn= pgnRepresentation.getFileText();

            try{
                FileWriter writer= new FileWriter(fileToSave);
                writer.write(chessGame.getPGNFile().getFileText());
                writer.close();
            }catch(IOException ex){
                System.err.println("IOException when creating FileWriter");
                ex.printStackTrace();
            }
            return userSelection;
        }else{
            return userSelection;
        }
    }

    public void setChessGame(ChessGame chessGame) {
        this.chessGame = chessGame;
    }
}
