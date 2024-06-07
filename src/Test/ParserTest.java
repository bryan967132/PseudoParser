package Test;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;
import Classes.Abstracts.Instruction;
import Classes.Env.Env;
import Classes.Instructions.MainMethod;
import Classes.Utils.Outs;
import Classes.Utils.TypeInst;
import Language.Parser;
import Language.Scanner;
public class ParserTest {
    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws Exception {
        try {
            String input = readInput("./Inputs/Ackermann.psp");
            Scanner scanner = new Scanner(
                new BufferedReader(
                    new StringReader(input)
                )
            );
            Parser parser = new Parser(scanner);
            ArrayList<Instruction> execute = (ArrayList<Instruction>) parser.parse().value;
            Classes.Utils.Outs.printConsole = new ArrayList<>();
            Env global = new Env(null, "Global");
            MainMethod mainMethod = null;
            for (Instruction instruction : execute) {
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
            System.out.println(Outs.getStringOuts());
        }
        catch(Exception e) {
            System.out.println(e);
        }
    }
    public static String readInput(String path) {
        try {
            File archivo = new File(path);
            FileInputStream fis = new FileInputStream(archivo);
            InputStreamReader isr = new InputStreamReader(fis,"UTF-8");
            BufferedReader br = new BufferedReader(isr);
            String texto = "";
            String linea;
            while ((linea = br.readLine()) != null) {
                texto += linea + "\n";
            }
            br.close();
            fis.close();
            return texto;
        }
        catch(Exception e) {
            System.out.println(e);
        }
        return "";
    }
}