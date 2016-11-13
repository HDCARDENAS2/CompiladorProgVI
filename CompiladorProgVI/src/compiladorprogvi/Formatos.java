/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
}
