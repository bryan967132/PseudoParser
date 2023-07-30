/* 1. Package e importaciones */
package Language;
import java_cup.runtime.Symbol;
import java.util.ArrayList;
import Components.ErrorL;

%%

/* 2. Configuraciones para el analisis (Operaciones y Declaraciones) */
%{
    ArrayList<ErrorL> errors = new ArrayList<>();
    public ArrayList<ErrorL> getErrors() {
        return errors;
    }
    void addError(int line,int column,String character) {
        errors.add(new ErrorL(line,column,character));
    }
%}

//Directivas
%class Scanner
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

"de lo contrario"       {return new Symbol(Sym.RW_else,       yyline, yychar, yytext());}
"con parametros"        {return new Symbol(Sym.RW_params,     yyline, yychar, yytext());}
"con incremento"        {return new Symbol(Sym.RW_incr,       yyline, yychar, yytext());}
"con valor"             {return new Symbol(Sym.RW_equ,        yyline, yychar, yytext());}
"fin mientras"          {return new Symbol(Sym.RW_endwhile,   yyline, yychar, yytext());}
"fin funcion"           {return new Symbol(Sym.RW_endfunc,    yyline, yychar, yytext());}
"fin metodo"            {return new Symbol(Sym.RW_endmeth,    yyline, yychar, yytext());}
"fin segun"             {return new Symbol(Sym.RW_endswitch,  yyline, yychar, yytext());}
"fin para"              {return new Symbol(Sym.RW_endfor,     yyline, yychar, yytext());}
"fin si"                {return new Symbol(Sym.RW_endif,      yyline, yychar, yytext());}
"o si"                  {return new Symbol(Sym.RW_elseif,     yyline, yychar, yytext());}
"imprimir nl"           {return new Symbol(Sym.RW_println,    yyline, yychar, yytext());}
"mayor igual que"       {return new Symbol(Sym.RW_moreequ,    yyline, yychar, yytext());}
"menor igual que"       {return new Symbol(Sym.RW_lessequ,    yyline, yychar, yytext());}
"cuando"                {return new Symbol(Sym.RW_until,      yyline, yychar, yytext());}
"hasta"                 {return new Symbol(Sym.RW_to,         yyline, yychar, yytext());}
"imprimir"              {return new Symbol(Sym.RW_print,      yyline, yychar, yytext());}
"mayor que"             {return new Symbol(Sym.RW_more,       yyline, yychar, yytext());}
"menor que"             {return new Symbol(Sym.RW_less,       yyline, yychar, yytext());}
"igual a"               {return new Symbol(Sym.RW_equequ,     yyline, yychar, yytext());}
"diferente de"          {return new Symbol(Sym.RW_notequ,     yyline, yychar, yytext());}
"en caso de ser"        {return new Symbol(Sym.RW_case,       yyline, yychar, yytext());}
"o"                     {return new Symbol(Sym.RW_or,         yyline, yychar, yytext());}
"y"                     {return new Symbol(Sym.RW_and,        yyline, yychar, yytext());}
"no"                    {return new Symbol(Sym.RW_not,        yyline, yychar, yytext());}
"inicio"                {return new Symbol(Sym.RW_begin,      yyline, yychar, yytext());}
"inicia"                {return new Symbol(Sym.RW_start,      yyline, yychar, yytext());}
"fin"                   {return new Symbol(Sym.RW_end,        yyline, yychar, yytext());}
"ingresar"              {return new Symbol(Sym.RW_insert,     yyline, yychar, yytext());}
"como"                  {return new Symbol(Sym.RW_as,         yyline, yychar, yytext());}
"entonces"              {return new Symbol(Sym.RW_then,       yyline, yychar, yytext());}
"si"                    {return new Symbol(Sym.RW_if,         yyline, yychar, yytext());}
"segun"                 {return new Symbol(Sym.RW_switch,     yyline, yychar, yytext());}
"hacer"                 {return new Symbol(Sym.RW_do,         yyline, yychar, yytext());}
"para"                  {return new Symbol(Sym.RW_for,        yyline, yychar, yytext());}
"mientras"              {return new Symbol(Sym.RW_while,      yyline, yychar, yytext());}
"repetir"               {return new Symbol(Sym.RW_repeat,     yyline, yychar, yytext());}
"retornar"              {return new Symbol(Sym.RW_return,     yyline, yychar, yytext());}
"metodo"                {return new Symbol(Sym.RW_meth,       yyline, yychar, yytext());}
"funcion"               {return new Symbol(Sym.RW_func,       yyline, yychar, yytext());}
"ejecutar"              {return new Symbol(Sym.RW_exec,       yyline, yychar, yytext());}
"Numero"                {return new Symbol(Sym.RW_num,        yyline, yychar, yytext());}
"Cadena"                {return new Symbol(Sym.RW_str,        yyline, yychar, yytext());}
"Booleano"              {return new Symbol(Sym.RW_bool,       yyline, yychar, yytext());}
"Caracter"              {return new Symbol(Sym.RW_char,       yyline, yychar, yytext());}
"potencia"              {return new Symbol(Sym.RW_pow,        yyline, yychar, yytext());}
"modulo"                {return new Symbol(Sym.RW_mod,        yyline, yychar, yytext());}
"Verdadero"             {return new Symbol(Sym.RW_true,       yyline, yychar, yytext());}
"Falso"                 {return new Symbol(Sym.RW_false,      yyline, yychar, yytext());}
"detener"               {return new Symbol(Sym.RW_break,      yyline, yychar, yytext());}
(\<[\s]*\-)             {return new Symbol(Sym.TK_prompt,     yyline, yychar, yytext());}
"+"                     {return new Symbol(Sym.TK_plus,       yyline, yychar, yytext());}
"-"                     {return new Symbol(Sym.TK_minus,      yyline, yychar, yytext());}
"*"                     {return new Symbol(Sym.TK_mult,       yyline, yychar, yytext());}
"/"                     {return new Symbol(Sym.TK_div,        yyline, yychar, yytext());}
"("                     {return new Symbol(Sym.TK_lpar,       yyline, yychar, yytext());}
")"                     {return new Symbol(Sym.TK_rpar,       yyline, yychar, yytext());}
","                     {return new Symbol(Sym.TK_comma,      yyline, yychar, yytext());}
{ID}                    {return new Symbol(Sym.TK_id,         yyline, yychar, yytext());}
{NUMBER}                {return new Symbol(Sym.TK_num,        yyline, yychar, yytext());}
{STRING}                {return new Symbol(Sym.TK_str,        yyline, yychar, yytext().substring(1, yytext().toString().length() - 1));}
"'${"[0-9]+"}'"         {return new Symbol(Sym.TK_char,       yyline, yychar, yytext().substring(1, yytext().toString().length() - 1));}
{CHAR}                  {return new Symbol(Sym.TK_char,       yyline, yychar, yytext().substring(1, yytext().toString().length() - 1));}
\n                      {yychar = 1;}
{UNUSED}                {}
{COMMENTS}              {}
{COMMENTM}              {}
.                       {addError(yyline,yychar,yytext());}