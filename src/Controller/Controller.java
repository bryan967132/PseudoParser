package Controller;
import java.awt.Image;
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
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.text.StyledDocument;

import Classes.Abstracts.Instruction;
import Classes.Env.Env;
import Classes.Instructions.MainMethod;
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
            String input = doc.getText(0,doc.getLength());
            WordPainter painter = new WordPainter();
            ScannerF scanner = new ScannerF(
                new BufferedReader(
                    new StringReader(input)
                ),
                painter
            );
            painter.setStyle(editor);
            ParserF parser = new ParserF(scanner,painter);
            parser.parse();
        }
        catch(Exception e) {}
    }
    public void analyze(IDE ide,int index,JTextPane editor,JTextPane console) {
        try {
            StyledDocument doc = editor.getStyledDocument();
            String input = doc.getText(0,doc.getLength());
            Scanner scanner = new Scanner(
                new BufferedReader(
                    new StringReader(input)
                )
            );
            Parser parser = new Parser(scanner);
            IconFile currentFile = pjs.get(index);
            parser.parse();
            Classes.Utils.Out.printConsole = new ArrayList<>();
            if(parser.isSuccessExecution()) {
                String out = "PseudoP: " + currentFile.name + "\n-> Análisis de Entrada Exitoso.";
                String outPrint = "PseudoP: " + currentFile.name;
                out += "\n-> Código Verificado\n-> Estado de Verificación: Satisfactorio";
                Env global = new Env(null, "Global");
                MainMethod mainMethod = null;
                for (Instruction instruction : parser.execute) {
                    try {
                        if (instruction.typeInst == TypeInst.MAIN) {
                            mainMethod = (MainMethod) instruction;
                        } else {
                            instruction.exec(global);
                        }
                    } catch (Exception e) {
                    }
                }
                if (mainMethod != null) {
                    mainMethod.exec(global);
                    if(Classes.Utils.Out.printConsole.size() > 0) {
                        for(String c : Classes.Utils.Out.printConsole) {
                            outPrint += "\n" + c;
                        }
                    }
                }
                ide.outConsole.setText(outPrint);
                console.setText(out);
            }
            else {
                console.setText("PseudoP: " + currentFile.name + "\n-> " + parser.getErrors());
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public void lookGraphs(IDE ide,int index) {
        try {
            // ide.zoomFactor = 1.05;
            // ide.graphics.removeMouseListener(ide);
            // ide.graphics.removeMouseWheelListener(ide);
            // ide.graphics.removeMouseMotionListener(ide);
            // ide.regexCB.repaint();
            // ide.graphics.removeAll();
            // ide.img = new JLabel();
            // ide.image = new ImageIcon((ide.treesR.isSelected() ? "Data/ARBOLES_201908355/tree_" : (ide.nextsR.isSelected() ? "Data/SIGUIENTES_201908355/nexts_" : (ide.transitionsR.isSelected() ? "Data/TRANSICIONES_201908355/transitions_" : (ide.afdsR.isSelected() ? "Data/AFD_201908355/afd_" : "Data/AFND_201908355/afnd_")))) + index + "_" + ide.regexCB.getSelectedItem() + ".png");
            // ide.icono = new ImageIcon(ide.image.getImage().getScaledInstance(ide.image.getIconWidth(),ide.image.getIconHeight(),Image.SCALE_DEFAULT));
            // ide.img.setIcon(ide.icono);
            // ide.img.setBounds(0,0,ide.icono.getIconWidth(),ide.icono.getIconHeight());
            // ide.graphics.add(ide.img);
            // ide.graphics.addMouseListener(ide);
            // ide.graphics.addMouseWheelListener(ide);
            // ide.graphics.addMouseMotionListener(ide);
            // ide.graphics.repaint();
        }
        catch(Exception e) {}
    }
    public void saveOLCPJ(int index,JTextPane editor) {
        try {
            BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(
                    new FileOutputStream(pjs.get(index).path),
                    "utf-8"
                )
            );
            StyledDocument doc = editor.getStyledDocument();
            String input = doc.getText(0,doc.getLength());
            writer.write(input);
            writer.close();
        } catch (Exception e) {}
    }
    public String readInput(String path) {
        try {
            File archivo = new File(path);
            FileInputStream fis = new FileInputStream(archivo);
            InputStreamReader isr = new InputStreamReader(fis,"UTF-8");
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
                pjs1[i] = new Path(pjs.get(i).id,pjs.get(i).path);
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
                pjs.add(new IconFile(i,new File(path.path),ide,this));
                i ++;
            }
        }
        catch (Exception e) {}
    }
}