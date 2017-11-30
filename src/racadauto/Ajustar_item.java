package racadauto;

import Conexion.Conexion;
import com.mysql.jdbc.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Ajustar_item extends javax.swing.JFrame {
    
    private Statement sentencia;
    Conexion con = new Conexion();
    Connection cn = (Connection) con.getConnection();
    DefaultTableModel modeloTabla;
    String msj = "";

    public Ajustar_item() {
         
        modeloTabla = new DefaultTableModel(null,getColumnas());
        setFilas();
        initComponents();
        fechaActual();
    }
    
    private String[] getColumnas() {

        String columna[] = new String[]{"NOMBRE", "STOCK ACTUAL", "STOCK CRITICO", "VALOR COSTO", "VALOR VENTA", "MEDIDA", "FAMILIA"};

        return columna;
    }
    
    void limpiaTabla() {
        do {
            modeloTabla.getRowCount();
            modeloTabla.removeRow(0);
        } while (modeloTabla.getRowCount() != 0);
    }
    
    
    private void setFilas() {
        try {
            sentencia = (com.mysql.jdbc.Statement) cn.createStatement();
            ResultSet lista = sentencia.executeQuery("SELECT i.nombre, i.stock_actual, i.stock_critico, i.valor_costo, i.valor_venta, um.nombre, f.nombre"
                    + " FROM inventario i INNER JOIN unidad_medida um ON i.id_medida = um.id_medida LEFT JOIN familia f ON i.id_familia = f.id_familia ");
            Object datos[] = new Object[7];
            while (lista.next()) {
                for (int i = 0; i < 7; i++) {
                    datos[i] = lista.getObject(i + 1);
                }
                modeloTabla.addRow(datos);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error al Conectar","Conexion",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    
    public void fechaActual(){
        java.util.Date fechaActual = new java.util.Date();
        long fecha = fechaActual.getTime();
        java.sql.Date sqlDate = new java.sql.Date(fecha);
        SimpleDateFormat formato = new SimpleDateFormat("dd-MMMM-yyy kk:mm");
        lblFecha.setText(formato.format(sqlDate).toUpperCase());
    }
       
    
    public int validar(){
        int val=0;
        if ((txtDesc.getText().equals(""))||
            (txtCant.getText().equals(""))){
            JOptionPane.showMessageDialog(null,
                "Error, dejó una casilla vacía","ERROR",
                JOptionPane.ERROR_MESSAGE);
                val+=1;
        }
        if(txtDesc.getText().length()>=140) {  
            JOptionPane.showMessageDialog(null,
                "Error, solo 140 caracteres","ERROR",
                JOptionPane.ERROR_MESSAGE);
                val+=1;
        }
        if(txtCant.getText().length()>=2) {  
            JOptionPane.showMessageDialog(null,
                "Error, cantidad <100 y >0","ERROR",
                JOptionPane.ERROR_MESSAGE);
                val+=1;
        }
        String cant = txtCant.getText();
        if(!cant.matches("[-+]?\\d*\\.?\\d+")){ 
            JOptionPane.showMessageDialog(null,
                "Error, stock tiene que ser numerico","ERROR",
                JOptionPane.ERROR_MESSAGE);
                val+=1;
        }
        
        return val;
    }
    
    public int validar2(){
        int val=0,can=0;
        String nom = jTable1.getValueAt(jTable1.getSelectedRow(),0).toString();
        try{
            sentencia=(Statement)cn.createStatement();
            ResultSet rs=sentencia.executeQuery("SELECT stock_actual FROM inventario WHERE nombre = '" + nom + "'" );
            while(rs.next()){
                can = rs.getInt("stock_actual");}
            }catch(SQLException f){
                msj="Error con Codigo";
        }
        if ((txtDesc.getText().equals(""))||
            (txtCant.getText().equals(""))){
            JOptionPane.showMessageDialog(null,
                "Error, dejó una casilla vacía","ERROR",
                JOptionPane.ERROR_MESSAGE);
                val+=1;
        }else{
            val=0;
        }
        if (can == 0){
            JOptionPane.showMessageDialog(null,
                "Error, item no puede ser negativo","ERROR",
                JOptionPane.ERROR_MESSAGE);
                val+=1;
        }else{
            val=0;
        }
        return val;
    }
    
    public void clean(){
        txtDesc.setText("");
        txtCant.setText("");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel9 = new javax.swing.JLabel();
        lblFecha = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtCant = new javax.swing.JTextField();
        btnIncrease = new javax.swing.JButton();
        btnDecrease = new javax.swing.JButton();
        JB_cancel = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lblEstado = new javax.swing.JLabel();
        btnRehab = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDesc = new javax.swing.JTextArea();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("AJUSTAR ITEM");

        jLabel9.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel9.setText("RACAD AUTOMOTRIZ - AJUSTAR ITEM");

        jLabel3.setText("Cantidad a Ajustar: ");

        btnIncrease.setText("Aumentar");
        btnIncrease.setPreferredSize(new java.awt.Dimension(80, 23));
        btnIncrease.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIncreaseActionPerformed(evt);
            }
        });

        btnDecrease.setText("Reducir");
        btnDecrease.setPreferredSize(new java.awt.Dimension(80, 23));
        btnDecrease.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDecreaseActionPerformed(evt);
            }
        });

        JB_cancel.setText("Volver");
        JB_cancel.setPreferredSize(new java.awt.Dimension(80, 23));
        JB_cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JB_cancelActionPerformed(evt);
            }
        });

        jLabel4.setText("Descripción del Ajuste:");

        jLabel5.setText("Fecha :");

        jLabel6.setText("Estado :");

        btnRehab.setText("Re-Habilitar Estado");
        btnRehab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRehabActionPerformed(evt);
            }
        });

        txtDesc.setColumns(20);
        txtDesc.setRows(5);
        jScrollPane1.setViewportView(txtDesc);

        jTable1.setModel(modeloTabla);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(jTable1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnIncrease, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 84, Short.MAX_VALUE)
                        .addComponent(btnDecrease, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(67, 67, 67)
                        .addComponent(JB_cancel, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(txtCant, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addComponent(lblFecha)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnRehab, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 372, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblEstado, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnRehab, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5)
                        .addComponent(lblFecha)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3)
                            .addComponent(txtCant, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE)
                        .addGap(64, 64, 64))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(JB_cancel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnDecrease, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnIncrease, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void JB_cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JB_cancelActionPerformed
        this.dispose();
    }//GEN-LAST:event_JB_cancelActionPerformed

    private void btnIncreaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIncreaseActionPerformed
        if (validar()==0){
        int dis=0;
        int sto=Integer.parseInt(txtCant.getText());
        int fol=0;
        String rut="185823393";
        String nom = jTable1.getValueAt(jTable1.getSelectedRow(),0).toString();
        int est = 0;
        int cod=0;
        String fech = lblFecha.getText();
        String desc = txtDesc.getText();
        int cant = Integer.parseInt(txtCant.getText());
        
        //se obtiene codigo
        try{
            sentencia=(Statement)cn.createStatement();
            ResultSet rs=sentencia.executeQuery("SELECT cod_item FROM inventario WHERE nombre = '" + nom + "'" );
            while(rs.next()){
                cod = rs.getInt("cod_item");}
            }catch(SQLException f){
                msj="Error con Codigo";
        }
        //se obtiene numero folio
        try{
                sentencia=(com.mysql.jdbc.Statement)cn.createStatement();
                ResultSet rs=sentencia.executeQuery("SELECT MAX(n_folio) as n_folio FROM ajuste");
                while(rs.next()){
                    fol = rs.getInt("n_folio");
                }
                fol+=1;
            }catch(SQLException f){
            msj="Error con Codigo";
        }
        //se obtiene rut trabajador
        /*
        try{
                sentencia=(com.mysql.jdbc.Statement)conexion.createStatement();
                ResultSet rs=sentencia.executeQuery("SELECT MAX(rut_trabajador) as rut_trabajador FROM trabajador");
                while(rs.next()){
                    rut = rs.getString("rut_trabajador");
                }
            }catch(SQLException f){
            msj="Error con Codigo";
        }
        */
            String sql="UPDATE inventario SET stock_actual =stock_actual+"+ sto +" WHERE  cod_item =" + cod + "";
            try{
                sentencia.executeUpdate(sql);
                msj="Datos Guardados";
                txtDesc.setText(msj);
                dis += 1;
            }
            catch(SQLException e){
                msj="Item no Ingresado stock";
                txtDesc.setText(msj);
                dis = 0;
            }
            
            
            String sql2="INSERT INTO ajuste(n_folio,rut_trabajador,cod_item,fecha,descrip_ajuste,cantidad) VALUES(" + fol + ",'" + rut +"'," + cod + ",'2017-11-17','" + desc + "'," + cant + ")";
            try{
                sentencia.executeUpdate(sql2);
                msj="Datos Guardados";
                txtDesc.setText(msj);
                dis += 1;
            }
            catch(SQLException e){
                msj="Item no Ingresado ajuste";
                txtDesc.setText(msj);
                dis = 0;
            }
            
            if (dis == 2){
                clean();
                lblEstado.setText("Completado!");
            }
        }else{
            lblEstado.setText("Llene todo!");
        }
        
        limpiaTabla();
        setFilas();
        
    }//GEN-LAST:event_btnIncreaseActionPerformed

    private void btnRehabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRehabActionPerformed
        String est = lblEstado.getText();
        String nom = jTable1.getValueAt(jTable1.getSelectedRow(),0).toString();
        if (est == "Des-Habilitado"){
            String sql="UPDATE inventario SET estado = 1 WHERE nombre ='" + nom + "'";
                try{
                    sentencia.executeUpdate(sql);
                    lblEstado.setText("Cambiado!");
                }
                catch(SQLException ee){
                    msj="Error al inactivar";
                    lblEstado.setText(msj);
                }
        }
        limpiaTabla();
        setFilas();
        
    }//GEN-LAST:event_btnRehabActionPerformed

    private void btnDecreaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDecreaseActionPerformed
        if (validar2()==0){
        int dis=0;
        int sto=Integer.parseInt(txtCant.getText());
        int fol=0;
        String rut="";
        String nom = jTable1.getValueAt(jTable1.getSelectedRow(),0).toString();
        int est = 0;
        int cod=0;
        String fech = "2017-10-05";
        String desc = txtDesc.getText();
        int cant = Integer.parseInt(txtCant.getText());
        try{
        sentencia=(Statement)cn.createStatement();
        ResultSet rs=sentencia.executeQuery("SELECT cod_item FROM inventario WHERE nombre = '" + nom + "'" );
        while(rs.next()){
            cod = rs.getInt("cod_item");}
        }catch(SQLException f){
            msj="Error con Codigo";
        }
        try{
                sentencia=(com.mysql.jdbc.Statement)cn.createStatement();
                ResultSet rs=sentencia.executeQuery("SELECT MAX(n_folio) as n_folio FROM ajuste");
                while(rs.next()){
                    fol = rs.getInt("n_folio");
                }
                fol+=1;
            }catch(SQLException f){
            msj="Error con Codigo";
        }
        try{
                sentencia=(com.mysql.jdbc.Statement)cn.createStatement();
                ResultSet rs=sentencia.executeQuery("SELECT MAX(rut_trabajador) as rut_trabajador FROM trabajador");
                while(rs.next()){
                    rut = rs.getString("rut_trabajador");
                }
            }catch(SQLException f){
            msj="Error con Codigo";
        }
            String sql="UPDATE inventario SET stock_actual = stock_actual - "+ sto +" WHERE  cod_item =" + cod + "";
            try{
                sentencia.executeUpdate(sql);
                msj="Datos Guardados";
                txtDesc.setText(msj);
                dis += 1;
            }
            catch(SQLException e){
                msj="Item no Ingresado Stock";
                txtDesc.setText(msj);
                dis = 0;
            }
            String sql2="INSERT INTO ajuste(n_folio,rut_trabajador,cod_item,fecha,descrip_ajuste,cantidad) VALUES(" + fol + ",'" + rut + "'," + cod + ",'2017-10-05','" + desc + "'," + cant + ")";
            try{
                sentencia.executeUpdate(sql2);
                msj="Datos Guardados";
                txtDesc.setText(msj);
                dis += 1;
            }
            catch(SQLException e){
                msj="Item no Ingresado Ajuste";
                txtDesc.setText(msj);
                dis = 0;
            }
            
            if (dis == 2){
                clean();
                lblEstado.setText("Completado!");
            }
            
        }else{
            lblEstado.setText("Llene todo!");
        }
        
        limpiaTabla();
        setFilas();
        
    }//GEN-LAST:event_btnDecreaseActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        
        String nom = jTable1.getValueAt(jTable1.getSelectedRow(),0).toString();
        int est = 0;
        try{
        sentencia=(Statement)cn.createStatement();
        ResultSet rs=sentencia.executeQuery("SELECT estado FROM inventario WHERE nombre = '" + nom + "'" );
        while(rs.next()){
            est = rs.getInt("estado");
            
            if (est == 1){
                lblEstado.setText("Habilitado");
            }else{
                lblEstado.setText("Des-Habilitado");
            }
            }
        }
        catch(SQLException ed){
            msj="No se pudo llenar Label";
        }
        
    }//GEN-LAST:event_jTable1MouseClicked

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
            java.util.logging.Logger.getLogger(Ajustar_item.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Ajustar_item.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Ajustar_item.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Ajustar_item.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Ajustar_item().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton JB_cancel;
    private javax.swing.JButton btnDecrease;
    private javax.swing.JButton btnIncrease;
    private javax.swing.JButton btnRehab;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lblEstado;
    private javax.swing.JLabel lblFecha;
    private javax.swing.JTextField txtCant;
    private javax.swing.JTextArea txtDesc;
    // End of variables declaration//GEN-END:variables
}
