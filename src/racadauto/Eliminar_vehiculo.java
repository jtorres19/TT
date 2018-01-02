package racadauto;

import Conexion.Conexion;
import com.mysql.jdbc.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class Eliminar_vehiculo extends javax.swing.JFrame {
    private Statement sentencia;
    Conexion con = new Conexion();
    Connection cn = (Connection) con.getConnection();
    private String msj;
    DefaultTableModel modeloTabla;
    private TableRowSorter trsfiltro;
    String filtro;

    public Eliminar_vehiculo() {
        modeloTabla = new DefaultTableModel(null, getColumnas());
        setFilas();
        initComponents();  
    }
    
    private String[] getColumnas() {

        String columna[] = new String[]{"PATENTE", "AÑO", "KILOMETRAJE", "V.I.N.", "COLOR","MARCA", "MODELO", "NOMBRE PROPIETARIO", "APELLIDO PATERNO","APELLIDO MATERNO"};

        return columna;
    }

    private void setFilas() {
        try {
            sentencia = (com.mysql.jdbc.Statement) cn.createStatement();
            ResultSet lista = sentencia.executeQuery("SELECT v.patente, v.año, v.kms, v.vin, v.color, ma.nombre, mo.nombre, c.nombre, c.ape_paterno, c.ape_materno "
                    + "FROM vehiculo v INNER JOIN cliente c ON v.rut_cliente = c.rut_cliente "
                    + "LEFT JOIN marca ma ON v.id_marca = ma.id_marca LEFT JOIN modelo mo ON v.id_modelo = mo.id_modelo");
            Object datos[] = new Object[10];
            while (lista.next()) {
                for (int i = 0; i < 10; i++) {
                    datos[i] = lista.getObject(i + 1);
                }
                modeloTabla.addRow(datos);
            }
        } catch (Exception e) {
            msj = "No se pudo llenar tabla";
        }
    }
    
    void limpiaTabla() {
        if (modeloTabla.getRowCount() > 0) {
            do {
                modeloTabla.getRowCount();
                modeloTabla.removeRow(0);
            } while (modeloTabla.getRowCount() != 0);
        }
    }
    
    public int verificar() {
        int yes = 0;
        String paten = "", paten2 = "";
        String patente = jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString().trim();
        try {
            sentencia = (com.mysql.jdbc.Statement) cn.createStatement();
            ResultSet rs = sentencia.executeQuery("SELECT patente FROM vehiculo WHERE patente = '" + patente + "'");
            while (rs.next()) {
                paten = rs.getString("patente");
            }
        } catch (SQLException eg) {
            msj = "Error con su Solicitud";
        }
        try {
            sentencia = (com.mysql.jdbc.Statement) cn.createStatement();
            ResultSet rs = sentencia.executeQuery("SELECT * FROM solicitud_servicio "
                    + "WHERE patente = '" + paten + "'");
            while (rs.next()) {
                paten2 = rs.getString("patente");
                if (paten.equals(paten2)) {
                    yes ++;
                }
            }

        } catch (SQLException t) {
            msj = "Error con su Solicitud";
        }
        
        
        if (yes > 0) {
            JOptionPane.showMessageDialog(null,
                    "ERROR, PATENTE referenciada en otras tablas no se puede eliminar", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
        }

        return yes;
    }
    
    public void filtroPatente() {

        filtro = JT_patente.getText().toUpperCase();
        int columna = 0;
        trsfiltro.setRowFilter(RowFilter.regexFilter(JT_patente.getText().toUpperCase(), columna));
    }
    
    public void filtroMarca() {

        filtro = JT_marca.getText().toUpperCase();
        int columna = 5;
        trsfiltro.setRowFilter(RowFilter.regexFilter(JT_marca.getText().toUpperCase(), columna));
    }
   
    public void filtroModelo() {

        filtro = JT_modelo.getText().toUpperCase();
        int columna = 6;
        trsfiltro.setRowFilter(RowFilter.regexFilter(JT_modelo.getText().toUpperCase(), columna));
    }
    
    public void filtroNombre() {

        filtro = JT_nombre.getText().toUpperCase();
        int columna = 7;
        trsfiltro.setRowFilter(RowFilter.regexFilter(JT_nombre.getText().toUpperCase(), columna));
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cmbCod = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        JB_cancel = new javax.swing.JButton();
        BTN_Del = new javax.swing.JButton();
        LBL_estado = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        JT_patente = new javax.swing.JTextField();
        JT_modelo = new javax.swing.JTextField();
        JT_marca = new javax.swing.JTextField();
        JT_nombre = new javax.swing.JTextField();

        cmbCod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbCodActionPerformed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("ELIMINAR VEHICULO");
        setMinimumSize(new java.awt.Dimension(420, 210));
        setResizable(false);

        jLabel9.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel9.setText("RACAD AUTOMOTRIZ - ELIMINAR VEHICULO");

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

        LBL_estado.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jTable1.setModel(modeloTabla);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        JT_patente.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        JT_patente.setForeground(new java.awt.Color(153, 153, 153));
        JT_patente.setText("Buscar por patente");
        JT_patente.setToolTipText("");
        JT_patente.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                JT_patenteFocusLost(evt);
            }
        });
        JT_patente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JT_patenteMouseClicked(evt);
            }
        });
        JT_patente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                JT_patenteKeyTyped(evt);
            }
        });

        JT_modelo.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        JT_modelo.setForeground(new java.awt.Color(153, 153, 153));
        JT_modelo.setText("Buscar por modelo");
        JT_modelo.setToolTipText("");
        JT_modelo.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        JT_modelo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                JT_modeloFocusLost(evt);
            }
        });
        JT_modelo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JT_modeloMouseClicked(evt);
            }
        });
        JT_modelo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JT_modeloActionPerformed(evt);
            }
        });
        JT_modelo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                JT_modeloKeyTyped(evt);
            }
        });

        JT_marca.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        JT_marca.setForeground(new java.awt.Color(153, 153, 153));
        JT_marca.setText("Buscar por marca");
        JT_marca.setToolTipText("");
        JT_marca.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                JT_marcaFocusLost(evt);
            }
        });
        JT_marca.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JT_marcaMouseClicked(evt);
            }
        });
        JT_marca.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                JT_marcaKeyTyped(evt);
            }
        });

        JT_nombre.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        JT_nombre.setForeground(new java.awt.Color(153, 153, 153));
        JT_nombre.setText("Buscar por nombre");
        JT_nombre.setToolTipText("");
        JT_nombre.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        JT_nombre.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                JT_nombreFocusLost(evt);
            }
        });
        JT_nombre.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JT_nombreMouseClicked(evt);
            }
        });
        JT_nombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JT_nombreActionPerformed(evt);
            }
        });
        JT_nombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                JT_nombreKeyTyped(evt);
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
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(BTN_Del, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(LBL_estado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(JB_cancel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 732, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(JT_patente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(JT_marca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JT_modelo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JT_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(81, 81, 81))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JT_patente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JT_marca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JT_modelo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JT_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(BTN_Del, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(JB_cancel, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(LBL_estado, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
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
        
        if (jTable1.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(null,
                    "ERROR, No se ha seleccionado ninguna fila", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
        }

        String pate = jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString().trim();
        String nombre = jTable1.getValueAt(jTable1.getSelectedRow(), 7).toString().trim();
        String paterno = jTable1.getValueAt(jTable1.getSelectedRow(), 8).toString().trim();
        String materno = jTable1.getValueAt(jTable1.getSelectedRow(), 9).toString().trim();

        int i = JOptionPane.showConfirmDialog(this,
                "¿Realmente desea eliminar el vehiculo patente " + pate + " del cliente " + nombre + " " + paterno + " " + materno + " ?", "Confirmar Eliminación",
                JOptionPane.YES_NO_OPTION);

        String patente = "";
        try {
            sentencia = (com.mysql.jdbc.Statement) cn.createStatement();
            ResultSet rs = sentencia.executeQuery("SELECT patente FROM vehiculo WHERE patente = '" + pate + "'");
            while (rs.next()) {
                patente = rs.getString("patente");
            }
        } catch (SQLException e) {
            msj = "Error al buscar VEHICULO en tabla?";
            LBL_estado.setText(msj);
        }

        if (verificar() == 0 && i == 0) {
            String sql = "DELETE FROM vehiculo WHERE patente = '" + patente + "'";
            try {
                sentencia.executeUpdate(sql);
                LBL_estado.setText("VEHICULO borrado con éxito");
            } catch (SQLException ee) {
                msj = "Error Al Borrar";
                LBL_estado.setText(msj);
            }
        }

        limpiaTabla();
        setFilas();                                    
            
    }//GEN-LAST:event_BTN_DelActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        
    }//GEN-LAST:event_jTable1MouseClicked

    private void JT_patenteFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_JT_patenteFocusLost
        JT_patente.setFont(new java.awt.Font("Tahoma",2,11));
        JT_patente.setForeground(new java.awt.Color(153,153,153));
        JT_patente.setText("Buscar por patente");
    }//GEN-LAST:event_JT_patenteFocusLost

    private void JT_patenteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JT_patenteMouseClicked
        JT_patente.setText("");
        JT_patente.setFont(new java.awt.Font("Tahoma",0,11));
        JT_patente.setForeground(new java.awt.Color(0,0,0));
    }//GEN-LAST:event_JT_patenteMouseClicked

    private void JT_patenteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JT_patenteKeyTyped
        JT_patente.addKeyListener(new KeyAdapter() {
            public void keyReleased(final KeyEvent e) {
                filtroPatente();
            }
        });
        trsfiltro = new TableRowSorter(modeloTabla);
        jTable1.setRowSorter(trsfiltro);
    }//GEN-LAST:event_JT_patenteKeyTyped

    private void JT_modeloFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_JT_modeloFocusLost
        JT_modelo.setFont(new java.awt.Font("Tahoma",2,11));
        JT_modelo.setForeground(new java.awt.Color(153,153,153));
        JT_modelo.setText("Buscar por modelo");
    }//GEN-LAST:event_JT_modeloFocusLost

    private void JT_modeloMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JT_modeloMouseClicked
        JT_modelo.setText("");
        JT_modelo.setFont(new java.awt.Font("Tahoma",0,11));
        JT_modelo.setForeground(new java.awt.Color(0,0,0));
    }//GEN-LAST:event_JT_modeloMouseClicked

    private void JT_modeloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JT_modeloActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JT_modeloActionPerformed

    private void JT_modeloKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JT_modeloKeyTyped
        JT_modelo.addKeyListener(new KeyAdapter() {
            public void keyReleased(final KeyEvent e) {
                filtroModelo();
            }
        });
        trsfiltro = new TableRowSorter(modeloTabla);
        jTable1.setRowSorter(trsfiltro);
    }//GEN-LAST:event_JT_modeloKeyTyped

    private void JT_marcaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_JT_marcaFocusLost
        JT_marca.setFont(new java.awt.Font("Tahoma",2,11));
        JT_marca.setForeground(new java.awt.Color(153,153,153));
        JT_marca.setText("Buscar por marca");
    }//GEN-LAST:event_JT_marcaFocusLost

    private void JT_marcaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JT_marcaMouseClicked
        JT_marca.setText("");
        JT_marca.setFont(new java.awt.Font("Tahoma",0,11));
        JT_marca.setForeground(new java.awt.Color(0,0,0));
    }//GEN-LAST:event_JT_marcaMouseClicked

    private void JT_marcaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JT_marcaKeyTyped
        JT_marca.addKeyListener(new KeyAdapter() {
            public void keyReleased(final KeyEvent e) {
                filtroMarca();
            }
        });
        trsfiltro = new TableRowSorter(modeloTabla);
        jTable1.setRowSorter(trsfiltro);
    }//GEN-LAST:event_JT_marcaKeyTyped

    private void JT_nombreFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_JT_nombreFocusLost
        JT_nombre.setFont(new java.awt.Font("Tahoma",2,11));
        JT_nombre.setForeground(new java.awt.Color(153,153,153));
        JT_nombre.setText("Buscar por nombre");
    }//GEN-LAST:event_JT_nombreFocusLost

    private void JT_nombreMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JT_nombreMouseClicked
        JT_nombre.setText("");
        JT_nombre.setFont(new java.awt.Font("Tahoma",0,11));
        JT_nombre.setForeground(new java.awt.Color(0,0,0));
    }//GEN-LAST:event_JT_nombreMouseClicked

    private void JT_nombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JT_nombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JT_nombreActionPerformed

    private void JT_nombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JT_nombreKeyTyped
        JT_nombre.addKeyListener(new KeyAdapter() {
            public void keyReleased(final KeyEvent e) {
                filtroNombre();
            }
        });
        trsfiltro = new TableRowSorter(modeloTabla);
        jTable1.setRowSorter(trsfiltro);
    }//GEN-LAST:event_JT_nombreKeyTyped

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
            java.util.logging.Logger.getLogger(Eliminar_item.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Eliminar_item.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Eliminar_item.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Eliminar_item.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Eliminar_vehiculo().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BTN_Del;
    private javax.swing.JButton JB_cancel;
    private javax.swing.JTextField JT_marca;
    private javax.swing.JTextField JT_modelo;
    private javax.swing.JTextField JT_nombre;
    private javax.swing.JTextField JT_patente;
    private javax.swing.JLabel LBL_estado;
    private javax.swing.JComboBox<String> cmbCod;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
