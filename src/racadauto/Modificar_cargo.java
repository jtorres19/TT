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

public class Modificar_cargo extends javax.swing.JFrame {

    private Statement sentencia;
    Conexion con = new Conexion();
    Connection cn = (Connection) con.getConnection();
    private String msj;
    DefaultTableModel modeloTabla;
    private TableRowSorter trsfiltro;
    String filtro = "";

    public Modificar_cargo() {

        modeloTabla = new DefaultTableModel(null, getColumnas());
        setFilas();
        initComponents();
    }

    private String[] getColumnas() {

        String columna[] = new String[]{"ID CARGO", "CARGO"};

        return columna;
    }

    private void setFilas() {
        try {
            sentencia = (com.mysql.jdbc.Statement) cn.createStatement();
            ResultSet lista = sentencia.executeQuery("SELECT * FROM cargo");
            Object datos[] = new Object[9];
            while (lista.next()) {
                for (int i = 0; i < 2; i++) {
                    datos[i] = lista.getObject(i + 1);
                }
                modeloTabla.addRow(datos);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Problemas con la base de datos, no se pudo llenar la tabla", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void filtro() {

        filtro = JT_nombre.getText().toUpperCase();
        int columna = 1;
        trsfiltro.setRowFilter(RowFilter.regexFilter(JT_nombre.getText().toUpperCase(), columna));
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

        int cont = 0;
        String nom = "";
        String nom2 = JT_nom.getText().toUpperCase().trim();

        try {
            sentencia = (com.mysql.jdbc.Statement) cn.createStatement();
            ResultSet rs = sentencia.executeQuery("SELECT nombre FROM cargo");
            while (rs.next()) {
                nom = rs.getString("nombre").trim();
                if (nom2.equals(nom)) {
                    JOptionPane.showMessageDialog(null,
                    "ERROR, NOMBRE no ha cambiado", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            cont ++;
        }
            }
        } catch (SQLException eg) {
            JOptionPane.showMessageDialog(null,
                    "Problemas con la base de datos, no se pudo obtener informacion", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
        }
        
        if (jTable1.getSelectedRow() == -1 ){
            JOptionPane.showMessageDialog(null,
                    "ERROR, no se ha seleccionado ninguna fila", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            cont++;
        }

        if (nom2.equals("")) {
            JOptionPane.showMessageDialog(null,
                    "ERROR, dejó una casilla vacía", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            cont++;
        }

        if (nom2.length() > 30) {
            JOptionPane.showMessageDialog(null,
                    "ERROR, nombre maximo 30 letras", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            cont++;
        } 

        return cont;
    }
    
    public int verificar2() {
        int yes = 0;
        int cargo = 0;
        int cargo2 = 0;
        String nom = jTable1.getValueAt(jTable1.getSelectedRow(), 1).toString().trim();
        try {
            sentencia = (com.mysql.jdbc.Statement) cn.createStatement();
            ResultSet rs = sentencia.executeQuery("SELECT id_cargo FROM cargo WHERE nombre = '" + nom + "'");
            while (rs.next()) {
                cargo = rs.getInt("id_cargo");
            }
        } catch (SQLException eg) {
            msj = "Error con su Solicitud";
        }
        try {
            sentencia = (com.mysql.jdbc.Statement) cn.createStatement();
            ResultSet rs = sentencia.executeQuery("SELECT * FROM trabajador");
            while (rs.next()) {
                cargo2 = rs.getInt("id_cargo");
                if (cargo == cargo2) {
                    yes += rs.getInt("id_cargo");
                }
            }

        } catch (SQLException t) {
            msj = "Error con su Solicitud";
        }
        
        if (yes > 0){
            JOptionPane.showMessageDialog(null,
                    "ERROR, CARGO referenciado en otras tablas no se puede eliminar", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
        }
        
        return yes;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        JB_cancel = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        BTN_Del = new javax.swing.JButton();
        JT_nombre = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        JB_OK = new javax.swing.JButton();
        JT_nom = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        JB_cancel.setText("\u2B8C");
        JB_cancel.setToolTipText("Volver");
        JB_cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JB_cancelActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel9.setText("MANTENER CARGO");

        jTable1.setModel(modeloTabla);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        BTN_Del.setText("\uD83D\uDDD1");
        BTN_Del.setToolTipText("Eliminar Cargo Seleccionado");
        BTN_Del.setPreferredSize(new java.awt.Dimension(100, 35));
        BTN_Del.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_DelActionPerformed(evt);
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

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        JB_OK.setText("\uD83D\uDDAB");
        JB_OK.setToolTipText("Modificar Cargo");
        JB_OK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JB_OKActionPerformed(evt);
            }
        });

        jLabel1.setText("Nombre :");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("MODIFICAR");
        jLabel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(JT_nom, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(JB_OK, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JB_OK)
                    .addComponent(JT_nom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addContainerGap())
        );

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/racadauto/Images/ok.jpg"))); // NOI18N
        jLabel3.setText("jLabel3");
        jLabel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel9))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 393, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(JT_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(88, 88, 88)
                        .addComponent(BTN_Del, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(74, 74, 74)
                        .addComponent(JB_cancel, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JT_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BTN_Del, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JB_cancel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void JB_OKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JB_OKActionPerformed

        if (verificar() == 0) {
            String nom = "";
            nom = jTable1.getValueAt(jTable1.getSelectedRow(), 1).toString().toUpperCase().trim();
            String nom2 = JT_nom.getText().toUpperCase().trim();
            int cargo = 0;

            try {
                sentencia = (com.mysql.jdbc.Statement) cn.createStatement();
                ResultSet rs = sentencia.executeQuery("SELECT id_cargo FROM cargo WHERE nombre = '" + nom + "'");
                while (rs.next()) {
                    cargo = rs.getInt("id_cargo");
                }

            } catch (SQLException f) {
                JOptionPane.showMessageDialog(null,
                    "Problemas con la base de datos, no se pudo obtener informacion", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            }

            String sql = "UPDATE cargo "
                    + "SET nombre ='" + nom2 + "'"
                    + "WHERE id_cargo = '" + cargo + "'";
            try {
                sentencia.executeUpdate(sql);
                JOptionPane.showMessageDialog(null,
                    "CARGO modificado exitosamente", "INFO",
                    JOptionPane.INFORMATION_MESSAGE);
                JT_nom.setText("");
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null,
                    "Problemas con la base de datos, CARGO no modificado", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            }

        } else {
            JOptionPane.showMessageDialog(null,
                    "Datos mal escritos, CARGO no modificado", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
        }

        jTable1.setRowSorter(null);
        limpiaTabla();
        setFilas();
    }//GEN-LAST:event_JB_OKActionPerformed

    private void JB_cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JB_cancelActionPerformed
        this.dispose();
    }//GEN-LAST:event_JB_cancelActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        String nom = jTable1.getValueAt(jTable1.getSelectedRow(), 1).toString().toUpperCase();
        JT_nom.setText(nom);

    }//GEN-LAST:event_jTable1MouseClicked

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
        JT_nombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(final KeyEvent e) {
                filtro();
            }
        });
        trsfiltro = new TableRowSorter(modeloTabla);
        jTable1.setRowSorter(trsfiltro);
        JT_nom.setText("");
    }//GEN-LAST:event_JT_nombreKeyTyped

    private void BTN_DelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_DelActionPerformed
        if (jTable1.getSelectedRow() == -1){
            JOptionPane.showMessageDialog(null,
                    "ERROR, No se ha seleccionado ninguna fila", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
        }
        
        String nom = jTable1.getValueAt(jTable1.getSelectedRow(), 1).toString().trim();
        
        int i = JOptionPane.showConfirmDialog(this,
                "¿Realmente desea eliminar " + nom + " de los cargos?","Confirmar Eliminación",
                JOptionPane.YES_NO_OPTION);
        
        int cargo = 0;
        try {
            sentencia = (com.mysql.jdbc.Statement) cn.createStatement();
            ResultSet rs = sentencia.executeQuery("SELECT id_cargo FROM cargo WHERE nombre = '" + nom + "'");
            while (rs.next()) {
                cargo = rs.getInt("id_cargo");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "No se pudo encontrar cargo en tabla", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
        }

        
        if (verificar2() == 0 && i== 0) {
            String sql = "DELETE FROM cargo WHERE id_cargo =" + cargo + "";
            try {
                sentencia.executeUpdate(sql);
                JOptionPane.showMessageDialog(null,
                    "Cargo Borrado!", "Exito",
                    JOptionPane.INFORMATION_MESSAGE);
            } catch (SQLException ee) {
                JOptionPane.showMessageDialog(null,
                    "No se pudo borrar cargo", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            }
        }
        
        JT_nom.setText("");
        jTable1.setRowSorter(null);
        limpiaTabla();
        setFilas();
        
    }//GEN-LAST:event_BTN_DelActionPerformed

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
            java.util.logging.Logger.getLogger(Modificar_cargo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Modificar_cargo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Modificar_cargo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Modificar_cargo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new Modificar_cargo().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BTN_Del;
    private javax.swing.JButton JB_OK;
    private javax.swing.JButton JB_cancel;
    private javax.swing.JTextField JT_nom;
    private javax.swing.JTextField JT_nombre;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
