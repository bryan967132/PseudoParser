package Classes.Instructions;
import Classes.Abstracts.Expression;
import Classes.Env.Env;
import Classes.Expressions.AccessID;
import Classes.Expressions.Primitive;
import Classes.Expressions.Relational;
import Classes.Utils.ReturnType;
import Classes.Utils.Type;
import Classes.Utils.TypeExp;
public class For extends Expression {
    private String id;
    private Expression v1;
    private Expression v2;
    private Expression v3;
    private Block instructions;
    public For(int line, int column, String id, Expression v1, Expression v2, Expression v3, Block instructions) {
        super(line, column, TypeExp.LOOP_FOR);
        this.id = id;
        this.v1 = v1;
        this.v2 = v2;
        this.v3 = v3 != null ? v3 : new Primitive(line, column, "1", Type.NUMBER);
        this.instructions = instructions;
    }
    public ReturnType exec(Env env) {
        Env envFor = new Env(env, env.name + " For");
        envFor.saveID(id, v1.exec(envFor).value, Type.NUMBER, line, column);
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
                if(block.value == TypeExp.CONTINUE) {
                    new AddSub(line, column, id, "+=", v3).exec(envFor);
                    condition = getConditionValue(envFor, sign, v2);
                }
                else if(block.value == TypeExp.BREAK) {
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
        if(Double.parseDouble(env.getValueID(id).value.toString()) >= Double.parseDouble(value.exec(env).value.toString())) {
            return "mayor igual que";
        }
        else if(Double.parseDouble(env.getValueID(id).value.toString()) <= Double.parseDouble(value.exec(env).value.toString())) {
            return "menor igual que";
        }
        return "";
    }
}