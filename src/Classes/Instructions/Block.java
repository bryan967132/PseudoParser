package Classes.Instructions;
import java.util.ArrayList;
import Classes.Abstracts.Expression;
import Classes.Abstracts.Instruction;
import Classes.Abstracts.Sentence;
import Classes.Env.Env;
import Classes.Generators.GoGen;
import Classes.Generators.PyGen;
import Classes.Utils.ReturnType;
import Classes.Utils.TypeExp;
import Classes.Utils.TypeSent;
public class Block extends Expression {
    ArrayList<Sentence> instructions;
    public Block(int line, int column, ArrayList<Sentence> instructions) {
        super(line, column, TypeExp.BLOCK_INST);
        this.instructions = instructions;
    }
    public ReturnType exec(Env env) {
        Env newEnv = new Env(env, env.name);
        Expression exp;
        Instruction inst;
        ReturnType ret;
        for(Sentence instruction : instructions) {
            if(instruction.typeSent == TypeSent.EXPRESSION) {
                exp = (Expression) instruction;
                ret = exp.exec(newEnv);
                if(ret != null && exp.typeExp != TypeExp.INC && exp.typeExp != TypeExp.DEC) {
                    return ret;
                }
            } else if(instruction.typeSent == TypeSent.INSTRUCTION) {
                inst = (Instruction) instruction;
                inst.exec(newEnv);
            }
        }
        return null;
    }
    public ReturnType goGenerate(Env env, GoGen goGen) {
        goGen.newEnv();
        for(Sentence instruction : instructions) {
            if(instruction.typeSent == TypeSent.EXPRESSION) {
                if(((Expression) instruction).typeExp != TypeExp.CALL_FUNC) {
                    ((Expression) instruction).goGenerate(env, goGen);
                    continue;
                }
                goGen.addInstruction(((Expression) instruction).goGenerate(env, goGen).value.toString());
            } else if(instruction.typeSent == TypeSent.INSTRUCTION) {
                ((Instruction) instruction).goGenerate(env, goGen);
            }
        }
        goGen.prevEnv();
        return null;
    }
    public ReturnType pyGenerate(Env env, PyGen pyGen) {
        pyGen.newEnv();
        if(instructions.size() > 0) {
            for(Sentence instruction : instructions) {
                if(instruction.typeSent == TypeSent.EXPRESSION) {
                    if(((Expression) instruction).typeExp != TypeExp.CALL_FUNC) {
                        ((Expression) instruction).pyGenerate(env, pyGen);
                        continue;
                    }
                    pyGen.addInstruction(((Expression) instruction).pyGenerate(env, pyGen).value.toString());
                } else if(instruction.typeSent == TypeSent.INSTRUCTION) {
                    ((Instruction) instruction).pyGenerate(env, pyGen);
                }
            }
        } else {
            pyGen.addInstruction("pass");
        }
        pyGen.prevEnv();
        return null;
    }
    public boolean thereAreInstructions() {
        return instructions.size() > 0;
    }
}