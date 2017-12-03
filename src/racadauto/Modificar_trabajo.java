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

public class Modificar_trabajo extends javax.swing.JFrame {
    private Statement sentencia;
    private Connection conexion;
    private String nomBD = "racad";
    private String usuario = "root";
    private String password = "";
    private String msj;
    DefaultTableModel modeloTabla1; 
    DefaultTableModel modeloTabla3; 

    public Modificar_trabajo() {
        conectar();
        modeloTabla1 = new DefaultTableModel(null, getColumnas());
        modeloTabla3 = new DefaultTableModel(null, getColumnas3());
        setFilas();
        initComponents();
    }
    
    private String[] getColumnas() {
        String columna[] = new String[]{"ID", "Folio", "Observaciones","Patente", "Estado"};
        return columna;
    }
    
    private void setFilas() {
        try {
            sentencia = (com.mysql.jdbc.Statement) conexion.createStatement();
            ResultSet lista = sentencia.executeQuery("SELECT cod_solicitud, folio, observaciones, patente, estado_solicitud "
                    + "FROM solicitud_servicio WHERE estado_solicitud <> 'FACTURA' ");
            Object datos[] = new Object[5];
            while (lista.next()) {
                for (int i = 0; i < 5; i++) {
                    datos[i] = lista.getObject(i + 1);
                }
                modeloTabla1.addRow(datos);
            }
        } catch (Exception e) {
            msj = "No se pudo llenar tabla";
            LBL_estado.setText(msj);
        }
    }
    
    private String[] getColumnas3() {
        String columna[] = new String[]{"ID", "Nombre", "Componente","Precio", "Categoría"};
        return columna;
    }
    
    //puede que tenga un error, no es muy convencional el metodo, pero en mis testeos funciona
    private void setFilas3() {
        int cod = Integer.parseInt(jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString());
        int id=0;
        try {
                sentencia = (com.mysql.jdbc.Statement) conexion.createStatement();
                ResultSet sr = sentencia.executeQuery("SELECT id_servicio FROM detalle_solicitud WHERE cod_solicitud = " + cod + "");
                while (sr.next()) {
                    id = sr.getInt("id_servicio");
                    try {
                        sentencia = (com.mysql.jdbc.Statement) conexion.createStatement();
                        ResultSet lista = sentencia.executeQuery("SELECT s.id_servicio, s.nombre, s.componente, s.precio, c.nombre "
                            + "FROM servicio s INNER JOIN categoria c ON s.id_categoria = c.id_categoria "
                            + "WHERE id_servicio = " + id + " ");
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
            } catch (SQLException f) {
                msj = "Error con Codigo";
            }
        
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
    
    void limpiaTabla() {
        do {
            modeloTabla1.setRowCount(0);
            modeloTabla3.setRowCount(0);
        } while ((modeloTabla1.getRowCount() != 0)&(modeloTabla3.getRowCount() != 0));
    }

    public int verificar(){
        int v=0;
        if (LBL_1.getText().equals("")){
            v++;
        }
        if (LBL_3.getText().equals("")){
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

    public int verificarv(){
        int vv=0;
        if (LBL_1.getText().equals("")){
            vv++;
        }
        return vv;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        jb_es = new javax.swing.JButton();
        JB_cancel1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jb_del = new javax.swing.JButton();
        jb_ter = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jb_mod1 = new javax.swing.JButton();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        LBL_estado = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jt_Obser = new javax.swing.JTextArea();
        LBL_3 = new javax.swing.JLabel();
        LBL_1 = new javax.swing.JLabel();
        LBL_estdel = new javax.swing.JLabel();

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Nombre Item", "Cantidad", "Precio", "Total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable2.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(jTable2);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel9.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel9.setText("RACAD AUTOMOTRIZ - MODIFICAR TRABAJOS");

        jb_es.setText("Eliminar Servicios de OT");
        jb_es.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jb_esActionPerformed(evt);
            }
        });

        JB_cancel1.setText("Salir");
        JB_cancel1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JB_cancel1ActionPerformed(evt);
            }
        });

        jLabel3.setText("Observación :");

