/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiladorprogvi;

import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 *
 * @author hernandario
 */
public class LectorClase {

    public Clase GenerarClase(ArrayList<LineaCodigo> array, FuncionesGenerales obj_func) {

        if (array.size() > 0) {

            String nombre = "";

            boolean flitro_clase = false;
            int linea_clase = -1;
            //Buscar la class

            for (LineaCodigo lineaCodigo : array) {
                String linea = lineaCodigo.getCodigo();
                String nr_linea = lineaCodigo.getLinea() + "";
                //Valida si existe clase
                if (obj_func.EvaluarPalabraExiste(linea, Formatos.clase)) {
                    if (obj_func.EvaluarPalabraExiste(linea, Formatos.funcion_incio)) {
                        String temp = obj_func.quitar_palabras(linea, Formatos.clase[0] + "," + Formatos.funcion_incio[0], null);
                        nombre = temp.trim();
                        flitro_clase = true;
                        linea_clase = lineaCodigo.getLinea();
                        break;
                    } else {
                        MensajesGlobal.setMensaje_global("No hay una metodo de begin a evaluar", nr_linea);
                    }
                }
            }

            if (flitro_clase) {
                //Se borran las lineas
                
                ArrayList<LineaCodigo> array_2 = (ArrayList<LineaCodigo> ) array.clone();
                
                for (LineaCodigo lineaCodigo : array) {
                    if (lineaCodigo.getLinea() > linea_clase) {
                        break;
                    } else {
                        array_2.remove(lineaCodigo);
                    }
                }
                
                array = (ArrayList<LineaCodigo> ) array_2.clone();

                for (int i = array.size()-1; i > 0; i--) {
                    
                    LineaCodigo lineaCodigo = array_2.get(i);
                    String linea = lineaCodigo.getCodigo();
                    array_2.remove(lineaCodigo);
                    if (obj_func.EvaluarPalabraExiste(linea, Formatos.funcion_fin)) {
                        break;
                    }
                }

                array = (ArrayList<LineaCodigo> ) array_2.clone();
                
                if (array.size() > 0) {

                            ArrayList<Metodo> metodos = new ArrayList<>();
                            ArrayList<LineaCodigo> lineas_codigo_metodo = new  ArrayList<> ();
                            ArrayList<Variable> variables = new ArrayList<>();
                          
                            String tipo_funcion = "";
                            String nombre_funcion = "";
                            String parametros = "";
                            int linea_max = -1;
                            boolean barrido_lineas = false;
                            boolean fallo = false;
    
                            for (LineaCodigo lineaCodigo : array) {

                                String linea = lineaCodigo.getCodigo();
                                String nr_linea = lineaCodigo.getLinea() + "";
                                
                                if( lineaCodigo.getLinea() > linea_max && linea_max != -1){
                                    barrido_lineas = false;
                                    lineas_codigo_metodo = new  ArrayList<> ();
                                }

                                if (barrido_lineas == true ) {
  
                                    if(lineaCodigo.getLinea() == linea_max){
                                        metodos.add(new Metodo(nombre_funcion, tipo_funcion, parametros, lineas_codigo_metodo));
                                        barrido_lineas = false;
                                    }else{
                                        lineas_codigo_metodo.add(lineaCodigo);
                                    }

                                } else if (barrido_lineas == false) {
                                    
                                   Object[] resultado = obj_func.evaluar_metodo(linea, nr_linea);

                                   if((boolean) resultado[0] == true){
                                       fallo = true;
                                       break;
                                   }else{
                                      
                                       //METODOS
                                       if( (int) resultado[1] == 1){
                                              int index_cortar_2 = linea.indexOf(Formatos.ind_funcion_inicio[0]);
                                              
                                              parametros = linea.substring(
                                                      linea.indexOf(Formatos.ind_funcion_inicio[0]) + 1,
                                                      linea.indexOf(Formatos.ind_funcion_fin[0])
                                              );
                                              linea = linea.replace(parametros, "");

                                              nombre_funcion = linea.substring(
                                                      0,
                                                      index_cortar_2 == -1 ? 0 : index_cortar_2
                                              );

                                              nombre_funcion = obj_func.quitar_palabras(nombre_funcion, null, new Object[]{Formatos.tipo_funcion});

                                              nombre_funcion = nombre_funcion.trim();
                                              nombre_funcion = nombre_funcion.replaceAll(" ", "");

                                              if (obj_func.No_existe_metodo(nombre_funcion, metodos)) {

                                                  tipo_funcion = obj_func.RetornarPalabraExiste(linea, Formatos.tipo_funcion);
                                                  
                                                  barrido_lineas=true;
                                            
                                                  for (LineaCodigo lineaCodigo2 : array_2) {
                                                      
                                                    if(lineaCodigo2.getLinea() > lineaCodigo.getLinea()){
      
                                                      String linea_aux    = lineaCodigo2.getCodigo();
                                                      String nr_linea_aux = lineaCodigo2.getLinea() + "";

                                                      if (obj_func.EvaluarPalabraExiste(linea_aux, Formatos.funcion_fin)) {
                                                          linea_max = lineaCodigo2.getLinea();
                                                      }else{
                                                          Object[] resultado2 = obj_func.evaluar_metodo(linea_aux,nr_linea_aux);
                                                          if((boolean) resultado2[0] == false && (int) resultado2[1] == 1){
                                                              break;
                                                          }else if((boolean) resultado2[0] == true){
                                                              fallo = true;
                                                              break;
                                                          }
                                                      } 
                                                     }
                                                  }  
                                                  
                                                  if( fallo ){
                                                     break;
                                                  }
                                                  
                                              } else {
                                                  MensajesGlobal.setMensaje_global("El metodo " + nombre_funcion + " ya existe ", nr_linea);
                                                  fallo = true;
                                              }
                                       }else if( (int) resultado[1] == 2){

                                            String temp = obj_func.quitar_palabras(linea, null, new Object[]{Formatos.tipo_variables, Formatos.ind_variable});
                                            temp = temp.trim();
                                            temp = temp.replaceAll(" ", "");

                                            if (obj_func.No_existe_variable(temp, variables)) {
                                                String tipo_variable = obj_func.RetornarPalabraExiste(linea, Formatos.tipo_variables);
                                                variables.add(new Variable(tipo_variable, temp));
                                                array_2.remove(lineaCodigo);
                                            } else {
                                                MensajesGlobal.setMensaje_global("la variable " + temp + " ya existe ", nr_linea);
                                                fallo = true;
                                            }                                     
                                       }
                                   } 
                                }
                            }

                            if (!fallo) {
                                return new Clase(nombre, metodos, variables);
                            }
                } else {
                    MensajesGlobal.setMensaje_global("No hay una funcion end para cerrar la clase.", null);
                }
            } else {
                MensajesGlobal.setMensaje_global("No hay una clase a evaluar", null);
            }

        } else {
            MensajesGlobal.setMensaje_global("No hay lineas de codigo a compilar", null);
        }
        return null;
    }


}
