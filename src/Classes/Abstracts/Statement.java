package Classes.Abstracts;
import Classes.Env.Env;
import Classes.Utils.ReturnType;
import Classes.Utils.TypeSent;
public abstract class Statement {
    public int line;
    public int column;
    public TypeSent typeSent;
    public Statement(int line, int column, TypeSent typeSent) {
        this.line = line;
        this.column = column;
        this.typeSent = typeSent;
    }
    public abstract ReturnType exec(Env env);
}