package Classes.Generators;
import java.util.ArrayList;
public class GoGen {
    private int tabs = 0;
    private int imports = 0;
    private String mainCall = "";
    private boolean fmtImport = false;
    private boolean mathImport = false;
    private boolean strconvImport = false;
    private ArrayList<String> goCode = new ArrayList<>();
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
    public void importFmt() {
        if(!fmtImport) {
            fmtImport = true;
            imports ++;
        }
    }
    public void importMath() {
        if(!mathImport) {
            mathImport = true;
            imports ++;
        }
    }
    public void importStrconv() {
        if(!strconvImport) {
            strconvImport = true;
            imports ++;
        }
    }
    public ArrayList<String> getFinalCode() {
        return goCode;
    }
    public void addInstruction(String instruction) {
        instructions.add("\t".repeat(tabs) + instruction);
    }
    public void setMainCall(String call) {
        this.mainCall = call;
    }
    public void generateFinalCode() {
        goCode.add("package main\n");
        if(fmtImport || mathImport || strconvImport) {
            if(imports > 1) {
                goCode.add("import (");
            }
            if(fmtImport) {
                goCode.add(imports > 1 ? "\t\"fmt\"" : "import \"fmt\"\n");
            }
            if(mathImport) {
                goCode.add(imports > 1 ? "\t\"math\"" : "import \"math\"\n");
            }
            if(strconvImport) {
                goCode.add(imports > 1 ? "\t\"strconv\"" : "import \"strconv\"\n");
            }
            if(imports > 1) {
                goCode.add(")\n");
            }
        }
        goCode.addAll(instructions);
        goCode.add("func main() {");
        goCode.add(!mainCall.equals("") ? "\t" + mainCall : "");
        goCode.add("}");
    }
}