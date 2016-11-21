
package compiladorprogvi;

import java.util.ArrayList;

/**
 *
 * @author Felipe
 */
public class ValidarIf {
    public boolean validarIf(LineaCodigo objeto,FuncionesGenerales obj,ArrayList<Variable> variables){
        int ultimo;
        String nuevaCadena;
        boolean salida=true;
        Semantica obj_sem=new Semantica();
        String resultado=(obj.quitar_palabras(objeto.getCodigo(), null, new Object[]{Formatos.borrado_if}));
        resultado=resultado.trim();
        ultimo=resultado.length();
        
        if (obj.validarParentesis(resultado)){
            ArrayList<String> vector=new ArrayList<String>();            
            nuevaCadena=resultado.substring(1,ultimo-1).trim();
            nuevaCadena=nuevaCadena.replace("AND",";");
            nuevaCadena=nuevaCadena.replace("OR",";");   
            nuevaCadena=nuevaCadena.replace("NOT",";");
            ultimo=nuevaCadena.length();
            boolean nomensaje = true;
            if (!(nuevaCadena.charAt(ultimo-1)+"").equals(";")){
                 vector=obj.Split_string(nuevaCadena.trim(),";");//
                    
                    salida=true;            
                    //if((vector.size()==1)||(vector.size()%2==0)){                
                        for (String vector1 : vector) {                      
                            if (vector1.equals(" ")||vector1.equals("")){                        
                                salida=false;
                            }else{                        
                                if(obj_sem.valSemIf(vector1,objeto.getLinea()+"",variables)){
                                   salida=true; 
                                }else{
                                    salida=false;
                                    nomensaje = false;
                                    break;
                                }
                            }                   
                       } 
                    //}else{               
                    //    salida=false; 
                    //}//aqui
            }else { salida=false;}
                   
             if (!salida && nomensaje) {
                MensajesGlobal.setMensaje_global("Faltan parametros en IF", objeto.getLinea()+"");
            }    
                
        }else{
            MensajesGlobal.setMensaje_global("Expresión perdida '('  ", objeto.getLinea()+"");
            salida=false;    
        }       
        
        return salida;
    }
}
