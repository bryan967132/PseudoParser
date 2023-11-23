package Classes.Instructions;
import java.util.ArrayList;
import Classes.Abstracts.Expression;
import Classes.Abstracts.Instruction;
import Classes.Env.Env;
import Classes.Generators.GoGen;
import Classes.Generators.PyGen;
import Classes.Utils.ReturnType;
import Classes.Utils.TypeInst;
public class AsignID extends Instruction {
    private ArrayList<String> ids;
    private Expression value;
    public AsignID(int line, int column, ArrayList<String> ids, Expression value) {
        super(line, column, TypeInst.ASIGN_ID);
        this.ids = ids;
        this.value = value;
    }
    public void exec(Env env) {
        ReturnType value = this.value.exec(env);
        for(String id : ids) {
            env.reasignID(id, value, this.line, this.column);
        }
    }
    public void goGenerate(Env env, GoGen goGen) {
        ArrayList<String> values = new ArrayList<>();
        for(int i = 0; i < ids.size(); i ++) {
            values.add(value.goGenerate(env, goGen).value.toString());
        }
        goGen.addInstruction(String.join(", ", ids) + " = " + String.join(", ", values));
    }
    public void pyGenerate(Env env, PyGen pyGen) {
        ArrayList<String> values = new ArrayList<>();
        for(int i = 0; i < ids.size(); i ++) {
            values.add(value.pyGenerate(env, pyGen).value.toString());
        }
        pyGen.addInstruction(String.join(", ", ids) + " = " + String.join(", ", values));
    }
}