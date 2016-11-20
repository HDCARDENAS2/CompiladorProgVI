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
                        MensajesGlobal.setMensaje_global("La variable "+variable.getNombre()+" no es entera.", linea);
                        salida = false;
                    }
                    break;
                }else{
                    MensajesGlobal.setMensaje_global("La variable "+this.palabras[0]+" no está definida.", linea);
                    salida = false;
                }
            }
            
            if (salida){
                for (Variable variable : variables) {
                    if(variable.equals(this.palabras[2])){
                        if (variable.getTipo().equals("INT")){
                            salida = true;
                        }else{
                            MensajesGlobal.setMensaje_global("La variable "+variable.getNombre()+" no es entera.", linea);
                            salida = false;
                        }
                        break;
                    }else{
                        MensajesGlobal.setMensaje_global("La variable "+this.palabras[2]+" no está definida.", linea);
                        salida = false;
                    }
                }
                
                if (salida == false){
                     try {
                            int x = Integer.parseInt(this.palabras[2]);
                            salida = true;
                         } 
                     catch (Exception e) {
                            MensajesGlobal.setMensaje_global("El parametro no es entero", linea);
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
                        MensajesGlobal.setMensaje_global("La variable "+variable.getNombre()+" no es entera.", linea);
                        salida = false;
                    }
                    break;
                }else{
                    MensajesGlobal.setMensaje_global("La variable "+this.palabras[0]+" no está definida.", linea);
                    salida = false;
                }
            }
            
            if (salida){
                for (Variable variable : variables) {
                    if(variable.equals(this.palabras[2])){
                        if (variable.getTipo().equals("INT")){
                            salida = true;
                        }else{
                            MensajesGlobal.setMensaje_global("La variable "+variable.getNombre()+" no es entera.", linea);
                            salida = false;
                        }
                        break;
                    }else{
                        MensajesGlobal.setMensaje_global("La variable "+this.palabras[2]+" no está definida.", linea);
                        salida = false;
                    }
                }
                
                if (salida == false){
                     try {
                            int x = Integer.parseInt(this.palabras[2]);
                            salida = true;
                         } 
                     catch (Exception e) {
                            MensajesGlobal.setMensaje_global("El parametro no es entero", linea);
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
                        MensajesGlobal.setMensaje_global("La variable "+variable.getNombre()+" no es entera.", linea);
                        salida = false;
                    }
                    break;
                }else{
                    MensajesGlobal.setMensaje_global("La variable "+this.palabras[0]+" no está definida.", linea);
                    salida = false;
                }
            }
            
        }else{
            MensajesGlobal.setMensaje_global("El operador "+this.palabras[1]+" no es válido.", linea);
            salida = false;
        }
        
        return salida;
    }
    
    public boolean valSemIf(){
        
        boolean salida = false;
        
        
        
        return salida;
        
    }
    
    public boolean validarSemantica(String opera, String linea, ArrayList<Variable> variables){
        
        this.operacion = opera.trim();
        
        this.palabras = operacion.split(" ");
    
        return false;
    }
    
}
