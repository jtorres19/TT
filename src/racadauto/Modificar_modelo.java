package racadauto;

import Conexion.Conexion;
import com.mysql.jdbc.Statement;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class Modificar_modelo extends javax.swing.JFrame {

    private Statement sentencia;
    Conexion con = new Conexion();
    java.sql.Connection cn = (java.sql.Connection) con.getConnection();
    private String msj;
    DefaultTableModel modeloTabla;
    private TableRowSorter trsfiltro;
    String filtro;
    

    public Modificar_modelo() {
        modeloTabla = new DefaultTableModel(null, getColumnas());
        setFilas();
        initComponents();
        llenarcombos();
    }

    private String[] getColumnas() {

        String columna[] = new String[]{"ID MODELO", "NOMBRE", "MARCA", "TIPO VEHICULO", "TIPO COMBUSTIBLE", "TIPO MOTOR"};

        return columna;
    }

    private void setFilas() {
        try {
            sentencia = (com.mysql.jdbc.Statement) cn.createStatement();
            ResultSet lista = sentencia.executeQuery("SELECT o.id_modelo,o.nombre,a.nombre,v.nombre,c.nombre,m.nombre "
                    + "FROM marca a, modelo o,tipo_vehiculo v,tipo_combustible c,tipo_motor m "
                    + "WHERE a.id_marca = o.id_marca AND o.id_tipo_vehiculo = v.id_tipo_vehiculo AND o.id_tipo_combustible = c.id_tipo_combustible AND o.id_tipo_motor = m.id_tipo_motor");
            Object datos[] = new Object[6];
            while (lista.next()) {
                for (int i = 0; i < 6; i++) {
                    datos[i] = lista.getObject(i + 1);
                }
                modeloTabla.addRow(datos);
            }
        } catch (Exception e) {
            msj = "No se pudo llenar tabla";
        }
    }
    
    public void filtroMarca() {

        filtro = JT_marca.getText().toUpperCase();
        int columna = 2;
        trsfiltro.setRowFilter(RowFilter.regexFilter(JT_marca.getText().toUpperCase(), columna));
        
    }
    
    public void filtroNombre() {

        filtro = JT_nombre.getText().toUpperCase();
        int columna = 1;
        trsfiltro.setRowFilter(RowFilter.regexFilter(JT_nombre.getText().toUpperCase(), columna));
    }
    
    public void filtroTipo() {

        filtro = JT_tipo.getText().toUpperCase();
        int columna = 3;
        trsfiltro.setRowFilter(RowFilter.regexFilter(JT_tipo.getText().toUpperCase(), columna));
    }

    public void filtroCombustible() {

        filtro = JT_combustible.getText().toUpperCase();
        int columna = 4;
        trsfiltro.setRowFilter(RowFilter.regexFilter(JT_combustible.getText().toUpperCase(), columna));
    }
    
    
    public void filtroMotor() {

        filtro = JT_motor.getText().toUpperCase();
        int columna = 5;
        trsfiltro.setRowFilter(RowFilter.regexFilter(JT_motor.getText().toUpperCase(), columna));
    }

    void limpiaTabla() {
        if (modeloTabla.getRowCount() > 0) {
            do {
                modeloTabla.getRowCount();
                modeloTabla.removeRow(0);
            } while (modeloTabla.getRowCount() != 0);
        }
    }

    public void llenarcombos() {
        cmb_tipo.removeAllItems();
        cmb_marc.removeAllItems();
        cmb_comb.removeAllItems();
        cmb_motor.removeAllItems();
        try {
            sentencia = (com.mysql.jdbc.Statement) cn.createStatement();
            ResultSet lista = sentencia.executeQuery("SELECT nombre FROM tipo_vehiculo");
            while (lista.next()) {
                cmb_tipo.addItem(lista.getString("nombre"));
            }
        } catch (SQLException ed) {
            msj = "no se pudo seleccionar";
        }
        try {
            sentencia = (com.mysql.jdbc.Statement) cn.createStatement();
            ResultSet lista = sentencia.executeQuery("SELECT nombre FROM marca");
            while (lista.next()) {
                cmb_marc.addItem(lista.getString("nombre"));
            }
        } catch (SQLException ed) {
            msj = "no se pudo seleccionar";
        }
        try {
            sentencia = (com.mysql.jdbc.Statement) cn.createStatement();
            ResultSet lista = sentencia.executeQuery("SELECT nombre FROM tipo_combustible");
            while (lista.next()) {
                cmb_comb.addItem(lista.getString("nombre"));
            }
        } catch (SQLException ed) {
            msj = "no se pudo seleccionar";
        }
        try {
            sentencia = (com.mysql.jdbc.Statement) cn.createStatement();
            ResultSet lista = sentencia.executeQuery("SELECT nombre FROM tipo_motor");
            while (lista.next()) {
                cmb_motor.addItem(lista.getString("nombre"));
            }
        } catch (SQLException ed) {
            msj = "no se pudo seleccionar";
        }
    }

    public int verificar() {

        int ver = 0;
        String nombre, tipo, combustible, marca, motor;
        nombre = txt_nom.getText().toUpperCase().trim();
        tipo = cmb_tipo.getSelectedItem().toString().trim();
        combustible = cmb_comb.getSelectedItem().toString().trim();
        marca = cmb_marc.getSelectedItem().toString().trim();
        motor = cmb_motor.getSelectedItem().toString().trim();

        if (nombre.equals("")) {
            JOptionPane.showMessageDialog(null,
                    "Error, dejó la casilla vacía", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            ver++;
        }
        if (jTable2.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(null,
                    "ERROR, No se ha seleccionado ninguna fila", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            ver++;
        } else if (nombre.equals(jTable2.getValueAt(jTable2.getSelectedRow(), 1).toString()) &&
                    marca.equals(jTable2.getValueAt(jTable2.getSelectedRow(), 2).toString()) &&
                    tipo.equals(jTable2.getValueAt(jTable2.getSelectedRow(), 3).toString()) &&
                    combustible.equals(jTable2.getValueAt(jTable2.getSelectedRow(), 4).toString()) &&
                    motor.equals(jTable2.getValueAt(jTable2.getSelectedRow(), 5).toString())) {
            JOptionPane.showMessageDialog(null,
                    "ERROR, No se ha MODIFICADO nada", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
        }

        if (nombre.length() > 15) {
            JOptionPane.showMessageDialog(null,
                    "Nombre no puede exceder 15 caracteres!", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            ver++;
        }
        return ver;
    }

    //para que se usa este??
    public int verificar2() {
        int ver2 = 0;
        int mod = 0;
        int mod2 = 0;
        int nom = Integer.parseInt(jTable2.getValueAt(jTable2.getSelectedRow(), 1).toString());
        try {
            sentencia = (com.mysql.jdbc.Statement) cn.createStatement();
            ResultSet rs = sentencia.executeQuery("SELECT * FROM vehiculo");
            while (rs.next()) {
                mod = rs.getInt("id_modelo");
                if (mod == nom) {
                    ver2++;
                }
            }
        } catch (SQLException y) {
            JOptionPane.showMessageDialog(null,
                    "Problema con Vehiculo", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
        }
        try {
            sentencia = (com.mysql.jdbc.Statement) cn.createStatement();
            ResultSet sr = sentencia.executeQuery("SELECT * FROM repuestos");
            while (sr.next()) {
                mod2 = sr.getInt("id_modelo");
                if (mod2 == nom) {
                    ver2++;
                }
            }
        } catch (SQLException s) {
            JOptionPane.showMessageDialog(null,
                    "Problema con Repuestos", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
        }
        return ver2;
    }
    
    public int verificar3() {
        int yes = 0;
        int modelo = 0;
        int modelo2 = 0;
        String nom = jTable2.getValueAt(jTable2.getSelectedRow(), 1).toString().trim();
        try {
            sentencia = (com.mysql.jdbc.Statement) cn.createStatement();
            ResultSet rs = sentencia.executeQuery("SELECT id_modelo FROM modelo WHERE nombre = '" + nom + "'");
            while (rs.next()) {
                modelo = rs.getInt("id_modelo");
            }
        } catch (SQLException eg) {
            msj = "Error con su Solicitud";
        }
        try {
            sentencia = (com.mysql.jdbc.Statement) cn.createStatement();
            ResultSet rs = sentencia.executeQuery("SELECT * FROM repuestos "
                    + "WHERE id_modelo = '" + modelo + "'");
            while (rs.next()) {
                modelo2 = rs.getInt("id_modelo");
                if (modelo == modelo2) {
                    yes += rs.getInt("id_modelo");
                }
            }

        } catch (SQLException t) {
            msj = "Error con su Solicitud";
        }
        
        try {
            sentencia = (com.mysql.jdbc.Statement) cn.createStatement();
            ResultSet rs = sentencia.executeQuery("SELECT * FROM vehiculo "
                    + "WHERE id_modelo = '" + modelo + "'");
            while (rs.next()) {
                modelo2 = rs.getInt("id_modelo");
                if (modelo == modelo2) {
                    yes += rs.getInt("id_modelo");
                }
            }

        } catch (SQLException t) {
            msj = "Error con su Solicitud";
        }
        
        if (yes > 0) {
            JOptionPane.showMessageDialog(null,
                    "ERROR, MODELO referenciado en otras tablas no se puede eliminar", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
        }

        return yes;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        JB_cancel = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        JT_motor = new javax.swing.JTextField();
        JT_nombre = new javax.swing.JTextField();
        JT_marca = new javax.swing.JTextField();
        JT_tipo = new javax.swing.JTextField();
        JT_combustible = new javax.swing.JTextField();
        BTN_Del = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        cmb_tipo = new javax.swing.JComboBox<>();
        cmb_marc = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        cmb_comb = new javax.swing.JComboBox<>();
        cmb_motor = new javax.swing.JComboBox<>();
        txt_nom = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        jTable1.setModel(modeloTabla);
        jScrollPane1.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("MODIFICAR MODELO VEHICULO");

        jLabel9.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel9.setText("MANTENER MODELO VEHICULO");

        JB_cancel.setText("\u2B8C");
        JB_cancel.setToolTipText("Volver");
        JB_cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JB_cancelActionPerformed(evt);
            }
        });

        jTable2.setModel(modeloTabla);
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable2);

        JT_motor.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        JT_motor.setForeground(new java.awt.Color(153, 153, 153));
        JT_motor.setText("Buscar por motor");
        JT_motor.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                JT_motorFocusLost(evt);
            }
        });
        JT_motor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JT_motorMouseClicked(evt);
            }
        });
        JT_motor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                JT_motorKeyTyped(evt);
            }
        });

        JT_nombre.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        JT_nombre.setForeground(new java.awt.Color(153, 153, 153));
        JT_nombre.setText("Buscar por modelo");
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

        JT_tipo.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        JT_tipo.setForeground(new java.awt.Color(153, 153, 153));
        JT_tipo.setText("Buscar por tipo");
        JT_tipo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                JT_tipoFocusLost(evt);
            }
        });
        JT_tipo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JT_tipoMouseClicked(evt);
            }
        });
        JT_tipo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                JT_tipoKeyTyped(evt);
            }
        });

        JT_combustible.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        JT_combustible.setForeground(new java.awt.Color(153, 153, 153));
        JT_combustible.setText("Buscar por combustible");
        JT_combustible.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                JT_combustibleFocusLost(evt);
            }
        });
        JT_combustible.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JT_combustibleMouseClicked(evt);
            }
        });
        JT_combustible.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                JT_combustibleKeyTyped(evt);
            }
        });

        BTN_Del.setText("\uD83D\uDDD1");
        BTN_Del.setToolTipText("Eliminar Modelo");
        BTN_Del.setPreferredSize(new java.awt.Dimension(100, 35));
        BTN_Del.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_DelActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel6.setText("Motor :");

        jLabel1.setText("Marca :");

        jLabel2.setText("Nombre Modelo Vehiculo :");

        cmb_comb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmb_combActionPerformed(evt);
            }
        });

        txt_nom.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_nomKeyTyped(evt);
            }
        });

        jButton1.setText("\uD83D\uDDAB");
        jButton1.setToolTipText("Modificar Modelo");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel4.setText("Tipo :");

        jLabel5.setText("Combustible :");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("MODIFICAR");
        jLabel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(18, 18, 18)
                                .addComponent(cmb_tipo, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(26, 26, 26)
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cmb_motor, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cmb_comb, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(txt_nom, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(28, 28, 28)
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(cmb_marc, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(62, 62, 62)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txt_nom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmb_marc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmb_tipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel6)
                    .addComponent(cmb_motor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(cmb_comb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/racadauto/Images/ok.jpg"))); // NOI18N
        jLabel3.setText("jLabel3");
        jLabel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(90, 90, 90)
                        .addComponent(jLabel9))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 573, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(JB_cancel, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BTN_Del, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(JT_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(JT_marca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(JT_tipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(JT_combustible, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(JT_motor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(16, Short.MAX_VALUE))
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel9))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(84, 84, 84)
                        .addComponent(BTN_Del, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(61, 61, 61)
                        .addComponent(JB_cancel))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JT_tipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JT_combustible, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JT_motor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JT_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JT_marca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void JB_cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JB_cancelActionPerformed
        this.dispose();
    }//GEN-LAST:event_JB_cancelActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (verificar() == 0) {
            String nom = txt_nom.getText().toUpperCase().trim();
            String tipo = (String) cmb_tipo.getSelectedItem();
            String comb = (String) cmb_comb.getSelectedItem();
            String marc = (String) cmb_marc.getSelectedItem();
            String motor = (String) cmb_motor.getSelectedItem();
            int id = Integer.parseInt(jTable2.getValueAt(jTable2.getSelectedRow(), 0).toString());
            int motor1 = 0;
            int tipo1 = 0;
            int comb1 = 0;
            int marc1 = 0;
            try {
                sentencia = (com.mysql.jdbc.Statement) cn.createStatement();
                ResultSet rs = sentencia.executeQuery("SELECT id_tipo_vehiculo FROM tipo_vehiculo WHERE  nombre = '" + tipo + "'");
                while (rs.next()) {
                    tipo1 = rs.getInt("id_tipo_vehiculo");
                }
            } catch (SQLException s) {
                JOptionPane.showMessageDialog(null,
                    "Problema con Tipo", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            }
            try {
                sentencia = (com.mysql.jdbc.Statement) cn.createStatement();
                ResultSet rs = sentencia.executeQuery("SELECT id_tipo_motor FROM tipo_motor WHERE  nombre = '" + motor + "'");
                while (rs.next()) {
                    motor1 = rs.getInt("id_tipo_motor");
                }
            } catch (SQLException s) {
                JOptionPane.showMessageDialog(null,
                    "Problema con Motor", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            }
            try {
                sentencia = (com.mysql.jdbc.Statement) cn.createStatement();
                ResultSet rs = sentencia.executeQuery("SELECT id_tipo_combustible FROM tipo_combustible WHERE  nombre = '" + comb + "'");
                while (rs.next()) {
                    comb1 = rs.getInt("id_tipo_combustible");
                }
            } catch (SQLException s) {
                JOptionPane.showMessageDialog(null,
                    "Problema con Combustible", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            }
            try {
                sentencia = (com.mysql.jdbc.Statement) cn.createStatement();
                ResultSet rs = sentencia.executeQuery("SELECT id_marca FROM marca WHERE  nombre = '" + marc + "'");
                while (rs.next()) {
                    marc1 = rs.getInt("id_marca");
                }
            } catch (SQLException s) {
                JOptionPane.showMessageDialog(null,
                    "Problema con Cliente", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            }
            String sql = "UPDATE modelo "
                    + "SET id_marca ='" + marc1 + "',"
                    + "nombre = '" + nom + "',"
                    + "id_tipo_vehiculo = '" + tipo1 + "',"
                    + "id_tipo_combustible = '" + comb1 + "',"
                    + "id_tipo_motor = '" + motor1 + "',"
                    + "id_marca = '" + marc1 + "'"
                    + "WHERE id_modelo = '" + id + "'";
            try {
                sentencia.executeUpdate(sql);
                JOptionPane.showMessageDialog(null,
                    "Datos Modificados", "Exito",
                    JOptionPane.INFORMATION_MESSAGE);
                txt_nom.setText("");
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null,
                    "Datos NO Modificados", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null,
                    "Problema con lista", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
        }

        limpiaTabla();
        setFilas();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked

        String nombre = jTable2.getValueAt(jTable2.getSelectedRow(), 1).toString().toUpperCase();
        txt_nom.setText(nombre);

        String marca = jTable2.getValueAt(jTable2.getSelectedRow(), 2).toString();
        cmb_marc.setSelectedItem(marca);

        String tipo = jTable2.getValueAt(jTable2.getSelectedRow(), 3).toString();
        cmb_tipo.setSelectedItem(tipo);

        String combustible = jTable2.getValueAt(jTable2.getSelectedRow(), 4).toString();
        cmb_comb.setSelectedItem(combustible);

        String motor = jTable2.getValueAt(jTable2.getSelectedRow(), 5).toString();
        cmb_motor.setSelectedItem(motor);

    }//GEN-LAST:event_jTable2MouseClicked

    private void cmb_combActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmb_combActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmb_combActionPerformed

    private void txt_nomKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_nomKeyTyped
        char validar = evt.getKeyChar();

        if (!Character.isLetter(validar) && !Character.isDigit(validar) && validar != evt.VK_SPACE && validar != evt.VK_BACK_SPACE) {
            getToolkit().beep();
            evt.consume();

            JOptionPane.showMessageDialog(null,
                    "ERROR, MODELO solo puede ser alfanumerico", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_txt_nomKeyTyped

    private void JT_motorFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_JT_motorFocusLost
        JT_motor.setFont(new java.awt.Font("Tahoma",2,11));
        JT_motor.setForeground(new java.awt.Color(153,153,153));
        JT_motor.setText("Buscar por motor");
    }//GEN-LAST:event_JT_motorFocusLost

    private void JT_motorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JT_motorMouseClicked
        JT_motor.setText("");
        JT_motor.setFont(new java.awt.Font("Tahoma",0,11));
        JT_motor.setForeground(new java.awt.Color(0,0,0));
    }//GEN-LAST:event_JT_motorMouseClicked

    private void JT_motorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JT_motorKeyTyped
        JT_motor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(final KeyEvent e) {
                filtroMotor();
            }
        });
        trsfiltro = new TableRowSorter(modeloTabla);
        jTable2.setRowSorter(trsfiltro);
    }//GEN-LAST:event_JT_motorKeyTyped

    private void JT_nombreFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_JT_nombreFocusLost
        JT_nombre.setFont(new java.awt.Font("Tahoma",2,11));
        JT_nombre.setForeground(new java.awt.Color(153,153,153));
        JT_nombre.setText("Buscar por modelo");
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
        JT_nombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(final KeyEvent e) {
                filtroNombre();
            }
        });
        trsfiltro = new TableRowSorter(modeloTabla);
        jTable2.setRowSorter(trsfiltro);
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
        JT_marca.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(final KeyEvent e) {
                filtroMarca();
            }
        });
        trsfiltro = new TableRowSorter(modeloTabla);
        jTable2.setRowSorter(trsfiltro);
    }//GEN-LAST:event_JT_marcaKeyTyped

    private void JT_tipoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_JT_tipoFocusLost
        JT_tipo.setFont(new java.awt.Font("Tahoma",2,11));
        JT_tipo.setForeground(new java.awt.Color(153,153,153));
        JT_tipo.setText("Buscar por tipo");
    }//GEN-LAST:event_JT_tipoFocusLost

    private void JT_tipoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JT_tipoMouseClicked
        JT_tipo.setText("");
        JT_tipo.setFont(new java.awt.Font("Tahoma",0,11));
        JT_tipo.setForeground(new java.awt.Color(0,0,0));
    }//GEN-LAST:event_JT_tipoMouseClicked

    private void JT_tipoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JT_tipoKeyTyped
        JT_tipo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(final KeyEvent e) {
                filtroTipo();
            }
        });
        trsfiltro = new TableRowSorter(modeloTabla);
        jTable2.setRowSorter(trsfiltro);

    }//GEN-LAST:event_JT_tipoKeyTyped

    private void JT_combustibleFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_JT_combustibleFocusLost
        JT_combustible.setFont(new java.awt.Font("Tahoma",2,11));
        JT_combustible.setForeground(new java.awt.Color(153,153,153));
        JT_combustible.setText("Buscar por combustible");
    }//GEN-LAST:event_JT_combustibleFocusLost

    private void JT_combustibleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JT_combustibleMouseClicked
        JT_combustible.setText("");
        JT_combustible.setFont(new java.awt.Font("Tahoma",0,11));
        JT_combustible.setForeground(new java.awt.Color(0,0,0));
    }//GEN-LAST:event_JT_combustibleMouseClicked

    private void JT_combustibleKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JT_combustibleKeyTyped
        JT_combustible.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(final KeyEvent e) {
                filtroCombustible();
            }
        });
        trsfiltro = new TableRowSorter(modeloTabla);
        jTable2.setRowSorter(trsfiltro);
    }//GEN-LAST:event_JT_combustibleKeyTyped

    private void BTN_DelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_DelActionPerformed
        if (jTable2.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(null,
                "ERROR, No se ha seleccionado ninguna fila", "ERROR",
                JOptionPane.ERROR_MESSAGE);
        }

        String nom = jTable2.getValueAt(jTable2.getSelectedRow(), 1).toString().trim();

        int i = JOptionPane.showConfirmDialog(this,
            "¿Realmente desea eliminar " + nom + " de los MODELOS?", "Confirmar Eliminación",
            JOptionPane.YES_NO_OPTION);

        int modelo = 0;
        try {
            sentencia = (com.mysql.jdbc.Statement) cn.createStatement();
            ResultSet rs = sentencia.executeQuery("SELECT id_modelo FROM modelo WHERE nombre = '" + nom + "'");
            while (rs.next()) {
                modelo = rs.getInt("id_modelo");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "No se encontró Marca", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
        }

        if (verificar3() == 0 && i == 0) {
            String sql = "DELETE FROM modelo WHERE id_modelo =" + modelo + "";
            try {
                sentencia.executeUpdate(sql);
                JOptionPane.showMessageDialog(null,
                    "Modelo Borrado", "Exito",
                    JOptionPane.INFORMATION_MESSAGE);
            } catch (SQLException ee) {
                JOptionPane.showMessageDialog(null,
                    "No se pudo Borrar", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            }
        }

        txt_nom.setText("");
        limpiaTabla();
        setFilas();
    }//GEN-LAST:event_BTN_DelActionPerformed

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
            java.util.logging.Logger.getLogger(Modificar_modelo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Modificar_modelo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Modificar_modelo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Modificar_modelo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Modificar_modelo().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BTN_Del;
    private javax.swing.JButton JB_cancel;
    private javax.swing.JTextField JT_combustible;
    private javax.swing.JTextField JT_marca;
    private javax.swing.JTextField JT_motor;
    private javax.swing.JTextField JT_nombre;
    private javax.swing.JTextField JT_tipo;
    private javax.swing.JComboBox<String> cmb_comb;
    private javax.swing.JComboBox<String> cmb_marc;
    private javax.swing.JComboBox<String> cmb_motor;
    private javax.swing.JComboBox<String> cmb_tipo;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField txt_nom;
    // End of variables declaration//GEN-END:variables
}
