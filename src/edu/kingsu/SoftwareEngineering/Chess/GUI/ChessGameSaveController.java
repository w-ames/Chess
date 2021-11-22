package edu.kingsu.SoftwareEngineering.Chess.GUI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ChessGameSaveController implements ActionListener{
    
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
        int userSelection= chooser.showSaveDialog(container);  //how do I specify the parent here?
        if(userSelection == JFileChooser.APPROVE_OPTION){
            File fileToSave= chooser.getSelectedFile();
        }
    }
}
