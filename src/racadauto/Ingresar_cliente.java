/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package racadauto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author falco
 */
public class Ingresar_cliente extends javax.swing.JFrame {

    private Statement sentencia;
    private Connection conexion;
    private String nomBD = "racad";
    private String usuario = "root";
    private String password = "";
    private String msj;
    DefaultTableModel modeloTabla;

    public Ingresar_cliente() {
        conectar();
        modeloTabla = new DefaultTableModel(null, getColumnas());
        setFilas();
        initComponents();
        llenarCombo();
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

    private String[] getColumnas() {

        String columna[] = new String[]{"RUT", "NOMBRE", "APELLIDO PATERNO", "APELLIDO MATERNO", "DIRECCION", "CIUDAD"};

        return columna;
    }

    private void setFilas() {
        try {
            sentencia = (com.mysql.jdbc.Statement) conexion.createStatement();
            ResultSet lista = sentencia.executeQuery("SELECT i.rut_cliente,i.nombre,i.ape_paterno,i.ape_materno,i.direccion,c.nombre "
                    + "                                 FROM cliente i,ciudad c "
                    + "                                 WHERE i.cod_ciudad = c.cod_ciudad");
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
        if (modeloTabla.getRowCount() > 0){ 
            do {
                modeloTabla.getRowCount();
                modeloTabla.removeRow(0);
            } while (modeloTabla.getRowCount() != 0);
        }
    }

    public void llenarCombo() {
        CMB_ciudad.removeAllItems();
        try {
            sentencia = (com.mysql.jdbc.Statement) conexion.createStatement();
            ResultSet lista = sentencia.executeQuery("SELECT * FROM ciudad");
            while (lista.next()) {
                CMB_ciudad.addItem(lista.getString("nombre"));
            }
        } catch (SQLException ed) {
            msj = "no se pudo seleccionar";
        }
    }

    public void clean() {

        JT_rut.setText("");
        JT_nombre.setText("");
        JT_materno.setText("");
        JT_paterno.setText("");
        JT_direccion.setText("");
        
    }

    public int verificar() {

        int cont = 0;

        String rut = JT_rut.getText();
        String nombre = JT_nombre.getText().toUpperCase();
        String paterno = JT_paterno.getText().toUpperCase();
        String materno = JT_materno.getText().toUpperCase();
        String ciudad = (String) CMB_ciudad.getSelectedItem();
        String rut2 = "";

        try {
            sentencia = (com.mysql.jdbc.Statement) conexion.createStatement();
            ResultSet rs = sentencia.executeQuery("SELECT rut_cliente FROM cliente");
            while (rs.next()) {
                rut2 = rs.getString("rut_cliente");
                if (JT_rut.getText().equals(rut2)) {
                    JOptionPane.showMessageDialog(null,
                            "Error, Ya Existe Cliente!", "ERROR",
                            JOptionPane.ERROR_MESSAGE);
                    cont++;
                }
            }
        } catch (SQLException eg) {
            msj = "Error con su Solicitud";
        }

        if ((JT_rut.getText().equals(""))
                || (JT_nombre.getText().equals(""))
                || (JT_paterno.getText().equals(""))
                || (JT_materno.getText().equals(""))
                || (JT_direccion.getText().equals(""))) {
            JOptionPane.showMessageDialog(null,
                    "Error, dejó una casilla vacía", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            cont++;
        }

        if (!(rut.matches("^([0-9]*\\d{8}[0-9k])$"))) {
            JOptionPane.showMessageDialog(null, "Error, Rut mal escrito", "ERROR", JOptionPane.ERROR_MESSAGE);
            cont++;
        } else if (JT_rut.getText().length() > 9) {
            JOptionPane.showMessageDialog(null,
                    "Error, rut maximo 9 digitos", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            cont++;
        }

        if (nombre.matches("[-+]?\\d*\\.?\\d+")) {
            JOptionPane.showMessageDialog(null, "Error, nombre no tiene que ser númerico", "ERROR", JOptionPane.ERROR_MESSAGE);
            cont++;
        } else if (JT_nombre.getText().length() > 40) {
            JOptionPane.showMessageDialog(null,
                    "Error, nombre maximo 40 letras", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            cont++;
        }

        if (JT_paterno.getText().length() > 30) {
            JOptionPane.showMessageDialog(null,
                    "Error, apellido paterno maximo 30 letras", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            cont++;
        } else if (paterno.matches("[-+]?\\d*\\.?\\d+")) {
            JOptionPane.showMessageDialog(null,
                    "Error, apellido paterno no deben ser numeros", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            cont++;
        }

        if (JT_materno.getText().length() > 30) {
            JOptionPane.showMessageDialog(null,
                    "Error, apellido materno maximo 30 letras", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            cont++;
        } else if (materno.matches("[-+]?\\d*\\.?\\d+")) {
            JOptionPane.showMessageDialog(null,
                    "Error, apellido materno no deben ser numeros", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            cont++;
        }

        return cont;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jLabel9 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        JT_rut = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        JT_nombre = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        JT_materno = new javax.swing.JTextField();
        JT_paterno = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        JT_direccion = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        CMB_ciudad = new javax.swing.JComboBox<>();
        JB_contacto = new javax.swing.JButton();
        JB_OK = new javax.swing.JButton();
        JB_cancel = new javax.swing.JButton();
        LBL_estado = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        jLabel1.setText("jLabel1");

        jCheckBox1.setText("jCheckBox1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(470, 300));
        setMinimumSize(new java.awt.Dimension(470, 300));

        jLabel9.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel9.setText("RACAD AUTOMOTRIZ - NUEVO CLIENTE");

        jLabel2.setText("RUT (Sin puntos ni guión) :");

        jLabel3.setText("Nombre :");

        jLabel4.setText("Apellido Paterno :");

        jLabel5.setText("Apellido Materno :");

        jLabel6.setText("Dirección :");

        jLabel7.setText("CIUDAD :");

        JB_contacto.setText("Agregar Contactos");
        JB_contacto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JB_contactoActionPerformed(evt);
            }
        });

        JB_OK.setText("OK");
        JB_OK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JB_OKActionPerformed(evt);
            }
        });

        JB_cancel.setText("Volver");
        JB_cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JB_cancelActionPerformed(evt);
            }
        });

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
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 369, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(JT_rut, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(JT_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel5)
                                                .addComponent(jLabel4))
                                            .addGap(10, 10, 10)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(JT_paterno, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(JT_materno, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGap(36, 36, 36)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(JB_contacto, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGroup(layout.createSequentialGroup()
                                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel7)
                                                        .addComponent(jLabel6))
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout.createSequentialGroup()
                                                            .addGap(10, 10, 10)
                                                            .addComponent(CMB_ciudad, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addComponent(JT_direccion, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                            .addComponent(JB_OK, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(LBL_estado, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(JB_cancel))
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(32, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 233, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JT_rut, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(JT_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(JT_direccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(JT_paterno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel7)
                        .addComponent(CMB_ciudad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5)
                        .addComponent(JT_materno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(JB_contacto))
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(JB_cancel, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(JB_OK)
                        .addComponent(LBL_estado, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void JB_cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JB_cancelActionPerformed
        this.dispose();
    }//GEN-LAST:event_JB_cancelActionPerformed

    private void JB_contactoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JB_contactoActionPerformed
        Ingresar_contacto t = new Ingresar_contacto();
        t.setVisible(true);
    }//GEN-LAST:event_JB_contactoActionPerformed

    private void JB_OKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JB_OKActionPerformed

        if (verificar() == 0) {
            String rut, nombre, paterno, materno, direccion, ciudad;
            int dis;

            rut = JT_rut.getText();
            nombre = JT_nombre.getText().toUpperCase();
            paterno = JT_paterno.getText().toUpperCase();
            materno = JT_materno.getText().toUpperCase();
            direccion = JT_direccion.getText().toUpperCase();
            ciudad = (String) CMB_ciudad.getSelectedItem();
            int cod = 0;

            try {
                sentencia = (com.mysql.jdbc.Statement) conexion.createStatement();
                ResultSet rs = sentencia.executeQuery("SELECT cod_ciudad FROM ciudad WHERE  nombre = '" + ciudad + "'");
                while (rs.next()) {
                    cod = rs.getInt("cod_ciudad");
                }
            } catch (SQLException s) {
                msj = "Error con Ciudad";
            }

            String sql = "INSERT INTO cliente(rut_cliente,nombre,ape_paterno,ape_materno,direccion,cod_ciudad) VALUES('" + rut + "','" + nombre + "','" + paterno + "','" + materno + "','" + direccion + "'," + cod + ")";
            try {
                sentencia.executeUpdate(sql);
                msj = "Datos Guardados";
                LBL_estado.setText(msj);
                dis = 1;
            } catch (SQLException e) {
                msj = "Cliente no Ingresado, Problema en el servidor";
                LBL_estado.setText(msj);
                dis = 0;
            }
            if (dis == 1) {
                clean();
            }
        } else {
            msj = "Cliente no Ingresado";
            LBL_estado.setText(msj);
        }

        limpiaTabla();
        setFilas();

    }//GEN-LAST:event_JB_OKActionPerformed

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
            java.util.logging.Logger.getLogger(Ingresar_cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Ingresar_cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Ingresar_cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Ingresar_cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Ingresar_cliente().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> CMB_ciudad;
    private javax.swing.JButton JB_OK;
    private javax.swing.JButton JB_cancel;
    private javax.swing.JButton JB_contacto;
    private javax.swing.JTextField JT_direccion;
    private javax.swing.JTextField JT_materno;
    private javax.swing.JTextField JT_nombre;
    private javax.swing.JTextField JT_paterno;
    private javax.swing.JTextField JT_rut;
    private javax.swing.JLabel LBL_estado;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
