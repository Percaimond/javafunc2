// Generated from E:\projects\javafunc2\Task3\Task3\src\Task3.g4 by ANTLR 4.9.2
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class Task3Parser extends Parser {
	static { RuntimeMetaData.checkVersion("4.9.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		A=1, B=2, C=3, WS=4;
	public static final int
		RULE_compilationUnit = 0, RULE_start = 1, RULE_leftlanguage = 2, RULE_rightlanguage = 3;
	private static String[] makeRuleNames() {
		return new String[] {
			"compilationUnit", "start", "leftlanguage", "rightlanguage"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'a'", "'b'", "'c'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "A", "B", "C", "WS"
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

	@Override
	public String getGrammarFileName() { return "Task3.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public Task3Parser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class CompilationUnitContext extends ParserRuleContext {
		public StartContext start() {
			return getRuleContext(StartContext.class,0);
		}
		public CompilationUnitContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_compilationUnit; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Task3Listener ) ((Task3Listener)listener).enterCompilationUnit(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Task3Listener ) ((Task3Listener)listener).exitCompilationUnit(this);
		}
	}

	public final CompilationUnitContext compilationUnit() throws RecognitionException {
		CompilationUnitContext _localctx = new CompilationUnitContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_compilationUnit);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(8);
			start();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StartContext extends ParserRuleContext {
		public LeftlanguageContext leftlanguage() {
			return getRuleContext(LeftlanguageContext.class,0);
		}
		public RightlanguageContext rightlanguage() {
			return getRuleContext(RightlanguageContext.class,0);
		}
		public StartContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_start; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Task3Listener ) ((Task3Listener)listener).enterStart(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Task3Listener ) ((Task3Listener)listener).exitStart(this);
		}
	}

	public final StartContext start() throws RecognitionException {
		StartContext _localctx = new StartContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_start);
		try {
			setState(12);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(10);
				leftlanguage();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(11);
				rightlanguage();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LeftlanguageContext extends ParserRuleContext {
		public TerminalNode A() { return getToken(Task3Parser.A, 0); }
		public LeftlanguageContext leftlanguage() {
			return getRuleContext(LeftlanguageContext.class,0);
		}
		public List<TerminalNode> B() { return getTokens(Task3Parser.B); }
		public TerminalNode B(int i) {
			return getToken(Task3Parser.B, i);
		}
		public LeftlanguageContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_leftlanguage; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Task3Listener ) ((Task3Listener)listener).enterLeftlanguage(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Task3Listener ) ((Task3Listener)listener).exitLeftlanguage(this);
		}
	}

	public final LeftlanguageContext leftlanguage() throws RecognitionException {
		LeftlanguageContext _localctx = new LeftlanguageContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_leftlanguage);
		try {
			setState(24);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(14);
				match(A);
				setState(15);
				leftlanguage();
				setState(16);
				match(B);
				setState(17);
				match(B);
				setState(18);
				match(B);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(20);
				match(A);
				setState(21);
				match(B);
				setState(22);
				match(B);
				setState(23);
				match(B);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RightlanguageContext extends ParserRuleContext {
		public TerminalNode A() { return getToken(Task3Parser.A, 0); }
		public RightlanguageContext rightlanguage() {
			return getRuleContext(RightlanguageContext.class,0);
		}
		public List<TerminalNode> B() { return getTokens(Task3Parser.B); }
		public TerminalNode B(int i) {
			return getToken(Task3Parser.B, i);
		}
		public TerminalNode C() { return getToken(Task3Parser.C, 0); }
		public RightlanguageContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_rightlanguage; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Task3Listener ) ((Task3Listener)listener).enterRightlanguage(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Task3Listener ) ((Task3Listener)listener).exitRightlanguage(this);
		}
	}

	public final RightlanguageContext rightlanguage() throws RecognitionException {
		RightlanguageContext _localctx = new RightlanguageContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_rightlanguage);
		try {
			setState(38);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(26);
				match(A);
				setState(27);
				rightlanguage();
				setState(28);
				match(B);
				setState(29);
				match(B);
				setState(30);
				match(B);
				setState(31);
				match(C);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(33);
				match(A);
				setState(34);
				match(B);
				setState(35);
				match(B);
				setState(36);
				match(B);
				setState(37);
				match(C);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\6+\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\3\2\3\2\3\3\3\3\5\3\17\n\3\3\4\3\4\3\4\3\4\3\4\3\4"+
		"\3\4\3\4\3\4\3\4\5\4\33\n\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3"+
		"\5\3\5\5\5)\n\5\3\5\2\2\6\2\4\6\b\2\2\2)\2\n\3\2\2\2\4\16\3\2\2\2\6\32"+
		"\3\2\2\2\b(\3\2\2\2\n\13\5\4\3\2\13\3\3\2\2\2\f\17\5\6\4\2\r\17\5\b\5"+
		"\2\16\f\3\2\2\2\16\r\3\2\2\2\17\5\3\2\2\2\20\21\7\3\2\2\21\22\5\6\4\2"+
		"\22\23\7\4\2\2\23\24\7\4\2\2\24\25\7\4\2\2\25\33\3\2\2\2\26\27\7\3\2\2"+
		"\27\30\7\4\2\2\30\31\7\4\2\2\31\33\7\4\2\2\32\20\3\2\2\2\32\26\3\2\2\2"+
		"\33\7\3\2\2\2\34\35\7\3\2\2\35\36\5\b\5\2\36\37\7\4\2\2\37 \7\4\2\2 !"+
		"\7\4\2\2!\"\7\5\2\2\")\3\2\2\2#$\7\3\2\2$%\7\4\2\2%&\7\4\2\2&\'\7\4\2"+
		"\2\')\7\5\2\2(\34\3\2\2\2(#\3\2\2\2)\t\3\2\2\2\5\16\32(";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}