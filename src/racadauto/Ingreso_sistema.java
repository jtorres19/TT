/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package racadauto;

import Conexion.Conexion;
import Conexion.Hash;
import Conexion.Trabajador;
import Conexion.sqlTrabajador;
import java.sql.Connection;
import javax.swing.JOptionPane;

/**
 *
 * @author falco
 */
public class Ingreso_sistema extends javax.swing.JFrame {

    Conexion con = new Conexion();
    Connection cn = (Connection) con.getConnection();

    public Ingreso_sistema() {
        initComponents();
    }

    public int verificar() {
        int cont = 0;

        String rut = JT_rut.getText().trim();
        String pass = new String(JP_contraseña.getPassword()).trim();

        if (rut.equals("") || pass.equals("")) {
            JOptionPane.showMessageDialog(null,
                    "ERROR, dejó una casilla vacía", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            cont++;
        } else if (!rut.matches("^([0-9]*\\d{7,8}[0-9k])$")) {
            JOptionPane.showMessageDialog(null, "ERROR, RUT mal escrito", "ERROR", JOptionPane.ERROR_MESSAGE);
            cont++;
        } else if (rut.length() > 9) {
            JOptionPane.showMessageDialog(null,
                    "ERROR, RUT maximo 9 digitos", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            cont++;
        }

        return cont;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        JT_rut = new javax.swing.JTextField();
        JB_cancel = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        JP_contraseña = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("LOG IN");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("RACAD AUTOMOTRIZ - INGRESAR AL SISTEMA");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("R.U.T:");

        jButton1.setText("OK");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        JB_cancel.setText("Cancelar");
        JB_cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JB_cancelActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("(Sin puntos ni guión)");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Contraseña:");

        JP_contraseña.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        JP_contraseña.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JP_contraseñaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 261, Short.MAX_VALUE)
                        .addComponent(JB_cancel))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel2))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(JT_rut, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
                                    .addComponent(JP_contraseña))
                                .addGap(18, 18, 18)
                                .addComponent(jLabel3)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(55, 55, 55)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(JT_rut, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(JP_contraseña, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 97, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JB_cancel)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void JB_cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JB_cancelActionPerformed
        System.exit(0);
    }//GEN-LAST:event_JB_cancelActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (verificar() == 0) {
            sqlTrabajador modSql = new sqlTrabajador();
            Trabajador mod = new Trabajador();
            int cont = 0;
            String rut = JT_rut.getText().trim();
            String pass = new String(JP_contraseña.getPassword()).trim();
            String contraseña = Hash.sha1(pass);
            String rut2 = "", cargo = "", pass2 = "";

            mod.setRut(rut);
            mod.setContraseña(contraseña);

            if (modSql.existeTrabajador(rut) == 0) {
                JOptionPane.showMessageDialog(null,
                        "ERROR, TRABAJADOR no existe", "ERROR",
                        JOptionPane.ERROR_MESSAGE);
            } else if (modSql.login(mod)) {

                Principal frmPrincipal = new Principal(mod);
                frmPrincipal.setVisible(true);
                this.dispose();

            } else {
                JOptionPane.showMessageDialog(null,
                        "ERROR, CONTRASEÑA no es correcta", "ERROR",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void JP_contraseñaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JP_contraseñaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JP_contraseñaActionPerformed

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
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Ingreso_sistema.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Ingreso_sistema.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Ingreso_sistema.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Ingreso_sistema.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Ingreso_sistema().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton JB_cancel;
    private javax.swing.JPasswordField JP_contraseña;
    private javax.swing.JTextField JT_rut;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    // End of variables declaration//GEN-END:variables
}
