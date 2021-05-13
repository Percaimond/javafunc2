import static org.junit.Assert.*;

import java.util.List;

import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.junit.Test;



public class TestCases {

	private class MySyntaxErrorListener extends BaseErrorListener {
		boolean hasSyntaxError = false;

		@Override
		public void syntaxError(Recognizer r, Object symbol, int line, int pos, String msg, RecognitionException e) {
			this.hasSyntaxError = true;
		}

	}

	private class MyParseErrorListener extends BaseErrorListener {
		boolean hasParseError = false;

		@Override
		public void syntaxError(Recognizer r, Object symbol, int line, int pos, String msg, RecognitionException e) {
			System.out.println(msg);
			this.hasParseError = true;
		}
	}

	private class MyParseTreeListener extends Task3BaseListener {
		boolean hasEnconteredParseError = false;

		@Override public void visitErrorNode(ErrorNode node) {
			//System.out.println("ERROR DURING PARSING");
			this.hasEnconteredParseError = true;

		}

	}


	private void testInput(String input, boolean expectError, int expectedTokens) {
		System.out.println("------------------ TESTING ---------------------");
		System.out.println("Input string: " + input);
		System.out.println("Number of tokens expected (by the scanner/lexer): " +  expectedTokens);
		System.out.println("Do we expect the ANTLR produced Scanner/Lexer and/or Parser to throw an error on this test (i.e. the string should be rejected)?\n"
				+ expectError +
				"\nInterpretation:" +
				"\n -true = yes, the string should be rejected; " +
				"\n  the FAILURE section should contain at least a true value." +
				"\n -false = no, the string should be accepted." +
				"\n  the FAILURE section should contain all false values.");
		System.out.println("RESULTS ----------------------------------------");

		// LEXER CHECK
		Task3Lexer lex = new Task3Lexer(CharStreams.fromString(input));
		MySyntaxErrorListener errorListener = new MySyntaxErrorListener();
		lex.addErrorListener(errorListener);
		System.out.println("Scanner/Lexer running ...");
		List<? extends Token> allTokens = lex.getAllTokens();
		// CHECK SYNTAX ERROR
		boolean failedOnSyntax = errorListener.hasSyntaxError;

		// PARSER CHECK - the string should come from a derivation of the grammar you defined
		lex = new Task3Lexer(CharStreams.fromString(input));
		CommonTokenStream tokens = new CommonTokenStream(lex);
		Task3Parser parser = new Task3Parser(tokens);
		MyParseErrorListener myParseErrorListener = new MyParseErrorListener();
		parser.addErrorListener(myParseErrorListener);
		MyParseTreeListener listener = new MyParseTreeListener();
		parser.addParseListener(listener);

		System.out.println("Parser running ...");
		Task3Parser.CompilationUnitContext tree = parser.compilationUnit(); // parse the token stream

		// CHECK PARSER ERROR
		boolean failedOnParsingParseListener = listener.hasEnconteredParseError;
		boolean failedOnParsingErrorListener = myParseErrorListener.hasParseError;

		// CHECK NUMBER OF TOKENS
		boolean failedOnNumberTokens = !(expectedTokens == allTokens.size());

		System.out.println("Number of tokens found (by Lexer/Scanner): " +  allTokens.size());
		System.out.println("FAILURES ---------------------------------------");
		System.out.println("Failure on syntax (lexer scan): " + failedOnSyntax);
		System.out.println("Failure on parsing (check 1 - parse listener): " + failedOnParsingParseListener);
		System.out.println("Failure on parsing (check 2 - errror listener): " + failedOnParsingParseListener);
		System.out.println("Failure on the number of tokens: " + failedOnNumberTokens);

		assertEquals(
				expectError,
				failedOnSyntax | failedOnNumberTokens |
						failedOnParsingParseListener | failedOnParsingErrorListener
		);
	}

	@Test
	public void testAABmistake() {
		String input = "aab";
		testInput(input, true, 3);
	}


	@Test
	public void testRandomTokens() {
		String input = "z#4$";
		testInput(input, true, 0);
	}

	@Test
	public void noCandNisTwo() {
		String input = "aabbbbbb";
		testInput(input, false, 8);
	}

	@Test
	public void longWrongString() {
		String input = "aaabbbcbbbbbb";
		testInput(input, true, 13);
	}

	@Test
	public void testSimpleCorrectN1() {
		String input = "abbb";
		testInput(input, false, 4);
	}

	@Test
	public void testAllB() {
		String input = "bbbbb";
		testInput(input, true, 5);
	}


	@Test
	public void testAllA() {
		String input = "aaaa";
		testInput(input, true, 4);
	}

	@Test
	public void basicLanguageWithC() {
		String input = "abbbc";
		testInput(input, false, 5);
	}
	@Test
	public void emptystringtest(){
		String input = " ";
		testInput(input,false,1);
	}
	/*@Test
	public void emptyempty(){
		String input = " ";
		testInput(input,false,1);
	}
	@Test
	public void emtpyemptyempty(){
		String input = "  ";
		testInput(input, true,0);
	}*/
	@Test
	public void randomlanguage(){
		String input = "aabbbcbbbc";
		testInput(input, false, 10);
	}
	@Test
	public void testonlyCCCC(){
		String input = "cccccc";
		testInput(input, true, 6);
	}
}

