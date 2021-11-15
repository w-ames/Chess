package edu.kingsu.SoftwareEngineering.Chess.PGN;

import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class PGNFile implements Iterable<String>{
    public static final String PLAYERTYPE_TAG= "PlayerTypes";   //change the value to something more appropriate
    private Map<String, String> tagPairs;
    private List<String> moveText;

    public PGNFile(File file) throws IllegalArgumentException, FileNotFoundException{
        //Check that file is correct filetype
        String fileName= file.getName();
        String fileType= "";
        if(fileName.length() > 4) fileName.substring(fileName.length() - 4);
        
        if(!fileType.equals(".pgn")) throw new IllegalArgumentException("Illegal file type");
        
        //Read tag pairs in header, store them in tagPairs
        tagPairs= new HashMap<String, String>();
        moveText= new ArrayList<String>();

        Scanner scanner= new Scanner(file);

        String line;
        String regex= "\\[(?<tag>[^\\n]+)\\s\"(?<value>[^\\n\\]]+)\"]";
        Pattern pattern= Pattern.compile(regex);
        Matcher matcher;

        //continue reading lines and parsing tag pairs until the tag pair pattern no longer matches
        if(!scanner.hasNextLine()) throw new IllegalArgumentException("File has no lines");
        line= scanner.nextLine();
        matcher= pattern.matcher(line);
        while(matcher.find()){
            tagPairs.put(matcher.group("tag"), matcher.group("value"));
            if(!scanner.hasNextLine()) return;
            line= scanner.nextLine();
            matcher= pattern.matcher(line);
        }

        //ensure that the mandatory tag pairs are present
        if(!tagPairs.containsKey("Event")) throw new IllegalArgumentException("No Event tag present");
        if(!tagPairs.containsKey("Site")) throw new IllegalArgumentException("No Site tag present");
        if(!tagPairs.containsKey("Date")) throw new IllegalArgumentException("No Date tag present");
        if(!tagPairs.containsKey("Round")) throw new IllegalArgumentException("No Round tag present");
        if(!tagPairs.containsKey("White")) throw new IllegalArgumentException("No White tag present");
        if(!tagPairs.containsKey("Black")) throw new IllegalArgumentException("No Black tag present");
        if(!tagPairs.containsKey("Result")) throw new IllegalArgumentException("No Result tag present");

        //skip the blank lines, if necessary
        regex= "\\n";
        matcher= pattern.matcher(line);

        while(matcher.find() && scanner.hasNextLine()){

        }
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

    public String getFileText(){    //return the string that will be written into the file
        String fileText= "";

        return fileText;
    }

    @Deprecated
    public File toFile(){
        //Create a new PGN file (where does it go?)
        //Nik says it would be better to let a file chooser handle this. But
        //how do I get/write to that file? (Pass it as a parameter?)
        //Need to work on the mechanics of all this

        //Write tagPairs to the file's header

        //Write moves to the file's body

        //Write the result at the end (how do I know the result? What if the game isn't over yet?)

        //return the file

        return null;
    }
}