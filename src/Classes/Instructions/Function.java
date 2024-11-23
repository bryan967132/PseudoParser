package Classes.Instructions;
import java.util.ArrayList;
import java.util.stream.Collectors;
import Classes.Abstracts.Instruction;
import Classes.Env.Env;
import Classes.Generators.GoGen;
import Classes.Generators.PyGen;
import Classes.Utils.Parameter;
import Classes.Utils.ReturnType;
import Classes.Utils.Type;
import Classes.Utils.TypeInst;
public class Function extends Instruction {
    public String id;
    public ArrayList<Parameter> params;
    public Block block;
    public Type type;
    public Function(int line, int column, String id, ArrayList<Parameter> params, Block block, Type type) {
        super(line, column, TypeInst.INITFUNC);
        this.id = id;
        this.params = params;
        this.block = block;
        this.type = type;
    }
    public ReturnType exec(Env env) {
        env.saveFunction(this);
        return null;
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
            code += " " + type.getGoValue();
        }
        code += " {";
        goGen.addInstruction(code);
        block.goGenerate(envFunc, goGen);
        code = "}\n";
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
            code += " -> " + type.getPyValue();
        }
        code += ":";
        pyGen.addInstruction(code);
        pyGen.isTemporal();
        block.pyGenerate(envFunc, pyGen);
        pyGen.addInstruction("");
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
}