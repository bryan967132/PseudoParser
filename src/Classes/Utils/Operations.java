package Classes.Utils;
public class Operations {
    public static Type[][] plus = {
        {Type.NUMBER,   Type.NUMBER,    Type.NUMBER,    Type.STRING},
        {Type.NUMBER,   Type.NULL,      Type.NULL,      Type.STRING},
        {Type.NUMBER,   Type.NULL,      Type.STRING,    Type.STRING},
        {Type.STRING,   Type.STRING,    Type.STRING,    Type.STRING}
    };
    public static Type[][] minus = {
        {Type.NUMBER,   Type.NUMBER,    Type.NUMBER,    Type.NULL},
        {Type.NUMBER,   Type.NULL,      Type.NULL,      Type.NULL},
        {Type.NUMBER,   Type.NULL,      Type.NULL,      Type.NULL},
        {Type.NULL,     Type.NULL,      Type.NULL,      Type.NULL}
    };
    public static Type[][] mult = {
        {Type.NUMBER,   Type.NULL,      Type.NUMBER,    Type.NULL},
        {Type.NULL,     Type.NULL,      Type.NULL,      Type.NULL},
        {Type.NUMBER,   Type.NULL,      Type.NULL,      Type.NULL},
        {Type.NULL,     Type.NULL,      Type.NULL,      Type.NULL}
    };
    public static Type[][] div = {
        {Type.NUMBER,   Type.NULL,      Type.NUMBER,    Type.NULL},
        {Type.NULL,     Type.NULL,      Type.NULL,      Type.NULL},
        {Type.NUMBER,   Type.NULL,      Type.NULL,      Type.NULL},
        {Type.NULL,     Type.NULL,      Type.NULL,      Type.NULL}
    };
    public static Type[][] pow = {
        {Type.NUMBER,   Type.NULL,      Type.NULL,      Type.NULL},
        {Type.NULL,     Type.NULL,      Type.NULL,      Type.NULL},
        {Type.NULL,     Type.NULL,      Type.NULL,      Type.NULL},
        {Type.NULL,     Type.NULL,      Type.NULL,      Type.NULL}
    };
    public static Type[][] mod = {
        {Type.NUMBER,   Type.NULL,      Type.NULL,      Type.NULL},
        {Type.NULL,     Type.NULL,      Type.NULL,      Type.NULL},
        {Type.NULL,     Type.NULL,      Type.NULL,      Type.NULL},
        {Type.NULL,     Type.NULL,      Type.NULL,      Type.NULL}
    };
}