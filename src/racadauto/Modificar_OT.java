package racadauto;

import Conexion.Conexion;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class Modificar_OT extends javax.swing.JFrame {

    private Statement sentencia;
    Conexion con = new Conexion();
    Connection cn = (Connection) con.getConnection();
    DefaultTableModel modeloTablaPresupuesto;
    DefaultTableModel modeloTablaServicio;
    DefaultTableModel modeloTablaServicioaux;
    DefaultTableModel modeloTablaInsumo;
    DefaultTableModel modeloTablaInsumoaux;
    DefaultTableModel modeloTablaTrabajador;
    private TableRowSorter trsfiltro;
    String filtro;

    public Modificar_OT() {
        modeloTablaPresupuesto = new DefaultTableModel(null, getColumnasPresupuesto());
        modeloTablaServicio = new DefaultTableModel(null, getColumnasServicio());
        modeloTablaServicioaux = new DefaultTableModel(null, getColumnasServicioaux());
        modeloTablaInsumo = new DefaultTableModel(null, getColumnasInsumo());
        modeloTablaInsumoaux = new DefaultTableModel(null, getColumnasInsumoaux());
        modeloTablaTrabajador = new DefaultTableModel(null, getColumnasTrabajador());
        setFilasPresupuesto();
        setFilasInsumo();
        setFilasServicio();
        setFilasTrabajador();
        initComponents();
        fechaActual();
        ocultarFila();
    }

    private String[] getColumnasPresupuesto() {
        String columna[] = new String[]{"CODIGO", "SUBTOTAL", "TOTAL", "FECHA ORDEN", "PATENTE", "RUT TRABAJADOR"};
        return columna;
    }

    private String[] getColumnasInsumo() {
        String columna[] = new String[]{"CODIGO", "NOMBRE", "VALOR VENTA"};
        return columna;
    }

    private String[] getColumnasInsumoaux() {
        String columna[] = new String[]{"CODIGO", "NOMBRE", "VALOR VENTA", "CANTIDAD", "CODIGO SERVICIO"};
        return columna;
    }
    
    public void ocultarFila(){
        tablaInsumoaux.getColumnModel().getColumn(4).setMaxWidth(0);
        tablaInsumoaux.getColumnModel().getColumn(4).setMinWidth(0);
        tablaInsumoaux.getColumnModel().getColumn(4).setPreferredWidth(0);
    }

    private String[] getColumnasServicio() {
        String columna[] = new String[]{"ID", "COMPONENTE", "PRECIO"};
        return columna;
    }

    private String[] getColumnasServicioaux() {
        String columna[] = new String[]{"ID", "COMPONENTE", "PRECIO", "ESTADO"};
        return columna;
    }

    private String[] getColumnasTrabajador() {
        String columna[] = new String[]{"RUT", "NOMBRE", "APELLIDO PATERNO", "APELLIDO MATERNO", "CARGO"};
        return columna;
    }

    public void filtroComponente() {

        filtro = JT_componente.getText().toUpperCase();
        int columna = 1;
        trsfiltro.setRowFilter(RowFilter.regexFilter(JT_componente.getText().toUpperCase(), columna));
    }

    public void filtroInsumo() {

        filtro = JT_insumo.getText().toUpperCase();
        int columna = 1;
        trsfiltro.setRowFilter(RowFilter.regexFilter(JT_insumo.getText().toUpperCase(), columna));

    }

    public void filtroCodigo() {

        filtro = JT_codigo.getText().toUpperCase();
        int columna = 0;
        trsfiltro.setRowFilter(RowFilter.regexFilter(JT_codigo.getText().toUpperCase(), columna));
    }

    public void filtroSubtotal() {

        filtro = JT_subtotal.getText().toUpperCase();
        int columna = 1;
        trsfiltro.setRowFilter(RowFilter.regexFilter(JT_subtotal.getText().toUpperCase(), columna));
    }

    public void filtroTotal() {

        filtro = JT_total.getText().toUpperCase();
        int columna = 2;
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

    public void filtroRutTrabajador() {

        filtro = JT_ruttrabajador.getText().toUpperCase();
        int columna = 0;
        trsfiltro.setRowFilter(RowFilter.regexFilter(JT_ruttrabajador.getText().toUpperCase(), columna));
    }

    public void filtroNombreTrabajador() {

        filtro = JT_nombretrabajador.getText().toUpperCase();
        int columna = 1;
        trsfiltro.setRowFilter(RowFilter.regexFilter(JT_nombretrabajador.getText().toUpperCase(), columna));
    }

    public void filtroPaternoTrabajador() {

        filtro = JT_paternotrabajador.getText().toUpperCase();
        int columna = 2;
        trsfiltro.setRowFilter(RowFilter.regexFilter(JT_paternotrabajador.getText().toUpperCase(), columna));
    }

    public void filtroMaternoTrabajador() {

        filtro = JT_maternotrabajador.getText().toUpperCase();
        int columna = 3;
        trsfiltro.setRowFilter(RowFilter.regexFilter(JT_maternotrabajador.getText().toUpperCase(), columna));
    }

    public void filtroCargo() {

        filtro = JT_cargo.getText().toUpperCase();
        int columna = 4;
        trsfiltro.setRowFilter(RowFilter.regexFilter(JT_cargo.getText().toUpperCase(), columna));
    }

    private void setFilasPresupuesto() {
        try {
            sentencia = (com.mysql.jdbc.Statement) cn.createStatement();
            ResultSet lista = sentencia.executeQuery("SELECT cod_solicitud, subtotal, total, fecha_orden, patente, rut_trabajador "
                    + "FROM solicitud_servicio "
                    + "WHERE estado_solicitud = 'OT'");
            Object datos[] = new Object[6];
            while (lista.next()) {
                for (int i = 0; i < 6; i++) {
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

    private void setFilasTrabajador() {
        try {
            sentencia = (com.mysql.jdbc.Statement) cn.createStatement();
            ResultSet lista = sentencia.executeQuery("SELECT t.rut_trabajador, t.nombre, t.ape_paterno, t.ape_materno, c.nombre "
                    + "FROM trabajador t, cargo c "
                    + "WHERE t.id_cargo = c.id_cargo");
            Object datos[] = new Object[5];
            while (lista.next()) {
                for (int i = 0; i < 5; i++) {
                    datos[i] = lista.getObject(i + 1);
                }
                modeloTablaTrabajador.addRow(datos);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "Problemas con la base de datos, no se pudo llenar la tabla", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void setFilasInsumo() {
        try {
            sentencia = (com.mysql.jdbc.Statement) cn.createStatement();
            ResultSet lista = sentencia.executeQuery("SELECT cod_item, nombre, valor_venta "
                    + "FROM inventario "
                    + "WHERE estado = 1");
            Object datos[] = new Object[3];
            while (lista.next()) {
                for (int i = 0; i < 3; i++) {
                    datos[i] = lista.getObject(i + 1);
                }
                modeloTablaInsumo.addRow(datos);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "Problemas con la base de datos, no se pudo llenar la tabla", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void setFilasInsumoaux() {

        int presupuesto = Integer.parseInt(tablaPresupuesto.getValueAt(tablaPresupuesto.getSelectedRow(), 0).toString());

        try {
            sentencia = (com.mysql.jdbc.Statement) cn.createStatement();
            ResultSet lista = sentencia.executeQuery("SELECT d.cod_item, i.nombre, i.valor_venta, d.cantidad, d.id_servicio "
                    + "FROM inventario i, detalle_insumo d "
                    + "WHERE d.cod_solicitud = " + presupuesto + " AND d.cod_item = i.cod_item");
            Object datos[] = new Object[5];
            while (lista.next()) {
                for (int i = 0; i < 5; i++) {
                    datos[i] = lista.getObject(i + 1);
                }
                modeloTablaInsumoaux.addRow(datos);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "Problemas con la base de datos, no se pudo llenar la tabla", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void setFilasServicio() {
        try {
            sentencia = (com.mysql.jdbc.Statement) cn.createStatement();
            ResultSet lista = sentencia.executeQuery("SELECT id_servicio, componente, precio "
                    + "FROM servicio ");
            Object datos[] = new Object[4];
            while (lista.next()) {
                for (int i = 0; i < 3; i++) {
                    datos[i] = lista.getObject(i + 1);
                }
                modeloTablaServicio.addRow(datos);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "Problemas con la base de datos, no se pudo llenar la tabla", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void setFilasServicioaux() {

        int presupuesto = Integer.parseInt(tablaPresupuesto.getValueAt(tablaPresupuesto.getSelectedRow(), 0).toString());

        try {
            sentencia = (com.mysql.jdbc.Statement) cn.createStatement();
            ResultSet lista = sentencia.executeQuery("SELECT d.id_servicio, s.componente, s.precio, d.estado_seguimiento "
                    + "FROM servicio s, detalle_solicitud d "
                    + "WHERE d.cod_solicitud = " + presupuesto + " AND d.id_servicio = s.id_servicio");
            Object datos[] = new Object[4];
            while (lista.next()) {
                for (int i = 0; i < 4; i++) {
                    datos[i] = lista.getObject(i + 1);
                }
                modeloTablaServicioaux.addRow(datos);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "Problemas con la base de datos, no se pudo llenar la tabla", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
        }

    }

    public void jcomboboxjtable() {
        String[] DATA = {"EN ESPERA", "EN PROCESO", "TERMINADO"};
        JComboBox comboBox = new JComboBox(DATA);
        comboBox.setSelectedIndex(0);
        DefaultCellEditor defaultCellEditor = new DefaultCellEditor(comboBox);
        tablaServicioaux.getColumnModel().getColumn(3).setCellEditor(defaultCellEditor);
    }

    private void setObservaciones() {

        int presupuesto = Integer.parseInt(tablaPresupuesto.getValueAt(tablaPresupuesto.getSelectedRow(), 0).toString());

        try {
            sentencia = (com.mysql.jdbc.Statement) cn.createStatement();
            ResultSet rs = sentencia.executeQuery("SELECT observaciones "
                    + "FROM solicitud_servicio "
                    + "WHERE cod_solicitud = " + presupuesto + "");
            while (rs.next()) {
                JTA_observaciones.setText(rs.getString("observaciones"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "Problemas con la base de datos, no se pudo llenar las observaciones", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
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

    void limpiaTablaInsumoaux() {
        do {
            modeloTablaInsumoaux.setRowCount(0);
        } while (modeloTablaInsumoaux.getRowCount() != 0);
    }

    void limpiaTablaServicioaux() {
        do {
            modeloTablaServicioaux.setRowCount(0);
        } while (modeloTablaServicioaux.getRowCount() != 0);
    }

    public int verificar() {

        int cont = 0;

        String obser = JTA_observaciones.getText().toUpperCase().trim();
        java.util.Date fechaentrega = JDC_fechaEntrega.getDate();
        java.util.Date fechaActual = new java.util.Date();
        long fecha = fechaActual.getTime();
        java.sql.Date sqlDate = new java.sql.Date(fecha);
        int columnaservicio = tablaServicioaux.getRowCount();

        for (int i = 0; i < columnaservicio; i++) {
            if (tablaServicioaux.getValueAt(i, 3).toString().equals("")) {
                JOptionPane.showMessageDialog(null,
                        "Indique estado de seguimiento del servicio ", "ERROR",
                        JOptionPane.ERROR_MESSAGE);
                cont++;
            }
        }

        if (fechaentrega == null) {
            JOptionPane.showMessageDialog(null,
                    "Indique fecha de entrega", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            cont++;
        } else if (fechaentrega.before(sqlDate)) {
            JOptionPane.showMessageDialog(null,
                    "Fecha no puede ser menor a la actual", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            cont++;
        }

        if (obser.equals("")) {
            JOptionPane.showMessageDialog(null,
                    "Dejó las observaciones vacía", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            cont++;
        }

        if (obser.length() > 140) {
            JOptionPane.showMessageDialog(null,
                    "Observacion no puede exceder los 140 caracteres", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            cont++;
        }

        if (tablaPresupuesto.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(null,
                    "Seleccione presupuesto!", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            cont++;
        }

        if (tablaTrabajador.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(null,
                    "Seleccione trabajador!", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            cont++;
        }

        if (tablaInsumoaux.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null,
                    "Añada insumo!", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            cont++;
        }

        if (tablaServicioaux.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null,
                    "Añada servicio!", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            cont++;
        }

        return cont;
    }

    public void clean() {
        JTA_observaciones.setText("");
        lblFecha.setText("");
        lblFecha1.setText("");
        lblFecha2.setText("");
        modeloTablaServicioaux.setRowCount(0);
        modeloTablaInsumoaux.setRowCount(0);
        modeloTablaPresupuesto.setRowCount(0);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lblFecha = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tablaPresupuesto = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        JT_codigo = new javax.swing.JTextField();
        JT_patente = new javax.swing.JTextField();
        JT_trabajador = new javax.swing.JTextField();
        JT_total = new javax.swing.JTextField();
        JT_subtotal = new javax.swing.JTextField();
        JDC_fechaEntrega = new com.toedter.calendar.JDateChooser();
        jLabel12 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lblFecha1 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tablaServicio = new javax.swing.JTable();
        jScrollPane9 = new javax.swing.JScrollPane();
        tablaInsumos = new javax.swing.JTable();
        JT_componente = new javax.swing.JTextField();
        JT_insumo = new javax.swing.JTextField();
        btnAddServicio = new javax.swing.JButton();
        btnQuitarServicio = new javax.swing.JButton();
        btnAddInsumo = new javax.swing.JButton();
        btnQuitarInsumo = new javax.swing.JButton();
        jScrollPane8 = new javax.swing.JScrollPane();
        tablaServicioaux = new javax.swing.JTable();
        jScrollPane10 = new javax.swing.JScrollPane();
        tablaInsumoaux = new javax.swing.JTable();
        btnAddCant = new javax.swing.JButton();
        btnRemoveCant = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        lblFecha2 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tablaTrabajador = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        JTA_observaciones = new javax.swing.JTextArea();
        JT_ruttrabajador = new javax.swing.JTextField();
        JT_nombretrabajador = new javax.swing.JTextField();
        JT_paternotrabajador = new javax.swing.JTextField();
        JT_maternotrabajador = new javax.swing.JTextField();
        JT_cargo = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        JB_cancel = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("MODIFICAR ORDEN DE TRABAJO");

        jLabel9.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel9.setText("RACAD AUTOMOTRIZ - MODIFICAR ORDEN DE TRABAJO");
        jLabel9.setToolTipText("");

        jLabel6.setText("Fecha Actual :");

        tablaPresupuesto.setModel(modeloTablaPresupuesto);
        tablaPresupuesto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaPresupuestoMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tablaPresupuesto);

        jLabel5.setText("ORDENES DE TRABAJO");

        jButton1.setText("Nuevo");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        JT_codigo.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        JT_codigo.setForeground(new java.awt.Color(153, 153, 153));
        JT_codigo.setText("Buscar por codigo");
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
        JT_codigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                JT_codigoKeyTyped(evt);
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

        JDC_fechaEntrega.setDateFormatString("yyyy-MM-dd");

        jLabel12.setText("Seleccionar Fecha de Entrega:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addComponent(lblFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton1)
                    .addComponent(jLabel9)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(JT_codigo, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JT_subtotal, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JT_total, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(122, 122, 122)
                        .addComponent(JT_patente, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JT_trabajador, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 715, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addGap(18, 18, 18)
                        .addComponent(JDC_fechaEntrega, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(282, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblFecha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JT_codigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JT_patente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JT_trabajador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JT_total, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JT_subtotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addGap(43, 43, 43)
                        .addComponent(jLabel12))
                    .addComponent(JDC_fechaEntrega, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(174, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Seleccionar OT", jPanel1);

        jLabel10.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel10.setText("RACAD AUTOMOTRIZ - MODIFICAR ORDEN DE TRABAJO");

        jLabel7.setText("Fecha Actual :");

        jLabel15.setText("Servicios");

        jLabel14.setText("Insumos:");

        tablaServicio.setModel(modeloTablaServicio);
        tablaServicio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaServicioMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(tablaServicio);

        tablaInsumos.setModel(modeloTablaInsumo);
        tablaInsumos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaInsumosMouseClicked(evt);
            }
        });
        jScrollPane9.setViewportView(tablaInsumos);

        JT_componente.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        JT_componente.setForeground(new java.awt.Color(153, 153, 153));
        JT_componente.setText("Buscar por componente");
        JT_componente.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                JT_componenteFocusLost(evt);
            }
        });
        JT_componente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JT_componenteMouseClicked(evt);
            }
        });
        JT_componente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                JT_componenteKeyTyped(evt);
            }
        });

        JT_insumo.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        JT_insumo.setForeground(new java.awt.Color(153, 153, 153));
        JT_insumo.setText("Buscar por nombre");
        JT_insumo.setToolTipText("");
        JT_insumo.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        JT_insumo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                JT_insumoFocusLost(evt);
            }
        });
        JT_insumo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JT_insumoMouseClicked(evt);
            }
        });
        JT_insumo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JT_insumoActionPerformed(evt);
            }
        });
        JT_insumo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                JT_insumoKeyTyped(evt);
            }
        });

        btnAddServicio.setText("Añadir");
        btnAddServicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddServicioActionPerformed(evt);
            }
        });

        btnQuitarServicio.setText("Quitar");
        btnQuitarServicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuitarServicioActionPerformed(evt);
            }
        });

        btnAddInsumo.setText("Añadir");
        btnAddInsumo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddInsumoActionPerformed(evt);
            }
        });

        btnQuitarInsumo.setText("Quitar");
        btnQuitarInsumo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuitarInsumoActionPerformed(evt);
            }
        });

        tablaServicioaux.setModel(modeloTablaServicioaux);
        tablaServicioaux.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaServicioauxMouseClicked(evt);
            }
        });
        jScrollPane8.setViewportView(tablaServicioaux);

        tablaInsumoaux.setModel(modeloTablaInsumoaux);
        tablaInsumoaux.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaInsumoauxMouseClicked(evt);
            }
        });
        jScrollPane10.setViewportView(tablaInsumoaux);

        btnAddCant.setText("+");
        btnAddCant.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddCantActionPerformed(evt);
            }
        });

        btnRemoveCant.setText("-");
        btnRemoveCant.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveCantActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(112, 112, 112)
                .addComponent(btnAddServicio)
                .addGap(18, 18, 18)
                .addComponent(btnQuitarServicio)
                .addGap(397, 397, 397)
                .addComponent(btnAddInsumo)
                .addGap(18, 18, 18)
                .addComponent(btnQuitarInsumo)
                .addContainerGap(210, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addGap(776, 776, 776))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 367, Short.MAX_VALUE)
                            .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addGap(48, 117, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 421, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel14)
                                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 419, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnAddCant)
                            .addComponent(btnRemoveCant, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(45, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(18, 18, 18)
                                .addComponent(lblFecha1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(125, 125, 125)
                .addComponent(JT_componente, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(JT_insumo, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(237, 237, 237))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblFecha1, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15)
                    .addComponent(jLabel14))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(JT_componente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(JT_insumo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnAddServicio)
                            .addComponent(btnQuitarServicio))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnAddCant)
                        .addGap(18, 18, 18)
                        .addComponent(btnRemoveCant)
                        .addGap(81, 81, 81))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnQuitarInsumo)
                            .addComponent(btnAddInsumo))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(28, Short.MAX_VALUE))))
        );

        jTabbedPane1.addTab("Seleccionar Servicios e Insumos", jPanel2);

        jLabel11.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel11.setText("RACAD AUTOMOTRIZ - MODIFICAR ORDEN DE TRABAJO");

        jLabel8.setText("Fecha Actual :");

        jLabel13.setText("Trabajador :");

        tablaTrabajador.setModel(modeloTablaTrabajador);
        tablaTrabajador.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaTrabajadorMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tablaTrabajador);

        jLabel4.setText("Observaciónes :");

        JTA_observaciones.setColumns(20);
        JTA_observaciones.setRows(5);
        jScrollPane1.setViewportView(JTA_observaciones);

        JT_ruttrabajador.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        JT_ruttrabajador.setForeground(new java.awt.Color(153, 153, 153));
        JT_ruttrabajador.setText("Buscar por rut");
        JT_ruttrabajador.setToolTipText("");
        JT_ruttrabajador.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                JT_ruttrabajadorFocusLost(evt);
            }
        });
        JT_ruttrabajador.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JT_ruttrabajadorMouseClicked(evt);
            }
        });
        JT_ruttrabajador.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                JT_ruttrabajadorKeyTyped(evt);
            }
        });

        JT_nombretrabajador.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        JT_nombretrabajador.setForeground(new java.awt.Color(153, 153, 153));
        JT_nombretrabajador.setText("Buscar por nombre");
        JT_nombretrabajador.setToolTipText("");
        JT_nombretrabajador.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        JT_nombretrabajador.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                JT_nombretrabajadorFocusLost(evt);
            }
        });
        JT_nombretrabajador.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JT_nombretrabajadorMouseClicked(evt);
            }
        });
        JT_nombretrabajador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JT_nombretrabajadorActionPerformed(evt);
            }
        });
        JT_nombretrabajador.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                JT_nombretrabajadorKeyTyped(evt);
            }
        });

        JT_paternotrabajador.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        JT_paternotrabajador.setForeground(new java.awt.Color(153, 153, 153));
        JT_paternotrabajador.setText("Buscar por paterno");
        JT_paternotrabajador.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                JT_paternotrabajadorFocusLost(evt);
            }
        });
        JT_paternotrabajador.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JT_paternotrabajadorMouseClicked(evt);
            }
        });
        JT_paternotrabajador.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                JT_paternotrabajadorKeyTyped(evt);
            }
        });

        JT_maternotrabajador.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        JT_maternotrabajador.setForeground(new java.awt.Color(153, 153, 153));
        JT_maternotrabajador.setText("Buscar por materno");
        JT_maternotrabajador.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                JT_maternotrabajadorFocusLost(evt);
            }
        });
        JT_maternotrabajador.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JT_maternotrabajadorMouseClicked(evt);
            }
        });
        JT_maternotrabajador.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                JT_maternotrabajadorKeyTyped(evt);
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

        jButton4.setText("Modificar");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton3.setText("Nuevo");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addComponent(jLabel8)
                            .addGap(18, 18, 18)
                            .addComponent(lblFecha2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jLabel13)
                        .addComponent(jLabel4)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addComponent(jButton3)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton4))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 530, Short.MAX_VALUE))
                    .addComponent(jLabel11)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addComponent(JT_ruttrabajador, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(JT_nombretrabajador, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(JT_paternotrabajador, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(JT_maternotrabajador, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(JT_cargo))
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 629, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(368, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblFecha2, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel13)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JT_ruttrabajador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JT_nombretrabajador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JT_paternotrabajador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JT_maternotrabajador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JT_cargo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton3)
                    .addComponent(jButton4))
                .addContainerGap(46, Short.MAX_VALUE))
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
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(JB_cancel)
                .addContainerGap())
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(JB_cancel))
                .addContainerGap())
        );

        jTabbedPane1.getAccessibleContext().setAccessibleName("Seleccionar OT");

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void tablaPresupuestoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaPresupuestoMouseClicked

        limpiaTablaServicioaux();
        limpiaTablaInsumoaux();
        JTA_observaciones.setText("");
        jcomboboxjtable();
        setFilasServicioaux();
        setFilasInsumoaux();
        setObservaciones();

    }//GEN-LAST:event_tablaPresupuestoMouseClicked

    private void JB_cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JB_cancelActionPerformed
        this.dispose();
    }//GEN-LAST:event_JB_cancelActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        if (verificar() == 0) {

            int solicitud = Integer.parseInt(tablaPresupuesto.getValueAt(tablaPresupuesto.getSelectedRow(), 0).toString());
            String rut = tablaTrabajador.getValueAt(tablaTrabajador.getSelectedRow(), 0).toString();
            String fechaorden = lblFecha.getText().trim();

            java.util.Date date = JDC_fechaEntrega.getDate();
            long d = date.getTime();
            java.sql.Date fechaentrega = new java.sql.Date(d);

            int total = 0, subtotal = 0, cont = 0;

            String observaciones = JTA_observaciones.getText().toUpperCase().trim();

            int columnaservicio = tablaServicioaux.getRowCount(), columnainsumo = tablaInsumoaux.getRowCount();

            for (int i = 0; i < columnainsumo; i++) {
                subtotal = subtotal + Integer.parseInt(tablaInsumoaux.getValueAt(i, 2).toString()) * Integer.parseInt(tablaInsumoaux.getValueAt(i, 3).toString());
            }

            for (int i = 0; i < columnaservicio; i++) {
                subtotal = subtotal + Integer.parseInt(tablaServicioaux.getValueAt(i, 2).toString());
            }

            total = (int) (subtotal * 1.19);

            String estado = "OT";

            String sql = "UPDATE solicitud_servicio "
                    + "SET observaciones ='" + observaciones + "',"
                    + "subtotal = '" + subtotal + "',"
                    + "total = '" + total + "',"
                    + "fecha_orden = '" + fechaorden + "',"
                    + "fecha_entrega = '" + fechaentrega + "',"
                    + "estado_solicitud = '" + estado + "',"
                    + "rut_trabajador = '" + rut + "'"
                    + "WHERE cod_solicitud = '" + solicitud + "'";

            try {
                sentencia.executeUpdate(sql);
            } catch (SQLException e) {
                cont++;
            }

            for (int i = 0; i < columnaservicio; i++) {
                try {
                    int servicio = Integer.parseInt(tablaServicioaux.getValueAt(i, 0).toString()), servicio2 = 0;
                    int precio = Integer.parseInt(tablaServicioaux.getValueAt(i, 2).toString());
                    String fechapresupuesto = tablaPresupuesto.getValueAt(tablaPresupuesto.getSelectedRow(), 3).toString();
                    String seguimiento = tablaServicioaux.getValueAt(i, 3).toString();

                    ResultSet rs = sentencia.executeQuery("SELECT id_servicio FROM detalle_solicitud "
                            + "WHERE cod_solicitud = " + solicitud + "");

                    while (rs.next()) {
                        servicio2 = rs.getInt("id_servicio");

                        if (servicio != servicio2) {

                            String sql2 = "INSERT into detalle_solicitud(cod_solicitud, id_servicio, precio, fecha_presupuesto, fecha_orden, fecha_entrega,estado_seguimiento, rut_trabajador) "
                                    + "VALUES( " + solicitud + "," + servicio + "," + precio + ",'" + fechapresupuesto + "','" + fechaorden + "','" + fechaentrega + "','" + seguimiento + "','" + rut + "')";

                            try {
                                sentencia.executeUpdate(sql2);
                            } catch (Exception e) {
                                //cont++;
                            }

                        } else {
                            String sql3 = "UPDATE detalle_solicitud "
                                    + "SET fecha_orden = '" + fechaorden + "',"
                                    + "fecha_entrega ='" + fechaentrega + "',"
                                    + "estado_seguimiento ='" + seguimiento + "',"
                                    + "rut_trabajador = '" + rut + "' "
                                    + "WHERE cod_solicitud = '" + solicitud + "' AND id_servicio = " + servicio + "";
                            try {
                                sentencia.executeUpdate(sql3);
                            } catch (Exception e) {
                                //cont++;
                            }
                        }
                    }

                } catch (SQLException ex) {
                }
            }

            for (int i = 0; i < columnainsumo; i++) {
                try {
                    int servicio = Integer.parseInt(tablaInsumoaux.getValueAt(i, 4).toString()), servicio2 = 0;
                    int insumo = Integer.parseInt(tablaInsumoaux.getValueAt(i, 0).toString()), insumo2 = 0;
                    int cantidad = Integer.parseInt(tablaInsumoaux.getValueAt(i, 3).toString()), cantidad2 = 0;

                    ResultSet rs = sentencia.executeQuery("SELECT id_servicio,cod_item,cantidad FROM detalle_insumo "
                            + "WHERE cod_solicitud = " + solicitud + "");

                    while (rs.next()) {
                        servicio2 = rs.getInt("id_servicio");
                        insumo2 = rs.getInt("cod_item");
                        cantidad2 = rs.getInt("cantidad");

                        if (servicio == servicio2 && insumo == insumo2 && cantidad != cantidad2) {

                            String sql4 = "UPDATE detalle_insumo "
                                    + "SET cantidad = '" + cantidad + "' "
                                    + "WHERE cod_solicitud = '" + solicitud + "' AND id_servicio = " + servicio + " AND cod_item = " + insumo + "";

                            try {
                                sentencia.executeUpdate(sql4);
                            } catch (Exception e) {
                                //cont++;
                            }

                        } else {
                            String sql5 = "INSERT into detalle_insumo(cod_solicitud, id_servicio, cod_item, cantidad) "
                                    + "VALUES( " + solicitud + "," + servicio + "," + insumo + "," + cantidad + ")";
                            try {
                                sentencia.executeUpdate(sql5);
                            } catch (Exception e) {
                                //cont++;
                            }
                        }
                    }

                } catch (SQLException ex) {
                }
            }

            if (cont == 0) {

                JOptionPane.showMessageDialog(null,
                        "ORDEN DE TRABAJO ingresado exitosamente", "INFO",
                        JOptionPane.INFORMATION_MESSAGE);
                clean();
                fechaActual();
                setFilasPresupuesto();

            } else {

                JOptionPane.showMessageDialog(null,
                        "Problemas con la base de datos,ORDEN DE TRABAJO no ingresada", "ERROR",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void tablaTrabajadorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaTrabajadorMouseClicked

    }//GEN-LAST:event_tablaTrabajadorMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Presupuestar_trabajo a = new Presupuestar_trabajo();
        a.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

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

    private void JT_codigoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JT_codigoKeyTyped
        JT_codigo.addKeyListener(new KeyAdapter() {
            public void keyReleased(final KeyEvent e) {
                filtroCodigo();
            }
        });
        trsfiltro = new TableRowSorter(modeloTablaPresupuesto);
        tablaPresupuesto.setRowSorter(trsfiltro);
    }//GEN-LAST:event_JT_codigoKeyTyped

    private void JT_codigoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JT_codigoMouseClicked
        JT_codigo.setText("");
        JT_codigo.setFont(new java.awt.Font("Tahoma", 0, 11));
        JT_codigo.setForeground(new java.awt.Color(0, 0, 0));
    }//GEN-LAST:event_JT_codigoMouseClicked

    private void JT_codigoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_JT_codigoFocusLost
        JT_codigo.setFont(new java.awt.Font("Tahoma", 2, 11));
        JT_codigo.setForeground(new java.awt.Color(153, 153, 153));
        JT_codigo.setText("Buscar por codigo");
    }//GEN-LAST:event_JT_codigoFocusLost

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

    private void tablaServicioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaServicioMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tablaServicioMouseClicked

    private void tablaInsumosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaInsumosMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tablaInsumosMouseClicked

    private void JT_componenteFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_JT_componenteFocusLost
        JT_componente.setFont(new java.awt.Font("Tahoma", 2, 11));
        JT_componente.setForeground(new java.awt.Color(153, 153, 153));
        JT_componente.setText("Buscar por componente");
    }//GEN-LAST:event_JT_componenteFocusLost

    private void JT_componenteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JT_componenteMouseClicked
        JT_componente.setText("");
        JT_componente.setFont(new java.awt.Font("Tahoma", 0, 11));
        JT_componente.setForeground(new java.awt.Color(0, 0, 0));
    }//GEN-LAST:event_JT_componenteMouseClicked

    private void JT_componenteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JT_componenteKeyTyped
        JT_componente.addKeyListener(new KeyAdapter() {
            public void keyReleased(final KeyEvent e) {
                filtroComponente();
            }
        });
        trsfiltro = new TableRowSorter(modeloTablaServicio);
        tablaServicio.setRowSorter(trsfiltro);
    }//GEN-LAST:event_JT_componenteKeyTyped

    private void JT_insumoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_JT_insumoFocusLost
        JT_insumo.setFont(new java.awt.Font("Tahoma", 2, 11));
        JT_insumo.setForeground(new java.awt.Color(153, 153, 153));
        JT_insumo.setText("Buscar por nombre");
    }//GEN-LAST:event_JT_insumoFocusLost

    private void JT_insumoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JT_insumoMouseClicked
        JT_insumo.setText("");
        JT_insumo.setFont(new java.awt.Font("Tahoma", 0, 11));
        JT_insumo.setForeground(new java.awt.Color(0, 0, 0));
    }//GEN-LAST:event_JT_insumoMouseClicked

    private void JT_insumoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JT_insumoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JT_insumoActionPerformed

    private void JT_insumoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JT_insumoKeyTyped
        JT_insumo.addKeyListener(new KeyAdapter() {
            public void keyReleased(final KeyEvent e) {
                filtroInsumo();
            }
        });
        trsfiltro = new TableRowSorter(modeloTablaInsumo);
        tablaInsumos.setRowSorter(trsfiltro);
    }//GEN-LAST:event_JT_insumoKeyTyped

    private void btnAddServicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddServicioActionPerformed

        int cont = 0;
        if (tablaServicio.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(null,
                    "Error, Seleccione Servicio!", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            cont++;
        }

        if (cont == 0) {

            int columnas = tablaServicioaux.getRowCount();
            int cod = Integer.parseInt(tablaServicio.getValueAt(tablaServicio.getSelectedRow(), 0).toString().trim());
            int cod2 = 0;

            Object fila[] = new Object[4];
            fila[0] = tablaServicio.getValueAt(tablaServicio.getSelectedRow(), 0);
            fila[1] = tablaServicio.getValueAt(tablaServicio.getSelectedRow(), 1);
            fila[2] = tablaServicio.getValueAt(tablaServicio.getSelectedRow(), 2);

            if (columnas != 0) {
                for (int i = 0; i < columnas; i++) {
                    cod2 = Integer.parseInt(tablaServicioaux.getValueAt(i, 0).toString().trim());
                    if (cod == cod2) {
                        cont++;
                    }
                }
            }

            if (cont == 0) {
                modeloTablaServicioaux.addRow(fila);
            } else {
                JOptionPane.showMessageDialog(null,
                        "Error, Ya selecciono este Servicio!", "ERROR",
                        JOptionPane.ERROR_MESSAGE);
            }

        }
    }//GEN-LAST:event_btnAddServicioActionPerformed

    private void btnQuitarServicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuitarServicioActionPerformed
        int cont = 0;

        if (tablaServicioaux.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(null,
                    "Seleccione servicio!", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            cont++;
        } else if (tablaServicioaux.getRowCount() <= 1) {
            JOptionPane.showMessageDialog(null,
                    "No puede eliminar todos los servicios", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            cont++;
        } else if (tablaPresupuesto.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(null,
                    "Seleccione Presupuesto!", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            cont++;
        }

        if (cont == 0) {

            int filaservicio = tablaServicioaux.getSelectedRow(), sw = 0;
            int columnainsumo = tablaInsumoaux.getRowCount();
            int presupuesto = Integer.parseInt(tablaPresupuesto.getValueAt(tablaPresupuesto.getSelectedRow(), 0).toString());
            int servicio = Integer.parseInt(tablaServicioaux.getValueAt(tablaServicioaux.getSelectedRow(), 0).toString()), servicio2 = 0;
            String nom = tablaServicioaux.getValueAt(tablaServicioaux.getSelectedRow(), 1).toString();

            try {
                sentencia = (com.mysql.jdbc.Statement) cn.createStatement();
                ResultSet rs = sentencia.executeQuery("SELECT id_servicio FROM detalle_solicitud "
                        + "WHERE cod_solicitud = " + presupuesto + "");
                while (rs.next()) {
                    servicio2 = rs.getInt("id_servicio");
                    if (servicio == servicio2) {
                        sw++;
                    }
                }
            } catch (SQLException f) {

            }

            if (sw > 0) {

                int i = JOptionPane.showConfirmDialog(this,
                        "¿Realmente desea eliminar " + nom + " de la ORDEN DE TRABAJO?", "Confirmar Eliminación",
                        JOptionPane.YES_NO_OPTION);
                if (i == 0) {
                    String sql = "DELETE FROM detalle_solicitud  WHERE cod_solicitud = " + presupuesto + " AND id_servicio = " + servicio + "";
                    modeloTablaServicioaux.removeRow(filaservicio);
                    try {
                        sentencia.executeUpdate(sql);
                    } catch (SQLException ex) {
                        Logger.getLogger(Orden_trabajo.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    JOptionPane.showMessageDialog(null,
                            "SERVICIO eliminado correctamente de la ORDEN DE TRABAJO", "INFO",
                            JOptionPane.INFORMATION_MESSAGE);
                    for (int j = 0; j < columnainsumo; j++) {
                        if (servicio == Integer.parseInt(tablaInsumoaux.getValueAt(j, 4).toString())) {
                            String sql2 = "DELETE FROM detalle_insumo  WHERE cod_solicitud = " + presupuesto + " AND id_servicio = " + servicio + " AND cod_item = " + Integer.parseInt(tablaInsumoaux.getValueAt(j, 0).toString()) + "";
                            modeloTablaInsumoaux.removeRow(j);
                            try {
                                sentencia.executeUpdate(sql2);
                            } catch (SQLException ex) {
                                Logger.getLogger(Orden_trabajo.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }
                }

            } else {
                modeloTablaServicioaux.removeRow(filaservicio);
            }
        }
    }//GEN-LAST:event_btnQuitarServicioActionPerformed

    private void btnAddInsumoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddInsumoActionPerformed

        int cont = 0;

        if (tablaInsumos.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(null,
                    "Error, Seleccione INSUMO!", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            cont++;
        } else if (tablaServicioaux.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(null,
                    "Error, Seleccione SERVICIO!", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            cont++;
        }

        if (cont == 0) {
            int columnas = tablaInsumoaux.getRowCount();
            int cod = Integer.parseInt(tablaInsumos.getValueAt(tablaInsumos.getSelectedRow(), 0).toString().trim());
            int ser = Integer.parseInt(tablaServicioaux.getValueAt(tablaServicioaux.getSelectedRow(), 0).toString().trim());
            int cod2 = 0, ser2;

            Object fila[] = new Object[5];
            fila[0] = tablaInsumos.getValueAt(tablaInsumos.getSelectedRow(), 0);
            fila[1] = tablaInsumos.getValueAt(tablaInsumos.getSelectedRow(), 1);
            fila[2] = tablaInsumos.getValueAt(tablaInsumos.getSelectedRow(), 2);
            fila[3] = 1;
            fila[4] = tablaServicioaux.getValueAt(tablaServicioaux.getSelectedRow(), 0);

            if (columnas != 0) {
                for (int i = 0; i < columnas; i++) {
                    cod2 = Integer.parseInt(tablaInsumoaux.getValueAt(i, 0).toString().trim());
                    ser2 = Integer.parseInt(tablaInsumoaux.getValueAt(i, 4).toString().trim());
                    if (cod == cod2 && ser == ser2) {
                        cont++;
                    }
                }
            }

            if (cont == 0) {
                modeloTablaInsumoaux.addRow(fila);
            } else {
                JOptionPane.showMessageDialog(null,
                        "Error, Ya Selecciono este INSUMO!", "ERROR",
                        JOptionPane.ERROR_MESSAGE);
            }

        }
    }//GEN-LAST:event_btnAddInsumoActionPerformed

    private void btnQuitarInsumoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuitarInsumoActionPerformed
        int cont = 0;
        if (tablaInsumoaux.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(null,
                    "Error, Seleccione INSUMO!", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            cont++;
        }else if (tablaPresupuesto.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(null,
                    "Seleccione ORDEN DE TRABAJO!", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            cont++;
        }

        if (cont == 0) {
            String nom = tablaInsumoaux.getValueAt(tablaInsumoaux.getSelectedRow(), 1).toString();
            int i = JOptionPane.showConfirmDialog(this,
                    "¿Realmente desea eliminar " + nom + " de la ORDEN DE TRABAJO?", "Confirmar Eliminación",
                    JOptionPane.YES_NO_OPTION);

            int filainsumo = tablaInsumoaux.getSelectedRow();
            int presupuesto = Integer.parseInt(tablaPresupuesto.getValueAt(tablaPresupuesto.getSelectedRow(), 0).toString());
            int servicio = Integer.parseInt(tablaInsumoaux.getValueAt(tablaInsumoaux.getSelectedRow(), 4).toString()), servicio2 = 0;
            int insumo = Integer.parseInt(tablaInsumoaux.getValueAt(tablaInsumoaux.getSelectedRow(), 0).toString()), insumo2 = 0;

            try {
                sentencia = (com.mysql.jdbc.Statement) cn.createStatement();
                ResultSet rs = sentencia.executeQuery("SELECT id_servicio, cod_item FROM detalle_insumo "
                        + "WHERE cod_solicitud = " + presupuesto + "");
                while (rs.next()) {
                    servicio2 = rs.getInt("id_servicio");
                    insumo2 = rs.getInt("cod_item");
                    if (servicio == servicio2 && insumo == insumo2 && i == 0) {
                        String sql = "DELETE FROM detalle_insumo  WHERE cod_solicitud = " + presupuesto + " AND id_servicio = " + servicio + " AND cod_item = " + insumo + "";
                        modeloTablaInsumoaux.removeRow(filainsumo);
                        sentencia.executeUpdate(sql);
                        JOptionPane.showMessageDialog(null,
                                "SERVICIO eliminado correctamente de la ORDEN DE TRABAJO", "INFO",
                                JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            } catch (SQLException f) {

            }
        }
    }//GEN-LAST:event_btnQuitarInsumoActionPerformed

    private void tablaServicioauxMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaServicioauxMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tablaServicioauxMouseClicked

    private void tablaInsumoauxMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaInsumoauxMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tablaInsumoauxMouseClicked

    private void JT_ruttrabajadorFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_JT_ruttrabajadorFocusLost
        JT_ruttrabajador.setFont(new java.awt.Font("Tahoma", 2, 11));
        JT_ruttrabajador.setForeground(new java.awt.Color(153, 153, 153));
        JT_ruttrabajador.setText("Buscar por rut");
    }//GEN-LAST:event_JT_ruttrabajadorFocusLost

    private void JT_ruttrabajadorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JT_ruttrabajadorMouseClicked
        JT_ruttrabajador.setText("");
        JT_ruttrabajador.setFont(new java.awt.Font("Tahoma", 0, 11));
        JT_ruttrabajador.setForeground(new java.awt.Color(0, 0, 0));
    }//GEN-LAST:event_JT_ruttrabajadorMouseClicked

    private void JT_ruttrabajadorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JT_ruttrabajadorKeyTyped
        JT_ruttrabajador.addKeyListener(new KeyAdapter() {
            public void keyReleased(final KeyEvent e) {
                filtroRutTrabajador();
            }
        });
        trsfiltro = new TableRowSorter(modeloTablaTrabajador);
        tablaTrabajador.setRowSorter(trsfiltro);
    }//GEN-LAST:event_JT_ruttrabajadorKeyTyped

    private void JT_nombretrabajadorFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_JT_nombretrabajadorFocusLost
        JT_nombretrabajador.setFont(new java.awt.Font("Tahoma", 2, 11));
        JT_nombretrabajador.setForeground(new java.awt.Color(153, 153, 153));
        JT_nombretrabajador.setText("Buscar por nombre");
    }//GEN-LAST:event_JT_nombretrabajadorFocusLost

    private void JT_nombretrabajadorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JT_nombretrabajadorMouseClicked
        JT_nombretrabajador.setText("");
        JT_nombretrabajador.setFont(new java.awt.Font("Tahoma", 0, 11));
        JT_nombretrabajador.setForeground(new java.awt.Color(0, 0, 0));
    }//GEN-LAST:event_JT_nombretrabajadorMouseClicked

    private void JT_nombretrabajadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JT_nombretrabajadorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JT_nombretrabajadorActionPerformed

    private void JT_nombretrabajadorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JT_nombretrabajadorKeyTyped
        JT_nombretrabajador.addKeyListener(new KeyAdapter() {
            public void keyReleased(final KeyEvent e) {
                filtroNombreTrabajador();
            }
        });
        trsfiltro = new TableRowSorter(modeloTablaTrabajador);
        tablaTrabajador.setRowSorter(trsfiltro);
    }//GEN-LAST:event_JT_nombretrabajadorKeyTyped

    private void JT_paternotrabajadorFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_JT_paternotrabajadorFocusLost
        JT_paternotrabajador.setFont(new java.awt.Font("Tahoma", 2, 11));
        JT_paternotrabajador.setForeground(new java.awt.Color(153, 153, 153));
        JT_paternotrabajador.setText("Buscar por paterno");
    }//GEN-LAST:event_JT_paternotrabajadorFocusLost

    private void JT_paternotrabajadorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JT_paternotrabajadorMouseClicked
        JT_paternotrabajador.setText("");
        JT_paternotrabajador.setFont(new java.awt.Font("Tahoma", 0, 11));
        JT_paternotrabajador.setForeground(new java.awt.Color(0, 0, 0));
    }//GEN-LAST:event_JT_paternotrabajadorMouseClicked

    private void JT_paternotrabajadorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JT_paternotrabajadorKeyTyped
        JT_paternotrabajador.addKeyListener(new KeyAdapter() {
            public void keyReleased(final KeyEvent e) {
                filtroPaternoTrabajador();
            }
        });
        trsfiltro = new TableRowSorter(modeloTablaTrabajador);
        tablaTrabajador.setRowSorter(trsfiltro);
    }//GEN-LAST:event_JT_paternotrabajadorKeyTyped

    private void JT_maternotrabajadorFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_JT_maternotrabajadorFocusLost
        JT_maternotrabajador.setFont(new java.awt.Font("Tahoma", 2, 11));
        JT_maternotrabajador.setForeground(new java.awt.Color(153, 153, 153));
        JT_maternotrabajador.setText("Buscar por materno");
    }//GEN-LAST:event_JT_maternotrabajadorFocusLost

    private void JT_maternotrabajadorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JT_maternotrabajadorMouseClicked
        JT_maternotrabajador.setText("");
        JT_maternotrabajador.setFont(new java.awt.Font("Tahoma", 0, 11));
        JT_maternotrabajador.setForeground(new java.awt.Color(0, 0, 0));
    }//GEN-LAST:event_JT_maternotrabajadorMouseClicked

    private void JT_maternotrabajadorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JT_maternotrabajadorKeyTyped
        JT_maternotrabajador.addKeyListener(new KeyAdapter() {
            public void keyReleased(final KeyEvent e) {
                filtroMaternoTrabajador();
            }
        });
        trsfiltro = new TableRowSorter(modeloTablaTrabajador);
        tablaTrabajador.setRowSorter(trsfiltro);
    }//GEN-LAST:event_JT_maternotrabajadorKeyTyped

    private void JT_cargoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_JT_cargoFocusLost
        JT_cargo.setFont(new java.awt.Font("Tahoma", 2, 11));
        JT_cargo.setForeground(new java.awt.Color(153, 153, 153));
        JT_cargo.setText("Buscar por ciudad");
    }//GEN-LAST:event_JT_cargoFocusLost

    private void JT_cargoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JT_cargoMouseClicked
        JT_cargo.setText("");
        JT_cargo.setFont(new java.awt.Font("Tahoma", 0, 11));
        JT_cargo.setForeground(new java.awt.Color(0, 0, 0));
    }//GEN-LAST:event_JT_cargoMouseClicked

    private void JT_cargoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JT_cargoKeyTyped
        JT_cargo.addKeyListener(new KeyAdapter() {
            public void keyReleased(final KeyEvent e) {
                filtroCargo();
            }
        });
        trsfiltro = new TableRowSorter(modeloTablaTrabajador);
        tablaTrabajador.setRowSorter(trsfiltro);
    }//GEN-LAST:event_JT_cargoKeyTyped

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        Modificar_trabajador a = new Modificar_trabajador();
        a.setVisible(true);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        Ingresar_trabajador a = new Ingresar_trabajador();
        a.setVisible(true);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void btnAddCantActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddCantActionPerformed
        int cont = 0, actual = 0, critico = 0;

        if (tablaInsumoaux.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(null,
                    "Error, Seleccione insumo!", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            cont++;
        }

        if (cont == 0) {
            int insumo = Integer.parseInt(tablaInsumoaux.getValueAt(tablaInsumoaux.getSelectedRow(), 0).toString());

            try {
                sentencia = (com.mysql.jdbc.Statement) cn.createStatement();
                ResultSet rs = sentencia.executeQuery("SELECT stock_actual,stock_critico FROM inventario WHERE cod_item = " + insumo + "");
                while (rs.next()) {
                    actual = rs.getInt("stock_actual");
                    critico = rs.getInt("stock_critico");
                }
            } catch (SQLException f) {
                JOptionPane.showMessageDialog(null,
                        "Problemas con la base de datos, no se pudo obtener informacion", "ERROR",
                        JOptionPane.ERROR_MESSAGE);
            }

            int base = Integer.parseInt(tablaInsumoaux.getValueAt(tablaInsumoaux.getSelectedRow(), 3).toString().trim());
            int valor = base + 1;

            if (valor == critico) {
                JOptionPane.showMessageDialog(null,
                        "Insumo en stock critico", "INFO",
                        JOptionPane.INFORMATION_MESSAGE);
                tablaInsumoaux.setValueAt(valor, tablaInsumoaux.getSelectedRow(), 3);
            } else if (valor > actual) {
                JOptionPane.showMessageDialog(null,
                        "Insumo no hay suficiente insumo", "ERROR",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                tablaInsumoaux.setValueAt(valor, tablaInsumoaux.getSelectedRow(), 3);
            }

        }
    }//GEN-LAST:event_btnAddCantActionPerformed

    private void btnRemoveCantActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveCantActionPerformed
        int cont = 0;
        if (tablaInsumoaux.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(null,
                    "Error, Seleccione insumo!", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            cont++;
        }
        if (cont == 0) {

            int base = Integer.parseInt(tablaInsumoaux.getValueAt(tablaInsumoaux.getSelectedRow(), 3).toString().trim());
            int valor = base - 1;
            if (valor < 1) {
                JOptionPane.showMessageDialog(null,
                        "Error, No se puede seguir disminuyendo insumo!", "ERROR",
                        JOptionPane.ERROR_MESSAGE);
                cont++;
            } else {
                tablaInsumoaux.setValueAt(valor, tablaInsumoaux.getSelectedRow(), 3);
            }
        }
    }//GEN-LAST:event_btnRemoveCantActionPerformed

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
            java.util.logging.Logger.getLogger(Modificar_OT.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Modificar_OT.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Modificar_OT.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Modificar_OT.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Modificar_OT().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton JB_cancel;
    private com.toedter.calendar.JDateChooser JDC_fechaEntrega;
    private javax.swing.JTextArea JTA_observaciones;
    private javax.swing.JTextField JT_cargo;
    private javax.swing.JTextField JT_codigo;
    private javax.swing.JTextField JT_componente;
    private javax.swing.JTextField JT_insumo;
    private javax.swing.JTextField JT_maternotrabajador;
    private javax.swing.JTextField JT_nombretrabajador;
    private javax.swing.JTextField JT_patente;
    private javax.swing.JTextField JT_paternotrabajador;
    private javax.swing.JTextField JT_ruttrabajador;
    private javax.swing.JTextField JT_subtotal;
    private javax.swing.JTextField JT_total;
    private javax.swing.JTextField JT_trabajador;
    private javax.swing.JButton btnAddCant;
    private javax.swing.JButton btnAddInsumo;
    private javax.swing.JButton btnAddServicio;
    private javax.swing.JButton btnQuitarInsumo;
    private javax.swing.JButton btnQuitarServicio;
    private javax.swing.JButton btnRemoveCant;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
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
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblFecha;
    private javax.swing.JLabel lblFecha1;
    private javax.swing.JLabel lblFecha2;
    private javax.swing.JTable tablaInsumoaux;
    private javax.swing.JTable tablaInsumos;
    private javax.swing.JTable tablaPresupuesto;
    private javax.swing.JTable tablaServicio;
    private javax.swing.JTable tablaServicioaux;
    private javax.swing.JTable tablaTrabajador;
    // End of variables declaration//GEN-END:variables
}
