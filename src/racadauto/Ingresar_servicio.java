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
public class Ingresar_servicio extends javax.swing.JFrame {

    private Statement sentencia;
    private Connection conexion;
    private String nomBD = "racad";
    private String usuario = "root";
    private String password = "";
    private String msj;
    DefaultTableModel modeloTabla;

    public Ingresar_servicio() {
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

        String columna[] = new String[]{"ID SERVICIO", "COMPONENTE", "PRECIO", "CATEGORIA"};

        return columna;
    }

    private void setFilas() {
        try {
            sentencia = (com.mysql.jdbc.Statement) conexion.createStatement();
            ResultSet lista = sentencia.executeQuery("SELECT s.id_servicio,s.componente,s.precio,c.nombre"
                    + " FROM servicio s, categoria c"
                    + " WHERE s.id_categoria = c.id_categoria");
            Object datos[] = new Object[7];
            while (lista.next()) {
                for (int i = 0; i < 4; i++) {
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

    public void clean() {
        JT_componente.setText("");
        JT_precio.setText("");
    }

    public void llenarCombo() {
        CMB_categoria.removeAllItems();
        try {
            sentencia = (com.mysql.jdbc.Statement) conexion.createStatement();
            ResultSet lista = sentencia.executeQuery("SELECT * FROM categoria");
            while (lista.next()) {
                CMB_categoria.addItem(lista.getString("nombre"));
            }
        } catch (SQLException ed) {
            msj = "no se pudo seleccionar";
        }
    }

    public int verificar() {

        int cont = 0, cat = 0, cat2 = 0;
        String componente = JT_componente.getText().toUpperCase().trim();
        String categoria = CMB_categoria.getSelectedItem().toString().trim();
        String precio = JT_precio.getText().trim();
        String comp = "";

        try {
            sentencia = (com.mysql.jdbc.Statement) conexion.createStatement();
            
            ResultSet es = sentencia.executeQuery("SELECT id_categoria FROM categoria WHERE nombre = '" + categoria + "'");
            while (es.next()) {
                cat2 = es.getInt("id_categoria");
            }
            ResultSet rs = sentencia.executeQuery("SELECT componente,id_categoria FROM servicio");
            while (rs.next()) {
                comp = rs.getString("componente").trim();
                cat = rs.getInt("id_categoria");

                if (componente.equals(comp) && cat == cat2) {
                    JOptionPane.showMessageDialog(null,
                            "ERROR, Ya existe este SERVICIO!", "ERROR",
                            JOptionPane.ERROR_MESSAGE);
                    cont++;
                }
            }
        } catch (SQLException eg) {
            msj = "Error con su Solicitud";
        }

        if ((JT_componente.getText().equals(""))
                || (JT_precio.getText().equals(""))) {
            JOptionPane.showMessageDialog(null,
                    "ERROR, Dejó una casilla vacía", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            cont++;
        }

        if (componente.length() > 30) {
            JOptionPane.showMessageDialog(null,
                    "ERROR, COMPONENTE máximo 30 letras", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            cont++;
        }

        if (precio.length() > 6) {
            JOptionPane.showMessageDialog(null,
                    "ERROR, PRECIO máximo 6 números", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            cont++;
        }
        
        if (Integer.parseInt(JT_precio.getText()) < 1){
            JOptionPane.showMessageDialog(null,
                    "Error, PRECIO debe ser mayor que 0", "ERROR",
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
        JB_cancel = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        JB_volver = new javax.swing.JButton();
        JB_OK = new javax.swing.JButton();
        LBL_estado = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        JT_componente = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        JT_precio = new javax.swing.JTextField();
        CMB_categoria = new javax.swing.JComboBox<>();

        jLabel1.setText("jLabel1");

        JB_cancel.setText("Cancelar");
        JB_cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JB_cancelActionPerformed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(300, 300));

        jLabel9.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel9.setText("RACAD AUTOMOTRIZ - INGRESAR SERVICIO");

        jLabel4.setText("Categoría: ");

        JB_volver.setText("Volver");
        JB_volver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JB_volverActionPerformed(evt);
            }
        });

        JB_OK.setText("OK");
        JB_OK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JB_OKActionPerformed(evt);
            }
        });

        jTable1.setModel(modeloTabla);
        jScrollPane1.setViewportView(jTable1);

        jLabel2.setText("Componente: ");

        JT_componente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JT_componenteActionPerformed(evt);
            }
        });
        JT_componente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                JT_componenteKeyTyped(evt);
            }
        });

        jLabel3.setText("Precio: ");

        JT_precio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                JT_precioKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 486, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(JB_OK, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(LBL_estado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(JB_volver))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(JT_componente, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(JT_precio, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel9)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(18, 18, 18)
                                .addComponent(CMB_categoria, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(133, 133, 133)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JT_precio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JT_componente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(CMB_categoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(JB_volver, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(JB_OK))
                    .addComponent(LBL_estado, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void JB_cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JB_cancelActionPerformed
        this.dispose();
    }//GEN-LAST:event_JB_cancelActionPerformed

    private void JB_volverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JB_volverActionPerformed
        this.dispose();
    }//GEN-LAST:event_JB_volverActionPerformed

    private void JB_OKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JB_OKActionPerformed
        if (verificar() == 0) {
            int dis;
            int est = 1;
            String componente = JT_componente.getText().toUpperCase().trim();
            int precio = Integer.parseInt(JT_precio.getText().trim());
            String categoria = (String) CMB_categoria.getSelectedItem();

            int categoria2 = 0;
            int servicio = 0;

            try {
                sentencia = (com.mysql.jdbc.Statement) conexion.createStatement();
                ResultSet rs = sentencia.executeQuery("SELECT MAX(id_servicio) as id_servicio FROM servicio");
                while (rs.next()) {
                    servicio = rs.getInt("id_servicio");
                }
                servicio++;
            } catch (SQLException f) {
                msj = "Error con SERVICIO";
            }

            try {
                sentencia = (com.mysql.jdbc.Statement) conexion.createStatement();
                ResultSet rs = sentencia.executeQuery("SELECT id_categoria FROM categoria WHERE nombre = '" + categoria + "'");
                while (rs.next()) {
                    categoria2 = rs.getInt("id_categoria");
                }
            } catch (SQLException f) {
                msj = "Error con CATEGORIA";
            }

            String sql = "INSERT INTO servicio(id_servicio,componente,precio,id_categoria) VALUES(" + servicio + ",'" + componente + "'," + precio + "," + categoria2 + ")";
            try {
                sentencia.executeUpdate(sql);
                msj = "Datos Guardados";
                LBL_estado.setText(msj);
                dis = 1;
            } catch (SQLException e) {
                msj = "SERVICIO no ingresado, problemas en base de datos";
                LBL_estado.setText(msj);
                dis = 0;
            }
            if (dis == 1) {
                clean();
            }
        } else {
            msj = "SERVICIO no ingresado";
            LBL_estado.setText(msj);
        }

        limpiaTabla();
        setFilas();

    }//GEN-LAST:event_JB_OKActionPerformed

    private void JT_componenteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JT_componenteKeyTyped
        char validar = evt.getKeyChar();

        if (!Character.isLetter(validar) && validar != evt.VK_SPACE && validar != evt.VK_BACK_SPACE) {
            getToolkit().beep();
            evt.consume();

            JOptionPane.showMessageDialog(null,
                    "ERROR, COMPONENTE solo pueden ser letras", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_JT_componenteKeyTyped

    private void JT_precioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JT_precioKeyTyped
        char validar = evt.getKeyChar();

        if (!Character.isDigit(validar) && validar != evt.VK_BACK_SPACE) {
            getToolkit().beep();
            evt.consume();

            JOptionPane.showMessageDialog(null,
                    "ERROR, PRECIO solo pueden ser números", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_JT_precioKeyTyped

    private void JT_componenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JT_componenteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JT_componenteActionPerformed

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
            java.util.logging.Logger.getLogger(Ingresar_servicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Ingresar_servicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Ingresar_servicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Ingresar_servicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new Ingresar_servicio().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> CMB_categoria;
    private javax.swing.JButton JB_OK;
    private javax.swing.JButton JB_cancel;
    private javax.swing.JButton JB_volver;
    private javax.swing.JTextField JT_componente;
    private javax.swing.JTextField JT_precio;
    private javax.swing.JLabel LBL_estado;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
