package Classes.Expressions;
import Classes.Abstracts.Expression;
import Classes.Env.Env;
import Classes.Generators.GoGen;
import Classes.Generators.PyGen;
import Classes.Utils.Operations;
import Classes.Utils.ReturnType;
import Classes.Utils.Type;
import Classes.Utils.TypeExp;
public class Arithmetic extends Expression {
    private Expression exp1;
    private String sign;
    private Expression exp2;
    private Type type;
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
                return new ReturnType("nulo", Type.NULL);
        }
    }
    public ReturnType plus(Env env) {
        ReturnType value1 = exp1.exec(env);
        ReturnType value2 = exp2.exec(env);
        int t1 = value1.type.ordinal();
        int t2 = value2.type.ordinal();
        type = !(t1 == 5 || t2 == 5) ? Operations.plus[t1][t2] : Type.NULL;
        if(type != Type.NULL) {
            if(type == Type.INT) {
                int result = Integer.parseInt(getValue(value1).value.toString()) + Integer.parseInt(getValue(value2).value.toString());
                return new ReturnType(result, type);
            }
            if(type == Type.DOUBLE) {
                double result = Double.parseDouble(getValue(value1).value.toString()) + Double.parseDouble(getValue(value2).value.toString());
                return new ReturnType(result, type);
            }
            if(type == Type.STRING) {
                String result = value1.value.toString() + value2.value.toString();
                return new ReturnType(result, type);
            }
        }
        env.setError("Los tipos no son válidos para operaciones aritméticas", exp1.line, exp1.column);
        return new ReturnType("nulo", type);
    }
    public ReturnType minus(Env env) {
        ReturnType value1 = exp1.exec(env);
        ReturnType value2 = exp2.exec(env);
        int t1 = value1.type.ordinal();
        int t2 = value2.type.ordinal();
        type = !(t1 == 5 || t2 == 5) ? Operations.minus[t1][t2] : Type.NULL;
        if(type != Type.NULL) {
            if(type == Type.INT) {
                int result = Integer.parseInt(getValue(value1).value.toString()) - Integer.parseInt(getValue(value2).value.toString());
                return new ReturnType(result, type);
            }
            if(type == Type.DOUBLE) {
                double result = Double.parseDouble(getValue(value1).value.toString()) - Double.parseDouble(getValue(value2).value.toString());
                return new ReturnType(result, type);
            }
        }
        env.setError("Los tipos no son válidos para operaciones aritméticas", exp1.line, exp1.column);
        return new ReturnType("nulo", type);
    }
    public ReturnType uminus(Env env) {
        ReturnType value2 = exp2.exec(env);
        type = value2.type;
        if(type != Type.NULL) {
            if(type == Type.INT) {
                int result = - Integer.parseInt(getValue(value2).value.toString());
                return new ReturnType(result, type);
            }
            if(type == Type.DOUBLE) {
                double result = - Double.parseDouble(getValue(value2).value.toString());
                return new ReturnType(result, type);
            }
        }
        env.setError("Los tipos no son válidos para operaciones aritméticas", exp2.line, exp2.column);
        return new ReturnType("nulo", type);
    }
    public ReturnType mult(Env env) {
        ReturnType value1 = exp1.exec(env);
        ReturnType value2 = exp2.exec(env);
        int t1 = value1.type.ordinal();
        int t2 = value2.type.ordinal();
        type = !(t1 == 5 || t2 == 5) ? Operations.mult[t1][t2] : Type.NULL;
        if(type != Type.NULL) {
            if(type == Type.INT) {
                int result = Integer.parseInt(getValue(value1).value.toString()) * Integer.parseInt(getValue(value2).value.toString());
                return new ReturnType(result, type);
            }
            if(type == Type.DOUBLE) {
                double result = Double.parseDouble(getValue(value1).value.toString()) * Double.parseDouble(getValue(value2).value.toString());
                return new ReturnType(result, type);
            }
        }
        env.setError("Los tipos no son válidos para operaciones aritméticas", exp1.line, exp1.column);
        return new ReturnType("nulo", type);
    }
    public ReturnType div(Env env) {
        ReturnType value1 = exp1.exec(env);
        ReturnType value2 = exp2.exec(env);
        int t1 = value1.type.ordinal();
        int t2 = value2.type.ordinal();
        type = !(t1 == 5 || t2 == 5) ? Operations.div[t1][t2] : Type.NULL;
        if(type != Type.NULL) {
            if(type == Type.DOUBLE) {
                double result = Double.parseDouble(getValue(value1).value.toString()) / Double.parseDouble(getValue(value2).value.toString());
                return new ReturnType(result, type);
            }
        }
        env.setError("Los tipos no son válidos para operaciones aritméticas", exp1.line, exp1.column);
        return new ReturnType("nulo", type);
    }
    public ReturnType pow(Env env) {
        ReturnType value1 = exp1.exec(env);
        ReturnType value2 = exp2.exec(env);
        int t1 = value1.type.ordinal();
        int t2 = value2.type.ordinal();
        type = !(t1 == 5 || t2 == 5) ? Operations.pow[t1][t2] : Type.NULL;
        if(type != Type.NULL) {
            if(type == Type.INT) {
                int result = (int) Math.pow(Integer.parseInt(getValue(value1).value.toString()), Integer.parseInt(getValue(value2).value.toString()));
                return new ReturnType(result, type);
            }
            if(type == Type.DOUBLE) {
                double result = Math.pow(Double.parseDouble(getValue(value1).value.toString()), Double.parseDouble(getValue(value2).value.toString()));
                return new ReturnType(result, type);
            }
        }
        env.setError("Los tipos no son válidos para operaciones aritméticas", exp1.line, exp1.column);
        return new ReturnType("nulo", type);
    }
    public ReturnType mod(Env env) {
        ReturnType value1 = exp1.exec(env);
        ReturnType value2 = exp2.exec(env);
        int t1 = value1.type.ordinal();
        int t2 = value2.type.ordinal();
        type = !(t1 == 5 || t2 == 5) ? Operations.mod[t1][t2] : Type.NULL;
        if(type != Type.NULL) {
            if(type == Type.DOUBLE) {
                double result = Double.parseDouble(getValue(value1).value.toString()) % Double.parseDouble(getValue(value2).value.toString());
                return new ReturnType(result, type);
            }
            if(type == Type.INT) {
                int result = Integer.parseInt(getValue(value1).value.toString()) % Integer.parseInt(getValue(value2).value.toString());
                return new ReturnType(result, type);
            }
        }
        env.setError("Los tipos no son válidos para operaciones aritméticas", exp1.line, exp1.column);
        return new ReturnType("nulo", type);
    }
    public ReturnType getValue(ReturnType value) {
        if(value.type == Type.BOOLEAN) {
            if(value.value.toString().equals("true")) {
                return new ReturnType(1, Type.INT);
            }
            return new ReturnType(0, Type.INT);
        }
        if(value.type == Type.CHAR) {
            return new ReturnType((int) value.value.toString().charAt(0), Type.INT);
        }
        return value;
    }
    public ReturnType goGenerate(Env env, GoGen goGen) {
        switch(this.sign) {
            case "+":
                return plusGo(env, goGen);
            case "-":
                if(exp1 != null) {
                    return minusGo(env, goGen);
                }
                return uminusGo(env, goGen);
            case "*":
                return multGo(env, goGen);
            case "/":
                return divGo(env, goGen);
            case "potencia":
                return powGo(env, goGen);
            case "modulo":
                return modGo(env, goGen);
            default:
                return new ReturnType("nil", Type.NULL);
        }
    }
    private ReturnType plusGo(Env env, GoGen goGen) {
        ReturnType value1 = exp1.goGenerate(env, goGen);
        ReturnType value2 = exp2.goGenerate(env, goGen);
        int t1 = value1.type.ordinal();
        int t2 = value2.type.ordinal();
        type = !(t1 == 5 || t2 == 5) ? Operations.plus[t1][t2] : Type.NULL;
        if(type != Type.NULL) {
            if(type != Type.STRING) {
                String result = value1.value.toString() + " + " + value2.value.toString();
                if(isGrp) {
                    result = "(" + result + ")";
                }
                return new ReturnType(result, type);
            }
            String result1 = value1.value.toString();
            if(value1.type == Type.INT) {
                result1 = itoa(goGen, result1);
            } else if(value1.type == Type.DOUBLE) {
                result1 = formatFloat(goGen, result1);
            }
            String result2 = value2.value.toString();
            if(value2.type == Type.INT) {
                result2 = itoa(goGen, result2);
            } else if(value2.type == Type.DOUBLE) {
                result2 = formatFloat(goGen, result2);
            }
            String result = result1 + " + " + result2;
            if(isGrp) {
                result = "(" + result + ")";
            }
            return new ReturnType(result, type);
        }
        return new ReturnType("nil", Type.NULL);
    }
    private ReturnType minusGo(Env env, GoGen goGen) {
        ReturnType value1 = exp1.goGenerate(env, goGen);
        ReturnType value2 = exp2.goGenerate(env, goGen);
        int t1 = value1.type.ordinal();
        int t2 = value2.type.ordinal();
        type = !(t1 == 5 || t2 == 5) ? Operations.minus[t1][t2] : Type.NULL;
        if(type != Type.NULL) {
            String result = value1.value.toString() + " - " + value2.value.toString();
            if(isGrp) {
                result = "(" + result + ")";
            }
            return new ReturnType(result, type);
        }
        return new ReturnType("nil", type);
    }
    private ReturnType uminusGo(Env env, GoGen goGen) {
        ReturnType value2 = exp2.goGenerate(env, goGen);
        type = value2.type;
        if(type != Type.NULL) {
            String result = "- " + value2.value.toString();
            if(isGrp) {
                result = "(" + result + ")";
            }
            return new ReturnType(result, type);
        }
        return new ReturnType("nil", type);
    }
    private ReturnType multGo(Env env, GoGen goGen) {
        ReturnType value1 = exp1.goGenerate(env, goGen);
        ReturnType value2 = exp2.goGenerate(env, goGen);
        int t1 = value1.type.ordinal();
        int t2 = value2.type.ordinal();
        type = !(t1 == 5 || t2 == 5) ? Operations.minus[t1][t2] : Type.NULL;
        if(type != Type.NULL) {
            String result = value1.value.toString() + " * " + value2.value.toString();
            if(isGrp) {
                result = "(" + result + ")";
            }
            return new ReturnType(result, type);
        }
        return new ReturnType("nil", type);
    }
    private ReturnType divGo(Env env, GoGen goGen) {
        ReturnType value1 = exp1.goGenerate(env, goGen);
        ReturnType value2 = exp2.goGenerate(env, goGen);
        int t1 = value1.type.ordinal();
        int t2 = value2.type.ordinal();
        type = !(t1 == 5 || t2 == 5) ? Operations.minus[t1][t2] : Type.NULL;
        if(type != Type.NULL) {
            String result = value1.value.toString() + " / " + value2.value.toString();
            if(isGrp) {
                result = "(" + result + ")";
            }
            return new ReturnType(result, type);
        }
        return new ReturnType("nil", type);
    }
    private ReturnType powGo(Env env, GoGen goGen) {
        ReturnType value1 = exp1.goGenerate(env, goGen);
        ReturnType value2 = exp2.goGenerate(env, goGen);
        int t1 = value1.type.ordinal();
        int t2 = value2.type.ordinal();
        type = !(t1 == 5 || t2 == 5) ? Operations.minus[t1][t2] : Type.NULL;
        if(type != Type.NULL) {
            goGen.importMath();
            String result = "math.Pow(" + value1.value.toString() + ", " + value2.value.toString() + ")";
            if(isGrp) {
                result = "(" + result + ")";
            }
            return new ReturnType(result, type);
        }
        return new ReturnType("nil", type);
    }
    private ReturnType modGo(Env env, GoGen goGen) {
        ReturnType value1 = exp1.goGenerate(env, goGen);
        ReturnType value2 = exp2.goGenerate(env, goGen);
        int t1 = value1.type.ordinal();
        int t2 = value2.type.ordinal();
        type = !(t1 == 5 || t2 == 5) ? Operations.minus[t1][t2] : Type.NULL;
        if(type != Type.NULL) {
            String result;
            if(type == Type.INT) {
                result = value1.value.toString() + " % " + value2.value.toString();
            } else {
                goGen.importMath();
                result = "math.Mod(" + value1.value.toString() + ", " + value2.value.toString() + ")";
            }
            if(isGrp) {
                result = "(" + result + ")";
            }
            return new ReturnType(result, type);
        }
        return new ReturnType("nil", type);
    }
    private String itoa(GoGen goGen, String value) {
        goGen.importStrconv();
        return "strconv.Itoa(" + value + ")";
    }
    private String formatFloat(GoGen goGen, String value) {
        goGen.importStrconv();
        return "strconv.FormatFloat(" + value + ", 'f', -1, 64)";
    }
    public ReturnType pyGenerate(Env env, PyGen pyGen) {
        switch(this.sign) {
            case "+":
                return plusPy(env, pyGen);
            case "-":
                if(exp1 != null) {
                    return minusPy(env, pyGen);
                }
                return uminusPy(env, pyGen);
            case "*":
                return multPy(env, pyGen);
            case "/":
                return divPy(env, pyGen);
            case "potencia":
                return powPy(env, pyGen);
            case "modulo":
                return modPy(env, pyGen);
            default:
                return new ReturnType("None", Type.NULL);
        }
    }
    private ReturnType plusPy(Env env, PyGen pyGen) {
        ReturnType value1 = exp1.pyGenerate(env, pyGen);
        ReturnType value2 = exp2.pyGenerate(env, pyGen);
        int t1 = value1.type.ordinal();
        int t2 = value2.type.ordinal();
        type = !(t1 == 5 || t2 == 5) ? Operations.plus[t1][t2] : Type.NULL;
        if(type != Type.NULL) {
            if(type != Type.STRING) {
                String result = value1.value.toString() + " + " + value2.value.toString();
                if(isGrp) {
                    result = "(" + result + ")";
                }
                return new ReturnType(result, type);
            }
            String result1 = value1.value.toString();
            if(value1.type == Type.INT || value1.type == Type.DOUBLE) {
                result1 = str(result1);
            }
            String result2 = value2.value.toString();
            if(value2.type == Type.INT || value2.type == Type.DOUBLE) {
                result2 = str(result2);
            }
            String result = result1 + " + " + result2;
            if(isGrp) {
                result = "(" + result + ")";
            }
            return new ReturnType(result, type);
        }
        return new ReturnType("None", Type.NULL);
    }
    private ReturnType minusPy(Env env, PyGen pyGen) {
        ReturnType value1 = exp1.pyGenerate(env, pyGen);
        ReturnType value2 = exp2.pyGenerate(env, pyGen);
        int t1 = value1.type.ordinal();
        int t2 = value2.type.ordinal();
        type = !(t1 == 5 || t2 == 5) ? Operations.minus[t1][t2] : Type.NULL;
        if(type != Type.NULL) {
            String result = value1.value.toString() + " - " + value2.value.toString();
            if(isGrp) {
                result = "(" + result + ")";
            }
            return new ReturnType(result, type);
        }
        return new ReturnType("None", type);
    }
    private ReturnType uminusPy(Env env, PyGen pyGen) {
        ReturnType value2 = exp2.pyGenerate(env, pyGen);
        type = value2.type;
        if(type != Type.NULL) {
            String result = "- " + value2.value.toString();
            if(isGrp) {
                result = "(" + result + ")";
            }
            return new ReturnType(result, type);
        }
        return new ReturnType("None", type);
    }
    private ReturnType multPy(Env env, PyGen pyGen) {
        ReturnType value1 = exp1.pyGenerate(env, pyGen);
        ReturnType value2 = exp2.pyGenerate(env, pyGen);
        int t1 = value1.type.ordinal();
        int t2 = value2.type.ordinal();
        type = !(t1 == 5 || t2 == 5) ? Operations.minus[t1][t2] : Type.NULL;
        if(type != Type.NULL) {
            String result = value1.value.toString() + " * " + value2.value.toString();
            if(isGrp) {
                result = "(" + result + ")";
            }
            return new ReturnType(result, type);
        }
        return new ReturnType("None", type);
    }
    private ReturnType divPy(Env env, PyGen pyGen) {
        ReturnType value1 = exp1.pyGenerate(env, pyGen);
        ReturnType value2 = exp2.pyGenerate(env, pyGen);
        int t1 = value1.type.ordinal();
        int t2 = value2.type.ordinal();
        type = !(t1 == 5 || t2 == 5) ? Operations.minus[t1][t2] : Type.NULL;
        if(type != Type.NULL) {
            String result = value1.value.toString() + " / " + value2.value.toString();
            if(isGrp) {
                result = "(" + result + ")";
            }
            return new ReturnType(result, type);
        }
        return new ReturnType("None", type);
    }
    private ReturnType powPy(Env env, PyGen pyGen) {
        ReturnType value1 = exp1.pyGenerate(env, pyGen);
        ReturnType value2 = exp2.pyGenerate(env, pyGen);
        int t1 = value1.type.ordinal();
        int t2 = value2.type.ordinal();
        type = !(t1 == 5 || t2 == 5) ? Operations.minus[t1][t2] : Type.NULL;
        if(type != Type.NULL) {
            String result = value1.value.toString() + " ** " + value2.value.toString();
            if(isGrp) {
                result = "(" + result + ")";
            }
            return new ReturnType(result, type);
        }
        return new ReturnType("None", type);
    }
    private ReturnType modPy(Env env, PyGen pyGen) {
        ReturnType value1 = exp1.pyGenerate(env, pyGen);
        ReturnType value2 = exp2.pyGenerate(env, pyGen);
        int t1 = value1.type.ordinal();
        int t2 = value2.type.ordinal();
        type = !(t1 == 5 || t2 == 5) ? Operations.minus[t1][t2] : Type.NULL;
        if(type != Type.NULL) {
            String result = value1.value.toString() + " % " + value2.value.toString();
            if(isGrp) {
                result = "(" + result + ")";
            }
            return new ReturnType(result, type);
        }
        return new ReturnType("None", type);
    }
    private String str(String value) {
        return "str(" + value + ")";
    }
}