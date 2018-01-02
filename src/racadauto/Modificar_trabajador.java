/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package racadauto;

import Conexion.Conexion;
import Conexion.Hash;
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

/**
 *
 * @author falco
 */
public class Modificar_trabajador extends javax.swing.JFrame {

    private Statement sentencia;
    Conexion con = new Conexion();
    Connection cn = (Connection) con.getConnection();
    private String msj;
    DefaultTableModel modeloTabla;
    private TableRowSorter trsfiltro;
    String filtro;

    public Modificar_trabajador() {
        modeloTabla = new DefaultTableModel(null, getColumnas());
        setFilas();
        initComponents();
        llenarCombo();
    }

    private String[] getColumnas() {

        String columna[] = new String[]{"RUT", "NOMBRE", "APELLIDO PATERNO", "APELLIDO MATERNO", "CONTRASEÑA", "FONO", "MAIL", "CARGO"};

        return columna;
    }

    private void setFilas() {
        try {
            sentencia = (com.mysql.jdbc.Statement) cn.createStatement();
            ResultSet lista = sentencia.executeQuery("SELECT t.rut_trabajador,t.nombre,t.ape_paterno,t.ape_materno,t.contraseña,t.fono,t.email,c.nombre "
                    + "                                 FROM trabajador t,cargo c "
                    + "                                 WHERE t.id_cargo = c.id_cargo");
            Object datos[] = new Object[8];
            while (lista.next()) {
                for (int i = 0; i < 8; i++) {
                    datos[i] = lista.getObject(i + 1);
                }
                modeloTabla.addRow(datos);
            }
        } catch (SQLException e) {
            msj = "No se pudo llenar tabla";
        }
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

        filtro = JT_paterno1.getText().toUpperCase();
        int columna = 2;
        trsfiltro.setRowFilter(RowFilter.regexFilter(JT_paterno1.getText().toUpperCase(), columna));
    }

    public void filtroMaterno() {

        filtro = JT_materno1.getText().toUpperCase();
        int columna = 3;
        trsfiltro.setRowFilter(RowFilter.regexFilter(JT_materno1.getText().toUpperCase(), columna));
    }
    
    
    public void filtroCargo() {

        filtro = JT_cargo.getText().toUpperCase();
        int columna = 7;
        trsfiltro.setRowFilter(RowFilter.regexFilter(JT_cargo.getText().toUpperCase(), columna));
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
        JT_nom.setText("");
        JT_paterno.setText("");
        JT_materno.setText("");
        JT_fono.setText("");
        JT_mail.setText("");
    }

    public void llenarCombo() {
        cmb_cargo.removeAllItems();
        try {
            sentencia = (com.mysql.jdbc.Statement) cn.createStatement();
            ResultSet lista = sentencia.executeQuery("SELECT * FROM cargo");
            while (lista.next()) {
                cmb_cargo.addItem(lista.getString("nombre"));
            }
        } catch (SQLException ed) {
            msj = "no se pudo seleccionar";
        }
    }

