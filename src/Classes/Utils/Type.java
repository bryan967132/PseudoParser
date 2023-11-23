package Classes.Utils;
public enum Type {
    INT     ("int",     "int",     "int"  ) ,
    DOUBLE  ("double",  "float64", "float") ,
    BOOLEAN ("boolean", "bool",    "bool" ) ,
    CHAR    ("char",    "rune",    "chr"  ) ,
    STRING  ("String",  "string",  "str"  ) ,
    NULL    ("null",    "nil",     "None" ) ;

    private String value;
    private String goValue;
    private String pyValue;
    private Type(String value, String goValue, String pyValue) {
        this.value = value;
        this.goValue = goValue;
        this.pyValue = pyValue;
    }
    public String getValue() {return value;}
    public String getGoValue() {return goValue;}
    public String getPyValue() {return pyValue;}
}