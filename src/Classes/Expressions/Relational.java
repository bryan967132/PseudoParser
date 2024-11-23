package Classes.Expressions;
import Classes.Abstracts.Expression;
import Classes.Env.Env;
import Classes.Generators.GoGen;
import Classes.Generators.PyGen;
import Classes.Utils.ReturnType;
import Classes.Utils.Type;
import Classes.Utils.TypeExp;
public class Relational extends Expression {
    private Expression exp1;
    private String sign;
    private Expression exp2;
    public Relational(int line, int column, Expression exp1, String sign, Expression exp2) {
        super(line, column, TypeExp.RELATIONAL);
        this.exp1 = exp1;
        this.sign = sign;
        this.exp2 = exp2;
    }
    public ReturnType exec(Env env) {
        switch(this.sign) {
            case "=":
                return equal(env);
            case "!=":
                return notEqual(env);
            case ">=":
                return greaterEqual(env);
            case "<=":
                return lessEqual(env);
            case ">":
                return greater(env);
            case "<":
                return less(env);
            default:
                return new ReturnType("nulo", Type.NULL);
        }
    }
    public ReturnType equal(Env env) {
        ReturnType value1 = exp1.exec(env);
        ReturnType value2 = exp2.exec(env);
        if(value1.type == Type.INT || value1.type == Type.DOUBLE || value1.type == Type.CHAR) {
            if(value2.type == Type.INT || value2.type == Type.DOUBLE || value2.type == Type.CHAR) {
                value1 = getValue(value1);
                value2 = getValue(value2);
                return new ReturnType(Double.parseDouble(value1.value.toString()) == Double.parseDouble(value2.value.toString()), Type.BOOLEAN);
            }
            env.setError("Los tipos no son válidos para operaciones relacionales", exp2.line, exp2.column);
            return new ReturnType("nulo", Type.NULL);
        }
        if(value1.type == value2.type && value1.type == Type.STRING) {
            return new ReturnType(value1.value.toString().equals(value2.value.toString()), Type.BOOLEAN);
        }
        env.setError("Los tipos no son válidos para operaciones relacionales", exp1.line, exp1.column);
        return new ReturnType("nulo", Type.NULL);
    }
    public ReturnType notEqual(Env env) {
        ReturnType value1 = exp1.exec(env);
        ReturnType value2 = exp2.exec(env);
        if(value1.type == Type.INT || value1.type == Type.DOUBLE || value1.type == Type.CHAR) {
            if(value2.type == Type.INT || value2.type == Type.DOUBLE || value2.type == Type.CHAR) {
                value1 = getValue(value1);
                value2 = getValue(value2);
                return new ReturnType(Double.parseDouble(value1.value.toString()) != Double.parseDouble(value2.value.toString()), Type.BOOLEAN);
            }
            env.setError("Los tipos no son válidos para operaciones relacionales", exp2.line, exp2.column);
            return new ReturnType("nulo", Type.NULL);
        }
        if(value1.type == value2.type && value1.type == Type.STRING) {
            return new ReturnType(!value1.value.toString().equals(value2.value.toString()), Type.BOOLEAN);
        }
        env.setError("Los tipos no son válidos para operaciones relacionales", exp1.line, exp1.column);
        return new ReturnType("nulo", Type.NULL);
    }
    public ReturnType greaterEqual(Env env) {
        ReturnType value1 = exp1.exec(env);
        ReturnType value2 = exp2.exec(env);
        if(value1.type == Type.INT || value1.type == Type.DOUBLE || value1.type == Type.CHAR) {
            if(value2.type == Type.INT || value2.type == Type.DOUBLE || value2.type == Type.CHAR) {
                value1 = getValue(value1);
                value2 = getValue(value2);
                return new ReturnType(Double.parseDouble(value1.value.toString()) >= Double.parseDouble(value2.value.toString()), Type.BOOLEAN);
            }
            env.setError("Los tipos no son válidos para operaciones relacionales", exp2.line, exp2.column);
            return new ReturnType("nulo", Type.NULL);
        }
        if(value1.type == value2.type && value1.type == Type.STRING) {
            return new ReturnType(value1.value.toString().compareTo(value2.value.toString()) >= 0, Type.BOOLEAN);
        }
        env.setError("Los tipos no son válidos para operaciones relacionales", exp1.line, exp1.column);
        return new ReturnType("nulo", Type.NULL);
    }
    public ReturnType lessEqual(Env env) {
        ReturnType value1 = exp1.exec(env);
        ReturnType value2 = exp2.exec(env);
        if(value1.type == Type.INT || value1.type == Type.DOUBLE || value1.type == Type.CHAR) {
            if(value2.type == Type.INT || value2.type == Type.DOUBLE || value2.type == Type.CHAR) {
                value1 = getValue(value1);
                value2 = getValue(value2);
                return new ReturnType(Double.parseDouble(value1.value.toString()) <= Double.parseDouble(value2.value.toString()), Type.BOOLEAN);
            }
            env.setError("Los tipos no son válidos para operaciones relacionales", exp2.line, exp2.column);
            return new ReturnType("nulo", Type.NULL);
        }
        if(value1.type == value2.type && value1.type == Type.STRING) {
            return new ReturnType(value1.value.toString().compareTo(value2.value.toString()) <= 0, Type.BOOLEAN);
        }
        env.setError("Los tipos no son válidos para operaciones relacionales", exp1.line, exp1.column);
        return new ReturnType("nulo", Type.NULL);
    }
    public ReturnType greater(Env env) {
        ReturnType value1 = exp1.exec(env);
        ReturnType value2 = exp2.exec(env);
        if(value1.type == Type.INT || value1.type == Type.DOUBLE || value1.type == Type.CHAR) {
            if(value2.type == Type.INT || value2.type == Type.DOUBLE || value2.type == Type.CHAR) {
                value1 = getValue(value1);
                value2 = getValue(value2);
                return new ReturnType(Double.parseDouble(value1.value.toString()) > Double.parseDouble(value2.value.toString()), Type.BOOLEAN);
            }
            env.setError("Los tipos no son válidos para operaciones relacionales", exp2.line, exp2.column);
            return new ReturnType("nulo", Type.NULL);
        }
        if(value1.type == value2.type && value1.type == Type.STRING) {
            return new ReturnType(value1.value.toString().compareTo(value2.value.toString()) > 0, Type.BOOLEAN);
        }
        env.setError("Los tipos no son válidos para operaciones relacionales", exp1.line, exp1.column);
        return new ReturnType("nulo", Type.NULL);
    }
    public ReturnType less(Env env) {
        ReturnType value1 = exp1.exec(env);
        ReturnType value2 = exp2.exec(env);
        if(value1.type == Type.INT || value1.type == Type.DOUBLE || value1.type == Type.CHAR) {
            if(value2.type == Type.INT || value2.type == Type.DOUBLE || value2.type == Type.CHAR) {
                value1 = getValue(value1);
                value2 = getValue(value2);
                return new ReturnType(Double.parseDouble(value1.value.toString()) < Double.parseDouble(value2.value.toString()), Type.BOOLEAN);
            }
            env.setError("Los tipos no son válidos para operaciones relacionales", exp2.line, exp2.column);
            return new ReturnType("nulo", Type.NULL);
        }
        if(value1.type == value2.type && value1.type == Type.STRING) {
            return new ReturnType(value1.value.toString().compareTo(value2.value.toString()) < 0, Type.BOOLEAN);
        }
        env.setError("Los tipos no son válidos para operaciones relacionales", exp1.line, exp1.column);
        return new ReturnType("nulo", Type.NULL);
    }
    public ReturnType getValue(ReturnType value) {
        return value.type == Type.CHAR ? new ReturnType((int) value.value.toString().charAt(0), Type.INT) : value;
    }
    public ReturnType goGenerate(Env env, GoGen goGen) {
        String code = "";
        code += exp1.goGenerate(env, goGen).value.toString();
        code += " " + getSign() + " ";
        code += exp2.goGenerate(env, goGen).value.toString();
        if(isGrp) {
            code = "(" + code + ")";
        }
        return new ReturnType(code, Type.BOOLEAN);
    }
    public ReturnType pyGenerate(Env env, PyGen pyGen) {
        String code = "";
        code += exp1.pyGenerate(env, pyGen).value.toString();
        code += " " + getSign() + " ";
        code += exp2.pyGenerate(env, pyGen).value.toString();
        if(isGrp) {
            code = "(" + code + ")";
        }
        return new ReturnType(code, Type.BOOLEAN);
    }
    private String getSign() {
        switch (sign) {
            case "=":
                return "==";
            default:
                return sign;
        }
    }
}