    public int verificar() {

        int cont = 0;

        String nombre = JT_nom.getText().toUpperCase().trim();
        String pass = new String(JP_contraseña.getPassword()).trim();
        String passconfirm = new String(JP_confirmarcontraseña.getPassword()).trim();
        String paterno = JT_paterno.getText().toUpperCase().trim();
        String materno = JT_materno.getText().toUpperCase().trim();
        String fono = String.valueOf(JT_fono.getText().trim());
        String mail = JT_mail.getText().trim();
        String cargo = (String) cmb_cargo.getSelectedItem();
        String rut2 = "";

        try {
            sentencia = (com.mysql.jdbc.Statement) cn.createStatement();
            ResultSet rs = sentencia.executeQuery("SELECT rut_trabajador FROM trabajador");
            while (rs.next()) {
                rut2 = rs.getString("rut_trabajador").trim();
            }
        } catch (SQLException eg) {
            msj = "Error con su Solicitud";
        }

        if (jTable1.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(null,
                    "ERROR, No se ha seleccionado ninguna fila", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            cont++;
        }

        if ((nombre.equals(""))
                || (paterno.equals(""))
                || (materno.equals(""))
                || (pass.equals(""))
                || (passconfirm.equals(""))
                || (fono.equals(""))
                || (mail.equals(""))) {
            JOptionPane.showMessageDialog(null,
                    "ERROR, dejó una casilla vacía", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            cont++;
        }

        if ((nombre.equals(jTable1.getValueAt(jTable1.getSelectedRow(), 1).toString()))
                && (paterno.equals(jTable1.getValueAt(jTable1.getSelectedRow(), 2).toString()))
                && (materno.equals(jTable1.getValueAt(jTable1.getSelectedRow(), 3).toString()))
                && (fono.equals(jTable1.getValueAt(jTable1.getSelectedRow(), 4).toString()))
                && (mail.equals(jTable1.getValueAt(jTable1.getSelectedRow(), 5).toString()))
                && (cargo.equals(jTable1.getValueAt(jTable1.getSelectedRow(), 6)))) {
            JOptionPane.showMessageDialog(null,
                    "ERROR, no se ha MODIFICADO nada", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            cont++;
        }

        if (nombre.length() >= 40) {
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

        if (fono.length() > 15) {
            JOptionPane.showMessageDialog(null,
                    "ERROR, FONO no puede exceder los 15 numeros", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            cont++;
        }

        if (mail.length() > 30) {
            JOptionPane.showMessageDialog(null,
                    "ERROR, MAIL no puede exceder los 30 caracteres", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            cont++;
        } else if (!mail.matches("^([0-9a-zA-Z]([_.w]*[0-9a-zA-Z])*@([0-9a-zA-Z][-w]*[0-9a-zA-Z].)+([a-zA-Z]{2,9}.)+[a-zA-Z]{2,3})$")) {
            JOptionPane.showMessageDialog(null,
                    "ERROR, MAIL mal escrito", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            cont++;
        }
        
        if (!pass.equals(passconfirm)) {
            JOptionPane.showMessageDialog(null,
                    "ERROR, CONTRASEÑAS no coinciden", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            cont++;
        }

        return cont;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel9 = new javax.swing.JLabel();
        JB_cancel = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        JT_nom = new javax.swing.JTextField();
        JT_paterno = new javax.swing.JTextField();
        JT_materno = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        JT_fono = new javax.swing.JTextField();
        JT_mail = new javax.swing.JTextField();
        cmb_cargo = new javax.swing.JComboBox<>();
        JB_OK = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        LBL_estado = new javax.swing.JLabel();
        JT_rut = new javax.swing.JTextField();
        JT_nombre = new javax.swing.JTextField();
        JT_paterno1 = new javax.swing.JTextField();
        JT_materno1 = new javax.swing.JTextField();
        JT_cargo = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        JP_contraseña = new javax.swing.JPasswordField();
        jLabel10 = new javax.swing.JLabel();
        JP_confirmarcontraseña = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("MODIFICAR EMPLEADO");

        jLabel9.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel9.setText("RACAD AUTOMOTRIZ - MODIFICAR TRABAJADOR");

        JB_cancel.setText("Volver");
        JB_cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JB_cancelActionPerformed(evt);
            }
        });

        jLabel2.setText("Nombre :");

        jLabel3.setText("Apellido Paterno :");

        jLabel4.setText("Apellido Materno :");

        JT_nom.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                JT_nomKeyTyped(evt);
            }
        });

        JT_paterno.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                JT_paternoKeyTyped(evt);
            }
        });

