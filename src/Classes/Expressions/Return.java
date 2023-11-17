package Classes.Expressions;
import Classes.Abstracts.Expression;
import Classes.Env.Env;
import Classes.Generators.GoGen;
import Classes.Generators.PyGen;
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
    public ReturnType goGenerate(Env env, GoGen goGen) {
        goGen.addInstruction("return" + (exp != null ? " " + exp.goGenerate(env, goGen).value.toString() : ""));
        return null;
    }
    public ReturnType pyGenerate(Env env, PyGen pyGen) {
        pyGen.addInstruction("return" + (exp != null ? " " + exp.pyGenerate(env, pyGen).value.toString() : ""));
        return null;
    }
}