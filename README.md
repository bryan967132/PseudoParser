# PseudoP
## Intérprete de Pseudocódigo: JFlex y CUP  
* Implementa patrón de diseño Interpreter.

## Traductor a Código Go y Python

## VSCode Theme
![Pantalla](Images/ScreenVSCodeTheme.png)

## GitHub Theme
![Pantalla](Images/ScreenGitHubTheme.png)

## Eclipse Theme
![Pantalla](Images/ScreenEclipseTheme.png)

## Gramática Libre del Contexto
```html
<INIT> ::=
    'inicio' TK_id <INSTSGLOBAL> 'fin' |
    'inicio' TK_id 'fin'

<INSTSGLOBAL> ::=
    <INSTSGLOBAL>  <INSTGLOBAL> |
    <INSTGLOBAL>

<INSTGLOBAL> ::=
    <CALLMAINFUNC> |
    <DECLID>       |
    <DECLFUNC>

<INSTRUCTIONS> ::=
    <INSTRUCTIONS> <INSTRUCTION> |
    <INSTRUCTION>

<INSTRUCTION> ::=
    <DECLID>         |
    <IDASIGN>        |
    <IFSTRUCT>       |
    <SWITCHSTRUCT>   |
    <LOOPFOR>        |
    <LOOPWHILE>      |
    <LOOPDOWHILE>    |
    <CALLFUNC>       |
    <PRINT>          |
    'retornar' <EXP> |
    'regresar'       |
    'continuar'      |
    'detener'

<CALLMAINFUNC> ::=
    'inicia' <CALLFUNC>

<DECLID> ::=
    'ingresar' <LISTID> 'como' <TYPE> 'con valor' <EXP>

<IDASIGN> ::=
    <LISTID> '<-' <EXP>

<LISTID> ::=
    <LISTID> ',' TK_id |
    TK_id

<IFSTRUCT> ::=
    'si' <EXP> 'entonces' <ELSEIFSTRUCT> |
    'si' <EXP> 'entonces' <ELSESTRUCT>   |
    'si' <EXP> 'entonces' <INSTIF>

<ELSEIFSTRUCT> ::=
    <INSTRUCTIONS> 'o si' <EXP> 'entonces' <ELSEIFSTRUCT> |
    <INSTRUCTIONS> 'o si' <EXP> 'entonces' <ELSESTRUCT>   |
    <INSTRUCTIONS> 'o si' <EXP> 'entonces' <INSTIF>       |
    'o si' <EXP> 'entonces' <ELSEIFSTRUCT>                |
    'o si' <EXP> 'entonces' <ELSESTRUCT>                  |
    'o si' <EXP> 'entonces' <INSTIF>

<ELSESTRUCT> ::=
    <INSTRUCTIONS> 'de lo contrario' <INSTIF> |
    'de lo contrario' <INSTIF>

<INSTIF> ::=
    <INSTRUCTIONS> 'fin si' |
    'fin si'

<SWITCHSTRUCT> ::=
    'segun' <EXP> 'hacer' <CASESDEFAULT> 'fin segun' |
    'segun' <EXP> 'hacer' 'fin segun'

<CASESDEFAULT> ::=
    <CASES> <DEFAULT> |
    <CASES>           |
    <DEFAULT>

<CASES> ::=
    <CASES> <CASE> |
    <CASE>

<CASE> ::=
    'en caso de ser' <EXP> 'entonces' <INSTRUCTIONS> |
    'en caso de ser' <EXP> 'entonces'

<DEFAULT> ::=
    'de lo contrario' 'entonces' <INSTRUCTIONS> |
    'de lo contrario' 'entonces'

<LOOPFOR> ::=
    'para' TK_id '<-' <EXP> 'hasta' <EXP> 'con incremento' <EXP> 'hacer' <INSTFOR> |
    'para' TK_id '<-' <EXP> 'hasta' <EXP> 'hacer' <INSTFOR>

<LOOPWHILE> ::=
    'mientras' <EXP>  'hacer' <INSTWHILE>

<LOOPDOWHILE> ::=
    <INSTREPEAT> 'cuando' <EXP>

<INSTFOR> ::=
    <INSTRUCTIONS> 'fin para' |
    'fin para'

<INSTWHILE> ::=
    <INSTRUCTIONS> 'fin mientras' |
    'fin mientras'

<INSTREPEAT> ::=
    'repetir' <INSTRUCTIONS> |
    'repetir'

<DECLFUNC> ::=
    'funcion' TK_id <TYPE> 'con parametros' '(' <LISTPARAMS> ')' <INSTFUNC> |
    'funcion' TK_id <TYPE> <INSTFUNC>                                       |
    'metodo'  TK_id 'con parametros' '(' <LISTPARAMS> ')' <INSTMETH>        |
    'metodo'  TK_id <INSTMETH>

<INSTFUNC> ::=
    <INSTRUCTIONS> 'fin funcion' |
    'fin funcion'

<INSTMETH> ::=
    <INSTRUCTIONS> 'fin metodo' |
    'fin metodo'

<LISTPARAMS> ::=
    <LISTPARAMS> ',' TK_id <TYPE> |
    TK_id <TYPE>

<CALLFUNC> ::=
    'ejecutar' TK_id '(' <LISTARGS> ')' |
    'ejecutar' TK_id '(' ')'

<LISTARGS> ::=
    <LISTARGS> ',' <EXP> |
    <EXP>

<PRINT> ::=
    'imprimir nl' <EXP> |
    'imprimir'    <EXP>

<TYPE> ::=
    'Cadena'   |
    'Entero'   |
    'Caracter' |
    'Booleano' |
    'Decimal'

<EXP> ::=
    <ARITHMETICS> |
    <RELATIONALS> |
    <LOGICS>      |
    <CALLFUNC>    |
    TK_id         |
    TK_str        |
    TK_char       |
    TK_num        |
    'Verdadero'   |
    'Falso'       |
    '(' <EXP> ')'

<ARITHMETICS> ::=
    <EXP> '+'        <EXP> |
    <EXP> '-'        <EXP> |
    <EXP> '*'        <EXP> |
    <EXP> '/'        <EXP> |
    <EXP> 'potencia' <EXP> |
    <EXP> 'modulo'   <EXP> |
    '-'   <EXP> 

<RELATIONALS> ::=
    <EXP> 'igual a'         <EXP> |
    <EXP> 'diferente de'    <EXP> |
    <EXP> 'menor igual que' <EXP> |
    <EXP> 'mayor igual que' <EXP> |
    <EXP> 'menor que'       <EXP> |
    <EXP> 'mayor que'       <EXP>

<LOGICS> ::=
    <EXP> 'y' <EXP> |
    <EXP> 'o' <EXP> |
    'no'  <EXP>
```

