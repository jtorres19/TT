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
public class Modificar_contacto extends javax.swing.JFrame {

    private Statement sentencia;
    private Connection conexion;
    private String nomBD = "racad";
    private String usuario = "root";
    private String password = "";
    private String msj;
    DefaultTableModel modeloTabla;
    
    public Modificar_contacto() {
        conectar();
        modeloTabla = new DefaultTableModel(null, getColumnas());
        setFilas();
        initComponents();
    }

    public void conectar() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/" + this.nomBD;
            this.conexion = (Connection) DriverManager.getConnection(url, this.usuario, this.password);
            this.sentencia = (Statement) this.conexion.createStatement();
        } catch (ClassNotFoundException | SQLException e) {
            msj = "ERROR AL CONECTAR";
        }
    }
    
    private String[] getColumnas() {

        String columna[] = new String[]{"RUT", "NOMBRE", "APELLIDO PATERNO", "APELLIDO MATERNO", "NUMERO", "TIPO CONTACTO","CONTACTO","CONTACTO PRINCIPAL"};

        return columna;
    }
    
    private void setFilas() {
        try {
            sentencia = (com.mysql.jdbc.Statement) conexion.createStatement();
            ResultSet lista = sentencia.executeQuery("SELECT c.rut_cliente,c.nombre,c.ape_paterno,c.ape_materno,co.num_correlativo,co.tipo_contacto,co.contacto,co.contacto_ppal "
                                                   +    "FROM cliente c, contacto co "
                                                   +    "WHERE c.rut_cliente = co.rut_cliente group by c.rut_cliente");
            Object datos[] = new Object[8];
            while (lista.next()) {
                for (int i = 0; i < 8; i++) {
                    datos[i] = lista.getObject(i + 1);
                }
                modeloTabla.addRow(datos);
            }
        } catch (SQLException e) {
            msj = "No se pudo llenar tabla";
        }
    }
    
     void limpiaTabla() {
        do {
            modeloTabla.getRowCount();
            modeloTabla.removeRow(0);
        } while (modeloTabla.getRowCount() != 0);
    }
     
    public int verificar() {

        int cont = 0, ppal = 0, ppal2 = 0;
        String rut = "", contacto = "", contacto2 = "";
        contacto = JT_contacto.getText();
        String tipo = (String) CMB_contacto.getSelectedItem();
        if(RB_principal.isSelected() == true){
            ppal = 1;
        }
        
        try {
            sentencia = (com.mysql.jdbc.Statement) conexion.createStatement();
            ResultSet rs = sentencia.executeQuery("SELECT contacto FROM contacto");
            while (rs.next()) {
                contacto2 = rs.getString("contacto");
            }
        } catch (SQLException eg) {
            msj = "Error con su Solicitud";
        }
        
         try {
            sentencia = (com.mysql.jdbc.Statement) conexion.createStatement();
            ResultSet rs = sentencia.executeQuery("SELECT contacto_ppal FROM contacto");
            while (rs.next()) {
                ppal2 = rs.getInt("contacto_ppal");
            }
        } catch (SQLException eg) {
            msj = "Error con su Solicitud";
        }
        
        if(jTable1.getSelectedRow() == -1){ 
           JOptionPane.showMessageDialog(null, 
                   "Error, No Se Ha Seleccionado Ningún Contacto De Cliente","ERROR",
                   JOptionPane.ERROR_MESSAGE);
           cont++;
         }
        
        if ((contacto2.equals(contacto)) && (ppal == ppal2)) {
            JOptionPane.showMessageDialog(null,
            "Error, CONTACTO No Ha Cambiado", "ERROR",
            JOptionPane.ERROR_MESSAGE);
            cont ++;
        }

        if (tipo.equals("TELEFONO")) {
            if (contacto.equals("")) {
            JOptionPane.showMessageDialog(null,
                    "Error, Dejó Una Casilla Vacía", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            cont++;
            
            }else if (!contacto.matches("[-+]?\\d*\\.?\\d+")) {
                JOptionPane.showMessageDialog(null,
                        "Error, FONO Tiene Que Ser Numérico", "ERROR",
                        JOptionPane.ERROR_MESSAGE);
                cont++;

            } else if (contacto.length() > 30) {
                JOptionPane.showMessageDialog(null,
                        "Error, FONO No Puede Exceder Los 30 Numeros", "ERROR",
                        JOptionPane.ERROR_MESSAGE);
                cont++;
            }

        } else if (contacto.equals("")) {
            JOptionPane.showMessageDialog(null,
                    "Error, Dejó Una Casilla Vacía", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            cont++;
        } else if (!contacto.matches("^([0-9a-zA-Z]([_.w]*[0-9a-zA-Z])*@([0-9a-zA-Z][-w]*[0-9a-zA-Z].)+([a-zA-Z]{2,9}.)+[a-zA-Z]{2,3})$")) {
            JOptionPane.showMessageDialog(null,
                    "Error, Formato de MAIL incorrecto", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            cont++;

        } else if (contacto.length() > 30) {
            JOptionPane.showMessageDialog(null,
                    "Error, MAIL No Puede Exceder Los 30 Caracteres", "ERROR",
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

        jLabel10 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        CMB_contacto = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        JT_contacto = new javax.swing.JTextField();
        JB_cancel = new javax.swing.JButton();
        JB_OK = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        RB_principal = new javax.swing.JRadioButton();
        LBL_estado = new javax.swing.JLabel();

        jLabel10.setText("Contacto (Correo o Numero) :");

        jTextField7.setText("jTextField7");

        jLabel1.setText("jLabel1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel8.setText("Tipo Contacto :");

        CMB_contacto.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "TELEFONO", "EMAIL" }));
        CMB_contacto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CMB_contactoActionPerformed(evt);
            }
        });

        jLabel11.setText("Contacto (Correo o Numero) :");

        JB_cancel.setText("Volver");
        JB_cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JB_cancelActionPerformed(evt);
            }
        });

        JB_OK.setText("OK");
        JB_OK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JB_OKActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel9.setText("RACAD AUTOMOTRIZ - MODIFICAR CONTACTOS");

        jTable1.setModel(modeloTabla);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        RB_principal.setText("Contacto Principal");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(CMB_contacto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addGap(18, 18, 18)
                                .addComponent(JT_contacto, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(RB_principal)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(JB_OK, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(LBL_estado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(JB_cancel)))
                        .addGap(135, 135, 135))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 572, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 52, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CMB_contacto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JT_contacto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addGap(18, 18, 18)
                .addComponent(RB_principal)
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JB_OK, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(JB_cancel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(LBL_estado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void JB_cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JB_cancelActionPerformed
        this.dispose();
    }//GEN-LAST:event_JB_cancelActionPerformed

    private void CMB_contactoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CMB_contactoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CMB_contactoActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        String contacto = jTable1.getValueAt(jTable1.getSelectedRow(), 6).toString();
        JT_contacto.setText(contacto);

        String principal = jTable1.getValueAt(jTable1.getSelectedRow(), 7).toString();
        if (principal.equals("true")){
            RB_principal.setSelected(true);
        }
        
        String tipo = jTable1.getValueAt(jTable1.getSelectedRow(), 5).toString();    
        CMB_contacto.setSelectedItem(tipo);
    }//GEN-LAST:event_jTable1MouseClicked

    private void JB_OKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JB_OKActionPerformed
        if (verificar() == 0) {
            String rut = "", rut2 = "";
            rut = jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString();
            int dis,ppal = 0;
            String contacto = JT_contacto.getText();
            String tipo = (String) CMB_contacto.getSelectedItem();
            if (RB_principal.isSelected() == true) {
                ppal = 1;
            }
                        
            try {
                sentencia = (com.mysql.jdbc.Statement) conexion.createStatement();
                ResultSet rs = sentencia.executeQuery("SELECT rut_cliente FROM contacto WHERE rut_cliente = '" + rut + "'");
                while (rs.next()) {
                    rut2 = rs.getString("rut_cliente");
                }

            } catch (SQLException f) {
                msj = "Error con RUT";
            }

            String sql = "UPDATE contacto "
                    + "SET tipo_contacto ='" + tipo + "',"
                    + "contacto = '" + contacto + "',"
                    + "contacto_ppal = '" + ppal + "'"
                    + "WHERE rut_cliente = '" + rut2 + "'";
            try {
                sentencia.executeUpdate(sql);
                msj = "Datos Modificados";
                LBL_estado.setText(msj);
                dis = 1;
            } catch (SQLException e) {
                msj = "Cliente no Modificado";
                LBL_estado.setText(msj);
                dis = 0;
            }
            if (dis == 1) {
                JT_contacto.setText("");
            }
        } else {
            msj = "Datos mal escritos";
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
            java.util.logging.Logger.getLogger(Ingresar_contacto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Ingresar_contacto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Ingresar_contacto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Ingresar_contacto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Modificar_contacto().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> CMB_contacto;
    private javax.swing.JButton JB_OK;
    private javax.swing.JButton JB_cancel;
    private javax.swing.JTextField JT_contacto;
    private javax.swing.JLabel LBL_estado;
    private javax.swing.JRadioButton RB_principal;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField7;
    // End of variables declaration//GEN-END:variables
}
