package racadauto;

import Conexion.Conexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class Modificar_cliente extends javax.swing.JFrame {

    private Statement sentencia;
    Conexion con = new Conexion();
    Connection cn = (Connection) con.getConnection();
    private String msj;
    DefaultTableModel modeloTabla;  
    private TableRowSorter trsfiltro;
    String filtro;
    
    public Modificar_cliente() {
        modeloTabla = new DefaultTableModel(null, getColumnas());
        setFilas();
        initComponents();
        llenarCombo();
    }
    
   
    
    private String[] getColumnas() {

        String columna[] = new String[]{"RUT", "NOMBRE", "APELLIDO PATERNO", "APELLIDO MATERNO", "DIRECCION", "CIUDAD"};

        return columna;
    }
    
    private void setFilas() {
        try {
            sentencia = (com.mysql.jdbc.Statement) cn.createStatement();
            ResultSet lista = sentencia.executeQuery("SELECT i.rut_cliente,i.nombre,i.ape_paterno,i.ape_materno,i.direccion,c.nombre "
                    + "                                 FROM cliente i,ciudad c "
                    + "                                 WHERE i.cod_ciudad = c.cod_ciudad");
            Object datos[] = new Object[7];
            while (lista.next()) {
                for (int i = 0; i < 6; i++) {
                    datos[i] = lista.getObject(i + 1);
                }
                modeloTabla.addRow(datos);
            }
        } catch (SQLException e) {
            msj = "No se pudo llenar tabla";
        }
    }
    
     void limpiaTabla() {
        do {
            modeloTabla.getRowCount();
            modeloTabla.removeRow(0);
        } while (modeloTabla.getRowCount() != 0);
    }
     
     public void clean() {
        JT_nombre.setText("");
        JT_paterno.setText("");
        JT_materno.setText("");
        JT_direccion.setText("");
    } 

    public void llenarCombo() {
        CMB_ciudad.removeAllItems();
        try {
            sentencia = (com.mysql.jdbc.Statement) cn.createStatement();
            ResultSet lista = sentencia.executeQuery("SELECT * FROM ciudad");
            while (lista.next()) {
                CMB_ciudad.addItem(lista.getString("nombre"));
            }
        } catch (SQLException ed) {
            msj = "no se pudo seleccionar";
        }
    } 
    
    public int verificar() {

        int cont = 0;
        
        String nombre = JT_nombre.getText().toUpperCase().trim();
        String paterno = JT_paterno.getText().toUpperCase().trim();
        String materno = JT_materno.getText().toUpperCase().trim();
        String direccion = JT_direccion.getText().toUpperCase().trim();
        String ciudad = (String) CMB_ciudad.getSelectedItem();
        String rut = "";

        try {
            sentencia=(com.mysql.jdbc.Statement)cn.createStatement();
            ResultSet rs = sentencia.executeQuery("SELECT rut_cliente FROM cliente");
            while (rs.next()) {
                rut = rs.getString("rut_cliente");
            }
        } catch (SQLException eg) {
            msj = "Error con su Solicitud";
        }
        
        
        if ((nombre.equals(""))
                || (paterno.equals(""))
                || (materno.equals("")) 
                || (direccion.equals(""))){
            JOptionPane.showMessageDialog(null,
                    "ERROR, dejó una casilla vacía", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            cont++;
        }
        
        if(jTable1.getSelectedRow() == -1){
            JOptionPane.showMessageDialog(null,
                    "ERROR, No ha seleccionado ningún CLIENTE", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            cont++;
        } else if ((nombre.equals(jTable1.getValueAt(jTable1.getSelectedRow(), 1).toString()))
                && (paterno.equals(jTable1.getValueAt(jTable1.getSelectedRow(), 2).toString()))
                && (materno.equals(jTable1.getValueAt(jTable1.getSelectedRow(), 3).toString())) 
                && (direccion.equals(jTable1.getValueAt(jTable1.getSelectedRow(), 4).toString()))
                && (ciudad.equals(jTable1.getValueAt(jTable1.getSelectedRow(), 5)))){
            JOptionPane.showMessageDialog(null,
                    "ERROR, No se ha modificado nada", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            cont++;
        }
        
        if (nombre.length() > 40) {
            JOptionPane.showMessageDialog(null,
                    "ERROR, NOMBRE maximo 40 letras", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            cont++;
        }
        
        
        if (paterno.length() > 30) {
            JOptionPane.showMessageDialog(null,
                    "ERROR, APELLIDO PATERNO maximo 30 letras", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            cont++;
        } 

        
        if (materno.length() > 30) {
            JOptionPane.showMessageDialog(null,
                    "ERROR, APELLIDO MATERNO maximo 30 letras", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            cont++;
        } 

        if (direccion.length() > 60) {
            JOptionPane.showMessageDialog(null,
                    "ERROR, DIRECCION no puede exceder los 60 caracteres", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            cont++;
        } 
 
        return cont;
    }
    
    public int verificar2() {
        int yes = 0;
        String rut2 = "",rut3 = "";
        String rut= jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString();
        try {
            sentencia = (com.mysql.jdbc.Statement) cn.createStatement();
            ResultSet rs = sentencia.executeQuery("SELECT rut_cliente FROM cliente WHERE rut_cliente = '" + rut + "'");
            while (rs.next()) {
                rut = rs.getString("rut_cliente");
            }
        } catch (SQLException eg) {
            msj = "Error con su Solicitud";
        }
        try {
            sentencia = (com.mysql.jdbc.Statement) cn.createStatement();
            ResultSet rs = sentencia.executeQuery("SELECT * FROM vehiculo "
                    + "WHERE rut_cliente = '" + rut + "'");
            while (rs.next()) {
                rut2 = rs.getString("rut_cliente");
                if (rut.equals(rut2)) {
                    //yes += rs.getInt("patente");
                    yes++;
                }
            }

        } catch (SQLException t) {
            msj = "No se puede Eliminar, CLIENTE referenciado en otra tabla";
        }
        
        try {
            sentencia = (com.mysql.jdbc.Statement) cn.createStatement();
            ResultSet rs = sentencia.executeQuery("SELECT * FROM contacto "
                    + "WHERE rut_cliente = '" + rut + "'");
            while (rs.next()) {
                rut3 = rs.getString("rut_cliente");
                if (rut.equals(rut3)) {
                    //yes += rs.getInt("rut_cliente");
                    yes++;
                }
            }

        } catch (SQLException t) {
            msj = "No se puede Eliminar, CLIENTE referenciado en otra tabla";
        }
        
        if (yes > 0){
            JOptionPane.showMessageDialog(null,
                    "ERROR, CLIENTE referenciado en otras tablas no se puede eliminar", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
        }

        return yes;
    }
    
    
    public void filtroRut() {

        filtro = JT_rut.getText();
        int columna = 0;
        trsfiltro.setRowFilter(RowFilter.regexFilter(JT_rut.getText(), columna));
        
    }
    
    public void filtroNombre() {

        filtro = JT_bnombre.getText().toUpperCase();
        int columna = 1;
        trsfiltro.setRowFilter(RowFilter.regexFilter(JT_bnombre.getText().toUpperCase(), columna));
    }
    
    public void filtroPaterno() {

        filtro = JT_paterno1.getText().toUpperCase();
        int columna = 2;
        trsfiltro.setRowFilter(RowFilter.regexFilter(JT_paterno1.getText().toUpperCase(), columna));
    }

    public void filtroMaterno() {

        filtro = JT_materno1.getText().toUpperCase();
        int columna = 3;
        trsfiltro.setRowFilter(RowFilter.regexFilter(JT_materno1.getText().toUpperCase(), columna));
    }
    
    public void filtroCiudad() {

        filtro = JT_ciudad.getText().toUpperCase();
        int columna = 5;
        trsfiltro.setRowFilter(RowFilter.regexFilter(JT_ciudad.getText().toUpperCase(), columna));
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jLabel9 = new javax.swing.JLabel();
        JB_cancel = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        JT_bnombre = new javax.swing.JTextField();
        JT_ciudad = new javax.swing.JTextField();
        BTN_Del = new javax.swing.JButton();
        JT_paterno1 = new javax.swing.JTextField();
        JT_materno1 = new javax.swing.JTextField();
        JT_rut = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        JT_nombre = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        JT_direccion = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        JT_paterno = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        JT_materno = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        CMB_ciudad = new javax.swing.JComboBox<>();
        JB_contacto = new javax.swing.JButton();
        JB_OK = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();

        jLabel1.setText("jLabel1");

        jCheckBox1.setText("jCheckBox1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(470, 300));
        setResizable(false);

        jLabel9.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel9.setText("MANTENEDOR DE CLIENTES");

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

        JT_bnombre.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        JT_bnombre.setForeground(new java.awt.Color(153, 153, 153));
        JT_bnombre.setText("Buscar por nombre");
        JT_bnombre.setToolTipText("");
        JT_bnombre.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        JT_bnombre.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                JT_bnombreFocusLost(evt);
            }
        });
        JT_bnombre.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JT_bnombreMouseClicked(evt);
            }
        });
        JT_bnombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JT_bnombreActionPerformed(evt);
            }
        });
        JT_bnombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                JT_bnombreKeyTyped(evt);
            }
        });

        JT_ciudad.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        JT_ciudad.setForeground(new java.awt.Color(153, 153, 153));
        JT_ciudad.setText("Buscar por ciudad");
        JT_ciudad.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                JT_ciudadFocusLost(evt);
            }
        });
        JT_ciudad.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JT_ciudadMouseClicked(evt);
            }
        });
        JT_ciudad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                JT_ciudadKeyTyped(evt);
            }
        });

        BTN_Del.setText("\uD83D\uDDD1");
        BTN_Del.setToolTipText("Eliminar Cliente Seleccionado");
        BTN_Del.setPreferredSize(new java.awt.Dimension(100, 35));
        BTN_Del.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_DelActionPerformed(evt);
            }
        });

        JT_paterno1.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        JT_paterno1.setForeground(new java.awt.Color(153, 153, 153));
        JT_paterno1.setText("Buscar por paterno");
        JT_paterno1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                JT_paterno1FocusLost(evt);
            }
        });
        JT_paterno1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JT_paterno1MouseClicked(evt);
            }
        });
        JT_paterno1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                JT_paterno1KeyTyped(evt);
            }
        });

        JT_materno1.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        JT_materno1.setForeground(new java.awt.Color(153, 153, 153));
        JT_materno1.setText("Buscar por materno");
        JT_materno1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                JT_materno1FocusLost(evt);
            }
        });
        JT_materno1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JT_materno1MouseClicked(evt);
            }
        });
        JT_materno1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                JT_materno1KeyTyped(evt);
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

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("MODIFICAR");
        jLabel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel3.setText("Nombre :");

        JT_nombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                JT_nombreKeyTyped(evt);
            }
        });

        jLabel6.setText("Dirección :");

        jLabel4.setText("Apellido Paterno :");

        JT_paterno.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                JT_paternoKeyTyped(evt);
            }
        });

        jLabel5.setText("Apellido Materno :");

        JT_materno.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                JT_maternoKeyTyped(evt);
            }
        });

        jLabel7.setText("CIUDAD :");

        CMB_ciudad.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Viña del Mar", "Quilpué" }));

        JB_contacto.setText("Modificar Contactos");
        JB_contacto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JB_contactoActionPerformed(evt);
            }
        });

        JB_OK.setText("\uD83D\uDDAB");
        JB_OK.setToolTipText("Modificar Cliente");
        JB_OK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JB_OKActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(10, 10, 10)
                                .addComponent(JT_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel4)
                                .addGap(10, 10, 10)
                                .addComponent(JT_paterno, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(JT_direccion, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(CMB_ciudad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(JB_contacto)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(JB_OK, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(10, 10, 10)
                                .addComponent(JT_materno, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(JT_materno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(JT_paterno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(JT_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel7)
                        .addComponent(CMB_ciudad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(JB_contacto)
                        .addComponent(JB_OK))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6)
                        .addComponent(JT_direccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/racadauto/Images/ok.jpg"))); // NOI18N
        jLabel8.setText("jLabel3");
        jLabel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(JT_rut, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21)
                        .addComponent(JT_bnombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(23, 23, 23)
                        .addComponent(JT_paterno1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(JT_materno1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(JT_ciudad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 571, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(JB_cancel, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BTN_Del, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel9)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(BTN_Del, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(JB_cancel)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(JT_paterno1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(JT_materno1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(JT_rut, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(JT_bnombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(JT_ciudad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(13, 13, 13)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void JB_cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JB_cancelActionPerformed
        this.dispose();
    }//GEN-LAST:event_JB_cancelActionPerformed

    private void JB_contactoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JB_contactoActionPerformed
        Modificar_contacto t =new Modificar_contacto();
        t.setVisible(true); 
    }//GEN-LAST:event_JB_contactoActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        
        String nom = jTable1.getValueAt(jTable1.getSelectedRow(), 1).toString();
        JT_nombre.setText(nom);

        String paterno = jTable1.getValueAt(jTable1.getSelectedRow(), 2).toString();
        JT_paterno.setText(paterno);

        String materno = jTable1.getValueAt(jTable1.getSelectedRow(), 3).toString();
        JT_materno.setText(materno);

        String direccion = jTable1.getValueAt(jTable1.getSelectedRow(), 4).toString();
        JT_direccion.setText(direccion);
        
        String ciudad = jTable1.getValueAt(jTable1.getSelectedRow(), 5).toString();
     
        CMB_ciudad.setSelectedItem(ciudad);

    }//GEN-LAST:event_jTable1MouseClicked

    private void JB_OKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JB_OKActionPerformed
        
        if (verificar() == 0) {
            String rut = "", ciudad,rut2 = "";
            rut = jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString();
            int dis;
            String nombre= JT_nombre.getText().toUpperCase().trim();
            String paterno = JT_paterno.getText().toUpperCase().trim();
            String materno = JT_materno.getText().toUpperCase().trim();
            String direccion = JT_direccion.getText().toUpperCase().trim();
            ciudad = (String) CMB_ciudad.getSelectedItem();
            
            int ciudad2 = 0;
            
            try {
                sentencia = (com.mysql.jdbc.Statement) cn.createStatement();
                ResultSet rs = sentencia.executeQuery("SELECT cod_ciudad FROM ciudad WHERE  nombre = '" + ciudad + "'");
                while (rs.next()) {
                    ciudad2 = rs.getInt("cod_ciudad");
                }
            } catch (SQLException s) {
                msj = "Error con Ciudad";
            }
            
            try {
                sentencia = (com.mysql.jdbc.Statement) cn.createStatement();
                ResultSet rs = sentencia.executeQuery("SELECT rut_cliente FROM cliente WHERE rut_cliente = '" + rut + "'");
                while (rs.next()) {
                    rut2 = rs.getString("rut_cliente");
                }

            } catch (SQLException f) {
                msj = "Error con Codigo";
            }

            String sql = "UPDATE cliente "
                    + "SET nombre ='" + nombre + "',"
                    + "ape_paterno = '" + paterno + "',"
                    + "ape_materno = '" + materno + "',"
                    + "direccion = '" + direccion + "',"
                    + "cod_ciudad = '" + ciudad2 + "'"
                    + "WHERE rut_cliente = '" + rut2 + "'";
            try {
                sentencia.executeUpdate(sql);
                JOptionPane.showMessageDialog(null,
                    "Cliente Actualizado!", "Exito",
                    JOptionPane.INFORMATION_MESSAGE);
                dis = 1;
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null,
                    "Cliente NO Actualizado", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
                dis = 0;
            }
            if (dis == 1) {
                clean();
            }
        } else {
            JOptionPane.showMessageDialog(null,
                    "Datos Mal Escritos", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
        }

        clean();
        jTable1.setRowSorter(null);
        limpiaTabla();
        setFilas();        
    }//GEN-LAST:event_JB_OKActionPerformed

    private void JT_nombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JT_nombreKeyTyped
        char validar = evt.getKeyChar();

        if (!Character.isLetter(validar) && validar != evt.VK_BACK_SPACE && validar != evt.VK_SPACE ) {
            getToolkit().beep();
            evt.consume();

            JOptionPane.showMessageDialog(null,
                    "ERROR, NOMBRE solo pueden ser letras", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_JT_nombreKeyTyped

    private void JT_paternoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JT_paternoKeyTyped
        char validar = evt.getKeyChar();

        if (!Character.isLetter(validar) && validar != evt.VK_BACK_SPACE && validar != evt.VK_SPACE ) {
            getToolkit().beep();
            evt.consume();

            JOptionPane.showMessageDialog(null,
                    "ERROR, APELLIDO PATERNO solo pueden ser letras", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_JT_paternoKeyTyped

    private void JT_maternoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JT_maternoKeyTyped
        char validar = evt.getKeyChar();

        if (!Character.isLetter(validar) && validar != evt.VK_BACK_SPACE && validar != evt.VK_SPACE ) {
            getToolkit().beep();
            evt.consume();

            JOptionPane.showMessageDialog(null,
                    "ERROR, APELLIDO MATERNO solo pueden ser letras", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_JT_maternoKeyTyped

    private void JT_bnombreFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_JT_bnombreFocusLost
        JT_bnombre.setFont(new java.awt.Font("Tahoma",2,11));
        JT_bnombre.setForeground(new java.awt.Color(153,153,153));
        JT_bnombre.setText("Buscar por nombre");
    }//GEN-LAST:event_JT_bnombreFocusLost

    private void JT_bnombreMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JT_bnombreMouseClicked
        JT_bnombre.setText("");
        JT_bnombre.setFont(new java.awt.Font("Tahoma",0,11));
        JT_bnombre.setForeground(new java.awt.Color(0,0,0));
    }//GEN-LAST:event_JT_bnombreMouseClicked

    private void JT_bnombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JT_bnombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JT_bnombreActionPerformed

    private void JT_bnombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JT_bnombreKeyTyped
        JT_bnombre.addKeyListener(new KeyAdapter() {
            public void keyReleased(final KeyEvent e) {
                filtroNombre();
            }
        });
        trsfiltro = new TableRowSorter(modeloTabla);
        jTable1.setRowSorter(trsfiltro);
        clean();
        
    }//GEN-LAST:event_JT_bnombreKeyTyped

    private void JT_ciudadFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_JT_ciudadFocusLost
        JT_ciudad.setFont(new java.awt.Font("Tahoma",2,11));
        JT_ciudad.setForeground(new java.awt.Color(153,153,153));
        JT_ciudad.setText("Buscar por ciudad");
    }//GEN-LAST:event_JT_ciudadFocusLost

    private void JT_ciudadMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JT_ciudadMouseClicked
        JT_ciudad.setText("");
        JT_ciudad.setFont(new java.awt.Font("Tahoma",0,11));
        JT_ciudad.setForeground(new java.awt.Color(0,0,0));
    }//GEN-LAST:event_JT_ciudadMouseClicked

    private void JT_ciudadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JT_ciudadKeyTyped
        JT_ciudad.addKeyListener(new KeyAdapter() {
            public void keyReleased(final KeyEvent e) {
                filtroCiudad();
            }
        });
        trsfiltro = new TableRowSorter(modeloTabla);
        jTable1.setRowSorter(trsfiltro);
        clean();
    }//GEN-LAST:event_JT_ciudadKeyTyped

    private void BTN_DelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_DelActionPerformed

        if(jTable1.getSelectedRow() == -1){
            JOptionPane.showMessageDialog(null,
                "Error, No Se Ha Seleccionado Ningún Cliente","ERROR",
                JOptionPane.ERROR_MESSAGE);

        }

        String rut = jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString();
        String nom = jTable1.getValueAt(jTable1.getSelectedRow(), 1).toString();

        int i =JOptionPane.showConfirmDialog(this,
            "¿Realmente Desea Eliminar al Cliente" + nom + " ?","Confirmar Eliminación",
            JOptionPane.YES_NO_OPTION);

        String rut2 = "";
        try {
            sentencia = (com.mysql.jdbc.Statement) cn.createStatement();
            ResultSet rs = sentencia.executeQuery("SELECT rut_cliente FROM cliente WHERE rut_cliente = '" + rut + "'");
            while (rs.next()) {
                rut2 = rs.getString("rut_cliente");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "No se encontró cliente en tabla", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
        }

        if (verificar2() == 0 && i== 0) {
            String sql = "DELETE FROM cliente WHERE rut_cliente =" + rut + "";
            try {
                sentencia.executeUpdate(sql);
                JOptionPane.showMessageDialog(null,
                    "Cliente Borrado!", "Exito",
                    JOptionPane.INFORMATION_MESSAGE);
            } catch (SQLException ee) {
                JOptionPane.showMessageDialog(null,
                    "Cliente NO Borrado", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            }
        }

        clean();
        jTable1.setRowSorter(null);
        limpiaTabla();
        setFilas();
    }//GEN-LAST:event_BTN_DelActionPerformed

    private void JT_paterno1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_JT_paterno1FocusLost
        JT_paterno1.setFont(new java.awt.Font("Tahoma",2,11));
        JT_paterno1.setForeground(new java.awt.Color(153,153,153));
        JT_paterno1.setText("Buscar por paterno");
    }//GEN-LAST:event_JT_paterno1FocusLost

    private void JT_paterno1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JT_paterno1MouseClicked
        JT_paterno1.setText("");
        JT_paterno1.setFont(new java.awt.Font("Tahoma",0,11));
        JT_paterno1.setForeground(new java.awt.Color(0,0,0));
    }//GEN-LAST:event_JT_paterno1MouseClicked

    private void JT_paterno1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JT_paterno1KeyTyped
        JT_paterno1.addKeyListener(new KeyAdapter() {
            public void keyReleased(final KeyEvent e) {
                filtroPaterno();
            }
        });
        trsfiltro = new TableRowSorter(modeloTabla);
        jTable1.setRowSorter(trsfiltro);
        clean();
    }//GEN-LAST:event_JT_paterno1KeyTyped

    private void JT_materno1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_JT_materno1FocusLost
        JT_materno1.setFont(new java.awt.Font("Tahoma",2,11));
        JT_materno1.setForeground(new java.awt.Color(153,153,153));
        JT_materno1.setText("Buscar por materno");
    }//GEN-LAST:event_JT_materno1FocusLost

    private void JT_materno1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JT_materno1MouseClicked
        JT_materno1.setText("");
        JT_materno1.setFont(new java.awt.Font("Tahoma",0,11));
        JT_materno1.setForeground(new java.awt.Color(0,0,0));
    }//GEN-LAST:event_JT_materno1MouseClicked

    private void JT_materno1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JT_materno1KeyTyped
        JT_materno1.addKeyListener(new KeyAdapter() {
            public void keyReleased(final KeyEvent e) {
                filtroMaterno();
            }
        });
        trsfiltro = new TableRowSorter(modeloTabla);
        jTable1.setRowSorter(trsfiltro);
        clean();
    }//GEN-LAST:event_JT_materno1KeyTyped

    private void JT_rutFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_JT_rutFocusLost
        JT_rut.setFont(new java.awt.Font("Tahoma",2,11));
        JT_rut.setForeground(new java.awt.Color(153,153,153));
        JT_rut.setText("Buscar por rut");
    }//GEN-LAST:event_JT_rutFocusLost

    private void JT_rutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JT_rutMouseClicked
        JT_rut.setText("");
        JT_rut.setFont(new java.awt.Font("Tahoma",0,11));
        JT_rut.setForeground(new java.awt.Color(0,0,0));
    }//GEN-LAST:event_JT_rutMouseClicked

    private void JT_rutKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JT_rutKeyTyped
        JT_rut.addKeyListener(new KeyAdapter() {
            public void keyReleased(final KeyEvent e) {
                filtroRut();
            }
        });
        trsfiltro = new TableRowSorter(modeloTabla);
        jTable1.setRowSorter(trsfiltro);
        clean();
    }//GEN-LAST:event_JT_rutKeyTyped

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
            java.util.logging.Logger.getLogger(Ingresar_cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Ingresar_cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Ingresar_cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Ingresar_cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Modificar_cliente().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BTN_Del;
    private javax.swing.JComboBox<String> CMB_ciudad;
    private javax.swing.JButton JB_OK;
    private javax.swing.JButton JB_cancel;
    private javax.swing.JButton JB_contacto;
    private javax.swing.JTextField JT_bnombre;
    private javax.swing.JTextField JT_ciudad;
    private javax.swing.JTextField JT_direccion;
    private javax.swing.JTextField JT_materno;
    private javax.swing.JTextField JT_materno1;
    private javax.swing.JTextField JT_nombre;
    private javax.swing.JTextField JT_paterno;
    private javax.swing.JTextField JT_paterno1;
    private javax.swing.JTextField JT_rut;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1;
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
    // End of variables declaration//GEN-END:variables
}
