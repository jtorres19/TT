package racadauto;

import com.mysql.jdbc.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.spi.DirStateFactory.Result;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class Consultar_vehiculo extends javax.swing.JFrame {
    private Statement sentencia;
    private Connection conexion;
    private String nomBD="racad";
    private String usuario="root";
    private String password="";
    private String msj;
    DefaultTableModel modeloTabla;
    private TableRowSorter trsfiltro;
    String filtro;

    public Consultar_vehiculo() {
        conectar();
        modeloTabla = new DefaultTableModel(null, getColumnas());
        setFilas();
        initComponents(); 
        
    }
    
    private String[] getColumnas() {

        String columna[] = new String[]{"Patente", "Año", "Kilometraje", "V.I.N.", "Color", "Nombre Dueño", "Apellido Paterno","Apellido Materno","Marca", "Modelo"};

        return columna;
    }

    private void setFilas() {
        try {
            sentencia = (com.mysql.jdbc.Statement) conexion.createStatement();
            ResultSet lista = sentencia.executeQuery("SELECT v.patente, v.año, v.kms, v.vin, v.color, c.nombre, c.ape_paterno, c.ape_materno, ma.nombre, mo.nombre "
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
    
    public void filtro() {

        filtro = txt_patente.getText().toUpperCase();
        int columna = 0;
        trsfiltro.setRowFilter(RowFilter.regexFilter(txt_patente.getText().toUpperCase(), columna));
    }
    
    public void filtro2() {

        filtro = txt_cliente.getText().toUpperCase();
        int columna = 5;
        trsfiltro.setRowFilter(RowFilter.regexFilter(txt_cliente.getText().toUpperCase(), columna));
    }
    
    public void filtro3() {

        filtro = txt_apep.getText().toUpperCase();
        int columna = 6;
        trsfiltro.setRowFilter(RowFilter.regexFilter(txt_apep.getText().toUpperCase(), columna));
    }
    
    public void filtro4() {

        filtro = txt_apem.getText().toUpperCase();
        int columna = 7;
        trsfiltro.setRowFilter(RowFilter.regexFilter(txt_apem.getText().toUpperCase(), columna));
    }
    
    public void filtro5() {

        filtro = txt_marc.getText().toUpperCase();
        int columna = 8;
        trsfiltro.setRowFilter(RowFilter.regexFilter(txt_marc.getText().toUpperCase(), columna));
    }
    
    public void filtro6() {

        filtro = txt_mod.getText().toUpperCase();
        int columna = 9;
        trsfiltro.setRowFilter(RowFilter.regexFilter(txt_mod.getText().toUpperCase(), columna));
    }
    
    
    void limpiaTabla() {
        do {
            modeloTabla.getRowCount();
            modeloTabla.removeRow(0);
        } while (modeloTabla.getRowCount() != 0);
    }
    
    public void conectar(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            String url="jdbc:mysql://localhost:3306/"+this.nomBD;
            this.conexion=(Connection)DriverManager.getConnection(url,this.usuario,this.password);
            this.sentencia=(Statement)this.conexion.createStatement();
        }
        catch(Exception e){
            msj="error al conectar";
        }
    }    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cmbCod = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        JB_cancel = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txt_patente = new javax.swing.JTextField();
        txt_cliente = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txt_marc = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txt_mod = new javax.swing.JTextField();
        txt_apep = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txt_apem = new javax.swing.JTextField();

        cmbCod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbCodActionPerformed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("ELIMINAR VEHICULO");
        setMinimumSize(new java.awt.Dimension(420, 210));
        setResizable(false);

        jLabel9.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel9.setText("RACAD AUTOMOTRIZ - CONSULTAR VEHICULO");

        JB_cancel.setText("Salir");
        JB_cancel.setPreferredSize(new java.awt.Dimension(100, 35));
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

        jLabel1.setText("Patente :");

        jLabel2.setText("Nombre Cliente :");

        txt_patente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_patenteActionPerformed(evt);
            }
        });
        txt_patente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_patenteKeyTyped(evt);
            }
        });

        txt_cliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_clienteKeyTyped(evt);
            }
        });

        jLabel3.setText("Marca :");

        jLabel4.setText("Apellido Paterno :");

        txt_marc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_marcKeyTyped(evt);
            }
        });

        jLabel5.setText("Modelo :");

        txt_mod.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_modKeyTyped(evt);
            }
        });

        txt_apep.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_apepKeyTyped(evt);
            }
        });

        jLabel6.setText("Apellido Materno :");

        txt_apem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_apemKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 426, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(101, 101, 101))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(txt_apep, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_apem)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_patente, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_marc, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_mod, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                        .addComponent(JB_cancel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel2)
                    .addComponent(txt_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_apep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(txt_apem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(JB_cancel, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(txt_patente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3)
                        .addComponent(txt_marc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5)
                        .addComponent(txt_mod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void cmbCodActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbCodActionPerformed
        //copia fallida
    }//GEN-LAST:event_cmbCodActionPerformed

    private void JB_cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JB_cancelActionPerformed
        this.dispose();
    }//GEN-LAST:event_JB_cancelActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked

    }//GEN-LAST:event_jTable1MouseClicked

    private void txt_patenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_patenteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_patenteActionPerformed

    private void txt_patenteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_patenteKeyTyped
       txt_patente.addKeyListener(new KeyAdapter() {
            public void keyReleased(final KeyEvent e) {
                filtro();
            }
        });
        trsfiltro = new TableRowSorter(modeloTabla);
        jTable1.setRowSorter(trsfiltro);
    
    }//GEN-LAST:event_txt_patenteKeyTyped

    private void txt_clienteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_clienteKeyTyped
        txt_cliente.addKeyListener(new KeyAdapter() {
            public void keyReleased(final KeyEvent e) {
                filtro2();
            }
        });
        trsfiltro = new TableRowSorter(modeloTabla);
        jTable1.setRowSorter(trsfiltro);
    
    }//GEN-LAST:event_txt_clienteKeyTyped

    private void txt_apepKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_apepKeyTyped
        txt_apep.addKeyListener(new KeyAdapter() {
            public void keyReleased(final KeyEvent e) {
                filtro3();
            }
        });
        trsfiltro = new TableRowSorter(modeloTabla);
        jTable1.setRowSorter(trsfiltro);
    }//GEN-LAST:event_txt_apepKeyTyped

    private void txt_apemKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_apemKeyTyped
        txt_apem.addKeyListener(new KeyAdapter() {
            public void keyReleased(final KeyEvent e) {
                filtro4();
            }
        });
        trsfiltro = new TableRowSorter(modeloTabla);
        jTable1.setRowSorter(trsfiltro);
    }//GEN-LAST:event_txt_apemKeyTyped

    private void txt_marcKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_marcKeyTyped
        txt_marc.addKeyListener(new KeyAdapter() {
            public void keyReleased(final KeyEvent e) {
                filtro5();
            }
        });
        trsfiltro = new TableRowSorter(modeloTabla);
        jTable1.setRowSorter(trsfiltro);
    }//GEN-LAST:event_txt_marcKeyTyped

    private void txt_modKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_modKeyTyped
        txt_mod.addKeyListener(new KeyAdapter() {
            public void keyReleased(final KeyEvent e) {
                filtro6();
            }
        });
        trsfiltro = new TableRowSorter(modeloTabla);
        jTable1.setRowSorter(trsfiltro);
    }//GEN-LAST:event_txt_modKeyTyped

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
            java.util.logging.Logger.getLogger(Eliminar_item.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Eliminar_item.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Eliminar_item.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Eliminar_item.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Consultar_vehiculo().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton JB_cancel;
    private javax.swing.JComboBox<String> cmbCod;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField txt_apem;
    private javax.swing.JTextField txt_apep;
    private javax.swing.JTextField txt_cliente;
    private javax.swing.JTextField txt_marc;
    private javax.swing.JTextField txt_mod;
    private javax.swing.JTextField txt_patente;
    // End of variables declaration//GEN-END:variables
}
