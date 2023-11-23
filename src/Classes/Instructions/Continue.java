package Classes.Instructions;
import Classes.Abstracts.Instruction;
import Classes.Env.Env;
import Classes.Generators.GoGen;
import Classes.Generators.PyGen;
import Classes.Utils.ReturnType;
import Classes.Utils.Type;
import Classes.Utils.TypeInst;
public class Continue extends Instruction {
    public Continue(int line, int column) {
        super(line, column, TypeInst.CONTINUE);
    }
    public ReturnType exec(Env env) {
        return new ReturnType(this.typeInst, Type.NULL);
    }
    public void goGenerate(Env env, GoGen goGen) {
        goGen.addInstruction("continue");
    }
    public void pyGenerate(Env env, PyGen pyGen) {
        pyGen.addInstruction("continue");
    }
}