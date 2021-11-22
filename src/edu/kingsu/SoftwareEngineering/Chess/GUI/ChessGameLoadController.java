package edu.kingsu.SoftwareEngineering.Chess.GUI;

import java.awt.*;
import java.awt.event.*;

public class ChessGameLoadController implements ActionListener{
    
    public void actionPerformed(ActionEvent e){
        JFileChooser chooser= new JFileChooser();
        FileNameExtensionFilter filter= new FileNameExtensionFilter("PGN files", "pgn");
        chooser.setTileFilter(filter);
        fileChooser.setDialogTitle("Load Chess Game");

        
    }
}
