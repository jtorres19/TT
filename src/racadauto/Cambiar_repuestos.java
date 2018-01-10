package racadauto;

import com.mysql.jdbc.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

public class Cambiar_repuestos extends javax.swing.JFrame {

    private Statement sentencia;
    private Connection conexion;
    private String nomBD = "racad";
    private String usuario = "root";
    private String password = "";
    private String msj;
    DefaultTableModel modeloTabla;
    String filtro;
    private TableRowSorter trsfiltro;


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
    
    public void filtro() {
        filtro = txtbuscarxnom.getText().toUpperCase();
        int columna = 0;
        trsfiltro.setRowFilter(RowFilter.regexFilter(txtbuscarxnom.getText().toUpperCase(), columna));
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel9 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        LBL_estado1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        JB_cancel = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        txtbuscarxnom = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jLabel9.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel9.setText("CAMBIAR REPUESTO");

        jTable1.setModel(modeloTabla);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jButton1.setText("Borrar Propiedades");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setText("Repuesto :");

        JB_cancel.setText("\u2B8C");
        JB_cancel.setToolTipText("Volver");
        JB_cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JB_cancelActionPerformed(evt);
            }
        });

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/racadauto/Images/ok.jpg"))); // NOI18N
        jLabel3.setText("jLabel3");
        jLabel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        txtbuscarxnom.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        txtbuscarxnom.setForeground(new java.awt.Color(153, 153, 153));
        txtbuscarxnom.setText("Buscar por nombre");
        txtbuscarxnom.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtbuscarxnomFocusLost(evt);
            }
        });
        txtbuscarxnom.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtbuscarxnomMouseClicked(evt);
            }
        });
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
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37)
                        .addComponent(jLabel9))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addGap(13, 13, 13)
                            .addComponent(txtbuscarxnom, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jLabel1)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(LBL_estado1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(JB_cancel, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel9))
                .addGap(11, 11, 11)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtbuscarxnom, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(JB_cancel)
                        .addComponent(jButton1))
                    .addComponent(LBL_estado1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        String nom = jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString();
        LBL_estado1.setText("" + nom + "");

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
                JOptionPane.showMessageDialog(null,
                    "Item NO en Tabla", "ERROR",
                    JOptionPane.ERROR_MESSAGE);

            }
            String sql = "DELETE FROM repuestos WHERE cod_item =" + cod + " AND id_marca =" + ma + " AND id_modelo = " + mo + "";
                try {
                    sentencia.executeUpdate(sql);
                    JOptionPane.showMessageDialog(null,
                        "Propiedades Quitadas!", "Exito",
                        JOptionPane.INFORMATION_MESSAGE);

                } catch (SQLException ee) {
                    JOptionPane.showMessageDialog(null,
                        "Propiedades NO Quitadas", "ERROR",
                        JOptionPane.ERROR_MESSAGE);

                }
                LBL_estado1.setText("");
                jTable1.setRowSorter(null);
                limpiaTabla();
                setFilas();
            }else{
                JOptionPane.showMessageDialog(null,
                    "Error, Seleccione algo!", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
        }
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void JB_cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JB_cancelActionPerformed
        this.dispose();
    }//GEN-LAST:event_JB_cancelActionPerformed

    private void txtbuscarxnomFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtbuscarxnomFocusLost
        txtbuscarxnom.setFont(new java.awt.Font("Tahoma",2,11));
        txtbuscarxnom.setForeground(new java.awt.Color(153,153,153));
        txtbuscarxnom.setText("Buscar por nombre");
    }//GEN-LAST:event_txtbuscarxnomFocusLost

    private void txtbuscarxnomMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtbuscarxnomMouseClicked
        txtbuscarxnom.setText("");
        txtbuscarxnom.setFont(new java.awt.Font("Tahoma",0,11));
        txtbuscarxnom.setForeground(new java.awt.Color(0,0,0));
    }//GEN-LAST:event_txtbuscarxnomMouseClicked

    private void txtbuscarxnomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtbuscarxnomActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtbuscarxnomActionPerformed

    private void txtbuscarxnomKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbuscarxnomKeyTyped
        txtbuscarxnom.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(final KeyEvent e) {
                filtro();
            }
        });
        trsfiltro = new TableRowSorter(modeloTabla);
        jTable1.setRowSorter(trsfiltro);
        LBL_estado1.setText("");
    }//GEN-LAST:event_txtbuscarxnomKeyTyped

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
    private javax.swing.JButton JB_cancel;
    private javax.swing.JLabel LBL_estado1;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField txtbuscarxnom;
    // End of variables declaration//GEN-END:variables
}
