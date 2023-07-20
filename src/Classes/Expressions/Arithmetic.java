package Classes.Expressions;
import Classes.Abstracts.Expression;
import Classes.Env.Env;
import Classes.Utils.Operations;
import Classes.Utils.ReturnType;
import Classes.Utils.Type;
import Classes.Utils.TypeExp;
public class Arithmetic extends Expression {
    private Type type;
    private Expression exp1;
    private String sign;
    private Expression exp2;
    public Arithmetic(int line, int column, Expression exp1, String sign, Expression exp2) {
        super(line, column, TypeExp.ARITHMETIC_OP);
        this.exp1 = exp1;
        this.sign = sign;
        this.exp2 = exp2;
        this.type = Type.NULL;
    }
    public ReturnType exec(Env env) {
        switch(this.sign) {
            case "+":
                return plus(env);
            case "-":
                if(exp1 != null) {
                    return minus(env);
                }
                return uminus(env);
            case "*":
                return mult(env);
            case "/":
                return div(env);
            case "potencia":
                return pow(env);
            case "modulo":
                return mod(env);
            default:
                return new ReturnType(-1, Type.NULL);
        }
    }
    public ReturnType plus(Env env) {
        ReturnType value1 = exp1.exec(env);
        ReturnType value2 = exp2.exec(env);
        int t1 = getType(value1.type);
        int t2 = getType(value2.type);
        type = !(t1 == 5 || t2 == 5) ? Operations.plus[t1][t2] : Type.NULL;
        if(type != Type.NULL) {
            if(type == Type.NUMBER) {
                double result = Double.parseDouble(getValue(value1).value.toString()) + Double.parseDouble(getValue(value2).value.toString());
                return new ReturnType(result, type);
            }
            if(type == Type.STRING) {
                String result = getValueB(value1) + getValueB(value2);
                return new ReturnType(result, type);
            }
        }
        return new ReturnType(-1, type);
    }
    public ReturnType minus(Env env) {
        ReturnType value1 = exp1.exec(env);
        ReturnType value2 = exp2.exec(env);
        int t1 = getType(value1.type);
        int t2 = getType(value2.type);
        type = !(t1 == 5 || t2 == 5) ? Operations.minus[t1][t2] : Type.NULL;
        if(type != Type.NULL) {
            if(type == Type.NUMBER) {
                double result = Double.parseDouble(getValue(value1).value.toString()) - Double.parseDouble(getValue(value2).value.toString());
                return new ReturnType(result, type);
            }
        }
        return new ReturnType(-1, type);
    }
    public ReturnType uminus(Env env) {
        ReturnType value2 = exp2.exec(env);
        type = value2.type;
        if(type != Type.NULL) {
            if(type == Type.NUMBER) {
                double result = - Double.parseDouble(getValue(value2).value.toString());
                return new ReturnType(result, type);
            }
        }
        return new ReturnType(-1, type);
    }
    public ReturnType mult(Env env) {
        ReturnType value1 = exp1.exec(env);
        ReturnType value2 = exp2.exec(env);
        int t1 = getType(value1.type);
        int t2 = getType(value2.type);
        type = !(t1 == 5 || t2 == 5) ? Operations.mult[t1][t2] : Type.NULL;
        if(type != Type.NULL) {
            if(type == Type.NUMBER) {
                double result = Double.parseDouble(getValue(value1).value.toString()) * Double.parseDouble(getValue(value2).value.toString());
                return new ReturnType(result, type);
            }
        }
        return new ReturnType(-1, type);
    }
    public ReturnType div(Env env) {
        ReturnType value1 = exp1.exec(env);
        ReturnType value2 = exp2.exec(env);
        int t1 = getType(value1.type);
        int t2 = getType(value2.type);
        type = !(t1 == 5 || t2 == 5) ? Operations.div[t1][t2] : Type.NULL;
        if(type != Type.NULL) {
            if(type == Type.NUMBER) {
                double result = Double.parseDouble(getValue(value1).value.toString()) / Double.parseDouble(getValue(value2).value.toString());
                return new ReturnType(result, type);
            }
        }
        return new ReturnType(-1, type);
    }
    public ReturnType pow(Env env) {
        ReturnType value1 = exp1.exec(env);
        ReturnType value2 = exp2.exec(env);
        int t1 = getType(value1.type);
        int t2 = getType(value2.type);
        type = !(t1 == 5 || t2 == 5) ? Operations.pow[t1][t2] : Type.NULL;
        if(type != Type.NULL) {
            if(type == Type.NUMBER) {
                double result = Math.pow(Double.parseDouble(getValue(value1).value.toString()), Double.parseDouble(getValue(value2).value.toString()));
                return new ReturnType(result, type);
            }
        }
        return new ReturnType(-1, type);
    }
    public ReturnType mod(Env env) {
        ReturnType value1 = exp1.exec(env);
        ReturnType value2 = exp2.exec(env);
        int t1 = getType(value1.type);
        int t2 = getType(value2.type);
        type = !(t1 == 5 || t2 == 5) ? Operations.mod[t1][t2] : Type.NULL;
        if(type != Type.NULL) {
            if(type == Type.NUMBER) {
                double result = Double.parseDouble(getValue(value1).value.toString()) % Double.parseDouble(getValue(value2).value.toString());
                return new ReturnType(result, type);
            }
        }
        return new ReturnType(-1, type);
    }
    public int getType(Type type) {
        if(type == Type.NUMBER) return 0;
        if(type == Type.BOOLEAN) return 1;
        if(type == Type.CHAR) return 2;
        if(type == Type.STRING) return 3;
        return 5;
    }
    public String getValueB(ReturnType value) {
        if(value.type == Type.BOOLEAN) {
            if(Boolean.parseBoolean(value.value.toString())) {
                return "Verdadero";
            }
            return "Falso";
        }
        return value.value.toString();
    }
    public ReturnType getValue(ReturnType value) {
        if(value.type == Type.BOOLEAN) {
            if(value.value.toString().equals("true")) {
                return new ReturnType(1, Type.NUMBER);
            }
            return new ReturnType(0, Type.NUMBER);
        }
        if(value.type == Type.CHAR) {
            return new ReturnType((int) value.value.toString().charAt(0), Type.NUMBER);
        }
        return value;
    }
}