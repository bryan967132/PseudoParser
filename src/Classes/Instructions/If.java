package Classes.Instructions;
import Classes.Abstracts.*;
import Classes.Env.Env;
import Classes.Generators.GoGen;
import Classes.Generators.PyGen;
import Classes.Utils.ReturnType;
import Classes.Utils.TypeInst;
import Classes.Utils.TypeSent;
public class If extends Instruction {
    public boolean isElseIf = false;
    public boolean isElse = false;
    Expression condition;
    Block block;
    Statement except;
    public If(int line, int column, Expression condition, Block block, Statement except) {
        super(line, column, TypeInst.IF);
        this.condition = condition;        
        this.block = block;        
        this.except = except;        
    }
    public ReturnType exec(Env env) {
        Env envIf = new Env(env, env.name + " If");
        ReturnType condition = this.condition.exec(envIf);
        if(Boolean.parseBoolean(condition.value.toString())) {
            ReturnType block = this.block.exec(envIf);
            if(block != null) {
                return block;
            }
            return null;
        }
        if(except != null) {
            ReturnType except = this.except.exec(envIf);
            if(except != null) {
                return except;
            }
        }
        return null;
    }
    public void goGenerate(Env env, GoGen goGen) {
        Env envIf = new Env(env, env.name + " If");
        if(!isElseIf) {
            goGen.addInstruction("if " + condition.goGenerate(env, goGen).value.toString() + " {");
        } else {
            goGen.addInstruction("} else if " + condition.goGenerate(env, goGen).value.toString() + " {");
        }
        block.goGenerate(envIf, goGen);
        if(except != null) {
            if(except.typeSent == TypeSent.INSTRUCTION && ((Instruction) except).typeInst == TypeInst.IF) {
                ((If) ((Instruction) except)).enableIsElseIf();
                ((Instruction) except).goGenerate(envIf, goGen);
                return;
            } else if(except.typeSent == TypeSent.INSTRUCTION && ((Instruction) except).typeInst == TypeInst.BLOCK_INST) {
                goGen.addInstruction("} else {");
                ((Instruction) except).goGenerate(envIf, goGen);
            }
        }
        goGen.addInstruction("}");
    }
    public void pyGenerate(Env env, PyGen pyGen) {
        Env envIf = new Env(env, env.name + " If");
        if(!isElseIf) {
            pyGen.addInstruction("if " + condition.pyGenerate(env, pyGen).value.toString() + ":");
        } else {
            pyGen.addInstruction("elif " + condition.pyGenerate(env, pyGen).value.toString() + ":");
        }
        block.pyGenerate(envIf, pyGen);
        if(except != null) {
            if(except.typeSent == TypeSent.INSTRUCTION && ((Instruction) except).typeInst == TypeInst.IF) {
                ((If) ((Instruction) except)).enableIsElseIf();
                ((Instruction) except).pyGenerate(envIf, pyGen);
                return;
            } else if(except.typeSent == TypeSent.INSTRUCTION && ((Instruction) except).typeInst == TypeInst.BLOCK_INST) {
                pyGen.addInstruction("else:");
                ((Instruction) except).pyGenerate(envIf, pyGen);
            }
        }
    }
    public void enableIsElseIf() {
        isElseIf = true;
    }
}