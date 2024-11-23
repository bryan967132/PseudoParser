package Classes.Expressions;
import Classes.Abstracts.Expression;
import Classes.Env.Env;
import Classes.Env.Symbol;
import Classes.Generators.GoGen;
import Classes.Generators.PyGen;
import Classes.Utils.ReturnType;
import Classes.Utils.Type;
import Classes.Utils.TypeExp;
public class AccessVar extends Expression {
    private String id;
    public AccessVar(int line, int column, String id) {
        super(line, column, TypeExp.ACCESSVAR);
        this.id = id;
    }
    public ReturnType exec(Env env) {
        Symbol value = env.getValueID(id, this.line, this.column);
        if(value != null) {
            return new ReturnType(value.value, value.type);
        }
        return new ReturnType("nulo", Type.NULL);
    }
    public ReturnType goGenerate(Env env, GoGen goGen) {
        Symbol value = env.getValueID(id, this.line, this.column);
        if(value != null) {
            return new ReturnType(id, value.type);
        }
        return new ReturnType("nil", Type.NULL);
    }
    public ReturnType pyGenerate(Env env, PyGen pyGen) {
        Symbol value = env.getValueID(id, this.line, this.column);
        if(value != null) {
            if(env.isGlobal(id)) {
                env.getLocal().setIdGlobal(id);
            }
            return new ReturnType(id, value.type);
        }
        return new ReturnType("None", Type.NULL);
    }
}