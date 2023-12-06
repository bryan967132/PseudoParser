package Classes.Instructions;
import Classes.Abstracts.Expression;
import Classes.Abstracts.Instruction;
import Classes.Env.Env;
import Classes.Expressions.AccessID;
import Classes.Expressions.Primitive;
import Classes.Expressions.Relational;
import Classes.Generators.GoGen;
import Classes.Generators.PyGen;
import Classes.Utils.ReturnType;
import Classes.Utils.Type;
import Classes.Utils.TypeInst;
public class For extends Instruction {
    private String id;
    private Expression v1;
    private Expression v2;
    private Expression v3;
    private Block instructions;
    public For(int line, int column, String id, Expression v1, Expression v2, Expression v3, Block instructions) {
        super(line, column, TypeInst.LOOP_FOR);
        this.id = id;
        this.v1 = v1;
        this.v2 = v2;
        this.v3 = v3 != null ? v3 : new Primitive(line, column, "1", Type.INT);
        this.instructions = instructions;
    }
    public ReturnType exec(Env env) {
        Env envFor = new Env(env, env.name + " For");
        envFor.saveID(id, v1.exec(envFor).value, Type.INT, line, column);
        String sign = getRelational(envFor, v2);
        if(sign.equals("mayor igual que") && Double.parseDouble(v3.exec(envFor).value.toString()) >= 0) {
            return null;
        }
        else if(sign.equals("menor igual que") && Double.parseDouble(v3.exec(envFor).value.toString()) <= 0) {
            return null;
        }
        Boolean condition = getConditionValue(envFor, sign, v2);
        while(condition) {
            ReturnType block = instructions.exec(envFor);
            if(block != null) {
                if(block.value == TypeInst.CONTINUE) {
                    new AddSub(line, column, id, "+=", v3).exec(envFor);
                    condition = getConditionValue(envFor, sign, v2);
                    continue;
                }
                else if(block.value == TypeInst.BREAK) {
                    break;
                }
                return block;
            }
            new AddSub(line, column, id, "+=", v3).exec(envFor);
            condition = getConditionValue(envFor, sign, v2);
        }
        return null;
    }
    public boolean getConditionValue(Env env, String sign, Expression value) {
        if(sign.equals("mayor igual que")) {
            return Boolean.parseBoolean(new Relational(line, column, new AccessID(line, column, id), "mayor igual que", this.v2).exec(env).value.toString());
        }
        else if(sign.equals("menor igual que")) {
            return Boolean.parseBoolean(new Relational(line, column, new AccessID(line, column, id), "menor igual que", this.v2).exec(env).value.toString());
        }
        return false;
    }
    public String getRelational(Env env, Expression value) {
        if(Double.parseDouble(env.getValueID(id, this.line, this.column).value.toString()) >= Double.parseDouble(value.exec(env).value.toString())) {
            return "mayor igual que";
        }
        else if(Double.parseDouble(env.getValueID(id, this.line, this.column).value.toString()) <= Double.parseDouble(value.exec(env).value.toString())) {
            return "menor igual que";
        }
        return "";
    }
    public void goGenerate(Env env, GoGen goGen) {
        Env envFor = new Env(env, env.name + " For");
        envFor.saveID(id, v1.exec(envFor).value, Type.INT, line, column);
        String sign = getRelational(envFor, v2);
        sign = sign.equals("mayor igual que") ? ">=" : "<=";
        String v1 = this.v1.goGenerate(envFor, goGen).value.toString();
        String v2 = this.v2.goGenerate(envFor, goGen).value.toString();
        String v3 = this.v3.goGenerate(envFor, goGen).value.toString();
        goGen.addInstruction("for " + id + " := " + v1 + "; " + id + " " + sign + " " + v2 + "; " + id + " += " + v3 + " {");
        instructions.goGenerate(envFor, goGen);
        goGen.addInstruction("}");
    }
    public void pyGenerate(Env env, PyGen pyGen) {
        Env envFor = new Env(env, env.name + " For");
        envFor.saveID(id, v1.exec(envFor).value, Type.INT, line, column);
        String sign = getRelational(envFor, v2);
        sign = sign.equals("mayor igual que") ? ">=" : "<=";
        String v1 = this.v1.pyGenerate(envFor, pyGen).value.toString();
        String v2 = this.v2.pyGenerate(envFor, pyGen).value.toString();
        String v3 = this.v3.pyGenerate(envFor, pyGen).value.toString();
        pyGen.addInstruction("for " + id + " in range(" + v1 + ", " + v2 + " + 1, " + v3 + "):");
        instructions.pyGenerate(envFor, pyGen);
    }
}