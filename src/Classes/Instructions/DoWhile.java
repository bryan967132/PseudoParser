package Classes.Instructions;
import Classes.Abstracts.Expression;
import Classes.Abstracts.Instruction;
import Classes.Env.Env;
import Classes.Generators.GoGen;
import Classes.Generators.PyGen;
import Classes.Utils.ReturnType;
import Classes.Utils.TypeInst;
public class DoWhile extends Instruction {
    private Expression condition;
    private Block block;
    public DoWhile(int line, int column, Expression condition, Block block) {
        super(line, column, TypeInst.DOWHILE);
        this.condition = condition;
        this.block = block;
    }
    public ReturnType exec(Env env) {
        Env envDoWhile = new Env(env, env.name + " Do While");
        ReturnType condition = null;
        do {
            ReturnType block = this.block.exec(envDoWhile);
            if(block != null) {
                if(block.value == TypeInst.CONTINUE) {
                    condition = this.condition.exec(envDoWhile);
                    continue;
                }
                if(block.value == TypeInst.BREAK) {
                    break;
                }
                return block;
            }
            condition = this.condition.exec(envDoWhile);
        } while(Boolean.parseBoolean(condition.value.toString()));
        return null;
    }
    public void goGenerate(Env env, GoGen goGen) {
        goGen.addInstruction("for true {");
        block.goGenerate(env, goGen);
        goGen.newEnv();
        goGen.addInstruction("if !(" + condition.goGenerate(env, goGen).value.toString() + ") {");
        goGen.newEnv();
        goGen.addInstruction("break");
        goGen.prevEnv();
        goGen.addInstruction("}");
        goGen.prevEnv();
        goGen.addInstruction("}");
    }
    public void pyGenerate(Env env, PyGen pyGen) {
        pyGen.addInstruction("while True:");
        block.pyGenerate(env, pyGen);
        pyGen.newEnv();
        pyGen.addInstruction("if not (" + condition.pyGenerate(env, pyGen).value.toString() + "):");
        pyGen.newEnv();
        pyGen.addInstruction("break");
        pyGen.prevEnv();
        pyGen.prevEnv();
    }
}