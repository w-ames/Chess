package edu.kingsu.SoftwareEngineering.Chess.PGN;

import org.apache.commons.lang3.StringEscapeUtils;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;

import edu.kingsu.SoftwareEngineering.Chess.Model.*;
import edu.kingsu.SoftwareEngineering.Chess.PGN.Parser.*;

public class PGNFileEvalListener extends PGNFileBaseListener {

    private ChessGame validationGame;
    private List<PGNFile> parsedFiles;
    private List<Integer> unparseableFileIndices;
    private PGNFile currentFile;
    private int currentFileIndex;
    private boolean currentFileInvalid;
    private String currentTagKey;
    private String currentTagVal;
    private int currentMoveNo;
    private int ignoringBranch;
    private int expectedMoves;

    public PGNFileEvalListener() {
        parsedFiles  = new ArrayList<PGNFile>();
        unparseableFileIndices = new ArrayList<Integer>();
        currentFileIndex = 0;
        ignoringBranch = 0;
    }

	@Override
    public void exitTag_section(PGNFileParser.Tag_sectionContext ctx) {
        // TODO make sure tags are there
    }

	@Override
    public void enterPgn_game(PGNFileParser.Pgn_gameContext ctx) {
        validationGame = new ChessGame(-1, -1, -1, -1);
        currentFile = new PGNFile();
        currentFileInvalid = false;
    }

	@Override
    public void exitPgn_game(PGNFileParser.Pgn_gameContext ctx) {
        if (currentFileInvalid) {
            unparseableFileIndices.add(currentFileIndex);
        } else {
            parsedFiles.add(currentFile);
        }
        currentFileIndex++;
    }

	@Override
    public void exitTag_pair(PGNFileParser.Tag_pairContext ctx) {
        currentFile.getTagPairMap().put(currentTagKey, currentTagVal);
    }

	@Override
    public void enterTag_name(PGNFileParser.Tag_nameContext ctx) {
        currentTagKey = ctx.SYMBOL().getText();
    }

	@Override
    public void enterTag_value(PGNFileParser.Tag_valueContext ctx) {
        String tagValStr = ctx.STRING().getText();
        currentTagVal = StringEscapeUtils.unescapeJava(tagValStr.substring(1, tagValStr.length()-1));
    }

	@Override
    public void enterMovetext_section(PGNFileParser.Movetext_sectionContext ctx) {
        ignoringBranch = 0;
        currentMoveNo = 1;
        expectedMoves = 0;
    }

	@Override
    public void enterElement_sequence(PGNFileParser.Element_sequenceContext ctx) {
    }

	@Override
    public void enterElement(PGNFileParser.ElementContext ctx) {
    }

	@Override
    public void enterMove_number_indication(PGNFileParser.Move_number_indicationContext ctx) {
        if (ignoringBranch == 0 && !currentFileInvalid) {
            if (currentMoveNo != Integer.parseInt(ctx.INTEGER().getText()) || expectedMoves > 0) {
                // wrong number or bad ordering
                currentFileInvalid = true;
            } else {
            }
            expectedMoves = 2;
        }
    }

	@Override
    public void exitMove_number_indication(PGNFileParser.Move_number_indicationContext ctx) {
        if (!currentFileInvalid) {
            currentMoveNo++;
        }
    }

	@Override
    public void enterSan_move(PGNFileParser.San_moveContext ctx) {
        if (ignoringBranch == 0 && !currentFileInvalid) {
            if (expectedMoves <= 0) {
                // too many moves before another number
                currentFileInvalid = true;
            } else {
                if (validationGame.performMove(ctx.SYMBOL().getText(), true)) {
                    // grab our version of the notation
                    currentFile.getMoveTextList().add(validationGame.getAlgebraicHistory().get(currentFile.getMoveTextList().size()));
                    expectedMoves--;
                } else {
                    currentFileInvalid = true;
                }
            }
        }
    }

	@Override
    public void enterRecursive_variation(PGNFileParser.Recursive_variationContext ctx) {
        ignoringBranch++;
    }

	@Override
    public void exitRecursive_variation(PGNFileParser.Recursive_variationContext ctx) {
        ignoringBranch--;
    }

	@Override
    public void enterGame_termination(PGNFileParser.Game_terminationContext ctx) {
        // TODO make sure correct game end
        currentFile.setResult(ctx.getText());
    }

    public PGNFile getPGNFile() { return parsedFiles.size()>0 ? parsedFiles.get(0) : null; }
    public PGNFile getPGNFile(int index) { return 0<=index && index<parsedFiles.size() ? parsedFiles.get(index) : null; }
    public int getPGNFileCount() { return parsedFiles.size(); }
    
}
