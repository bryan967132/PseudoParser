package Classes.Abstracts;
import Classes.Env.Env;
import Classes.Utils.ReturnType;
import Classes.Utils.TypeExp;
import Classes.Utils.TypeSent;
public abstract class Expression extends Sentence {
    public TypeExp typeExp;
    public Expression(int line, int column, TypeExp typeExp) {
        super(line, column, TypeSent.EXPRESSION);
        this.typeExp = typeExp;
    }
    public abstract ReturnType exec(Env env);
}