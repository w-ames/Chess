package edu.kingsu.SoftwareEngineering.Chess.GUI;

import java.awt.*;
import java.awt.event.*;
import java.io.*;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import edu.kingsu.SoftwareEngineering.Chess.Model.*;
import edu.kingsu.SoftwareEngineering.Chess.PGN.PGNFile;

public class ChessGameSaveController implements ActionListener{
    private ChessGame chessGame;

    public ChessGameSaveController(ChessGame chessGame){
        this.chessGame= chessGame;
    }

    public void actionPerformed(ActionEvent e){
        if (chessGame == null) {
            return;
        }
        JFileChooser chooser= new JFileChooser(new File(System.getProperty("user.dir")));
        FileNameExtensionFilter filter= new FileNameExtensionFilter("PGN files", "pgn");
        chooser.setFileFilter(filter);
        chooser.setDialogTitle("Save Chess Game");

        //get the application frame via the parents of the object which this event
        //occurred on
        Container container= (Container)e.getSource();
        while(container.getParent() != null){
            container= container.getParent();
        }

        int userSelection= chooser.showSaveDialog(container);
        if(userSelection == JFileChooser.APPROVE_OPTION){
            File fileToSave= chooser.getSelectedFile();
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
        }
    }

    public void setChessGame(ChessGame chessGame) {
        this.chessGame = chessGame;
    }
}
