package Components;
public class Response {
    String execution;
    ErrorS error;
    public Response(String execution) {
        this.execution = execution;
    }
    public Response(ErrorS error) {
        this.error = error;
    }
    public String getHTML() {
        return (error != null ? error.getHTML() : "\t\t\t<td>\n\t\t\t\t" + execution + "\n\t\t\t</td>\n");
    }
    public String toString() {
        return (execution != null ? execution : error) + "";
    }
}