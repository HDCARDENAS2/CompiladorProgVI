/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiladorprogvi;

import java.util.ArrayList;

/**
 *
 * @author Hernan
 */
public class ValidarMetodo {
    
    public boolean validarMetodo (Clase obj_clase){
        
        FuncionesGenerales obj_funcGenerales = new FuncionesGenerales();
        ValidarIf obj_validar_if = new ValidarIf();
        ValidarFor obj_validar_for = new ValidarFor();
        Semantica obj_sem=new Semantica();
        
        boolean romper = false;
    
        
        for (Metodo m : obj_clase.getMetodos()) {
            
            ArrayList<Variable> variables = new ArrayList<Variable>();
            
            ArrayList<LineaCodigo> lista_aux =( ArrayList<LineaCodigo>) m.getLineas_codigo().clone();
            
            
            if(m.getParametro() != "" && m.getParametro().length() > 1){
                
                String tipo_vr_pr  = obj_funcGenerales.RetornarPalabraExiste(m.getParametro(), Formatos.tipo_variables);
                String variable_pr = obj_funcGenerales.quitar_palabras(m.getParametro(), 
                                                                      tipo_vr_pr,
                                                                      null);
                variable_pr = variable_pr.trim();
                variables.add(new Variable(tipo_vr_pr, variable_pr));
                
            }
            
            for ( LineaCodigo array1 : m.getLineas_codigo() ) {
                
                
                
               String linea_de_codigo = array1.getCodigo();
               
               boolean metodo = false;
               boolean existe_variable = false;
               boolean existe_metodo = false;
                
               if( obj_funcGenerales.EvaluarPalabraExiste(linea_de_codigo, Formatos.ind_funcion_inicio) && 
                   obj_funcGenerales.EvaluarPalabraExiste(linea_de_codigo, Formatos.ind_funcion_fin) &&
                   obj_funcGenerales.EvaluarPalabraExiste(linea_de_codigo, Formatos.ind_variable) &&
                  !obj_funcGenerales.EvaluarPalabraExiste(linea_de_codigo, Formatos.ind_for) &&
                  !obj_funcGenerales.EvaluarPalabraExiste(linea_de_codigo, Formatos.ind_if) ){
                   metodo = true;
                }
               
                if ( obj_funcGenerales.EvaluarPalabraExiste( array1.getCodigo(), Formatos.ind_for )  && romper == false ){
                    if ( !obj_validar_for.validarFor( array1, obj_funcGenerales, variables )){
                        romper = true;
                        break;
                    } else {
                        
                        boolean encontro = false;
                        
                        for ( LineaCodigo arrayLineaCodigo : lista_aux ){
                            if ( arrayLineaCodigo.getLinea() > array1.getLinea() ){
                               if ( obj_funcGenerales.EvaluarPalabraExiste( arrayLineaCodigo.getCodigo(), Formatos.funcion_fin ) ){
                                   encontro = true;
                                   lista_aux.remove(arrayLineaCodigo);
                                   break;  
                               } 
                            }
                        }
                    
                        if(! encontro ){
                             MensajesGlobal.setMensaje_global("END del FOR no encotrado ", array1.getLinea()+"");
                             romper = true; 
                             break;
                        }
                    }
                } else if( obj_funcGenerales.EvaluarPalabraExiste( array1.getCodigo(), Formatos.ind_if ) && romper == false){
                    if ( !obj_validar_if.validarIf( array1, obj_funcGenerales,variables )){//PUSE NULL PARA QUE NO SACARA ERROR
                        romper = true;                                                //DEBE RECIBIR UN ARRAY DE VARIABLES      
                        break;
                    } else {
                        
                        boolean encontro = false;
                        
                        for ( LineaCodigo arrayLineaCodigo : lista_aux ){
                            if ( arrayLineaCodigo.getLinea() > array1.getLinea() ){
                                if ( obj_funcGenerales.EvaluarPalabraExiste( arrayLineaCodigo.getCodigo(), Formatos.funcion_fin ) ){
                                    encontro = true;
                                    lista_aux.remove(arrayLineaCodigo);
                                    break;
                                }
                            }
                        }
                        
                        if(! encontro ){
                             MensajesGlobal.setMensaje_global("END del IF no encotrado ", array1.getLinea()+"");
                             romper = true; 
                             break;
                        }
                        
                    }
                } else if ((obj_funcGenerales.EvaluarPalabraExiste(array1.getCodigo(), Formatos.tipo_variables)) && 
                           (obj_funcGenerales.EvaluarPalabraExiste(array1.getCodigo(), Formatos.ind_variable))
                           && romper == false) {
                    
                    String variable = obj_funcGenerales.quitar_palabras(array1.getCodigo(), null, new Object[]{Formatos.tipo_variables,Formatos.ind_variable});
                    variable = variable.trim();
                    
                    String tipo_variable = obj_funcGenerales.RetornarPalabraExiste(array1.getCodigo(), Formatos.tipo_variables);
                    
                    if(obj_funcGenerales.EvaluarPalabraExiste(array1.getCodigo(), Formatos.asignacion)){
                        variable = variable.substring( 0, variable.indexOf(Formatos.asignacion[0]));
                        variable = variable.replaceAll(" ", "");
                    }
                
                    existe_variable = validarNombreVariable( obj_clase.getVariables(), variables, variable, array1.getLinea()+"",0);
                    
                    if ( existe_variable ){
                        romper = true;
                        break;
                    } else {
                        variables.add(new Variable(tipo_variable, variable));
                    }
                }else if (  (!obj_funcGenerales.EvaluarPalabraExiste(array1.getCodigo(), Formatos.metodo_salida))&&
                            (obj_funcGenerales.EvaluarPalabraExiste(array1.getCodigo(), Formatos.ind_in))           
                            && romper == false) {
                    
                    String variable = obj_funcGenerales.quitar_palabras(array1.getCodigo(), null, new Object[]{Formatos.ind_in,Formatos.ind_variable});
                    variable = variable.trim();
                    
                    existe_variable = validarNombreVariable( obj_clase.getVariables(), variables, variable, array1.getLinea()+"",1);
                    
                    if ( !existe_variable ){
                        romper = true;
                        break;
                    } 
                    
                }else if (  (obj_funcGenerales.EvaluarPalabraExiste(array1.getCodigo(), Formatos.metodo_salida))&&
                            (!obj_funcGenerales.EvaluarPalabraExiste(array1.getCodigo(), Formatos.ind_in))           
                            && romper == false) {
                    
                    
                } else if ((obj_funcGenerales.EvaluarPalabraExiste(array1.getCodigo(), Formatos.asignacion)) && 
                           (obj_funcGenerales.EvaluarPalabraExiste(array1.getCodigo(), Formatos.ind_variable))
                           && romper == false) {
                   
                    String nombre_variable = array1.getCodigo().substring(
                                             0, 
                                             array1.getCodigo().indexOf(Formatos.asignacion[0])-1);
                    nombre_variable = nombre_variable.trim();
                    
                    
                    existe_variable = validarNombreVariable( obj_clase.getVariables(), variables, nombre_variable, array1.getLinea()+"",1);
                    
                    if ( !existe_variable ){
                        romper = true;
                        break;
                    }else{
                       String asignacion = array1.getCodigo().substring(
                                             array1.getCodigo().indexOf(Formatos.asignacion[0])+2, 
                                             array1.getCodigo().indexOf(Formatos.ind_variable[0])
                                             );
                        asignacion = asignacion.trim();
                        /* //funcion wilmer de validar operador*/
                        if(!obj_sem.valOperaNum2(asignacion, array1.getLinea()+"", variables)){
                            romper = true;
                            break;
                        }
                    }
                    
                    
                    
                }
     
                 if( romper ){
                  break;
                }else{
                     
                   if(metodo){
                      String nombre_funcion = array1.getCodigo().substring(
                                  array1.getCodigo().indexOf(Formatos.asignacion[0])+2, 
                                  array1.getCodigo().indexOf(Formatos.ind_funcion_inicio[0]) );

                      nombre_funcion = nombre_funcion.trim();
                      existe_metodo = validarNombreMetodo( obj_clase.getMetodos(), nombre_funcion, array1.getLinea()+"" );

                      if ( !existe_metodo ){
                              romper = true;
                              break;
                      }else{

                           String variable = array1.getCodigo().substring(
                                   array1.getCodigo().indexOf(Formatos.ind_funcion_inicio[0])+1, 
                                   array1.getCodigo().indexOf(Formatos.ind_funcion_fin[0]) );

                           existe_variable = validarNombreVariable( obj_clase.getVariables(), variables, variable, array1.getLinea()+"",1);

                          if ( !existe_variable ){
                             romper = true;
                             break;
                          } 

                      }   
                   }
                    
                 }
            }
            
            if( romper ){
                break;
            }
        }
        
        if ( romper ){
            return false;
        } else {
            return true;
        }
    }
    
