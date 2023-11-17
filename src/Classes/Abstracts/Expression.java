package Classes.Abstracts;
import Classes.Env.Env;
import Classes.Generators.GoGen;
import Classes.Generators.PyGen;
import Classes.Utils.ReturnType;
import Classes.Utils.TypeExp;
import Classes.Utils.TypeSent;
public abstract class Expression extends Sentence {
    public boolean isGrp = false;
    public TypeExp typeExp;
    public boolean isInstruction = false;
    public Expression(int line, int column, TypeExp typeExp) {
        super(line, column, TypeSent.EXPRESSION);
        this.typeExp = typeExp;
    }
    public abstract ReturnType exec(Env env);
    public abstract ReturnType goGenerate(Env env, GoGen goGen);
    public abstract ReturnType pyGenerate(Env env, PyGen pyGen);
}