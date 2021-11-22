// Generated from /users/ugrad/wames/Documents/CMPT320/Chess/chess/src/edu/kingsu/SoftwareEngineering/Chess/PGN/Parser/PGNFile.g4 by ANTLR 4.9.2
package edu.kingsu.SoftwareEngineering.Chess.PGN.Parser;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class PGNFileLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.9.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		WHITE_WINS=1, BLACK_WINS=2, DRAWN_GAME=3, REST_OF_LINE_COMMENT=4, BRACE_COMMENT=5, 
		ESCAPE=6, SPACES=7, STRING=8, INTEGER=9, PERIOD=10, ASTERISK=11, LEFT_BRACKET=12, 
		RIGHT_BRACKET=13, LEFT_PARENTHESIS=14, RIGHT_PARENTHESIS=15, LEFT_ANGLE_BRACKET=16, 
		RIGHT_ANGLE_BRACKET=17, NUMERIC_ANNOTATION_GLYPH=18, SYMBOL=19, SUFFIX_ANNOTATION=20, 
		UNEXPECTED_CHAR=21;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"WHITE_WINS", "BLACK_WINS", "DRAWN_GAME", "REST_OF_LINE_COMMENT", "BRACE_COMMENT", 
			"ESCAPE", "SPACES", "STRING", "INTEGER", "PERIOD", "ASTERISK", "LEFT_BRACKET", 
			"RIGHT_BRACKET", "LEFT_PARENTHESIS", "RIGHT_PARENTHESIS", "LEFT_ANGLE_BRACKET", 
			"RIGHT_ANGLE_BRACKET", "NUMERIC_ANNOTATION_GLYPH", "SYMBOL", "SUFFIX_ANNOTATION", 
			"UNEXPECTED_CHAR"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'1-0'", "'0-1'", "'1/2-1/2'", null, null, null, null, null, null, 
			"'.'", "'*'", "'['", "']'", "'('", "')'", "'<'", "'>'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "WHITE_WINS", "BLACK_WINS", "DRAWN_GAME", "REST_OF_LINE_COMMENT", 
			"BRACE_COMMENT", "ESCAPE", "SPACES", "STRING", "INTEGER", "PERIOD", "ASTERISK", 
			"LEFT_BRACKET", "RIGHT_BRACKET", "LEFT_PARENTHESIS", "RIGHT_PARENTHESIS", 
			"LEFT_ANGLE_BRACKET", "RIGHT_ANGLE_BRACKET", "NUMERIC_ANNOTATION_GLYPH", 
			"SYMBOL", "SUFFIX_ANNOTATION", "UNEXPECTED_CHAR"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


	public PGNFileLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "PGNFile.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	@Override
	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 5:
			return ESCAPE_sempred((RuleContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean ESCAPE_sempred(RuleContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return getCharPositionInLine() == 0;
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\27\u00a6\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\3\2\3\2\3\2\3\2\3\3\3\3"+
		"\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\5\3\5\7\5@\n\5\f\5\16\5C\13"+
		"\5\3\5\3\5\3\6\3\6\7\6I\n\6\f\6\16\6L\13\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7"+
		"\7\7U\n\7\f\7\16\7X\13\7\3\7\3\7\3\b\6\b]\n\b\r\b\16\b^\3\b\3\b\3\t\3"+
		"\t\3\t\3\t\3\t\3\t\7\ti\n\t\f\t\16\tl\13\t\3\t\3\t\3\n\6\nq\n\n\r\n\16"+
		"\nr\3\13\3\13\3\f\3\f\3\r\3\r\3\16\3\16\3\17\3\17\3\20\3\20\3\21\3\21"+
		"\3\22\3\22\3\23\3\23\6\23\u0087\n\23\r\23\16\23\u0088\3\24\3\24\7\24\u008d"+
		"\n\24\f\24\16\24\u0090\13\24\3\24\3\24\3\24\3\24\5\24\u0096\n\24\3\24"+
		"\3\24\5\24\u009a\n\24\3\24\5\24\u009d\n\24\5\24\u009f\n\24\3\25\3\25\5"+
		"\25\u00a3\n\25\3\26\3\26\2\2\27\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23"+
		"\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27\3\2\13"+
		"\4\2\f\f\17\17\3\2\177\177\5\2\13\f\17\17\"\"\4\2$$^^\3\2\62;\5\2\62;"+
		"C\\c|\n\2%%--//\62<??C\\aac|\4\2%%--\4\2##AA\2\u00b4\2\3\3\2\2\2\2\5\3"+
		"\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2"+
		"\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3"+
		"\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'"+
		"\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\3-\3\2\2\2\5\61\3\2\2\2\7\65\3\2\2\2\t"+
		"=\3\2\2\2\13F\3\2\2\2\rQ\3\2\2\2\17\\\3\2\2\2\21b\3\2\2\2\23p\3\2\2\2"+
		"\25t\3\2\2\2\27v\3\2\2\2\31x\3\2\2\2\33z\3\2\2\2\35|\3\2\2\2\37~\3\2\2"+
		"\2!\u0080\3\2\2\2#\u0082\3\2\2\2%\u0084\3\2\2\2\'\u008a\3\2\2\2)\u00a0"+
		"\3\2\2\2+\u00a4\3\2\2\2-.\7\63\2\2./\7/\2\2/\60\7\62\2\2\60\4\3\2\2\2"+
		"\61\62\7\62\2\2\62\63\7/\2\2\63\64\7\63\2\2\64\6\3\2\2\2\65\66\7\63\2"+
		"\2\66\67\7\61\2\2\678\7\64\2\289\7/\2\29:\7\63\2\2:;\7\61\2\2;<\7\64\2"+
		"\2<\b\3\2\2\2=A\7=\2\2>@\n\2\2\2?>\3\2\2\2@C\3\2\2\2A?\3\2\2\2AB\3\2\2"+
		"\2BD\3\2\2\2CA\3\2\2\2DE\b\5\2\2E\n\3\2\2\2FJ\7}\2\2GI\n\3\2\2HG\3\2\2"+
		"\2IL\3\2\2\2JH\3\2\2\2JK\3\2\2\2KM\3\2\2\2LJ\3\2\2\2MN\7\177\2\2NO\3\2"+
		"\2\2OP\b\6\2\2P\f\3\2\2\2QR\6\7\2\2RV\7\'\2\2SU\n\2\2\2TS\3\2\2\2UX\3"+
		"\2\2\2VT\3\2\2\2VW\3\2\2\2WY\3\2\2\2XV\3\2\2\2YZ\b\7\2\2Z\16\3\2\2\2["+
		"]\t\4\2\2\\[\3\2\2\2]^\3\2\2\2^\\\3\2\2\2^_\3\2\2\2_`\3\2\2\2`a\b\b\2"+
		"\2a\20\3\2\2\2bj\7$\2\2cd\7^\2\2di\7^\2\2ef\7^\2\2fi\7$\2\2gi\n\5\2\2"+
		"hc\3\2\2\2he\3\2\2\2hg\3\2\2\2il\3\2\2\2jh\3\2\2\2jk\3\2\2\2km\3\2\2\2"+
		"lj\3\2\2\2mn\7$\2\2n\22\3\2\2\2oq\t\6\2\2po\3\2\2\2qr\3\2\2\2rp\3\2\2"+
		"\2rs\3\2\2\2s\24\3\2\2\2tu\7\60\2\2u\26\3\2\2\2vw\7,\2\2w\30\3\2\2\2x"+
		"y\7]\2\2y\32\3\2\2\2z{\7_\2\2{\34\3\2\2\2|}\7*\2\2}\36\3\2\2\2~\177\7"+
		"+\2\2\177 \3\2\2\2\u0080\u0081\7>\2\2\u0081\"\3\2\2\2\u0082\u0083\7@\2"+
		"\2\u0083$\3\2\2\2\u0084\u0086\7&\2\2\u0085\u0087\t\6\2\2\u0086\u0085\3"+
		"\2\2\2\u0087\u0088\3\2\2\2\u0088\u0086\3\2\2\2\u0088\u0089\3\2\2\2\u0089"+
		"&\3\2\2\2\u008a\u008e\t\7\2\2\u008b\u008d\t\b\2\2\u008c\u008b\3\2\2\2"+
		"\u008d\u0090\3\2\2\2\u008e\u008c\3\2\2\2\u008e\u008f\3\2\2\2\u008f\u009e"+
		"\3\2\2\2\u0090\u008e\3\2\2\2\u0091\u0092\7\"\2\2\u0092\u0093\7g\2\2\u0093"+
		"\u0095\3\2\2\2\u0094\u0096\7\60\2\2\u0095\u0094\3\2\2\2\u0095\u0096\3"+
		"\2\2\2\u0096\u0097\3\2\2\2\u0097\u0099\7r\2\2\u0098\u009a\7\60\2\2\u0099"+
		"\u0098\3\2\2\2\u0099\u009a\3\2\2\2\u009a\u009c\3\2\2\2\u009b\u009d\t\t"+
		"\2\2\u009c\u009b\3\2\2\2\u009c\u009d\3\2\2\2\u009d\u009f\3\2\2\2\u009e"+
		"\u0091\3\2\2\2\u009e\u009f\3\2\2\2\u009f(\3\2\2\2\u00a0\u00a2\t\n\2\2"+
		"\u00a1\u00a3\t\n\2\2\u00a2\u00a1\3\2\2\2\u00a2\u00a3\3\2\2\2\u00a3*\3"+
		"\2\2\2\u00a4\u00a5\13\2\2\2\u00a5,\3\2\2\2\21\2AJV^hjr\u0088\u008e\u0095"+
		"\u0099\u009c\u009e\u00a2\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}