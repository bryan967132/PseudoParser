package Classes.Instructions;
import Classes.Abstracts.Expression;
import Classes.Env.Env;
import Classes.Utils.ReturnType;
import Classes.Utils.Type;
import Classes.Utils.TypeExp;
public class Continue extends Expression {
    public Continue(int line, int column) {
        super(line, column, TypeExp.CONTINUE);
    }
    public ReturnType exec(Env env) {
        return new ReturnType(this.typeExp, Type.NULL);
    }
}