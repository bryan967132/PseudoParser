package Components;
public class ErrorL {
    int line;
    int column;
    String character;
    public ErrorL(int line,int column,String character) {
        this.line = line;
        this.column = column;
        this.character = character;
    }
    public void print() {
        System.out.printf("%-6s%-8s%-10s\n",
            line,
            column,
            "Unrecognized Character: " + character
        );
    }
    public String getHTML() {
        return "\t\t\t<tr>\n\t\t\t\t<td>" + character + "</td>\n\t\t\t\t<td>" + line + "</td>\n\t\t\t\t<td>" + column + "</td>\n\t\t\t</tr>\n";
    }
    public String toString() {
        return line + " ".repeat(6 - String.valueOf(line).length()) + column + " ".repeat(8 - String.valueOf(column).length()) + "Unrecognized Character: " + character;
    }
}