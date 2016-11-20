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
                if(variable.getNombre().equals(this.palabras[0])){
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
                    break;
                }
            }
            
            if (salida){
                for (Variable variable : variables) {
                    if(variable.getNombre().equals(this.palabras[2])){
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
                        break;
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
                if(variable.getNombre().equals(this.palabras[0])){
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
                    if(variable.getNombre().equals(this.palabras[2])){
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
                if(variable.getNombre().equals(this.palabras[0])){
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
    
    public boolean valSemIf(String oper, String linea, ArrayList<Variable> variables){
        
        boolean salida = false;
        int posicion = 0;
        String opera;
        
        this.operacion = oper.trim();            
        this.palabras = this.operacion.split(" ");
        
        if (this.palabras.length == 1){
            
            for (Variable variable : variables) {
                if(variable.getNombre().equals(this.palabras[0])){
                    if (variable.getTipo().equals("BOOLEAN")){
                        salida = true;
                    }else{
                        MensajesGlobal.setMensaje_global("La variable "+variable.getNombre()+" no es BOOLEANA.", linea);
                        salida = false;
                    }
                    break;
                }else{
                    MensajesGlobal.setMensaje_global("La variable "+this.palabras[0]+" no está definida.", linea);
                    salida = false;
                }
            }
            
        }else if (this.palabras.length == 2){
            
            if (this.palabras[0].equals("NOT")){
                for (Variable variable : variables) {
                    if(variable.getNombre().equals(this.palabras[1])){
                        if (variable.getTipo().equals("BOOLEAN")){
                            salida = true;
                        }else{
                            MensajesGlobal.setMensaje_global("La variable "+variable.getNombre()+" no es BOOLEANA.", linea);
                            salida = false;
                        }
                        break;
                    }else{
                        MensajesGlobal.setMensaje_global("La variable "+this.palabras[0]+" no está definida.", linea);
                        salida = false;
                    }
                }
            }else{
                MensajesGlobal.setMensaje_global("La palabra "+this.palabras[0]+" no es una negación NOT.", linea);
                salida = false;
            }
            
        }else{
            int pos = -1;
            for (String palabra : this.palabras) {
                pos ++;
                if (palabra.equals("=")){                    
                    posicion = pos;
                    salida = true;
                    break;
                }else if (palabra.equals("NOT=")){
                    posicion = pos;
                    salida = true;
                    break;
                }else{
                    MensajesGlobal.setMensaje_global("no tiene un operador de comparación como '=' o 'NOT='", linea);
                    salida = false;
                }
            }
            
            if (salida){
                if (this.palabras[0].equals("NOT")){
                    for (int i = 2; i < (posicion - 1); i ++){
                        
                    }
                }
            }
        }
        
        return salida;
        
    }
    
    public boolean validarSemantica(String opera, String linea, ArrayList<Variable> variables){
        
        this.operacion = opera.trim();
        
        this.palabras = operacion.split(" ");
    
        return false;
    }
    
}
