// Generated from E:\projects\javafunc2\Task3\Task3\src\Task3.g4 by ANTLR 4.9.2
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link Task3Parser}.
 */
public interface Task3Listener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link Task3Parser#compilationUnit}.
	 * @param ctx the parse tree
	 */
	void enterCompilationUnit(Task3Parser.CompilationUnitContext ctx);
	/**
	 * Exit a parse tree produced by {@link Task3Parser#compilationUnit}.
	 * @param ctx the parse tree
	 */
	void exitCompilationUnit(Task3Parser.CompilationUnitContext ctx);
	/**
	 * Enter a parse tree produced by {@link Task3Parser#start}.
	 * @param ctx the parse tree
	 */
	void enterStart(Task3Parser.StartContext ctx);
	/**
	 * Exit a parse tree produced by {@link Task3Parser#start}.
	 * @param ctx the parse tree
	 */
	void exitStart(Task3Parser.StartContext ctx);
	/**
	 * Enter a parse tree produced by {@link Task3Parser#leftlanguage}.
	 * @param ctx the parse tree
	 */
	void enterLeftlanguage(Task3Parser.LeftlanguageContext ctx);
	/**
	 * Exit a parse tree produced by {@link Task3Parser#leftlanguage}.
	 * @param ctx the parse tree
	 */
	void exitLeftlanguage(Task3Parser.LeftlanguageContext ctx);
	/**
	 * Enter a parse tree produced by {@link Task3Parser#rightlanguage}.
	 * @param ctx the parse tree
	 */
	void enterRightlanguage(Task3Parser.RightlanguageContext ctx);
	/**
	 * Exit a parse tree produced by {@link Task3Parser#rightlanguage}.
	 * @param ctx the parse tree
	 */
	void exitRightlanguage(Task3Parser.RightlanguageContext ctx);
}