/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiladorprogvi;

/**
 *
 * @author hernandario
 */
public class LineaCodigo {
    
    private int linea;
    private String codigo;

    public LineaCodigo(int linea, String codigo) {
        this.linea = linea;
        this.codigo = codigo;
    }
    
    public int getLinea() {
        return linea;
    }

    public void setLinea(int linea) {
        this.linea = linea;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    @Override
    public String toString() {
        return "LineaCodigo{" + "linea=" + linea + ", codigo=" + codigo + '}';
    }
    
    
    
}
