package edu.kingsu.SoftwareEngineering.Chess.PGN;

import java.util.*;
import java.io.File;

public class PGNFile implements Iterable<String>{
    public static final String PLAYERTYPE_TAG= "PlayerTypes";   //change the value to something more appropriate
    private Map<String, String> tagPairs;
    private List<String> moveText;

    public PGNFile(File file){

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

        return null;
    }
}