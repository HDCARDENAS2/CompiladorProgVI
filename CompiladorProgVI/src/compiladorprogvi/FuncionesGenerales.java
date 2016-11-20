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
import javax.swing.JOptionPane;

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
            
            String line_axx = "6";
            
            System.out.println(linea); 
        
            imprimir_linea("1",nr_linea,line_axx);
  
             if (EvaluarPalabraExiste(linea, Formatos.palabrasReservadas)) {
                 
                  imprimir_linea("2",nr_linea,line_axx);
                 
                 if(EvaluarPalabraExiste(linea,Formatos.metodo_salida)){
  
                      imprimir_linea("3",nr_linea,line_axx);
                     
                      if(EvaluarPalabraExiste(linea, Formatos.asignacion)){
         
                           imprimir_linea("4",nr_linea,line_axx);
                           
                          if(!EvaluarPalabraExiste(linea, Formatos.ind_variable)){
                             if(!salto_linea(linea)){
                               MensajesGlobal.setMensaje_global("linea no reconocible 2.", nr_linea); 
                               vectores[0] = true;
                            } 
                          }
                          
                            
                      }else{   
                   
                           if(!EvaluarPalabraExiste(linea,  Formatos.metodo_salida)){
                              MensajesGlobal.setMensaje_global("linea no reconocible 3.", nr_linea); 
                              vectores[0] = true;
                           }
                      }
                      
                 }else{
                     
                   imprimir_linea("6",nr_linea,line_axx);
                     
                    if(!EvaluarPalabraExiste(linea, Formatos.asignacion)){
                        
                         imprimir_linea("7",nr_linea,line_axx);
                        
                        if(!EvaluarPalabraExiste(linea, Formatos.ind_variable)){
                            
                           imprimir_linea("8",nr_linea,line_axx);

                           if(!EvaluarPalabraExiste(linea, Formatos.ind_if) || !EvaluarPalabraExiste(linea, Formatos.funcion_incio)){
                              MensajesGlobal.setMensaje_global("linea no reconocible 1.", nr_linea); 
                              vectores[0] = true; 
                           } 
                        }
                        
                    }else{

                        if(EvaluarPalabraExiste(linea, Formatos.ind_for)){
                             if(!EvaluarPalabraExiste(linea, Formatos.funcion_incio)){
                                MensajesGlobal.setMensaje_global("linea no reconocible 7.", nr_linea); 
                                vectores[0] = true; 
                             }
                        }
                        
                    }
                 }
                 
             }else{
                   if(!EvaluarPalabraExiste(linea, Formatos.asignacion)){
                       if(!salto_linea(linea)){
                         MensajesGlobal.setMensaje_global("Asignacion no reconocible 5.", nr_linea); 
                         vectores[0] = true;
                       }  
                   }else{
                       if(EvaluarPalabraExiste(linea, Formatos.ind_funcion_inicio)){
                         MensajesGlobal.setMensaje_global("Asignacion no reconocible 6.", nr_linea); 
                         vectores[0] = true;
                       }
                   }
             }
            
            
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
      
      public void imprimir_linea(String dato,String nr_linea,String linea){
          if(nr_linea.equals(linea)){
              System.out.println(dato);
          }
      }
      
      public boolean validarParentesis(String cadena){
          boolean salida=false;
          int ultimo;
          ultimo=cadena.length();
          String funcion_inicio=cadena.charAt(0)+"".trim();                
          String funcion_fin=cadena.substring(ultimo-1);
          if((funcion_inicio.equals(Formatos.ind_funcion_inicio[0])) && (funcion_fin.equals(Formatos.ind_funcion_fin[0]))){
              salida=true;
          }          
          return salida;
      }
      public static void imprimirCadena(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje);
    }
    
}
