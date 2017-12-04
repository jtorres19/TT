package racadauto;

import com.mysql.jdbc.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.spi.DirStateFactory.Result;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Cambiar_repuestos extends javax.swing.JFrame {

    private Statement sentencia;
    private Connection conexion;
    private String nomBD = "racad";
    private String usuario = "root";
    private String password = "";
    private String msj;
    DefaultTableModel modeloTabla;

    public Cambiar_repuestos() {
        conectar();
        modeloTabla = new DefaultTableModel(null, getColumnas());
        setFilas();
        initComponents();
    }

    private String[] getColumnas() {
        String columna[] = new String[]{"Nombre", "ID", "Marca", "ID", "Modelo"};
        return columna;
    }
    
    private void setFilas() {
        try {
            sentencia = (com.mysql.jdbc.Statement) conexion.createStatement();
            ResultSet lista = sentencia.executeQuery("SELECT i.nombre, r.id_marca, ma.nombre, r.id_modelo, mo.nombre "
                    + "FROM repuestos r INNER JOIN inventario i ON r.cod_item=i.cod_item "
                    + "LEFT JOIN marca ma ON r.id_marca=ma.id_marca "
                    + "LEFT JOIN modelo mo ON r.id_modelo=mo.id_modelo");
            Object datos[] = new Object[5];
            while (lista.next()) {
                for (int i = 0; i < 5; i++) {
                    datos[i] = lista.getObject(i + 1);
                }
                modeloTabla.addRow(datos);
            }
        } catch (Exception e) {
            msj = "No se pudo llenar tabla";
        }
    }
    
    void limpiaTabla() {
        do {
            modeloTabla.setRowCount(0);
        } while(modeloTabla.getRowCount() != 0);
    }
    
    public void conectar() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/" + this.nomBD;
            this.conexion = (Connection) DriverManager.getConnection(url, this.usuario, this.password);
            this.sentencia = (Statement) this.conexion.createStatement();
        } catch (Exception e) {
            msj = "error al conectar";
        }
    }
    
    public int verificar1(){
        int v=0;
        if (LBL_estado1.getText().equals("")){
            v++;
        }
        return v;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel9 = new javax.swing.JLabel();
        JB_cancel1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        LBL_estado1 = new javax.swing.JLabel();
        LBL_estado2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel9.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel9.setText("RACAD AUTOMOTRIZ - CAMBIAR REPUESTO");

        JB_cancel1.setText("Salir");
        JB_cancel1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JB_cancel1ActionPerformed(evt);
            }
        });

        jTable1.setModel(modeloTabla);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jButton1.setText("Borrar Propiedades");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(LBL_estado1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(LBL_estado2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1)
                        .addGap(18, 18, 18)
                        .addComponent(JB_cancel1, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 479, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(LBL_estado1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(JB_cancel1)
                        .addComponent(jButton1))
                    .addComponent(LBL_estado2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void JB_cancel1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JB_cancel1ActionPerformed
        this.dispose();
    }//GEN-LAST:event_JB_cancel1ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        String nom = jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString();
        LBL_estado1.setText("Item : " + nom +"");

    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (verificar1()==0){
            String nom = jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString();
            int ma = Integer.parseInt(jTable1.getValueAt(jTable1.getSelectedRow(), 1).toString());
            int mo = Integer.parseInt(jTable1.getValueAt(jTable1.getSelectedRow(), 3).toString());
            int i =JOptionPane.showConfirmDialog(this,
                    "¿Realmente Desea Borrar las Propiedades del Repuesto " + nom + "?","Confirmar Eliminación",
                    JOptionPane.YES_NO_OPTION);
            int cod = 0;
            try {
                sentencia = (com.mysql.jdbc.Statement) conexion.createStatement();
                ResultSet rs = sentencia.executeQuery("SELECT cod_item FROM inventario WHERE nombre = '" + nom + "'");
                while (rs.next()) {
                    cod = rs.getInt("cod_item");
                }
            } catch (SQLException e) {
                msj = "Error al buscar item en tabla?";
                LBL_estado2.setText(msj);
            }
            String sql = "DELETE FROM repuestos WHERE cod_item =" + cod + " AND id_marca =" + ma + " AND id_modelo = " + mo + "";
                try {
                    sentencia.executeUpdate(sql);
                    LBL_estado2.setText("Propiedades quitadas");
                } catch (SQLException ee) {
                    msj = "Error en el proceso";
                    LBL_estado2.setText(msj);
                }
                limpiaTabla();
                LBL_estado1.setText("");
                setFilas();
            }else{
                JOptionPane.showMessageDialog(null,
                    "Error, Seleccione algo!", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
        }
        
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(ModEli_insumo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ModEli_insumo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ModEli_insumo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ModEli_insumo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Cambiar_repuestos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton JB_cancel1;
    private javax.swing.JLabel LBL_estado1;
    private javax.swing.JLabel LBL_estado2;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
