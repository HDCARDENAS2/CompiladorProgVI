
package compiladorprogvi;

/**
 *
 * @author Felipe
 */
public class ValidarIf {
    public boolean validarIf(LineaCodigo objeto,FuncionesGenerales obj){
        boolean salida=true;
        String resultado=(obj.quitar_palabras(objeto.getCodigo(), null, new Object[]{Formatos.borrado_if}));
        resultado=resultado.trim();
        if (obj.validarParentesis(resultado)){
            
        }else{
            MensajesGlobal.setMensaje_global("Expresión perdida '('  ", objeto.getLinea()+"");
            salida=false;    
        }       
        
        return salida;
    }
}
