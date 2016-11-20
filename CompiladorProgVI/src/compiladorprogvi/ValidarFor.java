package compiladorprogvi;

import java.util.ArrayList;

/**
 *
 * @author Felipe
 */
public class ValidarFor {
    
    public boolean validarFor(LineaCodigo objeto,FuncionesGenerales obj,ArrayList<Variable> variables){
        int ultimo;
        Semantica obj_sem=new Semantica();
        boolean salida=false;
        //Operaciones.imprimirCadena(obj.quitar_palabras(objeto.getCodigo(), null, new Object[]{Formatos.borrado_for}));
        String resultado=(obj.quitar_palabras(objeto.getCodigo(), null, new Object[]{Formatos.borrado_for}));
        resultado=resultado.trim();
        ultimo=resultado.length();
        if (obj.validarParentesis(resultado)){
            ArrayList<String> vector=new ArrayList<String>();
            vector=obj.Split_string(resultado.substring(1,ultimo-1).trim(),";");
            salida=true;
            
            boolean nomensaje = true;
            
            if (vector.size()==3){//VALIDA QUE EL FOR TENGA 3 PARAMETROS
               for (String vector1 : vector) {                    
                    if (vector1.equals(" ")||vector1.equals("")){
                        salida=false;
                    } else{
                        if(obj_sem.valSemFor(vector1,objeto.getLinea()+"",variables)){
                           salida=true; 
                        }else{
                            salida=false;
                            nomensaje = false;
                            break;
                        }
                    }                  
               } 
            }else{                
                salida=false;
            }
            if (!salida && nomensaje) {
                MensajesGlobal.setMensaje_global("Faltan parametros en For", objeto.getLinea()+"");
            }    
        }else{
            MensajesGlobal.setMensaje_global("Expresión perdida '('  ", objeto.getLinea()+"");
            salida=false;    
        }           
        return salida;
    }
}
