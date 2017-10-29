package racadauto;

import com.mysql.jdbc.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Modificar_item extends javax.swing.JFrame {

    private Statement sentencia;
    private Connection conexion;
    private String nomBD = "racad";
    private String usuario = "root";
    private String password = "";
    private String msj;
    DefaultTableModel modeloTabla;

    public Modificar_item() {

        conectar();
        modeloTabla = new DefaultTableModel(null, getColumnas());
        setFilas();
        initComponents();
        llenarCombo();
        llenarCombo2();

    }

    private String[] getColumnas() {

        String columna[] = new String[]{"CODIGO", "NOMBRE", "STOCK ACTUAL", "STOCK CRITICO", "VALOR COSTO", "VALOR VENTA", "ESTADO", "MEDIDA", "FAMILIA"};

        return columna;
    }

    private void setFilas() {
        try {
            sentencia = (com.mysql.jdbc.Statement) conexion.createStatement();
            ResultSet lista = sentencia.executeQuery("SELECT * FROM inventario"
            /*"SELECT i.cod_item,i.nombre,i.stock_actual,i.stock_critico,i.valor_costo,i.valor_venta,i.estado,m.nombre,f.nombre " +
                            "FROM inventario i,unidad_medida m,familia f" + 
                            "WHERE i.id_familia = f.id_familia AND m.id_medida = i.id_medida"*/);
            Object datos[] = new Object[9];
            while (lista.next()) {
                for (int i = 0; i < 9; i++) {
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

    public void clean() {
        JT_stock.setText("");
        JT_vventa.setText("");
        JT_vcosto.setText("");
        JT_nom.setText("");
    }

    public void llenarCombo() {
        cmb_med.removeAllItems();
        try {
            sentencia = (com.mysql.jdbc.Statement) conexion.createStatement();
            ResultSet lista = sentencia.executeQuery("SELECT * FROM unidad_medida");
            while (lista.next()) {
                cmb_med.addItem(lista.getString("nombre"));
            }
        } catch (SQLException ed) {
            msj = "no se pudo seleccionar";
        }
    }

    public void llenarCombo2() {
        cmb_fam.removeAllItems();
        try {
            sentencia = (com.mysql.jdbc.Statement) conexion.createStatement();
            ResultSet lista = sentencia.executeQuery("SELECT * FROM familia");
            while (lista.next()) {
                cmb_fam.addItem(lista.getString("nombre"));
            }
        } catch (SQLException ed) {
            msj = "no se pudo seleccionar";
        }
    }

    public int verificar() {

        int cont = 0;
        String nom = "";
        String nom2 = JT_nom.getText().toUpperCase();
        String stock = JT_stock.getText();
        String vcosto = JT_vcosto.getText();
        String vventa = JT_vventa.getText();
        String med = (String) cmb_med.getSelectedItem();
        String fam = (String) cmb_fam.getSelectedItem();
        
        try {
            sentencia = (com.mysql.jdbc.Statement) conexion.createStatement();
            ResultSet rs = sentencia.executeQuery("SELECT nombre FROM inventario");
            while (rs.next()) {
                nom = rs.getString("nombre");              
            }
        } catch (SQLException eg) {
            msj = "Error con su Solicitud";
        }

        if ((JT_stock.getText().equals(""))
                || (JT_vcosto.getText().equals(""))
                || (JT_nom.getText().equals(""))
                || (JT_vventa.getText().equals(""))) {
            JOptionPane.showMessageDialog(null,
                    "Error, dejó una casilla vacía", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            cont++;
        }
        
        /*if ((nom2.equals(jTable1.getValueAt(jTable1.getSelectedRow(), 1)))
                && (stock.equals(jTable1.getValueAt(jTable1.getSelectedRow(), 2)))
                && (vcosto.equals(jTable1.getValueAt(jTable1.getSelectedRow(), 3))) 
                && (vventa.equals(jTable1.getValueAt(jTable1.getSelectedRow(), 4)))
                && (med.equals(jTable1.getValueAt(jTable1.getSelectedRow(), 5)))
                && (fam.equals(jTable1.getValueAt(jTable1.getSelectedRow(), 5)))){
            JOptionPane.showMessageDialog(null,
                    "Error, No Se Ha Modificado Nada", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            cont++;
        }*/

        if (JT_nom.getText().length() > 30) {
            JOptionPane.showMessageDialog(null,
                    "Error, nombre maximo 30 letras", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            cont++;
        } else if (nom2.matches("[-+]?\\d*\\.?\\d+")) {

            JOptionPane.showMessageDialog(null,
                    "Error, nombre no tiene que ser númerico", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            cont++;
        }

        if (JT_stock.getText().length() > 2) {
            JOptionPane.showMessageDialog(null,
                    "Error, stock critico <100 y >0", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            cont++;
        } else if (!stock.matches("[-+]?\\d*\\.?\\d+")) {

            JOptionPane.showMessageDialog(null,
                    "Error, stock critico tiene que ser numerico", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            cont++;
        }

        if (JT_vcosto.getText().length() > 6) {
            JOptionPane.showMessageDialog(null,
                    "Error, valor costo <1M y >0", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            cont++;
        } else if (!vcosto.matches("[-+]?\\d*\\.?\\d+")) {
            JOptionPane.showMessageDialog(null,
                    "Error, valor costo tiene que ser numerico", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            cont++;
        }

        if (JT_vventa.getText().length() > 6) {
            JOptionPane.showMessageDialog(null,
                    "Error, valor venta maximo <1M y >0", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            cont++;
        } else if (!vventa.matches("[-+]?\\d*\\.?\\d+")) {
            JOptionPane.showMessageDialog(null,
                    "Error, valor venta tiene que ser numerico", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            cont++;
        }

        if ((Integer.parseInt(JT_vcosto.getText())) > (Integer.parseInt(JT_vventa.getText()))) {
            JOptionPane.showMessageDialog(null,
                    "Error, Valor Venta no puede ser menor a Costo", "error message",
                    JOptionPane.ERROR_MESSAGE);
            cont++;
        }

        return cont;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        JT_stock = new javax.swing.JTextField();
        JT_vcosto = new javax.swing.JTextField();
        JT_vventa = new javax.swing.JTextField();
        cmb_med = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        cmb_fam = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        JB_OK = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        JB_cancel = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        LBL_estado = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        JT_nom = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        cmb_med.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel4.setText("Valor Costo :");

        cmb_fam.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel5.setText("Valor Venta :");

        JB_OK.setText("OK");
        JB_OK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JB_OKActionPerformed(evt);
            }
        });

        jLabel6.setText("Stock Critico :");

        JB_cancel.setText("Volver");
        JB_cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JB_cancelActionPerformed(evt);
            }
        });

        jLabel7.setText("Medida :");

        jLabel9.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel9.setText("RACAD AUTOMOTRIZ - MODIFICAR ITEM");

        jLabel8.setText("Familia :");

        jLabel2.setText("Nombre :");

        jTable1.setAutoCreateRowSorter(true);
        jTable1.setModel(modeloTabla);
        jTable1.setName(""); // NOI18N
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
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 426, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(JB_OK, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(LBL_estado, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                            .addComponent(jLabel7)
                                            .addGap(18, 18, 18)
                                            .addComponent(cmb_med, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel4)
                                                .addGap(18, 18, 18)
                                                .addComponent(JT_vcosto, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel2)
                                                .addGap(18, 18, 18)
                                                .addComponent(JT_nom, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGap(18, 18, 18)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabel6)
                                            .addGap(18, 18, 18)
                                            .addComponent(JT_stock, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabel5)
                                            .addGap(18, 18, 18)
                                            .addComponent(JT_vventa, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabel8)
                                            .addGap(18, 18, 18)
                                            .addComponent(cmb_fam, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(JB_cancel))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 633, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(234, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(JT_stock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(JT_nom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 37, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(JT_vcosto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(JT_vventa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(cmb_med, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(cmb_fam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(JB_OK)
                        .addComponent(JB_cancel))
                    .addComponent(LBL_estado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void JB_OKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JB_OKActionPerformed
        
        if (verificar() == 0) {
            String nom = "";
            nom = jTable1.getValueAt(jTable1.getSelectedRow(), 1).toString();
            String fam, med;
            int dis;
            String nom2 = JT_nom.getText().toUpperCase();
            int cod = 0;
            int stock = Integer.parseInt(JT_stock.getText());
            int vcost = Integer.parseInt(JT_vcosto.getText());
            int vvent = Integer.parseInt(JT_vventa.getText());
            fam = (String) cmb_fam.getSelectedItem();
            med = (String) cmb_med.getSelectedItem();
            int fam2 = 0;
            int med2 = 0;

            try {
                sentencia = (com.mysql.jdbc.Statement) conexion.createStatement();
                ResultSet rs = sentencia.executeQuery("SELECT id_medida FROM unidad_medida WHERE  nombre = '" + med + "'");
                while (rs.next()) {
                    med2 = rs.getInt("id_medida");
                }
            } catch (SQLException s) {
                msj = "Error con Medida";
            }
            try {
                sentencia = (com.mysql.jdbc.Statement) conexion.createStatement();
                ResultSet rs = sentencia.executeQuery("SELECT id_familia FROM familia WHERE nombre = '" + fam + "'");
                while (rs.next()) {
                    fam2 = rs.getInt("id_familia");
                }
            } catch (SQLException t) {
                msj = "Error con Familia";
            }
            try {
                sentencia = (com.mysql.jdbc.Statement) conexion.createStatement();
                ResultSet rs = sentencia.executeQuery("SELECT cod_item FROM inventario WHERE nombre = '" + nom + "'");
                while (rs.next()) {
                    cod = rs.getInt("cod_item");
                }

            } catch (SQLException f) {
                msj = "Error con Codigo";
            }

            String sql = "UPDATE inventario "
                    + "SET nombre ='" + nom2 + "',"
                    + "stock_critico = '" + stock + "',"
                    + "valor_costo = '" + vcost + "',"
                    + "valor_venta = '" + vvent + "',"
                    + "id_medida = '" + med2 + "',"
                    + "id_familia = '" + fam2 + "'"
                    + "WHERE cod_item = '" + cod + "'";
            try {
                sentencia.executeUpdate(sql);
                msj = "Datos Modificados";
                LBL_estado.setText(msj);
                dis = 1;
            } catch (SQLException e) {
                msj = "Item no Modificado";
                LBL_estado.setText(msj);
                dis = 0;
            }
            if (dis == 1) {
                clean();
            }
        } else {
            msj = "Datos mal escritos";
            LBL_estado.setText(msj);
        }

        limpiaTabla();
        setFilas();
    }//GEN-LAST:event_JB_OKActionPerformed

    private void JB_cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JB_cancelActionPerformed
        this.dispose();
    }//GEN-LAST:event_JB_cancelActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        String nom = jTable1.getValueAt(jTable1.getSelectedRow(), 1).toString();
        JT_nom.setText(nom);

        String stock = jTable1.getValueAt(jTable1.getSelectedRow(), 3).toString();
        JT_stock.setText(stock);

        String vcosto = jTable1.getValueAt(jTable1.getSelectedRow(), 4).toString();
        JT_vcosto.setText(vcosto);

        String vventa = jTable1.getValueAt(jTable1.getSelectedRow(), 5).toString();
        JT_vventa.setText(vventa);
        
        int med = 0;
        med = Integer.parseInt(jTable1.getValueAt(jTable1.getSelectedRow(), 7).toString());
        med--;
        cmb_med.setSelectedIndex(med);
        
        int fam = 0;
        fam = Integer.parseInt(jTable1.getValueAt(jTable1.getSelectedRow(), 8).toString());
        fam--;
        cmb_med.setSelectedIndex(fam);


    }//GEN-LAST:event_jTable1MouseClicked

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
            java.util.logging.Logger.getLogger(Modificar_item.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Modificar_item.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Modificar_item.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Modificar_item.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Modificar_item().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton JB_OK;
    private javax.swing.JButton JB_cancel;
    private javax.swing.JTextField JT_nom;
    private javax.swing.JTextField JT_stock;
    private javax.swing.JTextField JT_vcosto;
    private javax.swing.JTextField JT_vventa;
    private javax.swing.JLabel LBL_estado;
    private javax.swing.JComboBox<String> cmb_fam;
    private javax.swing.JComboBox<String> cmb_med;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
