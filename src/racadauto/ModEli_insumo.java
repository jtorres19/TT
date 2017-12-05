package racadauto;

import com.mysql.jdbc.*;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ModEli_insumo extends javax.swing.JFrame {

    private Statement sentencia;
    private Connection conexion;
    private String nomBD = "racad";
    private String usuario = "root";
    private String password = "";
    private String msj;
    DefaultTableModel modeloTabla;

    public ModEli_insumo() {
        conectar();
        modeloTabla = new DefaultTableModel(null, getColumnas());
        setFilas();
        initComponents();
    }

    private String[] getColumnas() {
        String columna[] = new String[]{"Nombre", "ID", "Solicitud", "ID", "Servicio", "Cantidad"};
        return columna;
    }
    
    private void setFilas() {
        try {
            sentencia = (com.mysql.jdbc.Statement) conexion.createStatement();
            ResultSet lista = sentencia.executeQuery("SELECT i.nombre, d.cod_solicitud, ds.observaciones, d.id_servicio, s.nombre, d.cantidad "
                    + "FROM detalle_insumo d INNER JOIN inventario i ON d.cod_item = i.cod_item "
                    + "LEFT JOIN detalle_solicitud ds ON d.cod_solicitud = ds.cod_solicitud "
                    + "LEFT JOIN servicio s ON d.id_servicio = s.id_servicio ");
            Object datos[] = new Object[6];
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
    
    void limpiaTexto(){
        jt_can.setText("");
        LBL_estado1.setText("");
    }
    
    void limpiaTabla() {
        do {
            modeloTabla.setRowCount(0);
        } while(modeloTabla.getRowCount() != 0);
    }
    
    public void conectar() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/" + this.nomBD;
            this.conexion = (Connection) DriverManager.getConnection(url, this.usuario, this.password);
            this.sentencia = (Statement) this.conexion.createStatement();
        } catch (Exception e) {
            msj = "error al conectar";
        }
    }
    
    public int verificar(){
        int v=0;
        String sub = jt_can.getText();
        if (LBL_estado1.getText().equals("")){
            v++;
        }
        if (jt_can.getText().equals("")){
            v++;
        }
        if (!sub.matches("[-+]?\\d*\\.?\\d+")) {
            JOptionPane.showMessageDialog(null, "Error, cantidad tiene que ser númerica", "ERROR", JOptionPane.ERROR_MESSAGE);
            v++;
        } else if (jt_can.getText().length() >= 3) {
            JOptionPane.showMessageDialog(null,
                    "Error, cantidad maximo 2 digitos y no negativo!", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            v++;
        }
        return v;
    }
    
    public int verificar1(){
        int v=0;
        if (LBL_estado1.getText().equals("")){
            v++;
        }
        return v;
    }
    
    public int verificar2(){
        int vv=0;
        int sto = 0;
        String nom = jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString();
        int can2 = Integer.parseInt(jTable1.getValueAt(jTable1.getSelectedRow(), 5).toString());
        int can = Integer.parseInt(jt_can.getText());
        int can3 = can + can2;
        try {
            sentencia = (com.mysql.jdbc.Statement) conexion.createStatement();
            ResultSet sr = sentencia.executeQuery("SELECT stock_actual FROM inventario WHERE nombre = '" + nom + "'");
            while (sr.next()) {
                sto = sr.getInt("stock_actual");
            }
        } catch (SQLException f) {
            msj = "Error con Stock";
        }
        if (can3 > sto){
            vv++;
        }
        return vv;
    }
    
    public int verificar3(){
        int vvv=0;
        int sto = Integer.parseInt(jTable1.getValueAt(jTable1.getSelectedRow(), 5).toString());
        int can = Integer.parseInt(jt_can.getText());
        int res = sto - can;
        if (res<=0){
            vvv++;
        }
        return vvv;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel9 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jt_can = new javax.swing.JTextField();
        JB_cancel1 = new javax.swing.JButton();
        btn_del = new javax.swing.JButton();
        btn_add = new javax.swing.JButton();
        btn_less = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        LBL_estado1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel9.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel9.setText("RACAD AUTOMOTRIZ - MODIFICAR/ELIMINAR INSUMO");

        jLabel2.setText("Cantidad :");

        jt_can.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jt_canActionPerformed(evt);
            }
        });

        JB_cancel1.setText("Salir");
        JB_cancel1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JB_cancel1ActionPerformed(evt);
            }
        });

        btn_del.setText("Eliminar");
        btn_del.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_delActionPerformed(evt);
            }
        });

        btn_add.setText("Aumentar");
        btn_add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_addActionPerformed(evt);
            }
        });

        btn_less.setText("Disminuir");
        btn_less.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_lessActionPerformed(evt);
            }
        });

        jTable1.setModel(modeloTabla);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 479, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jt_can, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btn_add, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_less, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(LBL_estado1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(36, 36, 36)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btn_del, javax.swing.GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE)
                            .addComponent(JB_cancel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jt_can, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_add)
                    .addComponent(btn_less)
                    .addComponent(btn_del))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(JB_cancel1)
                    .addComponent(LBL_estado1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jt_canActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jt_canActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jt_canActionPerformed

    private void JB_cancel1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JB_cancel1ActionPerformed
        this.dispose();
    }//GEN-LAST:event_JB_cancel1ActionPerformed

    private void btn_addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_addActionPerformed
        if(verificar()==0){
            if(verificar2()==0){
                String nom = jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString();
                int cod2=0;
                int sto2=0;
                try {
                    sentencia = (com.mysql.jdbc.Statement) conexion.createStatement();
                    ResultSet sr = sentencia.executeQuery("SELECT * FROM inventario WHERE nombre = '" + nom + "'");
                   while (sr.next()) {
                        cod2 = sr.getInt("cod_item");
                        sto2 = sr.getInt("stock_actual");
                    }
                } catch (SQLException f) {
                    msj = "Error con Codigo";
                }
                int cod = Integer.parseInt(jTable1.getValueAt(jTable1.getSelectedRow(), 1).toString());
                int ids = Integer.parseInt(jTable1.getValueAt(jTable1.getSelectedRow(), 3).toString());
                int sto = Integer.parseInt(jTable1.getValueAt(jTable1.getSelectedRow(), 5).toString());
                int can = Integer.parseInt(jt_can.getText());
                int reb = sto + can;
                int reb2 = sto2 - can;
                String sql="UPDATE detalle_insumo SET cantidad = " + reb + " WHERE cod_item = " + cod2 + " AND cod_solicitud = " + cod + " ";
                try{
                    sentencia.executeUpdate(sql);
                    LBL_estado1.setText("Actualizado insumos");
                }
                catch(SQLException ee){
                    msj="Error al Actualizar"; 
                    LBL_estado1.setText(msj);
                }
                String sql2="UPDATE inventario SET stock_actual = " + reb2 + " WHERE cod_item = " + cod2 + "";
                try{
                    sentencia.executeUpdate(sql2);
                    LBL_estado1.setText("Actualizado inventario");
                }
                catch(SQLException ef){
                    msj="Error al disminuir";
                    LBL_estado1.setText(msj);
                }
                String sql3="UPDATE detalle_solicitud SET cantidad = " + reb + " WHERE cod_item = " + cod2 + " AND id_servicio = " + ids + " ";
                try{
                    sentencia.executeUpdate(sql3);
                    LBL_estado1.setText("Actualizado detalle");
                }
                catch(SQLException ef){
                    msj="Error al Aumentar detalle";
                    LBL_estado1.setText(msj);
                }
                limpiaTabla();
                limpiaTexto();
                setFilas();
            }else{
            JOptionPane.showMessageDialog(null,
                "Error, No puede sacar más de lo que hay!", "ERROR",
                JOptionPane.ERROR_MESSAGE);
            limpiaTexto();
            }
        }else{
            JOptionPane.showMessageDialog(null,
                "Error, Seleccione algo!", "ERROR",
                JOptionPane.ERROR_MESSAGE);
            limpiaTexto();
        }  
    }//GEN-LAST:event_btn_addActionPerformed

    private void btn_lessActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_lessActionPerformed
        if(verificar()==0){
            if(verificar3()==0){
                    String nom = jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString();
                    int cod2=0;
                    int sto2=0;
                    try {
                        sentencia = (com.mysql.jdbc.Statement) conexion.createStatement();
                        ResultSet sr = sentencia.executeQuery("SELECT * FROM inventario WHERE nombre = '" + nom + "'");
                       while (sr.next()) {
                            cod2 = sr.getInt("cod_item");
                            sto2 = sr.getInt("stock_actual");
                        }
                    } catch (SQLException f) {
                        msj = "Error con Codigo";
                    }
                    int cod = Integer.parseInt(jTable1.getValueAt(jTable1.getSelectedRow(), 1).toString());
                    int sto = Integer.parseInt(jTable1.getValueAt(jTable1.getSelectedRow(), 5).toString());
                    int can = Integer.parseInt(jt_can.getText());
                    int reb = sto - can;
                    int reb2 = sto2 + can;
                    String sql="UPDATE detalle_insumo SET cantidad = " + reb + " WHERE cod_item = " + cod2 + " AND cod_solicitud = " + cod + " ";
                    try{
                        sentencia.executeUpdate(sql);
                        LBL_estado1.setText("Actualizado insumos");
                    }
                    catch(SQLException ee){
                        msj="Error al Actualizar"; 
                        LBL_estado1.setText(msj);
                    }   
                    String sql2="UPDATE inventario SET stock_actual = " + reb2 + " WHERE cod_item = " + cod2 + "";
                    try{
                        sentencia.executeUpdate(sql2);
                        LBL_estado1.setText("Cantidad disminuida");
                    }
                    catch(SQLException ef){
                        msj="Error al Disminuir";
                        LBL_estado1.setText(msj);
                    }
                    String sql3="UPDATE detalle_solicitud SET cantidad = " + reb + " WHERE cod_item = " + cod2 + "";
                    try{
                        sentencia.executeUpdate(sql3);
                        LBL_estado1.setText("Actualizado detalle");
                    }
                    catch(SQLException ef){
                        msj="Error al Aumentar detalle";
                        LBL_estado1.setText(msj);
                    }
                    limpiaTabla();
                    limpiaTexto();
                    setFilas();
                }else{
                    JOptionPane.showMessageDialog(null,
                        "Error, No puede sacar 0 ni items negativos", "ERROR",
                        JOptionPane.ERROR_MESSAGE);
                    limpiaTexto();
                }
        }else{
            JOptionPane.showMessageDialog(null,
                "Error, Seleccione algo!", "ERROR",
                JOptionPane.ERROR_MESSAGE);
            limpiaTexto();
        }
    }//GEN-LAST:event_btn_lessActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        String nom = jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString();
        LBL_estado1.setText("Item : " + nom +"");

    }//GEN-LAST:event_jTable1MouseClicked

    private void btn_delActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_delActionPerformed
        if(verificar1()==0){
            String nom = jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString();
            int cod2=0;
            int sto2=0;
            try {
                sentencia = (com.mysql.jdbc.Statement) conexion.createStatement();
                ResultSet sr = sentencia.executeQuery("SELECT cod_item FROM inventario WHERE nombre = '" + nom + "'");
                while (sr.next()) {
                    cod2 = sr.getInt("cod_item");
                }
            } catch (SQLException f) {
                msj = "Error con Codigo";
                }
            try {
                sentencia = (com.mysql.jdbc.Statement) conexion.createStatement();
                ResultSet sr = sentencia.executeQuery("SELECT stock_actual FROM inventario WHERE nombre = '" + nom + "'");
                while (sr.next()) {
                sto2 = sr.getInt("stock_actual");
                }
            } catch (SQLException f) {
                msj = "Error con Codigo";
                }
            int sto = Integer.parseInt(jTable1.getValueAt(jTable1.getSelectedRow(), 5).toString());    
            int cod = Integer.parseInt(jTable1.getValueAt(jTable1.getSelectedRow(), 1).toString());
            int reb=sto+sto2;
            
            String sql="DELETE FROM detalle_insumo WHERE cod_item = " + cod2 + " AND cod_solicitud = " + cod + " ";
                try{
                    sentencia.executeUpdate(sql);
                    LBL_estado1.setText("Eliminado de insumos");
                }
                catch(SQLException ee){
                    msj="Error al Eliminar"; 
                    LBL_estado1.setText(msj);
                }       
        
        
            String sql2="UPDATE inventario SET stock_actual = " + reb + " WHERE cod_item = " + cod2 + "";
                try{
                    sentencia.executeUpdate(sql2);
                    LBL_estado1.setText("Devuelto a stock");
                }
                catch(SQLException ef){
                    msj="Error al devolver";
                    LBL_estado1.setText(msj);
                }
                limpiaTabla();
                limpiaTexto();
                setFilas();
        }else{
            JOptionPane.showMessageDialog(null,
                "Error, Seleccione algo!", "ERROR",
                JOptionPane.ERROR_MESSAGE);
            limpiaTexto();
        }    
    }//GEN-LAST:event_btn_delActionPerformed

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
            java.util.logging.Logger.getLogger(ModEli_insumo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ModEli_insumo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ModEli_insumo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ModEli_insumo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ModEli_insumo().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton JB_cancel1;
    private javax.swing.JLabel LBL_estado1;
    private javax.swing.JButton btn_add;
    private javax.swing.JButton btn_del;
    private javax.swing.JButton btn_less;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jt_can;
    // End of variables declaration//GEN-END:variables
}
