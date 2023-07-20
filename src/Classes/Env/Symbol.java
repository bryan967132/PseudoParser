package Classes.Env;
import Classes.Utils.Type;
public class Symbol {
    public Object value;
    String id;
    public Type type;
    Type arrayType;
    public Symbol(Object value, String id, Type type, Type arrayType) {
        this.value = value;
        this.id = id;
        this.type = type;
        this.arrayType = arrayType;
    }
}