package racadauto;

import Conexion.Conexion;
import com.mysql.jdbc.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Modificar_modelo extends javax.swing.JFrame {

    private Statement sentencia;
    Conexion con = new Conexion();
    java.sql.Connection cn = (java.sql.Connection) con.getConnection();
    private String msj;
    DefaultTableModel modeloTabla;

    public Modificar_modelo() {
        modeloTabla = new DefaultTableModel(null, getColumnas());
        setFilas();
        initComponents();
        llenarcombos();
    }

    private String[] getColumnas() {

        String columna[] = new String[]{"ID MODELO", "NOMBRE", "MARCA", "TIPO VEHICULO", "TIPO COMBUSTIBLE", "TIPO MOTOR"};

        return columna;
    }

    private void setFilas() {
        try {
            sentencia = (com.mysql.jdbc.Statement) cn.createStatement();
            ResultSet lista = sentencia.executeQuery("SELECT o.id_modelo,o.nombre,a.nombre,v.nombre,c.nombre,m.nombre "
                    + "FROM marca a, modelo o,tipo_vehiculo v,tipo_combustible c,tipo_motor m "
                    + "WHERE a.id_marca = o.id_marca AND o.id_tipo_vehiculo = v.id_tipo_vehiculo AND o.id_tipo_combustible = c.id_tipo_combustible AND o.id_tipo_motor = m.id_tipo_motor");
            Object datos[] = new Object[6];
            while (lista.next()) {
                for (int i = 0; i < 6; i++) {
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

    public void llenarcombos() {
        cmb_tipo.removeAllItems();
        cmb_marc.removeAllItems();
        cmb_comb.removeAllItems();
        cmb_motor.removeAllItems();
        try {
            sentencia = (com.mysql.jdbc.Statement) cn.createStatement();
            ResultSet lista = sentencia.executeQuery("SELECT nombre FROM tipo_vehiculo");
            while (lista.next()) {
                cmb_tipo.addItem(lista.getString("nombre"));
            }
        } catch (SQLException ed) {
            msj = "no se pudo seleccionar";
        }
        try {
            sentencia = (com.mysql.jdbc.Statement) cn.createStatement();
            ResultSet lista = sentencia.executeQuery("SELECT nombre FROM marca");
            while (lista.next()) {
                cmb_marc.addItem(lista.getString("nombre"));
            }
        } catch (SQLException ed) {
            msj = "no se pudo seleccionar";
        }
        try {
            sentencia = (com.mysql.jdbc.Statement) cn.createStatement();
            ResultSet lista = sentencia.executeQuery("SELECT nombre FROM tipo_combustible");
            while (lista.next()) {
                cmb_comb.addItem(lista.getString("nombre"));
            }
        } catch (SQLException ed) {
            msj = "no se pudo seleccionar";
        }
        try {
            sentencia = (com.mysql.jdbc.Statement) cn.createStatement();
            ResultSet lista = sentencia.executeQuery("SELECT nombre FROM tipo_motor");
            while (lista.next()) {
                cmb_motor.addItem(lista.getString("nombre"));
            }
        } catch (SQLException ed) {
            msj = "no se pudo seleccionar";
        }
    }

    public int verificar() {

        int ver = 0;
        String nombre, tipo, combustible, marca, motor;
        nombre = txt_nom.getText().toUpperCase().trim();
        tipo = cmb_tipo.getSelectedItem().toString().trim();
        combustible = cmb_comb.getSelectedItem().toString().trim();
        marca = cmb_marc.getSelectedItem().toString().trim();
        motor = cmb_motor.getSelectedItem().toString().trim();

        if (nombre.equals("")) {
            JOptionPane.showMessageDialog(null,
                    "Error, dejó la casilla vacía", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            ver++;
        }
        if (jTable2.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(null,
                    "ERROR, No se ha seleccionado ninguna fila", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            ver++;
        } else if (nombre.equals(jTable2.getValueAt(jTable2.getSelectedRow(), 1).toString()) &&
                    marca.equals(jTable2.getValueAt(jTable2.getSelectedRow(), 2).toString()) &&
                    tipo.equals(jTable2.getValueAt(jTable2.getSelectedRow(), 3).toString()) &&
                    combustible.equals(jTable2.getValueAt(jTable2.getSelectedRow(), 4).toString()) &&
                    motor.equals(jTable2.getValueAt(jTable2.getSelectedRow(), 5).toString())) {
            JOptionPane.showMessageDialog(null,
                    "ERROR, No se ha MODIFICADO nada", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
        }

        if (nombre.length() > 15) {
            JOptionPane.showMessageDialog(null,
                    "Nombre no puede exceder 15 caracteres!", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            ver++;
        }
        return ver;
    }

    public int verificar2() {
        int ver2 = 0;
        int mod = 0;
        int mod2 = 0;
        int nom = Integer.parseInt(jTable2.getValueAt(jTable2.getSelectedRow(), 1).toString());
        try {
            sentencia = (com.mysql.jdbc.Statement) cn.createStatement();
            ResultSet rs = sentencia.executeQuery("SELECT * FROM vehiculo");
            while (rs.next()) {
                mod = rs.getInt("id_modelo");
                if (mod == nom) {
                    ver2++;
                }
            }
        } catch (SQLException y) {
            msj = "Error con Vehiculo";
            LBL_estado.setText(msj);
        }
        try {
            sentencia = (com.mysql.jdbc.Statement) cn.createStatement();
            ResultSet sr = sentencia.executeQuery("SELECT * FROM repuestos");
            while (sr.next()) {
                mod2 = sr.getInt("id_modelo");
                if (mod2 == nom) {
                    ver2++;
                }
            }
        } catch (SQLException s) {
            msj = "Error con Repuestos";
            LBL_estado.setText(msj);
        }
        return ver2;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        JB_cancel = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        cmb_tipo = new javax.swing.JComboBox<>();
        cmb_marc = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        cmb_comb = new javax.swing.JComboBox<>();
        cmb_motor = new javax.swing.JComboBox<>();
        txt_nom = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        LBL_estado = new javax.swing.JLabel();

        jTable1.setModel(modeloTabla);
        jScrollPane1.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("MODIFICAR MODELO VEHICULO");

        jLabel9.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel9.setText("RACAD AUTOMOTRIZ - MODIFICAR MODELO VEHICULO");

        JB_cancel.setText("Volver");
        JB_cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JB_cancelActionPerformed(evt);
            }
        });

        jTable2.setModel(modeloTabla);
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable2);

        jLabel4.setText("Tipo :");

        jLabel5.setText("Combustible :");

        jLabel6.setText("Motor :");

        jLabel1.setText("Marca :");

        jLabel2.setText("Nombre Modelo Vehiculo :");

        cmb_comb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmb_combActionPerformed(evt);
            }
        });

        txt_nom.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_nomKeyTyped(evt);
            }
        });

        jButton1.setText("Modificar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        LBL_estado.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(txt_nom, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel4))
                                .addGap(30, 30, 30)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cmb_tipo, 0, 126, Short.MAX_VALUE)
                                    .addComponent(cmb_motor, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(64, 64, 64)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel1)
                                .addGap(6, 6, 6)
                                .addComponent(cmb_marc, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(49, 49, 49))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addComponent(jLabel5)
                                .addGap(18, 18, 18)
                                .addComponent(cmb_comb, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jButton1)
                                        .addGap(18, 18, 18)
                                        .addComponent(LBL_estado, javax.swing.GroupLayout.PREFERRED_SIZE, 409, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(JB_cancel, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txt_nom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(cmb_marc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(jLabel5)
                        .addComponent(cmb_comb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(cmb_tipo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(cmb_motor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton1)
                            .addComponent(LBL_estado, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(JB_cancel))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void JB_cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JB_cancelActionPerformed
        this.dispose();
    }//GEN-LAST:event_JB_cancelActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (verificar() == 0) {
            String nom = txt_nom.getText().toUpperCase().trim();
            String tipo = (String) cmb_tipo.getSelectedItem();
            String comb = (String) cmb_comb.getSelectedItem();
            String marc = (String) cmb_marc.getSelectedItem();
            String motor = (String) cmb_motor.getSelectedItem();
            int id = Integer.parseInt(jTable2.getValueAt(jTable2.getSelectedRow(), 0).toString());
            int motor1 = 0;
            int tipo1 = 0;
            int comb1 = 0;
            int marc1 = 0;
            try {
                sentencia = (com.mysql.jdbc.Statement) cn.createStatement();
                ResultSet rs = sentencia.executeQuery("SELECT id_tipo_vehiculo FROM tipo_vehiculo WHERE  nombre = '" + tipo + "'");
                while (rs.next()) {
                    tipo1 = rs.getInt("id_tipo_vehiculo");
                }
            } catch (SQLException s) {
                msj = "Error con tipo";
            }
            try {
                sentencia = (com.mysql.jdbc.Statement) cn.createStatement();
                ResultSet rs = sentencia.executeQuery("SELECT id_tipo_motor FROM tipo_motor WHERE  nombre = '" + motor + "'");
                while (rs.next()) {
                    motor1 = rs.getInt("id_tipo_motor");
                }
            } catch (SQLException s) {
                msj = "Error con motor";
            }
            try {
                sentencia = (com.mysql.jdbc.Statement) cn.createStatement();
                ResultSet rs = sentencia.executeQuery("SELECT id_tipo_combustible FROM tipo_combustible WHERE  nombre = '" + comb + "'");
                while (rs.next()) {
                    comb1 = rs.getInt("id_tipo_combustible");
                }
            } catch (SQLException s) {
                msj = "Error con combustible";
            }
            try {
                sentencia = (com.mysql.jdbc.Statement) cn.createStatement();
                ResultSet rs = sentencia.executeQuery("SELECT id_marca FROM marca WHERE  nombre = '" + marc + "'");
                while (rs.next()) {
                    marc1 = rs.getInt("id_marca");
                }
            } catch (SQLException s) {
                msj = "Error con Cliente";
            }
            String sql = "UPDATE modelo "
                    + "SET id_marca ='" + marc1 + "',"
                    + "nombre = '" + nom + "',"
                    + "id_tipo_vehiculo = '" + tipo1 + "',"
                    + "id_tipo_combustible = '" + comb1 + "',"
                    + "id_tipo_motor = '" + motor1 + "',"
                    + "id_marca = '" + marc1 + "'"
                    + "WHERE id_modelo = '" + id + "'";
            try {
                sentencia.executeUpdate(sql);
                msj = "Datos Guardados";
                LBL_estado.setText(msj);
                txt_nom.setText("");
            } catch (SQLException e) {
                msj = "MODELO no Ingresado";
                LBL_estado.setText(msj);
            }
        } else {
            msj = "MODELO no Ingresado";
            LBL_estado.setText(msj);
        }

        limpiaTabla();
        setFilas();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked

        String nombre = jTable2.getValueAt(jTable2.getSelectedRow(), 1).toString().toUpperCase();
        txt_nom.setText(nombre);

        String marca = jTable2.getValueAt(jTable2.getSelectedRow(), 2).toString();
        cmb_marc.setSelectedItem(marca);

        String tipo = jTable2.getValueAt(jTable2.getSelectedRow(), 3).toString();
        cmb_tipo.setSelectedItem(tipo);

        String combustible = jTable2.getValueAt(jTable2.getSelectedRow(), 4).toString();
        cmb_comb.setSelectedItem(combustible);

        String motor = jTable2.getValueAt(jTable2.getSelectedRow(), 5).toString();
        cmb_motor.setSelectedItem(motor);

    }//GEN-LAST:event_jTable2MouseClicked

    private void cmb_combActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmb_combActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmb_combActionPerformed

    private void txt_nomKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_nomKeyTyped
        char validar = evt.getKeyChar();

        if (!Character.isLetter(validar) && !Character.isDigit(validar) && validar != evt.VK_SPACE && validar != evt.VK_BACK_SPACE) {
            getToolkit().beep();
            evt.consume();

            JOptionPane.showMessageDialog(null,
                    "ERROR, MODELO solo puede ser alfanumerico", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_txt_nomKeyTyped

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
            java.util.logging.Logger.getLogger(Modificar_modelo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Modificar_modelo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Modificar_modelo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Modificar_modelo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new Modificar_modelo().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton JB_cancel;
    private javax.swing.JLabel LBL_estado;
    private javax.swing.JComboBox<String> cmb_comb;
    private javax.swing.JComboBox<String> cmb_marc;
    private javax.swing.JComboBox<String> cmb_motor;
    private javax.swing.JComboBox<String> cmb_tipo;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField txt_nom;
    // End of variables declaration//GEN-END:variables
}
