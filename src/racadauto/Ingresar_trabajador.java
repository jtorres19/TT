package racadauto;

import Conexion.Conexion;
import Conexion.Hash;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Ingresar_trabajador extends javax.swing.JFrame {

    private Statement sentencia;
    Conexion con = new Conexion();
    Connection cn = (Connection) con.getConnection();
    private String msj;
    DefaultTableModel modeloTabla;

    public Ingresar_trabajador() {
        modeloTabla = new DefaultTableModel(null, getColumnas());
        setFilas();
        initComponents();
        llenarCombo();
    }

    private String[] getColumnas() {

        String columna[] = new String[]{"RUT", "NOMBRE", "APELLIDO PATERNO", "APELLIDO MATERNO", "FONO", "MAIL", "CARGO"};

        return columna;
    }

    private void setFilas() {
        try {
            sentencia = (com.mysql.jdbc.Statement) cn.createStatement();
            ResultSet lista = sentencia.executeQuery("SELECT t.rut_trabajador,t.nombre,t.ape_paterno,t.ape_materno,t.fono,t.email,c.nombre "
                    + " FROM trabajador t,cargo c "
                    + " WHERE t.id_cargo = c.id_cargo");
            Object datos[] = new Object[7];
            while (lista.next()) {
                for (int i = 0; i < 7; i++) {
                    datos[i] = lista.getObject(i + 1);
                }
                modeloTabla.addRow(datos);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Problemas con la base de datos, no se pudo llenar la tabla", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
        
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

    public void llenarCombo() {
        cmb_cargo.removeAllItems();
        try {
            sentencia = (com.mysql.jdbc.Statement) cn.createStatement();
            ResultSet lista = sentencia.executeQuery("SELECT * FROM cargo");
            while (lista.next()) {
                cmb_cargo.addItem(lista.getString("nombre"));
            }
        } catch (SQLException ed) {
            JOptionPane.showMessageDialog(null,
                    "Problemas con la base de datos, no se pudo llenar la lista", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public void clean() {

        JT_rut.setText("");
        JT_nom.setText("");
        JT_fono.setText("");
        JT_mail.setText("");
        JT_materno.setText("");
        JT_paterno.setText("");

    }

    public int verificar() {

        int cont = 0;

        String rut = JT_rut.getText().trim();
        String pass = new String(JP_contraseña.getPassword()).trim();
        String passconfirm = new String(JP_confirmarcontraseña.getPassword()).trim();
        String nombre = JT_nom.getText().toUpperCase().trim();
        String paterno = JT_paterno.getText().toUpperCase().trim();
        String materno = JT_materno.getText().toUpperCase().trim();
        String fono = String.valueOf(JT_fono.getText()).trim();
        String mail = JT_mail.getText().trim();
        String cargo = (String) cmb_cargo.getSelectedItem();
        String rut2 = "";

        try {
            sentencia = (com.mysql.jdbc.Statement) cn.createStatement();
            ResultSet rs = sentencia.executeQuery("SELECT rut_trabajador FROM trabajador");
            while (rs.next()) {
                rut2 = rs.getString("rut_trabajador").trim();
                if (rut.equals(rut2)) {
                    JOptionPane.showMessageDialog(null,
                            "ERROR, ya existe trabajador!", "ERROR",
                            JOptionPane.ERROR_MESSAGE);
                    cont++;
                }
            }
        } catch (SQLException eg) {
            JOptionPane.showMessageDialog(null,
                    "Problemas con la base de datos, no se pudo otener informacion", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
        }

        if ((rut.equals(""))
                || (pass.equals(""))
                || (passconfirm.equals(""))
                || (nombre.equals(""))
                || (paterno.equals(""))
                || (materno.equals(""))
                || (fono.equals(""))
                || (mail.equals(""))) {
            JOptionPane.showMessageDialog(null,
                    "ERROR, dejó una casilla vacía", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            cont++;
        }

        if (!rut.matches("^([0-9]*\\d{7,8}[0-9k])$")) {
            JOptionPane.showMessageDialog(null, "ERROR, RUT mal escrito", "ERROR", JOptionPane.ERROR_MESSAGE);
            cont++;
        } else if (rut.length() > 9) {
            JOptionPane.showMessageDialog(null,
                    "ERROR, RUT maximo 9 digitos", "ERROR",
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

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel9 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        JT_rut = new javax.swing.JTextField();
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
        jLabel8 = new javax.swing.JLabel();
        JP_contraseña = new javax.swing.JPasswordField();
        jLabel10 = new javax.swing.JLabel();
        JP_confirmarcontraseña = new javax.swing.JPasswordField();
        jLabel11 = new javax.swing.JLabel();
        JB_cancel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("INGRESAR EMPLEADO");

        jLabel9.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel9.setText("NUEVO TRABAJADOR");

        jLabel1.setText("RUT (Sin puntos ni guión) :");

        JT_rut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JT_rutActionPerformed(evt);
            }
        });
        JT_rut.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                JT_rutKeyTyped(evt);
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

        JB_OK.setText("\uD83D\uDDAB");
        JB_OK.setToolTipText("Ingresar Trabajador");
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

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/racadauto/Images/ok.jpg"))); // NOI18N
        jLabel11.setText("jLabel3");
        jLabel11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        JB_cancel.setText("\u2B8C");
        JB_cancel.setToolTipText("Volver");
        JB_cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JB_cancelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(66, 66, 66)
                        .addComponent(jLabel9))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel6))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(JT_rut, javax.swing.GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE)
                            .addComponent(JT_nom, javax.swing.GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE)
                            .addComponent(cmb_cargo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(39, 39, 39)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(JP_contraseña, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(JT_paterno, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(42, 42, 42)
                                .addComponent(JT_fono, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(42, 42, 42)
                                .addComponent(JT_mail, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(JP_confirmarcontraseña, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel4)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(JT_materno, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(42, 42, 42)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(JB_cancel, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(JB_OK, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1106, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel11)
                    .addComponent(jLabel9))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(JT_rut, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)
                            .addComponent(JT_fono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7)
                            .addComponent(JT_mail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(17, 17, 17)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel3)
                                        .addComponent(JT_paterno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(JT_nom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(cmb_cargo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel8)
                                        .addComponent(JP_contraseña, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel4)
                                    .addComponent(JT_materno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(JP_confirmarcontraseña, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(JB_OK)
                        .addGap(30, 30, 30)
                        .addComponent(JB_cancel)
                        .addGap(17, 17, 17)))
                .addGap(23, 23, 23))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void JT_rutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JT_rutActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JT_rutActionPerformed

    private void JT_mailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JT_mailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JT_mailActionPerformed

    private void JB_OKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JB_OKActionPerformed
        // TODO add your handling code here:
        if (verificar() == 0) {
            String rut, nombre, paterno, materno, mail, cargo, contraseña;
            int dis;

            rut = JT_rut.getText().trim();
            String pass = new String(JP_contraseña.getPassword()).trim();
            contraseña = Hash.sha1(pass).trim();
            nombre = JT_nom.getText().toUpperCase().trim();
            paterno = JT_paterno.getText().toUpperCase().trim();
            materno = JT_materno.getText().toUpperCase().trim();
            mail = JT_mail.getText().trim();
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
                JOptionPane.showMessageDialog(null,
                    "Problemas con la base de datos, no se pudo otener informacion", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            }

            String sql = "INSERT INTO trabajador(rut_trabajador,contraseña,nombre,ape_paterno,ape_materno,fono,email,id_cargo) VALUES(" + rut + ",'" + contraseña + "','" + nombre + "','" + paterno + "','" + materno + "'," + fono + ",'" + mail + "'," + cargo2 + ")";
            try {
                sentencia.executeUpdate(sql);
                JOptionPane.showMessageDialog(null,
                    "TRABAJADOR ingresado correctamente", "INFO",
                    JOptionPane.INFORMATION_MESSAGE);
                dis = 1;
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null,
                    "Problemas con la base de datos, TRABAJADOR no ingresado", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
                dis = 0;
            }
            if (dis == 1) {
                clean();
            }
        } else {
            JOptionPane.showMessageDialog(null,
                    "TRABAJADOR no ingresado", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
        }

        limpiaTabla();
        setFilas();

    }//GEN-LAST:event_JB_OKActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked

    }//GEN-LAST:event_jTable1MouseClicked

    private void JT_rutKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JT_rutKeyTyped

    }//GEN-LAST:event_JT_rutKeyTyped

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

    private void JP_contraseñaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JP_contraseñaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JP_contraseñaActionPerformed

    private void JP_confirmarcontraseñaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JP_confirmarcontraseñaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JP_confirmarcontraseñaActionPerformed

    private void JB_cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JB_cancelActionPerformed
        this.dispose();
    }//GEN-LAST:event_JB_cancelActionPerformed

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
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Ingresar_trabajador().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton JB_OK;
    private javax.swing.JButton JB_cancel;
    private javax.swing.JPasswordField JP_confirmarcontraseña;
    private javax.swing.JPasswordField JP_contraseña;
    private javax.swing.JTextField JT_fono;
    private javax.swing.JTextField JT_mail;
    private javax.swing.JTextField JT_materno;
    private javax.swing.JTextField JT_nom;
    private javax.swing.JTextField JT_paterno;
    private javax.swing.JTextField JT_rut;
    private javax.swing.JComboBox<String> cmb_cargo;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
