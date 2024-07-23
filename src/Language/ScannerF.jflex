/* 1. Package e importaciones */
package Language;
import java_cup.runtime.Symbol;
import Painter.WordPainter;

@SuppressWarnings("unused")

%%

/* 2. Configuraciones para el analisis (Operaciones y Declaraciones) */
%{
    WordPainter painter;
    public ScannerF(String input, WordPainter painter) {
        yychar = 0;
        this.zzReader = new java.io.BufferedReader(
            new java.io.StringReader(input)
        );
        this.painter = painter;
    }
%}

//Directivas
%class ScannerF
%public
%cupsym TOK
%cup
%char
%full
%unicode

//Constructor
%init{
    yychar = 0;
%init}

//Expresiones regulares
UNUSED   = [ \r\t]+
ID       = [a-zA-Z][a-zA-Z0-9\_]*
CHAR     = \'([^\n\'\\]|\\.)\'
STRING   = \"(([^\n\"\\]|\\.)*)\"
INTEGER  = [0-9]+
DOUBLE   = [0-9]+\.[0-9]+
COMMENTS = "//"([^\r\n]*)
COMMENTM = [/][*][^*]*[*]+([^/*][^*]*[*]+)*[/]

%%

/* 3. Reglas Semanticas */

"de lo contrario"       {return new Symbol(TOK.RW_else,      yychar, yylength(), yytext());}
"con parametros"        {return new Symbol(TOK.RW_params,    yychar, yylength(), yytext());}
"con incremento"        {return new Symbol(TOK.RW_incr,      yychar, yylength(), yytext());}
"con valor"             {return new Symbol(TOK.RW_equ,       yychar, yylength(), yytext());}
"fin mientras"          {return new Symbol(TOK.RW_endwhile,  yychar, yylength(), yytext());}
"fin funcion"           {return new Symbol(TOK.RW_endfunc,   yychar, yylength(), yytext());}
"fin metodo"            {return new Symbol(TOK.RW_endmeth,   yychar, yylength(), yytext());}
"fin segun"             {return new Symbol(TOK.RW_endswitch, yychar, yylength(), yytext());}
"fin para"              {return new Symbol(TOK.RW_endfor,    yychar, yylength(), yytext());}
"fin si"                {return new Symbol(TOK.RW_endif,     yychar, yylength(), yytext());}
"o si"                  {return new Symbol(TOK.RW_elseif,    yychar, yylength(), yytext());}
"imprimir nl"           {return new Symbol(TOK.RW_println,   yychar, yylength(), yytext());}
"cuando"                {return new Symbol(TOK.RW_until,     yychar, yylength(), yytext());}
"hasta"                 {return new Symbol(TOK.RW_to,        yychar, yylength(), yytext());}
"imprimir"              {return new Symbol(TOK.RW_print,     yychar, yylength(), yytext());}
"en caso de ser"        {return new Symbol(TOK.RW_case,      yychar, yylength(), yytext());}
"o"                     {return new Symbol(TOK.RW_or,        yychar, yylength(), yytext());}
"y"                     {return new Symbol(TOK.RW_and,       yychar, yylength(), yytext());}
"no"                    {return new Symbol(TOK.RW_not,       yychar, yylength(), yytext());}
"inicio"                {return new Symbol(TOK.RW_begin,     yychar, yylength(), yytext());}
"inicia"                {return new Symbol(TOK.RW_start,     yychar, yylength(), yytext());}
"fin"                   {return new Symbol(TOK.RW_end,       yychar, yylength(), yytext());}
"ingresar"              {return new Symbol(TOK.RW_insert,    yychar, yylength(), yytext());}
"como"                  {return new Symbol(TOK.RW_as,        yychar, yylength(), yytext());}
"entonces"              {return new Symbol(TOK.RW_then,      yychar, yylength(), yytext());}
"si"                    {return new Symbol(TOK.RW_if,        yychar, yylength(), yytext());}
"segun"                 {return new Symbol(TOK.RW_switch,    yychar, yylength(), yytext());}
"hacer"                 {return new Symbol(TOK.RW_do,        yychar, yylength(), yytext());}
"para"                  {return new Symbol(TOK.RW_for,       yychar, yylength(), yytext());}
"mientras"              {return new Symbol(TOK.RW_while,     yychar, yylength(), yytext());}
"repetir"               {return new Symbol(TOK.RW_repeat,    yychar, yylength(), yytext());}
"retornar"              {return new Symbol(TOK.RW_return,    yychar, yylength(), yytext());}
"regresar"              {return new Symbol(TOK.RW_back,      yychar, yylength(), yytext());}
"metodo"                {return new Symbol(TOK.RW_meth,      yychar, yylength(), yytext());}
"funcion"               {return new Symbol(TOK.RW_func,      yychar, yylength(), yytext());}
"ejecutar"              {return new Symbol(TOK.RW_exec,      yychar, yylength(), yytext());}
"Entero"                {return new Symbol(TOK.RW_int,       yychar, yylength(), yytext());}
"Decimal"               {return new Symbol(TOK.RW_double,    yychar, yylength(), yytext());}
"Cadena"                {return new Symbol(TOK.RW_str,       yychar, yylength(), yytext());}
"Booleano"              {return new Symbol(TOK.RW_bool,      yychar, yylength(), yytext());}
"Caracter"              {return new Symbol(TOK.RW_char,      yychar, yylength(), yytext());}
"potencia"              {return new Symbol(TOK.RW_pow,       yychar, yylength(), yytext());}
"modulo"                {return new Symbol(TOK.RW_mod,       yychar, yylength(), yytext());}
"Verdadero"             {return new Symbol(TOK.RW_true,      yychar, yylength(), yytext());}
"Falso"                 {return new Symbol(TOK.RW_false,     yychar, yylength(), yytext());}
"detener"               {return new Symbol(TOK.RW_break,     yychar, yylength(), yytext());}
"continuar"             {return new Symbol(TOK.RW_continue,  yychar, yylength(), yytext());}
(\<[\s]*\-)             {return new Symbol(TOK.TK_prompt,    yychar, yylength(), yytext());}
"+"                     {return new Symbol(TOK.TK_plus,      yychar, yylength(), yytext());}
"-"                     {return new Symbol(TOK.TK_minus,     yychar, yylength(), yytext());}
"*"                     {return new Symbol(TOK.TK_mult,      yychar, yylength(), yytext());}
"/"                     {return new Symbol(TOK.TK_div,       yychar, yylength(), yytext());}
">="                    {return new Symbol(TOK.RW_moreequ,   yychar, yylength(), yytext());}
"<="                    {return new Symbol(TOK.RW_lessequ,   yychar, yylength(), yytext());}
">"                     {return new Symbol(TOK.RW_more,      yychar, yylength(), yytext());}
"<"                     {return new Symbol(TOK.RW_less,      yychar, yylength(), yytext());}
"="                     {return new Symbol(TOK.RW_equequ,    yychar, yylength(), yytext());}
"!="                    {return new Symbol(TOK.RW_notequ,    yychar, yylength(), yytext());}
"("                     {return new Symbol(TOK.TK_lpar,      yychar, yylength(), yytext());}
")"                     {return new Symbol(TOK.TK_rpar,      yychar, yylength(), yytext());}
","                     {return new Symbol(TOK.TK_comma,     yychar, yylength(), yytext());}
{ID}                    {return new Symbol(TOK.TK_id,        yychar, yylength(), yytext());}
{INTEGER}               {return new Symbol(TOK.TK_int,       yychar, yylength(), yytext());}
{DOUBLE}                {return new Symbol(TOK.TK_double,    yychar, yylength(), yytext());}
{STRING}                {return new Symbol(TOK.TK_str,       yychar, yylength(), yytext());}
"'${"[0-9]+"}'"         {return new Symbol(TOK.TK_char,      yychar, yylength(), yytext());}
{CHAR}                  {return new Symbol(TOK.TK_char,      yychar, yylength(), yytext());}
\n                      {}
{UNUSED}                {}
{COMMENTS}              {painter.COMMENT(yychar,yylength());}
{COMMENTM}              {painter.COMMENT(yychar,yylength());}
.                       {painter.ERROR(yychar,yylength());}