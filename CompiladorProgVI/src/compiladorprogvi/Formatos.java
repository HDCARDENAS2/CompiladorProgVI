
package compiladorprogvi;

/**
 *
 * @author Felipe
 */
public class Formatos {
    public static String indentificador = "[A-Z]";
    public static String numero = "[0-9]";   
    public static String[] palabrasReservadas = {"INT","DECIMAL","CHAR","STRING","BOOLEAN","VOID","IF","FOR","OUT","ELSE","IN"};  
    public static String[] operadores = {"+","-","*","/","%","^"};
    public static String[] operadoresLogicos = {"<",">",">=","<=","=","AND","OR","NOT"};
    public static String[] asignacion = {":="};    
    public static String[] funcion_incio = {"BEGIN"};    
    public static String[] funcion_fin = {"END"};    
    public static String[] clase = {"CLASS"};  
    public static String[] tipo_funcion = {"INT","DECIMAL","CHAR","STRING","BOOLEAN","VOID"};
    public static String[] ind_funcion_inicio = {"("};
    public static String[] ind_funcion_fin= {")"};
    public static String[] tipo_variables = {"INT","DECIMAL","CHAR","STRING","BOOLEAN"};
    public static String[] ind_variable = {";"};
    public static String[] main = {"MAIN"};        
    public static String[] borrado_for={"FOR","BEGIN"};
    public static String[] borrado_if={"IF","BEGIN"};
    public static String[] metodo_salida = {"OUT"};   
    public static String[] ind_if  = {"IF"};  
    public static String[] ind_for = {"FOR"};  
    
}
