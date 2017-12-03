package racadauto;

import com.mysql.jdbc.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.spi.DirStateFactory.Result;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Ingresar_insumo extends javax.swing.JFrame {

    private Statement sentencia;
    private Connection conexion;
    private String nomBD = "racad";
    private String usuario = "root";
    private String password = "";
    private String msj;
    DefaultTableModel modeloTabla;
    DefaultTableModel modeloTabla2;
    DefaultTableModel modeloTabla3;
    
    public Ingresar_insumo() {
        conectar();
        modeloTabla = new DefaultTableModel(null, getColumnas());
        modeloTabla2 = new DefaultTableModel(null, getColumnas2());
        modeloTabla3 = new DefaultTableModel(null, getColumnas3());
        setFilas();
        setFilas2();
        initComponents();
    }
    
    private String[] getColumnas() {
        String columna[] = new String[]{"Nombre", "Stock Actual", "Stock Critico", "Valor"};
        return columna;
    }
    
    private void setFilas() {
        try {
            sentencia = (com.mysql.jdbc.Statement) conexion.createStatement();
            ResultSet lista = sentencia.executeQuery("SELECT nombre, stock_actual, stock_critico, valor_costo FROM inventario WHERE estado = 1 ");
            Object datos[] = new Object[4];
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
    
    private String[] getColumnas2() {
        String columna[] = new String[]{"ID", "Folio", "Observaciones", "Patente"};
        return columna;
    }
    
    private void setFilas2() {
        try {
            sentencia = (com.mysql.jdbc.Statement) conexion.createStatement();
            ResultSet lista = sentencia.executeQuery("SELECT s.cod_solicitud, s.folio, s.observaciones, s.patente "
                    + "FROM solicitud_servicio s "
                    + "WHERE estado_solicitud = 'PRESUPUEST'");
            Object datos[] = new Object[4];
            while (lista.next()) {
                for (int i = 0; i < 4; i++) {
                    datos[i] = lista.getObject(i + 1);
                }
                modeloTabla2.addRow(datos);
            }
        } catch (Exception e) {
            msj = "No se pudo llenar tabla";
        }
    }
    
    private String[] getColumnas3() {
        String columna[] = new String[]{"ID", "Nombre", "Componente", "Precio","Categoría"};
        return columna;
    }
    
    
    //puede que tenga un error, no es muy convencional el metodo, pero en mis testeos funciona
    private void setFilas3() {
        int cod = Integer.parseInt(jTable2.getValueAt(jTable2.getSelectedRow(), 0).toString());
        int id=0;
        try {
            sentencia = (com.mysql.jdbc.Statement) conexion.createStatement();
            ResultSet sr = sentencia.executeQuery("SELECT id_servicio FROM detalle_insumo WHERE cod_solicitud = " + cod + "");
            while (sr.next()) {
                id = sr.getInt("id_servicio");
                try {
                    sentencia = (com.mysql.jdbc.Statement) conexion.createStatement();
                    ResultSet lista = sentencia.executeQuery("SELECT s.id_servicio, s.nombre, s.componente, s.precio, c.nombre "
                        + "FROM servicio s INNER JOIN categoria c ON s.id_categoria = c.id_categoria "
                        + "WHERE id_servicio = " + id + " ");
                    Object datos[] = new Object[5];
                    while (lista.next()) {
                        for (int i = 0; i < 5; i++) {
                            datos[i] = lista.getObject(i + 1);
                        }
                    modeloTabla3.addRow(datos);
                    }
                } catch (Exception e) {
                    msj = "No se pudo llenar tabla";
                }
            }
        } catch (SQLException f) {
            msj = "Error con Codigo";
        }
        
    }
    
    void limpiaTabla() {
        do {
            modeloTabla.setRowCount(0);
            modeloTabla2.setRowCount(0);
            modeloTabla3.setRowCount(0);
        } while ((modeloTabla.getRowCount() != 0)&(modeloTabla2.getRowCount() != 0)&(modeloTabla3.getRowCount() != 0));
    }
    
    void limpiaTexto(){
        jt_can.setText("");
        LBL_estado1.setText("");
        LBL_estado2.setText("");
        LBL_estado3.setText("");
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
        if (LBL_estado2.getText().equals("")){
            v++;
        }
        if (LBL_estado3.getText().equals("")){
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
    
    public int verificar2(){
        int vv=0;
        int sto = Integer.parseInt(jTable1.getValueAt(jTable1.getSelectedRow(), 1).toString());
        int can = Integer.parseInt(jt_can.getText());
        if (can > sto){
            vv++;
        }
        return vv;
    }
    
    public int verificar3(){
        int vvv=0;
        String nom = jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString();
        int sol = Integer.parseInt(jTable2.getValueAt(jTable2.getSelectedRow(), 0).toString());
        int cod=0;
        int cod2=0;
        int cos=0;
        try {
            sentencia = (com.mysql.jdbc.Statement) conexion.createStatement();
            ResultSet sr = sentencia.executeQuery("SELECT cod_item FROM inventario WHERE nombre = '" + nom + "'");
            while (sr.next()) {
                cod2 = sr.getInt("cod_item");
            }
        } catch (SQLException f) {
            msj = "Error con Codigo";
        }
        try{
            sentencia = (com.mysql.jdbc.Statement) conexion.createStatement();
            ResultSet rs = sentencia.executeQuery("SELECT * FROM detalle_insumo");
            while (rs.next()) {
                cod = rs.getInt("cod_item");
                cos = rs.getInt("cod_solicitud");
                if(cod==cod2){
                    if(sol==cos){
                        vvv++;
                    }
                }
            }
        }catch(SQLException ee){
            msj="Error al Verificar repeticion"; 
            LBL_estado2.setText(msj);
        }        
        return vvv;
    }
     
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jt_can = new javax.swing.JTextField();
        jb_add = new javax.swing.JButton();
        JB_cancel1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        LBL_estado2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        LBL_estado1 = new javax.swing.JLabel();
        LBL_estado4 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        LBL_estado3 = new javax.swing.JLabel();

        jLabel1.setText("jLabel1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel9.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel9.setText("RACAD AUTOMOTRIZ - AGREGAR INSUMO");

        jLabel2.setText("Cantidad :");

        jt_can.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jt_canActionPerformed(evt);
            }
        });

        jb_add.setText("Agregar");
        jb_add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jb_addActionPerformed(evt);
            }
        });

        JB_cancel1.setText("Salir");
        JB_cancel1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JB_cancel1ActionPerformed(evt);
            }
        });

        jTable1.setModel(modeloTabla);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jLabel3.setText("Item :");

        jTable2.setModel(modeloTabla2);
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jTable2);

        jLabel5.setText("Orden de Trabajo :");

        jTable3.setModel(modeloTabla3);
        jTable3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable3MouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(jTable3);

        jLabel4.setText("Servicio :");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(JB_cancel1, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 405, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel4)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(LBL_estado3, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 16, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 405, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(LBL_estado1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jb_add, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jt_can, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(LBL_estado4, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 405, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(LBL_estado2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel9))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(LBL_estado1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(LBL_estado2, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(LBL_estado3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jt_can, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jb_add)
                            .addComponent(JB_cancel1)))
                    .addComponent(LBL_estado4, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jt_canActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jt_canActionPerformed
        
    }//GEN-LAST:event_jt_canActionPerformed

    private void JB_cancel1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JB_cancel1ActionPerformed
        this.dispose();
    }//GEN-LAST:event_JB_cancel1ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        LBL_estado1.setText("OK");
        LBL_estado2.setText("");
        do {
            modeloTabla2.setRowCount(0);
            }while (modeloTabla2.getRowCount() != 0);
            setFilas2();
    }//GEN-LAST:event_jTable1MouseClicked

    private void jb_addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jb_addActionPerformed
        if(verificar()==0){
            if (verificar2()==0){
                if(verificar3()==0){
                    String nom = jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString();
                    int sto = Integer.parseInt(jTable1.getValueAt(jTable1.getSelectedRow(), 1).toString());
                    int sol = Integer.parseInt(jTable2.getValueAt(jTable2.getSelectedRow(), 0).toString());
                    int ids = Integer.parseInt(jTable3.getValueAt(jTable3.getSelectedRow(), 0).toString());
                    int cod = 0;
                    int can = Integer.parseInt(jt_can.getText());
                    int reb = sto - can;
                    try {
                        sentencia = (com.mysql.jdbc.Statement) conexion.createStatement();
                        ResultSet rs = sentencia.executeQuery("SELECT cod_item FROM inventario WHERE nombre = '" + nom + "'");
                        while (rs.next()) {
                            cod = rs.getInt("cod_item");
                        }
                    } catch (SQLException f) {
                        msj = "Error con Codigo";
                    }
                    String sql="INSERT INTO detalle_insumo(cod_item, cod_solicitud, id_servicio, cantidad) VALUES (" + cod + "," + sol +"," + ids +"," + can + ")";
                    try{
                        sentencia.executeUpdate(sql);
                        LBL_estado4.setText("Item ingresado insumos");
                    }
                    catch(SQLException ee){
                        msj="Error al Ingresar"; 
                        LBL_estado4.setText(msj);
                    }
                    String sql2="UPDATE inventario SET stock_actual = " + reb + " WHERE cod_item = " + cod + "";
                    try{
                        sentencia.executeUpdate(sql2);
                        LBL_estado4.setText("Item ingresado insumos");
                    }
                    catch(SQLException ef){
                        msj="Error al Rebajar";
                        LBL_estado4.setText(msj);
                    }    
                    String sql3="UPDATE detalle_solicitud SET cantidad = " + can + " WHERE cod_item = " + cod + " AND id_servicio =" + ids + " ";
                    try{
                        sentencia.executeUpdate(sql3);
                        LBL_estado1.setText("Actualizado detalle");
                    }
                    catch(SQLException ef){
                        msj="Error al Aumentar detalle";
                        LBL_estado1.setText(msj);
                    }
                    limpiaTabla();
                    setFilas();
                    setFilas2();
                    setFilas3();
                }else{
                    JOptionPane.showMessageDialog(null,
                        "Error, Item ya ingresado en esta OT. Modifique si desea aumentar la cantidad.", "ERROR",
                        JOptionPane.ERROR_MESSAGE);
                }                    
            }else{
                JOptionPane.showMessageDialog(null,
                    "Error, No hay suficientes items!", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            }
        }else{
            JOptionPane.showMessageDialog(null,
                    "Error, Seleccione item e indique cantidad a usar!", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
        }
        limpiaTexto();
        
    }//GEN-LAST:event_jb_addActionPerformed

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
        LBL_estado2.setText("OK");
        LBL_estado3.setText("");
        do {
        modeloTabla3.setRowCount(0);
        }while (modeloTabla3.getRowCount() != 0);
        setFilas3();
    }//GEN-LAST:event_jTable2MouseClicked

    private void jTable3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable3MouseClicked
        LBL_estado3.setText("OK");
    }//GEN-LAST:event_jTable3MouseClicked

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
            java.util.logging.Logger.getLogger(Ingresar_insumo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Ingresar_insumo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Ingresar_insumo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Ingresar_insumo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Ingresar_insumo().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton JB_cancel1;
    private javax.swing.JLabel LBL_estado1;
    private javax.swing.JLabel LBL_estado2;
    private javax.swing.JLabel LBL_estado3;
    private javax.swing.JLabel LBL_estado4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JButton jb_add;
    private javax.swing.JTextField jt_can;
    // End of variables declaration//GEN-END:variables
}
