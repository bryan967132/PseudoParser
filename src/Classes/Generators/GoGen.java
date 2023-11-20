package Classes.Generators;
import java.util.ArrayList;
public class GoGen {
    private int tabs = 0;
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
        }
    }
    public void importMath() {
        if(!mathImport) {
            mathImport = true;
        }
    }
    public void importStrconv() {
        if(!strconvImport) {
            strconvImport = true;
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
            goCode.add("import (");
            if(fmtImport) {
                goCode.add("\t\"fmt\"");
            }
            if(mathImport) {
                goCode.add("\t\"math\"");
            }
            if(strconvImport) {
                goCode.add("\t\"strconv\"");
            }
            goCode.add(")\n");
        }
        for(String instruction : instructions) {
            goCode.add(instruction);
        }
        goCode.add("func main() {");
        goCode.add(!mainCall.equals("") ? "\t" + mainCall : "");
        goCode.add("}");
    }
}