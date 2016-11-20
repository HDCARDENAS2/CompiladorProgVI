/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiladorprogvi;

/**
 *
 * @author hernandario
 */
public class MensajesGlobal {
    
    private static String mensaje_global;

    public static String getMensaje_global() {
        return mensaje_global;
    }

    public static void setMensaje_global(String mensaje_global,String linea) {
        MensajesGlobal.mensaje_global = (linea == null ? mensaje_global : "Error Linea "+linea+" : "+mensaje_global);
    }
    
    
}
