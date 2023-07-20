package Classes.Instructions;
import Classes.Abstracts.Expression;
import Classes.Abstracts.Sentence;
import Classes.Env.Env;
import Classes.Utils.ReturnType;
import Classes.Utils.TypeExp;
public class If extends Expression {
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
}