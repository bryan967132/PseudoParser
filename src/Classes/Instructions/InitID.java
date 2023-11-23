package Classes.Instructions;
import java.util.ArrayList;
import Classes.Abstracts.Expression;
import Classes.Abstracts.Instruction;
import Classes.Env.Env;
import Classes.Generators.GoGen;
import Classes.Generators.PyGen;
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
    public ReturnType exec(Env env) {
        for(String id : inits) {
            if(value != null) {
                ReturnType value = this.value.exec(env);
                if(value.type == type || type == Type.DOUBLE && value.type == Type.INT) {
                    env.saveID(id, value.value, type, line, column);
                    continue;
                }
                env.setError("Los tipos no coinciden en la declaración", line, column);
            }
            else {
                switch(type) {
                    case INT:
                        env.saveID(id, 0, type, line, column);
                        break;
                    case DOUBLE:
                        env.saveID(id, 0.0, type, line, column);
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
        return null;
    }
    public void goGenerate(Env env, GoGen goGen) {
        ArrayList<String> ids = new ArrayList<>();
        ArrayList<String> values = new ArrayList<>();
        for(String id : inits) {
            if(!env.name.equals("Global")) {
                env.saveID(id, null, type, line, column);
            }
            ids.add(id);
            if(value != null) {
                values.add(value.goGenerate(env, goGen).value.toString());
            }
            else {
                switch(type) {
                    case INT:
                        values.add("0");
                        break;
                    case DOUBLE:
                        values.add("0.0");
                        break;
                    case BOOLEAN:
                        values.add("true");
                        break;
                    case CHAR:
                        values.add("'0'");
                        break;
                    case STRING:
                        values.add("\"\"");
                        break;
                    default:
                        break;
                }
            }
        }
        goGen.addInstruction("var " + String.join(", ", ids) + " " + getGoType() + " = " + String.join(", ", values));
    }
    public void pyGenerate(Env env, PyGen pyGen) {
        ArrayList<String> ids = new ArrayList<>();
        ArrayList<String> values = new ArrayList<>();
        for(String id : inits) {
            if(!env.name.equals("Global")) {
                env.saveID(id, null, type, line, column);
            }
            ids.add(id);
            if(value != null) {
                values.add(value.pyGenerate(env, pyGen).value.toString());
            }
            else {
                switch(type) {
                    case INT:
                        values.add("0");
                        break;
                    case DOUBLE:
                        values.add("0.0");
                        break;
                    case BOOLEAN:
                        values.add("True");
                        break;
                    case CHAR:
                        values.add("'0'");
                        break;
                    case STRING:
                        values.add("''");
                        break;
                    default:
                        break;
                }
            }
        }
        pyGen.addInstruction(String.join(", ", ids) + (ids.size() > 1 ? "" : ": " + getPyType()) + " = " + String.join(", ", values));
    }
    private String getGoType() {
        switch(type) {
            case INT:
                return "int";
            case DOUBLE:
                return "float64";
            case BOOLEAN:
                return "bool";
            case STRING:
                return "string";
            case CHAR:
                return "rune";
            default:
                return "";
        }
    }
    private String getPyType() {
        switch(type) {
            case INT:
                return "int";
            case DOUBLE:
                return "float";
            case BOOLEAN:
                return "bool";
            case STRING:
                return "str";
            case CHAR:
                return "chr";
            default:
                return "";
        }
    }
}