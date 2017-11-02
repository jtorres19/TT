
package racadauto;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Modificar_vehiculo extends javax.swing.JFrame {
    private Statement sentencia;
    private Connection conexion;
    private String nomBD="racad";
    private String usuario="root";
    private String password="";
    private String msj;
    DefaultTableModel modeloTabla;


    public Modificar_vehiculo() {
        conectar();
        modeloTabla = new DefaultTableModel(null, getColumnas());
        setFilas();
        initComponents();  
        llenarCombo();
    }
    
    public void conectar(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            String url="jdbc:mysql://localhost:3306/"+this.nomBD;
            this.conexion=(Connection)DriverManager.getConnection(url,this.usuario,this.password);
            this.sentencia=(Statement)this.conexion.createStatement();
        }
        catch(Exception e){
            msj="error al conectar";
        }
    }

    private String[] getColumnas() {

        String columna[] = new String[]{"Patente", "Año", "Kilometraje", "V.I.N.", "Color", "Nombre Dueño", "Marca", "Modelo"};

        return columna;
    }

    private void setFilas() {
        try {
            sentencia = (com.mysql.jdbc.Statement) conexion.createStatement();
            ResultSet lista = sentencia.executeQuery("SELECT * FROM vehiculo");
            Object datos[] = new Object[8];
            while (lista.next()) {
                for (int i = 0; i < 8; i++) {
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
            modeloTabla.getRowCount();
            modeloTabla.removeRow(0);
        } while (modeloTabla.getRowCount() != 0);
    }
    
    public void llenarCombo() {
        String nom;
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

    public void llenarCombo2() {
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
    
    //La patente es clave primaria y no se debería modificar, pero que pasa
    //si se equivocan al ingresarlos??
    public int verificar() {
        int cont = 0;String year = txt_year.getText();
        String kms = txt_kms.getText();
        String vin = txt_vin.getText();
        String color = txt_color.getText();
        String nom = "";
        String patente2 = jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString();
        if ((txt_patente.getText().equals(""))
                || (txt_year.getText().equals(""))
                || (txt_kms.getText().equals(""))
                || (txt_vin.getText().equals(""))
                || (txt_color.getText().equals(""))) {
            JOptionPane.showMessageDialog(null,
                    "Error, dejó una casilla vacía", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            cont ++;
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
        jButton1 = new javax.swing.JButton();
        cmb_marca = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        txt_color = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        LBL_estado = new javax.swing.JLabel();
        txt_patente = new javax.swing.JLabel();
        txt_rut = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(500, 300));
        setResizable(false);

        jLabel9.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel9.setText("RACAD AUTOMOTRIZ - MODIFICAR VEHICULO");

        JB_cancel.setText("Salir");
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

        jButton1.setText("OK");
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

        txt_color.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_colorActionPerformed(evt);
            }
        });

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
                        .addContainerGap()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42)
                        .addComponent(LBL_estado, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(JB_cancel, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(41, 41, 41)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txt_year, javax.swing.GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE)
                                            .addComponent(txt_kms)
                                            .addComponent(txt_vin)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel8)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txt_rut, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cmb_marca, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cmb_model, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txt_color, javax.swing.GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE)
                                    .addComponent(txt_patente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(35, 35, 35)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 405, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1)
                    .addComponent(jLabel8)
                    .addComponent(txt_patente, javax.swing.GroupLayout.DEFAULT_SIZE, 17, Short.MAX_VALUE)
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
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(JB_cancel)
                        .addComponent(jButton1))
                    .addComponent(LBL_estado, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void JB_cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JB_cancelActionPerformed
        this.dispose();
    }//GEN-LAST:event_JB_cancelActionPerformed

    private void cmb_modelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmb_modelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmb_modelActionPerformed

    private void txt_colorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_colorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_colorActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        String patente = jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString();
        txt_patente.setText(patente);

        String anno = jTable1.getValueAt(jTable1.getSelectedRow(), 1).toString();
        txt_year.setText(anno);

        String kms = jTable1.getValueAt(jTable1.getSelectedRow(), 2).toString();
        txt_kms.setText(kms);

        String vin = jTable1.getValueAt(jTable1.getSelectedRow(), 3).toString();
        txt_vin.setText(vin);
        
        String color = jTable1.getValueAt(jTable1.getSelectedRow(), 4).toString();
        txt_color.setText(color);
        
        
        /*int nom = 0;
        nom = Integer.parseInt(jTable1.getValueAt(jTable1.getSelectedRow(), 5).toString());
        cmb_nom.setSelectedIndex(nom);*/
        
        //ocurre un error enorme porque se cambian los index en base a las marcas
        //no se me ocurre como arreglarlo, prefiero dejarlo así por ahora, porque
        //la restricción de marca con modelo es más importante
        /*int mar = 0;
        mar = Integer.parseInt(jTable1.getValueAt(jTable1.getSelectedRow(), 6).toString());
        mar--;
        cmb_marca.setSelectedIndex(mar);
        
        int mod = 0;
        mod = Integer.parseInt(jTable1.getValueAt(jTable1.getSelectedRow(), 7).toString());
        mod--;
        cmb_model.setSelectedIndex(mod);*/
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (verificar() == 0) {
            String patente = txt_patente.getText().toUpperCase();
            int year = Integer.parseInt(txt_year.getText());
            int kms = Integer.parseInt(txt_kms.getText());
            String vin = txt_vin.getText().toUpperCase();
            String color = txt_color.getText().toUpperCase();
            String client, marca, model;
            marca = (String) cmb_marca.getSelectedItem();
            model = (String) cmb_model.getSelectedItem();
            int client2 = 0;
            int marca2 = 0;
            int model2 = 0;
            /*try {
                sentencia=(com.mysql.jdbc.Statement)conexion.createStatement();
                ResultSet rs = sentencia.executeQuery("SELECT rut_cliente FROM cliente WHERE  nombre = '" + client + "'");
                while (rs.next()) {
                    client2 = rs.getInt("rut_cliente");
                }      
            } catch (SQLException s) {
                msj = "Error con Cliente";
            }*/
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
                msj = "Item no Ingresado";
                LBL_estado.setText(msj);               
            }
        } else {
            msj = "Item no Ingresado";
            LBL_estado.setText(msj);
        }
        
        limpiaTabla();
        setFilas();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void cmb_marcaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmb_marcaItemStateChanged
        llenarCombo2();
    }//GEN-LAST:event_cmb_marcaItemStateChanged

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
    private javax.swing.JLabel txt_patente;
    private javax.swing.JLabel txt_rut;
    private javax.swing.JTextField txt_vin;
    private javax.swing.JTextField txt_year;
    // End of variables declaration//GEN-END:variables
}
