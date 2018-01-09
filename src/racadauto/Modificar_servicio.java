package racadauto;

import Conexion.Conexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class Modificar_servicio extends javax.swing.JFrame {

    private Statement sentencia;
    Conexion con = new Conexion();
    Connection cn = (Connection) con.getConnection();
    private String msj;
    DefaultTableModel modeloTabla;
    private TableRowSorter trsfiltro;
    String filtro;

    public Modificar_servicio() {

        modeloTabla = new DefaultTableModel(null, getColumnas());
        setFilas();
        initComponents();
        llenarCombo();
    }

    private String[] getColumnas() {

        String columna[] = new String[]{"ID SERVICIO","COMPONENTE", "PRECIO", "CATEGORÍA"};

        return columna;
    }

    private void setFilas() {
        try {
            sentencia = (com.mysql.jdbc.Statement) cn.createStatement();
            ResultSet lista = sentencia.executeQuery("SELECT s.id_servicio,s.componente,s.precio,c.nombre"
                    + " FROM servicio s, categoria c"
                    + " WHERE s.id_categoria = c.id_categoria");
            Object datos[] = new Object[9];
            while (lista.next()) {
                for (int i = 0; i < 4; i++) {
                    datos[i] = lista.getObject(i + 1);
                }
                modeloTabla.addRow(datos);
            }
        } catch (SQLException e) {
            msj = "No se pudo llenar tabla";
        }
    }
    
    public void llenarCombo() {
        CMB_categoria.removeAllItems();
        try {
            sentencia = (com.mysql.jdbc.Statement) cn.createStatement();
            ResultSet lista = sentencia.executeQuery("SELECT * FROM categoria");
            while (lista.next()) {
                CMB_categoria.addItem(lista.getString("nombre"));
            }
        } catch (SQLException ed) {
            msj = "no se pudo seleccionar";
        }
    }

    void limpiaTabla() {
        if (modeloTabla.getRowCount() > 0) {
            do {
                modeloTabla.getRowCount();
                modeloTabla.removeRow(0);
            } while (modeloTabla.getRowCount() != 0);
        }
    }
    
    public void clean() {
        JT_comp.setText("");
        JT_price.setText("");
    }

   

    public int verificar() {

        int cont = 0;
        String componente = JT_comp.getText().toUpperCase().trim();
        String precio = JT_price.getText().trim();
        String cat = CMB_categoria.getSelectedItem().toString().trim();
        String nom = "", comp = "";

        try {
            sentencia = (com.mysql.jdbc.Statement) cn.createStatement();
            ResultSet rs = sentencia.executeQuery("SELECT componente FROM servicio");
            while (rs.next()) {
                comp = rs.getString("componente").toUpperCase().trim();
            }
        } catch (SQLException eg) {
            msj = "Error con su Solicitud";
        }
        
        if (jTable1.getSelectedRow() == -1 ){
            JOptionPane.showMessageDialog(null,
                    "ERROR, no se ha seleccionado ninguna fila", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            cont++;
        }
        
        if ((componente.equals(jTable1.getValueAt(jTable1.getSelectedRow(), 1).toString()))
                && (precio.equals(jTable1.getValueAt(jTable1.getSelectedRow(), 2).toString())) 
                && (cat.equals(jTable1.getValueAt(jTable1.getSelectedRow(), 3)))){
            JOptionPane.showMessageDialog(null,
                    "ERROR, no se ha MODIFICADO nada", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            cont++;
        }   

        if ((componente.equals(""))
                || (precio.equals(""))) {
            JOptionPane.showMessageDialog(null,
                    "ERROR, dejó una casilla vacía", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            cont++;
        }

        if (componente.length() > 30) {
            JOptionPane.showMessageDialog(null,
                    "ERROR, COMPONENTE máximo 30 letras", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            cont++;
        }

       if (precio.length() > 6 || Integer.parseInt(precio) < 1) {
            JOptionPane.showMessageDialog(null,
                    "ERROR, PRECIO <1M y >0", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            cont++;
        }

        return cont;
    }
    
    public int verificar2() {
        int yes = 0;
        int id = 0;
        int id2 = 0;
        String comp = jTable1.getValueAt(jTable1.getSelectedRow(), 1).toString();
        try {
            sentencia = (com.mysql.jdbc.Statement) cn.createStatement();
            ResultSet rs = sentencia.executeQuery("SELECT id_servicio FROM servicio WHERE componente = '" + comp + "'");
            while (rs.next()) {
                id = rs.getInt("id_servicio");
            }
        } catch (SQLException eg) {
            msj = "Error con su Solicitud";
        }
        try {
            sentencia = (com.mysql.jdbc.Statement) cn.createStatement();
            ResultSet rs = sentencia.executeQuery("SELECT * FROM detalle_insumo");
            while (rs.next()) {
                id2 = rs.getInt("id_servicio");
                if (id == id2) {
                    yes += rs.getInt("cod_item");
                }
            }

        } catch (SQLException t) {
            msj = "Error con su Solicitud";
        }
        try {
            sentencia = (com.mysql.jdbc.Statement) cn.createStatement();
            ResultSet rs = sentencia.executeQuery("SELECT * FROM detalle_solicitud");
            while (rs.next()) {
                id2 = rs.getInt("id_servicio");
                if (id == id2) {
                    yes += rs.getInt("cod_item");
                }
            }

        } catch (SQLException t) {
            msj = "Error con su Solicitud";
        }
        
        if (yes > 0){
            JOptionPane.showMessageDialog(null,
                    "ERROR, SERVICIO referenciado en otras tablas no se puede eliminar", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
        }
        
        return yes;
    }
    
    public void filtroCategoria() {

        filtro = JT_categoria.getText().toUpperCase();
        int columna = 3;
        trsfiltro.setRowFilter(RowFilter.regexFilter(JT_categoria.getText().toUpperCase(), columna));
    }
    
    public void filtroComponente() {

        filtro = JT_componente.getText().toUpperCase();
        int columna = 1;
        trsfiltro.setRowFilter(RowFilter.regexFilter(JT_componente.getText().toUpperCase(), columna));
    }
    
    public void filtroPrecio() {

        filtro = JT_precio.getText().toUpperCase();
        int columna = 2;
        trsfiltro.setRowFilter(RowFilter.regexFilter(JT_precio.getText().toUpperCase(), columna));
    }
    
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        JB_cancel = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        JT_categoria = new javax.swing.JTextField();
        JT_componente = new javax.swing.JTextField();
        JT_precio = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        JT_comp = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        JT_price = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        CMB_categoria = new javax.swing.JComboBox<>();
        JB_OK = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        BTN_Del = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("MODIFICAR SERVICIO");

        JB_cancel.setText("\u2B8C");
        JB_cancel.setToolTipText("Volver");
        JB_cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JB_cancelActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel9.setText("MANTENER SERVICIO");

        jTable1.setModel(modeloTabla);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        JT_categoria.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        JT_categoria.setForeground(new java.awt.Color(153, 153, 153));
        JT_categoria.setText("Buscar por categoria");
        JT_categoria.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                JT_categoriaFocusLost(evt);
            }
        });
        JT_categoria.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JT_categoriaMouseClicked(evt);
            }
        });
        JT_categoria.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                JT_categoriaKeyTyped(evt);
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

        JT_precio.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        JT_precio.setForeground(new java.awt.Color(153, 153, 153));
        JT_precio.setText("Buscar por precio");
        JT_precio.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                JT_precioFocusLost(evt);
            }
        });
        JT_precio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JT_precioMouseClicked(evt);
            }
        });
        JT_precio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                JT_precioKeyTyped(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("MODIFICAR");
        jLabel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        JT_comp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                JT_compKeyTyped(evt);
            }
        });

        jLabel2.setText("Componente: ");

        jLabel3.setText("Precio: ");

        JT_price.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                JT_priceKeyTyped(evt);
            }
        });

        jLabel4.setText("Categoría: ");

        JB_OK.setText("\uD83D\uDDAB");
        JB_OK.setToolTipText("Modificar Servicio");
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
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(18, 18, 18)
                                .addComponent(CMB_categoria, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(JT_comp, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(36, 36, 36)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(JT_price, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(JB_OK, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JT_comp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JT_price, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CMB_categoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JB_OK))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/racadauto/Images/ok.jpg"))); // NOI18N
        jLabel5.setText("jLabel3");
        jLabel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        BTN_Del.setText("\uD83D\uDDD1");
        BTN_Del.setToolTipText("Eliminar Servicio");
        BTN_Del.setPreferredSize(new java.awt.Dimension(100, 35));
        BTN_Del.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_DelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addComponent(JT_categoria, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(JT_componente, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(JT_precio, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 487, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(JB_cancel, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BTN_Del, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(54, 54, 54)
                        .addComponent(jLabel9)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(BTN_Del, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(49, 49, 49)
                        .addComponent(JB_cancel)
                        .addGap(35, 35, 35)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JT_categoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JT_componente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JT_precio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void JB_OKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JB_OKActionPerformed
        if (verificar() == 0) {
            String componente = "",categoria = "";
            int cod = 0,dis, cat = 0;
            componente = jTable1.getValueAt(jTable1.getSelectedRow(), 1).toString().trim();
            String componente2 = JT_comp.getText().toUpperCase().trim();
            int precio = Integer.parseInt(JT_price.getText().trim());
            categoria = (String) CMB_categoria.getSelectedItem();

            try {
                sentencia = (com.mysql.jdbc.Statement) cn.createStatement();
                ResultSet rs = sentencia.executeQuery("SELECT id_categoria FROM categoria WHERE  nombre = '" + categoria + "'");
                while (rs.next()) {
                    cat = rs.getInt("id_categoria");
                }
            } catch (SQLException s) {
                msj = "Error con CATEGORIA";
            }
            
            
            try {
                sentencia = (com.mysql.jdbc.Statement) cn.createStatement();
                ResultSet rs = sentencia.executeQuery("SELECT id_servicio FROM servicio WHERE componente = '" + componente + "'");
                while (rs.next()) {
                    cod = rs.getInt("id_servicio");
                }
                

            } catch (SQLException f) {
                msj = "Error con Codigo";
            }

            String sql = "UPDATE servicio "
                    + "SET componente = '" + componente2 + "',"
                    + "precio = '" + precio + "',"
                    + "id_categoria = '" + cat + "'"
                    + "WHERE id_servicio = '" + cod + "'";
            try {
                sentencia.executeUpdate(sql);
                JOptionPane.showMessageDialog(null,
                    "Servicio Modificado!", "Exito",
                    JOptionPane.INFORMATION_MESSAGE);
                dis = 1;
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null,
                    "Servicio NO modificado", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
                dis = 0;
            }
            if (dis == 1) {
                clean();
            }
        } else {
            JOptionPane.showMessageDialog(null,
                    "Datos mal escritos", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
        }

        limpiaTabla();
        setFilas();
    }//GEN-LAST:event_JB_OKActionPerformed

    private void JB_cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JB_cancelActionPerformed
        this.dispose();
    }//GEN-LAST:event_JB_cancelActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        
        String componente = jTable1.getValueAt(jTable1.getSelectedRow(), 1).toString().toUpperCase();
        JT_comp.setText(componente);
        
        String precio = jTable1.getValueAt(jTable1.getSelectedRow(), 2).toString().toUpperCase();
        JT_price.setText(precio);
        
        String cat = jTable1.getValueAt(jTable1.getSelectedRow(), 3).toString();
        CMB_categoria.setSelectedItem(cat);
    }//GEN-LAST:event_jTable1MouseClicked

    private void JT_compKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JT_compKeyTyped
        char validar = evt.getKeyChar();

        if (!Character.isLetter(validar) && validar != evt.VK_SPACE && validar != evt.VK_BACK_SPACE) {
            getToolkit().beep();
            evt.consume();

            JOptionPane.showMessageDialog(null,
                    "Error, COMPONENTE solo pueden ser letras", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_JT_compKeyTyped

    private void JT_priceKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JT_priceKeyTyped
        char validar = evt.getKeyChar();

        if (!Character.isDigit(validar) && validar != evt.VK_BACK_SPACE) {
            getToolkit().beep();
            evt.consume();

            JOptionPane.showMessageDialog(null,
                    "Error, PRECIO solo pueden ser números", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_JT_priceKeyTyped

    private void JT_categoriaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_JT_categoriaFocusLost
        JT_categoria.setFont(new java.awt.Font("Tahoma",2,11));
        JT_categoria.setForeground(new java.awt.Color(153,153,153));
        JT_categoria.setText("Buscar por categoria");
    }//GEN-LAST:event_JT_categoriaFocusLost

    private void JT_categoriaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JT_categoriaMouseClicked
        JT_categoria.setText("");
        JT_categoria.setFont(new java.awt.Font("Tahoma",0,11));
        JT_categoria.setForeground(new java.awt.Color(0,0,0));
    }//GEN-LAST:event_JT_categoriaMouseClicked

    private void JT_categoriaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JT_categoriaKeyTyped
        JT_categoria.addKeyListener(new KeyAdapter() {
            public void keyReleased(final KeyEvent e) {
                filtroCategoria();
            }
        });
        trsfiltro = new TableRowSorter(modeloTabla);
        jTable1.setRowSorter(trsfiltro);
    }//GEN-LAST:event_JT_categoriaKeyTyped

    private void JT_componenteFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_JT_componenteFocusLost
        JT_componente.setFont(new java.awt.Font("Tahoma",2,11));
        JT_componente.setForeground(new java.awt.Color(153,153,153));
        JT_componente.setText("Buscar por componente");
    }//GEN-LAST:event_JT_componenteFocusLost

    private void JT_componenteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JT_componenteMouseClicked
        JT_componente.setText("");
        JT_componente.setFont(new java.awt.Font("Tahoma",0,11));
        JT_componente.setForeground(new java.awt.Color(0,0,0));
    }//GEN-LAST:event_JT_componenteMouseClicked

    private void JT_componenteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JT_componenteKeyTyped
        JT_componente.addKeyListener(new KeyAdapter() {
            public void keyReleased(final KeyEvent e) {
                filtroComponente();
            }
        });
        trsfiltro = new TableRowSorter(modeloTabla);
        jTable1.setRowSorter(trsfiltro);
    }//GEN-LAST:event_JT_componenteKeyTyped

    private void JT_precioFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_JT_precioFocusLost
        JT_precio.setFont(new java.awt.Font("Tahoma",2,11));
        JT_precio.setForeground(new java.awt.Color(153,153,153));
        JT_precio.setText("Buscar por precio");
    }//GEN-LAST:event_JT_precioFocusLost

    private void JT_precioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JT_precioMouseClicked
        JT_precio.setText("");
        JT_precio.setFont(new java.awt.Font("Tahoma",0,11));
        JT_precio.setForeground(new java.awt.Color(0,0,0));
    }//GEN-LAST:event_JT_precioMouseClicked

    private void JT_precioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JT_precioKeyTyped
        JT_precio.addKeyListener(new KeyAdapter() {
            public void keyReleased(final KeyEvent e) {
                filtroPrecio();
            }
        });
        trsfiltro = new TableRowSorter(modeloTabla);
        jTable1.setRowSorter(trsfiltro);
    }//GEN-LAST:event_JT_precioKeyTyped

    private void BTN_DelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_DelActionPerformed
        if (jTable1.getSelectedRow() == -1){
            JOptionPane.showMessageDialog(null,
                "ERROR, No se ha seleccionado ninguna fila", "ERROR",
                JOptionPane.ERROR_MESSAGE);
        }

        String comp = jTable1.getValueAt(jTable1.getSelectedRow(), 1).toString().trim();

        int i = JOptionPane.showConfirmDialog(this,
            "¿Realmente desea eliminar " + comp + " de los SERVICIOS?","Confirmar Eliminación",
            JOptionPane.YES_NO_OPTION);

        int id = 0;
        try {
            sentencia = (com.mysql.jdbc.Statement) cn.createStatement();
            ResultSet rs = sentencia.executeQuery("SELECT id_servicio FROM servicio WHERE componente = '" + comp + "'");
            while (rs.next()) {
                id = rs.getInt("id_servicio");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "No se encontró servicio", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
        }

        if (verificar2() == 0 && i== 0) {
            String sql = "DELETE FROM servicio WHERE id_servicio =" + id + "";
            try {
                sentencia.executeUpdate(sql);
                JOptionPane.showMessageDialog(null,
                    "Servicio Borrado!", "Exito",
                    JOptionPane.INFORMATION_MESSAGE);
            } catch (SQLException ee) {
                JOptionPane.showMessageDialog(null,
                    "No se pudo borrar servicio", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            }
        }

        clean();
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
            java.util.logging.Logger.getLogger(Modificar_servicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Modificar_servicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Modificar_servicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Modificar_servicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Modificar_servicio().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BTN_Del;
    private javax.swing.JComboBox<String> CMB_categoria;
    private javax.swing.JButton JB_OK;
    private javax.swing.JButton JB_cancel;
    private javax.swing.JTextField JT_categoria;
    private javax.swing.JTextField JT_comp;
    private javax.swing.JTextField JT_componente;
    private javax.swing.JTextField JT_precio;
    private javax.swing.JTextField JT_price;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
