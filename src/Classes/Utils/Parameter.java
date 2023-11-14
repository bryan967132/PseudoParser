package Classes.Utils;
public class Parameter {
    public String id;
    public Type type;
    public Parameter(int line, int column, String id, Type type) {
        this.id = id;
        this.type = type;
    }
}