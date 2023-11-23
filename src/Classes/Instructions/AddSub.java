package Classes.Instructions;
import Classes.Abstracts.Expression;
import Classes.Abstracts.Instruction;
import Classes.Env.Env;
import Classes.Env.Symbol;
import Classes.Expressions.Arithmetic;
import Classes.Expressions.Primitive;
import Classes.Generators.GoGen;
import Classes.Generators.PyGen;
import Classes.Utils.ReturnType;
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
    public ReturnType exec(Env env) {
        Symbol value = env.getValueID(id, this.line, this.column);
        switch(this.sign) {
            case "+=":
                env.reasignID(
                    id,
                    new Arithmetic(line, column,
                        new Primitive(line, column, value.value.toString(), value.type),
                        "+",
                        exp
                    ).exec(env),
                    this.line, this.column
                );
                break;
            case "-=":
                env.reasignID(
                    id,
                    new Arithmetic(line, column,
                        new Primitive(line, column, value.value.toString(), value.type),
                        "-",
                        exp
                    ).exec(env),
                    this.line, this.column
                );
                break;
        }
        return null;
    }
    public void goGenerate(Env env, GoGen goGen) {
        goGen.addInstruction(id + " " + sign + " " + exp.goGenerate(env, goGen));
    }
    public void pyGenerate(Env env, PyGen pyGen) {
        pyGen.addInstruction(id + " " + sign + " " + exp.pyGenerate(env, pyGen));
    }
}