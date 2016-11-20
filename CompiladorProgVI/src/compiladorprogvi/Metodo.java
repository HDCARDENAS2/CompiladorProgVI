/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiladorprogvi;

import java.util.ArrayList;

/**
 *
 * @author hernandario
 */
public class Metodo {
       
    private String Nombre;
    private String Tipo;
    private String Parametro;
    private ArrayList<LineaCodigo> lineas_codigo;

    public Metodo(String Nombre, String Tipo, String Parametro, ArrayList<LineaCodigo> lineas_codigo) {
        this.Nombre = Nombre;
        this.Tipo = Tipo;
        this.Parametro = Parametro;
        this.lineas_codigo = lineas_codigo;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getTipo() {
        return Tipo;
    }

    public void setTipo(String Tipo) {
        this.Tipo = Tipo;
    }

    public String getParametro() {
        return Parametro;
    }

    public void setParametro(String Parametro) {
        this.Parametro = Parametro;
    }

    public ArrayList<LineaCodigo> getLineas_codigo() {
        return lineas_codigo;
    }

    public void setLineas_codigo(ArrayList<LineaCodigo> lineas_codigo) {
        this.lineas_codigo = lineas_codigo;
    }

    @Override
    public String toString() {
        return "Metodo{" + "Nombre=" + Nombre + ", Tipo=" + Tipo + ", Parametro=" + Parametro + ", lineas_codigo=" + lineas_codigo.toString() + '}';
    }

    
  
}
