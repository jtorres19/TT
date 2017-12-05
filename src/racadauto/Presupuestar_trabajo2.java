package racadauto;

import Conexion.Conexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Presupuestar_trabajo2 extends javax.swing.JFrame {

    private Statement sentencia;
    Conexion con = new Conexion();
    Connection cn = (Connection) con.getConnection();
    private String msj;
    DefaultTableModel modeloTabla;
    DefaultTableModel modeloTabla2;
    DefaultTableModel modeloTabla3;
    DefaultTableModel modeloTabla4;
    DefaultTableModel modeloTabla5;
    DefaultTableModel modeloTabla6;
    DefaultTableModel modeloTabla7;

    public Presupuestar_trabajo2() {
        modeloTabla = new DefaultTableModel(null, getColumnas());
        modeloTabla2 = new DefaultTableModel(null, getColumnas2());
        modeloTabla3 = new DefaultTableModel(null, getColumnas3());
        modeloTabla4 = new DefaultTableModel(null, getColumnas4());
        modeloTabla5 = new DefaultTableModel(null, getColumnas5());
        modeloTabla6 = new DefaultTableModel(null, getColumnas6());
        modeloTabla7 = new DefaultTableModel(null, getColumnas7());
        setFilas();
        setFilas3();
        setFilas5();
        setFilas7();
        initComponents();
        fechaActual();
        llenarLabels();
    }

    private String[] getColumnas() {
        String columna[] = new String[]{"RUT", "NOMBRE", "APELLIDO PATERNO", "APELLIDO MATERNO"};
        return columna;
    }

    private String[] getColumnas2() {
        String columna[] = new String[]{"PATENTE", "AÑO", "KMS", "V.I.N", "COLOR", "MARCA", "MODELO"};
        return columna;
    }

    private String[] getColumnas3() {
        String columna[] = new String[]{"CODIGO", "NOMBRE", "VALOR VENTA"};
        return columna;
    }

    private String[] getColumnas4() {
        String columna[] = new String[]{"CODIGO", "NOMBRE", "VALOR VENTA", "CANTIDAD"};
        return columna;
    }

    private String[] getColumnas5() {
        String columna[] = new String[]{"ID", "COMPONENTE", "PRECIO"};
        return columna;
    }

    private String[] getColumnas6() {
        String columna[] = new String[]{"ID", "COMPONENTE", "PRECIO"};
        return columna;
    }

    private String[] getColumnas7() {
        String columna[] = new String[]{"RUT", "NOMBRE", "APELLIDO PATERNO", "APELLIDO MATERNO"};
        return columna;
    }

    private void setFilas() {
        try {
            sentencia = (com.mysql.jdbc.Statement) cn.createStatement();
            ResultSet lista = sentencia.executeQuery("SELECT rut_cliente, nombre, ape_paterno, ape_materno "
                    + "FROM cliente ");
            Object datos[] = new Object[4];
            while (lista.next()) {
                for (int i = 0; i < 4; i++) {
                    datos[i] = lista.getObject(i + 1);
                }
                modeloTabla.addRow(datos);
            }
        } catch (Exception e) {
            msj = "No se pudo llenar tabla";
            LBL_estado.setText(msj);
        }
    }

    private void setFilas7() {
        try {
            sentencia = (com.mysql.jdbc.Statement) cn.createStatement();
            ResultSet lista = sentencia.executeQuery("SELECT rut_trabajador, nombre, ape_paterno, ape_materno "
                    + "FROM trabajador ");
            Object datos[] = new Object[4];
            while (lista.next()) {
                for (int i = 0; i < 4; i++) {
                    datos[i] = lista.getObject(i + 1);
                }
                modeloTabla7.addRow(datos);
            }
        } catch (Exception e) {
            msj = "No se pudo llenar tabla";
            LBL_estado.setText(msj);
        }
    }

    private void setFilas3() {
        try {
            sentencia = (com.mysql.jdbc.Statement) cn.createStatement();
            ResultSet lista = sentencia.executeQuery("SELECT cod_item, nombre, valor_venta "
                    + "FROM inventario "
                    + "WHERE estado = 1");
            Object datos[] = new Object[4];
            while (lista.next()) {
                for (int i = 0; i < 3; i++) {
                    datos[i] = lista.getObject(i + 1);
                }
                modeloTabla3.addRow(datos);
            }
        } catch (Exception e) {
            msj = "No se pudo llenar tabla";
            LBL_estado.setText(msj);
        }
    }

    private void setFilas5() {
        try {
            sentencia = (com.mysql.jdbc.Statement) cn.createStatement();
            ResultSet lista = sentencia.executeQuery("SELECT id_servicio, componente, precio "
                    + "FROM servicio ");
            Object datos[] = new Object[4];
            while (lista.next()) {
                for (int i = 0; i < 3; i++) {
                    datos[i] = lista.getObject(i + 1);
                }
                modeloTabla5.addRow(datos);
            }
        } catch (Exception e) {
            msj = "No se pudo llenar tabla";
            LBL_estado.setText(msj);
        }
    }

    public void fechaActual() {
        java.util.Date fechaActual = new java.util.Date();
        long fecha = fechaActual.getTime();
        java.sql.Date sqlDate = new java.sql.Date(fecha);
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        lblFecha.setText(formato.format(sqlDate).trim());
        lblFecha1.setText(formato.format(sqlDate).trim());
        lblFecha2.setText(formato.format(sqlDate).trim());
    }

    public void llenarLabels() {
        int cod = 0;
        try {
            sentencia = (com.mysql.jdbc.Statement) cn.createStatement();
            ResultSet rs = sentencia.executeQuery("SELECT MAX(cod_solicitud) as cod_solicitud FROM solicitud_servicio");
            while (rs.next()) {
                cod = rs.getInt("cod_solicitud");
            }
            cod++;
        } catch (SQLException f) {
            msj = "Error con Codigo";
        }
        LBL_solicitud.setText("" + cod + "");
        LBL_solicitud1.setText("" + cod + "");
        LBL_solicitud2.setText("" + cod + "");
    }

    void limpiaTabla() {
        do {
            modeloTabla2.setRowCount(0);
        } while (modeloTabla2.getRowCount() != 0);
    }

    public int verificar() {
        int cont = 0;
        String obser = JTA_observaciones.getText().toUpperCase().trim();

        if ((obser.equals(""))) {
            JOptionPane.showMessageDialog(null,
                    "Error, dejó una casilla vacía", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            cont++;
        }
        if (obser.length() > 140) {
            JOptionPane.showMessageDialog(null,
                    "Error, observacion no puede exceder los 140 caracteres", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            cont++;
        }

        if (jTable1.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(null,
                    "Error, Seleccione cliente!", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            cont++;
        } else if (jTable2.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(null,
                    "Error, Seleccione vehiculo!", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            cont++;
        } else if (jTable7.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(null,
                    "Error, Seleccione trabajador!", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            cont++;
        }

        if (jTable4.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null,
                    "Error, Añada insumo!", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            cont++;
        } else if (jTable6.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null,
                    "Error, Añada servicio!", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            cont++;
        }

        return cont;
    }

    public void clean() {
        JTA_observaciones.setText("");
        LBL_estado.setText("");
        lblFecha.setText("");
        lblFecha1.setText("");
        lblFecha2.setText("");
        LBL_solicitud.setText("");
        LBL_solicitud1.setText("");
        LBL_solicitud2.setText("");
        modeloTabla4.setRowCount(0);
        modeloTabla6.setRowCount(0);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        LBL_solicitud = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lblFecha = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        LBL_solicitud1 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lblFecha1 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jLabel15 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jScrollPane8 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        jScrollPane9 = new javax.swing.JScrollPane();
        jTable5 = new javax.swing.JTable();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jScrollPane10 = new javax.swing.JScrollPane();
        jTable6 = new javax.swing.JTable();
        jLabel16 = new javax.swing.JLabel();
        LBL_total = new javax.swing.JLabel();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        LBL_solicitud2 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        lblFecha2 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable7 = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        JTA_observaciones = new javax.swing.JTextArea();
        JB_cancel = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        LBL_estado = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("PRESUPUESTO");

        jLabel9.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel9.setText("RACAD AUTOMOTRIZ - PRESUPUESTAR TRABAJO");

        jLabel1.setText("Codigo Solicitud :");

        jLabel6.setText("Fecha Actual :");

        jLabel12.setText("Vehiculo:");

        jTable1.setModel(modeloTabla);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(jTable1);

        jTable2.setModel(modeloTabla2);
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(jTable2);

        jLabel5.setText("Clientes:");

        jButton1.setText("Ingresar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton7.setText("Modificar");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8.setText("Ingresar");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton9.setText("Modificar");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel9)
                .addGap(0, 216, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(LBL_solicitud, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel12)
                    .addComponent(jLabel5)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                            .addComponent(jButton1)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton7))
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 405, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                            .addComponent(jButton8)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton9))
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 405, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel9)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LBL_solicitud, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton7))
                .addGap(61, 61, 61)
                .addComponent(jLabel12)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton8)
                    .addComponent(jButton9))
                .addContainerGap(65, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Seleccionar Vehiculo", jPanel1);

        jLabel10.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel10.setText("RACAD AUTOMOTRIZ - PRESUPUESTAR TRABAJO");

        jLabel2.setText("Codigo Solicitud :");

        jLabel7.setText("Fecha Actual :");

        jLabel14.setText("Insumos:");

        jTable3.setModel(modeloTabla3);
        jTable3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable3MouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(jTable3);

        jLabel15.setText("Servicios");

        jButton3.setText("Añadir");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Quitar");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jTable4.setModel(modeloTabla4);
        jTable4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable4MouseClicked(evt);
            }
        });
        jScrollPane8.setViewportView(jTable4);

        jTable5.setModel(modeloTabla5);
        jTable5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable5MouseClicked(evt);
            }
        });
        jScrollPane9.setViewportView(jTable5);

        jButton5.setText("Añadir");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setText("Quitar");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jTable6.setModel(modeloTabla6);
        jTable6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable6MouseClicked(evt);
            }
        });
        jScrollPane10.setViewportView(jTable6);

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel16.setText("Total:");

        LBL_total.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jButton10.setText("+");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jButton11.setText("-");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel10)
                .addGap(0, 216, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(LBL_solicitud1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblFecha1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel14)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton3)
                            .addComponent(jButton4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel15)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton5)
                            .addComponent(jButton6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addGap(18, 18, 18)
                        .addComponent(LBL_total)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel10)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblFecha1, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LBL_solicitud1, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel14)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addComponent(jButton3)
                        .addGap(18, 18, 18)
                        .addComponent(jButton4))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(67, 67, 67)
                        .addComponent(jButton10)
                        .addGap(18, 18, 18)
                        .addComponent(jButton11))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jLabel15)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton5)
                        .addGap(18, 18, 18)
                        .addComponent(jButton6)
                        .addGap(140, 140, 140))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel16)
                            .addComponent(LBL_total))
                        .addGap(34, 34, 34))))
        );

        jTabbedPane1.addTab("Seleccionar Servicios e Insumos", jPanel2);

        jLabel11.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel11.setText("RACAD AUTOMOTRIZ - PRESUPUESTAR TRABAJO");

        jLabel3.setText("Codigo Solicitud :");

        jLabel8.setText("Fecha Actual :");

        jLabel13.setText("Trabajador :");

        jTable7.setModel(modeloTabla7);
        jTable7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable7MouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(jTable7);

        jLabel4.setText("Observaciónes :");

        JTA_observaciones.setColumns(20);
        JTA_observaciones.setRows(5);
        jScrollPane1.setViewportView(JTA_observaciones);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel11)
                .addGap(0, 216, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(LBL_solicitud2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblFecha2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel13)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 405, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel11)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblFecha2, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LBL_solicitud2, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(jLabel13)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(103, 103, 103))
        );

        jTabbedPane1.addTab("Seleccionar Responsale y otros", jPanel3);

        JB_cancel.setText("Volver");
        JB_cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JB_cancelActionPerformed(evt);
            }
        });

        jButton2.setText("Ingresar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton2)
                        .addGap(18, 18, 18)
                        .addComponent(LBL_estado, javax.swing.GroupLayout.PREFERRED_SIZE, 474, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(JB_cancel)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 593, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton2)
                        .addComponent(JB_cancel))
                    .addComponent(LBL_estado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        limpiaTabla();
        String rut = jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString();
        try {
            sentencia = (com.mysql.jdbc.Statement) cn.createStatement();
            ResultSet lista = sentencia.executeQuery("SELECT v.patente,v.año, v.kms,v.vin, v.color, ma.nombre, mo.nombre "
                    + "FROM vehiculo v INNER JOIN cliente c ON v.rut_cliente = '" + rut + "'"
                    + "LEFT JOIN marca ma ON v.id_marca = ma.id_marca "
                    + "LEFT JOIN modelo mo ON v.id_modelo = mo.id_modelo ");
            Object datos[] = new Object[8];
            while (lista.next()) {
                for (int i = 0; i < 7; i++) {
                    datos[i] = lista.getObject(i + 1);
                }

            }
            modeloTabla2.addRow(datos);
        } catch (Exception e) {
            msj = "No se pudo llenar tabla";
            LBL_estado.setText(msj);
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void JB_cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JB_cancelActionPerformed
        this.dispose();
    }//GEN-LAST:event_JB_cancelActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if (verificar() == 0) {
            String pat = jTable2.getValueAt(jTable2.getSelectedRow(), 0).toString();
            String rut = jTable7.getValueAt(jTable7.getSelectedRow(), 0).toString();
            int dis;
            int fol = 0;
            String fech = lblFecha.getText();
            String obser = JTA_observaciones.getText().toUpperCase().trim();
            int cod = 0;

            int total = 0;
            int columna = jTable4.getRowCount(), columna2 = jTable6.getRowCount();
            for (int i = 0; i < columna; i++) {
                total = total + Integer.parseInt(jTable4.getValueAt(i, 2).toString()) * Integer.parseInt(jTable4.getValueAt(i, 3).toString());
            }

            for (int i = 0; i < columna2; i++) {
                total = total + Integer.parseInt(jTable6.getValueAt(i, 2).toString());
            }

            LBL_total.setText(String.valueOf(total));
            int mod = 0;
            int mar = 0;
            String est = "PRESUPUESTO";
            try {
                sentencia = (com.mysql.jdbc.Statement) cn.createStatement();
                ResultSet rs = sentencia.executeQuery("SELECT MAX(cod_solicitud) as cod_solicitud FROM solicitud_servicio");
                while (rs.next()) {
                    cod = rs.getInt("cod_solicitud");
                }
                cod++;
            } catch (SQLException f) {
                msj = "Error con Codigo";
            }

            //la fecha orden y entrega so "place-holders" que se quedarán para que funcione el programa
            //la fecha presupuesto hay que hacer que sea la actual si
            //sub-total se dejó en cero, puesto a que esto es un presupuesto
            String sql = "INSERT INTO solicitud_servicio(cod_solicitud,observaciones,subtotal,total,fecha_presupuesto,estado_solicitud,patente,rut_trabajador) "
                    + "VALUES(" + cod + ",'" + obser + "'," + total + "," + total + ",'" + fech + "','" + est + "','" + pat + "','" + rut + "')";

            try {
                sentencia.executeUpdate(sql);
                msj = "Datos Guardados";
                LBL_estado.setText(msj);
                dis = 1;
            } catch (SQLException e) {
                msj = "PRESUPUESTO no Ingresado en bd";
                LBL_estado.setText(msj);
                dis = 0;
            }
            
            int i = 1,j = 1,item,venta,cantidad,servicio,precio;
            while (i < columna &&  j < columna2) {
                item = Integer.parseInt(jTable4.getValueAt(i, 0).toString());
                venta = Integer.parseInt(jTable4.getValueAt(i, 2).toString());
                cantidad = Integer.parseInt(jTable4.getValueAt(i, 3).toString());
                servicio = Integer.parseInt(jTable6.getValueAt(j, 0).toString());
                precio = Integer.parseInt(jTable6.getValueAt(j, 2).toString());

                String sql2 = "INSERT INTO detalle_solicitud(cod_solicitud,cod_item,id_servicio,precio,valor_venta,cantidad,observaciones,fecha_presupueto,rut_trabajador) "
                        + "VALUES(" + cod + "," + item + "," + servicio + "," + precio + "," + venta + "," + cantidad + ",'" + obser + "','" + fech + "','" + rut + "')";

                try {
                    sentencia.executeUpdate(sql2);
                    msj = "Datos Guardados";
                    LBL_estado.setText(msj);
                    dis = 1;
                } catch (SQLException e) {
                    msj = "DETALLE no Ingresado en bd";
                    LBL_estado.setText(msj);
                    dis = 0;
                }
                i++;
                j++;
            }

            if (dis == 1) {
                clean();
                limpiaTabla();
                llenarLabels();
                fechaActual();

            }
        } else {
            msj = "PRESUPUESTO no Ingresado";
            LBL_estado.setText(msj);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTable7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable7MouseClicked

    }//GEN-LAST:event_jTable7MouseClicked

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable2MouseClicked

    private void jTable3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable3MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable3MouseClicked

    private void jTable4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable4MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable4MouseClicked

    private void jTable5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable5MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable5MouseClicked

    private void jTable6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable6MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable6MouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

        int cont = 0;
        if (jTable3.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(null,
                    "Error, Seleccione insumo!", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            cont++;
        }

        if (cont == 0) {
            int columnas = jTable4.getRowCount();
            int cod = Integer.parseInt(jTable3.getValueAt(jTable3.getSelectedRow(), 0).toString().trim());
            int cod2 = 0;

            Object fila[] = new Object[4];
            fila[0] = jTable3.getValueAt(jTable3.getSelectedRow(), 0);
            fila[1] = jTable3.getValueAt(jTable3.getSelectedRow(), 1);
            fila[2] = jTable3.getValueAt(jTable3.getSelectedRow(), 2);
            fila[3] = 1;

            if (columnas != 0) {
                for (int i = 0; i < columnas; i++) {
                    cod2 = Integer.parseInt(jTable4.getValueAt(i, 0).toString().trim());
                    if (cod == cod2) {
                        cont++;
                    }
                }
            }

            if (cont == 0) {
                modeloTabla4.addRow(fila);
            } else {
                JOptionPane.showMessageDialog(null,
                        "Error, Ya Selecciono este insumo!", "ERROR",
                        JOptionPane.ERROR_MESSAGE);
            }

        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Ingresar_cliente a = new Ingresar_cliente();
        a.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        Ingresar_vehiculo a = new Ingresar_vehiculo();
        a.setVisible(true);
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        Modificar_vehiculo a = new Modificar_vehiculo();
        a.setVisible(true);
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        Modificar_cliente a = new Modificar_cliente();
        a.setVisible(true);
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        int cont = 0;
        if (jTable4.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(null,
                    "Error, Seleccione insumo!", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            cont++;
        }
        if (cont == 0) {

            int base = Integer.parseInt(jTable4.getValueAt(jTable4.getSelectedRow(), 3).toString().trim());
            int valor = base + 1;
            jTable4.setValueAt(valor, jTable4.getSelectedRow(), 3);
        }
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        int cont = 0;
        if (jTable4.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(null,
                    "Error, Seleccione insumo!", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            cont++;
        }
        if (cont == 0) {

            int base = Integer.parseInt(jTable4.getValueAt(jTable4.getSelectedRow(), 3).toString().trim());
            int valor = base - 1;
            if (valor < 1) {
                JOptionPane.showMessageDialog(null,
                        "Error, No se puede seguir disminuyendo insumo!", "ERROR",
                        JOptionPane.ERROR_MESSAGE);
                cont++;
            } else {
                jTable4.setValueAt(valor, jTable4.getSelectedRow(), 3);
            }
        }
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        int cont = 0;
        if (jTable4.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(null,
                    "Error, Seleccione insumo!", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            cont++;
        }

        if (cont == 0) {
            int fila = jTable4.getSelectedRow();
            modeloTabla4.removeRow(fila);
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        int cont = 0;
        if (jTable5.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(null,
                    "Error, Seleccione SERVICIO!", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            cont++;
        }

        if (cont == 0) {
            int columnas = jTable6.getRowCount();
            int cod = Integer.parseInt(jTable5.getValueAt(jTable5.getSelectedRow(), 0).toString().trim());
            int cod2 = 0;

            Object fila[] = new Object[3];
            fila[0] = jTable5.getValueAt(jTable5.getSelectedRow(), 0);
            fila[1] = jTable5.getValueAt(jTable5.getSelectedRow(), 1);
            fila[2] = jTable5.getValueAt(jTable5.getSelectedRow(), 2);

            if (columnas != 0) {
                for (int i = 0; i < columnas; i++) {
                    cod2 = Integer.parseInt(jTable6.getValueAt(i, 0).toString().trim());
                    if (cod == cod2) {
                        cont++;
                    }
                }
            }

            if (cont == 0) {
                modeloTabla6.addRow(fila);
            } else {
                JOptionPane.showMessageDialog(null,
                        "Error, Ya Selecciono este SERVICIO!", "ERROR",
                        JOptionPane.ERROR_MESSAGE);
            }

        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        int cont = 0;
        if (jTable6.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(null,
                    "Error, Seleccione SERVICIO!", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            cont++;
        }

        if (cont == 0) {
            int fila = jTable6.getSelectedRow();
            modeloTabla6.removeRow(fila);
        }
    }//GEN-LAST:event_jButton6ActionPerformed

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
            java.util.logging.Logger.getLogger(Presupuestar_trabajo2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Presupuestar_trabajo2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Presupuestar_trabajo2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Presupuestar_trabajo2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Presupuestar_trabajo2().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton JB_cancel;
    private javax.swing.JTextArea JTA_observaciones;
    private javax.swing.JLabel LBL_estado;
    private javax.swing.JLabel LBL_solicitud;
    private javax.swing.JLabel LBL_solicitud1;
    private javax.swing.JLabel LBL_solicitud2;
    private javax.swing.JLabel LBL_total;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTable jTable4;
    private javax.swing.JTable jTable5;
    private javax.swing.JTable jTable6;
    private javax.swing.JTable jTable7;
    private javax.swing.JLabel lblFecha;
    private javax.swing.JLabel lblFecha1;
    private javax.swing.JLabel lblFecha2;
    // End of variables declaration//GEN-END:variables
}
