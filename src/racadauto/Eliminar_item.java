package racadauto;

import Conexion.Conexion;
import com.mysql.jdbc.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.spi.DirStateFactory.Result;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class Eliminar_item extends javax.swing.JFrame {

    private Statement sentencia;
    Conexion con = new Conexion();
    Connection cn = (Connection) con.getConnection();
    private String msj;
    private TableRowSorter trsfiltro;
    DefaultTableModel modeloTabla;
    String filtro;

    public Eliminar_item() {
        modeloTabla = new DefaultTableModel(null, getColumnas());
        setFilas();
        initComponents();
    }

    private String[] getColumnas() {

        String columna[] = new String[]{"Nombre", "Stock actual", "Stock crítico", "Valor costo", "Valor venta", "Medida", "Familia"};
            //"Código", 
        return columna;
    }

    private void setFilas() {
        try {
            sentencia = (com.mysql.jdbc.Statement) cn.createStatement();
            ResultSet lista = sentencia.executeQuery("SELECT i.nombre, i.stock_actual, i.stock_critico, i.valor_costo, i.valor_venta, um.nombre, f.nombre"
                    + " FROM inventario i INNER JOIN unidad_medida um ON i.id_medida = um.id_medida LEFT JOIN familia f ON i.id_familia = f.id_familia "
                    + "WHERE i.estado = 1"    //no se si poner esta condicion, si esta inactivo jamas se borraray se podra usar el espacio
            
                    /*"SELECT i.nombre, i.stock_actual, i.stock_critico, i.valor_costo, i.valor_venta, i.estado, um.nombre, f.nombre"
                    + "FROM inventario i INNER JOIN unidad_medida um ON i.id_medida = um.id_medida"
                    + "LEFT JOIN familia f ON i.id_familia = f.id_familia"*/
            //"SELECT i.*, um.nombre, f.nombre FROM inventario i 
                    //INNER JOIN unidad_medida um ON i.id_medida = um.id_medida LEFT JOIN familia f ON i.id_familia = f.id_familia "
            //"SELECT * FROM inventario"
            /*"SELECT i.cod_item,i.nombre,i.stock_actual,i.stock_critico,i.valor_costo,i.valor_venta,i.estado,m.nombre,f.nombre " +
                            "FROM inventario i,unidad_medida m,familia f" + 
                            "WHERE i.id_familia = f.id_familia AND m.id_medida = i.id_medida"*/);
            Object datos[] = new Object[7];
            while (lista.next()) {
                for (int i = 0; i < 7; i++) {
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
    
    //verifica si los items están en otras tablas
    public int verificar() {
        int yes = 0;
        int cod = 0;
        int cod2 = 0;
        String nom = jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString();
        try {
            sentencia = (com.mysql.jdbc.Statement) cn.createStatement();
            ResultSet rs = sentencia.executeQuery("SELECT cod_item FROM inventario WHERE nombre = '" + nom + "'");
            while (rs.next()) {
                cod = rs.getInt("cod_item");
            }
        } catch (SQLException eg) {
            msj = "Error con su Solicitud";
        }
        try {
            sentencia = (com.mysql.jdbc.Statement) cn.createStatement();
            ResultSet rs = sentencia.executeQuery("SELECT * FROM detalle_insumo");
            while (rs.next()) {
                cod2 = rs.getInt("cod_item");
                if (cod == cod2) {
                    yes += rs.getInt("cod_solicitud");
                }
            }
        } catch (SQLException t) {
            msj = "Error con su Solicitud";
        }
        try {
            sentencia = (com.mysql.jdbc.Statement) cn.createStatement();
            ResultSet rs = sentencia.executeQuery("SELECT * FROM detalle_solicitud");
            while (rs.next()) {
                cod2 = rs.getInt("cod_item");
                if (cod == cod2) {
                    yes += rs.getInt("cod_solicitud");
                }
            }
        } catch (SQLException t) {
            msj = "Error con su Solicitud";
        }
        try {
            sentencia = (com.mysql.jdbc.Statement) cn.createStatement();
            ResultSet rs = sentencia.executeQuery("SELECT * FROM repuestos");
            while (rs.next()) {
                cod2 = rs.getInt("cod_item");
                if (cod == cod2) {
                    yes += rs.getInt("id_marca");
                }
            }
        } catch (SQLException t) {
            msj = "Error con su Solicitud";
        }
        try {
            sentencia = (com.mysql.jdbc.Statement) cn.createStatement();
            ResultSet rs = sentencia.executeQuery("SELECT * FROM ajuste");
            while (rs.next()) {
                cod2 = rs.getInt("cod_item");
                if (cod == cod2) {
                    yes = rs.getInt("n_folio");
                }
            }
        } catch (SQLException t) {
            msj = "Error con su Solicitud";
        }
        return yes;
    }
    
    public void filtroNombre() {

        filtro = JT_nombre.getText().toUpperCase();
        int columna = 0;
        trsfiltro.setRowFilter(RowFilter.regexFilter(JT_nombre.getText().toUpperCase(), columna));
    }

    public void filtroCosto() {

        filtro = JT_vcosto.getText().toUpperCase();
        int columna = 3;
        trsfiltro.setRowFilter(RowFilter.regexFilter(JT_vcosto.getText().toUpperCase(), columna));
    }

    public void filtroVenta() {

        filtro = JT_vventa.getText().toUpperCase();
        int columna = 4;
        trsfiltro.setRowFilter(RowFilter.regexFilter(JT_vventa.getText().toUpperCase(), columna));
    }
    
    public void filtroActual() {

        filtro = JT_actual.getText();
        int columna = 1;
        trsfiltro.setRowFilter(RowFilter.regexFilter(JT_actual.getText(), columna));
        
    }
   

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel9 = new javax.swing.JLabel();
        JB_cancel = new javax.swing.JButton();
        BTN_Del = new javax.swing.JButton();
        jl_Event = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        JT_nombre = new javax.swing.JTextField();
        JT_actual = new javax.swing.JTextField();
        JT_vcosto = new javax.swing.JTextField();
        JT_vventa = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("ELIMINAR ITEM");
        setMinimumSize(new java.awt.Dimension(420, 210));
        setResizable(false);

        jLabel9.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel9.setText("RACAD AUTOMOTRIZ - ELIMINAR ITEM");

        JB_cancel.setText("Volver");
        JB_cancel.setPreferredSize(new java.awt.Dimension(100, 35));
        JB_cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JB_cancelActionPerformed(evt);
            }
        });

        BTN_Del.setText("Eliminar");
        BTN_Del.setPreferredSize(new java.awt.Dimension(100, 35));
        BTN_Del.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_DelActionPerformed(evt);
            }
        });

        jl_Event.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jTable1.setModel(modeloTabla);
        jScrollPane1.setViewportView(jTable1);

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

        JT_actual.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        JT_actual.setForeground(new java.awt.Color(153, 153, 153));
        JT_actual.setText("Buscar por stock actual");
        JT_actual.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                JT_actualFocusLost(evt);
            }
        });
        JT_actual.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JT_actualMouseClicked(evt);
            }
        });
        JT_actual.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                JT_actualKeyTyped(evt);
            }
        });

        JT_vcosto.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        JT_vcosto.setForeground(new java.awt.Color(153, 153, 153));
        JT_vcosto.setText("Buscar por valor costo");
        JT_vcosto.setToolTipText("");
        JT_vcosto.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        JT_vcosto.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                JT_vcostoFocusLost(evt);
            }
        });
        JT_vcosto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JT_vcostoMouseClicked(evt);
            }
        });
        JT_vcosto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JT_vcostoActionPerformed(evt);
            }
        });
        JT_vcosto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                JT_vcostoKeyTyped(evt);
            }
        });

        JT_vventa.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        JT_vventa.setForeground(new java.awt.Color(153, 153, 153));
        JT_vventa.setText("Buscar por valor venta");
        JT_vventa.setToolTipText("");
        JT_vventa.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        JT_vventa.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                JT_vventaFocusLost(evt);
            }
        });
        JT_vventa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JT_vventaMouseClicked(evt);
            }
        });
        JT_vventa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JT_vventaActionPerformed(evt);
            }
        });
        JT_vventa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                JT_vventaKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1)
                    .addComponent(jLabel9)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(BTN_Del, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jl_Event, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(JB_cancel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(JT_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JT_actual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JT_vcosto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JT_vventa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JT_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JT_actual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JT_vcosto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JT_vventa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(JB_cancel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(BTN_Del, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jl_Event, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void JB_cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JB_cancelActionPerformed
        this.dispose();
    }//GEN-LAST:event_JB_cancelActionPerformed

    private void BTN_DelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_DelActionPerformed
        String nom = jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString();
        
        int i =JOptionPane.showConfirmDialog(this,
                "¿Realmente Desea Eliminar " + nom + " del Inventario?","Confirmar Eliminación",
                JOptionPane.YES_NO_OPTION);
        int estado = 0;
        int cod = 0;
        try {
            sentencia = (com.mysql.jdbc.Statement) cn.createStatement();
            ResultSet rs = sentencia.executeQuery("SELECT cod_item FROM inventario WHERE nombre = '" + nom + "'");
            while (rs.next()) {
                cod = rs.getInt("cod_item");
            }
        } catch (SQLException e) {
            msj = "Error al buscar item en tabla?";
            jl_Event.setText(msj);
        }

        
        if (verificar() == 0 && i== 0) {
            String sql = "DELETE FROM inventario WHERE cod_item =" + cod + "";
            try {
                sentencia.executeUpdate(sql);
                jl_Event.setText("Item borrado con exito");
            } catch (SQLException ee) {
                msj = "Error al borrar";
                jl_Event.setText(msj);
            }
        }else{
            if(i==0){
                String sql="UPDATE inventario SET estado ="+ estado +" WHERE cod_item ="+ cod +"";
                try{
                    sentencia.executeUpdate(sql);
                    jl_Event.setText("Item marcado como Inactivo");
                }
                catch(SQLException ee){
                    msj="Error al inactivar";
                    jl_Event.setText(msj);
                }
            }
        }
        
        limpiaTabla();
        setFilas();
        
    }//GEN-LAST:event_BTN_DelActionPerformed

    private void JT_nombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JT_nombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JT_nombreActionPerformed

    private void JT_nombreMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JT_nombreMouseClicked
        JT_nombre.setText("");
        JT_nombre.setFont(new java.awt.Font("Tahoma",0,11));
        JT_nombre.setForeground(new java.awt.Color(0,0,0));
    }//GEN-LAST:event_JT_nombreMouseClicked

    private void JT_nombreFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_JT_nombreFocusLost
        JT_nombre.setFont(new java.awt.Font("Tahoma",2,11));
        JT_nombre.setForeground(new java.awt.Color(153,153,153));
        JT_nombre.setText("Buscar por nombre");
    }//GEN-LAST:event_JT_nombreFocusLost

    private void JT_nombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JT_nombreKeyTyped
        JT_nombre.addKeyListener(new KeyAdapter() {
            public void keyReleased(final KeyEvent e) {
                filtroNombre();
            }
        });
        trsfiltro = new TableRowSorter(modeloTabla);
        jTable1.setRowSorter(trsfiltro);
    }//GEN-LAST:event_JT_nombreKeyTyped

    private void JT_actualFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_JT_actualFocusLost
        JT_actual.setFont(new java.awt.Font("Tahoma",2,11));
        JT_actual.setForeground(new java.awt.Color(153,153,153));
        JT_actual.setText("Buscar por stock actual");
    }//GEN-LAST:event_JT_actualFocusLost

    private void JT_actualMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JT_actualMouseClicked
        JT_actual.setText("");
        JT_actual.setFont(new java.awt.Font("Tahoma",0,11));
        JT_actual.setForeground(new java.awt.Color(0,0,0));
    }//GEN-LAST:event_JT_actualMouseClicked

    private void JT_actualKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JT_actualKeyTyped
        JT_actual.addKeyListener(new KeyAdapter() {
            public void keyReleased(final KeyEvent e) {
                filtroActual();
            }
        });
        trsfiltro = new TableRowSorter(modeloTabla);
        jTable1.setRowSorter(trsfiltro);
    }//GEN-LAST:event_JT_actualKeyTyped

    private void JT_vcostoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_JT_vcostoFocusLost
        JT_vcosto.setFont(new java.awt.Font("Tahoma",2,11));
        JT_vcosto.setForeground(new java.awt.Color(153,153,153));
        JT_vcosto.setText("Buscar por valor costo");
    }//GEN-LAST:event_JT_vcostoFocusLost

    private void JT_vcostoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JT_vcostoMouseClicked
        JT_vcosto.setText("");
        JT_vcosto.setFont(new java.awt.Font("Tahoma",0,11));
        JT_vcosto.setForeground(new java.awt.Color(0,0,0));
    }//GEN-LAST:event_JT_vcostoMouseClicked

    private void JT_vcostoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JT_vcostoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JT_vcostoActionPerformed

    private void JT_vcostoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JT_vcostoKeyTyped
        JT_vcosto.addKeyListener(new KeyAdapter() {
            public void keyReleased(final KeyEvent e) {
                filtroCosto();
            }
        });
        trsfiltro = new TableRowSorter(modeloTabla);
        jTable1.setRowSorter(trsfiltro);
    }//GEN-LAST:event_JT_vcostoKeyTyped

    private void JT_vventaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_JT_vventaFocusLost
        JT_vventa.setFont(new java.awt.Font("Tahoma",2,11));
        JT_vventa.setForeground(new java.awt.Color(153,153,153));
        JT_vventa.setText("Buscar por valor venta");
    }//GEN-LAST:event_JT_vventaFocusLost

    private void JT_vventaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JT_vventaMouseClicked
        JT_vventa.setText("");
        JT_vventa.setFont(new java.awt.Font("Tahoma",0,11));
        JT_vventa.setForeground(new java.awt.Color(0,0,0));
    }//GEN-LAST:event_JT_vventaMouseClicked

    private void JT_vventaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JT_vventaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JT_vventaActionPerformed

    private void JT_vventaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JT_vventaKeyTyped
        JT_vventa.addKeyListener(new KeyAdapter() {
            public void keyReleased(final KeyEvent e) {
                filtroVenta();
            }
        });
        trsfiltro = new TableRowSorter(modeloTabla);
        jTable1.setRowSorter(trsfiltro);
    }//GEN-LAST:event_JT_vventaKeyTyped

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
            java.util.logging.Logger.getLogger(Eliminar_item.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Eliminar_item.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Eliminar_item.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Eliminar_item.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Eliminar_item().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BTN_Del;
    private javax.swing.JButton JB_cancel;
    private javax.swing.JTextField JT_actual;
    private javax.swing.JTextField JT_nombre;
    private javax.swing.JTextField JT_vcosto;
    private javax.swing.JTextField JT_vventa;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel jl_Event;
    // End of variables declaration//GEN-END:variables
}
