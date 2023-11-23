package Classes.Instructions;
import Classes.Abstracts.Expression;
import Classes.Abstracts.Instruction;
import Classes.Env.Env;
import Classes.Generators.GoGen;
import Classes.Generators.PyGen;
import Classes.Utils.ReturnType;
import Classes.Utils.Type;
import Classes.Utils.TypeInst;
public class Case extends Instruction {
    Expression _case;
    Block block;
    ReturnType caseEvaluate;
    public boolean flag = false;
    public Case(int line, int column, Expression _case, Block block){
        super(line, column, TypeInst.CASE);
        this._case = _case;
        this.block = block;
    }
    public void setCase(ReturnType caseEvaluate) {
        this.caseEvaluate = caseEvaluate;
    }
    public ReturnType exec(Env env){
        Env envCase = new Env(env, env.name + " Case");
        ReturnType caseE = caseEvaluate; 
        ReturnType case_ = _case.exec(envCase); 
        envCase.name += " " + case_.value.toString();
        if(compare(case_, caseE)) {
            flag = true;
            ReturnType block = this.block.exec(envCase);
            if (block != null) {
                return block;
            }
        }
        return null;
    }
    public boolean compare(ReturnType value1, ReturnType value2) {
        if(value1.type == value2.type) {
            if(value1.type == Type.INT) {
                return Integer.parseInt(value1.value.toString()) == Integer.parseInt(value2.value.toString());
            }
            if(value1.type == Type.DOUBLE) {
                return Double.parseDouble(value1.value.toString()) == Double.parseDouble(value2.value.toString());
            }
            if(value1.type == Type.BOOLEAN) {
                return Boolean.parseBoolean(value1.value.toString()) && Boolean.parseBoolean(value2.value.toString());
            }
            if(value1.type == Type.CHAR) {
                return ((int) value1.value.toString().charAt(0)) == ((int) value2.value.toString().charAt(0));
            }
            if(value1.type == Type.STRING) {
                return value1.value.toString().equals(value2.value.toString());
            }
        }
        return false;
    }
    public void goGenerate(Env env, GoGen goGen) {
        goGen.addInstruction("case " + _case.goGenerate(env, goGen).value.toString() + ":");
        block.goGenerate(env, goGen);
    }
    public void pyGenerate(Env env, PyGen pyGen) {
        pyGen.addInstruction("case " + _case.pyGenerate(env, pyGen).value.toString() + ":");
        block.pyGenerate(env, pyGen);
    }
}