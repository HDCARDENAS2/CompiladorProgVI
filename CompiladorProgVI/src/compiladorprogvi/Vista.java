/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiladorprogvi;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Felipe
 */
public class Vista extends javax.swing.JFrame {

    private FuncionesGenerales obj_func;
    private LectorClase obj_clase;
    
    /**
     * Creates new form Vista
     */
    public Vista() {
        initComponents();
        obj_func = new FuncionesGenerales();
        obj_clase = new LectorClase();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setText("Compilar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 360, -1, -1));

        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);
        jScrollPane2.setViewportView(jTextArea2);

        jScrollPane4.setViewportView(jScrollPane2);

        getContentPane().add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 422, 475, 100));

        jLabel1.setText("Salida");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 402, -1, -1));

        jLabel2.setText("Entrada");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, -1, -1));

        jLabel3.setText("COMPILADOR");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(209, 0, -1, -1));

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jTextArea1.setText("class Numero begin \n   int a;\n   \n  void main() begin\n\n  out \"ingrese numero\";\n  in  a;\n  int b := sumaPares(a);\n  out \"La salida es \", b;\n\nend\n\n int sumaPares(int a) begin\n    int suma;\n    for( i := 1; i<=a; i++) begin\n       if(i %2 = 0 ) begin\n          suma := suma + i;\n      end \n   end\n end\nend");
        jScrollPane1.setViewportView(jTextArea1);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 490, 290));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

      ArrayList<LineaCodigo> array = obj_func.TranFomarTextoaArray(jTextArea1.getText());
      Operaciones ope=new Operaciones();
      ValidarFor obj_validar=new ValidarFor();
     
      Clase clase_generada = obj_clase.GenerarClase(array,obj_func);
      if(clase_generada != null){
          
          for (Variable v : clase_generada.getVariables()) {
              System.out.println(v.toString());
          }
          
          boolean romper=false;
          for (Metodo m : clase_generada.getMetodos()) {
              
              System.out.println(m.getTipo());
              System.out.println(m.getNombre());
              System.out.println(m.getParametro());
         
              for (LineaCodigo array1 : m.getLineas_codigo()) {                  
                  if (obj_func.EvaluarPalabraExiste(array1.getCodigo(),Formatos.ind_for)){
                      if (!obj_validar.validarFor(array1,obj_func)){
                          romper=true;
                          break;                          
                      }                      
                  }     
              }
              if(romper){
                  break;
              }
          }      
          
          if (romper){
             MensajeCompilador(MensajesGlobal.getMensaje_global()); 
          }else {
          MensajeCompilador("Correcto");
          }
          //Evaluar metodos
      }else{
          MensajeCompilador(MensajesGlobal.getMensaje_global());
      }
    }//GEN-LAST:event_jButton1ActionPerformed

    
     public void MensajeCompilador(String mensaje){
         jTextArea2.setText(mensaje);
     }
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Vista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Vista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Vista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Vista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Vista().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
    // End of variables declaration//GEN-END:variables
}
