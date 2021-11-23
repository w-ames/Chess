package edu.kingsu.SoftwareEngineering.Chess.GUI;

import java.awt.*;
import java.awt.event.*;
import java.io.FileWriter;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import edu.kingsu.SoftwareEngineering.Chess.PGN.PGNFile;

public class ChessGameSaveController implements ActionListener{
    private ChessGame chessGame;

    public ChessGameSaveController(ChessGame chessGame){
        this.chessGame= chessGame;
    }

    public void actionPerformed(ActionEvent e){
        JFileChooser chooser= new JFileChooser();
        FileNameExtensionFilter filter= new FileNameExtensionFilter("PGN files", "pgn");
        chooser.setTileFilter(filter);
        fileChooser.setDialogTitle("Save Chess Game");

        //get the application frame via the parents of the object which this event
        //occurred on
        Container container= e.getSource();
        while(container.getParent() != null){
            container= container.getParent();
        }

        int userSelection= chooser.showSaveDialog(container);
        if(userSelection == JFileChooser.APPROVE_OPTION){
            File fileToSave= chooser.getSelectedFile();
            try{
                PGNFile pgnRepresentation= new PGNFile(chessGame.getTagPairMap(), chessGame.getAlgebraicHistory(), chessGame.getResult());  //ChessGame needs to store the result
                String pgn= pgnRepresentation.getFileText();

                FileWriter writer= new FileWriter(fileToSave);
                writer.write(pgn);
                writer.close();
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }
    }
}
