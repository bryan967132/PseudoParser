/* 1. Package e importaciones */
package Language;
import java_cup.runtime.Symbol;
import java.util.ArrayList;
import Painter.WordPainter;
import Components.ErrorL;

%%

/* 2. Configuraciones para el analisis (Operaciones y Declaraciones) */
%{
    WordPainter painter;
    public ScannerF(java.io.Reader in,WordPainter painter) {
        yyline = 0;
        yychar = 0;
        this.zzReader = in;
        this.painter = painter;
    }
    ArrayList<ErrorL> errors = new ArrayList<>();
    public ArrayList<ErrorL> getErrors() {
        return errors;
    }
    void addError(int line,int column,String character) {
        errors.add(new ErrorL(line,column,character));
    }
%}

//Directivas
%class ScannerF
%public
%cupsym Sym
%cup
%char
%column
%full
%line
%unicode

//Constructor
%init{
    yyline = 1;
    yychar = 1;
%init}

//Expresiones regulares
UNUSED=[ \r\t]+
ID = [a-zA-Z][a-zA-Z0-9\_]*
NUMBER = [0-9]+(\.[0-9]+)?
STRING = \"(([^\"\\]?|\\.)*)\"
CHAR = \'(([^\"\\]?|\\.))\'
COMMENTS = "//"([^\r\n]*)
COMMENTM = [/][*][^*]*[*]+([^/*][^*]*[*]+)*[/]

%%

/* 3. Reglas Semanticas */

"de lo contrario"       {return new Symbol(Sym.RW_else,      yychar, yylength(), yytext());}
"con parametros"        {return new Symbol(Sym.RW_params,    yychar, yylength(), yytext());}
"con incremento"        {return new Symbol(Sym.RW_incr,      yychar, yylength(), yytext());}
"con valor"             {return new Symbol(Sym.RW_equ,       yychar, yylength(), yytext());}
"fin mientras"          {return new Symbol(Sym.RW_endwhile,  yychar, yylength(), yytext());}
"fin funcion"           {return new Symbol(Sym.RW_endfunc,   yychar, yylength(), yytext());}
"fin metodo"            {return new Symbol(Sym.RW_endmeth,   yychar, yylength(), yytext());}
"fin segun"             {return new Symbol(Sym.RW_endswitch, yychar, yylength(), yytext());}
"fin para"              {return new Symbol(Sym.RW_endfor,    yychar, yylength(), yytext());}
"fin si"                {return new Symbol(Sym.RW_endif,     yychar, yylength(), yytext());}
"o si"                  {return new Symbol(Sym.RW_elseif,    yychar, yylength(), yytext());}
"imprimir nl"           {return new Symbol(Sym.RW_println,   yychar, yylength(), yytext());}
"mayor igual que"       {return new Symbol(Sym.RW_moreequ,   yychar, yylength(), yytext());}
"menor igual que"       {return new Symbol(Sym.RW_lessequ,   yychar, yylength(), yytext());}
"cuando"                {return new Symbol(Sym.RW_until,     yychar, yylength(), yytext());}
"hasta"                 {return new Symbol(Sym.RW_to,        yychar, yylength(), yytext());}
"imprimir"              {return new Symbol(Sym.RW_print,     yychar, yylength(), yytext());}
"mayor que"             {return new Symbol(Sym.RW_more,      yychar, yylength(), yytext());}
"menor que"             {return new Symbol(Sym.RW_less,      yychar, yylength(), yytext());}
"igual a"               {return new Symbol(Sym.RW_equequ,    yychar, yylength(), yytext());}
"diferente de"          {return new Symbol(Sym.RW_notequ,    yychar, yylength(), yytext());}
"en caso de ser"        {return new Symbol(Sym.RW_case,      yychar, yylength(), yytext());}
"o"                     {return new Symbol(Sym.RW_or,        yychar, yylength(), yytext());}
"y"                     {return new Symbol(Sym.RW_and,       yychar, yylength(), yytext());}
"no"                    {return new Symbol(Sym.RW_not,       yychar, yylength(), yytext());}
"inicio"                {return new Symbol(Sym.RW_begin,     yychar, yylength(), yytext());}
"inicia"                {return new Symbol(Sym.RW_start,     yychar, yylength(), yytext());}
"fin"                   {return new Symbol(Sym.RW_end,       yychar, yylength(), yytext());}
"ingresar"              {return new Symbol(Sym.RW_insert,    yychar, yylength(), yytext());}
"como"                  {return new Symbol(Sym.RW_as,        yychar, yylength(), yytext());}
"entonces"              {return new Symbol(Sym.RW_then,      yychar, yylength(), yytext());}
"si"                    {return new Symbol(Sym.RW_if,        yychar, yylength(), yytext());}
"segun"                 {return new Symbol(Sym.RW_switch,    yychar, yylength(), yytext());}
"hacer"                 {return new Symbol(Sym.RW_do,        yychar, yylength(), yytext());}
"para"                  {return new Symbol(Sym.RW_for,       yychar, yylength(), yytext());}
"mientras"              {return new Symbol(Sym.RW_while,     yychar, yylength(), yytext());}
"repetir"               {return new Symbol(Sym.RW_repeat,    yychar, yylength(), yytext());}
"retornar"              {return new Symbol(Sym.RW_return,    yychar, yylength(), yytext());}
"metodo"                {return new Symbol(Sym.RW_meth,      yychar, yylength(), yytext());}
"funcion"               {return new Symbol(Sym.RW_func,      yychar, yylength(), yytext());}
"ejecutar"              {return new Symbol(Sym.RW_exec,      yychar, yylength(), yytext());}
"Numero"                {return new Symbol(Sym.RW_num,       yychar, yylength(), yytext());}
"Cadena"                {return new Symbol(Sym.RW_str,       yychar, yylength(), yytext());}
"Booleano"              {return new Symbol(Sym.RW_bool,      yychar, yylength(), yytext());}
"Caracter"              {return new Symbol(Sym.RW_char,      yychar, yylength(), yytext());}
"potencia"              {return new Symbol(Sym.RW_pow,       yychar, yylength(), yytext());}
"modulo"                {return new Symbol(Sym.RW_mod,       yychar, yylength(), yytext());}
"Verdadero"             {return new Symbol(Sym.RW_true,      yychar, yylength(), yytext());}
"Falso"                 {return new Symbol(Sym.RW_false,     yychar, yylength(), yytext());}
"detener"               {return new Symbol(Sym.RW_break,     yychar, yylength(), yytext());}
(\-[\s]*\>)             {return new Symbol(Sym.TK_prompt,    yychar, yylength(), yytext());}
"+"                     {return new Symbol(Sym.TK_plus,      yychar, yylength(), yytext());}
"-"                     {return new Symbol(Sym.TK_minus,     yychar, yylength(), yytext());}
"*"                     {return new Symbol(Sym.TK_mult,      yychar, yylength(), yytext());}
"/"                     {return new Symbol(Sym.TK_div,       yychar, yylength(), yytext());}
"("                     {return new Symbol(Sym.TK_lpar,      yychar, yylength(), yytext());}
")"                     {return new Symbol(Sym.TK_rpar,      yychar, yylength(), yytext());}
","                     {return new Symbol(Sym.TK_comma,     yychar, yylength(), yytext());}
{ID}                    {return new Symbol(Sym.TK_id,        yychar, yylength(), yytext());}
{NUMBER}                {return new Symbol(Sym.TK_num,       yychar, yylength(), yytext());}
{STRING}                {return new Symbol(Sym.TK_str,       yychar, yylength(), yytext());}
"'${"[0-9]+"}'"         {return new Symbol(Sym.TK_char,      yychar, yylength(), yytext());}
{CHAR}                  {return new Symbol(Sym.TK_char,      yychar, yylength(), yytext());}
\n                      {}
{UNUSED}                {}
{COMMENTS}              {painter.COMMENT(yychar,yylength());}
{COMMENTM}              {painter.COMMENT(yychar,yylength());}
.                       {painter.ERROR(yychar,yylength());}