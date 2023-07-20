package Classes.Instructions;
import java.util.ArrayList;
import Classes.Abstracts.Expression;
import Classes.Abstracts.Instruction;
import Classes.Env.Env;
import Classes.Utils.ReturnType;
import Classes.Utils.Type;
import Classes.Utils.TypeInst;
public class InitID extends Instruction {
    ArrayList<String> inits;
    Expression value;
    Type type;
    public InitID(int line, int column, ArrayList<String> inits, Expression value, Type type) {
        super(line, column, TypeInst.INIT_ID);
        this.inits = inits;
        this.value = value;
        this.type = type;
    }
    public void exec(Env env) {
        for(String id : inits) {
            if(value != null) {
                ReturnType value = this.value.exec(env);
                env.saveID(id, value.value, type, line, column);
            }
            else {
                switch(type) {
                    case NUMBER:
                        env.saveID(id, 0, type, line, column);
                        break;
                    case BOOLEAN:
                        env.saveID(id, true, type, line, column);
                        break;
                    case CHAR:
                        env.saveID(id, '0', type, line, column);
                        break;
                    case STRING:
                        env.saveID(id, "", type, line, column);
                        break;
                    default:
                        break;
                }
            }
        }
    }
}