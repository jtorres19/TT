package racadauto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Ingresar_vehiculo extends javax.swing.JFrame {

    private Statement sentencia;
    private Connection conexion;
    private String nomBD = "racad";
    private String usuario = "root";
    private String password = "";
    private String msj;
    DefaultTableModel modeloTabla;
    
    
    public Ingresar_vehiculo() {
        conectar();
        modeloTabla = new DefaultTableModel(null, getColumnas());
        setFilas();     
        initComponents();
        llenarCombo2();
    }
    
    private String[] getColumnas() {

        String columna[] = new String[]{"R.U.T. Cliente", "Nombre Cliente", "Apellido Paterno", "Apellido Materno"};

        return columna;
    }

    private void setFilas() {
        try {
            sentencia = (com.mysql.jdbc.Statement) conexion.createStatement();
            ResultSet lista = sentencia.executeQuery("SELECT rut_cliente, nombre, ape_paterno, ape_materno FROM cliente");
            Object datos[] = new Object[4];
            while (lista.next()) {
                for (int i = 0; i < 4; i++) {
                    datos[i] = lista.getObject(i + 1);
                }
                modeloTabla.addRow(datos);
            }
        } catch (Exception e) {
            msj = "No se pudo llenar tabla";
            LBL_estado.setText(msj);
        }
    }
    
    void limpiaTabla() {
        do {
            modeloTabla.getRowCount();
            modeloTabla.removeRow(0);
        } while (modeloTabla.getRowCount() != 0);
    }

    
    public void conectar() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/" + this.nomBD;
            this.conexion = (Connection) DriverManager.getConnection(url, this.usuario, this.password);
            this.sentencia = (Statement) this.conexion.createStatement();
        } catch (Exception e) {
            msj = "ERROR AL CONECTAR";
        }
    }
    
    public void clean(){
        txt_patente.setText("");
        txt_year.setText("");
        txt_kms.setText("");
        txt_vin.setText("");
        txt_color.setText("");
        txt_rut.setText("");
    }
    
    public void llenarCombo2() {
        cmb_marca.removeAllItems();
        try {
            sentencia = (com.mysql.jdbc.Statement) conexion.createStatement();
            ResultSet lista = sentencia.executeQuery("SELECT nombre FROM marca");
            while (lista.next()) {
                cmb_marca.addItem(lista.getString("nombre"));
            }
        } catch (SQLException ed) {
            msj = "no se pudo seleccionar";
        }
    }
    
    public void llenarCombo3() {
        cmb_model.removeAllItems();
        String marca = (String) cmb_marca.getSelectedItem();
        int marca2=0;
        try {
            sentencia=(com.mysql.jdbc.Statement)conexion.createStatement();
            ResultSet rs = sentencia.executeQuery("SELECT id_marca FROM marca WHERE nombre = '" + marca + "'");
            while (rs.next()) {
                marca2 = rs.getInt("id_marca");
            }
        } catch (SQLException s) {
            msj = "Error con Marca";
        }
        try {
            sentencia = (com.mysql.jdbc.Statement) conexion.createStatement();
            ResultSet lista = sentencia.executeQuery("SELECT nombre FROM modelo WHERE id_marca = " + marca2 + "");
            while (lista.next()) {
                cmb_model.addItem(lista.getString("nombre"));
            }
        } catch (SQLException ed) {
            msj = "no se pudo seleccionar";
        }
    }
    
    
    public int verificar() {
        int cont = 0;
        String patente = txt_patente.getText();
        String year = txt_year.getText();
        String kms = txt_kms.getText();
        String vin = txt_vin.getText();
        String color = txt_color.getText();
        String nom = "";
        try {
            sentencia=(com.mysql.jdbc.Statement)conexion.createStatement();
            ResultSet rs = sentencia.executeQuery("SELECT patente FROM vehiculo");
            while (rs.next()) {
                nom = rs.getString("patente");
            }
        } catch (SQLException eg) {
            msj = "Error con su Solicitud";
        }
        if ((txt_patente.getText().equals(""))
                || (txt_year.getText().equals(""))
                || (txt_kms.getText().equals(""))
                || (txt_vin.getText().equals(""))
                || (txt_color.getText().equals(""))) {
            JOptionPane.showMessageDialog(null,
                    "Error, dejó una casilla vacía", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            cont += 1;
        }
        if (txt_rut.getText().equals("")){
            JOptionPane.showMessageDialog(null,
                    "Error, faltó seleccionar cliente!", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            cont += 1;
        }
        if (txt_patente.getText().equals(nom)) {
            JOptionPane.showMessageDialog(null,
                    "Error, ya existe Patente!", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            cont++;
        } else if (txt_patente.getText().length() >= 7) {
            JOptionPane.showMessageDialog(null,
                    "Error, patente maximo 6 caracteres", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            cont++;
        }
        if (txt_year.getText().length() >= 5) {
            JOptionPane.showMessageDialog(null,
                    "Error, año <10000 y >0", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            cont++;
        } else if (!year.matches("[-+]?\\d*\\.?\\d+")) {
            JOptionPane.showMessageDialog(null,
                    "Error, año tiene que ser numerico", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            cont++;
        }
        if (txt_kms.getText().length() >= 8) {
            JOptionPane.showMessageDialog(null,
                    "Error, stock critico <10000000 y >0", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            cont++;
        } else if (!kms.matches("[-+]?\\d*\\.?\\d+")) {
            JOptionPane.showMessageDialog(null,
                    "Error, Kilometros tienen que ser numerico", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            cont++;
        }
        if (txt_vin.getText().length() >= 21) {
            JOptionPane.showMessageDialog(null,
                    "Error, vin maximo 20 caracteres", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            cont++;
        }
        if (txt_color.getText().length() >= 11) {
            JOptionPane.showMessageDialog(null,
                    "Error, color maximo 10 caracteres", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            cont++;
        } else if (color.matches("[-+]?\\d*\\.?\\d+")) {
            JOptionPane.showMessageDialog(null,
                    "Error, color no tiene que ser numerico", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            cont++;
        }
        return cont;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel9 = new javax.swing.JLabel();
        JB_cancel = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txt_patente = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txt_year = new javax.swing.JTextField();
        txt_kms = new javax.swing.JTextField();
        txt_vin = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        cmb_model = new javax.swing.JComboBox<>();
        btn_ok = new javax.swing.JButton();
        cmb_marca = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        txt_color = new javax.swing.JTextField();
        LBL_estado = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        txt_rut = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(500, 300));
        setResizable(false);

        jLabel9.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel9.setText("RACAD AUTOMOTRIZ - INGRESAR VEHICULO");

        JB_cancel.setText("Salir");
        JB_cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JB_cancelActionPerformed(evt);
            }
        });

        jLabel1.setText("Patente :");

        txt_patente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_patenteActionPerformed(evt);
            }
        });

        jLabel2.setText("Año :");

        jLabel3.setText("Kilometraje :");

        jLabel4.setText("V.I.N. :");

        jLabel5.setText("Color :");

        jLabel6.setText("Modelo :");

        jLabel7.setText("Marca :");

        cmb_model.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmb_modelActionPerformed(evt);
            }
        });

        btn_ok.setText("OK");
        btn_ok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_okActionPerformed(evt);
            }
        });

        cmb_marca.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmb_marcaItemStateChanged(evt);
            }
        });
        cmb_marca.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cmb_marcaMouseEntered(evt);
            }
        });

        jLabel8.setText("RUT Cliente :");

        jTable1.setModel(modeloTabla);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 405, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txt_patente, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txt_vin)
                                    .addComponent(txt_kms)
                                    .addComponent(txt_year, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(28, 28, 28)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(jLabel8)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txt_rut, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(cmb_marca, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(cmb_model, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(txt_color, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btn_ok, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(LBL_estado, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(JB_cancel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txt_patente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1)
                        .addComponent(jLabel8))
                    .addComponent(txt_rut, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txt_year, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(txt_color, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txt_kms, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(cmb_marca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txt_vin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(cmb_model, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn_ok)
                            .addComponent(LBL_estado, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(1, 1, 1))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JB_cancel)))
                .addContainerGap(45, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void JB_cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JB_cancelActionPerformed
        this.dispose();
    }//GEN-LAST:event_JB_cancelActionPerformed

    private void txt_patenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_patenteActionPerformed

    }//GEN-LAST:event_txt_patenteActionPerformed

    private void cmb_modelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmb_modelActionPerformed

    }//GEN-LAST:event_cmb_modelActionPerformed

    private void btn_okActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_okActionPerformed
        if (verificar() == 0) {
            int client2 = Integer.parseInt(jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString());
            String patente = txt_patente.getText().toUpperCase();
            int year = Integer.parseInt(txt_year.getText());
            int kms = Integer.parseInt(txt_kms.getText());
            String vin = txt_vin.getText().toUpperCase();
            String color = txt_color.getText().toUpperCase();
            String client, marca, model;
            marca = (String) cmb_marca.getSelectedItem();
            model = (String) cmb_model.getSelectedItem();
            int marca2 = 0;
            int model2 = 0;
            int dis = 0;
            try {
                sentencia=(com.mysql.jdbc.Statement)conexion.createStatement();
                ResultSet rs = sentencia.executeQuery("SELECT id_marca FROM marca WHERE nombre = '" + marca + "'");
                while (rs.next()) {
                    marca2 = rs.getInt("id_marca");
                }
            } catch (SQLException s) {
                msj = "Error con Marca";
            }
            try {
                sentencia=(com.mysql.jdbc.Statement)conexion.createStatement();
                ResultSet rs = sentencia.executeQuery("SELECT id_modelo FROM modelo WHERE nombre = '" + model + "'");
                while (rs.next()) {
                    model2 = rs.getInt("id_modelo");
                }
            } catch (SQLException s) {
                msj = "Error con Modelo";
            }
            String sql = "INSERT INTO vehiculo(patente, año, kms, vin, color, rut_cliente, id_marca, id_modelo) VALUES ('" + patente + "'," + year + "," + kms + ",'" + vin + "','" + color + "'," + client2 + "," + marca2 + "," + model2 + ")";
            try {
                sentencia.executeUpdate(sql);
                msj = "Datos Guardados";
                LBL_estado.setText(msj);
                dis = 1;
            } catch (SQLException e) {
                msj = "Item no Ingresado";
                LBL_estado.setText(msj);
                dis = 0;
            }
            if (dis == 1) {
                clean();
            }
        } else {
            msj = "Item no Ingresado";
            LBL_estado.setText(msj);
        }
    }//GEN-LAST:event_btn_okActionPerformed

    private void cmb_marcaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmb_marcaMouseEntered

    }//GEN-LAST:event_cmb_marcaMouseEntered

    private void cmb_marcaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmb_marcaItemStateChanged
        llenarCombo3();
    }//GEN-LAST:event_cmb_marcaItemStateChanged

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        String client = jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString();
        txt_rut.setText(client);
        
    }//GEN-LAST:event_jTable1MouseClicked

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        /*try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Ingresar_empleado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Ingresar_empleado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Ingresar_empleado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Ingresar_empleado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }/*
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Ingresar_vehiculo().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton JB_cancel;
    private javax.swing.JLabel LBL_estado;
    private javax.swing.JButton btn_ok;
    private javax.swing.JComboBox<String> cmb_marca;
    private javax.swing.JComboBox<String> cmb_model;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField txt_color;
    private javax.swing.JTextField txt_kms;
    private javax.swing.JTextField txt_patente;
    private javax.swing.JLabel txt_rut;
    private javax.swing.JTextField txt_vin;
    private javax.swing.JTextField txt_year;
    // End of variables declaration//GEN-END:variables
}
