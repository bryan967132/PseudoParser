package Classes.Expressions;
import Classes.Abstracts.Expression;
import Classes.Env.Env;
import Classes.Generators.GoGen;
import Classes.Generators.PyGen;
import Classes.Utils.ReturnType;
import Classes.Utils.Type;
import Classes.Utils.TypeExp;
public class Logic extends Expression {
    private Expression exp1;
    private String sign;
    private Expression exp2;
    public Logic(int line, int column, Expression exp1, String sign, Expression exp2) {
        super(line, column, TypeExp.LOGIC_OP);
        this.exp1 = exp1;
        this.sign = sign;
        this.exp2 = exp2;
    }
    public ReturnType exec(Env env) {
        switch(this.sign) {
            case "y":
                return and(env);
            case "o":
                return or(env);
            case "no":
                return not(env);
            default:
                return new ReturnType("nulo", Type.NULL);
        }
    }
    public ReturnType and(Env env) {
        ReturnType value1 = exp1.exec(env);
        if(value1.type != Type.BOOLEAN) {
            env.setError("Los tipos no son válidos para operaciones lógicas", exp1.line, exp1.column);
            return new ReturnType("nulo", Type.NULL);
        }
        ReturnType value2 = exp2.exec(env);
        if(value2.type == Type.BOOLEAN) {
            return new ReturnType(Boolean.parseBoolean(value1.value.toString()) && Boolean.parseBoolean(value2.value.toString()), Type.BOOLEAN);
        }
        env.setError("Los tipos no son válidos para operaciones lógicas", exp2.line, exp2.column);
        return new ReturnType("nulo", Type.NULL);
    }
    public ReturnType or(Env env) {
        ReturnType value1 = exp1.exec(env);
        if(value1.type != Type.BOOLEAN) {
            env.setError("Los tipos no son válidos para operaciones lógicas", exp1.line, exp1.column);
            return new ReturnType("nulo", Type.NULL);
        }
        ReturnType value2 = exp2.exec(env);
        if(value2.type == Type.BOOLEAN) {
            return new ReturnType(Boolean.parseBoolean(value1.value.toString()) || Boolean.parseBoolean(value2.value.toString()), Type.BOOLEAN);
        }
        env.setError("Los tipos no son válidos para operaciones lógicas", exp2.line, exp2.column);
        return new ReturnType("nulo", Type.NULL);
    }
    public ReturnType not(Env env) {
        ReturnType value2 = exp2.exec(env);
        if(value2.type == Type.BOOLEAN) {
            return new ReturnType(!Boolean.parseBoolean(value2.value.toString()), Type.BOOLEAN);
        }
        env.setError("Los tipos no son válidos para operaciones lógicas", exp2.line, exp2.column);
        return new ReturnType("nulo", Type.NULL);
    }
    public ReturnType goGenerate(Env env, GoGen goGen) {
        String code = "";
        if(exp1 != null) {
            code += exp1.goGenerate(env, goGen).value + " ";
        }
        if(!sign.equals("no")) {
            code += getSignGo() + " ";
        } else {
            code += getSignGo();
        }
        code += exp2.goGenerate(env, goGen).value.toString();
        if(isGrp) {
            code = "(" + code + ")";
        }
        return new ReturnType(code, Type.BOOLEAN);
    }
    public ReturnType pyGenerate(Env env, PyGen pyGen) {
        String code = "";
        if(exp1 != null) {
            code += exp1.pyGenerate(env, pyGen).value.toString() + " ";
        }
        code += getSignPy() + " ";
        code += exp2.pyGenerate(env, pyGen).value.toString();
        if(isGrp) {
            code = "(" + code + ")";
        }
        return new ReturnType(code, Type.BOOLEAN);
    }
    public String getSignGo() {
        switch(this.sign) {
            case "y":
                return "&&";
            case "o":
                return "||";
            case "no":
                return "!";
            default:
                return "";
        }
    }
    public String getSignPy() {
        switch(this.sign) {
            case "y":
                return "and";
            case "o":
                return "or";
            case "no":
                return "not";
            default:
                return "";
        }
    }
}