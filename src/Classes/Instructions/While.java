package Classes.Instructions;
import Classes.Abstracts.Expression;
import Classes.Abstracts.Instruction;
import Classes.Env.Env;
import Classes.Generators.GoGen;
import Classes.Generators.PyGen;
import Classes.Utils.ReturnType;
import Classes.Utils.TypeInst;
public class While extends Instruction {
    private Expression condition;
    private Block block;
    public While(int line, int column, Expression condition, Block block) {
        super(line, column, TypeInst.LOOP_WHILE);
        this.condition = condition;
        this.block = block;
    }
    public ReturnType exec(Env env) {
        Env envWhile = new Env(env, env.name + " While");
        ReturnType condition = this.condition.exec(envWhile);
        while(Boolean.parseBoolean(condition.value.toString())) {
            ReturnType block = this.block.exec(envWhile);
            if(block != null) {
                if(block.value == TypeInst.CONTINUE) {
                    condition = this.condition.exec(envWhile);
                    continue;
                }
                if(block.value == TypeInst.BREAK) {
                    break;
                }
                return block;
            }
            condition = this.condition.exec(envWhile);
        }
        return null;
    }
    public void goGenerate(Env env, GoGen goGen) {
        goGen.addInstruction("for " + condition.goGenerate(env, goGen).value.toString() + " {");
        block.goGenerate(env, goGen);
        goGen.addInstruction("}");
    }
    public void pyGenerate(Env env, PyGen pyGen) {
        pyGen.addInstruction("while " + condition.pyGenerate(env, pyGen).value.toString() + ":");
        block.pyGenerate(env, pyGen);
    }
}