package compiladorprogvi;

import java.util.ArrayList;

/**
 *
 * @author Felipe
 */
public class ValidarFor {
    
    public boolean validarFor(LineaCodigo objeto,FuncionesGenerales obj){
        int ultimo;
        boolean salida=false;
        //Operaciones.imprimirCadena(obj.quitar_palabras(objeto.getCodigo(), null, new Object[]{Formatos.borrado_for}));
        String resultado=(obj.quitar_palabras(objeto.getCodigo(), null, new Object[]{Formatos.borrado_for}));
        resultado=resultado.trim();
        ultimo=resultado.length();
        if (obj.validarParentesis(resultado)){
            ArrayList<String> vector=new ArrayList<String>();
            vector=obj.Split_string(resultado.substring(1,ultimo-1).trim(),";");
            salida=true;
            if (vector.size()==3){//VALIDA QUE EL FOR TENGA 3 PARAMETROS
               for (String vector1 : vector) { 
                   
                    if (vector1.equals(" ")||vector1.equals("")){
                        salida=false;
                    }                   
               } 
            }else{                
                salida=false;
            }
            if (!salida) {
                MensajesGlobal.setMensaje_global("Faltan parametros en For", objeto.getLinea()+"");
            }    
        }else{
            MensajesGlobal.setMensaje_global("Expresión perdida '('  ", objeto.getLinea()+"");
            salida=false;    
        }           
        return salida;
    }
}
