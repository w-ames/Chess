package edu.kingsu.SoftwareEngineering.Chess.PGN;

import java.util.*;
import java.io.File;

public class PGNFile implements Iterable<String>{
    public static final String PLAYERTYPE_TAG= "PlayerTypes";   //change the value to something more appropriate
    private Map<String, String> tagPairs;
    private List<String> moveText;

    public PGNFile(File file){
        //Check that file is correct filetype
        String fileName= file.getName();
        String fileType= "";
        if(fileName.length() > 4) fileName.substring(fileName.length() - 4);
        
        if(!fileType.equals(".pgn")) throw new IllegalArgumentException("Illegal file type");
        
        //Read tag pairs in header, store them in tagPairs

        //Read moves in body, store them in moveText
    }

    public PGNFile(Map<String, String> tagPairs, List<String> moves){
        this.tagPairs= tagPairs;
        moveText= moves;
    }

    public Iterator<String> iterator(){
        return moveText.iterator();
    }

    public Map<String, String> getTagPairMap(){
        return tagPairs;
    }

    public List<String> getMoveTextList(){
        return moveText;
    }

    public File toFile(){
        //Create a new PGN file (where does it go?)

        //Write tagPairs to the file's header

        //Write moves to the file's body

        //Write the result at the end (how do I know the result? What if the game isn't over yet?)

        //return the file

        return null;
    }
}