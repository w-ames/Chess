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
        tagPairs= new LinkedHashMap<String, String>();
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

        //Write tagPairs to the header
        Set<String> keySet= tagPairs.keySet();
        Iterator it= keySet.iterator();
        while(it.hasNext()){
            String key= (String)it.next();
            fileText += ("[" + key + " \"" + tagPairs.get(key) + "\"]\n");
        }

        fileText += "\n";

        //Write moves to the body --> do a maximum of 85 characters to a row without splitting up any entities when creating a new line (let a move number and the moves by both white and black count as one entity)
        int turnCounter= 1;
        int lineCounter= 0; //use to constrict a line to a maximum of 85 characters
        for(int i=0; i < moveText.size(); i++){
            //if it is white's turn, add the turn counter to the string
            if(i % 2 == 0){
                if(turnCounter < 10) lineCounter += 3;
                else if(turnCounter < 100) lineCounter += 4;
                else lineCounter += 5; //this will work properly for a game with up to 999 turns

                //create a new line if at the maximum
                if(lineCounter > 85){
                    fileText += "\n";
                    lineCounter= 0;

                    if(turnCounter < 10) lineCounter += 3;
                    else if(turnCounter < 100) lineCounter += 4;
                    else lineCounter += 5;
                }

                fileText += ("" + turnCounter + ". ");
                turnCounter++;
            }

            //add the move to the string
            if(lineCounter + moveText.get(i).length() > 85){
                fileText += "\n";
                lineCounter= 0;
            }

            fileText += moveText.get(i);
        }

        //Write the result at the end (how do I know the result? What if the game isn't over yet?)

        return fileText;
    }
}