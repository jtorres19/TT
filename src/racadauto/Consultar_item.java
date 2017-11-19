package racadauto;

import com.mysql.jdbc.*;
import java.sql.DriverManager;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.RowFilter;
import javax.swing.table.TableRowSorter;

public class Consultar_item extends javax.swing.JFrame /*implements ActionListener*/ {

    private Statement sentencia;
    private Connection conexion;
    private String nomBD = "racad";
    private String usuario = "root";
    private String password = "";
    private String msj;
    DefaultTableModel modeloTabla;
    private TableRowSorter trsfiltro;
    String filtro;

    public Consultar_item() {

        conectar();
        modeloTabla = new DefaultTableModel(null, getColumnas());
        setFilas();
        initComponents();
    }

    private String[] getColumnas() {

        String columna[] = new String[]{"Nombre", "Stock actual", "Stock crítico", "Valor costo", "Valor venta", "Medida", "Familia"};

        return columna;
    }

    private void setFilas() {
        try {
            sentencia = (Statement) conexion.createStatement();
            ResultSet lista = sentencia.executeQuery("SELECT i.nombre, i.stock_actual, i.stock_critico, i.valor_costo, i.valor_venta, um.nombre, f.nombre"
                    + " FROM inventario i INNER JOIN unidad_medida um ON i.id_medida = um.id_medida LEFT JOIN familia f ON i.id_familia = f.id_familia "
                    + "WHERE i.estado = 1");
            Object datos[] = new Object[7];
            while (lista.next()) {
                for (int i = 0; i < 7; i++) {
                    datos[i] = lista.getObject(i + 1);
                }
                modeloTabla.addRow(datos);
            }
        } catch (Exception e) {
            msj = "No se pudo llenar tabla";
        }
    }

    public void filtro() {

        filtro = txtbuscarxnom.getText().toUpperCase();
        int columna = 0;
        trsfiltro.setRowFilter(RowFilter.regexFilter(txtbuscarxnom.getText().toUpperCase(), columna));
    }
    
    public void filtro2() {

        filtro = txt_compra.getText();
        int columna = 3;
        trsfiltro.setRowFilter(RowFilter.regexFilter(txt_compra.getText(), columna));
    }
    
    public void filtro3() {

        filtro = txt_venta.getText();
        int columna = 4;
        trsfiltro.setRowFilter(RowFilter.regexFilter(txt_venta.getText(), columna));
    }

    public void clean() {
        try {
            modeloTabla.setRowCount(0);
        } catch (Exception e) {
            msj = "No se pudo vaciar tabla";
        }
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

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jRadioButtonMenuItem1 = new javax.swing.JRadioButtonMenuItem();
        jButton2 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        JB_cancel = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        txtbuscarxnom = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txt_compra = new javax.swing.JTextField();
        txt_venta = new javax.swing.JTextField();

        jRadioButtonMenuItem1.setSelected(true);
        jRadioButtonMenuItem1.setText("jRadioButtonMenuItem1");

        jButton2.setText("jButton2");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel9.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel9.setText("RACAD AUTOMOTRIZ - CONSULTAR INVENTARIO");

        JB_cancel.setText("Salir");
        JB_cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JB_cancelActionPerformed(evt);
            }
        });

        jTable1.setAutoCreateRowSorter(true);
        jTable1.setModel(modeloTabla);
        jScrollPane1.setViewportView(jTable1);

        txtbuscarxnom.setToolTipText("busqueda por nombre");
        txtbuscarxnom.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
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

        jLabel2.setText("Nombre :");

        jButton1.setText("Menor o Igual a Critico");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton3.setText("Tabla Completa");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel3.setText("Precio Compra :");

        jLabel4.setText("Precio Venta :");

        txt_compra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_compraActionPerformed(evt);
            }
        });
        txt_compra.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_compraKeyTyped(evt);
            }
        });

        txt_venta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_ventaKeyTyped(evt);
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
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 426, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txt_compra, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel4)
                                .addGap(18, 18, 18)
                                .addComponent(txt_venta, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(JB_cancel, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel2)
                                    .addGap(18, 18, 18)
                                    .addComponent(txtbuscarxnom, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(41, 41, 41)
                                    .addComponent(jButton1)
                                    .addGap(47, 47, 47)
                                    .addComponent(jButton3))
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(16, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtbuscarxnom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jButton1)
                    .addComponent(jButton3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(txt_compra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_venta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JB_cancel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void JB_cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JB_cancelActionPerformed
        this.dispose();
    }//GEN-LAST:event_JB_cancelActionPerformed

    private void txtbuscarxnomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtbuscarxnomActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtbuscarxnomActionPerformed

    private void txtbuscarxnomKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbuscarxnomKeyTyped
        txtbuscarxnom.addKeyListener(new KeyAdapter() {
            public void keyReleased(final KeyEvent e) {
                filtro();
            }
        });
        trsfiltro = new TableRowSorter(modeloTabla);
        jTable1.setRowSorter(trsfiltro);
    }//GEN-LAST:event_txtbuscarxnomKeyTyped

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        clean();
        try {
            sentencia = (Statement) conexion.createStatement();
            ResultSet lista = sentencia.executeQuery("SELECT i.nombre, i.stock_actual, i.stock_critico, i.valor_costo, i.valor_venta, um.nombre, f.nombre"
                    + " FROM inventario i INNER JOIN unidad_medida um ON i.id_medida = um.id_medida LEFT JOIN familia f ON i.id_familia = f.id_familia "
                    + "WHERE i.stock_actual  <= i.stock_critico");
            Object datos[] = new Object[7];
            while (lista.next()) {
                for (int i = 0; i < 7; i++) {
                    datos[i] = lista.getObject(i + 1);
                }
                modeloTabla.addRow(datos);
            }
        } catch (Exception e) {
            msj = "No se pudo llenar tabla";
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        clean();
        setFilas();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void txt_compraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_compraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_compraActionPerformed

    private void txt_ventaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_ventaKeyTyped
        txt_venta.addKeyListener(new KeyAdapter() {
            public void keyReleased(final KeyEvent e) {
                filtro3();
            }
        });
        trsfiltro = new TableRowSorter(modeloTabla);
        jTable1.setRowSorter(trsfiltro);
    }//GEN-LAST:event_txt_ventaKeyTyped

    private void txt_compraKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_compraKeyTyped
        txt_compra.addKeyListener(new KeyAdapter() {
            public void keyReleased(final KeyEvent e) {
                filtro2();
            }
        });
        trsfiltro = new TableRowSorter(modeloTabla);
        jTable1.setRowSorter(trsfiltro);
    }//GEN-LAST:event_txt_compraKeyTyped

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
            java.util.logging.Logger.getLogger(Consultar_item.class
.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        

} catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Consultar_item.class
.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        

} catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Consultar_item.class
.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        

} catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Consultar_item.class
.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Consultar_item().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton JB_cancel;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JRadioButtonMenuItem jRadioButtonMenuItem1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField txt_compra;
    private javax.swing.JTextField txt_venta;
    private javax.swing.JTextField txtbuscarxnom;
    // End of variables declaration//GEN-END:variables
}
