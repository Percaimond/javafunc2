grammar Task3;

/*
 * Lexer Rules
 * Rules to specify the tokens of the language.
 */

A: 'a';
B: 'b';
C: 'c';

WS: [ \t\r\n]+ -> skip;


/*
 * Parser Rules
 * Rules that specify the grammar of the language.
 */

compilationUnit:
	start;
 
/*
 * epsilon can be matched directly in the parser section
 * https://stackoverflow.com/a/5522603
 */

// DON'T MODIFY ABOVE THIS LINE

// TODO: insert rules here
start:LEFTPART|RIGHTPART|;
LEFTPART:A X_ B B B;
fragment X_:A X_ B B B|;
RIGHTPART:A Y_ B B B C;
fragment Y_:(A Y_ B B B C)|;

