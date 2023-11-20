package Classes.Instructions;
import java.util.ArrayList;
import java.util.stream.Collectors;
import Classes.Abstracts.Instruction;
import Classes.Env.Env;
import Classes.Generators.GoGen;
import Classes.Generators.PyGen;
import Classes.Utils.Parameter;
import Classes.Utils.Type;
import Classes.Utils.TypeInst;
public class Function extends Instruction {
    public String id;
    public ArrayList<Parameter> params;
    public Block block;
    public Type type;
    public Function(int line, int column, String id, ArrayList<Parameter> params, Block block, Type type) {
        super(line, column, TypeInst.INIT_FUNCTION);
        this.id = id;
        this.params = params;
        this.block = block;
        this.type = type;
    }
    public void exec(Env env) {
        env.saveFunction(this);
    }
    public Type getType() {
        return type;
    }
    public void goGenerate(Env env, GoGen goGen) {
        String code = "func " + id + "(";
        code += params.stream().map(Parameter::goCode).collect(Collectors.joining(", "));
        Env envFunc = new Env(env.getGlobal(), "Funcion " + id);
        for(Parameter param : params) {
            envFunc.saveID(param.id, null, param.type, line, column);
        }
        code += ")";
        if(type != Type.NULL) {
            code += " " + getGoType();
        }
        code += " {";
        goGen.addInstruction(code);
        block.goGenerate(envFunc, goGen);
        code = "}";
        goGen.addInstruction(code);
    }
    public void pyGenerate(Env env, PyGen pyGen) {
        String code = "def " + id + "(";
        code += params.stream().map(Parameter::pyCode).collect(Collectors.joining(", "));
        Env envFunc = new Env(env.getGlobal(), "Funcion " + id);
        for(Parameter param : params) {
            envFunc.saveID(param.id, null, param.type, line, column);
        }
        code += ")";
        if(type != Type.NULL) {
            code += " -> " + getPyType();
        }
        code += ":";
        pyGen.addInstruction(code);
        pyGen.isTemporal();
        block.pyGenerate(envFunc, pyGen);
        // GLOBALS
        pyGen.isNotTemporal();
        pyGen.newEnv();
        for(String global : envFunc.getGlobals()) {
            pyGen.addInstruction("global " + global);
        }
        pyGen.prevEnv();
        // END GLOBALS
        pyGen.addLocal();
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