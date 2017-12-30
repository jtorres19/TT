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

public class Modificar_item extends javax.swing.JFrame {

    private Statement sentencia;
    Conexion con = new Conexion();
    Connection cn = (Connection) con.getConnection();
    private String msj;
    DefaultTableModel modeloTabla;
    private TableRowSorter trsfiltro;
    String filtro;

    public Modificar_item() {

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
            msj = "no se pudo seleccionar";
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
            msj = "no se pudo seleccionar";
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

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        JT_stock = new javax.swing.JTextField();
        JT_vcosto = new javax.swing.JTextField();
        JT_vventa = new javax.swing.JTextField();
        cmb_med = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        cmb_fam = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        JB_OK = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        JB_cancel = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        LBL_estado = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        JT_nom = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        JT_bvcosto = new javax.swing.JTextField();
        JT_bvventa = new javax.swing.JTextField();
        JT_nombre = new javax.swing.JTextField();
        JT_actual = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

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

        JB_OK.setText("OK");
        JB_OK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JB_OKActionPerformed(evt);
            }
        });

        jLabel6.setText("Stock Critico :");

        JB_cancel.setText("Volver");
        JB_cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JB_cancelActionPerformed(evt);
            }
        });

        jLabel7.setText("Medida :");

        jLabel9.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel9.setText("RACAD AUTOMOTRIZ - MODIFICAR ITEM");

        jLabel8.setText("Familia :");

        jLabel2.setText("Nombre :");

        JT_nom.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                JT_nomKeyTyped(evt);
            }
        });

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(JT_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JT_actual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JT_bvcosto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JT_bvventa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabel2)
                                            .addGap(36, 36, 36)
                                            .addComponent(JT_nom, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabel4)
                                            .addGap(18, 18, 18)
                                            .addComponent(JT_vcosto, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel7)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(cmb_med, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(34, 34, 34)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(JT_stock, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(JT_vventa, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cmb_fam, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 546, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(JB_OK, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(LBL_estado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(JB_cancel, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(14, 14, 14)))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JT_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JT_actual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JT_bvcosto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JT_bvventa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(JT_stock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(JT_nom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(JT_vventa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(JT_vcosto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmb_med, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(cmb_fam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(JB_cancel, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(LBL_estado, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JB_OK, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(29, 29, 29))
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
                msj = "Error con Medida";
            }
            try {
                sentencia = (com.mysql.jdbc.Statement) cn.createStatement();
                ResultSet rs = sentencia.executeQuery("SELECT id_familia FROM familia WHERE nombre = '" + fam + "'");
                while (rs.next()) {
                    fam2 = rs.getInt("id_familia");
                }
            } catch (SQLException t) {
                msj = "Error con Familia";
            }
            try {
                sentencia = (com.mysql.jdbc.Statement) cn.createStatement();
                ResultSet rs = sentencia.executeQuery("SELECT cod_item FROM inventario WHERE nombre = '" + nom + "'");
                while (rs.next()) {
                    cod = rs.getInt("cod_item");
                }

            } catch (SQLException f) {
                msj = "Error con Codigo";
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
                msj = "Datos Modificados";
                LBL_estado.setText(msj);
                dis = 1;
            } catch (SQLException e) {
                msj = "Item no Modificado";
                LBL_estado.setText(msj);
                dis = 0;
            }
            if (dis == 1) {
                clean();
            }
        } else {
            msj = "Datos Mal Ingresados";
            LBL_estado.setText(msj);
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
    private javax.swing.JButton JB_OK;
    private javax.swing.JButton JB_cancel;
    private javax.swing.JTextField JT_actual;
    private javax.swing.JTextField JT_bvcosto;
    private javax.swing.JTextField JT_bvventa;
    private javax.swing.JTextField JT_nom;
    private javax.swing.JTextField JT_nombre;
    private javax.swing.JTextField JT_stock;
    private javax.swing.JTextField JT_vcosto;
    private javax.swing.JTextField JT_vventa;
    private javax.swing.JLabel LBL_estado;
    private javax.swing.JComboBox<String> cmb_fam;
    private javax.swing.JComboBox<String> cmb_med;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
