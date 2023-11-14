package Classes.Instructions;
import java.util.ArrayList;
import Classes.Abstracts.Instruction;
import Classes.Env.Env;
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
}