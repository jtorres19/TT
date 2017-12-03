package racadauto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Presupuestar_trabajo extends javax.swing.JFrame {

    private Statement sentencia;
    private Connection conexion;
    private String nomBD = "racad";
    private String usuario = "root";
    private String password = "";
    private String msj;
    DefaultTableModel modeloTabla; 
    DefaultTableModel modeloTabla2; 

    
    public Presupuestar_trabajo() {
        conectar();
        modeloTabla = new DefaultTableModel(null, getColumnas());
        modeloTabla2 = new DefaultTableModel(null, getColumnas2());
        setFilas();
        setFilas2();
        initComponents();
        fechaActual();
        llenarLabels();
    }

    private String[] getColumnas() {
        String columna[] = new String[]{"Patente", "KMS", "Color","Nombre Cliente", "Apellido Paterno", "Apellido Materno", "Marca","Modelo"};
        return columna;
    }
    
    private String[] getColumnas2() {
        String columna[] = new String[]{"R.U.T. Trabajador", "Nombre Trabajador", "Apellido Paterno", "Apellido Materno"};
        return columna;
    }

    private void setFilas() {
        try {
            sentencia = (com.mysql.jdbc.Statement) conexion.createStatement();
            ResultSet lista = sentencia.executeQuery("SELECT v.patente, v.kms, v.color, c.nombre, c.ape_paterno, c.ape_materno, ma.nombre, mo.nombre "
                    + "FROM vehiculo v INNER JOIN cliente c ON v.rut_cliente = c.rut_cliente "
                    + "LEFT JOIN marca ma ON v.id_marca = ma.id_marca "
                    + "LEFT JOIN modelo mo ON v.id_modelo = mo.id_modelo ");
            Object datos[] = new Object[8];
            while (lista.next()) {
                for (int i = 0; i < 8; i++) {
                    datos[i] = lista.getObject(i + 1);
                }
                modeloTabla.addRow(datos);
            }
        } catch (Exception e) {
            msj = "No se pudo llenar tabla";
            LBL_estado.setText(msj);
        }
    }
    
    private void setFilas2() {
        try {
            sentencia = (com.mysql.jdbc.Statement) conexion.createStatement();
            ResultSet lista = sentencia.executeQuery("SELECT rut_trabajador, nombre, ape_paterno, ape_materno "
                    + "FROM trabajador ");
            Object datos[] = new Object[4];
            while (lista.next()) {
                for (int i = 0; i < 4; i++) {
                    datos[i] = lista.getObject(i + 1);
                }
                modeloTabla2.addRow(datos);
            }
        } catch (Exception e) {
            msj = "No se pudo llenar tabla";
            LBL_estado.setText(msj);
        }
    }
    
    void limpiaTabla() {
        do {
            modeloTabla.setRowCount(0);
            modeloTabla2.setRowCount(0);
        } while ((modeloTabla.getRowCount() != 0)&(modeloTabla2.getRowCount() != 0));
    }
    
    
    public void conectar() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/" + this.nomBD;
            this.conexion = (Connection) DriverManager.getConnection(url, this.usuario, this.password);
            this.sentencia = (Statement) this.conexion.createStatement();
        } catch (Exception e) {
            msj = "ERROR AL CONECTAR";
        }
    }

    public void clean() {
        jl_Cod.setText("");
        jt_Obser.setText("");
        //jt_Sub.setText("");
        jt_Total.setText("");
        lblFecha.setText("");
        LBL_estado.setText("");
        LBL_estado1.setText("");
    }
    
    public void fechaActual(){
        java.util.Date fechaActual = new java.util.Date();
        long fecha = fechaActual.getTime();
        java.sql.Date sqlDate = new java.sql.Date(fecha);
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        lblFecha.setText(formato.format(sqlDate).toUpperCase());
    }
    
    public void llenarLabels(){
        int cod=0;
        try {
            sentencia=(com.mysql.jdbc.Statement)conexion.createStatement();
            ResultSet rs = sentencia.executeQuery("SELECT MAX(cod_solicitud) as cod_solicitud FROM solicitud_servicio");
            while (rs.next()) {
            cod = rs.getInt("cod_solicitud");
            }
            cod++;
        } catch (SQLException f) {
            msj = "Error con Codigo";
        }
        jl_Cod.setText("" + cod + "");
    }

    public int verificar() {
        int cont = 0;
        String obser = jt_Obser.getText();
        //String sub = jt_Sub.getText();
        String total = jt_Total.getText();
        if ((jt_Obser.getText().equals(""))
                //|| (jt_Sub.getText().equals(""))
                || (jt_Total.getText().equals(""))) {
            JOptionPane.showMessageDialog(null,
                    "Error, dejó una casilla vacía", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            cont += 1;
        }/*
        if (!sub.matches("[-+]?\\d*\\.?\\d+")) {
            JOptionPane.showMessageDialog(null, "Error, subtotal tiene que ser númerico", "ERROR", JOptionPane.ERROR_MESSAGE);
            cont++;
        } else if (jt_Sub.getText().length() >= 8) {
            JOptionPane.showMessageDialog(null,
                    "Error, subtotal maximo 7 digitos y no negativo!", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            cont++;
        }*/
        if (jt_Total.getText().length() >= 8) {
            JOptionPane.showMessageDialog(null,
                    "Error, total maximo 7 digitos y no negativo!", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            cont++;
        } else if (!total.matches("[-+]?\\d*\\.?\\d+")) {
            JOptionPane.showMessageDialog(null,
                    "Error, total tiene que ser numerico!", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            cont++;
        }
        if (jt_Obser.getText().length() >= 51) {
            JOptionPane.showMessageDialog(null,
                    "Error, observacion no puede exceder los 50 caracteres", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            cont++;
        }
        if (LBL_estado.getText().equals("")){
            JOptionPane.showMessageDialog(null,
                    "Error, Seleccione vehiculo!", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            cont++;
        }
        if (LBL_estado1.getText().equals("")){
            JOptionPane.showMessageDialog(null,
                    "Error, Seleccione trabajador!", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            cont++;
        }
        /*if ((Integer.parseInt(jt_Sub.getText())) > (Integer.parseInt(jt_Total.getText()))) {
            JOptionPane.showMessageDialog(null,
                    "Error, Sub-total no puede ser mayor al Total", "error message",
                    JOptionPane.ERROR_MESSAGE);
            cont++;
        }*/
        return cont;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel9 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jt_Total = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        JB_cancel = new javax.swing.JButton();
        jl_Cod = new javax.swing.JLabel();
        lblFecha = new javax.swing.JLabel();
        LBL_estado = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jt_Obser = new javax.swing.JTextArea();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        LBL_estado1 = new javax.swing.JLabel();
        LBL_estado3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel9.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel9.setText("RACAD AUTOMOTRIZ - PRESUPUESTAR TRABAJO");

        jLabel1.setText("Codigo Solicitud :");

        jLabel3.setText("Observaciónes :");

        jLabel5.setText("Total a gastar :");

        jLabel6.setText("Fecha Actual :");

        jLabel7.setText("Patente :");

        jLabel8.setText("Nombre Trabajador :");

        jButton2.setText("OK");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        JB_cancel.setText("Cancelar");
        JB_cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JB_cancelActionPerformed(evt);
            }
        });

        jt_Obser.setColumns(20);
        jt_Obser.setRows(5);
        jScrollPane1.setViewportView(jt_Obser);

        jTable1.setModel(modeloTabla);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(jTable1);

        jTable2.setModel(modeloTabla2);
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(jTable2);

        jButton1.setText("Generar Informe");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel8)
                    .addComponent(jLabel7)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(JB_cancel, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jt_Total, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(LBL_estado3, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jl_Cod, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(LBL_estado, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(LBL_estado1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 405, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 405, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(17, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jl_Cod, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5)
                        .addComponent(jt_Total, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(LBL_estado3, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(LBL_estado, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton2)
                            .addComponent(JB_cancel)
                            .addComponent(jButton1)))
                    .addComponent(LBL_estado1, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void JB_cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JB_cancelActionPerformed
        this.dispose();
    }//GEN-LAST:event_JB_cancelActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if (verificar() == 0) {
            String obser;
            String pat = jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString();
            int rut = Integer.parseInt(jTable2.getValueAt(jTable2.getSelectedRow(), 0).toString());
            int dis;
            int fol = 0;
            String fech = lblFecha.getText();
            obser = jt_Obser.getText();
            int cod = 0;
            //int sub = Integer.parseInt(jt_Sub.getText());
            int total = Integer.parseInt(jt_Total.getText());
            int mod=0;
            int mar=0;
            String est = "PRESUPUEST";
            try {
                sentencia=(com.mysql.jdbc.Statement)conexion.createStatement();
                ResultSet rs = sentencia.executeQuery("SELECT MAX(cod_solicitud) as cod_solicitud FROM solicitud_servicio");
                while (rs.next()) {
                    cod = rs.getInt("cod_solicitud");
                }
                cod++;
            } catch (SQLException f) {
                msj = "Error con Codigo";
            }
            
            try {
                sentencia=(com.mysql.jdbc.Statement)conexion.createStatement();
                ResultSet rs = sentencia.executeQuery("SELECT MAX(folio) as folio FROM solicitud_servicio");
                while (rs.next()) {
                    fol = rs.getInt("folio");
                }
                fol++;
            } catch (SQLException f) {
                msj = "Error con Codigo";
            }

            //la fecha orden y entrega so "place-holders" que se quedarán para que funcione el programa
            //la fecha presupuesto hay que hacer que sea la actual si
            //sub-total se dejó en cero, puesto a que esto es un presupuesto
            String sql = "INSERT INTO solicitud_servicio(cod_solicitud,folio,observaciones,subtotal,total,fecha_presupuesto,fecha_orden,fecha_entrega,estado_solicitud,patente,rut_trabajador) "
                         + "VALUES(" + cod + "," + fol + ",'" + obser + "',0," + total + ",'" + fech + "','2017-10-05','2017-10-05','" + est + "','" + pat + "'," + rut + ")";
            
            try {
                sentencia.executeUpdate(sql);
                msj = "Datos Guardados";
                LBL_estado3.setText(msj);
                dis = 1;
            } catch (SQLException e) {
                msj = "Item no Ingresado en bd";
                LBL_estado3.setText(msj);
                dis = 0;
            }
            if (dis == 1) {
                clean();
                limpiaTabla();
                setFilas();
                setFilas2();
            }
        } else {
            msj = "Item no Ingresado";
            LBL_estado3.setText(msj);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        String nom = jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString();
        LBL_estado.setText("Vehiculo : " + nom +"");

    }//GEN-LAST:event_jTable1MouseClicked

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
        String nom = jTable1.getValueAt(jTable2.getSelectedRow(), 0).toString();
        LBL_estado1.setText("Trabajador : " + nom +"");
    }//GEN-LAST:event_jTable2MouseClicked

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
            java.util.logging.Logger.getLogger(Presupuestar_trabajo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Presupuestar_trabajo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Presupuestar_trabajo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Presupuestar_trabajo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Presupuestar_trabajo().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton JB_cancel;
    private javax.swing.JLabel LBL_estado;
    private javax.swing.JLabel LBL_estado1;
    private javax.swing.JLabel LBL_estado3;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JLabel jl_Cod;
    private javax.swing.JTextArea jt_Obser;
    private javax.swing.JTextField jt_Total;
    private javax.swing.JLabel lblFecha;
    // End of variables declaration//GEN-END:variables
}
