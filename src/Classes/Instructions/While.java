package Classes.Instructions;
import Classes.Abstracts.Expression;
import Classes.Env.Env;
import Classes.Utils.ReturnType;
import Classes.Utils.TypeExp;
public class While extends Expression {
    private Expression condition;
    private Block block;
    public While(int line, int column, Expression condition, Block block) {
        super(line, column, TypeExp.LOOP_WHILE);
        this.condition = condition;
        this.block = block;
    }
    public ReturnType exec(Env env) {
        Env envWhile = new Env(env, env.name + " While");
        ReturnType condition = this.condition.exec(envWhile);
        while(Boolean.parseBoolean(condition.value.toString())) {
            ReturnType block = this.block.exec(envWhile);
            if(block != null) {
                if(block.value == TypeExp.CONTINUE) {
                    condition = this.condition.exec(envWhile);
                    continue;
                }
                if(block.value == TypeExp.BREAK) {
                    break;
                }
                return block;
            }
            condition = this.condition.exec(envWhile);
        }
        return null;
    }
}