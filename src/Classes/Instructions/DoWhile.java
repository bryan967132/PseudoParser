package Classes.Instructions;
import Classes.Abstracts.Expression;
import Classes.Env.Env;
import Classes.Utils.ReturnType;
import Classes.Utils.TypeExp;
public class DoWhile extends Expression {
    private Expression condition;
    private Block block;
    public DoWhile(int line, int column, Expression condition, Block block) {
        super(line, column, TypeExp.LOOP_DOWHILE);
        this.condition = condition;
        this.block = block;
    }
    public ReturnType exec(Env env) {
        Env envDoWhile = new Env(env, env.name + " Do While");
        ReturnType condition = null;
        do {
            ReturnType block = this.block.exec(envDoWhile);
            if(block != null) {
                if(block.value == TypeExp.CONTINUE) {
                    condition = this.condition.exec(envDoWhile);
                    continue;
                }
                if(block.value == TypeExp.BREAK) {
                    break;
                }
                return block;
            }
            condition = this.condition.exec(envDoWhile);
        } while(Boolean.parseBoolean(condition.value.toString()));
        return null;
    }
}