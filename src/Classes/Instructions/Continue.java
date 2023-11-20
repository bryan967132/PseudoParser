package Classes.Instructions;
import Classes.Abstracts.Expression;
import Classes.Env.Env;
import Classes.Generators.GoGen;
import Classes.Generators.PyGen;
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
    public ReturnType goGenerate(Env env, GoGen goGen) {
        goGen.addInstruction("continue");
        return null;
    }
    public ReturnType pyGenerate(Env env, PyGen pyGen) {
        pyGen.addInstruction("continue");
        return null;
    }
}