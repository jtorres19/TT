package racadauto;

import Conexion.Conexion;
import com.mysql.jdbc.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class Eliminar_trabajador extends javax.swing.JFrame {
    private Statement sentencia;
    Conexion con = new Conexion();
    Connection cn = (Connection) con.getConnection();
    private String msj;
    DefaultTableModel modeloTabla;
    String filtro;
    private TableRowSorter trsfiltro;


    public Eliminar_trabajador() {
        modeloTabla = new DefaultTableModel(null, getColumnas());
        setFilas();
        initComponents();
        
    }
     
    
    
    
    
    private String[] getColumnas() {

        String columna[] = new String[]{"RUT", "NOMBRE", "APELLIDO PATERNO", "APELLIDO MATERNO", "CONTRASEÑA", "FONO", "MAIL", "CARGO"};

        return columna;
    }
    
    
    private void setFilas() {
        try {
            sentencia = (com.mysql.jdbc.Statement) cn.createStatement();
            ResultSet lista = sentencia.executeQuery("SELECT t.rut_trabajador,t.nombre,t.ape_paterno,t.ape_materno,t.contraseña,t.fono,t.email,c.nombre "
                    + "                                 FROM trabajador t, cargo c "
                    + "                                 WHERE t.id_cargo = c.id_cargo");
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
        if (modeloTabla.getRowCount() > 0) {
            do {
                modeloTabla.getRowCount();
                modeloTabla.removeRow(0);
            } while (modeloTabla.getRowCount() != 0);
        }
    }
      
    
    public void filtroRut() {

        filtro = JT_rut.getText();
        int columna = 0;
        trsfiltro.setRowFilter(RowFilter.regexFilter(JT_rut.getText(), columna));
        
    }
    
    public void filtroNombre() {

        filtro = JT_nombre.getText().toUpperCase();
        int columna = 1;
        trsfiltro.setRowFilter(RowFilter.regexFilter(JT_nombre.getText().toUpperCase(), columna));
    }
    
    public void filtroPaterno() {

        filtro = JT_paterno.getText().toUpperCase();
        int columna = 2;
        trsfiltro.setRowFilter(RowFilter.regexFilter(JT_paterno.getText().toUpperCase(), columna));
    }

    public void filtroMaterno() {

        filtro = JT_materno.getText().toUpperCase();
        int columna = 3;
        trsfiltro.setRowFilter(RowFilter.regexFilter(JT_materno.getText().toUpperCase(), columna));
    }
    
    
    public void filtroCargo() {

        filtro = JT_cargo.getText().toUpperCase();
        int columna = 7;
        trsfiltro.setRowFilter(RowFilter.regexFilter(JT_cargo.getText().toUpperCase(), columna));
    }
    
    
    public int verificar() {
        int yes = 0;
        String rut2 = "";
        String rut= jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString().trim();
        try {
            sentencia = (com.mysql.jdbc.Statement) cn.createStatement();
            ResultSet rs = sentencia.executeQuery("SELECT rut_trabajador FROM trabajador WHERE rut_trabajador = '" + rut + "'");
            while (rs.next()) {
                rut = rs.getString("rut_trabajador").trim();
            }
        } catch (SQLException eg) {
            msj = "Error con su Solicitud";
        }
        try {
            sentencia = (com.mysql.jdbc.Statement) cn.createStatement();
            ResultSet rs = sentencia.executeQuery("SELECT * FROM detalle_solicitud");
            while (rs.next()) {
                rut2 = rs.getString("rut_trabajador").trim();
                if (rut == rut2) {
                    yes += rs.getInt("cod_solicitud");
                }
            }

        } catch (SQLException t) {
            msj = "No se puede Eliminar, trabajador referenciado en otra tabla";
        }
        try {
            sentencia = (com.mysql.jdbc.Statement) cn.createStatement();
            ResultSet rs = sentencia.executeQuery("SELECT * FROM solicitud_servicio");
            while (rs.next()) {
                rut2 = rs.getString("rut_trabajador").trim();
                if (rut == rut2) {
                    yes += rs.getInt("cod_solicitud");
                }
            }
        } catch (SQLException t) {
            msj = "No se puede Eliminar, trabajador referenciado en otra tabla";
        }
        
        if (yes > 0){
            JOptionPane.showMessageDialog(null,
                    "ERROR, TRABAJADOR referenciado en otras tablas no se puede eliminar", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
        }
        return yes;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cmbCod = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        JB_cancel = new javax.swing.JButton();
        BTN_Del = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jl_Event = new javax.swing.JLabel();
        JT_rut = new javax.swing.JTextField();
        JT_nombre = new javax.swing.JTextField();
        JT_paterno = new javax.swing.JTextField();
        JT_materno = new javax.swing.JTextField();
        JT_cargo = new javax.swing.JTextField();

        cmbCod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbCodActionPerformed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("ELIMINAR EMPLEADO");
        setMinimumSize(new java.awt.Dimension(420, 210));
        setResizable(false);

        jLabel9.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel9.setText("RACAD AUTOMOTRIZ - ELIMINAR TRABAJADOR");

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

        jTable1.setAutoCreateRowSorter(true);
        jTable1.setModel(modeloTabla);
        jScrollPane1.setViewportView(jTable1);

        JT_rut.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        JT_rut.setForeground(new java.awt.Color(153, 153, 153));
        JT_rut.setText("Buscar por rut");
        JT_rut.setToolTipText("");
        JT_rut.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                JT_rutFocusLost(evt);
            }
        });
        JT_rut.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JT_rutMouseClicked(evt);
            }
        });
        JT_rut.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                JT_rutKeyTyped(evt);
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

        JT_paterno.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        JT_paterno.setForeground(new java.awt.Color(153, 153, 153));
        JT_paterno.setText("Buscar por paterno");
        JT_paterno.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                JT_paternoFocusLost(evt);
            }
        });
        JT_paterno.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JT_paternoMouseClicked(evt);
            }
        });
        JT_paterno.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                JT_paternoKeyTyped(evt);
            }
        });

        JT_materno.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        JT_materno.setForeground(new java.awt.Color(153, 153, 153));
        JT_materno.setText("Buscar por materno");
        JT_materno.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                JT_maternoFocusLost(evt);
            }
        });
        JT_materno.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JT_maternoMouseClicked(evt);
            }
        });
        JT_materno.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                JT_maternoKeyTyped(evt);
            }
        });

        JT_cargo.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        JT_cargo.setForeground(new java.awt.Color(153, 153, 153));
        JT_cargo.setText("Buscar por cargo");
        JT_cargo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                JT_cargoFocusLost(evt);
            }
        });
        JT_cargo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JT_cargoMouseClicked(evt);
            }
        });
        JT_cargo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                JT_cargoKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(BTN_Del, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(56, 56, 56)
                                .addComponent(jl_Event, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(JB_cancel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(JT_rut, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(JT_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(JT_paterno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(JT_materno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 266, Short.MAX_VALUE)
                        .addComponent(JT_cargo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(JT_rut, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(JT_paterno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(JT_materno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(JT_cargo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(JT_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE, false)
                    .addComponent(JB_cancel, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jl_Event, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                    .addComponent(BTN_Del, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void cmbCodActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbCodActionPerformed
        //copia fallida
    }//GEN-LAST:event_cmbCodActionPerformed

    private void JB_cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JB_cancelActionPerformed
        this.dispose();
    }//GEN-LAST:event_JB_cancelActionPerformed

    private void BTN_DelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_DelActionPerformed
        if (jTable1.getSelectedRow() == -1){
            JOptionPane.showMessageDialog(null,
                    "ERROR, No se ha seleccionado ninguna fila", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
        }
        
        String rut = jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString().trim();
        String nom = jTable1.getValueAt(jTable1.getSelectedRow(), 1).toString().trim();
        
        int i =JOptionPane.showConfirmDialog(this,
                "¿Realmente Desea Eliminar al Trabajador" + nom + " ?","Confirmar Eliminación",
                JOptionPane.YES_NO_OPTION);
        
        String rut2 = "";
        try {
            sentencia = (com.mysql.jdbc.Statement) cn.createStatement();
            ResultSet rs = sentencia.executeQuery("SELECT rut_trabajador FROM trabajador WHERE rut_trabajador = '" + rut + "'");
            while (rs.next()) {
                rut2 = rs.getString("rut_trabajador").trim();
            }
        } catch (SQLException e) {
            msj = "Error al buscar TRABAJADOR en tabla?";
            jl_Event.setText(msj);
        }

        
        if (verificar() == 0 && i== 0) {
            String sql = "DELETE FROM trabajador WHERE rut_trabajador =" + rut + "";
            try {
                sentencia.executeUpdate(sql);
                jl_Event.setText("TRABAJADOR borrado con exito");
            } catch (SQLException ee) {
                msj = "Error al borrar";
                jl_Event.setText(msj);
            }
        }
        
        limpiaTabla();
        setFilas();

    }//GEN-LAST:event_BTN_DelActionPerformed

    private void JT_rutFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_JT_rutFocusLost
        JT_rut.setText("");
        JT_rut.setFont(new java.awt.Font("Tahoma",2,11));
        JT_rut.setForeground(new java.awt.Color(153,153,153));
        JT_rut.setText("Buscar por rut");     
        limpiaTabla();
        setFilas();
    }//GEN-LAST:event_JT_rutFocusLost

    private void JT_rutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JT_rutMouseClicked
        JT_rut.setText("");
        JT_rut.setFont(new java.awt.Font("Tahoma",0,11));
        JT_rut.setForeground(new java.awt.Color(0,0,0));
        limpiaTabla();
        setFilas();
    }//GEN-LAST:event_JT_rutMouseClicked

    private void JT_rutKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JT_rutKeyTyped
        JT_rut.addKeyListener(new KeyAdapter() {
            public void keyReleased(final KeyEvent e) {
                filtroRut();
            }
        });
        trsfiltro = new TableRowSorter(modeloTabla);
        jTable1.setRowSorter(trsfiltro);
    }//GEN-LAST:event_JT_rutKeyTyped

    private void JT_nombreFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_JT_nombreFocusLost
        JT_nombre.setText("");
        JT_nombre.setFont(new java.awt.Font("Tahoma",2,11));
        JT_nombre.setForeground(new java.awt.Color(153,153,153));
        JT_nombre.setText("Buscar por nombre");
        limpiaTabla();
        setFilas();
    }//GEN-LAST:event_JT_nombreFocusLost

    private void JT_nombreMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JT_nombreMouseClicked
        JT_nombre.setText("");
        JT_nombre.setFont(new java.awt.Font("Tahoma",0,11));
        JT_nombre.setForeground(new java.awt.Color(0,0,0));
        limpiaTabla();
        setFilas();
    }//GEN-LAST:event_JT_nombreMouseClicked

    private void JT_nombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JT_nombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JT_nombreActionPerformed

    private void JT_nombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JT_nombreKeyTyped
        JT_nombre.addKeyListener(new KeyAdapter() {
            public void keyReleased(final KeyEvent e) {
                filtroNombre();
            }
        });
        trsfiltro = new TableRowSorter(modeloTabla);
        jTable1.setRowSorter(trsfiltro);
    }//GEN-LAST:event_JT_nombreKeyTyped

    private void JT_paternoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_JT_paternoFocusLost
        JT_paterno.setText("");
        JT_paterno.setFont(new java.awt.Font("Tahoma",2,11));
        JT_paterno.setForeground(new java.awt.Color(153,153,153));
        JT_paterno.setText("Buscar por paterno");
        limpiaTabla();
        setFilas();
    }//GEN-LAST:event_JT_paternoFocusLost

    private void JT_paternoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JT_paternoMouseClicked
        JT_paterno.setText("");
        JT_paterno.setFont(new java.awt.Font("Tahoma",0,11));
        JT_paterno.setForeground(new java.awt.Color(0,0,0));
        limpiaTabla();
        setFilas();
    }//GEN-LAST:event_JT_paternoMouseClicked

    private void JT_paternoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JT_paternoKeyTyped
        JT_paterno.addKeyListener(new KeyAdapter() {
            public void keyReleased(final KeyEvent e) {
                filtroPaterno();
            }
        });
        trsfiltro = new TableRowSorter(modeloTabla);
        jTable1.setRowSorter(trsfiltro);
    }//GEN-LAST:event_JT_paternoKeyTyped

    private void JT_maternoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_JT_maternoFocusLost
        JT_materno.setText("");
        JT_materno.setFont(new java.awt.Font("Tahoma",2,11));
        JT_materno.setForeground(new java.awt.Color(153,153,153));
        JT_materno.setText("Buscar por materno");
        limpiaTabla();
        setFilas();
    }//GEN-LAST:event_JT_maternoFocusLost

    private void JT_maternoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JT_maternoMouseClicked
        JT_materno.setText("");
        JT_materno.setFont(new java.awt.Font("Tahoma",0,11));
        JT_materno.setForeground(new java.awt.Color(0,0,0));
        limpiaTabla();
        setFilas();
    }//GEN-LAST:event_JT_maternoMouseClicked

    private void JT_maternoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JT_maternoKeyTyped
        JT_materno.addKeyListener(new KeyAdapter() {
            public void keyReleased(final KeyEvent e) {
                filtroMaterno();
            }
        });
        trsfiltro = new TableRowSorter(modeloTabla);
        jTable1.setRowSorter(trsfiltro);
    }//GEN-LAST:event_JT_maternoKeyTyped

    private void JT_cargoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_JT_cargoFocusLost
        JT_cargo.setText("");
        JT_cargo.setFont(new java.awt.Font("Tahoma",2,11));
        JT_cargo.setForeground(new java.awt.Color(153,153,153));
        JT_cargo.setText("Buscar por ciudad");
        limpiaTabla();
        setFilas();
    }//GEN-LAST:event_JT_cargoFocusLost

    private void JT_cargoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JT_cargoMouseClicked
        JT_cargo.setText("");
        JT_cargo.setFont(new java.awt.Font("Tahoma",0,11));
        JT_cargo.setForeground(new java.awt.Color(0,0,0));
        limpiaTabla();
        setFilas();
    }//GEN-LAST:event_JT_cargoMouseClicked

    private void JT_cargoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JT_cargoKeyTyped
        JT_cargo.addKeyListener(new KeyAdapter() {
            public void keyReleased(final KeyEvent e) {
                filtroCargo();
            }
        });
        trsfiltro = new TableRowSorter(modeloTabla);
        jTable1.setRowSorter(trsfiltro);
    }//GEN-LAST:event_JT_cargoKeyTyped

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

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Eliminar_trabajador().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BTN_Del;
    private javax.swing.JButton JB_cancel;
    private javax.swing.JTextField JT_cargo;
    private javax.swing.JTextField JT_materno;
    private javax.swing.JTextField JT_nombre;
    private javax.swing.JTextField JT_paterno;
    private javax.swing.JTextField JT_rut;
    private javax.swing.JComboBox<String> cmbCod;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel jl_Event;
    // End of variables declaration//GEN-END:variables
}
