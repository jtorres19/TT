package racadauto;

import Conexion.Conexion;
import com.mysql.jdbc.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Modificar_vehiculo extends javax.swing.JFrame {

    private Statement sentencia;
    Conexion con = new Conexion();
    java.sql.Connection cn = (java.sql.Connection) con.getConnection();
    private String msj;
    DefaultTableModel modeloTabla;

    public Modificar_vehiculo() {
        modeloTabla = new DefaultTableModel(null, getColumnas());
        setFilas();
        initComponents();
        llenarCombo();
    }

    private String[] getColumnas() {

        String columna[] = new String[]{"PATENTE", "AÑO", "KILOMETRAJE", "V.I.N.", "COLOR", "MARCA", "MODELO", "NOMBRE PROPIETARIO", "APELLIDO PATERNO", "APELLIDO MATERNO"};

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

    public void llenarCombo() {
        String nom;
        cmb_marca.removeAllItems();
        try {
            sentencia = (com.mysql.jdbc.Statement) cn.createStatement();
            ResultSet lista = sentencia.executeQuery("SELECT nombre FROM marca");
            while (lista.next()) {
                cmb_marca.addItem(lista.getString("nombre"));
            }
        } catch (SQLException ed) {
            msj = "no se pudo seleccionar";
        }
    }

    public void llenarCombo2() {
        cmb_model.removeAllItems();
        String marca = (String) cmb_marca.getSelectedItem();
        int marca2 = 0;
        try {
            sentencia = (com.mysql.jdbc.Statement) cn.createStatement();
            ResultSet rs = sentencia.executeQuery("SELECT id_marca FROM marca WHERE nombre = '" + marca + "'");
            while (rs.next()) {
                marca2 = rs.getInt("id_marca");
            }
        } catch (SQLException s) {
            msj = "Error con Marca";
        }
        try {
            sentencia = (com.mysql.jdbc.Statement) cn.createStatement();
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
        String year = txt_year.getText().trim();
        String kms = txt_kms.getText().trim();
        String vin = txt_vin.getText().toUpperCase().trim();
        String color = txt_color.getText().toUpperCase().trim();
        String patente2 = "";
        String patente = jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString();
        String marca = cmb_marca.getSelectedItem().toString();
        String modelo = cmb_model.getSelectedItem().toString();

        if (patente.equals("")
                || (year.equals(""))
                || (kms.equals(""))
                || (vin.equals(""))
                || (color.equals(""))) {
            JOptionPane.showMessageDialog(null,
                    "ERROR, dejó una casilla vacía", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            cont++;
        } else if(patente.equals(jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString())&& 
                    year.equals(jTable1.getValueAt(jTable1.getSelectedRow(), 1).toString()) &&
                    kms.equals(jTable1.getValueAt(jTable1.getSelectedRow(), 2).toString()) &&
                    vin.equals(jTable1.getValueAt(jTable1.getSelectedRow(), 3).toString()) &&
                    color.equals(jTable1.getValueAt(jTable1.getSelectedRow(), 4).toString()) &&
                    marca.equals(jTable1.getValueAt(jTable1.getSelectedRow(), 5).toString()) &&
                    modelo.equals(jTable1.getValueAt(jTable1.getSelectedRow(), 6).toString())){
            
            JOptionPane.showMessageDialog(null,
                    "ERROR, no se ha MODIFICADO nada", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            cont++;
        
        }else if (jTable1.getSelectedRow() == -1){
            JOptionPane.showMessageDialog(null,
                    "ERROR, no se ha seleccionado ningun vehiculo", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            cont++;
        }
        
        
        if (year.length() >= 5) {
            JOptionPane.showMessageDialog(null,
                    "ERROR, AÑO <10000 y >0", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            cont++;
        }

        if (kms.length() >= 8) {
            JOptionPane.showMessageDialog(null,
                    "ERROR, KILOMETRAJE <10000000 y >0", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            cont++;
        }

        if (vin.length() >= 21) {
            JOptionPane.showMessageDialog(null,
                    "ERROR, V.I.N maximo 20 caracteres", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            cont++;
        }

        if (color.length() >= 11) {
            JOptionPane.showMessageDialog(null,
                    "ERROR, COLOR maximo 10 caracteres", "ERROR",
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
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        cmb_model = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        cmb_marca = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        LBL_estado = new javax.swing.JLabel();
        LBL_rut = new javax.swing.JLabel();
        txt_kms = new javax.swing.JTextField();
        txt_year = new javax.swing.JTextField();
        txt_vin = new javax.swing.JTextField();
        txt_color = new javax.swing.JTextField();
        LBL_patente = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("MODIFICAR VEHICULO");
        setMinimumSize(new java.awt.Dimension(500, 300));

        jLabel9.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel9.setText("RACAD AUTOMOTRIZ - MODIFICAR VEHICULO");

        JB_cancel.setText("Volver");
        JB_cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JB_cancelActionPerformed(evt);
            }
        });

        jLabel1.setText("Patente :");

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

        jButton1.setText("Modificar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        cmb_marca.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmb_marcaItemStateChanged(evt);
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

        txt_kms.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_kmsKeyTyped(evt);
            }
        });

        txt_year.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_yearKeyTyped(evt);
            }
        });

        txt_vin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_vinKeyTyped(evt);
            }
        });

        txt_color.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_colorKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addGap(18, 18, 18)
                        .addComponent(LBL_estado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(JB_cancel))
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(18, 18, 18)
                                .addComponent(LBL_rut, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(64, 64, 64))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel3)
                                            .addComponent(jLabel2))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txt_year, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(txt_vin, javax.swing.GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)
                                                .addComponent(txt_kms))))
                                    .addComponent(jLabel4))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 104, Short.MAX_VALUE)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(LBL_patente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addGap(22, 22, 22))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel7)
                                            .addComponent(jLabel6))
                                        .addGap(13, 13, 13)))
                                .addGap(9, 9, 9)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cmb_model, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cmb_marca, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txt_color, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(165, 165, 165)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(LBL_rut, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txt_year, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txt_kms, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txt_vin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jLabel1))
                            .addComponent(LBL_patente, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(16, 16, 16)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txt_color, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(cmb_marca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(cmb_model, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 56, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(JB_cancel)
                        .addComponent(jButton1))
                    .addComponent(LBL_estado, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void JB_cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JB_cancelActionPerformed
        this.dispose();
    }//GEN-LAST:event_JB_cancelActionPerformed

    private void cmb_modelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmb_modelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmb_modelActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        String patente = jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString();
        LBL_patente.setText(patente);

        String anno = jTable1.getValueAt(jTable1.getSelectedRow(), 1).toString();
        txt_year.setText(anno);

        String kms = jTable1.getValueAt(jTable1.getSelectedRow(), 2).toString();
        txt_kms.setText(kms);

        String vin = jTable1.getValueAt(jTable1.getSelectedRow(), 3).toString();
        txt_vin.setText(vin);

        String color = jTable1.getValueAt(jTable1.getSelectedRow(), 4).toString();
        txt_color.setText(color);

        String nombre = jTable1.getValueAt(jTable1.getSelectedRow(), 7).toString();
        String paterno = jTable1.getValueAt(jTable1.getSelectedRow(), 8).toString();
        String materno = jTable1.getValueAt(jTable1.getSelectedRow(), 9).toString();
        String rut = "";
        try {
            sentencia = (com.mysql.jdbc.Statement) cn.createStatement();
            ResultSet rs = sentencia.executeQuery("SELECT rut_cliente "
                    + "FROM cliente "
                    + "WHERE nombre = '" + nombre + "' AND ape_paterno = '" + paterno + "' AND ape_materno = '" + materno + "'");
            while (rs.next()) {
                rut = rs.getString("rut_cliente");
            }
        } catch (Exception e) {
        }

        LBL_rut.setText(rut);

        String marca = jTable1.getValueAt(jTable1.getSelectedRow(), 5).toString();
        cmb_marca.setSelectedItem(marca);

        String modelo = jTable1.getValueAt(jTable1.getSelectedRow(), 6).toString();
        cmb_model.setSelectedItem(modelo);

    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (verificar() == 0) {
            
            String rut = LBL_rut.getText().trim();
            String patente = LBL_patente.getText().toUpperCase().trim();
            int year = Integer.parseInt(txt_year.getText().trim());
            int kms = Integer.parseInt(txt_kms.getText().trim());
            String vin = txt_vin.getText().toUpperCase().trim();
            String color = txt_color.getText().toUpperCase().trim();
            String client, marca, model;
            marca = (String) cmb_marca.getSelectedItem();
            model = (String) cmb_model.getSelectedItem();
            int client2 = 0;
            int marca2 = 0;
            int model2 = 0;
            /*try {
                sentencia=(com.mysql.jdbc.Statement)cn.createStatement();
                ResultSet rs = sentencia.executeQuery("SELECT rut_cliente FROM cliente WHERE  nombre = '" + client + "'");
                while (rs.next()) {
                    client2 = rs.getInt("rut_cliente");
                }      
            } catch (SQLException s) {
                msj = "Error con Cliente";
            }*/
            try {
                sentencia = (com.mysql.jdbc.Statement) cn.createStatement();
                ResultSet rs = sentencia.executeQuery("SELECT id_marca FROM marca WHERE nombre = '" + marca + "'");
                while (rs.next()) {
                    marca2 = rs.getInt("id_marca");
                }
            } catch (SQLException s) {
                msj = "Error con Marca";
            }
            try {
                sentencia = (com.mysql.jdbc.Statement) cn.createStatement();
                ResultSet rs = sentencia.executeQuery("SELECT id_modelo FROM modelo WHERE nombre = '" + model + "'");
                while (rs.next()) {
                    model2 = rs.getInt("id_modelo");
                }
            } catch (SQLException s) {
                msj = "Error con Modelo";
            }
            String sql = "UPDATE vehiculo "
                    + "SET año ='" + year + "',"
                    + "kms = '" + kms + "',"
                    + "vin = '" + vin + "',"
                    + "color = '" + color + "',"
                    + "id_marca = '" + marca2 + "',"
                    + "id_modelo = '" + model2 + "'"
                    + "WHERE patente = '" + patente + "'";
            try {
                sentencia.executeUpdate(sql);
                msj = "Datos Guardados";
                LBL_estado.setText(msj);
            } catch (SQLException e) {
                msj = "VEHICULO no Ingresado";
                LBL_estado.setText(msj);
            }
        } else {
            msj = "VEHICULO no Ingresado";
            LBL_estado.setText(msj);
        }

        limpiaTabla();
        setFilas();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void cmb_marcaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmb_marcaItemStateChanged
        llenarCombo2();
    }//GEN-LAST:event_cmb_marcaItemStateChanged

    private void txt_kmsKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_kmsKeyTyped
        char validar = evt.getKeyChar();

        if (!Character.isDigit(validar) && validar != evt.VK_BACK_SPACE) {
            getToolkit().beep();
            evt.consume();

            JOptionPane.showMessageDialog(null,
                    "ERROR, KILOMETROS solo puede ser numerico", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_txt_kmsKeyTyped

    private void txt_yearKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_yearKeyTyped
        char validar = evt.getKeyChar();

        if (!Character.isDigit(validar) && validar != evt.VK_BACK_SPACE) {
            getToolkit().beep();
            evt.consume();

            JOptionPane.showMessageDialog(null,
                    "ERROR, AÑO solo puede ser numerico", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_txt_yearKeyTyped

    private void txt_vinKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_vinKeyTyped
        char validar = evt.getKeyChar();

        if (!Character.isLetterOrDigit(validar) && validar != evt.VK_BACK_SPACE) {
            getToolkit().beep();
            evt.consume();

            JOptionPane.showMessageDialog(null,
                    "ERROR, V.I.N solo puede ser alfanumerico", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_txt_vinKeyTyped

    private void txt_colorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_colorKeyTyped
        char validar = evt.getKeyChar();

        if (!Character.isLetterOrDigit(validar) && validar != evt.VK_BACK_SPACE) {
            getToolkit().beep();
            evt.consume();

            JOptionPane.showMessageDialog(null,
                    "ERROR, COLOR solo puede ser alfabetico", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_txt_colorKeyTyped

    /**
     * @param args the command line arguments
     */
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
        }*/
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Modificar_vehiculo().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton JB_cancel;
    private javax.swing.JLabel LBL_estado;
    private javax.swing.JLabel LBL_patente;
    private javax.swing.JLabel LBL_rut;
    private javax.swing.JComboBox<String> cmb_marca;
    private javax.swing.JComboBox<String> cmb_model;
    private javax.swing.JButton jButton1;
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
    private javax.swing.JTextField txt_vin;
    private javax.swing.JTextField txt_year;
    // End of variables declaration//GEN-END:variables
}
