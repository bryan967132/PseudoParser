package Components;
public class ErrorS {
    int line;
    int column;
    Object token;
    String type;
    public ErrorS(int line,int column,Object token,String type) {
        this.line = line;
        this.column = column;
        this.token = token;
        this.type = type;
    }
    public void print() {
        System.out.println(
            "Syntax Error" + (token != null ? " in Line " + line + " Column " + column : "") +
            ". This Component was not expected: " + (token != null ? type + " = " + token : "EOF") + "."
        );
    }
    public String getHTML() {
        return "\t\t\t<tr>\n\t\t\t\t<td>" + toString() + "</td>\n\t\t\t</tr>\n";
    }
    public String toString() {
        return "Error Sintáctico" + (token != null ? " en Línea " + line + " Columna " + column : "") +
            ". No se esperaba este componente: " + (token != null ? (token.equals("\"\"") ? "EMPTYSTRING" + " = " + token : type + " = " + token) : "EOF") + ".";
    }
}