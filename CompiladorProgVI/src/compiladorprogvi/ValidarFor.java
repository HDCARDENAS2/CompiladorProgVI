package compiladorprogvi;
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
        
        String funcion_inicio=resultado.charAt(0)+"".trim();                
        String funcion_fin=resultado.substring(ultimo-1);
        
        if((funcion_inicio.equals(Formatos.ind_funcion_inicio[0])) && (funcion_fin.equals(Formatos.ind_funcion_fin[0]))){
            salida=true;
        }else{
            MensajesGlobal.setMensaje_global("Expresión perdida '('  ", objeto.getLinea()+"");
            salida=false;    
        }           
        return salida;
    }
}
