package Classes.Utils;
public class Parameter {
    public String id;
    public Type type;
    public Parameter(int line, int column, String id, Type type) {
        this.id = id;
        this.type = type;
    }
    public String goCode() {
        return id + " " + getGoType();
    }
    public String pyCode() {
        return id + ": " + getPyType();
    }
    private String getGoType() {
        switch(type) {
            case INT:
                return "int";
            case DOUBLE:
                return "float64";
            case BOOLEAN:
                return "bool";
            case STRING:
                return "string";
            case CHAR:
                return "rune";
            default:
                return "";
        }
    }
    private String getPyType() {
        switch(type) {
            case INT:
                return "int";
            case DOUBLE:
                return "float";
            case BOOLEAN:
                return "bool";
            case STRING:
                return "str";
            case CHAR:
                return "chr";
            default:
                return "";
        }
    }
}