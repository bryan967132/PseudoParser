package Classes.Expressions;
import Classes.Abstracts.Expression;
import Classes.Env.Env;
import Classes.Utils.ReturnType;
import Classes.Utils.Type;
import Classes.Utils.TypeExp;
public class Primitive extends Expression {
    public Object value;
    Type type;
    public Primitive(int line, int column, Object value, Type type) {
        super(line, column, TypeExp.PRIMITIVE);
        this.value = value;
        this.type = type;
    }
    public ReturnType exec(Env env) {
        switch(type) {
            case NUMBER:
                return new ReturnType(Double.parseDouble(value.toString()), type);
            case BOOLEAN:
                return new ReturnType(value.toString().equals("Verdadero"), type);
            default:
                value = value.toString().replace("\\n", "\n");
                value = value.toString().replace("\\t", "\t");
                value = value.toString().replace("\\\"", "\"");
                value = value.toString().replace("\\'", "\'");
                value = value.toString().replace("\\\\", "\\");
                return new ReturnType(value.toString(), type);
        }
    }
}