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
        
        if (this.palabras.length > 1){
            if (this.palabras[1].equals(":=") && this.palabras.length == 3){

                boolean otro_mensaje = false;

                for (Variable variable : variables) {
                    if(variable.getNombre().equals(this.palabras[0])){
                        if (variable.getTipo().equals("INT")){
                            salida = true;
                            MensajesGlobal.setMensaje_global(null, null);
                        }else{
                            MensajesGlobal.setMensaje_global("La variable "+variable.getNombre()+" no es entera.", linea);
                            salida = false;
                            otro_mensaje = true;
                        }
                        break;
                    }else{
                        salida = false; 
                    }
                }

                if(!salida && !otro_mensaje){
                    MensajesGlobal.setMensaje_global("La variable "+this.palabras[0]+" no está definida.", linea);
                    return false;
                }

                if (salida){
                    for (Variable variable : variables) {
                        if(variable.getNombre().equals(this.palabras[2])){
                            if (variable.getTipo().equals("INT")){
                                salida = true;
                                MensajesGlobal.setMensaje_global(null, null);
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
                                MensajesGlobal.setMensaje_global(null, null);
                             } 
                         catch (Exception e) {
                                MensajesGlobal.setMensaje_global("El parametro no es entero", linea);
                                salida = false;
                            }
                    }
                }

            }else if ((this.palabras[1].equals("=") || 
                      this.palabras[1].equals("<") ||
                      this.palabras[1].equals(">") ||
                      this.palabras[1].equals("<=") ||
                      this.palabras[1].equals(">=")) &&
                      (this.palabras.length == 3)
                     ){

                for (Variable variable : variables) {
                    if(variable.getNombre().equals(this.palabras[0])){
                        if (variable.getTipo().equals("INT")){
                            salida = true;
                            MensajesGlobal.setMensaje_global(null, null);
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

                    if(isNumero(this.palabras[2])){
                        salida = true;
                    }else{     

                        for (Variable variable : variables) {
                            if(variable.getNombre().equals(this.palabras[2])){
                                if (variable.getTipo().equals("INT")){
                                    salida = true;
                                    MensajesGlobal.setMensaje_global(null, null);
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
                    }
                }

            }else if ((this.palabras[1].equals("++") ||
                       this.palabras[1].equals("--")) && 
                      (this.palabras.length == 2)
                     ){

                for (Variable variable : variables) {
                    if(variable.getNombre().equals(this.palabras[0])){
                        if (variable.getTipo().equals("INT")){
                            salida = true;
                            MensajesGlobal.setMensaje_global(null, null);
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
                MensajesGlobal.setMensaje_global("La definición ("+this.operacion+") es incorrecta.", linea);
                salida = false;
            }
        }else{
            MensajesGlobal.setMensaje_global("La definición ("+this.operacion+") es incorrecta.", linea);
            salida = false;
        }
        return salida;
    }
    
    public boolean valSemIf(String oper, String linea, ArrayList<Variable> variables){
        
        boolean salida = false;
        int posicion = 0;
        String opera = "";
        
        this.operacion = oper.trim();            
        this.palabras = this.operacion.split(" ");
        
        if (this.palabras.length == 1){
            
            for (Variable variable : variables) {
                if(variable.getNombre().equals(this.palabras[0])){
                    if (variable.getTipo().equals("BOOLEAN")){
                        salida = true;
                        MensajesGlobal.setMensaje_global(null, null);
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
            int pos = -1;
            for (String palabra : this.palabras) {
                pos ++;
                if (palabra.equals("=")){                    
                    posicion = pos;
                    salida = true;
                    MensajesGlobal.setMensaje_global(null, null);
                    break;
                }else if (palabra.equals("NOT=")){
                    posicion = pos;
                    salida = true;
                    MensajesGlobal.setMensaje_global(null, null);
                    break;
                }else{
                    MensajesGlobal.setMensaje_global("no tiene un operador de comparación como '=' o 'NOT='", linea);
                    salida = false;
                }
            }
            
            if (salida){
                for (int i = 0; i < posicion; i ++){
                    
                    if (i == 0) {
                        opera = this.palabras[i];
                    }else{
                        opera = opera+" "+this.palabras[i];
                    }
                }
                
                if (valOperaNum(opera,linea,variables)){
                
                    for (int i = (posicion + 1); i < this.palabras.length; i ++){

                        if (i == (posicion + 1)) {
                            opera = this.palabras[i];
                        }else{
                            opera = opera+" "+this.palabras[i];
                        }
                    }
                    
                    if (valOperaNum(opera,linea,variables)){
                        salida = true;
                    }else{
                        salida = false;
                    }
                }else{
                    salida = false;
                }
            }
        }
        
        return salida;
        
    }
    
    public boolean valOperaNum(String oper, String linea, ArrayList<Variable> variables){
        boolean salida = false;
        int pos = -1;
        String[] pal;  
        
        this.operacion = oper.trim();            
        pal = this.operacion.split(" ");
        
        if ((this.palabras.length % 2) != 0){            
            for (String palabra : pal) {
                pos ++;
                if ((pos % 2) == 0){
                    if (isNumero(palabra)){
                        MensajesGlobal.setMensaje_global(null, null);
                        salida = true;
                    }else{
                        for (Variable variable : variables) {
                            if(variable.getNombre().equals(palabra)){
                                if (variable.getTipo().equals("INT") || variable.getTipo().equals("DECIMAL")){
                                    salida = true;
                                    MensajesGlobal.setMensaje_global(null, null);
                                    break;
                                }else{
                                    MensajesGlobal.setMensaje_global("La variable "+variable.getNombre()+" no es numerica.", linea);
                                    salida = false;
                                }
                            }else{
                                MensajesGlobal.setMensaje_global("La variable "+palabra+" no está definida.", linea);
                                salida = false;
                            }
                        }
                    }
                }else{
                    for (String operador: Formatos.operadores){
                            if (operador.equals(palabra)){
                                salida = true;
                                MensajesGlobal.setMensaje_global(null, null);
                                break;
                            }else{
                                MensajesGlobal.setMensaje_global("El operador "+palabra+" no es valido.", linea);
                                salida = false;
                            }
                        }
                    if (salida == false){
                        break;
                    }
                }                
            }
        }else{
            MensajesGlobal.setMensaje_global("la operación es incorrecta", linea);
            salida = false;
        }
        return salida;
    }
    
    public boolean isNumero(String numero){
        try {
                int x = Integer.parseInt(numero);
                return true;
             } 
         catch (Exception e) {
                return false;
            }
    }
    
}
