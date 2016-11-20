/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiladorprogvi;

import java.util.ArrayList;

public class Semantica {

    private String operacion;
    private String[] palabras;
    
    public Semantica() {
    }
    
    public boolean valSemFor(String oper, String linea, ArrayList<Variable> variables){
    
        boolean salida = false;
        
        this.operacion = oper.trim();            
        this.palabras = this.operacion.split(" ");
        
        if (this.palabras[1].equals(":=")){
            
            for (Variable variable : variables) {
                if(variable.equals(this.palabras[0])){
                    if (variable.getTipo().equals("INT")){
                        salida = true;
                    }else{
                        salida = false;
                    }
                    break;
                }else{
                    salida = false;
                }
            }
            
            if (salida){
                for (Variable variable : variables) {
                    if(variable.equals(this.palabras[2])){
                        if (variable.getTipo().equals("INT")){
                            salida = true;
                        }else{
                            salida = false;
                        }
                        break;
                    }else{
                        salida = false;
                    }
                }
                
                if (salida == false){
                     try {
                            int x = Integer.parseInt(this.palabras[2]);
                            salida = true;
                         } 
                     catch (Exception e) {
                            salida = false;
                        }
                }
            }
            
        }else if (this.palabras[1].equals("=") || 
                  this.palabras[1].equals("<") ||
                  this.palabras[1].equals(">") ||
                  this.palabras[1].equals("<=") ||
                  this.palabras[1].equals(">=")
                 ){
            
            for (Variable variable : variables) {
                if(variable.equals(this.palabras[0])){
                    if (variable.getTipo().equals("INT")){
                        salida = true;
                    }else{
                        salida = false;
                    }
                    break;
                }else{
                    salida = false;
                }
            }
            
            if (salida){
                for (Variable variable : variables) {
                    if(variable.equals(this.palabras[2])){
                        if (variable.getTipo().equals("INT")){
                            salida = true;
                        }else{
                            salida = false;
                        }
                        break;
                    }else{
                        salida = false;
                    }
                }
                
                if (salida == false){
                     try {
                            int x = Integer.parseInt(this.palabras[2]);
                            salida = true;
                         } 
                     catch (Exception e) {
                            salida = false;
                        }
                }
            }
            
        }else if (this.palabras[1].equals("++") ||
                  this.palabras[1].equals("--")
                 ){
            
            for (Variable variable : variables) {
                if(variable.equals(this.palabras[0])){
                    if (variable.getTipo().equals("INT")){
                        salida = true;
                    }else{
                        salida = false;
                    }
                    break;
                }else{
                    salida = false;
                }
            }
            
        }else{
            salida = false;
        }
        
        return salida;
    }
    
    public boolean validarSemantica(String opera, String linea, ArrayList<Variable> variables){
        
        this.operacion = opera.trim();
        
        this.palabras = operacion.split(" ");
    
        return false;
    }
    
}
