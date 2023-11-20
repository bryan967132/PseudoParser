package Classes.Instructions;
import Classes.Abstracts.*;
import Classes.Env.Env;
import Classes.Generators.GoGen;
import Classes.Generators.PyGen;
import Classes.Utils.ReturnType;
import Classes.Utils.TypeExp;
import Classes.Utils.TypeSent;
public class If extends Expression {
    public boolean isElseIf = false;
    public boolean isElse = false;
    Expression condition;
    Block block;
    Sentence except;
    public If(int line, int column, Expression condition, Block block, Sentence except) {
        super(line, column, TypeExp.IF);
        this.condition = condition;        
        this.block = block;        
        this.except = except;        
    }
    public ReturnType exec(Env env) {
        Env envIf = new Env(env, env.name + " If");
        ReturnType condition = this.condition.exec(envIf);
        if(Boolean.parseBoolean(condition.value.toString())) { // if(condicion)
            ReturnType block = this.block.exec(envIf);           //   instrucciones
            if(block != null) {
                return block;
            }
            return null;
        }
        // else
        if(except != null) {
            ReturnType except = ((Expression) this.except).exec(envIf); // if | instrucciones_else
            if(except != null) {
                return except;
            }
        }
        return null;
    }
    public ReturnType goGenerate(Env env, GoGen goGen) {
        Env envIf = new Env(env, env.name + " If");
        if(!isElseIf) {
            goGen.addInstruction("if " + condition.goGenerate(env, goGen).value.toString() + " {");
        } else {
            goGen.addInstruction("} else if " + condition.goGenerate(env, goGen).value.toString() + " {");
        }
        block.goGenerate(envIf, goGen);
        if(except != null) {
            if(except.typeSent == TypeSent.EXPRESSION && ((Expression) except).typeExp == TypeExp.IF) {
                ((If) ((Expression) except)).enableIsElseIf();
                ((Expression) except).goGenerate(envIf, goGen);
                return null;
            } else if(except.typeSent == TypeSent.EXPRESSION && ((Expression) except).typeExp == TypeExp.BLOCK_INST) {
                goGen.addInstruction("} else {");
                ((Expression) except).goGenerate(envIf, goGen);
            }
        }
        goGen.addInstruction("}");
        return null;
    }
    public ReturnType pyGenerate(Env env, PyGen pyGen) {
        Env envIf = new Env(env, env.name + " If");
        if(!isElseIf) {
            pyGen.addInstruction("if " + condition.pyGenerate(env, pyGen).value.toString() + ":");
        } else {
            pyGen.addInstruction("elif " + condition.pyGenerate(env, pyGen).value.toString() + ":");
        }
        block.pyGenerate(envIf, pyGen);
        if(except != null) {
            if(except.typeSent == TypeSent.EXPRESSION && ((Expression) except).typeExp == TypeExp.IF) {
                ((If) ((Expression) except)).enableIsElseIf();
                ((Expression) except).pyGenerate(envIf, pyGen);
                return null;
            } else if(except.typeSent == TypeSent.EXPRESSION && ((Expression) except).typeExp == TypeExp.BLOCK_INST) {
                pyGen.addInstruction("else:");
                ((Expression) except).pyGenerate(envIf, pyGen);
            }
        }
        return null;
    }
    public void enableIsElseIf() {
        isElseIf = true;
    }
}