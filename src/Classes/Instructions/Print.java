package Classes.Instructions;
import Classes.Abstracts.Expression;
import Classes.Abstracts.Instruction;
import Classes.Env.Env;
import Classes.Generators.GoGen;
import Classes.Generators.PyGen;
import Classes.Utils.TypeInst;
import Classes.Utils.ReturnType;
import Classes.Utils.Type;
public class Print extends Instruction {
    Expression exp;
    public Print(int line, int column, Expression exp) {
        super(line, column, TypeInst.PRINT);
        this.exp = exp;
    }
    public void exec(Env env) {
        String value = "";
        if(exp != null) {
            ReturnType value1 = exp.exec(env);
            value = getValueB(value1);
        }
        env.setPrint(value);
    }
    public String getValueB(ReturnType value) {
        if(value.type == Type.BOOLEAN) {
            if(Boolean.parseBoolean(value.value.toString())) {
                return "Verdadero";
            }
            return "Falso";
        }
        return value.value.toString();
    }
    public void goGenerate(Env env, GoGen goGen) {
        goGen.importFmt();
        goGen.addInstruction("fmt.Println(" + (exp != null ? exp.goGenerate(env, goGen).value.toString() : "") + ")");
    }
    public void pyGenerate(Env env, PyGen pyGen) {
        pyGen.addInstruction("print(" + (exp != null ? exp.pyGenerate(env, pyGen).value.toString() : "") + ")");
    }
}