        JT_materno.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                JT_maternoKeyTyped(evt);
            }
        });

        jLabel5.setText("Fono :");

        jLabel6.setText("Cargo :");

        jLabel7.setText("E-Mail :");

        JT_fono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                JT_fonoKeyTyped(evt);
            }
        });

        JT_mail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JT_mailActionPerformed(evt);
            }
        });

        JB_OK.setText("Modificar");
        JB_OK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JB_OKActionPerformed(evt);
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

        jLabel8.setText("Contraseña:");

        JP_contraseña.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JP_contraseñaActionPerformed(evt);
            }
        });

        jLabel10.setText("<html>Confirmar contraseña:<hml>");

        JP_confirmarcontraseña.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JP_confirmarcontraseñaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(JB_OK)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(JB_cancel, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(JT_rut, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(JT_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(JT_paterno1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(JT_materno1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 379, Short.MAX_VALUE)
                        .addComponent(JT_cargo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(JT_fono, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabel4)
                                            .addGap(18, 18, 18)
                                            .addComponent(JT_materno, javax.swing.GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE))
                                        .addGroup(layout.createSequentialGroup()
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel3)
                                                .addComponent(jLabel2))
                                            .addGap(20, 20, 20)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(JT_paterno, javax.swing.GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE)
                                                .addComponent(JT_nom))))
                                    .addComponent(JT_mail, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(150, 150, 150)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cmb_cargo, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(JP_confirmarcontraseña, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(JP_contraseña, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel9)
                            .addComponent(jLabel5)
                            .addComponent(jLabel7)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(LBL_estado, javax.swing.GroupLayout.PREFERRED_SIZE, 371, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(35, 35, 35)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(JT_rut, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(JT_paterno1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(JT_materno1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(JT_cargo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(JT_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(JT_nom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(JP_contraseña, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(JT_paterno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JP_confirmarcontraseña, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(JT_materno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(cmb_cargo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(JT_fono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(JT_mail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(JB_cancel, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(JB_OK, javax.swing.GroupLayout.Alignment.TRAILING))
                    .addComponent(LBL_estado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void JB_cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JB_cancelActionPerformed
        this.dispose();
    }//GEN-LAST:event_JB_cancelActionPerformed

    private void JT_mailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JT_mailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JT_mailActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        String nom = jTable1.getValueAt(jTable1.getSelectedRow(), 1).toString();
        JT_nom.setText(nom);

        String paterno = jTable1.getValueAt(jTable1.getSelectedRow(), 2).toString();
        JT_paterno.setText(paterno);

        String materno = jTable1.getValueAt(jTable1.getSelectedRow(), 3).toString();
        JT_materno.setText(materno);

        /*String contraseña = jTable1.getValueAt(jTable1.getSelectedRow(), 4).toString();
        JP_contraseña.setText(contraseña);*/
        
        String fono = jTable1.getValueAt(jTable1.getSelectedRow(), 5).toString();
        JT_fono.setText(fono);

        String mail = jTable1.getValueAt(jTable1.getSelectedRow(), 6).toString();
        JT_mail.setText(mail);

        String cargo = jTable1.getValueAt(jTable1.getSelectedRow(), 7).toString();
        cmb_cargo.setSelectedItem(cargo);


    }//GEN-LAST:event_jTable1MouseClicked

    private void JB_OKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JB_OKActionPerformed
        // TODO add your handling code here:
        if (verificar() == 0) {
            String rut = "", cargo, rut2 = "";
            rut = jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString().trim();
            int dis;
            String nombre = JT_nom.getText().toUpperCase().trim();
            String paterno = JT_paterno.getText().toUpperCase().trim();
            String materno = JT_materno.getText().toUpperCase().trim();
            String pass = new String(JP_contraseña.getPassword()).trim();
            String contraseña = Hash.sha1(pass).trim();
            String mail = JT_mail.getText().trim();
            int fono = Integer.parseInt(JT_fono.getText().trim());
            cargo = (String) cmb_cargo.getSelectedItem();

            int cargo2 = 0;

            try {
                sentencia = (com.mysql.jdbc.Statement) cn.createStatement();
                ResultSet rs = sentencia.executeQuery("SELECT id_cargo FROM cargo WHERE  nombre = '" + cargo + "'");
                while (rs.next()) {
                    cargo2 = rs.getInt("id_cargo");
                }
            } catch (SQLException s) {
                msj = "Error con CARGO";
            }

            try {
                sentencia = (com.mysql.jdbc.Statement) cn.createStatement();
                ResultSet rs = sentencia.executeQuery("SELECT rut_trabajador FROM trabajador WHERE rut_trabajador = '" + rut + "'");
                while (rs.next()) {
                    rut2 = rs.getString("rut_trabajador").trim();
                }

            } catch (SQLException f) {
                msj = "Error con CODIGO";
            }

            String sql = "UPDATE trabajador "
                    + "SET contraseña = '" + contraseña + "',"
                    + "nombre ='" + nombre + "',"
                    + "ape_paterno = '" + paterno + "',"
                    + "ape_materno = '" + materno + "',"
                    + "fono = '" + fono + "',"
                    + "email = '" + mail + "',"
                    + "id_cargo = '" + cargo2 + "'"
                    + "WHERE rut_trabajador = '" + rut2 + "'";
            try {
                sentencia.executeUpdate(sql);
                msj = "Datos modificados";
                LBL_estado.setText(msj);
                dis = 1;
            } catch (SQLException e) {
                msj = "TRABAJADOR no modificado";
                LBL_estado.setText(msj);
                dis = 0;
            }
            if (dis == 1) {
                clean();
            }
        } else {
            msj = "Datos mal escritos";
            LBL_estado.setText(msj);
        }

        limpiaTabla();
        setFilas();
    }//GEN-LAST:event_JB_OKActionPerformed

    private void JT_nomKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JT_nomKeyTyped
        char validar = evt.getKeyChar();

        if (!Character.isLetter(validar) && validar != evt.VK_BACK_SPACE && validar != evt.VK_SPACE) {
            getToolkit().beep();
            evt.consume();

            JOptionPane.showMessageDialog(null,
                    "ERROR, NOMBRE solo pueden ser letras", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_JT_nomKeyTyped

    private void JT_paternoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JT_paternoKeyTyped
        char validar = evt.getKeyChar();

        if (!Character.isLetter(validar) && validar != evt.VK_BACK_SPACE && validar != evt.VK_SPACE) {
            getToolkit().beep();
            evt.consume();

            JOptionPane.showMessageDialog(null,
                    "ERROR, APELLIDO PATERNO solo pueden ser letras", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_JT_paternoKeyTyped

    private void JT_maternoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JT_maternoKeyTyped
        char validar = evt.getKeyChar();

        if (!Character.isLetter(validar) && validar != evt.VK_BACK_SPACE && validar != evt.VK_SPACE) {
            getToolkit().beep();
            evt.consume();

            JOptionPane.showMessageDialog(null,
                    "ERROR, APELLIDO MATERNO solo pueden ser letras", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_JT_maternoKeyTyped

    private void JT_fonoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JT_fonoKeyTyped
        char validar = evt.getKeyChar();

        if (!Character.isDigit(validar) && validar != evt.VK_BACK_SPACE) {
            getToolkit().beep();
            evt.consume();

            JOptionPane.showMessageDialog(null,
                    "ERROR, FONO solo pueden ser números", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_JT_fonoKeyTyped

    private void JT_rutFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_JT_rutFocusLost
        JT_rut.setText("");
        JT_rut.setFont(new java.awt.Font("Tahoma",2,11));
        JT_rut.setForeground(new java.awt.Color(153,153,153));
        JT_rut.setText("Buscar por rut");
        limpiaTabla();
        setFilas();
    }//GEN-LAST:event_JT_rutFocusLost

    private void JT_rutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JT_rutMouseClicked
        JT_rut.setText("");
        JT_rut.setFont(new java.awt.Font("Tahoma",0,11));
        JT_rut.setForeground(new java.awt.Color(0,0,0));
        limpiaTabla();
        setFilas();
    }//GEN-LAST:event_JT_rutMouseClicked

    private void JT_rutKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JT_rutKeyTyped
        JT_rut.addKeyListener(new KeyAdapter() {
            public void keyReleased(final KeyEvent e) {
                filtroRut();
            }
        });
        trsfiltro = new TableRowSorter(modeloTabla);
        jTable1.setRowSorter(trsfiltro);
    }//GEN-LAST:event_JT_rutKeyTyped

    private void JT_nombreFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_JT_nombreFocusLost
        JT_nombre.setText("");
        JT_nombre.setFont(new java.awt.Font("Tahoma",2,11));
        JT_nombre.setForeground(new java.awt.Color(153,153,153));
        JT_nombre.setText("Buscar por nombre");
        limpiaTabla();
        setFilas();
    }//GEN-LAST:event_JT_nombreFocusLost

    private void JT_nombreMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JT_nombreMouseClicked
        JT_nombre.setText("");
        JT_nombre.setFont(new java.awt.Font("Tahoma",0,11));
        JT_nombre.setForeground(new java.awt.Color(0,0,0));
        limpiaTabla();
        setFilas();
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

    private void JT_paterno1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_JT_paterno1FocusLost
        JT_paterno1.setText("");
        JT_paterno1.setFont(new java.awt.Font("Tahoma",2,11));
        JT_paterno1.setForeground(new java.awt.Color(153,153,153));
        JT_paterno1.setText("Buscar por paterno");
        limpiaTabla();
        setFilas();
    }//GEN-LAST:event_JT_paterno1FocusLost

    private void JT_paterno1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JT_paterno1MouseClicked
        JT_paterno1.setText("");
        JT_paterno1.setFont(new java.awt.Font("Tahoma",0,11));
        JT_paterno1.setForeground(new java.awt.Color(0,0,0));
        limpiaTabla();
        setFilas();
    }//GEN-LAST:event_JT_paterno1MouseClicked

    private void JT_paterno1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JT_paterno1KeyTyped
        JT_paterno1.addKeyListener(new KeyAdapter() {
            public void keyReleased(final KeyEvent e) {
                filtroPaterno();
            }
        });
        trsfiltro = new TableRowSorter(modeloTabla);
        jTable1.setRowSorter(trsfiltro);
    }//GEN-LAST:event_JT_paterno1KeyTyped

    private void JT_materno1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_JT_materno1FocusLost
        JT_materno1.setText("");
        JT_materno1.setFont(new java.awt.Font("Tahoma",2,11));
        JT_materno1.setForeground(new java.awt.Color(153,153,153));
        JT_materno1.setText("Buscar por materno");
        limpiaTabla();
        setFilas();
    }//GEN-LAST:event_JT_materno1FocusLost

    private void JT_materno1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JT_materno1MouseClicked
        JT_materno1.setText("");
        JT_materno1.setFont(new java.awt.Font("Tahoma",0,11));
        JT_materno1.setForeground(new java.awt.Color(0,0,0));
        limpiaTabla();
        setFilas();
    }//GEN-LAST:event_JT_materno1MouseClicked

    private void JT_materno1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JT_materno1KeyTyped
        JT_materno1.addKeyListener(new KeyAdapter() {
            public void keyReleased(final KeyEvent e) {
                filtroMaterno();
            }
        });
        trsfiltro = new TableRowSorter(modeloTabla);
        jTable1.setRowSorter(trsfiltro);
    }//GEN-LAST:event_JT_materno1KeyTyped

    private void JT_cargoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_JT_cargoFocusLost
        JT_cargo.setText("");
        JT_cargo.setFont(new java.awt.Font("Tahoma",2,11));
        JT_cargo.setForeground(new java.awt.Color(153,153,153));
        JT_cargo.setText("Buscar por ciudad");
        limpiaTabla();
        setFilas();
    }//GEN-LAST:event_JT_cargoFocusLost

    private void JT_cargoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JT_cargoMouseClicked
        JT_cargo.setText("");
        JT_cargo.setFont(new java.awt.Font("Tahoma",0,11));
        JT_cargo.setForeground(new java.awt.Color(0,0,0));
        limpiaTabla();
        setFilas();
    }//GEN-LAST:event_JT_cargoMouseClicked

    private void JT_cargoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JT_cargoKeyTyped
        JT_cargo.addKeyListener(new KeyAdapter() {
            public void keyReleased(final KeyEvent e) {
                filtroCargo();
            }
        });
        trsfiltro = new TableRowSorter(modeloTabla);
        jTable1.setRowSorter(trsfiltro);
    }//GEN-LAST:event_JT_cargoKeyTyped

    private void JP_contraseñaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JP_contraseñaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JP_contraseñaActionPerformed

    private void JP_confirmarcontraseñaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JP_confirmarcontraseñaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JP_confirmarcontraseñaActionPerformed

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
            java.util.logging.Logger.getLogger(Ingresar_trabajador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Ingresar_trabajador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Ingresar_trabajador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Ingresar_trabajador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Modificar_trabajador().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton JB_OK;
    private javax.swing.JButton JB_cancel;
    private javax.swing.JPasswordField JP_confirmarcontraseña;
    private javax.swing.JPasswordField JP_contraseña;
    private javax.swing.JTextField JT_cargo;
    private javax.swing.JTextField JT_fono;
    private javax.swing.JTextField JT_mail;
    private javax.swing.JTextField JT_materno;
    private javax.swing.JTextField JT_materno1;
    private javax.swing.JTextField JT_nom;
    private javax.swing.JTextField JT_nombre;
    private javax.swing.JTextField JT_paterno;
    private javax.swing.JTextField JT_paterno1;
    private javax.swing.JTextField JT_rut;
    private javax.swing.JLabel LBL_estado;
    private javax.swing.JComboBox<String> cmb_cargo;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
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
