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

public class Modificar_item2 extends javax.swing.JFrame {

    private Statement sentencia;
    Conexion con = new Conexion();
    Connection cn = (Connection) con.getConnection();
    private String msj;
    DefaultTableModel modeloTabla;
    private TableRowSorter trsfiltro;
    String filtro;

    public Modificar_item2() {

        modeloTabla = new DefaultTableModel(null, getColumnas());
        setFilas();
        initComponents();
        llenarCombo();
        llenarCombo2();

    }

    private String[] getColumnas() {

        String columna[] = new String[]{"Nombre", "Stock actual", "Stock crítico", "Valor costo", "Valor venta", "Medida", "Familia"};

        return columna;
    }

    private void setFilas() {
        try {
            sentencia = (com.mysql.jdbc.Statement) cn.createStatement();
            ResultSet lista = sentencia.executeQuery("SELECT i.nombre, i.stock_actual, i.stock_critico, i.valor_costo, i.valor_venta, um.nombre, f.nombre"
                    + " FROM inventario i INNER JOIN unidad_medida um ON i.id_medida = um.id_medida LEFT JOIN familia f ON i.id_familia = f.id_familia"
                    + " WHERE i.estado = 1");
            Object datos[] = new Object[7];
            while (lista.next()) {
                for (int i = 0; i < 7; i++) {
                    datos[i] = lista.getObject(i + 1);
                }
                modeloTabla.addRow(datos);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "No se pudo llenar tabla", "Error",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    void limpiaTabla() {
        do {
            modeloTabla.getRowCount();
            modeloTabla.removeRow(0);
        } while (modeloTabla.getRowCount() != 0);
    }

   

    public void clean() {
        JT_stock.setText("");
        JT_vventa.setText("");
        JT_vcosto.setText("");
        JT_nom.setText("");
    }

    public void llenarCombo() {
        String nom;
        cmb_med.removeAllItems();
        try {
            sentencia = (com.mysql.jdbc.Statement) cn.createStatement();
            ResultSet lista = sentencia.executeQuery("SELECT * FROM unidad_medida");
            while (lista.next()) {
                cmb_med.addItem(lista.getString("nombre"));
            }
        } catch (SQLException ed) {
            JOptionPane.showMessageDialog(null,
                    "No se pudo seleccionar", "Error",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void llenarCombo2() {
        cmb_fam.removeAllItems();
        try {
            sentencia = (com.mysql.jdbc.Statement) cn.createStatement();
            ResultSet lista = sentencia.executeQuery("SELECT * FROM familia");
            while (lista.next()) {
                cmb_fam.addItem(lista.getString("nombre"));
            }
        } catch (SQLException ed) {
            JOptionPane.showMessageDialog(null,
                    "No se pudo seleccionar", "Error",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    public void filtroNombre() {

        filtro = JT_nombre.getText().toUpperCase();
        int columna = 0;
        trsfiltro.setRowFilter(RowFilter.regexFilter(JT_nombre.getText().toUpperCase(), columna));
    }
    
    public void filtroCosto() {

        filtro = JT_bvcosto.getText().toUpperCase();
        int columna = 3;
        trsfiltro.setRowFilter(RowFilter.regexFilter(JT_bvcosto.getText().toUpperCase(), columna));
    }

    public void filtroVenta() {

        filtro = JT_bvventa.getText().toUpperCase();
        int columna = 4;
        trsfiltro.setRowFilter(RowFilter.regexFilter(JT_bvventa.getText().toUpperCase(), columna));
    }
    
    public void filtroActual() {

        filtro = JT_actual.getText();
        int columna = 1;
        trsfiltro.setRowFilter(RowFilter.regexFilter(JT_actual.getText(), columna));
        
    }
    
    public void filtroFamilia() {

        filtro = JT_familia.getText().toUpperCase();
        int columna = 6;
        trsfiltro.setRowFilter(RowFilter.regexFilter(JT_familia.getText().toUpperCase(), columna));
    }

    public int verificar() {
        int cont = 0;
        String nom = "";
        String nom2 = JT_nom.getText().toUpperCase().trim();
        String stock = JT_stock.getText().trim();
        String vcosto = JT_vcosto.getText().trim();
        String vventa = JT_vventa.getText().trim();
        String nom3 = jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString();

        try {
            sentencia = (com.mysql.jdbc.Statement) cn.createStatement();
            ResultSet rs = sentencia.executeQuery("SELECT nombre FROM inventario");
            while (rs.next()) {
                nom = rs.getString("nombre");
                }
        } catch (SQLException eg) {
            msj = "Error con su Solicitud";
        }
        
        if (JT_nom.getText().equals(nom3)) {
            cont=0;
        }else{
            if(JT_nom.getText().equals(nom)){
                JOptionPane.showMessageDialog(null,
                    "Error, ya existe Item!", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
                cont++;
            }
        }

        if ((JT_stock.getText().equals(""))
                || (JT_vcosto.getText().equals(""))
                || (JT_nom.getText().equals(""))
                || (JT_vventa.getText().equals(""))) {
            JOptionPane.showMessageDialog(null,
                    "Error, dejó una casilla vacía", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            cont++;
        }

        if (JT_nom.getText().length() >= 31) {
            JOptionPane.showMessageDialog(null,
                    "Error, nombre maximo 30 letras", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            cont++;
        } else if (nom2.matches("[-+]?\\d*\\.?\\d+")) {

            JOptionPane.showMessageDialog(null,
                    "Error, nombre no tiene que ser númerico", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            cont++;
        }

        if (JT_stock.getText().length() >= 3) {
            JOptionPane.showMessageDialog(null,
                    "Error, stock critico <100 y >0", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            cont++;
        } else if (!stock.matches("[-+]?\\d*\\.?\\d+")) {

            JOptionPane.showMessageDialog(null,
                    "Error, stock critico tiene que ser numerico", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            cont++;
        }

        if (JT_vcosto.getText().length() >= 7) {
            JOptionPane.showMessageDialog(null,
                    "Error, valor costo <1M y >0", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            cont++;
        } else if (!vcosto.matches("[-+]?\\d*\\.?\\d+")) {
            JOptionPane.showMessageDialog(null,
                    "Error, valor costo tiene que ser numerico", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            cont++;
        }

        if (JT_vventa.getText().length() >= 7) {
            JOptionPane.showMessageDialog(null,
                    "Error, valor venta maximo <1M y >0", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            cont++;
        } else if (!vventa.matches("[-+]?\\d*\\.?\\d+")) {
            JOptionPane.showMessageDialog(null,
                    "Error, valor venta tiene que ser numerico", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            cont++;
        }

        if ((Integer.parseInt(JT_vcosto.getText())) > (Integer.parseInt(JT_vventa.getText()))) {
            JOptionPane.showMessageDialog(null,
                    "Error, Valor Venta no puede ser menor a Costo", "error message",
                    JOptionPane.ERROR_MESSAGE);
            cont++;
        }

        return cont;
    }
    
    public int verificar2() {
        int yes = 0;
        int cod = 0;
        int cod2 = 0;
        String nom = jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString();
        try {
            sentencia = (com.mysql.jdbc.Statement) cn.createStatement();
            ResultSet rs = sentencia.executeQuery("SELECT cod_item FROM inventario WHERE nombre = '" + nom + "'");
            while (rs.next()) {
                cod = rs.getInt("cod_item");
            }
        } catch (SQLException eg) {
            JOptionPane.showMessageDialog(null,
                    "Solicitud Fallida", "Error",
                    JOptionPane.INFORMATION_MESSAGE);
        }
        try {
            sentencia = (com.mysql.jdbc.Statement) cn.createStatement();
            ResultSet rs = sentencia.executeQuery("SELECT * FROM detalle_insumo");
            while (rs.next()) {
                cod2 = rs.getInt("cod_item");
                if (cod == cod2) {
                    yes += rs.getInt("cod_solicitud");
                }
            }
        } catch (SQLException t) {
            JOptionPane.showMessageDialog(null,
                    "Solicitud Fallida", "Error",
                    JOptionPane.INFORMATION_MESSAGE);
        }
        try {
            sentencia = (com.mysql.jdbc.Statement) cn.createStatement();
            ResultSet rs = sentencia.executeQuery("SELECT * FROM detalle_solicitud");
            while (rs.next()) {
                cod2 = rs.getInt("cod_item");
                if (cod == cod2) {
                    yes += rs.getInt("cod_solicitud");
                }
            }
        } catch (SQLException t) {
            JOptionPane.showMessageDialog(null,
                    "Solicitud Fallida", "Error",
                    JOptionPane.INFORMATION_MESSAGE);
        }
        try {
            sentencia = (com.mysql.jdbc.Statement) cn.createStatement();
            ResultSet rs = sentencia.executeQuery("SELECT * FROM repuestos");
            while (rs.next()) {
                cod2 = rs.getInt("cod_item");
                if (cod == cod2) {
                    yes += rs.getInt("id_marca");
                }
            }
        } catch (SQLException t) {
            JOptionPane.showMessageDialog(null,
                    "Solicitud Fallida", "Error",
                    JOptionPane.INFORMATION_MESSAGE);
        }
        try {
            sentencia = (com.mysql.jdbc.Statement) cn.createStatement();
            ResultSet rs = sentencia.executeQuery("SELECT * FROM ajuste");
            while (rs.next()) {
                cod2 = rs.getInt("cod_item");
                if (cod == cod2) {
                    yes = rs.getInt("n_folio");
                }
            }
        } catch (SQLException t) {
            JOptionPane.showMessageDialog(null,
                    "Solicitud Fallida", "Error",
                    JOptionPane.INFORMATION_MESSAGE);
        }
        return yes;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        JB_cancel = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        JT_bvcosto = new javax.swing.JTextField();
        JT_bvventa = new javax.swing.JTextField();
        JT_nombre = new javax.swing.JTextField();
        JT_actual = new javax.swing.JTextField();
        JT_familia = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        BTN_Del = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        JT_nom = new javax.swing.JTextField();
        JT_stock = new javax.swing.JTextField();
        JT_vcosto = new javax.swing.JTextField();
        JT_vventa = new javax.swing.JTextField();
        cmb_med = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        cmb_fam = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        JB_OK = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        JB_cancel.setText("\u2B8C");
        JB_cancel.setToolTipText("Volver");
        JB_cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JB_cancelActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel9.setText("MODIFICAR, ELIMINAR Y CONSULTAR ITEM");

        jTable1.setModel(modeloTabla);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        JT_bvcosto.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        JT_bvcosto.setForeground(new java.awt.Color(153, 153, 153));
        JT_bvcosto.setText("Buscar por valor costo");
        JT_bvcosto.setToolTipText("");
        JT_bvcosto.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        JT_bvcosto.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                JT_bvcostoFocusLost(evt);
            }
        });
        JT_bvcosto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JT_bvcostoMouseClicked(evt);
            }
        });
        JT_bvcosto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JT_bvcostoActionPerformed(evt);
            }
        });
        JT_bvcosto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                JT_bvcostoKeyTyped(evt);
            }
        });

        JT_bvventa.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        JT_bvventa.setForeground(new java.awt.Color(153, 153, 153));
        JT_bvventa.setText("Buscar por valor venta");
        JT_bvventa.setToolTipText("");
        JT_bvventa.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        JT_bvventa.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                JT_bvventaFocusLost(evt);
            }
        });
        JT_bvventa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JT_bvventaMouseClicked(evt);
            }
        });
        JT_bvventa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JT_bvventaActionPerformed(evt);
            }
        });
        JT_bvventa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                JT_bvventaKeyTyped(evt);
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

        jButton2.setText("\u2264");
        jButton2.setToolTipText("Menor o Igual a Critico");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        BTN_Del.setText("\uD83D\uDDD1");
        BTN_Del.setToolTipText("Borrar item seleccionado");
        BTN_Del.setPreferredSize(new java.awt.Dimension(100, 35));
        BTN_Del.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_DelActionPerformed(evt);
            }
        });

        jButton3.setText("\uD83D\uDDB9");
        jButton3.setToolTipText("Resetear Tabla");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel6.setText("Stock Critico :");

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("MODIFICAR");
        jLabel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel7.setText("Medida :");

        jLabel8.setText("Familia :");

        jLabel2.setText("Nombre :");

        JT_nom.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                JT_nomKeyTyped(evt);
            }
        });

        JT_stock.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                JT_stockKeyTyped(evt);
            }
        });

        JT_vcosto.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                JT_vcostoFocusLost(evt);
            }
        });
        JT_vcosto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                JT_vcostoKeyTyped(evt);
            }
        });

        JT_vventa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                JT_vventaKeyTyped(evt);
            }
        });

        cmb_med.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel4.setText("Valor Costo :");

        cmb_fam.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel5.setText("Valor Venta :");

        JB_OK.setText("\uD83D\uDDAB");
        JB_OK.setToolTipText("Modificar Item");
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
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(JT_vcosto, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JT_vventa, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cmb_fam, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(JB_OK, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(JT_nom, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JT_stock, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cmb_med, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(100, 100, 100))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(50, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(cmb_med, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JT_stock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(JT_vcosto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(JT_vventa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(cmb_fam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JB_OK))
                .addGap(19, 19, 19))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(JT_nom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/racadauto/Images/ok.jpeg"))); // NOI18N
        jLabel3.setText("jLabel3");
        jLabel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(JT_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(JT_actual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(JT_bvcosto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(JT_bvventa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(JT_familia, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(BTN_Del, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(JB_cancel, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(18, Short.MAX_VALUE))
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(BTN_Del, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton2)
                        .addGap(39, 39, 39)
                        .addComponent(JB_cancel)
                        .addGap(46, 46, 46)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JT_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JT_actual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JT_bvcosto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JT_bvventa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JT_familia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void JB_OKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JB_OKActionPerformed
        if (verificar() == 0) {
            String nom = "";
            nom = jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString();
            String fam, med;
            int dis;
            String nom2 = JT_nom.getText().toUpperCase();
            int cod = 0;
            int stock = Integer.parseInt(JT_stock.getText());
            int vcost = Integer.parseInt(JT_vcosto.getText());
            int vvent = Integer.parseInt(JT_vventa.getText());
            fam = (String) cmb_fam.getSelectedItem();
            med = (String) cmb_med.getSelectedItem();
            int fam2 = 0;
            int med2 = 0;

            try {
                sentencia = (com.mysql.jdbc.Statement) cn.createStatement();
                ResultSet rs = sentencia.executeQuery("SELECT id_medida FROM unidad_medida WHERE  nombre = '" + med + "'");
                while (rs.next()) {
                    med2 = rs.getInt("id_medida");
                }
            } catch (SQLException s) {
                JOptionPane.showMessageDialog(null,
                    "Medida Incorrecta", "Error",
                    JOptionPane.INFORMATION_MESSAGE);
            }
            try {
                sentencia = (com.mysql.jdbc.Statement) cn.createStatement();
                ResultSet rs = sentencia.executeQuery("SELECT id_familia FROM familia WHERE nombre = '" + fam + "'");
                while (rs.next()) {
                    fam2 = rs.getInt("id_familia");
                }
            } catch (SQLException t) {
                JOptionPane.showMessageDialog(null,
                    "Familia Incorrecta", "Error",
                    JOptionPane.INFORMATION_MESSAGE);
            }
            try {
                sentencia = (com.mysql.jdbc.Statement) cn.createStatement();
                ResultSet rs = sentencia.executeQuery("SELECT cod_item FROM inventario WHERE nombre = '" + nom + "'");
                while (rs.next()) {
                    cod = rs.getInt("cod_item");
                }

            } catch (SQLException f) {
                JOptionPane.showMessageDialog(null,
                    "Codigo Incorrecto", "Error",
                    JOptionPane.INFORMATION_MESSAGE);
            }

            String sql = "UPDATE inventario "
                    + "SET nombre ='" + nom2 + "',"
                    + "stock_critico = '" + stock + "',"
                    + "valor_costo = '" + vcost + "',"
                    + "valor_venta = '" + vvent + "',"
                    + "id_medida = '" + med2 + "',"
                    + "id_familia = '" + fam2 + "'"
                    + "WHERE cod_item = '" + cod + "'";
            try {
                sentencia.executeUpdate(sql);
                JOptionPane.showMessageDialog(null,
                    "Datos Modificados", "Mensaje",
                    JOptionPane.INFORMATION_MESSAGE);
                dis = 1;
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null,
                    "Datos NO Modificados", "Error",
                    JOptionPane.INFORMATION_MESSAGE);
                dis = 0;
            }
            if (dis == 1) {
                clean();
            }
        } else {
            JOptionPane.showMessageDialog(null,
                    "Datos Mal Escritos", "Error",
                    JOptionPane.INFORMATION_MESSAGE);
        }

        limpiaTabla();
        setFilas();
    }//GEN-LAST:event_JB_OKActionPerformed

    private void JB_cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JB_cancelActionPerformed
        this.dispose();
    }//GEN-LAST:event_JB_cancelActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        String nom = jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString();
        JT_nom.setText(nom);

        String stock = jTable1.getValueAt(jTable1.getSelectedRow(), 2).toString();
        JT_stock.setText(stock);

        String vcosto = jTable1.getValueAt(jTable1.getSelectedRow(), 3).toString();
        JT_vcosto.setText(vcosto);

        String vventa = jTable1.getValueAt(jTable1.getSelectedRow(), 4).toString();
        JT_vventa.setText(vventa);
        
        
        String med = jTable1.getValueAt(jTable1.getSelectedRow(), 5).toString();
        cmb_med.setSelectedItem(med);
        
        String fam = jTable1.getValueAt(jTable1.getSelectedRow(), 6).toString();
        cmb_fam.setSelectedItem(fam);
        
        
    }//GEN-LAST:event_jTable1MouseClicked

    private void JT_nomKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JT_nomKeyTyped
        char validar = evt.getKeyChar();

        if (!Character.isLetter(validar) && validar != evt.VK_BACK_SPACE && validar != evt.VK_SPACE ) {
            getToolkit().beep();
            evt.consume();

            JOptionPane.showMessageDialog(null,
                    "ERROR, NOMBRE solo pueden ser letras", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_JT_nomKeyTyped

    private void JT_stockKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JT_stockKeyTyped
        char validar = evt.getKeyChar();

        if (!Character.isDigit(validar) && validar != evt.VK_BACK_SPACE) {
            getToolkit().beep();
            evt.consume();

            JOptionPane.showMessageDialog(null,
                    "ERROR, STOCK CRITICO solo pueden ser números", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_JT_stockKeyTyped

    private void JT_vcostoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JT_vcostoKeyTyped

    }//GEN-LAST:event_JT_vcostoKeyTyped

    private void JT_vventaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JT_vventaKeyTyped

    }//GEN-LAST:event_JT_vventaKeyTyped

    private void JT_bvcostoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_JT_bvcostoFocusLost
        JT_bvcosto.setFont(new java.awt.Font("Tahoma",2,11));
        JT_bvcosto.setForeground(new java.awt.Color(153,153,153));
        JT_bvcosto.setText("Buscar por valor costo");
    }//GEN-LAST:event_JT_bvcostoFocusLost

    private void JT_bvcostoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JT_bvcostoMouseClicked
        JT_bvcosto.setText("");
        JT_bvcosto.setFont(new java.awt.Font("Tahoma",0,11));
        JT_bvcosto.setForeground(new java.awt.Color(0,0,0));
    }//GEN-LAST:event_JT_bvcostoMouseClicked

    private void JT_bvcostoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JT_bvcostoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JT_bvcostoActionPerformed

    private void JT_bvcostoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JT_bvcostoKeyTyped
        JT_bvcosto.addKeyListener(new KeyAdapter() {
            public void keyReleased(final KeyEvent e) {
                filtroCosto();
            }
        });
        trsfiltro = new TableRowSorter(modeloTabla);
        jTable1.setRowSorter(trsfiltro);
    }//GEN-LAST:event_JT_bvcostoKeyTyped

    private void JT_bvventaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_JT_bvventaFocusLost
        JT_bvventa.setFont(new java.awt.Font("Tahoma",2,11));
        JT_bvventa.setForeground(new java.awt.Color(153,153,153));
        JT_bvventa.setText("Buscar por valor venta");
    }//GEN-LAST:event_JT_bvventaFocusLost

    private void JT_bvventaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JT_bvventaMouseClicked
        JT_bvventa.setText("");
        JT_bvventa.setFont(new java.awt.Font("Tahoma",0,11));
        JT_bvventa.setForeground(new java.awt.Color(0,0,0));
    }//GEN-LAST:event_JT_bvventaMouseClicked

    private void JT_bvventaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JT_bvventaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JT_bvventaActionPerformed

    private void JT_bvventaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JT_bvventaKeyTyped
        JT_bvventa.addKeyListener(new KeyAdapter() {
            public void keyReleased(final KeyEvent e) {
                filtroVenta();
            }
        });
        trsfiltro = new TableRowSorter(modeloTabla);
        jTable1.setRowSorter(trsfiltro);
    }//GEN-LAST:event_JT_bvventaKeyTyped

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

    private void JT_vcostoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_JT_vcostoFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_JT_vcostoFocusLost

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

    private void BTN_DelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_DelActionPerformed
        String nom = jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString();

        int i =JOptionPane.showConfirmDialog(this,
            "¿Realmente Desea Eliminar " + nom + " del Inventario?","Confirmar Eliminación",
            JOptionPane.YES_NO_OPTION);
        int estado = 0;
        int cod = 0;
        try {
            sentencia = (com.mysql.jdbc.Statement) cn.createStatement();
            ResultSet rs = sentencia.executeQuery("SELECT cod_item FROM inventario WHERE nombre = '" + nom + "'");
            while (rs.next()) {
                cod = rs.getInt("cod_item");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Item no encontrado?", "Error?",
                    JOptionPane.INFORMATION_MESSAGE);
        }

        if (verificar2() == 0 && i== 0) {
            String sql = "DELETE FROM inventario WHERE cod_item =" + cod + "";
            try {
                sentencia.executeUpdate(sql);
                JOptionPane.showMessageDialog(null,
                    "Datos Borrados", "Mensaje",
                    JOptionPane.INFORMATION_MESSAGE);
            } catch (SQLException ee) {
                JOptionPane.showMessageDialog(null,
                    "Datos NO Modificados", "Error",
                    JOptionPane.INFORMATION_MESSAGE);
            }
        }else{
            if(i==0){
                String sql="UPDATE inventario SET estado ="+ estado +" WHERE cod_item ="+ cod +"";
                try{
                    sentencia.executeUpdate(sql);
                    JOptionPane.showMessageDialog(null,
                        "Item marcado como inactivo", "Mensaje",
                        JOptionPane.INFORMATION_MESSAGE);
                }
                catch(SQLException ee){
                    JOptionPane.showMessageDialog(null,
                        "No se pudo inactivar?", "Error?",
                        JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }

        clean();
        limpiaTabla();
        setFilas();

    }//GEN-LAST:event_BTN_DelActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        limpiaTabla();
        setFilas();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        limpiaTabla();
        try {
            sentencia = (com.mysql.jdbc.Statement) cn.createStatement();
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
            JOptionPane.showMessageDialog(null,
                    "No se pudo llenar tabla?", "Error?",
                    JOptionPane.INFORMATION_MESSAGE);
        }
        if(modeloTabla.getRowCount() == 0){
            setFilas();
        }
    }//GEN-LAST:event_jButton2ActionPerformed

   
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
            java.util.logging.Logger.getLogger(Modificar_item.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Modificar_item.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Modificar_item.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Modificar_item.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Modificar_item().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BTN_Del;
    private javax.swing.JButton JB_OK;
    private javax.swing.JButton JB_cancel;
    private javax.swing.JTextField JT_actual;
    private javax.swing.JTextField JT_bvcosto;
    private javax.swing.JTextField JT_bvventa;
    private javax.swing.JTextField JT_familia;
    private javax.swing.JTextField JT_nom;
    private javax.swing.JTextField JT_nombre;
    private javax.swing.JTextField JT_stock;
    private javax.swing.JTextField JT_vcosto;
    private javax.swing.JTextField JT_vventa;
    private javax.swing.JComboBox<String> cmb_fam;
    private javax.swing.JComboBox<String> cmb_med;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
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