        jb_del.setText("Eliminar OT/Presupuesto");
        jb_del.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jb_delActionPerformed(evt);
            }
        });

        jb_ter.setText("Marcar como Terminado");
        jb_ter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jb_terActionPerformed(evt);
            }
        });

        jTable1.setModel(modeloTabla1);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(jTable1);

        jLabel4.setText("Seleccionar OT/ Presupuesto :");

        jb_mod1.setText("Confirmar Modificación de Observación del Detalle");
        jb_mod1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jb_mod1ActionPerformed(evt);
            }
        });

        jTable3.setModel(modeloTabla3);
        jTable3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable3MouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(jTable3);

        jLabel5.setText("Seleccione Servicio :");

        jt_Obser.setColumns(20);
        jt_Obser.setRows(5);
        jScrollPane1.setViewportView(jt_Obser);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 405, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(LBL_3, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(LBL_1, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel3))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 403, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jb_del)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(LBL_estdel, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(LBL_estado, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(JB_cancel1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jb_es)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jb_ter)
                        .addGap(34, 34, 34))))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 405, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(72, 72, 72)
                        .addComponent(jb_mod1)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(LBL_1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(9, 9, 9)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jb_del)
                            .addComponent(LBL_estdel, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(144, 144, 144))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(LBL_3, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jb_es)
                            .addComponent(jb_ter))))
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jb_mod1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(LBL_estado, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JB_cancel1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void JB_cancel1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JB_cancel1ActionPerformed
        this.dispose();
    }//GEN-LAST:event_JB_cancel1ActionPerformed

    private void jb_esActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jb_esActionPerformed
        if(verificar()==0){
            int cod = Integer.parseInt(jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString());
            int ids = Integer.parseInt(jTable3.getValueAt(jTable3.getSelectedRow(), 0).toString());
            int i =JOptionPane.showConfirmDialog(this,
                    "¿Realmente Desea Eliminar OT/Presupuesto de ID : " + cod + "?","Confirmar Eliminación",
                    JOptionPane.YES_NO_OPTION);
            if (i== 0) {
                String sql2 = "DELETE FROM detalle_solicitud WHERE cod_solicitud =" + cod + " AND id_servicio = " + ids +" ";
                try {
                    sentencia.executeUpdate(sql2);
                    LBL_estdel.setText("Borrado de Detalle Solicitud");
                } catch (SQLException fe) {
                    msj = "Error al borrar de detalle solicitud";
                    LBL_estdel.setText(msj);
                }
                int cont=0;
                try {
                    sentencia=(com.mysql.jdbc.Statement)conexion.createStatement();
                    ResultSet rs = sentencia.executeQuery("SELECT cod_solicitud as cod_solicitud FROM detalle_insumo");
                    while (rs.next()) {
                        int cod2 = rs.getInt("cod_solicitud");
                        if (cod2 == cod){
                            cont++;
                        }
                    }
                } catch (SQLException f) {
                    msj = "Error con Codigo";
                }
                if (cont>0){
                    try {
                        sentencia=(com.mysql.jdbc.Statement)conexion.createStatement();
                        ResultSet rs = sentencia.executeQuery("SELECT * FROM detalle_insumo WHERE cod_solicitud =" + cod +" AND id_servicio = " + ids + " ");
                        while (rs.next()) {
                            int cod2 = rs.getInt("cod_item");
                            int can = rs.getInt("cantidad");
                            String sql0="UPDATE inventario SET stock_actual = stock_actual + " + can + " WHERE cod_item = " + cod2 + "";
                            try{
                                sentencia.executeUpdate(sql0);
                                LBL_estdel.setText("Cantidad aumentada stock");
                            }
                            catch(SQLException ff){
                                msj="Error al Disminuir";
                                LBL_estdel.setText(msj);
                            }
                        }
                    } catch (SQLException f) {
                        msj = "Error con Codigo";
                    }
                    String sql = "DELETE FROM detalle_insumo WHERE cod_solicitud =" + cod + " AND id_servicio =" + ids + "";
                    try {
                        sentencia.executeUpdate(sql);
                        LBL_estdel.setText("Borrado de Detalle Insumo");
                    } catch (SQLException ee) {
                        msj = "Error al borrar de detalle insumo";
                        LBL_estdel.setText(msj);
                    }
                }
            }
        }else{
            JOptionPane.showMessageDialog(null,
                "Error, Seleccione algo para borrar!", "ERROR",
                JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jb_esActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        LBL_1.setText("OK");
        LBL_3.setText("");
        do {
            modeloTabla3.setRowCount(0);
        }while (modeloTabla3.getRowCount() != 0);
        int cod = Integer.parseInt(jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString());
        setFilas3();
        String sql = "SELECT observaciones FROM detalle_solicitud WHERE cod_solicitud ";
    }//GEN-LAST:event_jTable1MouseClicked

    private void jTable3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable3MouseClicked
        LBL_3.setText("OK");
    }//GEN-LAST:event_jTable3MouseClicked

    private void jb_delActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jb_delActionPerformed
        if(verificarv()==0){
            int cod = Integer.parseInt(jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString());
            String est = jTable1.getValueAt(jTable1.getSelectedRow(), 4).toString();
            int i =JOptionPane.showConfirmDialog(this,
                    "¿Realmente Desea Eliminar OT/Presupuesto de ID : " + cod + "?","Confirmar Eliminación",
                    JOptionPane.YES_NO_OPTION);
            if (i== 0) {
                if(est.equals("PRESUPUEST")){
                    String sql2 = "DELETE FROM solicitud_servicio WHERE cod_solicitud =" + cod + "";
                    try {
                        sentencia.executeUpdate(sql2);
                        LBL_estdel.setText("Borrado de Detalle Solicitud");
                    } catch (SQLException fe) {
                        msj = "Error al borrar de detalle solicitud";
                        LBL_estdel.setText(msj);
                    }
                    LBL_3.setText("");
                    LBL_1.setText("");
                    jt_Obser.setText("");
                    limpiaTabla();
                    setFilas();
                }else{
                    int cont=0;
                    try {
                        sentencia=(com.mysql.jdbc.Statement)conexion.createStatement();
                        ResultSet rs = sentencia.executeQuery("SELECT cod_solicitud as cod_solicitud FROM detalle_insumo");
                        while (rs.next()) {
                            int cod2 = rs.getInt("cod_solicitud");
                            if (cod2 == cod){
                                cont++;
                            }
                        }
                    } catch (SQLException f) {
                        msj = "Error con Codigo";
                    }
                    if (cont>0){
                        try {
                            sentencia=(com.mysql.jdbc.Statement)conexion.createStatement();
                            ResultSet rs = sentencia.executeQuery("SELECT * FROM detalle_insumo WHERE cod_solicitud =" + cod +"");
                            while (rs.next()) {
                                int cod2 = rs.getInt("cod_item");
                                int can = rs.getInt("cantidad");
                                String sql0="UPDATE inventario SET stock_actual = stock_actual + " + can + " WHERE cod_item = " + cod2 + "";
                                try{
                                    sentencia.executeUpdate(sql0);
                                    LBL_estdel.setText("Cantidad aumentada stock");
                                }
                                catch(SQLException ff){
                                    msj="Error al Disminuir";
                                    LBL_estdel.setText(msj);
                                }
                            }
                        } catch (SQLException f) {
                            msj = "Error con Codigo";
                        }
                        String sql = "DELETE FROM detalle_insumo WHERE cod_solicitud =" + cod + "";
                        try {
                            sentencia.executeUpdate(sql);
                            LBL_estdel.setText("Borrado de Detalle Insumo");
                        } catch (SQLException ee) {
                            msj = "Error al borrar de detalle insumo";
                            LBL_estdel.setText(msj);
                        }
                    }
                    String sql2 = "DELETE FROM detalle_solicitud WHERE cod_solicitud =" + cod + "";
                    try {
                        sentencia.executeUpdate(sql2);
                        LBL_estdel.setText("Borrado de Detalle Solicitud");
                    } catch (SQLException fe) {
                        msj = "Error al borrar de detalle solicitud";
                        LBL_estdel.setText(msj);
                    }
                    String sql3 = "DELETE FROM solicitud_servicio WHERE cod_solicitud =" + cod + "";
                    try {
                        sentencia.executeUpdate(sql3);
                        LBL_estdel.setText("Borrado de Solicitud Servicio");
                    } catch (SQLException eg) {
                        msj = "Error al borrar de solicitud servicio";
                        LBL_estdel.setText(msj);
                    }
                    LBL_3.setText("");
                    LBL_1.setText("");
                    jt_Obser.setText("");
                    limpiaTabla();
                    setFilas();
                }
            }
        }else{
            JOptionPane.showMessageDialog(null,
                "Error, Seleccione algo para borrar!", "ERROR",
                JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jb_delActionPerformed

    private void jb_mod1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jb_mod1ActionPerformed
        if(verificar()==0){
            String obs = jt_Obser.getText();
            int cod = Integer.parseInt(jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString());    
            int ids = Integer.parseInt(jTable3.getValueAt(jTable3.getSelectedRow(), 0).toString());
            String sql="UPDATE detalle_solicitud SET observaciones = '" + obs + "' WHERE cod_item = " + cod + " AND id_servicio =" + ids + "";
            try{
                sentencia.executeUpdate(sql);
                LBL_estdel.setText("Observación cambiada");
            }
            catch(SQLException ff){
                msj="Error al cambiar";
                LBL_estdel.setText(msj);
            }
        }
    }//GEN-LAST:event_jb_mod1ActionPerformed

    private void jb_terActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jb_terActionPerformed
        if(verificar()==0){
            int cod = Integer.parseInt(jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString());
            int ids = Integer.parseInt(jTable3.getValueAt(jTable3.getSelectedRow(), 0).toString());
            String sql="UPDATE detalle_solicitud SET estado_seguimiento = 'TERMINO' WHERE cod_item = " + cod + " AND id_servicio =" + ids + "";
            try{
                sentencia.executeUpdate(sql);
                LBL_estdel.setText("Estado cambiado a Terminado");
            }
            catch(SQLException ff){
                msj="Error al cambiar";
                LBL_estdel.setText(msj);
            }
        }else{
            JOptionPane.showMessageDialog(null,
                "Error, Seleccione algo para cambiar el estado!", "ERROR",
                JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jb_terActionPerformed

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
            java.util.logging.Logger.getLogger(Modificar_trabajo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Modificar_trabajo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Modificar_trabajo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Modificar_trabajo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Modificar_trabajo().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton JB_cancel1;
    private javax.swing.JLabel LBL_1;
    private javax.swing.JLabel LBL_3;
    private javax.swing.JLabel LBL_estado;
    private javax.swing.JLabel LBL_estdel;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JButton jb_del;
    private javax.swing.JButton jb_es;
    private javax.swing.JButton jb_mod1;
    private javax.swing.JButton jb_ter;
    private javax.swing.JTextArea jt_Obser;
    // End of variables declaration//GEN-END:variables
}
