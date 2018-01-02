package racadauto;

import Conexion.Conexion;
import com.mysql.jdbc.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class Consultar_vehiculo extends javax.swing.JFrame {
    private Statement sentencia;
    Conexion con = new Conexion();
    Connection cn = (Connection) con.getConnection();
    private String msj;
    DefaultTableModel modeloTabla;
    private TableRowSorter trsfiltro;
    String filtro;

    public Consultar_vehiculo() {
        
        modeloTabla = new DefaultTableModel(null, getColumnas());
        setFilas();
        initComponents(); 
        
    }
    
    private String[] getColumnas() {

        String columna[] = new String[]{"PATENTE", "AÑO", "KILOMETRAJE", "V.I.N.", "COLOR","MARCA", "MODELO", "NOMBRE PROPIETARIO", "APELLIDO PATERNO","APELLIDO MATERNO"};

        return columna;
    }

    private void setFilas() {
        try {
            sentencia = (com.mysql.jdbc.Statement) cn.createStatement();
            ResultSet lista = sentencia.executeQuery("SELECT v.patente, v.año, v.kms, v.vin, v.color, ma.nombre, mo.nombre, c.nombre, c.ape_paterno, c.ape_materno "
                    + "FROM vehiculo v INNER JOIN cliente c ON v.rut_cliente = c.rut_cliente "
                    + "LEFT JOIN marca ma ON v.id_marca = ma.id_marca LEFT JOIN modelo mo ON v.id_modelo = mo.id_modelo");
            Object datos[] = new Object[10];
            while (lista.next()) {
                for (int i = 0; i < 10; i++) {
                    datos[i] = lista.getObject(i + 1);
                }
                modeloTabla.addRow(datos);
            }
        } catch (Exception e) {
            msj = "No se pudo llenar tabla";
        }
    }
    
    public void filtroPatente() {

        filtro = JT_patente.getText().toUpperCase();
        int columna = 0;
        trsfiltro.setRowFilter(RowFilter.regexFilter(JT_patente.getText().toUpperCase(), columna));
    }
    
    public void filtroAño() {

        filtro = JT_año.getText().toUpperCase();
        int columna = 1;
        trsfiltro.setRowFilter(RowFilter.regexFilter(JT_año.getText().toUpperCase(), columna));
    }
    
    public void filtroKms() {

        filtro = JT_kms.getText().toUpperCase();
        int columna = 2;
        trsfiltro.setRowFilter(RowFilter.regexFilter(JT_kms.getText().toUpperCase(), columna));
    }
    
    public void filtroVin() {

        filtro = JT_vin.getText().toUpperCase();
        int columna = 3;
        trsfiltro.setRowFilter(RowFilter.regexFilter(JT_vin.getText().toUpperCase(), columna));
    }
    
    public void filtroColor() {

        filtro = JT_color.getText().toUpperCase();
        int columna = 4;
        trsfiltro.setRowFilter(RowFilter.regexFilter(JT_color.getText().toUpperCase(), columna));
    }
    
    public void filtroMarca() {

        filtro = JT_marca.getText().toUpperCase();
        int columna = 5;
        trsfiltro.setRowFilter(RowFilter.regexFilter(JT_marca.getText().toUpperCase(), columna));
    }
   
    public void filtroModelo() {

        filtro = JT_modelo.getText().toUpperCase();
        int columna = 6;
        trsfiltro.setRowFilter(RowFilter.regexFilter(JT_modelo.getText().toUpperCase(), columna));
    }
    
    public void filtroNombre() {

        filtro = JT_nombre.getText().toUpperCase();
        int columna = 7;
        trsfiltro.setRowFilter(RowFilter.regexFilter(JT_nombre.getText().toUpperCase(), columna));
    }
    
    public void filtroPaterno() {

        filtro = JT_paterno.getText().toUpperCase();
        int columna = 8;
        trsfiltro.setRowFilter(RowFilter.regexFilter(JT_paterno.getText().toUpperCase(), columna));
    }
    
    public void filtroMaterno() {

        filtro = JT_materno.getText().toUpperCase();
        int columna = 9;
        trsfiltro.setRowFilter(RowFilter.regexFilter(JT_materno.getText().toUpperCase(), columna));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cmbCod = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        JB_cancel = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        JT_patente = new javax.swing.JTextField();
        JT_año = new javax.swing.JTextField();
        JT_kms = new javax.swing.JTextField();
        JT_vin = new javax.swing.JTextField();
        JT_color = new javax.swing.JTextField();
        JT_marca = new javax.swing.JTextField();
        JT_modelo = new javax.swing.JTextField();
        JT_nombre = new javax.swing.JTextField();
        JT_paterno = new javax.swing.JTextField();
        JT_materno = new javax.swing.JTextField();

        cmbCod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbCodActionPerformed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("CONSULTAR VEHICULO");
        setMinimumSize(new java.awt.Dimension(420, 210));
        setResizable(false);

        jLabel9.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel9.setText("RACAD AUTOMOTRIZ - CONSULTAR VEHICULO");

        JB_cancel.setText("Volver");
        JB_cancel.setPreferredSize(new java.awt.Dimension(100, 35));
        JB_cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JB_cancelActionPerformed(evt);
            }
        });

        jTable1.setAutoCreateRowSorter(true);
        jTable1.setModel(modeloTabla);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        JT_patente.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        JT_patente.setForeground(new java.awt.Color(153, 153, 153));
        JT_patente.setText("Buscar por patente");
        JT_patente.setToolTipText("");
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

        JT_año.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        JT_año.setForeground(new java.awt.Color(153, 153, 153));
        JT_año.setText("Buscar por año");
        JT_año.setToolTipText("");
        JT_año.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        JT_año.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                JT_añoFocusLost(evt);
            }
        });
        JT_año.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JT_añoMouseClicked(evt);
            }
        });
        JT_año.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JT_añoActionPerformed(evt);
            }
        });
        JT_año.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                JT_añoKeyTyped(evt);
            }
        });

        JT_kms.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        JT_kms.setForeground(new java.awt.Color(153, 153, 153));
        JT_kms.setText("Buscar por kms");
        JT_kms.setToolTipText("");
        JT_kms.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                JT_kmsFocusLost(evt);
            }
        });
        JT_kms.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JT_kmsMouseClicked(evt);
            }
        });
        JT_kms.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                JT_kmsKeyTyped(evt);
            }
        });

        JT_vin.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        JT_vin.setForeground(new java.awt.Color(153, 153, 153));
        JT_vin.setText("Buscar por vin");
        JT_vin.setToolTipText("");
        JT_vin.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        JT_vin.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                JT_vinFocusLost(evt);
            }
        });
        JT_vin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JT_vinMouseClicked(evt);
            }
        });
        JT_vin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JT_vinActionPerformed(evt);
            }
        });
        JT_vin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                JT_vinKeyTyped(evt);
            }
        });

        JT_color.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        JT_color.setForeground(new java.awt.Color(153, 153, 153));
        JT_color.setText("Buscar por color");
        JT_color.setToolTipText("");
        JT_color.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                JT_colorFocusLost(evt);
            }
        });
        JT_color.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JT_colorMouseClicked(evt);
            }
        });
        JT_color.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                JT_colorKeyTyped(evt);
            }
        });

        JT_marca.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        JT_marca.setForeground(new java.awt.Color(153, 153, 153));
        JT_marca.setText("Buscar por marca");
        JT_marca.setToolTipText("");
        JT_marca.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                JT_marcaFocusLost(evt);
            }
        });
        JT_marca.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JT_marcaMouseClicked(evt);
            }
        });
        JT_marca.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                JT_marcaKeyTyped(evt);
            }
        });

        JT_modelo.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        JT_modelo.setForeground(new java.awt.Color(153, 153, 153));
        JT_modelo.setText("Buscar por modelo");
        JT_modelo.setToolTipText("");
        JT_modelo.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        JT_modelo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                JT_modeloFocusLost(evt);
            }
        });
        JT_modelo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JT_modeloMouseClicked(evt);
            }
        });
        JT_modelo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JT_modeloActionPerformed(evt);
            }
        });
        JT_modelo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                JT_modeloKeyTyped(evt);
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 426, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(JT_patente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(JT_año, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(JT_kms, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(JT_vin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(JT_color, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(JT_marca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(JT_modelo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(JT_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(JT_paterno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(JT_materno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(JB_cancel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JT_patente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JT_año, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JT_kms, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JT_vin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JT_color, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JT_marca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JT_modelo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JT_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JT_paterno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JT_materno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(JB_cancel, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked

    }//GEN-LAST:event_jTable1MouseClicked

    private void JT_patenteFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_JT_patenteFocusLost
        JT_patente.setFont(new java.awt.Font("Tahoma",2,11));
        JT_patente.setForeground(new java.awt.Color(153,153,153));
        JT_patente.setText("Buscar por patente");
    }//GEN-LAST:event_JT_patenteFocusLost

    private void JT_patenteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JT_patenteMouseClicked
        JT_patente.setText("");
        JT_patente.setFont(new java.awt.Font("Tahoma",0,11));
        JT_patente.setForeground(new java.awt.Color(0,0,0));
    }//GEN-LAST:event_JT_patenteMouseClicked

    private void JT_patenteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JT_patenteKeyTyped
        JT_patente.addKeyListener(new KeyAdapter() {
            public void keyReleased(final KeyEvent e) {
                filtroPatente();
            }
        });
        trsfiltro = new TableRowSorter(modeloTabla);
        jTable1.setRowSorter(trsfiltro);
    }//GEN-LAST:event_JT_patenteKeyTyped

    private void JT_añoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_JT_añoFocusLost
        JT_año.setFont(new java.awt.Font("Tahoma",2,11));
        JT_año.setForeground(new java.awt.Color(153,153,153));
        JT_año.setText("Buscar por año");
    }//GEN-LAST:event_JT_añoFocusLost

    private void JT_añoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JT_añoMouseClicked
        JT_año.setText("");
        JT_año.setFont(new java.awt.Font("Tahoma",0,11));
        JT_año.setForeground(new java.awt.Color(0,0,0));
    }//GEN-LAST:event_JT_añoMouseClicked

    private void JT_añoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JT_añoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JT_añoActionPerformed

    private void JT_añoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JT_añoKeyTyped
        JT_año.addKeyListener(new KeyAdapter() {
            public void keyReleased(final KeyEvent e) {
                filtroAño();
            }
        });
        trsfiltro = new TableRowSorter(modeloTabla);
        jTable1.setRowSorter(trsfiltro);

    }//GEN-LAST:event_JT_añoKeyTyped

    private void JT_kmsFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_JT_kmsFocusLost
        JT_kms.setFont(new java.awt.Font("Tahoma",2,11));
        JT_kms.setForeground(new java.awt.Color(153,153,153));
        JT_kms.setText("Buscar por kms");
    }//GEN-LAST:event_JT_kmsFocusLost

    private void JT_kmsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JT_kmsMouseClicked
        JT_kms.setText("");
        JT_kms.setFont(new java.awt.Font("Tahoma",0,11));
        JT_kms.setForeground(new java.awt.Color(0,0,0));
    }//GEN-LAST:event_JT_kmsMouseClicked

    private void JT_kmsKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JT_kmsKeyTyped
        JT_kms.addKeyListener(new KeyAdapter() {
            public void keyReleased(final KeyEvent e) {
                filtroKms();
            }
        });
        trsfiltro = new TableRowSorter(modeloTabla);
        jTable1.setRowSorter(trsfiltro);
    }//GEN-LAST:event_JT_kmsKeyTyped

    private void JT_vinFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_JT_vinFocusLost
        JT_vin.setFont(new java.awt.Font("Tahoma",2,11));
        JT_vin.setForeground(new java.awt.Color(153,153,153));
        JT_vin.setText("Buscar por vin");
    }//GEN-LAST:event_JT_vinFocusLost

    private void JT_vinMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JT_vinMouseClicked
        JT_vin.setText("");
        JT_vin.setFont(new java.awt.Font("Tahoma",0,11));
        JT_vin.setForeground(new java.awt.Color(0,0,0));
    }//GEN-LAST:event_JT_vinMouseClicked

    private void JT_vinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JT_vinActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JT_vinActionPerformed

    private void JT_vinKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JT_vinKeyTyped
        JT_vin.addKeyListener(new KeyAdapter() {
            public void keyReleased(final KeyEvent e) {
                filtroVin();
            }
        });
        trsfiltro = new TableRowSorter(modeloTabla);
        jTable1.setRowSorter(trsfiltro);

    }//GEN-LAST:event_JT_vinKeyTyped

    private void JT_colorFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_JT_colorFocusLost
        JT_color.setFont(new java.awt.Font("Tahoma",2,11));
        JT_color.setForeground(new java.awt.Color(153,153,153));
        JT_color.setText("Buscar por color");
    }//GEN-LAST:event_JT_colorFocusLost

    private void JT_colorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JT_colorMouseClicked
        JT_color.setText("");
        JT_color.setFont(new java.awt.Font("Tahoma",0,11));
        JT_color.setForeground(new java.awt.Color(0,0,0));
    }//GEN-LAST:event_JT_colorMouseClicked

    private void JT_colorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JT_colorKeyTyped
        JT_color.addKeyListener(new KeyAdapter() {
            public void keyReleased(final KeyEvent e) {
                filtroColor();
            }
        });
        trsfiltro = new TableRowSorter(modeloTabla);
        jTable1.setRowSorter(trsfiltro);
    }//GEN-LAST:event_JT_colorKeyTyped

    private void JT_marcaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_JT_marcaFocusLost
        JT_marca.setFont(new java.awt.Font("Tahoma",2,11));
        JT_marca.setForeground(new java.awt.Color(153,153,153));
        JT_marca.setText("Buscar por marca");
    }//GEN-LAST:event_JT_marcaFocusLost

    private void JT_marcaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JT_marcaMouseClicked
        JT_marca.setText("");
        JT_marca.setFont(new java.awt.Font("Tahoma",0,11));
        JT_marca.setForeground(new java.awt.Color(0,0,0));
    }//GEN-LAST:event_JT_marcaMouseClicked

    private void JT_marcaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JT_marcaKeyTyped
        JT_marca.addKeyListener(new KeyAdapter() {
            public void keyReleased(final KeyEvent e) {
                filtroMarca();
            }
        });
        trsfiltro = new TableRowSorter(modeloTabla);
        jTable1.setRowSorter(trsfiltro);
    }//GEN-LAST:event_JT_marcaKeyTyped

    private void JT_modeloFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_JT_modeloFocusLost
        JT_modelo.setFont(new java.awt.Font("Tahoma",2,11));
        JT_modelo.setForeground(new java.awt.Color(153,153,153));
        JT_modelo.setText("Buscar por modelo");
    }//GEN-LAST:event_JT_modeloFocusLost

    private void JT_modeloMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JT_modeloMouseClicked
        JT_modelo.setText("");
        JT_modelo.setFont(new java.awt.Font("Tahoma",0,11));
        JT_modelo.setForeground(new java.awt.Color(0,0,0));
    }//GEN-LAST:event_JT_modeloMouseClicked

    private void JT_modeloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JT_modeloActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JT_modeloActionPerformed

    private void JT_modeloKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JT_modeloKeyTyped
        JT_modelo.addKeyListener(new KeyAdapter() {
            public void keyReleased(final KeyEvent e) {
                filtroModelo();
            }
        });
        trsfiltro = new TableRowSorter(modeloTabla);
        jTable1.setRowSorter(trsfiltro);

    }//GEN-LAST:event_JT_modeloKeyTyped

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

    private void JT_paternoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_JT_paternoFocusLost
        JT_paterno.setFont(new java.awt.Font("Tahoma",2,11));
        JT_paterno.setForeground(new java.awt.Color(153,153,153));
        JT_paterno.setText("Buscar por paterno");
    }//GEN-LAST:event_JT_paternoFocusLost

    private void JT_paternoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JT_paternoMouseClicked
        JT_paterno.setText("");
        JT_paterno.setFont(new java.awt.Font("Tahoma",0,11));
        JT_paterno.setForeground(new java.awt.Color(0,0,0));
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
        JT_materno.setFont(new java.awt.Font("Tahoma",2,11));
        JT_materno.setForeground(new java.awt.Color(153,153,153));
        JT_materno.setText("Buscar por materno");
    }//GEN-LAST:event_JT_maternoFocusLost

    private void JT_maternoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JT_maternoMouseClicked
        JT_materno.setText("");
        JT_materno.setFont(new java.awt.Font("Tahoma",0,11));
        JT_materno.setForeground(new java.awt.Color(0,0,0));
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
                new Consultar_vehiculo().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton JB_cancel;
    private javax.swing.JTextField JT_año;
    private javax.swing.JTextField JT_color;
    private javax.swing.JTextField JT_kms;
    private javax.swing.JTextField JT_marca;
    private javax.swing.JTextField JT_materno;
    private javax.swing.JTextField JT_modelo;
    private javax.swing.JTextField JT_nombre;
    private javax.swing.JTextField JT_patente;
    private javax.swing.JTextField JT_paterno;
    private javax.swing.JTextField JT_vin;
    private javax.swing.JComboBox<String> cmbCod;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
