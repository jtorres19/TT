package racadauto;

import Conexion.Conexion;
import com.mysql.jdbc.Statement;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class Modificar_vehiculo extends javax.swing.JFrame {

    private Statement sentencia;
    Conexion con = new Conexion();
    java.sql.Connection cn = (java.sql.Connection) con.getConnection();
    private String msj;
    DefaultTableModel modeloTabla;
    private TableRowSorter trsfiltro;
    String filtro;

    public Modificar_vehiculo() {
        modeloTabla = new DefaultTableModel(null, getColumnas());
        setFilas();
        initComponents();
        llenarCombo();
    }

    private String[] getColumnas() {

        String columna[] = new String[]{"PATENTE", "AÑO", "KILOMETRAJE", "V.I.N.", "COLOR", "MARCA", "MODELO", "NOMBRE PROPIETARIO", "APELLIDO PATERNO", "APELLIDO MATERNO"};

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
    
    public void clean() {
        txt_year.setText("");
        txt_color.setText("");
        txt_kms.setText("");
        txt_vin.setText("");
        LBL_rut.setText("");
        LBL_patente.setText("");
    }

    void limpiaTabla() {
        if (modeloTabla.getRowCount() > 0) {
            do {
                modeloTabla.getRowCount();
                modeloTabla.removeRow(0);
            } while (modeloTabla.getRowCount() != 0);
        }
    }

    public void llenarCombo() {
        String nom;
        cmb_marca.removeAllItems();
        try {
            sentencia = (com.mysql.jdbc.Statement) cn.createStatement();
            ResultSet lista = sentencia.executeQuery("SELECT nombre FROM marca");
            while (lista.next()) {
                cmb_marca.addItem(lista.getString("nombre"));
            }
        } catch (SQLException ed) {
            msj = "no se pudo seleccionar";
        }
    }

    public void llenarCombo2() {
        cmb_model.removeAllItems();
        String marca = (String) cmb_marca.getSelectedItem();
        int marca2 = 0;
        try {
            sentencia = (com.mysql.jdbc.Statement) cn.createStatement();
            ResultSet rs = sentencia.executeQuery("SELECT id_marca FROM marca WHERE nombre = '" + marca + "'");
            while (rs.next()) {
                marca2 = rs.getInt("id_marca");
            }
        } catch (SQLException s) {
            msj = "Error con Marca";
        }
        try {
            sentencia = (com.mysql.jdbc.Statement) cn.createStatement();
            ResultSet lista = sentencia.executeQuery("SELECT nombre FROM modelo WHERE id_marca = " + marca2 + "");
            while (lista.next()) {
                cmb_model.addItem(lista.getString("nombre"));
            }
        } catch (SQLException ed) {
            msj = "no se pudo seleccionar";
        }
    }

   
    public int verificar() {
        int cont = 0;
        String year = txt_year.getText().trim();
        String kms = txt_kms.getText().trim();
        String vin = txt_vin.getText().toUpperCase().trim();
        String color = txt_color.getText().toUpperCase().trim();
        String patente2 = "";
        String patente = jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString();
        String marca = cmb_marca.getSelectedItem().toString();
        String modelo = cmb_model.getSelectedItem().toString();

        if (patente.equals("")
                || (year.equals(""))
                || (kms.equals(""))
                || (vin.equals(""))
                || (color.equals(""))) {
            JOptionPane.showMessageDialog(null,
                    "ERROR, dejó una casilla vacía", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            cont++;
        } else if(patente.equals(jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString())&& 
                    year.equals(jTable1.getValueAt(jTable1.getSelectedRow(), 1).toString()) &&
                    kms.equals(jTable1.getValueAt(jTable1.getSelectedRow(), 2).toString()) &&
                    vin.equals(jTable1.getValueAt(jTable1.getSelectedRow(), 3).toString()) &&
                    color.equals(jTable1.getValueAt(jTable1.getSelectedRow(), 4).toString()) &&
                    marca.equals(jTable1.getValueAt(jTable1.getSelectedRow(), 5).toString()) &&
                    modelo.equals(jTable1.getValueAt(jTable1.getSelectedRow(), 6).toString())){
            
            JOptionPane.showMessageDialog(null,
                    "ERROR, no se ha MODIFICADO nada", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            cont++;
        
        }else if (jTable1.getSelectedRow() == -1){
            JOptionPane.showMessageDialog(null,
                    "ERROR, no se ha seleccionado ningun vehiculo", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            cont++;
        }
        
        
        if (year.length() >= 5) {
            JOptionPane.showMessageDialog(null,
                    "ERROR, AÑO <10000 y >0", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            cont++;
        }

        if (kms.length() >= 8) {
            JOptionPane.showMessageDialog(null,
                    "ERROR, KILOMETRAJE <10000000 y >0", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            cont++;
        }

        if (vin.length() >= 21) {
            JOptionPane.showMessageDialog(null,
                    "ERROR, V.I.N maximo 20 caracteres", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            cont++;
        }

        if (color.length() >= 11) {
            JOptionPane.showMessageDialog(null,
                    "ERROR, COLOR maximo 10 caracteres", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            cont++;
        }

        return cont;

    }
    
    public int verificar2() {
        int yes = 0;
        String paten = "", paten2 = "";
        String patente = jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString().trim();
        try {
            sentencia = (com.mysql.jdbc.Statement) cn.createStatement();
            ResultSet rs = sentencia.executeQuery("SELECT patente FROM vehiculo WHERE patente = '" + patente + "'");
            while (rs.next()) {
                paten = rs.getString("patente");
            }
        } catch (SQLException eg) {
            msj = "Error con su Solicitud";
        }
        try {
            sentencia = (com.mysql.jdbc.Statement) cn.createStatement();
            ResultSet rs = sentencia.executeQuery("SELECT * FROM solicitud_servicio "
                    + "WHERE patente = '" + paten + "'");
            while (rs.next()) {
                paten2 = rs.getString("patente");
                if (paten.equals(paten2)) {
                    yes ++;
                }
            }

        } catch (SQLException t) {
            msj = "Error con su Solicitud";
        }
        
        
        if (yes > 0) {
            JOptionPane.showMessageDialog(null,
                    "ERROR, PATENTE referenciada en otras tablas no se puede eliminar", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
        }
        return yes;
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

        jLabel9 = new javax.swing.JLabel();
        JB_cancel = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        JT_patente = new javax.swing.JTextField();
        JT_nombre = new javax.swing.JTextField();
        JT_marca = new javax.swing.JTextField();
        JT_modelo = new javax.swing.JTextField();
        BTN_Del = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        LBL_rut = new javax.swing.JLabel();
        LBL_patente = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txt_year = new javax.swing.JTextField();
        txt_color = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        cmb_model = new javax.swing.JComboBox<>();
        cmb_marca = new javax.swing.JComboBox<>();
        txt_kms = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txt_vin = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        JT_vin = new javax.swing.JTextField();
        JT_color = new javax.swing.JTextField();
        JT_año = new javax.swing.JTextField();
        JT_kms = new javax.swing.JTextField();
        JT_paterno = new javax.swing.JTextField();
        JT_materno = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("MODIFICAR VEHICULO");
        setMinimumSize(new java.awt.Dimension(500, 300));

        jLabel9.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel9.setText("MANTENEDOR DE VEHICULO");

        JB_cancel.setText("\u2B8C");
        JB_cancel.setToolTipText("Volver");
        JB_cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JB_cancelActionPerformed(evt);
            }
        });

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

        JT_nombre.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        JT_nombre.setForeground(new java.awt.Color(153, 153, 153));
        JT_nombre.setText("Buscar por nombre cliente");
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

        BTN_Del.setText("\uD83D\uDDD1");
        BTN_Del.setToolTipText("Borrar vehiculo seleccionado");
        BTN_Del.setPreferredSize(new java.awt.Dimension(100, 35));
        BTN_Del.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_DelActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel10.setText("MODIFICAR");
        jLabel10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setText("Patente :");

        jLabel8.setText("RUT Cliente :");

        txt_year.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_yearKeyTyped(evt);
            }
        });

        txt_color.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_colorKeyTyped(evt);
            }
        });

        jLabel2.setText("Año :");

        jLabel5.setText("Color :");

        jLabel6.setText("Modelo :");

        jLabel7.setText("Marca :");

        cmb_model.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmb_modelActionPerformed(evt);
            }
        });

        cmb_marca.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmb_marcaItemStateChanged(evt);
            }
        });

        txt_kms.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_kmsKeyTyped(evt);
            }
        });

        jLabel3.setText("Kilometraje :");

        txt_vin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_vinKeyTyped(evt);
            }
        });

        jLabel4.setText("V.I.N. :");

        jButton1.setText("\uD83D\uDDAB");
        jButton1.setToolTipText("Modificar Vehiculo");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(txt_year, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(LBL_rut, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(txt_kms, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(91, 91, 91)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(LBL_patente, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel4))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txt_color, javax.swing.GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE)
                                    .addComponent(txt_vin))))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(cmb_model, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cmb_marca, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10)
                .addGap(11, 11, 11)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(cmb_marca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(cmb_model, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1)
                            .addComponent(LBL_patente, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel8)
                                .addComponent(LBL_rut, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(11, 11, 11)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txt_year, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)
                            .addComponent(txt_color, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txt_kms, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(txt_vin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/racadauto/Images/ok.jpg"))); // NOI18N
        jLabel11.setText("jLabel3");
        jLabel11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

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
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(26, 26, 26)
                                        .addComponent(jLabel9))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(JT_patente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(JT_marca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(JT_modelo, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(JT_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(JT_paterno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(JT_materno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 668, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(JB_cancel, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BTN_Del, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(JT_año, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(JT_kms, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)
                        .addComponent(JT_vin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(46, 46, 46)
                        .addComponent(JT_color, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(BTN_Del, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(119, 119, 119)
                        .addComponent(JB_cancel)
                        .addGap(94, 94, 94))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel9)
                            .addComponent(jLabel11))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(13, 13, 13)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(JT_patente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(JT_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(JT_marca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(JT_modelo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(JT_paterno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(JT_materno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(JT_año, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(JT_kms, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(JT_vin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(JT_color, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(7, 7, 7)))
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void JB_cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JB_cancelActionPerformed
        this.dispose();
    }//GEN-LAST:event_JB_cancelActionPerformed

    private void cmb_modelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmb_modelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmb_modelActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        String patente = jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString();
        LBL_patente.setText(patente);

        String anno = jTable1.getValueAt(jTable1.getSelectedRow(), 1).toString();
        txt_year.setText(anno);

        String kms = jTable1.getValueAt(jTable1.getSelectedRow(), 2).toString();
        txt_kms.setText(kms);

        String vin = jTable1.getValueAt(jTable1.getSelectedRow(), 3).toString();
        txt_vin.setText(vin);

        String color = jTable1.getValueAt(jTable1.getSelectedRow(), 4).toString();
        txt_color.setText(color);

        String nombre = jTable1.getValueAt(jTable1.getSelectedRow(), 7).toString();
        String paterno = jTable1.getValueAt(jTable1.getSelectedRow(), 8).toString();
        String materno = jTable1.getValueAt(jTable1.getSelectedRow(), 9).toString();
        String rut = "";
        try {
            sentencia = (com.mysql.jdbc.Statement) cn.createStatement();
            ResultSet rs = sentencia.executeQuery("SELECT rut_cliente "
                    + "FROM cliente "
                    + "WHERE nombre = '" + nombre + "' AND ape_paterno = '" + paterno + "' AND ape_materno = '" + materno + "'");
            while (rs.next()) {
                rut = rs.getString("rut_cliente");
            }
        } catch (Exception e) {
        }

        LBL_rut.setText(rut);

        String marca = jTable1.getValueAt(jTable1.getSelectedRow(), 5).toString();
        cmb_marca.setSelectedItem(marca);

        String modelo = jTable1.getValueAt(jTable1.getSelectedRow(), 6).toString();
        cmb_model.setSelectedItem(modelo);

    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (verificar() == 0) {
            
            String rut = LBL_rut.getText().trim();
            String patente = LBL_patente.getText().toUpperCase().trim();
            int year = Integer.parseInt(txt_year.getText().trim());
            int kms = Integer.parseInt(txt_kms.getText().trim());
            String vin = txt_vin.getText().toUpperCase().trim();
            String color = txt_color.getText().toUpperCase().trim();
            String client, marca, model;
            marca = (String) cmb_marca.getSelectedItem();
            model = (String) cmb_model.getSelectedItem();
            int client2 = 0;
            int marca2 = 0;
            int model2 = 0;
            /*try {
                sentencia=(com.mysql.jdbc.Statement)cn.createStatement();
                ResultSet rs = sentencia.executeQuery("SELECT rut_cliente FROM cliente WHERE  nombre = '" + client + "'");
                while (rs.next()) {
                    client2 = rs.getInt("rut_cliente");
                }      
            } catch (SQLException s) {
                msj = "Error con Cliente";
            }*/
            try {
                sentencia = (com.mysql.jdbc.Statement) cn.createStatement();
                ResultSet rs = sentencia.executeQuery("SELECT id_marca FROM marca WHERE nombre = '" + marca + "'");
                while (rs.next()) {
                    marca2 = rs.getInt("id_marca");
                }
            } catch (SQLException s) {
                JOptionPane.showMessageDialog(null,
                    "Marca mal escrita", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            }
            try {
                sentencia = (com.mysql.jdbc.Statement) cn.createStatement();
                ResultSet rs = sentencia.executeQuery("SELECT id_modelo FROM modelo WHERE nombre = '" + model + "'");
                while (rs.next()) {
                    model2 = rs.getInt("id_modelo");
                }
            } catch (SQLException s) {
                JOptionPane.showMessageDialog(null,
                    "Modelo mal escrito", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            }
            String sql = "UPDATE vehiculo "
                    + "SET año ='" + year + "',"
                    + "kms = '" + kms + "',"
                    + "vin = '" + vin + "',"
                    + "color = '" + color + "',"
                    + "id_marca = '" + marca2 + "',"
                    + "id_modelo = '" + model2 + "'"
                    + "WHERE patente = '" + patente + "'";
            try {
                sentencia.executeUpdate(sql);
                JOptionPane.showMessageDialog(null,
                    "Datos Guardados", "Exito",
                    JOptionPane.INFORMATION_MESSAGE);
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null,
                    "Datos no ingresados", "Error",
                    JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null,
                    "Datos NO Ingresados", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }

        clean();
        limpiaTabla();
        setFilas();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void cmb_marcaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmb_marcaItemStateChanged
        llenarCombo2();
    }//GEN-LAST:event_cmb_marcaItemStateChanged

    private void txt_kmsKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_kmsKeyTyped
        char validar = evt.getKeyChar();

        if (!Character.isDigit(validar) && validar != evt.VK_BACK_SPACE) {
            getToolkit().beep();
            evt.consume();

            JOptionPane.showMessageDialog(null,
                    "ERROR, KILOMETROS solo puede ser numerico", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_txt_kmsKeyTyped

    private void txt_yearKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_yearKeyTyped
        char validar = evt.getKeyChar();

        if (!Character.isDigit(validar) && validar != evt.VK_BACK_SPACE) {
            getToolkit().beep();
            evt.consume();

            JOptionPane.showMessageDialog(null,
                    "ERROR, AÑO solo puede ser numerico", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_txt_yearKeyTyped

    private void txt_vinKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_vinKeyTyped
        char validar = evt.getKeyChar();

        if (!Character.isLetterOrDigit(validar) && validar != evt.VK_BACK_SPACE) {
            getToolkit().beep();
            evt.consume();

            JOptionPane.showMessageDialog(null,
                    "ERROR, V.I.N solo puede ser alfanumerico", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_txt_vinKeyTyped

    private void txt_colorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_colorKeyTyped
        char validar = evt.getKeyChar();

        if (!Character.isLetterOrDigit(validar) && validar != evt.VK_BACK_SPACE) {
            getToolkit().beep();
            evt.consume();

            JOptionPane.showMessageDialog(null,
                    "ERROR, COLOR solo puede ser alfabetico", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_txt_colorKeyTyped

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

    private void JT_nombreFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_JT_nombreFocusLost
        JT_nombre.setFont(new java.awt.Font("Tahoma",2,11));
        JT_nombre.setForeground(new java.awt.Color(153,153,153));
        JT_nombre.setText("Buscar por nombre cliente");
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

    private void BTN_DelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_DelActionPerformed

        if (jTable1.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(null,
                "ERROR, No se ha seleccionado ninguna fila", "ERROR",
                JOptionPane.ERROR_MESSAGE);
        }

        String pate = jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString().trim();
        String nombre = jTable1.getValueAt(jTable1.getSelectedRow(), 7).toString().trim();
        String paterno = jTable1.getValueAt(jTable1.getSelectedRow(), 8).toString().trim();
        String materno = jTable1.getValueAt(jTable1.getSelectedRow(), 9).toString().trim();

        int i = JOptionPane.showConfirmDialog(this,
            "¿Realmente desea eliminar el vehiculo patente " + pate + " del cliente " + nombre + " " + paterno + " " + materno + " ?", "Confirmar Eliminación",
            JOptionPane.YES_NO_OPTION);

        String patente = "";
        try {
            sentencia = (com.mysql.jdbc.Statement) cn.createStatement();
            ResultSet rs = sentencia.executeQuery("SELECT patente FROM vehiculo WHERE patente = '" + pate + "'");
            while (rs.next()) {
                patente = rs.getString("patente");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                "ERROR, No se pudo encontrar vehiculo?", "ERROR",
                JOptionPane.ERROR_MESSAGE);
        }

        if (verificar2() == 0 && i == 0) {
            String sql = "DELETE FROM vehiculo WHERE patente = '" + patente + "'";
            try {
                sentencia.executeUpdate(sql);
                JOptionPane.showMessageDialog(null,
                    "Vehiculo Eliminado", "Exito",
                    JOptionPane.INFORMATION_MESSAGE);
            } catch (SQLException ee) {
                JOptionPane.showMessageDialog(null,
                "No se pudo borrar!", "ERROR",
                JOptionPane.ERROR_MESSAGE);
            }
        }

        clean();
        limpiaTabla();
        setFilas();

    }//GEN-LAST:event_BTN_DelActionPerformed

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
 /*try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Ingresar_empleado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Ingresar_empleado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Ingresar_empleado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Ingresar_empleado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }*/
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Modificar_vehiculo().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BTN_Del;
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
    private javax.swing.JLabel LBL_patente;
    private javax.swing.JLabel LBL_rut;
    private javax.swing.JComboBox<String> cmb_marca;
    private javax.swing.JComboBox<String> cmb_model;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField txt_color;
    private javax.swing.JTextField txt_kms;
    private javax.swing.JTextField txt_vin;
    private javax.swing.JTextField txt_year;
    // End of variables declaration//GEN-END:variables
}
