package edu.kingsu.SoftwareEngineering.Chess.PGN;

import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import edu.kingsu.SoftwareEngineering.Chess.Model.*;
import edu.kingsu.SoftwareEngineering.Chess.Model.Moves.*;
import edu.kingsu.SoftwareEngineering.Chess.PGN.Parser.*;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

/**
 * Represents a PGN file, including all of its constituent parts: tag pairs, list of moves, and result.
 */
public class PGNFile implements Iterable<String>{
    public static final String PLAYERTYPE_TAG= "PlayerTypes";   //white player is human/easyAI/etc, black player is human/etc.
    private Map<String, String> tagPairs;
    private List<String> moveText;
    private String result;

    /**
     * Constructor which parses a PGN file to obtain the tag pairs, list of moves, and result.
     * @param file the PGN file used to create the object
     * @throws IllegalArgumentException if the given file contains an invalid tag pair, move, or result,
     *  or if any of the mandatory tag pairs is missing
     * @throws FileNotFoundException if the given file cannot be found
     */
    public PGNFile(File file) throws IllegalArgumentException, FileNotFoundException{
        //Check that file is correct filetype
        String fileName= file.getName();
        String fileType= "";
        if(fileName.length() > 4) fileName.substring(fileName.length() - 4);
        
        if(!fileType.equals(".pgn")) throw new IllegalArgumentException("Illegal file type");
        
        //Read tag pairs in header, store them in tagPairs
        tagPairs= new LinkedHashMap<String, String>();
        moveText= new ArrayList<String>();

        PGNFileLexer lexer= null;
        try{
            lexer= new PGNFileLexer(CharStreams.fromReader(new FileReader(file)));
        }catch(Exception e){
            e.printStackTrace();
        }

        CommonTokenStream tokens= new CommonTokenStream(lexer);
        PGNFileParser parser= new PGNFileParser(tokens);
        ParseTree tree= parser.parse();
        ParseTreeWalker walker= new ParseTreeWalker();
        PGNFileEvalListener listener= new PGNFileEvalListener();
        walker.walk(listener, tree);
        listener.getPGNFile();

        /* THE FOLLOWING USES REGEX, WHICH WE'RE SWITCHING OUT FOR ANTLR
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
        }*/

        validateTagPairs(tagPairs);
        moveText= validateMoveTextAndResult(moveText, result);

        /* THE FOLLOWING USES REGEX, WHICH WE'RE SWITCHING OUT FOR ANTLR
        //skip the blank lines, if necessary
        regex= "\\n";
        matcher= pattern.matcher(line);

        while(matcher.find() && scanner.hasNextLine()){

        }*/

        //Read moves in body, store them in moveText

        //Read the result, store it in result (if the game is still in progress, result is *)
    }

    /**
     * Constructor which creates the object from a given set of tag pairs, moves, and the result.
     * @param tagPairs the tag pairs for this PGNFile
     * @param moves a list of the moves for this PGNFile
     * @param result the result of the chess game represented by this PGNFile. Can be any of "1-0" (white win),
     *  "0-1" (black win), "1/2-1/2" (stalemate), or "*" (in progress)
     * @throws IllegalArgumentException if any of the mandatory tag pairs is missing, a tag pair value contains
     *  a quotation mark, any of the moves are invalid, or the result is invalid
     */
    public PGNFile(Map<String, String> tagPairs, List<String> moves, String result) throws IllegalArgumentException{
        validateTagPairs(tagPairs);
        this.tagPairs= tagPairs;

        moveText= validateMoveTextAndResult(moves, result);
        this.result= result;
    }

    /**
     * Retrieves an Iterator to traverse over the list of moves in this PGNFile
     * @return the moveText iterator
     */
    public Iterator<String> iterator(){
        return moveText.iterator();
    }

    /**
     * Retrieves the tag pair map of this PGNFile
     * @return the tag pair map
     */
    public Map<String, String> getTagPairMap(){
        return tagPairs;
    }

    /**
     * Retrieves the list of moves represented as Strings in algebraic notation for the chess
     * game represented by this PGNFile
     * @return the list of moves
     */
    public List<String> getMoveTextList(){
        return moveText;
    }

