/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiladorprogvi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author hernandario
 */
public class FuncionesGenerales {
    
     public ArrayList<LineaCodigo> TranFomarTextoaArray(String datos){
       ArrayList<LineaCodigo> lineascodigo = new ArrayList<LineaCodigo>();
       String s[] = datos.split("\\r?\\n");
       ArrayList<String>arrList = new ArrayList<>(Arrays.asList(s)) ;
       int linea = 1;
       for (String string : arrList) {
           lineascodigo.add(new LineaCodigo(linea, string.toUpperCase()));
           linea++;
       }
       return lineascodigo;
    }
     
    public ArrayList<String> Split_string(String datos,String separador){
       String s[] = datos.split(separador);
       ArrayList<String>arrList = new ArrayList<>(Arrays.asList(s)) ;
       return arrList;
    }
     
    public boolean EvaluarPalabraExiste(String linea_codigo,String[] vector_datos){
        
        for (String vector_dato : vector_datos) {
            if(linea_codigo.indexOf(vector_dato) > -1){
                return true;
            }
        }
        return false;
    }
    
    public String RetornarPalabraExiste(String linea_codigo,String[] vector_datos){
       ArrayList<String>arrList = new ArrayList<>(Arrays.asList(vector_datos)) ;
       for (String string : arrList) {
           if(linea_codigo.contains(string)){
               return string;
           } 
       }
       return null;
    }
    
    public boolean eva_ex_regular(String cadena,String expresion){
        Pattern pat = Pattern.compile(expresion);
        Matcher mat = pat.matcher(cadena);
        if (mat.matches()) {
            return true;
        }else{
            return false;
        }
    }
   
    
    public String quitar_palabras(String cadena,String palabras,Object[] vectores){    
        
        if(palabras == null && vectores != null){
            palabras = "";
            for (Object vectore : vectores) {
                String[]datos = (String[])vectore;
                for (int i = 0; i < datos.length; i++) {
                    if(palabras.equals("")){
                       palabras = datos[i];
                    }else{
                       palabras += ","+datos[i];
                    }  
                }
            }
        }
        
        for (String dato : Split_string(palabras,",")) {
            cadena = cadena.replaceAll(dato,"");
        }   
        return cadena;
    }
    
    
    public boolean No_existe_variable(String dato,ArrayList<Variable> variables){
        for (Variable variable : variables) {
            if(variable.getNombre().equals(dato)){
                return false;
            }
        }
        return true;
    }
    
     public boolean No_existe_metodo(String dato,ArrayList<Metodo> metodos){
        for (Metodo metodo : metodos) {
            if(metodo.getNombre().equals(dato)){
                return false;
            }
        }
        return true;
    }
    
     
     
    public Object[] evaluar_metodo(String linea,String nr_linea) {
        
        Object[] vectores = new Object[2];
        
        vectores[0] = false;
        vectores[1] = 0;
        
        if (EvaluarPalabraExiste(linea, Formatos.tipo_funcion)) {
            
            if (EvaluarPalabraExiste(linea, Formatos.funcion_incio)) {
                
                if (EvaluarPalabraExiste(linea, Formatos.ind_funcion_inicio)
                    &&
                    EvaluarPalabraExiste(linea, Formatos.ind_funcion_fin)) {
                    vectores[1] = 1;
                } else {
                    MensajesGlobal.setMensaje_global("La sobrecarga no esta bien declarada.", nr_linea); 
                    vectores[0] = true;
                }
            } else {
                
                boolean[] vectores2 = evaluar_variable( linea, nr_linea);
                
                if(!vectores2[0]){
                   if(vectores2[1]){
                       vectores[1] = 2;
                   }else{
                       MensajesGlobal.setMensaje_global("La funcion no esta bien declarada.", nr_linea); 
                       vectores[0] = true; 
                   }  
                }else{
                   vectores[0] = true; 
                }
                
            }
        }else{
          //Pendiente aqui se validan las lineas
         //  if(!salto_linea(linea)){
            //  MensajesGlobal.setMensaje_global("linea no recono sible.", nr_linea); 
            //  vectores[0] = true;   
          // }
                    
            
        }
        
        return vectores;
    }
     
  
      public boolean[] evaluar_variable(String linea,String nr_linea) {
        
        boolean[] vectores = new boolean[2];
        vectores[0] = false;
        vectores[1] = false;

             if (EvaluarPalabraExiste(linea, Formatos.tipo_variables)) {       
                    if (EvaluarPalabraExiste(linea, Formatos.ind_variable)) {
                       vectores[1] = true;
                    }else{
                       MensajesGlobal.setMensaje_global("la variable no esta bien declarada ", nr_linea);
                       vectores[0] = true;
                   }
             }
             
         return vectores;
      }
      
      public boolean salto_linea(String texto){
          texto = texto.trim().replace(" ", "");
          return texto.length() == 0;
      }
    
}
