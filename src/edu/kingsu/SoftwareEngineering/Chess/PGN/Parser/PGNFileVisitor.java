// Generated from /users/ugrad/wames/Documents/CMPT320/Chess/chess/src/edu/kingsu/SoftwareEngineering/Chess/PGN/Parser/PGNFile.g4 by ANTLR 4.9.2
package edu.kingsu.SoftwareEngineering.Chess.PGN.Parser;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link PGNFileParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface PGNFileVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link PGNFileParser#parse}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParse(PGNFileParser.ParseContext ctx);
	/**
	 * Visit a parse tree produced by {@link PGNFileParser#pgn_database}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPgn_database(PGNFileParser.Pgn_databaseContext ctx);
	/**
	 * Visit a parse tree produced by {@link PGNFileParser#pgn_game}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPgn_game(PGNFileParser.Pgn_gameContext ctx);
	/**
	 * Visit a parse tree produced by {@link PGNFileParser#tag_section}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTag_section(PGNFileParser.Tag_sectionContext ctx);
	/**
	 * Visit a parse tree produced by {@link PGNFileParser#tag_pair}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTag_pair(PGNFileParser.Tag_pairContext ctx);
	/**
	 * Visit a parse tree produced by {@link PGNFileParser#tag_name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTag_name(PGNFileParser.Tag_nameContext ctx);
	/**
	 * Visit a parse tree produced by {@link PGNFileParser#tag_value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTag_value(PGNFileParser.Tag_valueContext ctx);
	/**
	 * Visit a parse tree produced by {@link PGNFileParser#movetext_section}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMovetext_section(PGNFileParser.Movetext_sectionContext ctx);
	/**
	 * Visit a parse tree produced by {@link PGNFileParser#element_sequence}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitElement_sequence(PGNFileParser.Element_sequenceContext ctx);
	/**
	 * Visit a parse tree produced by {@link PGNFileParser#element}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitElement(PGNFileParser.ElementContext ctx);
	/**
	 * Visit a parse tree produced by {@link PGNFileParser#move_number_indication}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMove_number_indication(PGNFileParser.Move_number_indicationContext ctx);
	/**
	 * Visit a parse tree produced by {@link PGNFileParser#san_move}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSan_move(PGNFileParser.San_moveContext ctx);
	/**
	 * Visit a parse tree produced by {@link PGNFileParser#recursive_variation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRecursive_variation(PGNFileParser.Recursive_variationContext ctx);
	/**
	 * Visit a parse tree produced by {@link PGNFileParser#game_termination}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGame_termination(PGNFileParser.Game_terminationContext ctx);
}