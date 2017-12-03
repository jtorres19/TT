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

public class Orden_trabajo extends javax.swing.JFrame {
    private Statement sentencia;
    private Connection conexion;
    private String nomBD = "racad";
    private String usuario = "root";
    private String password = "";
    private String msj;
    DefaultTableModel modeloTabla1; 
    //DefaultTableModel modeloTabla2; 
    DefaultTableModel modeloTabla3; 
    DefaultTableModel modeloTabla4;

    public Orden_trabajo() {
        conectar();
        modeloTabla1 = new DefaultTableModel(null, getColumnas());
        //modeloTabla2 = new DefaultTableModel(null, getColumnas2());
        modeloTabla3 = new DefaultTableModel(null, getColumnas3());
        modeloTabla4 = new DefaultTableModel(null, getColumnas4());
        setFilas();
        setFilas4();
        initComponents();
        fechaActual();

    }
    
    private String[] getColumnas() {
        String columna[] = new String[]{"ID", "Folio", "Observaciones","Patente"};
        return columna;
    }
    
    private void setFilas() {
        try {
            sentencia = (com.mysql.jdbc.Statement) conexion.createStatement();
            ResultSet lista = sentencia.executeQuery("SELECT cod_solicitud, folio, observaciones, patente "
                    + "FROM solicitud_servicio WHERE estado_solicitud = 'PRESUPUEST' ");
            Object datos[] = new Object[4];
            while (lista.next()) {
                for (int i = 0; i < 4; i++) {
                    datos[i] = lista.getObject(i + 1);
                }
                modeloTabla1.addRow(datos);
            }
        } catch (Exception e) {
            msj = "No se pudo llenar tabla";
            LBL_estado.setText(msj);
        }
    }
    
    /*private String[] getColumnas2() {
        String columna[] = new String[]{"Nombre", "ID", "Solicitud", "ID", "Servicio", "Cantidad"};
        return columna;
    }
    
    private void setFilas2() {
        int cod = Integer.parseInt(jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString());
        int sol = Integer.parseInt(jTable3.getValueAt(jTable3.getSelectedRow(), 0).toString());
        try {
            sentencia = (com.mysql.jdbc.Statement) conexion.createStatement();
            ResultSet lista = sentencia.executeQuery("SELECT i.nombre, d.cod_solicitud, ds.observaciones, d.id_servicio, s.nombre, d.cantidad "
                    + "FROM detalle_insumo d INNER JOIN inventario i ON d.cod_item = i.cod_item "
                    + "LEFT JOIN detalle_solicitud ds ON d.cod_solicitud = ds.cod_solicitud "
                    + "LEFT JOIN servicio s ON d.id_servicio = s.id_servicio "
                    + "WHERE d.cod_solicitud =" + cod + " AND d.id_servicio =" + sol + "");
            Object datos[] = new Object[6];
            while (lista.next()) {
                for (int i = 0; i < 6; i++) {
                    datos[i] = lista.getObject(i + 1);
                }
                modeloTabla2.addRow(datos);
            }
        } catch (Exception e) {
            msj = "No se pudo llenar tabla";
            LBL_estado.setText(msj);
        }
    }*/
    
    private String[] getColumnas3() {
        String columna[] = new String[]{"ID", "Nombre", "Componente","Precio", "Categoría"};
        return columna;
    }
    
    private void setFilas3() {
        int cod = Integer.parseInt(jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString());
        int id=0;
        try {
                sentencia = (com.mysql.jdbc.Statement) conexion.createStatement();
                ResultSet sr = sentencia.executeQuery("SELECT id_servicio FROM detalle_solicitud WHERE cod_solicitud = " + cod + "");
                while (sr.next()) {
                    id = sr.getInt("id_servicio");
                }
            } catch (SQLException f) {
                msj = "Error con Codigo";
            }
        try {
            sentencia = (com.mysql.jdbc.Statement) conexion.createStatement();
            ResultSet lista = sentencia.executeQuery("SELECT s.id_servicio, s.nombre, s.componente, s.precio, c.nombre "
                    + "FROM servicio s INNER JOIN categoria c ON s.id_categoria = c.id_categoria "
                    + "WHERE NOT id_servicio = " + id + " ");
            Object datos[] = new Object[5];
            while (lista.next()) {
                for (int i = 0; i < 5; i++  ) {
                    datos[i] = lista.getObject(i + 1);
                }
                modeloTabla3.addRow(datos);
            }
        } catch (Exception e) {
            msj = "No se pudo llenar tabla";
            LBL_estado.setText(msj);
        }
    }
    
