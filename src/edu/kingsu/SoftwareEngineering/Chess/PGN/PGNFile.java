package edu.kingsu.SoftwareEngineering.Chess.PGN;

import java.util.*;
import java.io.File;

public class PGNFile implements Iterable{
    public static final String PLAYERTYPE_TAG= "PlayerTypes";
    private Map<String, String> tagPairs;
    private List<String> moveText;

    public PGNFile(File file){

    }

    public PGNFile(Map<String, String> tagPairs, List<String> moves){
        this.tagPairs= tagPairs;
        moveText= moves;
    }

    public Iterator<String> iterator(){
        //write anonymous inner class to create a new iterator

        return null;
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