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


/*start : ( leins | lzwei | ' ');

leins : ( A B B B | A leins B B B );

lzwei : ( A lzwei B B B C | A B B B C );*/




start:x
     |y
     |em

     ;

em:' ' ;


x:(A x B B B)
 |(A B B B)
 ;

y:(A y B B B C)
 |(A B B B C)
 ;