## Precedencia de Operadores
| Nivel | Asociatividad  |                              Token                               |
|   -   |       -        |                                -                                 |
|   8   | Izquierda      | ```RW_or```                                                      |
|   7   | Izquierda      | ```RW_and```                                                     |
|   6   | Derecha        | ```RW_not```                                                     |
|   5   | Izquierda      | ```RW_equequ```, ```RW_notequ```                                 |
|   4   | Izquierda      | ```RW_less```, ```RW_lessequ```, ```RW_more```, ```RW_moreequ``` |
|   3   | Izquierda      | ```TK_plus```, ```TK_minus```                                    |
|   2   | Izquierda      | ```TK_mult```, ```TK_div```, ```RW_mod```                        |
|   1   | No Asociativa  | ```RW_pow```                                                     |
|   0   | Derecha        | ```TK_uminus```                                                  |

## Instalación JFlex y CUP
* Descargar la JFlex: [JFlex](https://jflex.de/download.html)
* Descargar la CUP: [CUP](http://www2.cs.tum.edu/projects/cup/)
* Incluir las librerías en el proyecto java.

## Generación de Scanner
```java
import java.io.File;
public class GScanner {
    public static void main(String[] args) {
        jflex.Main.generate(
            new File(
                "src/Language/Scanner.jflex"
            )
        );
    }
}
```

## Generación de Parser
```java
public class GParser {
    public static void main(String[] args) {
        generate();
    }
    public static void generate() {
        try {
            java_cup.Main.main(
                new String[] {
                    "-destdir",
                    "src/Language",
                    "-symbols",
                    "TOK",
                    "-parser",
                    "Parser",
                    "src/Language/Parser.cup"
                }
            );
        }
        catch(Exception e) {
            System.out.println(e);
        }
    }
}
```

## Intérprete con JFlex y CUP
Usuario: [brandonT2002](https://github.com/brandonT2002)  
Repositorio: [MiniJ](https://github.com/brandonT2002/MiniJ)

## Intérprete con JavaCC
Usuario: [bryan967132](https://github.com/bryan967132)  
Repositorio: [MiniJInterpreter](https://github.com/bryan967132/MiniJInterpreter)

## Traductor a C3D con JFlex y CUP
Usuario: [bryan967132](https://github.com/bryan967132)  
Repositorio: [MiniJC3D](https://github.com/bryan967132/MiniJC3D)

## Traductor a Risc-V con JFlex y CUP
Usuario: [bryan967132](https://github.com/bryan967132)  
Repositorio: [MiniJRiscV](https://github.com/bryan967132/MiniJRiscV)