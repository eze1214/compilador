package analisislexico;
import common.Terminal;
/* Sección de declaraciones de JFlex */
%%
%public
%class AnalizadorLexico
%type Terminal //El tipo de objetos que devuelve
%function escanear  
%unicode
%ignorecase
%line
%column
identificador = [A-Za-z] ([A-Za-z] | [0-9])*
numero = 0 | [1-9][0-9]*
cadenaLiteral = \' [^']* \'
lineTerminator = \r|\n|\r\n
whiteSpace = {lineTerminator} | [ \t\f]

%{
    private Terminal t;

    public String getTexto() {
        return yytext();
    }

    public Terminal getT(){
        return t;
    }

    public Integer getLine(){
        return yyline;
    }
%}

%%
{lineTerminator} {}
{whiteSpace} {}

"(" {t = Terminal.ABRE_PARENTESIS; return t;}
")" {t = Terminal.CIERRA_PARENTESIS; return t;}
"+" {t = Terminal.MAS; return t;}
"-" {t = Terminal.MENOS; return t;}
"*" {t = Terminal.POR; return t;}
"/" {t = Terminal.DIVIDIDO; return t;}
"=" {t = Terminal.IGUAL; return t;}
">" {t  = Terminal.MAYOR; return t;}
"<" {t = Terminal.MENOR; return t;}
"<=" {t = Terminal.MENOR_IGUAL; return t;}
">=" {t = Terminal.MAYOR_IGUAL; return t;}
"<>" {t = Terminal.DISTINTO; return t;}
":=" {t = Terminal.ASIGNACION; return t;}
";" {t = Terminal.PUNTO_COMA; return t;}
"," {t = Terminal.COMA; return t;}
"." {t = Terminal.PUNTO; return t;}
"VAR" {t = Terminal.VAR; return t;}
"if" {t = Terminal.IF; return t;}
"while" {t = Terminal.WHILE; return t;}
"CONST" {t = Terminal.CONST; return t;}
"begin" {t = Terminal.BEGIN; return t;}
"end" {t = Terminal.END; return t;}
"odd" {t = Terminal.ODD; return t;}
"procedure" {t = Terminal.PROCEDURE; return t;}
 "call" {t = Terminal.CALL; return t;}
"then" {t = Terminal.THEN; return t;}
"do" {t = Terminal.DO; return t;}
"readln" {t = Terminal.READLN; return t;}
"writeln" {t = Terminal.WRITELN; return t;}
"write" {t = Terminal.WRITE; return t;} 
{identificador} {t = Terminal.IDENT; return t;}
{numero} {
    t = Terminal.NUMERO; return t;
}

{cadenaLiteral} {
    t = Terminal.CADENA_TEXTO; return t;
}

[^] {t = Terminal.NULO; return t;}

<<EOF>> {t = Terminal.EOF; return t;}