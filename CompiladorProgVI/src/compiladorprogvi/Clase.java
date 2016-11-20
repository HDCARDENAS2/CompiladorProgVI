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
public class Clase {
    
    private String Nombre;
    private ArrayList<Metodo> metodos;
    private ArrayList<Variable> variables;

    public Clase(String Nombre, ArrayList<Metodo> metodos, ArrayList<Variable> variable) {
        this.Nombre = Nombre;
        this.metodos = metodos;
        this.variables = variable;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public ArrayList<Metodo> getMetodos() {
        return metodos;
    }

    public void setMetodos(ArrayList<Metodo> metodos) {
        this.metodos = metodos;
    }

    public ArrayList<Variable> getVariables() {
        return variables;
    }

    public void setVariables(ArrayList<Variable> variables) {
        this.variables = variables;
    }

    @Override
    public String toString() {
        return "Clase{" + "Nombre=" + Nombre + ", metodos=" + metodos.toString() + ", variables=" + variables.toString() + '}';
    }
    
    
    
    
}
