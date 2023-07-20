package Classes.Utils;
public class ReturnType {
    public Object value;
    public Type type;
    public ReturnType(Object value, Type type) {
        this.value = value;
        this.type = type;
    }
    @Override
    public String toString() {
        return "Object: " + value + ", Type: " + type;
    }
}