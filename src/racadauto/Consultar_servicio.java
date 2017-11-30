package racadauto;

import Conexion.Conexion;
import com.mysql.jdbc.*;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.RowFilter;
import javax.swing.table.TableRowSorter;

public class Consultar_servicio extends javax.swing.JFrame /*implements ActionListener*/ {

    private Statement sentencia;
    Conexion con = new Conexion();
    Connection cn = (Connection) con.getConnection();
    private String msj;
    DefaultTableModel modeloTabla;
    private TableRowSorter trsfiltro;
    String filtro;

    public Consultar_servicio() {

        modeloTabla = new DefaultTableModel(null, getColumnas());
        setFilas();
        initComponents();
    }

    private String[] getColumnas() {

        String columna[] = new String[]{"ID SERVICIO", "COMPONENTE","PRECIO","CATEGORIA"};

        return columna;
    }

    private void setFilas() {
        try {
            sentencia = (Statement) cn.createStatement();
            ResultSet lista = sentencia.executeQuery("SELECT s.id_servicio,s.componente,s.precio,c.nombre "
                    + "FROM servicio s,categoria c "
                    + "WHERE s.id_categoria = c.id_categoria");
            Object datos[] = new Object[9];
            while (lista.next()) {
                for (int i = 0; i < 4; i++) {
                    datos[i] = lista.getObject(i + 1);
                }
                modeloTabla.addRow(datos);
            }
        } catch (Exception e) {
            msj = "No se pudo llenar tabla";
        }
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

    public void filtroCategoria() {

        filtro = JT_categoria.getText().toUpperCase();
        int columna = 3;
        trsfiltro.setRowFilter(RowFilter.regexFilter(JT_categoria.getText().toUpperCase(), columna));
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
        JT_categoria = new javax.swing.JTextField();
        JT_componente = new javax.swing.JTextField();
        JT_precio = new javax.swing.JTextField();

        jRadioButtonMenuItem1.setSelected(true);
        jRadioButtonMenuItem1.setText("jRadioButtonMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("CONSULTAR SERVICIO");
        setMinimumSize(new java.awt.Dimension(670, 450));

        jLabel9.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel9.setText("RACAD AUTOMOTRIZ - CONSULTAR SERVICIO");

        JB_cancel.setText("Volver");
        JB_cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JB_cancelActionPerformed(evt);
            }
        });

        jTable1.setAutoCreateRowSorter(true);
        jTable1.setModel(modeloTabla);
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 650, Short.MAX_VALUE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(JB_cancel, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(JT_componente, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(JT_precio, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(JT_categoria, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JT_componente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JT_precio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JT_categoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(73, 73, 73)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 72, Short.MAX_VALUE)
                        .addComponent(JB_cancel)
                        .addContainerGap())))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void JB_cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JB_cancelActionPerformed
        this.dispose();
    }//GEN-LAST:event_JB_cancelActionPerformed

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
            java.util.logging.Logger.getLogger(Consultar_servicio.class
.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        

} catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Consultar_servicio.class
.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        

} catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Consultar_servicio.class
.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        

} catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Consultar_servicio.class
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
                new Consultar_servicio().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton JB_cancel;
    private javax.swing.JTextField JT_categoria;
    private javax.swing.JTextField JT_componente;
    private javax.swing.JTextField JT_precio;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JRadioButtonMenuItem jRadioButtonMenuItem1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
