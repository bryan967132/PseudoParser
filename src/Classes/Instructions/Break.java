package Classes.Instructions;
import Classes.Abstracts.Expression;
import Classes.Env.Env;
import Classes.Utils.ReturnType;
import Classes.Utils.Type;
import Classes.Utils.TypeExp;
public class Break extends Expression {
    public Break(int line, int column) {
        super(line, column, TypeExp.BREAK);
    }
    public ReturnType exec(Env env) {
        return new ReturnType(this.typeExp, Type.NULL);
    }
}