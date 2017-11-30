package racadauto;

import Conexion.Conexion;
import com.mysql.jdbc.*;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.RowFilter;
import javax.swing.table.TableRowSorter;

public class Consultar_item extends javax.swing.JFrame /*implements ActionListener*/ {

    private Statement sentencia;
    Conexion con = new Conexion();
    Connection cn = (Connection) con.getConnection();
    private String msj;
    DefaultTableModel modeloTabla;
    private TableRowSorter trsfiltro;
    String filtro;
    
    public Consultar_item() {
        modeloTabla = new DefaultTableModel(null, getColumnas());
        setFilas();
        initComponents();
    }

    private String[] getColumnas() {

        String columna[] = new String[]{"Nombre", "Stock actual", "Stock crítico", "Valor costo", "Valor venta", "Medida", "Familia"};

        return columna;
    }

    private void setFilas() {
        try {
            sentencia = (Statement) cn.createStatement();
            ResultSet lista = sentencia.executeQuery("SELECT i.nombre, i.stock_actual, i.stock_critico, i.valor_costo, i.valor_venta, um.nombre, f.nombre"
                    + " FROM inventario i INNER JOIN unidad_medida um ON i.id_medida = um.id_medida LEFT JOIN familia f ON i.id_familia = f.id_familia "
                    + "WHERE i.estado = 1");
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

    public void filtroActual() {

        filtro = JT_actual.getText();
        int columna = 1;
        trsfiltro.setRowFilter(RowFilter.regexFilter(JT_actual.getText(), columna));
        
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
    
    
    public void filtroMedida() {

        filtro = JT_medida.getText().toUpperCase();
        int columna = 5;
        trsfiltro.setRowFilter(RowFilter.regexFilter(JT_medida.getText().toUpperCase(), columna));
    }

    public void filtroFamilia() {

        filtro = JT_familia.getText().toUpperCase();
        int columna = 6;
        trsfiltro.setRowFilter(RowFilter.regexFilter(JT_familia.getText().toUpperCase(), columna));
    }
    
    public void clean() {
        try {
            modeloTabla.setRowCount(0);
        } catch (Exception e) {
            msj = "No se pudo vaciar tabla";
        }
    }

    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jRadioButtonMenuItem1 = new javax.swing.JRadioButtonMenuItem();
        jButton2 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        JB_cancel = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        JT_medida = new javax.swing.JTextField();
        JT_nombre = new javax.swing.JTextField();
        JT_actual = new javax.swing.JTextField();
        JT_vventa = new javax.swing.JTextField();
        JT_vcosto = new javax.swing.JTextField();
        JT_familia = new javax.swing.JTextField();

        jRadioButtonMenuItem1.setSelected(true);
        jRadioButtonMenuItem1.setText("jRadioButtonMenuItem1");

        jButton2.setText("jButton2");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("CONSULTAR INVENTARIO");

        jLabel9.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel9.setText("RACAD AUTOMOTRIZ - CONSULTAR INVENTARIO");

        JB_cancel.setText("Volver");
        JB_cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JB_cancelActionPerformed(evt);
            }
        });

        jTable1.setAutoCreateRowSorter(true);
        jTable1.setModel(modeloTabla);
        jScrollPane1.setViewportView(jTable1);

