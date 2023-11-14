package Classes.Utils;
import java.util.ArrayList;
public class Outs {
    public static ArrayList<String> printConsole = new ArrayList<>();
    public static ArrayList<Error> errors = new ArrayList<>();
    public static String getStringOuts() {
        String out = "";
        for(int i = 0; i < printConsole.size(); i ++) {
            out += printConsole.get(i);
            if(i < printConsole.size() - 1) {
                out += "\n";
            }
        }
        if(errors.size() > 0) {
            if(!out.equals("")) {
                out += "\n\n↳ ERRORES\n";
            } else {
                out += "↳ ERRORES\n";
            }
            for(int i = 0; i < errors.size(); i ++) {
                out += errors.get(i);
                if(i < errors.size() - 1) {
                    out += "\n";
                }
            }
        }
        return out;
    }
    public static Error newError(int line, int column, TypeError type, String description) {
        return new Error(line, column, type, description);
    }
    public static void resetOuts() {
        printConsole.clear();
        errors.clear();
    }
}