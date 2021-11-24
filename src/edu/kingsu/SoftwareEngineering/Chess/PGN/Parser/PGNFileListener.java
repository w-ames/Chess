// Generated from /users/ugrad/wames/Documents/CMPT320/Chess/chess/src/edu/kingsu/SoftwareEngineering/Chess/PGN/Parser/PGNFile.g4 by ANTLR 4.9.2
package edu.kingsu.SoftwareEngineering.Chess.PGN.Parser;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link PGNFileParser}.
 */
public interface PGNFileListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link PGNFileParser#parse}.
	 * @param ctx the parse tree
	 */
	void enterParse(PGNFileParser.ParseContext ctx);
	/**
	 * Exit a parse tree produced by {@link PGNFileParser#parse}.
	 * @param ctx the parse tree
	 */
	void exitParse(PGNFileParser.ParseContext ctx);
	/**
	 * Enter a parse tree produced by {@link PGNFileParser#pgn_database}.
	 * @param ctx the parse tree
	 */
	void enterPgn_database(PGNFileParser.Pgn_databaseContext ctx);
	/**
	 * Exit a parse tree produced by {@link PGNFileParser#pgn_database}.
	 * @param ctx the parse tree
	 */
	void exitPgn_database(PGNFileParser.Pgn_databaseContext ctx);
	/**
	 * Enter a parse tree produced by {@link PGNFileParser#pgn_game}.
	 * @param ctx the parse tree
	 */
	void enterPgn_game(PGNFileParser.Pgn_gameContext ctx);
	/**
	 * Exit a parse tree produced by {@link PGNFileParser#pgn_game}.
	 * @param ctx the parse tree
	 */
	void exitPgn_game(PGNFileParser.Pgn_gameContext ctx);
	/**
	 * Enter a parse tree produced by {@link PGNFileParser#tag_section}.
	 * @param ctx the parse tree
	 */
	void enterTag_section(PGNFileParser.Tag_sectionContext ctx);
	/**
	 * Exit a parse tree produced by {@link PGNFileParser#tag_section}.
	 * @param ctx the parse tree
	 */
	void exitTag_section(PGNFileParser.Tag_sectionContext ctx);
	/**
	 * Enter a parse tree produced by {@link PGNFileParser#tag_pair}.
	 * @param ctx the parse tree
	 */
	void enterTag_pair(PGNFileParser.Tag_pairContext ctx);
	/**
	 * Exit a parse tree produced by {@link PGNFileParser#tag_pair}.
	 * @param ctx the parse tree
	 */
	void exitTag_pair(PGNFileParser.Tag_pairContext ctx);
	/**
	 * Enter a parse tree produced by {@link PGNFileParser#tag_name}.
	 * @param ctx the parse tree
	 */
	void enterTag_name(PGNFileParser.Tag_nameContext ctx);
	/**
	 * Exit a parse tree produced by {@link PGNFileParser#tag_name}.
	 * @param ctx the parse tree
	 */
	void exitTag_name(PGNFileParser.Tag_nameContext ctx);
	/**
	 * Enter a parse tree produced by {@link PGNFileParser#tag_value}.
	 * @param ctx the parse tree
	 */
	void enterTag_value(PGNFileParser.Tag_valueContext ctx);
	/**
	 * Exit a parse tree produced by {@link PGNFileParser#tag_value}.
	 * @param ctx the parse tree
	 */
	void exitTag_value(PGNFileParser.Tag_valueContext ctx);
	/**
	 * Enter a parse tree produced by {@link PGNFileParser#movetext_section}.
	 * @param ctx the parse tree
	 */
	void enterMovetext_section(PGNFileParser.Movetext_sectionContext ctx);
	/**
	 * Exit a parse tree produced by {@link PGNFileParser#movetext_section}.
	 * @param ctx the parse tree
	 */
	void exitMovetext_section(PGNFileParser.Movetext_sectionContext ctx);
	/**
	 * Enter a parse tree produced by {@link PGNFileParser#element_sequence}.
	 * @param ctx the parse tree
	 */
	void enterElement_sequence(PGNFileParser.Element_sequenceContext ctx);
	/**
	 * Exit a parse tree produced by {@link PGNFileParser#element_sequence}.
	 * @param ctx the parse tree
	 */
	void exitElement_sequence(PGNFileParser.Element_sequenceContext ctx);
	/**
	 * Enter a parse tree produced by {@link PGNFileParser#element}.
	 * @param ctx the parse tree
	 */
	void enterElement(PGNFileParser.ElementContext ctx);
	/**
	 * Exit a parse tree produced by {@link PGNFileParser#element}.
	 * @param ctx the parse tree
	 */
	void exitElement(PGNFileParser.ElementContext ctx);
	/**
	 * Enter a parse tree produced by {@link PGNFileParser#move_number_indication}.
	 * @param ctx the parse tree
	 */
	void enterMove_number_indication(PGNFileParser.Move_number_indicationContext ctx);
	/**
	 * Exit a parse tree produced by {@link PGNFileParser#move_number_indication}.
	 * @param ctx the parse tree
	 */
	void exitMove_number_indication(PGNFileParser.Move_number_indicationContext ctx);
	/**
	 * Enter a parse tree produced by {@link PGNFileParser#san_move}.
	 * @param ctx the parse tree
	 */
	void enterSan_move(PGNFileParser.San_moveContext ctx);
	/**
	 * Exit a parse tree produced by {@link PGNFileParser#san_move}.
	 * @param ctx the parse tree
	 */
	void exitSan_move(PGNFileParser.San_moveContext ctx);
	/**
	 * Enter a parse tree produced by {@link PGNFileParser#recursive_variation}.
	 * @param ctx the parse tree
	 */
	void enterRecursive_variation(PGNFileParser.Recursive_variationContext ctx);
	/**
	 * Exit a parse tree produced by {@link PGNFileParser#recursive_variation}.
	 * @param ctx the parse tree
	 */
	void exitRecursive_variation(PGNFileParser.Recursive_variationContext ctx);
	/**
	 * Enter a parse tree produced by {@link PGNFileParser#game_termination}.
	 * @param ctx the parse tree
	 */
	void enterGame_termination(PGNFileParser.Game_terminationContext ctx);
	/**
	 * Exit a parse tree produced by {@link PGNFileParser#game_termination}.
	 * @param ctx the parse tree
	 */
	void exitGame_termination(PGNFileParser.Game_terminationContext ctx);
}