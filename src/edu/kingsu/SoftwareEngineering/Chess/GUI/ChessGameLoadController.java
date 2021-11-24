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

    public ChessGameLoadController(ChessGame chessGame){
        this.chessGame= chessGame;
    }

    public void actionPerformed(ActionEvent e){
        JFileChooser chooser= new JFileChooser();
        FileNameExtensionFilter filter= new FileNameExtensionFilter("PGN files", "pgn");
        chooser.setFileFilter(filter);
        chooser.setDialogTitle("Load Chess Game");

        //get the application frame via the parents of the object which this event
        //occurred on
        Container container= (Container)e.getSource();
        while(container.getParent() != null){
            container= container.getParent();
        }

        int userSelection= chooser.showOpenDialog(container);
        if(userSelection == JFileChooser.APPROVE_OPTION){
            File selectedFile= chooser.getSelectedFile();
            try{
                PGNFile pgnFile= new PGNFile(selectedFile);
            }catch(FileNotFoundException ex){
                System.err.println("Could not find the designated file");
                ex.printStackTrace();
            }catch(IllegalArgumentException ex){
                System.err.println("File could not be used as a PGN file");
                ex.printStackTrace();
            }
            //how to set pgnFile as the member in GameSetUp?
        }
    }
}
