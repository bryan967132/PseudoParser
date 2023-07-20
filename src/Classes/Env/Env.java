package Classes.Env;
import java.util.Map;
import java.util.TreeMap;
import Classes.Instructions.Function;
import Classes.Utils.ReturnType;
import Classes.Utils.Type;
public class Env {
    private Map<String, Symbol> ids = new TreeMap<>();
    private Map<String, Function> functions = new TreeMap<>();
    private Env anterior;
    public String name;
    public Env(Env anterior, String name) {
        this.anterior = anterior;
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
            current = current.anterior;
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
            current = current.anterior;
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
            current = current.anterior;
        }
        // ERROR SEMANTICO
        return null;
    }
    public Env getGlobal() {
        Env env = this;
        while(env.anterior != null) {
            env = env.anterior;
        }
        return env;
    }
}