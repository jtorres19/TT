package racadauto;

import Conexion.Conexion;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class Presupuestar_trabajo2 extends javax.swing.JFrame {

    private Statement sentencia;
    Conexion con = new Conexion();
    Connection cn = (Connection) con.getConnection();
    DefaultTableModel modeloTablaCliente;
    DefaultTableModel modeloTablaVehiculo;
    DefaultTableModel modeloTablaServicio;
    DefaultTableModel modeloTablaInsumo;
    DefaultTableModel modeloTablaServicioaux;
    DefaultTableModel modeloTablaInsumoaux;
    DefaultTableModel modeloTablaTrabajador;
    private TableRowSorter trsfiltro;
    String filtro;

    public Presupuestar_trabajo2() {
        modeloTablaCliente = new DefaultTableModel(null, getColumnasCliente());
        modeloTablaVehiculo = new DefaultTableModel(null, getColumnasVehiculo());
        modeloTablaServicio = new DefaultTableModel(null, getColumnasServicio());
        modeloTablaInsumo = new DefaultTableModel(null, getColumnasInsumo());
        modeloTablaServicioaux = new DefaultTableModel(null, getColumnasServicio());
        modeloTablaInsumoaux = new DefaultTableModel(null, getColumnasInsumoaux());
        modeloTablaTrabajador = new DefaultTableModel(null, getColumnasTrabajador());
        setFilasCliente();
        setFilasInsumo();
        setFilasServicio();
        setFilasTrabajador();
        initComponents();
        fechaActual();
        llenarLabels();
    }

    private String[] getColumnasCliente() {
        String columna[] = new String[]{"RUT", "NOMBRE", "APELLIDO PATERNO", "APELLIDO MATERNO"};
        return columna;
    }

    private String[] getColumnasVehiculo() {
        String columna[] = new String[]{"PATENTE", "AÑO", "KMS", "V.I.N", "COLOR", "MARCA", "MODELO"};
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

    private String[] getColumnasServicio() {
        String columna[] = new String[]{"ID", "COMPONENTE", "PRECIO"};
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

    public void filtroRutTrabajador() {

        filtro = JT_ruttrabajador.getText();
        int columna = 0;
        trsfiltro.setRowFilter(RowFilter.regexFilter(JT_ruttrabajador.getText(), columna));

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
        int columna = 7;
        trsfiltro.setRowFilter(RowFilter.regexFilter(JT_cargo.getText().toUpperCase(), columna));
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

    private void setFilasCliente() {
        try {
            sentencia = (com.mysql.jdbc.Statement) cn.createStatement();
            ResultSet lista = sentencia.executeQuery("SELECT rut_cliente, nombre, ape_paterno, ape_materno "
                    + "FROM cliente ");
            Object datos[] = new Object[4];
            while (lista.next()) {
                for (int i = 0; i < 4; i++) {
                    datos[i] = lista.getObject(i + 1);
                }
                modeloTablaCliente.addRow(datos);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "Error, Poblemas con el servidor(Clientes)", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void setFilasVehiculo() {

        String rut = tablaClientes.getValueAt(tablaClientes.getSelectedRow(), 0).toString();
        
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
            modeloTablaVehiculo.addRow(datos);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "Error, Poblemas con el servidor(Vehiculo)", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
        }

    }

    private void setFilasTrabajador() {
        try {
            sentencia = (com.mysql.jdbc.Statement) cn.createStatement();
            ResultSet lista = sentencia.executeQuery("SELECT t.rut_trabajador, t.nombre, t.ape_paterno, t.ape_materno, c.nombre "
                    + "FROM trabajador t,cargo c WHERE t.id_cargo = c.id_cargo");
            Object datos[] = new Object[5];
            while (lista.next()) {
                for (int i = 0; i < 5; i++) {
                    datos[i] = lista.getObject(i + 1);
                }
                modeloTablaTrabajador.addRow(datos);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "Error, Poblemas con el servidor(Trabajador)", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void setFilasInsumo() {
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
                modeloTablaInsumo.addRow(datos);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "Error, Poblemas con el servidor(Inventario)", "ERROR",
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
                    "Error, Poblemas con el servidor(Servicio)", "ERROR",
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
            JOptionPane.showMessageDialog(null,
                    "Error, Poblemas con el servidor(Solicitud)", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
        }
        LBL_solicitud.setText("" + cod + "");
        LBL_solicitud1.setText("" + cod + "");
        LBL_solicitud2.setText("" + cod + "");
    }

    void limpiaTabla() {
        do {
            modeloTablaVehiculo.setRowCount(0);
        } while (modeloTablaVehiculo.getRowCount() != 0);
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

        if (tablaClientes.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(null,
                    "Error, Seleccione cliente!", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            cont++;
        } else if (tablaVehiculo.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(null,
                    "Error, Seleccione vehiculo!", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            cont++;
        } else if (tablaTrabajador.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(null,
                    "Error, Seleccione trabajador!", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            cont++;
        }

        if (tablaServicioaux.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null,
                    "Error, Añada Servicio!", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            cont++;
        } else if (tablaInsumoaux.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null,
                    "Error, Añada Insumo!", "ERROR",
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
        LBL_solicitud.setText("");
        LBL_solicitud1.setText("");
        LBL_solicitud2.setText("");
        modeloTablaServicioaux.setRowCount(0);
        modeloTablaInsumoaux.setRowCount(0);
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
        tablaClientes = new javax.swing.JTable();
        jScrollPane6 = new javax.swing.JScrollPane();
        tablaVehiculo = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        JT_rut = new javax.swing.JTextField();
        JT_nombre = new javax.swing.JTextField();
        JT_paterno = new javax.swing.JTextField();
        JT_materno = new javax.swing.JTextField();
        JT_patente = new javax.swing.JTextField();
        JT_año = new javax.swing.JTextField();
        JT_kms = new javax.swing.JTextField();
        JT_vin = new javax.swing.JTextField();
        JT_color = new javax.swing.JTextField();
        JT_marca = new javax.swing.JTextField();
        JT_modelo = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        LBL_solicitud1 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lblFecha1 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tablaServicio = new javax.swing.JTable();
        jLabel15 = new javax.swing.JLabel();
        btnAddServicio = new javax.swing.JButton();
        btnQuitarServicio = new javax.swing.JButton();
        jScrollPane8 = new javax.swing.JScrollPane();
        tablaServicioaux = new javax.swing.JTable();
        jScrollPane9 = new javax.swing.JScrollPane();
        tablaInsumos = new javax.swing.JTable();
        btnAddInsumo = new javax.swing.JButton();
        btnQuitarInsumo = new javax.swing.JButton();
        jScrollPane10 = new javax.swing.JScrollPane();
        tablaInsumoaux = new javax.swing.JTable();
        btnAddCant = new javax.swing.JButton();
        btnRemoveCant = new javax.swing.JButton();
        JT_componente = new javax.swing.JTextField();
        JT_insumo = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        LBL_solicitud2 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        lblFecha2 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tablaTrabajador = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        JTA_observaciones = new javax.swing.JTextArea();
        JT_nombretrabajador = new javax.swing.JTextField();
        JT_paternotrabajador = new javax.swing.JTextField();
        JT_maternotrabajador = new javax.swing.JTextField();
        JT_cargo = new javax.swing.JTextField();
        JT_ruttrabajador = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        JB_cancel = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("PRESUPUESTO");

        jLabel9.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel9.setText("RACAD AUTOMOTRIZ - PRESUPUESTAR TRABAJO");

        jLabel1.setText("Codigo Solicitud :");

        jLabel6.setText("Fecha Actual :");

        jLabel12.setText("Vehiculo:");

        tablaClientes.setModel(modeloTablaCliente);
        tablaClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaClientesMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tablaClientes);

        tablaVehiculo.setModel(modeloTablaVehiculo);
        tablaVehiculo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaVehiculoMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(tablaVehiculo);

        jLabel5.setText("Clientes:");

        jButton1.setText("Nuevo");
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

        jButton8.setText("Nuevo");
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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel9)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jButton9))
                            .addComponent(jScrollPane6)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton1)
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
                                    .addComponent(jButton8)
                                    .addComponent(JT_rut, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(JT_patente, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(JT_año, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(JT_kms, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(JT_vin, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(JT_color, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(8, 8, 8)
                                        .addComponent(JT_marca, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(JT_modelo, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap(104, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(112, 112, 112)
                                .addComponent(JT_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(JT_paterno, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27)
                                .addComponent(JT_materno, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 488, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton7))
                        .addContainerGap(337, Short.MAX_VALUE))))
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
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(JT_rut, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(JT_paterno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(JT_materno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(JT_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1)
                    .addComponent(jButton7))
                .addGap(18, 18, 18)
                .addComponent(jLabel12)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JT_patente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JT_año, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JT_kms, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JT_vin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JT_color, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JT_marca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JT_modelo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton8)
                    .addComponent(jButton9))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Seleccionar Vehiculo", jPanel1);

        jLabel10.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel10.setText("RACAD AUTOMOTRIZ - PRESUPUESTAR TRABAJO");

        jLabel2.setText("Codigo Solicitud :");

        jLabel7.setText("Fecha Actual :");

        jLabel14.setText("Insumos:");

        tablaServicio.setModel(modeloTablaServicio);
        tablaServicio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaServicioMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(tablaServicio);

        jLabel15.setText("Servicios");

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

        tablaServicioaux.setModel(modeloTablaServicioaux);
        tablaServicioaux.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaServicioauxMouseClicked(evt);
            }
        });
        jScrollPane8.setViewportView(tablaServicioaux);

        tablaInsumos.setModel(modeloTablaInsumo);
        tablaInsumos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaInsumosMouseClicked(evt);
            }
        });
        jScrollPane9.setViewportView(tablaInsumos);

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

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(115, 115, 115)
                .addComponent(JT_componente, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(JT_insumo, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(194, 194, 194))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(74, 74, 74)
                .addComponent(btnAddServicio)
                .addGap(18, 18, 18)
                .addComponent(btnQuitarServicio)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 299, Short.MAX_VALUE)
                .addComponent(btnAddInsumo)
                .addGap(18, 18, 18)
                .addComponent(btnQuitarInsumo)
                .addGap(177, 177, 177))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 335, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 335, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(50, 50, 50)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane10, javax.swing.GroupLayout.DEFAULT_SIZE, 366, Short.MAX_VALUE)
                            .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnRemoveCant, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnAddCant)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(6, 6, 6)
                                .addComponent(LBL_solicitud1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel7)
                                .addGap(6, 6, 6)
                                .addComponent(lblFecha1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel15)
                                .addGap(343, 343, 343)
                                .addComponent(jLabel14)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel10)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(LBL_solicitud1, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(lblFecha1, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                            .addComponent(btnAddInsumo)
                            .addComponent(btnQuitarInsumo)
                            .addComponent(btnAddServicio)
                            .addComponent(btnQuitarServicio))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(124, 124, 124)
                        .addComponent(btnAddCant)
                        .addGap(18, 18, 18)
                        .addComponent(btnRemoveCant)))
                .addGap(0, 44, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Seleccionar Servicios e Insumos", jPanel2);

        jLabel11.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel11.setText("RACAD AUTOMOTRIZ - PRESUPUESTAR TRABAJO");

        jLabel3.setText("Codigo Solicitud :");

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

        jButton3.setText("Nuevo");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Modificar");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel11)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 538, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(LBL_solicitud2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblFecha2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel13)
                    .addComponent(jLabel4)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(JT_ruttrabajador, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(JT_nombretrabajador, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(JT_paternotrabajador, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(JT_maternotrabajador, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(JT_cargo))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton4)))
                .addContainerGap(290, Short.MAX_VALUE))
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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                .addComponent(jLabel13)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(JT_ruttrabajador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(JT_nombretrabajador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(JT_paternotrabajador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(JT_maternotrabajador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(JT_cargo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton3)
                    .addComponent(jButton4))
                .addGap(34, 34, 34))
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
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 645, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(JB_cancel))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void tablaClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaClientesMouseClicked

        limpiaTabla();
        setFilasVehiculo();

    }//GEN-LAST:event_tablaClientesMouseClicked

    private void JB_cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JB_cancelActionPerformed
        this.dispose();
    }//GEN-LAST:event_JB_cancelActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        if (verificar() == 0) {

            String pat = tablaVehiculo.getValueAt(tablaVehiculo.getSelectedRow(), 0).toString();
            String rut = tablaTrabajador.getValueAt(tablaTrabajador.getSelectedRow(), 0).toString();

            int cod = 0, subtotal = 0, total = 0, cont = 0;

            String fecha = lblFecha.getText();
            String obser = JTA_observaciones.getText().toUpperCase().trim();

            int columnaservicio = tablaServicioaux.getRowCount(), columnainsumo = tablaInsumoaux.getRowCount();

            for (int i = 0; i < columnainsumo; i++) {
                subtotal = subtotal + Integer.parseInt(tablaInsumoaux.getValueAt(i, 2).toString()) * Integer.parseInt(tablaInsumoaux.getValueAt(i, 3).toString());
            }

            for (int i = 0; i < columnaservicio; i++) {
                subtotal = subtotal + Integer.parseInt(tablaServicioaux.getValueAt(i, 2).toString());
            }

            total = (int) (subtotal * 1.19);

            String est = "PRESUPUESTO";

            try {
                sentencia = (com.mysql.jdbc.Statement) cn.createStatement();
                ResultSet rs = sentencia.executeQuery("SELECT MAX(cod_solicitud) as cod_solicitud FROM solicitud_servicio");
                while (rs.next()) {
                    cod = rs.getInt("cod_solicitud");
                }
                cod++;
            } catch (SQLException f) {
                cont++;
            }

            String sql = "INSERT INTO solicitud_servicio(cod_solicitud,observaciones,subtotal,total,fecha_presupuesto,estado_solicitud,patente,rut_trabajador) "
                    + "VALUES(" + cod + ",'" + obser + "'," + subtotal + "," + total + ",'" + fecha + "','" + est + "','" + pat + "','" + rut + "')";

            try {
                sentencia.executeUpdate(sql);

            } catch (SQLException e) {
                cont++;
            }

            for (int i = 0; i < columnaservicio; i++) {

                try {

                    int servicio = Integer.parseInt(tablaServicioaux.getValueAt(i, 0).toString());
                    int precio = Integer.parseInt(tablaServicioaux.getValueAt(i, 2).toString());

                    String sql2 = "INSERT into detalle_solicitud(cod_solicitud, id_servicio, precio, fecha_presupuesto,rut_trabajador) "
                            + "VALUES(" + cod + ", " + servicio + "," + precio + ",'" + fecha + "','" + rut + "')";
                    sentencia.executeUpdate(sql2);

                } catch (SQLException ex) {
                    cont++;
                }
            }

            for (int i = 0; i < columnainsumo; i++) {

                try {

                    int insumo = Integer.parseInt(tablaInsumoaux.getValueAt(i, 0).toString());
                    int cantidad = Integer.parseInt(tablaInsumoaux.getValueAt(i, 3).toString());
                    int servicio = Integer.parseInt(tablaInsumoaux.getValueAt(i, 4).toString());

                    String sql3 = "INSERT into detalle_insumo(cod_item,cod_solicitud, id_servicio, cantidad) "
                            + "VALUES(" + insumo + ", " + cod + "," + servicio + ",'" + cantidad + "')";
                    sentencia.executeUpdate(sql3);

                } catch (SQLException ex) {
                    cont++;
                }
            }

            if (cont == 0) {
                JOptionPane.showMessageDialog(null,
                        "PRESUPUESTO ingresado exitosamente", "INFO",
                        JOptionPane.INFORMATION_MESSAGE);
                clean();
                limpiaTabla();
                llenarLabels();
                fechaActual();
            } else {
                JOptionPane.showMessageDialog(null,
                        "Problemas con la base de datos,PRESUPUESTO no ingresado", "ERROR",
                        JOptionPane.ERROR_MESSAGE);
            }

        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void tablaTrabajadorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaTrabajadorMouseClicked

    }//GEN-LAST:event_tablaTrabajadorMouseClicked

    private void tablaVehiculoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaVehiculoMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tablaVehiculoMouseClicked

    private void tablaServicioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaServicioMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tablaServicioMouseClicked

    private void tablaServicioauxMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaServicioauxMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tablaServicioauxMouseClicked

    private void tablaInsumosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaInsumosMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tablaInsumosMouseClicked

    private void tablaInsumoauxMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaInsumoauxMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tablaInsumoauxMouseClicked

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

            Object fila[] = new Object[3];
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

    private void btnAddCantActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddCantActionPerformed
        int cont = 0;
        if (tablaInsumoaux.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(null,
                    "Error, Seleccione insumo!", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            cont++;
        }
        if (cont == 0) {

            int base = Integer.parseInt(tablaInsumoaux.getValueAt(tablaInsumoaux.getSelectedRow(), 3).toString().trim());
            int valor = base + 1;
            tablaInsumoaux.setValueAt(valor, tablaInsumoaux.getSelectedRow(), 3);
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

    private void btnQuitarServicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuitarServicioActionPerformed
        int cont = 0;
        if (tablaServicioaux.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(null,
                    "Error, Seleccione servicio!", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            cont++;
        }

        if (cont == 0) {
            int fila = tablaServicioaux.getSelectedRow();
            modeloTablaServicioaux.removeRow(fila);
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
        }

        if (cont == 0) {
            int fila = tablaInsumoaux.getSelectedRow();
            modeloTablaInsumoaux.removeRow(fila);
        }
    }//GEN-LAST:event_btnQuitarInsumoActionPerformed

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
                filtroNombre();
            }
        });
        trsfiltro = new TableRowSorter(modeloTablaInsumo);
        tablaInsumos.setRowSorter(trsfiltro);
    }//GEN-LAST:event_JT_insumoKeyTyped

    private void JT_rutFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_JT_rutFocusLost
        JT_rut.setFont(new java.awt.Font("Tahoma", 2, 11));
        JT_rut.setForeground(new java.awt.Color(153, 153, 153));
        JT_rut.setText("Buscar por rut");
    }//GEN-LAST:event_JT_rutFocusLost

    private void JT_rutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JT_rutMouseClicked
        JT_rut.setText("");
        JT_rut.setFont(new java.awt.Font("Tahoma", 0, 11));
        JT_rut.setForeground(new java.awt.Color(0, 0, 0));
    }//GEN-LAST:event_JT_rutMouseClicked

    private void JT_rutKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JT_rutKeyTyped
        JT_rut.addKeyListener(new KeyAdapter() {
            public void keyReleased(final KeyEvent e) {
                filtroRut();
            }
        });
        trsfiltro = new TableRowSorter(modeloTablaCliente);
        tablaClientes.setRowSorter(trsfiltro);
    }//GEN-LAST:event_JT_rutKeyTyped

    private void JT_nombreFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_JT_nombreFocusLost
        JT_nombre.setFont(new java.awt.Font("Tahoma", 2, 11));
        JT_nombre.setForeground(new java.awt.Color(153, 153, 153));
        JT_nombre.setText("Buscar por nombre");
    }//GEN-LAST:event_JT_nombreFocusLost

    private void JT_nombreMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JT_nombreMouseClicked
        JT_nombre.setText("");
        JT_nombre.setFont(new java.awt.Font("Tahoma", 0, 11));
        JT_nombre.setForeground(new java.awt.Color(0, 0, 0));
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
        trsfiltro = new TableRowSorter(modeloTablaCliente);
        tablaClientes.setRowSorter(trsfiltro);

    }//GEN-LAST:event_JT_nombreKeyTyped

    private void JT_paternoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_JT_paternoFocusLost
        JT_paterno.setFont(new java.awt.Font("Tahoma", 2, 11));
        JT_paterno.setForeground(new java.awt.Color(153, 153, 153));
        JT_paterno.setText("Buscar por paterno");
    }//GEN-LAST:event_JT_paternoFocusLost

    private void JT_paternoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JT_paternoMouseClicked
        JT_paterno.setText("");
        JT_paterno.setFont(new java.awt.Font("Tahoma", 0, 11));
        JT_paterno.setForeground(new java.awt.Color(0, 0, 0));
    }//GEN-LAST:event_JT_paternoMouseClicked

    private void JT_paternoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JT_paternoKeyTyped
        JT_paterno.addKeyListener(new KeyAdapter() {
            public void keyReleased(final KeyEvent e) {
                filtroPaterno();
            }
        });
        trsfiltro = new TableRowSorter(modeloTablaCliente);
        tablaClientes.setRowSorter(trsfiltro);

    }//GEN-LAST:event_JT_paternoKeyTyped

    private void JT_maternoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_JT_maternoFocusLost
        JT_materno.setFont(new java.awt.Font("Tahoma", 2, 11));
        JT_materno.setForeground(new java.awt.Color(153, 153, 153));
        JT_materno.setText("Buscar por materno");
    }//GEN-LAST:event_JT_maternoFocusLost

    private void JT_maternoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JT_maternoMouseClicked
        JT_materno.setText("");
        JT_materno.setFont(new java.awt.Font("Tahoma", 0, 11));
        JT_materno.setForeground(new java.awt.Color(0, 0, 0));
    }//GEN-LAST:event_JT_maternoMouseClicked

    private void JT_maternoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JT_maternoKeyTyped
        JT_materno.addKeyListener(new KeyAdapter() {
            public void keyReleased(final KeyEvent e) {
                filtroMaterno();
            }
        });
        trsfiltro = new TableRowSorter(modeloTablaCliente);
        tablaClientes.setRowSorter(trsfiltro);
    }//GEN-LAST:event_JT_maternoKeyTyped

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
        trsfiltro = new TableRowSorter(modeloTablaVehiculo);
        tablaVehiculo.setRowSorter(trsfiltro);
    }//GEN-LAST:event_JT_patenteKeyTyped

    private void JT_añoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_JT_añoFocusLost
        JT_año.setFont(new java.awt.Font("Tahoma", 2, 11));
        JT_año.setForeground(new java.awt.Color(153, 153, 153));
        JT_año.setText("Buscar por año");
    }//GEN-LAST:event_JT_añoFocusLost

    private void JT_añoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JT_añoMouseClicked
        JT_año.setText("");
        JT_año.setFont(new java.awt.Font("Tahoma", 0, 11));
        JT_año.setForeground(new java.awt.Color(0, 0, 0));
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
        trsfiltro = new TableRowSorter(modeloTablaVehiculo);
        tablaVehiculo.setRowSorter(trsfiltro);
    }//GEN-LAST:event_JT_añoKeyTyped

    private void JT_kmsFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_JT_kmsFocusLost
        JT_kms.setFont(new java.awt.Font("Tahoma", 2, 11));
        JT_kms.setForeground(new java.awt.Color(153, 153, 153));
        JT_kms.setText("Buscar por kms");
    }//GEN-LAST:event_JT_kmsFocusLost

    private void JT_kmsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JT_kmsMouseClicked
        JT_kms.setText("");
        JT_kms.setFont(new java.awt.Font("Tahoma", 0, 11));
        JT_kms.setForeground(new java.awt.Color(0, 0, 0));
    }//GEN-LAST:event_JT_kmsMouseClicked

    private void JT_kmsKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JT_kmsKeyTyped
        JT_kms.addKeyListener(new KeyAdapter() {
            public void keyReleased(final KeyEvent e) {
                filtroKms();
            }
        });
        trsfiltro = new TableRowSorter(modeloTablaVehiculo);
        tablaVehiculo.setRowSorter(trsfiltro);
    }//GEN-LAST:event_JT_kmsKeyTyped

    private void JT_vinFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_JT_vinFocusLost
        JT_vin.setFont(new java.awt.Font("Tahoma", 2, 11));
        JT_vin.setForeground(new java.awt.Color(153, 153, 153));
        JT_vin.setText("Buscar por vin");
    }//GEN-LAST:event_JT_vinFocusLost

    private void JT_vinMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JT_vinMouseClicked
        JT_vin.setText("");
        JT_vin.setFont(new java.awt.Font("Tahoma", 0, 11));
        JT_vin.setForeground(new java.awt.Color(0, 0, 0));
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
        trsfiltro = new TableRowSorter(modeloTablaVehiculo);
        tablaVehiculo.setRowSorter(trsfiltro);
    }//GEN-LAST:event_JT_vinKeyTyped

    private void JT_colorFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_JT_colorFocusLost
        JT_color.setFont(new java.awt.Font("Tahoma", 2, 11));
        JT_color.setForeground(new java.awt.Color(153, 153, 153));
        JT_color.setText("Buscar por color");
    }//GEN-LAST:event_JT_colorFocusLost

    private void JT_colorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JT_colorMouseClicked
        JT_color.setText("");
        JT_color.setFont(new java.awt.Font("Tahoma", 0, 11));
        JT_color.setForeground(new java.awt.Color(0, 0, 0));
    }//GEN-LAST:event_JT_colorMouseClicked

    private void JT_colorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JT_colorKeyTyped
        JT_color.addKeyListener(new KeyAdapter() {
            public void keyReleased(final KeyEvent e) {
                filtroColor();
            }
        });
        trsfiltro = new TableRowSorter(modeloTablaVehiculo);
        tablaVehiculo.setRowSorter(trsfiltro);
    }//GEN-LAST:event_JT_colorKeyTyped

    private void JT_marcaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_JT_marcaFocusLost
        JT_marca.setFont(new java.awt.Font("Tahoma", 2, 11));
        JT_marca.setForeground(new java.awt.Color(153, 153, 153));
        JT_marca.setText("Buscar por marca");
    }//GEN-LAST:event_JT_marcaFocusLost

    private void JT_marcaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JT_marcaMouseClicked
        JT_marca.setText("");
        JT_marca.setFont(new java.awt.Font("Tahoma", 0, 11));
        JT_marca.setForeground(new java.awt.Color(0, 0, 0));
    }//GEN-LAST:event_JT_marcaMouseClicked

    private void JT_marcaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JT_marcaKeyTyped
        JT_marca.addKeyListener(new KeyAdapter() {
            public void keyReleased(final KeyEvent e) {
                filtroMarca();
            }
        });
        trsfiltro = new TableRowSorter(modeloTablaVehiculo);
        tablaVehiculo.setRowSorter(trsfiltro);
    }//GEN-LAST:event_JT_marcaKeyTyped

    private void JT_modeloFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_JT_modeloFocusLost
        JT_modelo.setFont(new java.awt.Font("Tahoma", 2, 11));
        JT_modelo.setForeground(new java.awt.Color(153, 153, 153));
        JT_modelo.setText("Buscar por modelo");
    }//GEN-LAST:event_JT_modeloFocusLost

    private void JT_modeloMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JT_modeloMouseClicked
        JT_modelo.setText("");
        JT_modelo.setFont(new java.awt.Font("Tahoma", 0, 11));
        JT_modelo.setForeground(new java.awt.Color(0, 0, 0));
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
        trsfiltro = new TableRowSorter(modeloTablaVehiculo);
        tablaVehiculo.setRowSorter(trsfiltro);
    }//GEN-LAST:event_JT_modeloKeyTyped

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

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        Ingresar_trabajador a = new Ingresar_trabajador();
        a.setVisible(true);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        Modificar_trabajador a = new Modificar_trabajador();
        a.setVisible(true);
    }//GEN-LAST:event_jButton4ActionPerformed

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
    private javax.swing.JTextField JT_año;
    private javax.swing.JTextField JT_cargo;
    private javax.swing.JTextField JT_color;
    private javax.swing.JTextField JT_componente;
    private javax.swing.JTextField JT_insumo;
    private javax.swing.JTextField JT_kms;
    private javax.swing.JTextField JT_marca;
    private javax.swing.JTextField JT_materno;
    private javax.swing.JTextField JT_maternotrabajador;
    private javax.swing.JTextField JT_modelo;
    private javax.swing.JTextField JT_nombre;
    private javax.swing.JTextField JT_nombretrabajador;
    private javax.swing.JTextField JT_patente;
    private javax.swing.JTextField JT_paterno;
    private javax.swing.JTextField JT_paternotrabajador;
    private javax.swing.JTextField JT_rut;
    private javax.swing.JTextField JT_ruttrabajador;
    private javax.swing.JTextField JT_vin;
    private javax.swing.JLabel LBL_solicitud;
    private javax.swing.JLabel LBL_solicitud1;
    private javax.swing.JLabel LBL_solicitud2;
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
    private javax.swing.JLabel lblFecha;
    private javax.swing.JLabel lblFecha1;
    private javax.swing.JLabel lblFecha2;
    private javax.swing.JTable tablaClientes;
    private javax.swing.JTable tablaInsumoaux;
    private javax.swing.JTable tablaInsumos;
    private javax.swing.JTable tablaServicio;
    private javax.swing.JTable tablaServicioaux;
    private javax.swing.JTable tablaTrabajador;
    private javax.swing.JTable tablaVehiculo;
    // End of variables declaration//GEN-END:variables
}