    /**
     * Retrieves the result of the chess game represented by this PGNFile
     * @return the chess game's result
     */
    public String getResult(){
        return result;
    }

    /**
     * Sets the result of the chess game represented by this PGNFile
     * @param newResult the chess game's result
     */
    public void setResult(String newResult){
        result = newResult;
    }

    /**
     * Retrieves the text which represents the PGNFile object, which can be used to write to a file
     * of the PGN filetype
     * @return the text of the PGN file
     */
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

        //Write the result at the end
        fileText += result;

        return fileText;
    }

    /**
     * Validates the given tag pairs, throwing an exception if any part of them is invalid.
     * @param tagPairs the tag pairs to validate
     * @throws IllegalArgumentException if any of the mandatory tag pairs is missing, or if a tag
     *  pair value contains a quotation mark
     */
    private void validateTagPairs(Map<String, String> tagPairs) throws IllegalArgumentException{
        if(!tagPairs.containsKey("Event")) throw new IllegalArgumentException("No Event tag present");
        if(!tagPairs.containsKey("Site")) throw new IllegalArgumentException("No Site tag present");
        if(!tagPairs.containsKey("Date")) throw new IllegalArgumentException("No Date tag present");
        if(!tagPairs.containsKey("Round")) throw new IllegalArgumentException("No Round tag present");
        if(!tagPairs.containsKey("White")) throw new IllegalArgumentException("No White tag present");
        if(!tagPairs.containsKey("Black")) throw new IllegalArgumentException("No Black tag present");
        if(!tagPairs.containsKey("Result")) throw new IllegalArgumentException("No Result tag present");

        for(Map.Entry<String, String> entry : tagPairs.entrySet()){
            if(entry.getValue().contains("\"")) throw new IllegalArgumentException("Illegal tag pair value: contains \"");
        }
    }

    //returns the moveText, but corrected so the format matches what we want to have for each move
    /**
     * Validates the given list of moves and the end result. It is not required for a checkmate or stalemate
     * to exist on the game board in order for the game to end in these results, but a result contrary to
     * a checkmate or stalemate on the board is forbidden.
     * @param moveText the list of moves in algebraic notation
     * @param result the end result of the game. Can be any of "1-0" (white win), "0-1" (black win), "1/2-1/2" (stalemate),
     *  or "*" (in progress)
     * @return a list of moves which have had their format corrected to match this application's preferred algebraic format
     * @throws IllegalArgumentException if any of the moves is invalid or if the game result is invalid
     */
    private List<String> validateMoveTextAndResult(List<String> moveText, String result) throws IllegalArgumentException{
        List<String> correctedMoves= new ArrayList<String>();
        ChessGame verifyGame= new ChessGame(-1, -1, 0, 0);

        for(int i=0; i < moveText.size(); i++){
            Move move= PGNTranslator.translatePGNToMove(moveText.get(i), verifyGame.getBoard(), verifyGame.getPlayerTurn().isWhite());
            String correctedPGN= PGNTranslator.translateMoveToPGN(move, verifyGame.getBoard());
            if(!verifyGame.performMove(move, true)) throw new IllegalArgumentException("Illegal move at moveText index " + i);
            correctedMoves.add(correctedPGN);
        }

        GameState gameState= verifyGame.getState();
        //allow victories and stalemates outside of the ChessGame explicitly being in checkmate or stalemate
        //do not allow results which are explicitly contradictory to a ChessGame in an endgame state
        if(gameState == GameState.BLACK_CHECKMATE && !result.equals("0-1"))
            throw new IllegalArgumentException("Game result incorrect; should be 0-1");
        else if(gameState == GameState.WHITE_CHECKMATE && !result.equals("1-0"))
            throw new IllegalArgumentException("Game result incorrect; should be 1-0");
        else if(gameState == GameState.STALEMATE_NOMOVES && !result.equals("1/2-1/2"))
            throw new IllegalArgumentException("Game result incorrect; should be 1/2-1/2");
        
        if(!result.equals("1-0") && !result.equals("0-1") && !result.equals("1/2-1/2") && !result.equals("*"))
            throw new IllegalArgumentException("Game result invalid; must be one of \"1-0\", \"0-1\", \"1/2-1/2\", or \"*\"");

        return correctedMoves;
    }
}
