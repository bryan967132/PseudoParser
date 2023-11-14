package Classes.Env;
import java.util.Map;
import java.util.TreeMap;
import Classes.Instructions.Function;
import Classes.Utils.ReturnType;
import Classes.Utils.Type;
import Classes.Utils.TypeError;
import Classes.Utils.Error;
import Classes.Utils.Outs;
public class Env {
    private Map<String, Symbol> ids = new TreeMap<>();
    private Map<String, Function> functions = new TreeMap<>();
    private Env previous;
    public String name;
    public Env(Env previous, String name) {
        this.previous = previous;
        this.name = name;
    }
    public boolean saveID(String id, Object value, Type type, int line, int column) {
        if(!this.ids.containsKey(id)) {
            this.ids.put(id, new Symbol(value, id, type, null));
            return true;
        }
        // ERROR SEMANTICO
        return false;
    }
    public Symbol getValueID(String id) {
        Env current = this;
        while(current != null) {
            if(current.ids.containsKey(id)) {
                return current.ids.get(id);
            }
            current = current.previous;
        }
        // ERROR SEMANTICO
        return null;
    }
    public boolean reasignID(String id, ReturnType value) {
        Env current = this;
        while(current != null) {
            if(current.ids.containsKey(id)) {
                current.ids.get(id).value = value.value;
                return true;
            }
            current = current.previous;
        }
        // ERROR SEMANTICO
        return false;
    }
    public boolean saveFunction(Function func) {
        if(!this.functions.containsKey(func.id)) {
            this.functions.put(func.id, func);
            return true;
        }
        // ERROR SEMANTICO
        return false;
    }
    public Function getFunction(String id) {
        Env current = this;
        while(current != null) {
            if(current.functions.containsKey(id)) {
                return current.functions.get(id);
            }
            current = current.previous;
        }
        // ERROR SEMANTICO
        return null;
    }
    public Env getGlobal() {
        Env env = this;
        while(env.previous != null) {
            env = env.previous;
        }
        return env;
    }
    public void setPrint(String print) {
        Outs.printConsole.add(print);
    }
    public void setError(String errorD, int line, int column) {
        if(!match(errorD, line, column)) {

        }
    }
    public boolean match(String err, int line, int column) {
        for(Error s : Outs.errors) {
            if(s.toString().equals(new Error(line, column, TypeError.SEMANTIC, err).toString())) {
                return true;
            }
        }
        return false;
    }
}