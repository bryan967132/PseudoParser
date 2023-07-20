package Classes.Utils;
import java.util.ArrayList;
import Classes.Abstracts.Sentence;
public class ElseIfStruct {
    public ArrayList<Sentence> instructions;
    public Sentence _if;
    public ElseIfStruct(ArrayList<Sentence> instructions, Sentence _if) {
        this.instructions = instructions;
        this._if = _if;
    }
}