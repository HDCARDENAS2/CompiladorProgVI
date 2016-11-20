
package compiladorprogvi;

import java.util.ArrayList;

/**
 *
 * @author Felipe
 */
public class ValidarIf {
    public boolean validarIf(LineaCodigo objeto,FuncionesGenerales obj){
        int ultimo;
        
        boolean salida=true;
        String resultado=(obj.quitar_palabras(objeto.getCodigo(), null, new Object[]{Formatos.borrado_if}));
        resultado=resultado.trim();
        ultimo=resultado.length();
        
        if (obj.validarParentesis(resultado)){
            ArrayList<String> vector=new ArrayList<String>();
            vector=obj.Split_string(resultado.substring(1,ultimo-1).trim(),"");
            
            salida=true;
            
            if((vector.size()==1)||(vector.size()%2==0)){
                obj.imprimirCadena("ES 1 O MODULO");
                for (String vector1 : vector) {    
                    obj.imprimirCadena(vector1);
                    if (vector1.equals(" ")||vector1.equals("")){
                        
                        salida=false;
                    }                   
               } 
            }else{
                MensajesGlobal.setMensaje_global("Faltan parametros  ", objeto.getLinea()+"");
                salida=false; 
            }
                
        }else{
            MensajesGlobal.setMensaje_global("Expresión perdida '('  ", objeto.getLinea()+"");
            salida=false;    
        }       
        
        return salida;
    }
}
