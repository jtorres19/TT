package racadauto;

import Conexion.Conexion;
import com.mysql.jdbc.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.spi.DirStateFactory.Result;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class Eliminar_medida extends javax.swing.JFrame {

    private Statement sentencia;
    Conexion con = new Conexion();
    Connection cn = (Connection) con.getConnection();
    private String msj;
    DefaultTableModel modeloTabla;
    String filtro;
    private TableRowSorter trsfiltro;

    public Eliminar_medida() {
        modeloTabla = new DefaultTableModel(null, getColumnas());
        setFilas();
        initComponents();
    }

    private String[] getColumnas() {

        String columna[] = new String[]{"ID MEDIDA", "NOMBRE"};

        return columna;
    }

    private void setFilas() {
        try {
            sentencia = (com.mysql.jdbc.Statement) cn.createStatement();
            ResultSet lista = sentencia.executeQuery("SELECT * FROM unidad_medida");
            Object datos[] = new Object[9];
            while (lista.next()) {
                for (int i = 0; i < 2; i++) {
                    datos[i] = lista.getObject(i + 1);
                }
                modeloTabla.addRow(datos);
            }
        } catch (Exception e) {
            msj = "No se pudo llenar tabla";
        }
    }

    void limpiaTabla() {
        if (modeloTabla.getRowCount() > 0){
            do {
                modeloTabla.getRowCount();
                modeloTabla.removeRow(0);
            } while (modeloTabla.getRowCount() != 0);
        }
    }
    
    public void filtro() {

        filtro = txtbuscarxnom.getText().toUpperCase();
        int columna = 1;
        trsfiltro.setRowFilter(RowFilter.regexFilter(txtbuscarxnom.getText().toUpperCase(), columna));
    }
    
    //verifica si los items están en otras tablas
    public int verificar() {
        int yes = 0;
        int medida = 0;
        int medida2 = 0;
        String nom = jTable1.getValueAt(jTable1.getSelectedRow(), 1).toString().trim();
        try {
            sentencia = (com.mysql.jdbc.Statement) cn.createStatement();
            ResultSet rs = sentencia.executeQuery("SELECT id_medida FROM unidad_medida WHERE nombre = '" + nom + "'");
            while (rs.next()) {
                medida = rs.getInt("id_medida");
            }
        } catch (SQLException eg) {
            msj = "Error con su Solicitud";
        }
        try {
            sentencia = (com.mysql.jdbc.Statement) cn.createStatement();
            ResultSet rs = sentencia.executeQuery("SELECT * FROM inventario");
            while (rs.next()) {
                medida2 = rs.getInt("id_medida");
                if (medida == medida2) {
                    yes += rs.getInt("cod_item");
                }
            }

        } catch (SQLException t) {
            msj = "Error con su Solicitud";
        }
        
        if (yes > 0){
            JOptionPane.showMessageDialog(null,
                    "ERROR, UNIDAD DE MEDIDA referenciado en otras tablas no se puede eliminar", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
        }
        
        return yes;
    }

    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cmbCod = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        JB_cancel = new javax.swing.JButton();
        BTN_Del = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        txtbuscarxnom = new javax.swing.JTextField();
        LBL_estado = new javax.swing.JLabel();

        cmbCod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbCodActionPerformed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("ELIMINAR ITEM");
        setMinimumSize(new java.awt.Dimension(420, 210));
        setResizable(false);

        jLabel9.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel9.setText("RACAD AUTOMOTRIZ - ELIMINAR MEDIDA");

        JB_cancel.setText("Volver");
        JB_cancel.setPreferredSize(new java.awt.Dimension(100, 35));
        JB_cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JB_cancelActionPerformed(evt);
            }
        });

        BTN_Del.setText("Eliminar");
        BTN_Del.setPreferredSize(new java.awt.Dimension(100, 35));
        BTN_Del.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_DelActionPerformed(evt);
            }
        });

        jTable1.setModel(modeloTabla);
        jScrollPane1.setViewportView(jTable1);

        jLabel1.setText("Filtrar por Nombre:");

        txtbuscarxnom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtbuscarxnomActionPerformed(evt);
            }
        });
        txtbuscarxnom.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtbuscarxnomKeyTyped(evt);
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
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtbuscarxnom, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(BTN_Del, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(LBL_estado, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(JB_cancel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 408, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 10, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtbuscarxnom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(JB_cancel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(BTN_Del, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(LBL_estado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void cmbCodActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbCodActionPerformed
        //copia fallida
    }//GEN-LAST:event_cmbCodActionPerformed

    private void JB_cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JB_cancelActionPerformed
        this.dispose();
    }//GEN-LAST:event_JB_cancelActionPerformed

    private void BTN_DelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_DelActionPerformed
        if (jTable1.getSelectedRow() == -1){
            JOptionPane.showMessageDialog(null,
                    "ERROR, No se ha seleccionado ninguna fila", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
        }
        
        String nom = jTable1.getValueAt(jTable1.getSelectedRow(), 1).toString().trim();
        
        int i = JOptionPane.showConfirmDialog(this,
                "¿Realmente Desea Eliminar " + nom + " De Las UNIDADES DE MEDIDA?","Confirmar Eliminación",
                JOptionPane.YES_NO_OPTION);
        
        int medida = 0;
        try {
            sentencia = (com.mysql.jdbc.Statement) cn.createStatement();
            ResultSet rs = sentencia.executeQuery("SELECT id_medida FROM unidad_medida WHERE nombre = '" + nom + "'");
            while (rs.next()) {
                medida = rs.getInt("id_medida");
            }
        } catch (SQLException e) {
            msj = "Error al buscar MEDIDA en tabla?";
            LBL_estado.setText(msj);
        }

        
        if (verificar() == 0 && i== 0) {
            String sql = "DELETE FROM unidad_medida WHERE id_medida =" + medida + "";
            try {
                sentencia.executeUpdate(sql);
                LBL_estado.setText("UNIDAD DE MEDIDA Borrada Con Éxito");
            } catch (SQLException ee) {
                msj = "Error Al Borrar";
                LBL_estado.setText(msj);
            }
        }
        
        limpiaTabla();
        setFilas();
        
    }//GEN-LAST:event_BTN_DelActionPerformed

    private void txtbuscarxnomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtbuscarxnomActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtbuscarxnomActionPerformed

    private void txtbuscarxnomKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbuscarxnomKeyTyped
        // TODO add your handling code here:
        txtbuscarxnom.addKeyListener(new KeyAdapter() {
            public void keyReleased(final KeyEvent e) {
                filtro();
            }
        });
        trsfiltro = new TableRowSorter(modeloTabla);
        jTable1.setRowSorter(trsfiltro);
    }//GEN-LAST:event_txtbuscarxnomKeyTyped

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
            java.util.logging.Logger.getLogger(Eliminar_medida.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Eliminar_medida.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Eliminar_medida.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Eliminar_medida.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Eliminar_medida().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BTN_Del;
    private javax.swing.JButton JB_cancel;
    private javax.swing.JLabel LBL_estado;
    private javax.swing.JComboBox<String> cmbCod;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField txtbuscarxnom;
    // End of variables declaration//GEN-END:variables
}
