/* 1. Package e importaciones */
package Language;
import java_cup.runtime.Symbol;
import Classes.Utils.TypeError;
import Classes.Utils.Outs;

@SuppressWarnings("unused")

%%

/* 2. Configuraciones para el analisis (Operaciones y Declaraciones) */
%{
    public Scanner(String input) {
        yyline = 1;
        yychar = 1;
        this.zzReader = new java.io.BufferedReader(
            new java.io.StringReader(input)
        );
    }
%}

//Directivas
%class Scanner
%public
%cupsym TOK
%cup
%char
%full
%line
%unicode

//Constructor
%init{
    yyline = 1;
    yychar = 1;
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

"de lo contrario"       {return new Symbol(TOK.RW_else,       yyline, yychar, yytext());}
"con parametros"        {return new Symbol(TOK.RW_params,     yyline, yychar, yytext());}
"con incremento"        {return new Symbol(TOK.RW_incr,       yyline, yychar, yytext());}
"con valor"             {return new Symbol(TOK.RW_equ,        yyline, yychar, yytext());}
"fin mientras"          {return new Symbol(TOK.RW_endwhile,   yyline, yychar, yytext());}
"fin funcion"           {return new Symbol(TOK.RW_endfunc,    yyline, yychar, yytext());}
"fin metodo"            {return new Symbol(TOK.RW_endmeth,    yyline, yychar, yytext());}
"fin segun"             {return new Symbol(TOK.RW_endswitch,  yyline, yychar, yytext());}
"fin para"              {return new Symbol(TOK.RW_endfor,     yyline, yychar, yytext());}
"fin si"                {return new Symbol(TOK.RW_endif,      yyline, yychar, yytext());}
"o si"                  {return new Symbol(TOK.RW_elseif,     yyline, yychar, yytext());}
"imprimir nl"           {return new Symbol(TOK.RW_println,    yyline, yychar, yytext());}
"mayor igual que"       {return new Symbol(TOK.RW_moreequ,    yyline, yychar, yytext());}
"menor igual que"       {return new Symbol(TOK.RW_lessequ,    yyline, yychar, yytext());}
"cuando"                {return new Symbol(TOK.RW_until,      yyline, yychar, yytext());}
"hasta"                 {return new Symbol(TOK.RW_to,         yyline, yychar, yytext());}
"imprimir"              {return new Symbol(TOK.RW_print,      yyline, yychar, yytext());}
"mayor que"             {return new Symbol(TOK.RW_more,       yyline, yychar, yytext());}
"menor que"             {return new Symbol(TOK.RW_less,       yyline, yychar, yytext());}
"igual a"               {return new Symbol(TOK.RW_equequ,     yyline, yychar, yytext());}
"diferente de"          {return new Symbol(TOK.RW_notequ,     yyline, yychar, yytext());}
"en caso de ser"        {return new Symbol(TOK.RW_case,       yyline, yychar, yytext());}
"o"                     {return new Symbol(TOK.RW_or,         yyline, yychar, yytext());}
"y"                     {return new Symbol(TOK.RW_and,        yyline, yychar, yytext());}
"no"                    {return new Symbol(TOK.RW_not,        yyline, yychar, yytext());}
"inicio"                {return new Symbol(TOK.RW_begin,      yyline, yychar, yytext());}
"inicia"                {return new Symbol(TOK.RW_start,      yyline, yychar, yytext());}
"fin"                   {return new Symbol(TOK.RW_end,        yyline, yychar, yytext());}
"ingresar"              {return new Symbol(TOK.RW_insert,     yyline, yychar, yytext());}
"como"                  {return new Symbol(TOK.RW_as,         yyline, yychar, yytext());}
"entonces"              {return new Symbol(TOK.RW_then,       yyline, yychar, yytext());}
"si"                    {return new Symbol(TOK.RW_if,         yyline, yychar, yytext());}
"segun"                 {return new Symbol(TOK.RW_switch,     yyline, yychar, yytext());}
"hacer"                 {return new Symbol(TOK.RW_do,         yyline, yychar, yytext());}
"para"                  {return new Symbol(TOK.RW_for,        yyline, yychar, yytext());}
"mientras"              {return new Symbol(TOK.RW_while,      yyline, yychar, yytext());}
"repetir"               {return new Symbol(TOK.RW_repeat,     yyline, yychar, yytext());}
"retornar"              {return new Symbol(TOK.RW_return,     yyline, yychar, yytext());}
"regresar"              {return new Symbol(TOK.RW_back,       yyline, yychar, yytext());}
"metodo"                {return new Symbol(TOK.RW_meth,       yyline, yychar, yytext());}
"funcion"               {return new Symbol(TOK.RW_func,       yyline, yychar, yytext());}
"ejecutar"              {return new Symbol(TOK.RW_exec,       yyline, yychar, yytext());}
"Entero"                {return new Symbol(TOK.RW_int,        yyline, yychar, yytext());}
"Decimal"               {return new Symbol(TOK.RW_double,     yyline, yychar, yytext());}
"Cadena"                {return new Symbol(TOK.RW_str,        yyline, yychar, yytext());}
"Booleano"              {return new Symbol(TOK.RW_bool,       yyline, yychar, yytext());}
"Caracter"              {return new Symbol(TOK.RW_char,       yyline, yychar, yytext());}
"potencia"              {return new Symbol(TOK.RW_pow,        yyline, yychar, yytext());}
"modulo"                {return new Symbol(TOK.RW_mod,        yyline, yychar, yytext());}
"Verdadero"             {return new Symbol(TOK.RW_true,       yyline, yychar, yytext());}
"Falso"                 {return new Symbol(TOK.RW_false,      yyline, yychar, yytext());}
"detener"               {return new Symbol(TOK.RW_break,      yyline, yychar, yytext());}
"continuar"             {return new Symbol(TOK.RW_continue,   yyline, yychar, yytext());}
(\<[\s]*\-)             {return new Symbol(TOK.TK_prompt,     yyline, yychar, yytext());}
"+"                     {return new Symbol(TOK.TK_plus,       yyline, yychar, yytext());}
"-"                     {return new Symbol(TOK.TK_minus,      yyline, yychar, yytext());}
"*"                     {return new Symbol(TOK.TK_mult,       yyline, yychar, yytext());}
"/"                     {return new Symbol(TOK.TK_div,        yyline, yychar, yytext());}
"("                     {return new Symbol(TOK.TK_lpar,       yyline, yychar, yytext());}
")"                     {return new Symbol(TOK.TK_rpar,       yyline, yychar, yytext());}
","                     {return new Symbol(TOK.TK_comma,      yyline, yychar, yytext());}
{ID}                    {return new Symbol(TOK.TK_id,         yyline, yychar, yytext());}
{INTEGER}               {return new Symbol(TOK.TK_int,        yyline, yychar, yytext());}
{DOUBLE}                {return new Symbol(TOK.TK_double,     yyline, yychar, yytext());}
{STRING}                {return new Symbol(TOK.TK_str,        yyline, yychar, yytext().substring(1, yytext().toString().length() - 1));}
"'${"[0-9]+"}'"         {return new Symbol(TOK.TK_char,       yyline, yychar, yytext().substring(1, yytext().toString().length() - 1));}
{CHAR}                  {return new Symbol(TOK.TK_char,       yyline, yychar, yytext().substring(1, yytext().toString().length() - 1));}
\n                      {yychar = 1;}
{UNUSED}                {}
{COMMENTS}              {}
{COMMENTM}              {}
.                       {Outs.errors.add(Outs.newError(yyline, yychar, TypeError.LEXICAL, "Caracter no reconocido. «" + yytext() + "»"));}