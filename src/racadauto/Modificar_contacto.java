package racadauto;

import Conexion.Conexion;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class Modificar_contacto extends javax.swing.JFrame {

    private Statement sentencia;
    Conexion con = new Conexion();
    Connection cn = (Connection) con.getConnection();
    private String msj;
    DefaultTableModel modeloTabla;
    String filtro;
    private TableRowSorter trsfiltro;


    public Modificar_contacto() {
        modeloTabla = new DefaultTableModel(null, getColumnas());
        setFilas();
        initComponents();
    }

    private String[] getColumnas() {

        String columna[] = new String[]{"RUT", "NOMBRE", "APELLIDO PATERNO", "APELLIDO MATERNO", "NUMERO", "TIPO CONTACTO", "CONTACTO", "CONTACTO PRINCIPAL"};

        return columna;
    }

    private void setFilas() {
        try {
            sentencia = (com.mysql.jdbc.Statement) cn.createStatement();
            ResultSet lista = sentencia.executeQuery("SELECT c.rut_cliente,c.nombre,c.ape_paterno,c.ape_materno,co.num_correlativo,co.tipo_contacto,co.contacto,co.contacto_ppal "
                    + "                                 FROM cliente c, contacto co "
                    + "                                 WHERE c.rut_cliente = co.rut_cliente");
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
    
    public void filtro() {

        filtro = txtbuscarxnom.getText().toUpperCase();
        int columna = 1;
        trsfiltro.setRowFilter(RowFilter.regexFilter(txtbuscarxnom.getText().toUpperCase(), columna));
    }


    void limpiaTabla() {
        do {
            modeloTabla.getRowCount();
            modeloTabla.removeRow(0);
        } while (modeloTabla.getRowCount() != 0);
    }

    public int verificar() {

        int cont = 0;
        String rut = "", contacto = "",ppal = "";
        contacto = JT_contacto.getText().trim();
        String tipo = (String) CMB_contacto.getSelectedItem();
        
        if (RB_principal.isSelected() == true){
            ppal = "true";
        }

        if (jTable1.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(null,
                    "ERROR, No se ha seleccionado ningún contacto de cliente", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            cont++;
        }else if ((tipo.equals(jTable1.getValueAt(jTable1.getSelectedRow(), 5).toString()))
                && (contacto.equals(jTable1.getValueAt(jTable1.getSelectedRow(), 6).toString()))
                && (ppal.equals(jTable1.getValueAt(jTable1.getSelectedRow(), 7).toString()))){
            JOptionPane.showMessageDialog(null,
                    "ERROR, No se ha MODIFICADO nada", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            cont++;
        }
        
        if (tipo.equals("TELEFONO")) {
            if (contacto.equals("")) {
                JOptionPane.showMessageDialog(null,
                        "ERROR, dejó una casilla vacía", "ERROR",
                        JOptionPane.ERROR_MESSAGE);
                cont++;

            } else if (!contacto.matches("[-+]?\\d*\\.?\\d+")) {
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
                    "ERROR, Dejó nna casilla vacía", "ERROR",
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
    
    public int verificar2() {
        int yes = 0;
        String rut2 = "";
        String rut= jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString();
        try {
            sentencia = (com.mysql.jdbc.Statement) cn.createStatement();
            ResultSet rs = sentencia.executeQuery("SELECT rut_cliente FROM cliente WHERE rut_cliente = '" + rut + "'");
            while (rs.next()) {
                rut = rs.getString("rut_cliente");
            }
        } catch (SQLException eg) {
            msj = "Error con su Solicitud";
        }
        try {
            sentencia = (com.mysql.jdbc.Statement) cn.createStatement();
            ResultSet rs = sentencia.executeQuery("SELECT * FROM vehiculo");
            while (rs.next()) {
                rut2 = rs.getString("rut_cliente");
                if (rut == rut2) {
                    yes += rs.getInt("patente");
                }
            }

        } catch (SQLException t) {
            msj = "No se puede Eliminar, CONTACTO referenciado en otra tabla";
        }

        return yes;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel10 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        JT_contacto = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        CMB_contacto = new javax.swing.JComboBox<>();
        RB_principal = new javax.swing.JRadioButton();
        JB_OK = new javax.swing.JButton();
        JB_cancel = new javax.swing.JButton();
        BTN_Del = new javax.swing.JButton();
        txtbuscarxnom = new javax.swing.JTextField();

        jLabel10.setText("Contacto (Correo o Numero) :");

        jTextField7.setText("jTextField7");

        jLabel1.setText("jLabel1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel9.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel9.setText("MANTENEDOR DE CONTACTOS");

        jTable1.setModel(modeloTabla);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/racadauto/Images/ok.jpg"))); // NOI18N
        jLabel3.setText("jLabel3");
        jLabel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("MODIFICAR");
        jLabel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel11.setText("Contacto (Correo o Numero) :");

        jLabel8.setText("Tipo Contacto :");

        CMB_contacto.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "TELEFONO", "EMAIL" }));
        CMB_contacto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CMB_contactoActionPerformed(evt);
            }
        });

        RB_principal.setBackground(new java.awt.Color(204, 204, 204));
        RB_principal.setText("Contacto Principal");

        JB_OK.setText("\uD83D\uDDAB");
        JB_OK.setToolTipText("Modificar Contacto");
        JB_OK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JB_OKActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addGap(28, 28, 28)
                                .addComponent(JT_contacto, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(18, 18, 18)
                                .addComponent(CMB_contacto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(43, 43, 43)
                                .addComponent(RB_principal)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(JB_OK, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JT_contacto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(CMB_contacto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(RB_principal)
                    .addComponent(JB_OK, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        JB_cancel.setText("\u2B8C");
        JB_cancel.setToolTipText("Volver");
        JB_cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JB_cancelActionPerformed(evt);
            }
        });

        BTN_Del.setText("\uD83D\uDDD1");
        BTN_Del.setToolTipText("Eliminar Contacto");
        BTN_Del.setPreferredSize(new java.awt.Dimension(100, 35));
        BTN_Del.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_DelActionPerformed(evt);
            }
        });

        txtbuscarxnom.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        txtbuscarxnom.setForeground(new java.awt.Color(153, 153, 153));
        txtbuscarxnom.setText("Buscar por nombre");
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
                        .addGap(18, 18, 18)
                        .addComponent(jLabel9))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 596, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 12, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(txtbuscarxnom, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(BTN_Del, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(87, 87, 87)
                .addComponent(JB_cancel, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46))
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel9))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtbuscarxnom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BTN_Del, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JB_cancel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void CMB_contactoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CMB_contactoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CMB_contactoActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        String contacto = jTable1.getValueAt(jTable1.getSelectedRow(), 6).toString();
        JT_contacto.setText(contacto);

        String principal = jTable1.getValueAt(jTable1.getSelectedRow(), 7).toString();
        if (principal.equals("true")) {
            RB_principal.setSelected(true);
        }else{
            RB_principal.setSelected(false);
        } 

        String tipo = jTable1.getValueAt(jTable1.getSelectedRow(), 5).toString();
        CMB_contacto.setSelectedItem(tipo);
    }//GEN-LAST:event_jTable1MouseClicked

    private void JB_OKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JB_OKActionPerformed

        if (verificar() == 0) {

            String rut = "", rut2 = "";
            rut = jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString();
            int num = Integer.parseInt(jTable1.getValueAt(jTable1.getSelectedRow(), 4).toString());
            int num2 = 0, dis, ppal = 0;
            String contacto = JT_contacto.getText().trim();
            String tipo = CMB_contacto.getSelectedItem().toString().toUpperCase().trim();

            if (RB_principal.isSelected() == true) {
                ppal = 1;
            }

            try {
                sentencia = (com.mysql.jdbc.Statement) cn.createStatement();
                ResultSet rs = sentencia.executeQuery("SELECT rut_cliente,num_correlativo "
                        + "FROM contacto "
                        + "WHERE rut_cliente = '" + rut + "' AND num_correlativo = '" + num + "'");
                while (rs.next()) {
                    rut2 = rs.getString("rut_cliente");
                    num2 = rs.getInt("num_correlativo");
                }

            } catch (SQLException f) {
                msj = "Error con Codigo";
            }

            String sql = "UPDATE contacto "
                    + "SET tipo_contacto ='" + tipo + "',"
                    + "contacto = '" + contacto + "',"
                    + "contacto_ppal = '" + ppal + "'"
                    + "WHERE rut_cliente = '" + rut2 + "' AND num_correlativo = '" + num2 + "'";
            try {
                sentencia.executeUpdate(sql);
                JOptionPane.showMessageDialog(null,
                    "Datos Modificados!", "Exito",
                    JOptionPane.INFORMATION_MESSAGE);
                dis = 1;
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null,
                    "Datos NO Modificados", "ERROR",
                    JOptionPane.ERROR_MESSAGE);

                dis = 0;
            }
            if (dis == 1) {
                JT_contacto.setText("");
            }
        } else {
            JOptionPane.showMessageDialog(null,
                "Datos mal escritos", "ERROR",
                JOptionPane.ERROR_MESSAGE);

        }

        limpiaTabla();
        setFilas();
    }//GEN-LAST:event_JB_OKActionPerformed

    private void JB_cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JB_cancelActionPerformed
        this.dispose();
    }//GEN-LAST:event_JB_cancelActionPerformed

    private void BTN_DelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_DelActionPerformed

        String rut = jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString();
        String nom = jTable1.getValueAt(jTable1.getSelectedRow(), 1).toString();
        int cod = Integer.parseInt(jTable1.getValueAt(jTable1.getSelectedRow(), 4).toString());

        int i =JOptionPane.showConfirmDialog(this,
            "¿Realmente Desea Eliminar al Contacto" + nom + " ?","Confirmar Eliminación",
            JOptionPane.YES_NO_OPTION);

        String rut2 = "";
        try {
            sentencia = (com.mysql.jdbc.Statement) cn.createStatement();
            ResultSet rs = sentencia.executeQuery("SELECT rut_cliente FROM contacto WHERE rut_cliente = '" + rut + "'");
            while (rs.next()) {
                rut2 = rs.getString("rut_cliente");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                "Cliente no en tabla", "ERROR",
                JOptionPane.ERROR_MESSAGE);

        }

        if (verificar2() == 0 && i== 0) {
            String sql = "DELETE FROM contacto WHERE rut_cliente ='" + rut + "'  AND num_correlativo = " + cod + "";
            try {
                sentencia.executeUpdate(sql);
                JOptionPane.showMessageDialog(null,
                    "Contacto Borrado!", "Exito",
                    JOptionPane.INFORMATION_MESSAGE);
            } catch (SQLException ee) {
              JOptionPane.showMessageDialog(null,
                    "Datos NO Borrados", "ERROR",
                    JOptionPane.ERROR_MESSAGE);

            }
        }

        JT_contacto.setText("");
        limpiaTabla();
        setFilas();
    }//GEN-LAST:event_BTN_DelActionPerformed

    private void txtbuscarxnomKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbuscarxnomKeyTyped
        txtbuscarxnom.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(final KeyEvent e) {
                filtro();
            }
        });
        trsfiltro = new TableRowSorter(modeloTabla);
        jTable1.setRowSorter(trsfiltro);
    }//GEN-LAST:event_txtbuscarxnomKeyTyped

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
    private javax.swing.JButton BTN_Del;
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
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField txtbuscarxnom;
    // End of variables declaration//GEN-END:variables
}
