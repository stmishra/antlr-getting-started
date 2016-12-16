grammar Calc;

@header {
   package in.gen.berserker.parser;
}
prog:	(expr NEWLINE)* ;
expr:	expr op=('*'|'/') expr  #multOrDiv
    |	expr op=('+'|'-') expr  #plusOrMinus
    |	INT                  #int
    |	'(' expr ')'         #abcd
    ;
NEWLINE : [\r\n]+ ;
INT     : [0-9]+ ;
