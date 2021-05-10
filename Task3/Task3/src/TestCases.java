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
	
	private class MyParseTreeListener extends Task3BaseListener {
		boolean hasEnconteredParseError = false;

		@Override public void visitErrorNode(ErrorNode node) { 
			//System.out.println("ERROR DURING PARSING");
			this.hasEnconteredParseError = true;
			
		}

	}
	
	
	private void testInput(String input, boolean expectError, int expectedTokens) {
		System.out.println("------------------ TESTING ---------------------");
		System.out.println("Input string:" + input);

		// LEXER CHECK
		Task3Lexer lex = new Task3Lexer(CharStreams.fromString(input));
		MySyntaxErrorListener errorListener = new MySyntaxErrorListener();
		lex.addErrorListener(errorListener);
		List<? extends Token> allTokens = lex.getAllTokens();
		// CHECK SYNTAX ERROR
		boolean failedOnSyntax = errorListener.hasSyntaxError;
		
		// PARSER CHECK - the string should come from a derivation of the grammar you defined
		lex = new Task3Lexer(CharStreams.fromString(input));
		CommonTokenStream tokens = new CommonTokenStream(lex);
		Task3Parser parser = new Task3Parser(tokens);
		MyParseTreeListener listener = new MyParseTreeListener();
		parser.addParseListener(listener);

		Task3Parser.CompilationUnitContext tree = parser.compilationUnit(); // parse the token stream
		
		// CHECK PARSER ERROR
		boolean failedOnParsing = listener.hasEnconteredParseError;
		
		// CHECK NUMBER OF TOKENS
		boolean failedOnNumberTokens = !(expectedTokens == allTokens.size());

		System.out.println("Should this string be accepted? " + expectError);
		System.out.println("Number of tokens found: " +  allTokens.size());
		System.out.println("Failure on syntax: " + failedOnSyntax);
		System.out.println("Failure on parsing: " + failedOnParsing);
		System.out.println("Failure on the number of tokens: " + failedOnNumberTokens);
		
		assertEquals(
				expectError, 
				failedOnSyntax | failedOnParsing | failedOnNumberTokens
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
	public void testSimpleCorrectN1() {
		String input = "abbb";
		testInput(input, false, 4);
	}

	@Test
	public void testAllB() {
		String input = "bbbbb";
		testInput(input, true, 0);
	}
	

	@Test
	public void testAllA() {
		String input = "aaaa";
		testInput(input, true, 0);
	}
	
	@Test
	public void basicLanguageWithC() {
		String input = "abbbc";
		testInput(input, false, 5);
	}
	@Test
	public void emptyTest(){
		String input ="";
		testInput(input, false,0);
	}

}
