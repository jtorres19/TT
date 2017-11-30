package racadauto;

import Conexion.Conexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Ingresar_modelo extends javax.swing.JFrame {

    private Statement sentencia;
    Conexion con = new Conexion();
    Connection cn = (Connection) con.getConnection();
    private String msj;
    DefaultTableModel modeloTabla;

    public Ingresar_modelo() {
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
            Object datos[] = new Object[7];
            while (lista.next()) {
                for (int i = 0; i < 6; i++) {
                    datos[i] = lista.getObject(i + 1);
                }
                modeloTabla.addRow(datos);
            }
        } catch (SQLException e) {
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
        int ver = 0, marca = 0, motor = 0, combustible = 0, tipo = 0,marc,mot,comb,tip;
        String nom = "";

        String nombre = txt_nom.getText().toUpperCase().trim();
        String marca2 = cmb_marc.getSelectedItem().toString().trim();
        String motor2 = cmb_motor.getSelectedItem().toString().trim();
        String combustible2 = cmb_comb.getSelectedItem().toString().trim();
        String tipo2 = cmb_tipo.getSelectedItem().toString().trim();

        try {
            sentencia = (com.mysql.jdbc.Statement) cn.createStatement();
            ResultSet rs = sentencia.executeQuery("SELECT id_marca FROM marca "
                    + "WHERE nombre = '" + marca2 + "'");
            while (rs.next()) {
                marca = rs.getInt("id_marca");
            }

            ResultSet es = sentencia.executeQuery("SELECT id_tipo_motor FROM tipo_motor "
                    + "WHERE nombre = '" + motor2 + "'");
            while (es.next()) {
                motor = es.getInt("id_tipo_motor");
            }

            ResultSet ss = sentencia.executeQuery("SELECT id_tipo_combustible FROM tipo_combustible "
                    + "WHERE nombre = '" + combustible2 + "'");
            while (ss.next()) {
                combustible = ss.getInt("id_tipo_combustible");
            }

            ResultSet us = sentencia.executeQuery("SELECT id_tipo_vehiculo FROM tipo_vehiculo "
                    + "WHERE nombre = '" + tipo2 + "'");
            while (us.next()) {
                tipo = us.getInt("id_tipo_vehiculo");
            }

            ResultSet ls = sentencia.executeQuery("SELECT * FROM modelo");
            while (ls.next()) {
                
                nom = ls.getString("nombre");
                marc = ls.getInt("id_marca");
                tip = ls.getInt("id_tipo_vehiculo");
                mot = ls.getInt("id_tipo_motor");
                comb = ls.getInt("id_tipo_combustible");
                
                if (nombre.equals(nom) && marca == marc && tipo == tip && motor == mot && combustible == comb) {
                    JOptionPane.showMessageDialog(null,
                            "ERROR, Ya existe este MODELO!", "ERROR",
                            JOptionPane.ERROR_MESSAGE);
                    ver++;
                }
            }
        } catch (SQLException eg) {
            msj = "Error con su Solicitud";
        }

        if (nombre.equals("")) {
            JOptionPane.showMessageDialog(null,
                    "ERROR, dejó la casilla vacía", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            ver++;
        }
        if (nombre.length() >= 16) {
            JOptionPane.showMessageDialog(null,
                    "ERROR, NOMBRE no puede exceder 15 caracteres!", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            ver++;
        }
        return ver;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        cmb_tipo = new javax.swing.JComboBox<>();
        cmb_marc = new javax.swing.JComboBox<>();
        cmb_comb = new javax.swing.JComboBox<>();
        cmb_motor = new javax.swing.JComboBox<>();
        txt_nom = new javax.swing.JTextField();
        JB_cancel = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        LBL_estado = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        jLabel7.setText("jLabel7");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("AGREGAR MODELO VEHICULO");

        jLabel9.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel9.setText("RACAD AUTOMOTRIZ - AGREGAR MODELO VEHICULO");

        txt_nom.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_nomKeyTyped(evt);
            }
        });

        JB_cancel.setText("Volver");
        JB_cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JB_cancelActionPerformed(evt);
            }
        });

        jLabel1.setText("Marca :");

        jLabel2.setText("Nombre Modelo Vehiculo :");

        jLabel4.setText("Tipo :");

        jLabel5.setText("Combustible :");

        jLabel6.setText("Motor :");

        jButton1.setText("Agregar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        LBL_estado.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jTable1.setModel(modeloTabla);
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 474, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel6)
                                        .addComponent(jLabel5))
                                    .addGap(76, 76, 76)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(cmb_motor, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(cmb_comb, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(jLabel4)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(cmb_tipo, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(jLabel2)
                                    .addGap(18, 18, 18)
                                    .addComponent(txt_nom, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(jLabel1)
                                    .addGap(18, 18, 18)
                                    .addComponent(cmb_marc, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(65, 65, 65))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton1)
                                .addGap(18, 18, 18)
                                .addComponent(LBL_estado, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(JB_cancel, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txt_nom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(cmb_marc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(cmb_comb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)
                            .addComponent(cmb_tipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(cmb_motor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(LBL_estado, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(JB_cancel)))
                .addContainerGap())
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
            int motor1 = 0;
            int tipo1 = 0;
            int comb1 = 0;
            int marc1 = 0;
            int id = 0;
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
            try {
                sentencia = (com.mysql.jdbc.Statement) cn.createStatement();
                ResultSet rs = sentencia.executeQuery("SELECT MAX(id_modelo) as id_modelo FROM modelo");
                while (rs.next()) {
                    id = rs.getInt("id_modelo");
                }
                id++;
            } catch (SQLException f) {
                msj = "Error con Codigo";
            }

            String sql = "INSERT INTO modelo (id_marca, id_modelo, nombre, id_tipo_vehiculo, id_tipo_combustible, id_tipo_motor) VALUES (" + marc1 + "," + id + ",'" + nom + "'," + tipo1 + "," + comb1 + "," + motor1 + ")";
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
            java.util.logging.Logger.getLogger(Ingresar_modelo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Ingresar_modelo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Ingresar_modelo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Ingresar_modelo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
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
                new Ingresar_modelo().setVisible(true);
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
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField txt_nom;
    // End of variables declaration//GEN-END:variables
}
