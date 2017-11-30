package racadauto;

import Conexion.Conexion;
import com.mysql.jdbc.*;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.RowFilter;
import javax.swing.table.TableRowSorter;

public class Consultar_modelo extends javax.swing.JFrame /*implements ActionListener*/ {

    private Statement sentencia;
    Conexion con = new Conexion();
    Connection cn = (Connection) con.getConnection();
    private String msj;
    DefaultTableModel modeloTabla;
    private TableRowSorter trsfiltro;
    String filtro;

    public Consultar_modelo() {

        modeloTabla = new DefaultTableModel(null, getColumnas());
        setFilas();
        initComponents();
    }

    private String[] getColumnas() {

        String columna[] = new String[]{"ID MODELO", "NOMBRE","MARCA","TIPO VEHICULO","TIPO COMBUSTIBLE","TIPO MOTOR"};

        return columna;
    }

    private void setFilas() {
        try {
            sentencia = (Statement) cn.createStatement();
            ResultSet lista = sentencia.executeQuery("SELECT o.id_modelo,o.nombre,a.nombre,v.nombre,c.nombre,m.nombre "
                    + "FROM marca a, modelo o,tipo_vehiculo v,tipo_combustible c,tipo_motor m "
                    + "WHERE a.id_marca = o.id_marca AND o.id_tipo_vehiculo = v.id_tipo_vehiculo AND o.id_tipo_combustible = c.id_tipo_combustible AND o.id_tipo_motor = m.id_tipo_motor");
            Object datos[] = new Object[9];
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

    public void clean() {
        try {
            modeloTabla.setRowCount(0);
        } catch (Exception e) {
            msj = "No se pudo vaciar tabla";
        }
    }

    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jRadioButtonMenuItem1 = new javax.swing.JRadioButtonMenuItem();
        jLabel9 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        JB_cancel = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        JT_motor = new javax.swing.JTextField();
        JT_nombre = new javax.swing.JTextField();
        JT_marca = new javax.swing.JTextField();
        JT_tipo = new javax.swing.JTextField();
        JT_combustible = new javax.swing.JTextField();

        jRadioButtonMenuItem1.setSelected(true);
        jRadioButtonMenuItem1.setText("jRadioButtonMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("CONSULTAR MODELO VEHICULO");
        setMinimumSize(new java.awt.Dimension(670, 450));

        jLabel9.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel9.setText("RACAD AUTOMOTRIZ - CONSULTAR MODELO VEHICULO");

        JB_cancel.setText("Volver");
        JB_cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JB_cancelActionPerformed(evt);
            }
        });

        jTable1.setAutoCreateRowSorter(true);
        jTable1.setModel(modeloTabla);
        jScrollPane1.setViewportView(jTable1);

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(JB_cancel))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 113, Short.MAX_VALUE)
                        .addComponent(JT_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(JT_marca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(JT_tipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(JT_combustible, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(JT_motor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JT_tipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JT_combustible, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JT_motor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JT_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JT_marca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 133, Short.MAX_VALUE)
                        .addComponent(JB_cancel)
                        .addContainerGap())))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void JB_cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JB_cancelActionPerformed
        this.dispose();
    }//GEN-LAST:event_JB_cancelActionPerformed

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
        JT_motor.addKeyListener(new KeyAdapter() {
            public void keyReleased(final KeyEvent e) {
                filtroMotor();
            }
        });
        trsfiltro = new TableRowSorter(modeloTabla);
        jTable1.setRowSorter(trsfiltro);
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
        JT_tipo.addKeyListener(new KeyAdapter() {
            public void keyReleased(final KeyEvent e) {
                filtroTipo();
            }
        });
        trsfiltro = new TableRowSorter(modeloTabla);
        jTable1.setRowSorter(trsfiltro);

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
        JT_combustible.addKeyListener(new KeyAdapter() {
            public void keyReleased(final KeyEvent e) {
                filtroCombustible();
            }
        });
        trsfiltro = new TableRowSorter(modeloTabla);
        jTable1.setRowSorter(trsfiltro);
    }//GEN-LAST:event_JT_combustibleKeyTyped

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
            java.util.logging.Logger.getLogger(Consultar_modelo.class
.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        

} catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Consultar_modelo.class
.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        

} catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Consultar_modelo.class
.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        

} catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Consultar_modelo.class
.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Consultar_modelo().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton JB_cancel;
    private javax.swing.JTextField JT_combustible;
    private javax.swing.JTextField JT_marca;
    private javax.swing.JTextField JT_motor;
    private javax.swing.JTextField JT_nombre;
    private javax.swing.JTextField JT_tipo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JRadioButtonMenuItem jRadioButtonMenuItem1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
