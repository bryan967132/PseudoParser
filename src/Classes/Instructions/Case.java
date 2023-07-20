package Classes.Instructions;
import Classes.Abstracts.Expression;
import Classes.Env.Env;
import Classes.Utils.ReturnType;
import Classes.Utils.Type;
import Classes.Utils.TypeExp;
public class Case extends Expression {
    Expression _case;
    Block block;
    ReturnType caseEvaluate;
    public Case(int line, int column, Expression _case, Block block){
        super(line, column, TypeExp.IF);
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
            ReturnType block = this.block.exec(envCase);
            if (block != null) {
                return block;
            }
        }
        return null;
    }
    public boolean compare(ReturnType value1, ReturnType value2) {
        if(value1.type == value2.type) {
            if(value1.type == Type.NUMBER) {
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
}