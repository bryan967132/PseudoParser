package Classes.Expressions;
import Classes.Abstracts.Expression;
import Classes.Env.Env;
import Classes.Generators.GoGen;
import Classes.Generators.PyGen;
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
            case INT:
                return new ReturnType(Integer.parseInt(value.toString()), type);
            case DOUBLE:
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
    public ReturnType goGenerate(Env env, GoGen goGen) {
        if(type != Type.STRING) {
            if(type == Type.CHAR) {
                return new ReturnType("'" + value.toString() + "'", type);
            }
            if(type != Type.BOOLEAN) {
                return new ReturnType(value.toString(), type);
            }
            return new ReturnType(value.toString().equals("Verdadero") ? "true" : "false", type);
        }
        return new ReturnType('"' + value.toString() + '"', type);
    }
    public ReturnType pyGenerate(Env env, PyGen pyGen) {
        if(type != Type.STRING) {
            if(type == Type.CHAR) {
                return new ReturnType("'" + value.toString() + "'", type);
            }
            if(type != Type.BOOLEAN) {
                return new ReturnType(value.toString(), type);
            }
            return new ReturnType(value.toString().equals("Verdadero") ? "True" : "False", type);
        }
        return new ReturnType("'" + value.toString() + "'", type);
    }
}