package Controller;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.util.ArrayList;
import javax.swing.JTextPane;
import javax.swing.text.StyledDocument;
import Classes.Abstracts.Instruction;
import Classes.Env.Env;
import Classes.Generators.GoGen;
import Classes.Generators.PyGen;
import Classes.Instructions.Function;
import Classes.Instructions.MainMethod;
import Classes.Utils.Outs;
import Classes.Utils.TypeInst;
import Interface.IDE;
import Interface.IconFile;
import Interface.Path;
import Language.Parser;
import Language.ParserF;
import Language.Scanner;
import Language.ScannerF;
import Painter.WordPainter;
public class Controller {
    public ArrayList<IconFile> pjs = new ArrayList<>();
    public int existPJFile(String path) {
        for(int i = 0; i < pjs.size(); i ++) {
            if(pjs.get(i).path.equals(path)) {
                return i;
            }
        }
        return -1;
    }
    public int countPJ() {
        return pjs.size();
    }
    public void setFormat(JTextPane editor) {
        try {
            StyledDocument doc = editor.getStyledDocument();
            String input = doc.getText(0, doc.getLength());
            WordPainter painter = new WordPainter();
            ScannerF scanner = new ScannerF(
                new BufferedReader(
                    new StringReader(input)
                ), 
                painter
            );
            painter.setStyle(editor);
            ParserF parser = new ParserF(scanner, painter);
            parser.parse();
        }
        catch(Exception e) {}
    }
    public void analyze(IDE ide,  int index,  JTextPane editor,  JTextPane console) {
        IconFile currentFile = pjs.get(index);
        Outs.resetOuts();
        try {
            StyledDocument doc = editor.getStyledDocument();
            String input = doc.getText(0, doc.getLength());
            Scanner scanner = new Scanner(
                new BufferedReader(
                    new StringReader(input)
                )
            );
            Parser parser = new Parser(scanner);
            parser.parse();
            Env global = new Env(null,  "Global");
            MainMethod mainMethod = null;
            for (Instruction instruction : parser.execute) {
                try {
                    if (instruction.typeInst == TypeInst.MAIN) {
                        mainMethod = (MainMethod) instruction;
                    } else {
                        instruction.exec(global);
                    }
                } catch (Exception e) {}
            }
            if (mainMethod != null) {
                mainMethod.exec(global);
            }
            String outPrint = "PseudoP: " + currentFile.name + "\n";
            outPrint += Outs.getStringOuts();
            console.setText(outPrint);
        } catch (Exception e) {
            String outPrint = "PseudoP: " + currentFile.name + "\n";
            outPrint += Outs.getStringOuts();
            console.setText(outPrint);
        }
    }
    public void generateGoCode(IDE ide,  int index,  JTextPane editor,  JTextPane console) {
        IconFile currentFile = pjs.get(index);
        Outs.resetOuts();
        try {
            StyledDocument doc = editor.getStyledDocument();
            String input = doc.getText(0, doc.getLength());
            Scanner scanner = new Scanner(
                new BufferedReader(
                    new StringReader(input)
                )
            );
            Parser parser = new Parser(scanner);
            parser.parse();
            Env global = new Env(null,  "Global");
            GoGen goGen = new GoGen();
            for (Instruction instruction : parser.execute) {
                try {
                    if (instruction.typeInst != TypeInst.MAIN) {
                        instruction.exec(global);
                    }
                } catch (Exception e) {}
            }
            for(Instruction instruction : parser.execute) {
                instruction.goGenerate(global, goGen);
            }
            goGen.generateFinalCode();
            String outPrint = "PseudoP: " + currentFile.name + "\n";
            Outs.printConsole.add(0, String.join("\n", goGen.getFinalCode()));
            outPrint += Outs.getStringOuts();
            console.setText(outPrint);
            exportCode(String.join("\n", goGen.getFinalCode()), parser.fileName, parser.fileName + ".go");
        } catch (Exception e) {
            String outPrint = "PseudoP: " + currentFile.name + "\n";
            outPrint += Outs.getStringOuts();
            console.setText(outPrint);
        }
    }
    public void generatePyCode(IDE ide,  int index,  JTextPane editor,  JTextPane console) {
        IconFile currentFile = pjs.get(index);
        Outs.resetOuts();
        try {
            StyledDocument doc = editor.getStyledDocument();
            String input = doc.getText(0, doc.getLength());
            Scanner scanner = new Scanner(
                new BufferedReader(
                    new StringReader(input)
                )
            );
            Parser parser = new Parser(scanner);
            parser.parse();
            Env global = new Env(null,  "Global");
            PyGen pyGen = new PyGen();
            for (Instruction instruction : parser.execute) {
                try {
                    if (instruction.typeInst != TypeInst.MAIN) {
                        instruction.exec(global);
                    }
                } catch (Exception e) {}
            }
            for(Instruction instruction : parser.execute) {
                instruction.pyGenerate(global, pyGen);
            }
            pyGen.generateFinalCode();
            String outPrint = "PseudoP: " + currentFile.name + "\n";
            Outs.printConsole.add(0, String.join("\n", pyGen.getFinalCode()));
            outPrint += Outs.getStringOuts();
            console.setText(outPrint);
            exportCode(String.join("\n", pyGen.getFinalCode()), parser.fileName, parser.fileName + ".py");
        } catch (Exception e) {
            String outPrint = "PseudoP: " + currentFile.name + "\n";
            outPrint += Outs.getStringOuts();
            console.setText(outPrint);
        }
    }
    public void savePJ(int index, JTextPane editor) {
        try {
            BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(
                    new FileOutputStream(pjs.get(index).path), 
                    "utf-8"
                )
            );
            StyledDocument doc = editor.getStyledDocument();
            String input = doc.getText(0, doc.getLength());
            writer.write(input);
            writer.close();
        } catch (Exception e) {}
    }
    public void exportCode(String content, String dir, String fileName) {
        try {
            File dirName = new File("./Out/" + dir);
            if(!dirName.exists()) {
                dirName.mkdirs();
            }
            BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(
                    new FileOutputStream("./Out/" + dir + "/" + fileName), 
                    "utf-8"
                )
            );
            writer.write(content);
            writer.close();
        } catch (Exception e) {}
    }
    public String readInput(String path) {
        try {
            File archivo = new File(path);
            FileInputStream fis = new FileInputStream(archivo);
            InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
            BufferedReader br = new BufferedReader(isr);
            String text = "";
            String line;
            while ((line = br.readLine()) != null) {
                text += line;
                if(br.ready()) {
                    text += "\n";
                }
            }
            br.close();
            fis.close();
            return text;
        } catch (Exception e) {
            return "java.io.FileNotFoundException";
        }
    }
    public void serialize() {
        try {
            Path[] pjs1 = new Path[pjs.size()];
            for(int i = 0; i < pjs.size(); i ++) {
                pjs1[i] = new Path(pjs.get(i).id, pjs.get(i).path);
            }
            FileOutputStream file = new FileOutputStream("bin/Files");
            ObjectOutputStream output = new ObjectOutputStream(file);
            output.writeObject(pjs1);
            output.close();
            file.close();
        }
        catch(Exception e) {}
    }
    public void deserialize(IDE ide) {
        try {
            FileInputStream file = new FileInputStream("bin/Files");
            ObjectInputStream input = new ObjectInputStream(file);
            Path[] pjs1 = (Path[]) input.readObject();
            input.close();
            file.close();
            pjs = new ArrayList<>();
            int i = 0;
            for(Path path : pjs1) {
                pjs.add(new IconFile(i, new File(path.path), ide, this));
                i ++;
            }
        }
        catch (Exception e) {}
    }
}