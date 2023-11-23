package Classes.Instructions;
import Classes.Abstracts.Expression;
import Classes.Abstracts.Instruction;
import Classes.Env.Env;
import Classes.Expressions.CallFunction;
import Classes.Generators.GoGen;
import Classes.Generators.PyGen;
import Classes.Utils.ReturnType;
import Classes.Utils.TypeInst;
public class MainMethod extends Instruction {
    Expression method;
    public MainMethod(int line, int column, Expression method) {
        super(line, column, TypeInst.MAIN);
        this.method = method;
    }
    public ReturnType exec(Env env) {
        method.exec(env);
        return null;
    }
    public void goGenerate(Env env, GoGen goGen) {
        goGen.setMainCall(method.goGenerate(env, goGen).value.toString());
    }
    public void pyGenerate(Env env, PyGen pyGen) {
        pyGen.setMainCall(method.pyGenerate(env, pyGen).value.toString());
    }
    public String getId() {
        return ((CallFunction) method).getId();
    }
}