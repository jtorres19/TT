package racadauto;

import Conexion.Conexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Ingresar_contacto extends javax.swing.JFrame {

    private Statement sentencia;
    Conexion con = new Conexion();
    Connection cn = (Connection) con.getConnection();
    private String msj;
    DefaultTableModel modeloTabla;
    DefaultTableModel modeloTabla2;

    public Ingresar_contacto() {
        modeloTabla = new DefaultTableModel(null, getColumnas());
        modeloTabla2 = new DefaultTableModel(null, getColumnas2());
        setFilas();
        setFilas2();
        initComponents();
    }

    
    private String[] getColumnas() {

        String columna[] = new String[]{"RUT", "NOMBRE", "APELLIDO PATERNO", "APELLIDO MATERNO","TIPO CONTACTO","CONTACTO"};

        return columna;
    }

    private void setFilas() {
        try {
            sentencia = (com.mysql.jdbc.Statement) cn.createStatement();
            ResultSet lista = sentencia.executeQuery("SELECT c.rut_cliente,c.nombre,c.ape_paterno,c.ape_materno,o.tipo_contacto,o.contacto "
                    + "FROM cliente c,contacto o "
                    + "WHERE c.rut_cliente = o.rut_cliente "
                    /*+ "GROUP BY rut_cliente"*/);
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
    
    private String[] getColumnas2() {

        String columna[] = new String[]{"RUT", "NOMBRE", "APELLIDO PATERNO", "APELLIDO MATERNO","DIRECCION","CIUDAD"};

        return columna;
    }
    
    private void setFilas2() {
        try {
            sentencia = (com.mysql.jdbc.Statement) cn.createStatement();
            ResultSet lista = sentencia.executeQuery("SELECT i.rut_cliente,i.nombre,i.ape_paterno,i.ape_materno,i.direccion,c.nombre "
                    + "                                 FROM cliente i,ciudad c "
                    + "                                 WHERE i.cod_ciudad = c.cod_ciudad");
            Object datos[] = new Object[7];
            while (lista.next()) {
                for (int i = 0; i < 6; i++) {
                    datos[i] = lista.getObject(i + 1);
                }
                modeloTabla2.addRow(datos);
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
    
    void limpiaTabla2() {
        do {
            modeloTabla2.getRowCount();
            modeloTabla2.removeRow(0);
        } while (modeloTabla2.getRowCount() != 0);
    }

    public int verificar() {

        int cont = 0;
        String rut = "", contacto2 = "";
        String contacto = JT_contacto.getText().trim();
        String tipo = (String) CMB_contacto.getSelectedItem();
        
        try { 
            sentencia = (com.mysql.jdbc.Statement) cn.createStatement();
            ResultSet rs = sentencia.executeQuery("SELECT contacto FROM contacto");
            while (rs.next()) {
                contacto2 = rs.getString("contacto");
                if (contacto.equals(contacto2)) { 
                    JOptionPane.showMessageDialog(null,
                            "ERROR, Ya existe este contacto!", "ERROR",
                            JOptionPane.ERROR_MESSAGE);
                    cont++;
                }
            }
        } catch (SQLException eg) {
            msj = "Error con su Solicitud";
        }
        
        if(jTable2.getSelectedRow() == -1){ 
           JOptionPane.showMessageDialog(null, 
                   "ERROR, No se ha seleccionado ningún cliente","ERROR",
                   JOptionPane.ERROR_MESSAGE);
           cont++;
         }

        if (tipo.equals("TELEFONO")) {
            if (contacto.equals("")) {
            JOptionPane.showMessageDialog(null,
                    "ERROR, Dejó una casilla vacía", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            cont++;
            
            }else if (!contacto.matches("[-+]?\\d*\\.?\\d+")) {
                JOptionPane.showMessageDialog(null,
                        "ERROR, FONO tiene que ser numérico", "ERROR",
                        JOptionPane.ERROR_MESSAGE);
                cont++;

            } else if (contacto.length() > 30) {
                JOptionPane.showMessageDialog(null,
                        "ERROR, FONO no puede exceder los 30 numeros", "ERROR",
                        JOptionPane.ERROR_MESSAGE);
                cont++;
            }

        } else if (contacto.equals("")) {
            JOptionPane.showMessageDialog(null,
                    "ERROR, Dejó una casilla vacía", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            cont++;
        } else if (!contacto.matches("^([0-9a-zA-Z]([_.w]*[0-9a-zA-Z])*@([0-9a-zA-Z][-w]*[0-9a-zA-Z].)+([a-zA-Z]{2,9}.)+[a-zA-Z]{2,3})$")) {
            JOptionPane.showMessageDialog(null,
                    "ERROR, Formato de MAIL incorrecto", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            cont++;

        } else if (contacto.length() > 30) {
            JOptionPane.showMessageDialog(null,
                    "ERROR, MAIL no puede exceder los 30 caracteres", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            cont++;
        }

        return cont;
    }

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
        JB_OK = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        RB_principal = new javax.swing.JRadioButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        JB_cancel = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jl_rut = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        jLabel10.setText("Contacto (Correo o Numero) :");

        jTextField7.setText("jTextField7");

        jLabel1.setText("jLabel1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel8.setText("Tipo Contacto :");

        CMB_contacto.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "TELEFONO", "EMAIL" }));
        CMB_contacto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CMB_contactoActionPerformed(evt);
            }
        });

        jLabel11.setText("Contacto (Correo o Numero) :");

        JB_OK.setText("\uD83D\uDDAB");
        JB_OK.setToolTipText("Ingresar Contactos");
        JB_OK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JB_OKActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel9.setText("AGREGAR CONTACTOS");

        RB_principal.setText("Contacto principal");

        jTable1.setModel(modeloTabla);
        jScrollPane1.setViewportView(jTable1);

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/racadauto/Images/ok.jpg"))); // NOI18N
        jLabel3.setText("jLabel3");
        jLabel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        JB_cancel.setText("\u2B8C");
        JB_cancel.setToolTipText("Volver");
        JB_cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JB_cancelActionPerformed(evt);
            }
        });

        jTable2.setModel(modeloTabla2);
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable2);

        jLabel2.setText("R.U.T. Cliente :");

        jLabel5.setText("Seleccione Cliente :");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane2)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel9))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 511, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(jLabel11)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(JT_contacto, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jl_rut, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(jLabel8)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(CMB_contacto, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(41, 41, 41)
                                        .addComponent(RB_principal)
                                        .addGap(18, 18, 18)
                                        .addComponent(JB_OK, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(JB_cancel, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(10, 10, 10)
                            .addComponent(jLabel5))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(JT_contacto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jl_rut, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(CMB_contacto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(RB_principal)
                    .addComponent(JB_OK)
                    .addComponent(JB_cancel))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void CMB_contactoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CMB_contactoActionPerformed

    }//GEN-LAST:event_CMB_contactoActionPerformed

    private void JB_OKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JB_OKActionPerformed
      
        if (verificar() == 0) {

            String rut = "", tipo = "";
            rut = jTable2.getValueAt(jTable2.getSelectedRow(), 0).toString();
            int dis, num = 0, ppal = 0;
            String contacto = JT_contacto.getText().trim();
            tipo = (String) CMB_contacto.getSelectedItem().toString().toUpperCase().trim();

            if (RB_principal.isSelected() == true) {
                ppal = 1;
            }

            try {
                sentencia = (com.mysql.jdbc.Statement) cn.createStatement();
                ResultSet rs = sentencia.executeQuery("SELECT MAX(num_correlativo) as num_correlativo FROM contacto");
                while (rs.next()) {
                    num = rs.getInt("num_correlativo");

                }
                num++;
            } catch (SQLException f) {
                msj = "Error con Numero Correlativo";
            }

            String sql = "INSERT INTO contacto(rut_cliente,num_correlativo,tipo_contacto,contacto,contacto_ppal) VALUES('" + rut + "'," + num + ",'" + tipo + "','" + contacto + "'," + ppal + ")";
            try {
                sentencia.executeUpdate(sql);
                JOptionPane.showMessageDialog(null,
                    "Datos Ingresados!", "Exito",
                    JOptionPane.INFORMATION_MESSAGE);
                dis = 1;
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null,
                    "Datos NO Ingresados", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
                dis = 0;
            }
            if (dis == 1) {
                JT_contacto.setText("");
            }
        } else {
            JOptionPane.showMessageDialog(null,
                "Datos NO Ingresados", "ERROR",
                JOptionPane.ERROR_MESSAGE);

        }
        
        limpiaTabla();
        limpiaTabla2();
        setFilas();
        setFilas2();

    }//GEN-LAST:event_JB_OKActionPerformed

    private void JB_cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JB_cancelActionPerformed
        this.dispose();
    }//GEN-LAST:event_JB_cancelActionPerformed

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
        String rut = jTable2.getValueAt(jTable2.getSelectedRow(), 0).toString();
        jl_rut.setText(rut);
    }//GEN-LAST:event_jTable2MouseClicked

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
                new Ingresar_contacto().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> CMB_contacto;
    private javax.swing.JButton JB_OK;
    private javax.swing.JButton JB_cancel;
    private javax.swing.JTextField JT_contacto;
    private javax.swing.JRadioButton RB_principal;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JLabel jl_rut;
    // End of variables declaration//GEN-END:variables
}
