package Classes.Utils;
import java.util.ArrayList;
import Classes.Abstracts.Statement;
public class ElseIfStruct {
    public ArrayList<Statement> instructions;
    public Statement _if;
    public ElseIfStruct(ArrayList<Statement> instructions, Statement _if) {
        this.instructions = instructions;
        this._if = _if;
    }
}