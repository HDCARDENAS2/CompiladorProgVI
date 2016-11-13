
package compiladorprogvi;

/**
 *
 * @author Felipe
 */
public class Formatos {
    public static String indentificador = "[A-Z]";
    public static String numero = "[0-9]";   
    public static String[] palabrasReservadas = {"INT","DECIMAL","CHAR","STRING","BOOLEAN","VOID","IF","FOR"};  
    public static String[] operadores = {"+","-","*","/","%","^"};
    public static String[] operadoresLogicos = {"<",">",">=","<=","=","AND","OR","NOT"};
    public static String[] asignacion = {":="};
    
    private final String mundo;
    public Formatos(String mundo) {
        this.mundo = mundo;
    }

    public Formatos() {
    }
    
    
}
