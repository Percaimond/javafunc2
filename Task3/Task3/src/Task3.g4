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

start:X_|Y_;
fragment X_: ('a' X_ 'b' 'b' 'b');
fragment Y_: ('a' Y_ 'b' 'b' 'b' 'c')|;



