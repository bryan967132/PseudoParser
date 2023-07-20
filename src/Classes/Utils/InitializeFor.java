package Classes.Utils;
import java.util.ArrayList;
import Classes.Instructions.AsignID;
import Classes.Instructions.InitID;
public class InitializeFor {
    public InitID inits;
    public ArrayList<AsignID> asigns;
    public InitializeFor(InitID inits) {
        this.inits = inits;
    }
    public InitializeFor(ArrayList<AsignID> asigns) {
        this.asigns = asigns;
    }
}