    public boolean validarNombreVariable ( ArrayList<Variable> variableGlobal, ArrayList<Variable> variableLocal, String variableEvaluar, String num_linea, int no_existe ){
        boolean romper = false;
        
        for ( int i = 0; i < variableGlobal.size(); i++) {
            if (variableGlobal.get(i).getNombre().equals(variableEvaluar)){
                romper = true;
                break;
            }
        }
        
        for ( int i = 0; i < variableLocal.size(); i++) {
            if ( variableLocal.get(i).getNombre().equals(variableEvaluar)){
                romper = true;
                break;
            }
        }
        
        if ( romper ){
            if(no_existe == 0){
                MensajesGlobal.setMensaje_global("La variable "+variableEvaluar+" ya se encuentra declarada.", num_linea); 
            }
            return true;
        } else {
            if(no_existe == 1){
                MensajesGlobal.setMensaje_global("La variable "+variableEvaluar+" no se encuentra declarada.", num_linea);
            }
            return false;
        }
    }
    
    public boolean validarNombreMetodo ( ArrayList<Metodo> metodoGlobal, String metodoEvaluar, String num_linea ){
        boolean romper = false;
        
        for ( int i = 0; i < metodoGlobal.size(); i++) {
            if ( metodoGlobal.get(i).getNombre().equals(metodoEvaluar) ){
                romper = true;
                break;
            }
        }
        
        if ( !romper ){
            MensajesGlobal.setMensaje_global("El metodo "+metodoEvaluar+" no se encuentra declarado.", num_linea);
            return false;
        } else {
            return true;
        }
    }
}
