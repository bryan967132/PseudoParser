package Classes.Generators;
import java.util.ArrayList;
public class PyGen {
    private int tabs = 0;
    private String mainCall = "";
    private boolean isLocal = false;
    private ArrayList<String> pyCode = new ArrayList<>();
    private ArrayList<String> temporal = new ArrayList<>();
    private ArrayList<String> instructions = new ArrayList<>();
    public void newEnv() {
        tabs ++;
    }
    public void prevEnv() {
        tabs --;
    }
    public boolean isGlobal() {
        return tabs == 0;
    }
    public ArrayList<String> getFinalCode() {
        return pyCode;
    }
    public void isTemporal() {
        isLocal = true;
    }
    public void isNotTemporal() {
        isLocal = false;
    }
    public void addInstruction(String instruction) {
        if(!isLocal) {
            instructions.add("\t".repeat(tabs) + instruction);
        } else {
            temporal.add("\t".repeat(tabs) + instruction);
        }
    }
    public void addLocal() {
        instructions.addAll(temporal);
        temporal.clear();
    }
    public void setMainCall(String call) {
        this.mainCall = call;
    }
    public void generateFinalCode() {
        for(String instruction : instructions) {
            pyCode.add(instruction);
        }
        pyCode.add("if __name__ == '__main__':");
        pyCode.add("\t" + (!mainCall.equals("") ? mainCall : "pass"));
    }
}