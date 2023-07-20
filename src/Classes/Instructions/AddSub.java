package Classes.Instructions;
import Classes.Abstracts.Expression;
import Classes.Abstracts.Instruction;
import Classes.Env.Env;
import Classes.Env.Symbol;
import Classes.Expressions.Arithmetic;
import Classes.Expressions.Primitive;
import Classes.Utils.TypeInst;
public class AddSub extends Instruction {
    private String id;
    private String sign;
    private Expression exp;
    public AddSub(int line, int column, String id, String sign, Expression exp) {
        super(line, column, sign.equals("+=") ? TypeInst.ADD : TypeInst.SUB);
        this.id = id;
        this.sign = sign;
        this.exp = exp;
    }
    public void exec(Env env) {
        Symbol value = env.getValueID(id);
        switch(this.sign) {
            case "+=":
                env.reasignID(
                    id,
                    new Arithmetic(line, column,
                        new Primitive(line, column, value.value.toString(), value.type),
                        "+",
                        exp
                    ).exec(env)
                );
                break;
            case "-=":
                env.reasignID(
                    id,
                    new Arithmetic(line, column,
                        new Primitive(line, column, value.value.toString(), value.type),
                        "-",
                        exp
                    ).exec(env)
                );
                break;
        }
    }
}