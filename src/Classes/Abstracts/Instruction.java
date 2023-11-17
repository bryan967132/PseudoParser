package Classes.Abstracts;
import Classes.Env.Env;
import Classes.Generators.GoGen;
import Classes.Generators.PyGen;
import Classes.Utils.TypeInst;
import Classes.Utils.TypeSent;
public abstract class Instruction extends Sentence {
    public TypeInst typeInst;
    public Instruction(int line, int column, TypeInst typeInst) {
        super(line, column, TypeSent.INSTRUCTION);
        this.typeInst = typeInst;
    }
    public abstract void exec(Env env);
    public abstract void goGenerate(Env env, GoGen goGen);
    public abstract void pyGenerate(Env env, PyGen pyGen);
}