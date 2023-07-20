package Classes.Expressions;
import Classes.Abstracts.Expression;
import Classes.Env.Env;
import Classes.Utils.ReturnType;
import Classes.Utils.Type;
import Classes.Utils.TypeExp;
public class Return extends Expression {
    Expression exp;
    public Return(int line, int column, Expression exp) {
        super(line, column, TypeExp.RETURN);
        this.exp = exp;
    }
    public ReturnType exec(Env env) {
        if(exp != null) {
            return exp.exec(env);
        }
        return new ReturnType(this.typeExp, Type.NULL);
    }
}