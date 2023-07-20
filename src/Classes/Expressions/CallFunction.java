package Classes.Expressions;
import java.util.ArrayList;
import Classes.Abstracts.Expression;
import Classes.Env.Env;
import Classes.Instructions.Function;
import Classes.Utils.ReturnType;
import Classes.Utils.TypeExp;
public class CallFunction extends Expression {
    String id;
    ArrayList<Expression> args;
    public CallFunction(int line, int column, String id, ArrayList<Expression> args) {
        super(line, column, TypeExp.CALL_FUNC);
        this.id = id;
        this.args = args;
    }
    public ReturnType exec(Env env) {
        Function func = env.getFunction(id);
        if(func != null) {
            Env envFunc = new Env(env.getGlobal(), "Funcion " + id);
            if(func.params.size() == args.size()) {
                for(int i = 0; i < func.params.size(); i++) {
                    ReturnType value = args.get(i).exec(env);
                    ReturnType param = func.params.get(i).exec(env);
                    if(value.type == param.type) {
                        envFunc.saveID(param.value.toString(), value.value, param.type, this.line, this.column);
                        continue;
                    }
                    // ERROR SEMANTICO: NO COINCIDEN LOS TIPOS DE LOS PARAMETROS
                    return null;
                }
                ReturnType execute = func.block.exec(envFunc);
                if(execute != null) {
                    if(execute.value == TypeExp.RETURN) {
                        return null;
                    }
                    return execute;
                }
                return null;
            }
            // ERROR SEMANTICO: NO CONICIDE LA CANTIDAD DE PARAMETROS ENVIADOS CON LOS RECIBIDOS
            return null;
        }
        // ERROR SEMANTICO: LA FUNCION QUE SE INTENTA LLAMAR NO ESTÁ DECLARADO
        return null;
    }
}