        jButton1.setText("Menor o Igual a Critico");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton3.setText("Tabla Completa");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        JT_medida.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        JT_medida.setForeground(new java.awt.Color(153, 153, 153));
        JT_medida.setText("Buscar por medida");
        JT_medida.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                JT_medidaFocusLost(evt);
            }
        });
        JT_medida.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JT_medidaMouseClicked(evt);
            }
        });
        JT_medida.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                JT_medidaKeyTyped(evt);
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

        JT_familia.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        JT_familia.setForeground(new java.awt.Color(153, 153, 153));
        JT_familia.setText("Buscar por familia");
        JT_familia.setToolTipText("");
        JT_familia.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        JT_familia.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                JT_familiaFocusLost(evt);
            }
        });
        JT_familia.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JT_familiaMouseClicked(evt);
            }
        });
        JT_familia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JT_familiaActionPerformed(evt);
            }
        });
        JT_familia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                JT_familiaKeyTyped(evt);
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
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 426, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(JT_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(JT_actual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(127, 127, 127)
                                .addComponent(JT_vcosto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(JT_vventa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(JT_medida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(JT_familia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(327, 327, 327))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButton3)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1)
                        .addGap(426, 426, 426)
                        .addComponent(JB_cancel, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(JT_actual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(JT_medida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(JT_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(JT_vventa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(JT_vcosto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(JT_familia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 99, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(JB_cancel, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton3, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void JB_cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JB_cancelActionPerformed
        this.dispose();
    }//GEN-LAST:event_JB_cancelActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        clean();
        try {
            sentencia = (Statement) cn.createStatement();
            ResultSet lista = sentencia.executeQuery("SELECT i.nombre, i.stock_actual, i.stock_critico, i.valor_costo, i.valor_venta, um.nombre, f.nombre"
                    + " FROM inventario i INNER JOIN unidad_medida um ON i.id_medida = um.id_medida LEFT JOIN familia f ON i.id_familia = f.id_familia "
                    + "WHERE i.stock_actual  <= i.stock_critico");
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
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        clean();
        setFilas();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void JT_medidaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_JT_medidaFocusLost
        JT_medida.setFont(new java.awt.Font("Tahoma",2,11));
        JT_medida.setForeground(new java.awt.Color(153,153,153));
        JT_medida.setText("Buscar por medida");
    }//GEN-LAST:event_JT_medidaFocusLost

    private void JT_medidaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JT_medidaMouseClicked
        JT_medida.setText("");
        JT_medida.setFont(new java.awt.Font("Tahoma",0,11));
        JT_medida.setForeground(new java.awt.Color(0,0,0));
    }//GEN-LAST:event_JT_medidaMouseClicked

    private void JT_medidaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JT_medidaKeyTyped
        JT_medida.addKeyListener(new KeyAdapter() {
            public void keyReleased(final KeyEvent e) {
                filtroMedida();
            }
        });
        trsfiltro = new TableRowSorter(modeloTabla);
        jTable1.setRowSorter(trsfiltro);
    }//GEN-LAST:event_JT_medidaKeyTyped

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

    private void JT_familiaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_JT_familiaFocusLost
        JT_familia.setFont(new java.awt.Font("Tahoma",2,11));
        JT_familia.setForeground(new java.awt.Color(153,153,153));
        JT_familia.setText("Buscar por familia");
    }//GEN-LAST:event_JT_familiaFocusLost

    private void JT_familiaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JT_familiaMouseClicked
        JT_familia.setText("");
        JT_familia.setFont(new java.awt.Font("Tahoma",0,11));
        JT_familia.setForeground(new java.awt.Color(0,0,0));
    }//GEN-LAST:event_JT_familiaMouseClicked

    private void JT_familiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JT_familiaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JT_familiaActionPerformed

    private void JT_familiaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JT_familiaKeyTyped
        JT_familia.addKeyListener(new KeyAdapter() {
            public void keyReleased(final KeyEvent e) {
                filtroFamilia();
            }
        });
        trsfiltro = new TableRowSorter(modeloTabla);
        jTable1.setRowSorter(trsfiltro);
    }//GEN-LAST:event_JT_familiaKeyTyped

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
            java.util.logging.Logger.getLogger(Consultar_item.class
.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        

} catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Consultar_item.class
.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        

} catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Consultar_item.class
.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        

} catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Consultar_item.class
.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Consultar_item().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton JB_cancel;
    private javax.swing.JTextField JT_actual;
    private javax.swing.JTextField JT_familia;
    private javax.swing.JTextField JT_medida;
    private javax.swing.JTextField JT_nombre;
    private javax.swing.JTextField JT_vcosto;
    private javax.swing.JTextField JT_vventa;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JRadioButtonMenuItem jRadioButtonMenuItem1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
