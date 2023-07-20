package Classes.Expressions;
import Classes.Abstracts.Expression;
import Classes.Env.Env;
import Classes.Utils.ReturnType;
import Classes.Utils.Type;
import Classes.Utils.TypeExp;
public class Parameter extends Expression {
    String id;
    Type type;
    public Parameter(int line, int column, String id, Type type) {
        super(line, column, TypeExp.PARAMETER);
        this.id = id;
        this.type = type;
    }
    public ReturnType exec(Env env) {
        return new ReturnType(id, this.type);
    }
}