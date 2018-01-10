/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package racadauto;

import Conexion.Conexion;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author JTorres
 */
public class Facturar_OT extends javax.swing.JFrame {

    private Statement sentencia;
    Conexion con = new Conexion();
    Connection cn = (Connection) con.getConnection();
    DefaultTableModel modeloTablaPresupuesto;
    private TableRowSorter trsfiltro;
    String filtro;

    public Facturar_OT() {
        modeloTablaPresupuesto = new DefaultTableModel(null, getColumnasPresupuesto());
        setFilasPresupuesto();
        initComponents();
        ocultarFila();
    }

    private String[] getColumnasPresupuesto() {
        String columna[] = new String[]{"CODIGO", "FOLIO", "SUBTOTAL", "TOTAL", "FECHA ORDEN", "PATENTE", "RUT TRABAJADOR"};
        return columna;
    }

    public void ocultarFila() {
        tablaPresupuesto.getColumnModel().getColumn(0).setMaxWidth(0);
        tablaPresupuesto.getColumnModel().getColumn(0).setMinWidth(0);
        tablaPresupuesto.getColumnModel().getColumn(0).setPreferredWidth(0);
    }

    private void setFilasPresupuesto() {
        try {
            sentencia = (com.mysql.jdbc.Statement) cn.createStatement();
            ResultSet lista = sentencia.executeQuery("SELECT cod_solicitud, folio, subtotal, total, fecha_orden, patente, rut_trabajador "
                    + "FROM solicitud_servicio "
                    + "WHERE estado_solicitud = 'OT'");
            Object datos[] = new Object[7];
            while (lista.next()) {
                for (int i = 0; i < 7; i++) {
                    datos[i] = lista.getObject(i + 1);
                }
                modeloTablaPresupuesto.addRow(datos);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "Problemas con la base de datos, no se pudo llenar la tabla", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public void filtroCodigo() {

        filtro = JT_codigo.getText().toUpperCase();
        int columna = 1;
        trsfiltro.setRowFilter(RowFilter.regexFilter(JT_codigo.getText().toUpperCase(), columna));
    }

    public void filtroSubtotal() {

        filtro = JT_subtotal.getText().toUpperCase();
        int columna = 2;
        trsfiltro.setRowFilter(RowFilter.regexFilter(JT_subtotal.getText().toUpperCase(), columna));
    }

    public void filtroTotal() {

        filtro = JT_total.getText().toUpperCase();
        int columna = 3;
        trsfiltro.setRowFilter(RowFilter.regexFilter(JT_total.getText().toUpperCase(), columna));
    }

    public void filtroPatente() {

        filtro = JT_patente.getText().toUpperCase();
        int columna = 4;
        trsfiltro.setRowFilter(RowFilter.regexFilter(JT_patente.getText().toUpperCase(), columna));
    }

    public void filtroTrabajador() {

        filtro = JT_trabajador.getText().toUpperCase();
        int columna = 5;
        trsfiltro.setRowFilter(RowFilter.regexFilter(JT_trabajador.getText().toUpperCase(), columna));
    }

    public int verificar() {
        int cont = 0;
        
        if (tablaPresupuesto.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(null,
                    "Seleccione una ORDEN DE TRABAJO", "INFO",
                    JOptionPane.INFORMATION_MESSAGE);
            cont++;
        }

        return cont;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel9 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tablaPresupuesto = new javax.swing.JTable();
        JT_codigo = new javax.swing.JTextField();
        JT_subtotal = new javax.swing.JTextField();
        JT_total = new javax.swing.JTextField();
        JT_patente = new javax.swing.JTextField();
        JT_trabajador = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        JB_cancel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel9.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel9.setText("RACAD AUTOMOTRIZ - FACTURAR ORDEN DE TRABAJO");
        jLabel9.setToolTipText("");

        jLabel5.setText("ORDENES DE TRABAJO");

        tablaPresupuesto.setModel(modeloTablaPresupuesto);
        tablaPresupuesto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaPresupuestoMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tablaPresupuesto);

        JT_codigo.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        JT_codigo.setForeground(new java.awt.Color(153, 153, 153));
        JT_codigo.setText("Buscar por folio");
        JT_codigo.setToolTipText("");
        JT_codigo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                JT_codigoFocusLost(evt);
            }
        });
        JT_codigo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JT_codigoMouseClicked(evt);
            }
        });
        JT_codigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JT_codigoActionPerformed(evt);
            }
        });
        JT_codigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                JT_codigoKeyTyped(evt);
            }
        });

        JT_subtotal.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        JT_subtotal.setForeground(new java.awt.Color(153, 153, 153));
        JT_subtotal.setText("Buscar por subtotal");
        JT_subtotal.setToolTipText("");
        JT_subtotal.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        JT_subtotal.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                JT_subtotalFocusLost(evt);
            }
        });
        JT_subtotal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JT_subtotalMouseClicked(evt);
            }
        });
        JT_subtotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JT_subtotalActionPerformed(evt);
            }
        });
        JT_subtotal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                JT_subtotalKeyTyped(evt);
            }
        });

        JT_total.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        JT_total.setForeground(new java.awt.Color(153, 153, 153));
        JT_total.setText("Buscar por total");
        JT_total.setToolTipText("");
        JT_total.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        JT_total.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                JT_totalFocusLost(evt);
            }
        });
        JT_total.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JT_totalMouseClicked(evt);
            }
        });
        JT_total.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JT_totalActionPerformed(evt);
            }
        });
        JT_total.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                JT_totalKeyTyped(evt);
            }
        });

        JT_patente.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        JT_patente.setForeground(new java.awt.Color(153, 153, 153));
        JT_patente.setText("Buscar por patente");
        JT_patente.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                JT_patenteFocusLost(evt);
            }
        });
        JT_patente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JT_patenteMouseClicked(evt);
            }
        });
        JT_patente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                JT_patenteKeyTyped(evt);
            }
        });

        JT_trabajador.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        JT_trabajador.setForeground(new java.awt.Color(153, 153, 153));
        JT_trabajador.setText("Buscar por trabajador");
        JT_trabajador.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                JT_trabajadorFocusLost(evt);
            }
        });
        JT_trabajador.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JT_trabajadorMouseClicked(evt);
            }
        });
        JT_trabajador.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                JT_trabajadorKeyTyped(evt);
            }
        });

        jButton1.setText("Modificar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Ingresar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        JB_cancel.setText("Volver");
        JB_cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JB_cancelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addComponent(jButton2)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(JB_cancel))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(JT_codigo, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(JT_subtotal, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(JT_total, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(JT_patente, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(JT_trabajador, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel9)
                                        .addGap(217, 217, 217)))
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 719, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jButton1))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(124, 124, 124)
                        .addComponent(jButton1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(JT_patente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(JT_codigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(JT_subtotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(JT_total, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(JT_trabajador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 196, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(JB_cancel))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void tablaPresupuestoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaPresupuestoMouseClicked


    }//GEN-LAST:event_tablaPresupuestoMouseClicked

    private void JT_codigoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_JT_codigoFocusLost
        JT_codigo.setFont(new java.awt.Font("Tahoma", 2, 11));
        JT_codigo.setForeground(new java.awt.Color(153, 153, 153));
        JT_codigo.setText("Buscar por codigo");
    }//GEN-LAST:event_JT_codigoFocusLost

    private void JT_codigoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JT_codigoMouseClicked
        JT_codigo.setText("");
        JT_codigo.setFont(new java.awt.Font("Tahoma", 0, 11));
        JT_codigo.setForeground(new java.awt.Color(0, 0, 0));
    }//GEN-LAST:event_JT_codigoMouseClicked

    private void JT_codigoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JT_codigoKeyTyped
        JT_codigo.addKeyListener(new KeyAdapter() {
            public void keyReleased(final KeyEvent e) {
                filtroCodigo();
            }
        });
        trsfiltro = new TableRowSorter(modeloTablaPresupuesto);
        tablaPresupuesto.setRowSorter(trsfiltro);
    }//GEN-LAST:event_JT_codigoKeyTyped

    private void JT_subtotalFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_JT_subtotalFocusLost
        JT_subtotal.setFont(new java.awt.Font("Tahoma", 2, 11));
        JT_subtotal.setForeground(new java.awt.Color(153, 153, 153));
        JT_subtotal.setText("Buscar por subtotal");
    }//GEN-LAST:event_JT_subtotalFocusLost

    private void JT_subtotalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JT_subtotalMouseClicked
        JT_subtotal.setText("");
        JT_subtotal.setFont(new java.awt.Font("Tahoma", 0, 11));
        JT_subtotal.setForeground(new java.awt.Color(0, 0, 0));
    }//GEN-LAST:event_JT_subtotalMouseClicked

    private void JT_subtotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JT_subtotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JT_subtotalActionPerformed

    private void JT_subtotalKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JT_subtotalKeyTyped
        JT_subtotal.addKeyListener(new KeyAdapter() {
            public void keyReleased(final KeyEvent e) {
                filtroSubtotal();
            }
        });
        trsfiltro = new TableRowSorter(modeloTablaPresupuesto);
        tablaPresupuesto.setRowSorter(trsfiltro);
    }//GEN-LAST:event_JT_subtotalKeyTyped

    private void JT_totalFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_JT_totalFocusLost
        JT_total.setFont(new java.awt.Font("Tahoma", 2, 11));
        JT_total.setForeground(new java.awt.Color(153, 153, 153));
        JT_total.setText("Buscar por total");
    }//GEN-LAST:event_JT_totalFocusLost

    private void JT_totalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JT_totalMouseClicked
        JT_total.setText("");
        JT_total.setFont(new java.awt.Font("Tahoma", 0, 11));
        JT_total.setForeground(new java.awt.Color(0, 0, 0));
    }//GEN-LAST:event_JT_totalMouseClicked

    private void JT_totalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JT_totalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JT_totalActionPerformed

    private void JT_totalKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JT_totalKeyTyped
        JT_total.addKeyListener(new KeyAdapter() {
            public void keyReleased(final KeyEvent e) {
                filtroTotal();
            }
        });
        trsfiltro = new TableRowSorter(modeloTablaPresupuesto);
        tablaPresupuesto.setRowSorter(trsfiltro);
    }//GEN-LAST:event_JT_totalKeyTyped

    private void JT_patenteFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_JT_patenteFocusLost
        JT_patente.setFont(new java.awt.Font("Tahoma", 2, 11));
        JT_patente.setForeground(new java.awt.Color(153, 153, 153));
        JT_patente.setText("Buscar por patente");
    }//GEN-LAST:event_JT_patenteFocusLost

    private void JT_patenteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JT_patenteMouseClicked
        JT_patente.setText("");
        JT_patente.setFont(new java.awt.Font("Tahoma", 0, 11));
        JT_patente.setForeground(new java.awt.Color(0, 0, 0));
    }//GEN-LAST:event_JT_patenteMouseClicked

    private void JT_patenteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JT_patenteKeyTyped
        JT_patente.addKeyListener(new KeyAdapter() {
            public void keyReleased(final KeyEvent e) {
                filtroPatente();
            }
        });
        trsfiltro = new TableRowSorter(modeloTablaPresupuesto);
        tablaPresupuesto.setRowSorter(trsfiltro);
    }//GEN-LAST:event_JT_patenteKeyTyped

    private void JT_trabajadorFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_JT_trabajadorFocusLost
        JT_trabajador.setFont(new java.awt.Font("Tahoma", 2, 11));
        JT_trabajador.setForeground(new java.awt.Color(153, 153, 153));
        JT_trabajador.setText("Buscar por trabajador");
    }//GEN-LAST:event_JT_trabajadorFocusLost

    private void JT_trabajadorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JT_trabajadorMouseClicked
        JT_trabajador.setText("");
        JT_trabajador.setFont(new java.awt.Font("Tahoma", 0, 11));
        JT_trabajador.setForeground(new java.awt.Color(0, 0, 0));
    }//GEN-LAST:event_JT_trabajadorMouseClicked

    private void JT_trabajadorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JT_trabajadorKeyTyped
        JT_trabajador.addKeyListener(new KeyAdapter() {
            public void keyReleased(final KeyEvent e) {
                filtroTrabajador();
            }
        });
        trsfiltro = new TableRowSorter(modeloTablaPresupuesto);
        tablaPresupuesto.setRowSorter(trsfiltro);
    }//GEN-LAST:event_JT_trabajadorKeyTyped

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Modificar_OT a = new Modificar_OT();
        a.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        if (verificar() == 0) {

            int solicitud = Integer.parseInt(tablaPresupuesto.getValueAt(tablaPresupuesto.getSelectedRow(), 0).toString());
            int cont = 0, servicio = 0;

            try {

                ResultSet rs = sentencia.executeQuery("SELECT id_servicio FROM detalle_solicitud "
                        + "WHERE cod_solicitud = " + solicitud + " AND estado_seguimiento <> 'TERMINADO'");

                while (rs.next()) {
                    servicio = rs.getInt("id_servicio");

                    if (servicio > 0) {
                        JOptionPane.showMessageDialog(null,
                        "ORDEN DE TRABAJO no FACTURADA aun quedan servicios por terminar", "ERROR",
                        JOptionPane.ERROR_MESSAGE);
                    } else {
                        String estado = "FACTURA";

                        String sql = "UPDATE solicitud_servicio "
                                + "SET estado_solicitud ='" + estado + "' "
                                + "WHERE cod_solicitud = '" + solicitud + "'";

                        try {
                            sentencia.executeUpdate(sql);
                        } catch (SQLException e) {
                            cont++;
                        }
                    }
                }

            } catch (SQLException ex) {
                cont++;
            }

            if (cont == 0) {
                JOptionPane.showMessageDialog(null,
                        "ORDEN DE TRABAJO FACTURADA con exito", "INFO",
                        JOptionPane.INFORMATION_MESSAGE);
            } else{
                JOptionPane.showMessageDialog(null,
                        "ORDEN DE TRABAJO no FACTURADA,problemas con la base de datos", "ERROR",
                        JOptionPane.ERROR_MESSAGE);
            }

        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void JB_cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JB_cancelActionPerformed
        this.dispose();
    }//GEN-LAST:event_JB_cancelActionPerformed

    private void JT_codigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JT_codigoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JT_codigoActionPerformed

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
            java.util.logging.Logger.getLogger(Facturar_OT.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Facturar_OT.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Facturar_OT.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Facturar_OT.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Facturar_OT().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton JB_cancel;
    private javax.swing.JTextField JT_codigo;
    private javax.swing.JTextField JT_patente;
    private javax.swing.JTextField JT_subtotal;
    private javax.swing.JTextField JT_total;
    private javax.swing.JTextField JT_trabajador;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable tablaPresupuesto;
    // End of variables declaration//GEN-END:variables
}
