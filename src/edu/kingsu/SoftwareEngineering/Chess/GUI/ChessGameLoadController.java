package edu.kingsu.SoftwareEngineering.Chess.GUI;

import java.awt.*;
import java.awt.event.*;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import edu.kingsu.SoftwareEngineering.Chess.PGN.PGNFile;

public class ChessGameLoadController implements ActionListener{
    private ChessGame chessGame;

    public ChessGameLoadController(ChessGame chessGame){
        this.chessGame= chessGame;
    }

    public void actionPerformed(ActionEvent e){
        JFileChooser chooser= new JFileChooser();
        FileNameExtensionFilter filter= new FileNameExtensionFilter("PGN files", "pgn");
        chooser.setTileFilter(filter);
        fileChooser.setDialogTitle("Load Chess Game");

        //get the application frame via the parents of the object which this event
        //occurred on
        Container container= e.getSource();
        while(container.getParent() != null){
            container= container.getParent();
        }

        int userSelection= chooser.showOpenDialog(container);
        if(userSelection == JFileChooser.APPROVE_OPTION){
            File selectedFile= chooser.getSelectedFile();
            PGNFile pgnFile= new PGNFile(selectedFile);
            //how to set pgnFile as the member in GameSetUp?
        }
    }
}