    private String[] getColumnas4() {
        String columna[] = new String[]{"R.U.T.", "Nombre Trabajador", "Apellido Paterno", "Apellido Materno"};
        return columna;
    }
    
    private void setFilas4() {
        try {
            sentencia = (com.mysql.jdbc.Statement) conexion.createStatement();
            ResultSet lista = sentencia.executeQuery("SELECT rut_trabajador, nombre, ape_paterno, ape_materno "
                    + "FROM trabajador ");
            Object datos[] = new Object[4];
            while (lista.next()) {
                for (int i = 0; i < 4; i++) {
                    datos[i] = lista.getObject(i + 1);
                }
                modeloTabla4.addRow(datos);
            }
        } catch (Exception e) {
            msj = "No se pudo llenar tabla";
            LBL_estado.setText(msj);
        }
    }
    
    public void clean() {
        jt_Obser.setText("");
        LBL_1.setText("");
        //LBL_2.setText("");
        LBL_3.setText("");
        LBL_4.setText("");
        
    }

    void limpiaTabla() {
        do {
            modeloTabla1.setRowCount(0);
            //modeloTabla2.setRowCount(0);
            modeloTabla3.setRowCount(0);
            modeloTabla4.setRowCount(0);
        } while ((modeloTabla1.getRowCount() != 0)/*&(modeloTabla2.getRowCount() != 0)*/&(modeloTabla3.getRowCount() != 0)&(modeloTabla4.getRowCount() != 0));
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

    public void fechaActual(){
        java.util.Date fechaActual = new java.util.Date();
        long fecha = fechaActual.getTime();
        java.sql.Date sqlDate = new java.sql.Date(fecha);
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        lblFecha.setText(formato.format(sqlDate).toUpperCase());
    }
    
    public int verificar(){
        int v=0;
        if (LBL_1.getText().equals("")){
            v++;
        }
        /*if (LBL_2.getText().equals("")){
            v++;
        }*/
        if (LBL_3.getText().equals("")){
            v++;
        }
        if (LBL_4.getText().equals("")){
            v++;
        }
        if (jt_Obser.getText().length() >= 51) {
            JOptionPane.showMessageDialog(null,
                "Error, observacion no puede exceder los 50 caracteres", "ERROR",
                JOptionPane.ERROR_MESSAGE);
            v++;
        }
        return v;
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel9 = new javax.swing.JLabel();
        JB_cancel = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jt_Obser = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();
        lblFecha = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        LBL_estado = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        LBL_1 = new javax.swing.JLabel();
        LBL_3 = new javax.swing.JLabel();
        LBL_4 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel9.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel9.setText("RACAD AUTOMOTRIZ - ORDEN DE TRABAJO");

        JB_cancel.setText("Salir");
        JB_cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JB_cancelActionPerformed(evt);
            }
        });

        jButton2.setText("Ingresar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jt_Obser.setColumns(20);
        jt_Obser.setRows(5);
        jScrollPane1.setViewportView(jt_Obser);

        jLabel3.setText("Observaciónes :");

        jLabel6.setText("Fecha Actual :");

        jLabel2.setText("Seleccione Presupuesto :");

        jTable3.setModel(modeloTabla3);
        jTable3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable3MouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(jTable3);

        jLabel4.setText("Seleccione Servicio :");

        jTable1.setModel(modeloTabla1);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(jTable1);

        jLabel5.setText("Seleccione Trabajador :");

        jTable4.setModel(modeloTabla4);
        jTable4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable4MouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(jTable4);

        jButton1.setText("jButton1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(LBL_1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 405, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton1)
                                .addGap(45, 45, 45))))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(LBL_3, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 405, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 405, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(LBL_4, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(34, 34, 34)
                                .addComponent(LBL_estado, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(149, 149, 149)
                                .addComponent(JB_cancel, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6)
                        .addComponent(lblFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(LBL_1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(LBL_3, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(LBL_4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(1, 1, 1)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(LBL_estado, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JB_cancel)
                    .addComponent(jButton2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void JB_cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JB_cancelActionPerformed
        this.dispose();
    }//GEN-LAST:event_JB_cancelActionPerformed

    private void jTable3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable3MouseClicked
        LBL_3.setText("OK");
        //LBL_1.setText("");
        /*if (LBL_2.getText().equals("OK")){
            do {
            modeloTabla2.setRowCount(0);
            }while (modeloTabla2.getRowCount() != 0);
            setFilas2();
        }*/
    }//GEN-LAST:event_jTable3MouseClicked

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        LBL_1.setText("OK");
        LBL_3.setText("");
        do {
        modeloTabla3.setRowCount(0);
        }while (modeloTabla3.getRowCount() != 0);
        setFilas3();
    }//GEN-LAST:event_jTable1MouseClicked

    private void jTable4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable4MouseClicked
        LBL_4.setText("OK");
    }//GEN-LAST:event_jTable4MouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if(verificar()==0){
            int dis=0;
            String obs = jt_Obser.getText();
            String fo = lblFecha.getText();
            int rut = Integer.parseInt(jTable4.getValueAt(jTable4.getSelectedRow(), 0).toString());
            int id = Integer.parseInt(jTable3.getValueAt(jTable3.getSelectedRow(), 0).toString());
            int pr = Integer.parseInt(jTable3.getValueAt(jTable3.getSelectedRow(), 3).toString());
            int sol = Integer.parseInt(jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString());
            String fp = "";
            try {
                sentencia = (com.mysql.jdbc.Statement) conexion.createStatement();
                ResultSet sr = sentencia.executeQuery("SELECT fecha_presupuesto FROM solicitud_servicio WHERE cod_solicitud = " + sol + "");
                while (sr.next()) {
                    fp = sr.getString("fecha_presupuesto");
                }
            } catch (SQLException f) {
                msj = "Error con Codigo";
            }
            
            //me puede servir para 2_2_2
            /*String nom = jTable2.getValueAt(jTable2.getSelectedRow(), 0).toString();
                int cod=0;
                int vv=0;
                try {
                    sentencia = (com.mysql.jdbc.Statement) conexion.createStatement();
                    ResultSet sr = sentencia.executeQuery("SELECT * FROM inventario WHERE nombre = '" + nom + "'");
                   while (sr.next()) {
                        cod = sr.getInt("cod_item");
                        vv = sr.getInt("valor_venta");
                    }
                } catch (SQLException f) {
                    msj = "Error con Codigo";
                }
            int can = Integer.parseInt(jTable2.getValueAt(jTable2.getSelectedRow(), 5).toString());
            */
            String sql = "INSERT INTO detalle_solicitud(cod_solicitud, cod_item, id_servicio, precio, valor_venta, cantidad, observaciones, fecha_presupuesto, fecha_orden, fecha_entrega, estado_seguimiento, rut_trabajador) "
                    + "VALUES(" + sol + ",0," + id + "," + pr + ",0,0,'" + obs + "','" + fp + "','" + fo + "','2017-10-05','PROCESO'," + rut + ")";
            try {
                sentencia.executeUpdate(sql);
                msj = "Datos Guardados";
                LBL_estado.setText(msj);
                dis++;
            } catch (SQLException e) {
                msj = "Item no Ingresado en bd";
                LBL_estado.setText(msj);
            }
            String sql2="UPDATE solicitud_servicio SET fecha_orden = " + fo + " AND estado_solicitud = 'OT' WHERE cod_solicitud = " + sol + " ";
                try{
                    sentencia.executeUpdate(sql2);
                    LBL_estado.setText("Actualizado insumos");
                    dis++;
                }
                catch(SQLException de){
                    msj="Error al Actualizar"; 
                    LBL_estado.setText(msj);
                }
            if (dis > 0) {
                limpiaTabla();
                setFilas();
                setFilas4();
                clean();
            }    
        } else {
            msj = "Item no Ingresado";
            LBL_estado.setText(msj);
            JOptionPane.showMessageDialog(null,
                "Error, Asegurese de ingresar todo bien!", "ERROR",
                JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Ingresar_insumo wuz=new Ingresar_insumo();
        wuz.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(Orden_trabajo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Orden_trabajo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Orden_trabajo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Orden_trabajo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Orden_trabajo().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton JB_cancel;
    private javax.swing.JLabel LBL_1;
    private javax.swing.JLabel LBL_3;
    private javax.swing.JLabel LBL_4;
    private javax.swing.JLabel LBL_estado;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable3;
    private javax.swing.JTable jTable4;
    private javax.swing.JTextArea jt_Obser;
    private javax.swing.JLabel lblFecha;
    // End of variables declaration//GEN-END:variables
}
