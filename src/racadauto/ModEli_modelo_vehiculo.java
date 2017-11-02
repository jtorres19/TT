package racadauto;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ModEli_modelo_vehiculo extends javax.swing.JFrame {

    private Statement sentencia;
    private Connection conexion;
    private String nomBD="racad";
    private String usuario="root";
    private String password="";
    private String msj;
    DefaultTableModel modeloTabla;

    
    public ModEli_modelo_vehiculo() {
        conectar();
        modeloTabla = new DefaultTableModel(null, getColumnas());
        setFilas();
        initComponents();
    }
    
    private String[] getColumnas() {

        String columna[] = new String[]{"Marca", "Modelo", "Nombre", "Tipo", "Combustible", "Motor"};

        return columna;
    }

    private void setFilas() {
        try {
            sentencia = (com.mysql.jdbc.Statement) conexion.createStatement();
            ResultSet lista = sentencia.executeQuery("SELECT * FROM modelo");
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
    
    void limpiaTabla() {
        do {
            modeloTabla.getRowCount();
            modeloTabla.removeRow(0);
        } while (modeloTabla.getRowCount() != 0);
    }
    
    public void llenarcombos(){
        cmb_tipo.removeAllItems();
        cmb_marc.removeAllItems();
        cmb_comb.removeAllItems();
        cmb_motor.removeAllItems();
        try {
            sentencia = (com.mysql.jdbc.Statement) conexion.createStatement();
            ResultSet lista = sentencia.executeQuery("SELECT nombre FROM tipo_vehiculo");
            while (lista.next()) {
                cmb_tipo.addItem(lista.getString("nombre"));
            }
        } catch (SQLException ed) {
            msj = "no se pudo seleccionar";
        }
        try {
            sentencia = (com.mysql.jdbc.Statement) conexion.createStatement();
            ResultSet lista = sentencia.executeQuery("SELECT nombre FROM marca");
            while (lista.next()) {
                cmb_marc.addItem(lista.getString("nombre"));
            }
        } catch (SQLException ed) {
            msj = "no se pudo seleccionar";
        }
        try {
            sentencia = (com.mysql.jdbc.Statement) conexion.createStatement();
            ResultSet lista = sentencia.executeQuery("SELECT nombre FROM tipo_combustible");
            while (lista.next()) {
                cmb_comb.addItem(lista.getString("nombre"));
            }
        } catch (SQLException ed) {
            msj = "no se pudo seleccionar";
        }
        try {
            sentencia = (com.mysql.jdbc.Statement) conexion.createStatement();
            ResultSet lista = sentencia.executeQuery("SELECT nombre FROM tipo_motor");
            while (lista.next()) {
                cmb_motor.addItem(lista.getString("nombre"));
            }
        } catch (SQLException ed) {
            msj = "no se pudo seleccionar";
        }
    }
    
    public int verificar(){
        int ver = 0;
        if (txt_nom.getText().equals("")){
            JOptionPane.showMessageDialog(null,
                    "Error, dejó la casilla vacía", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            ver ++;
        }
        if (cmb_tipo.getSelectedIndex()==-1){
            JOptionPane.showMessageDialog(null,
                    "Error, no seleccionó en la tabla", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            ver ++;
        }
        if (txt_nom.getText().length() >=16){
            JOptionPane.showMessageDialog(null,
                    "Nombre no puede exceder 15 caracteres!", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            ver ++;
        }
        return ver;
    }
    
    public int verificar2(){
        int ver2=0;
        int mod=0;
        int mod2=0;
        int nom = Integer.parseInt(jTable2.getValueAt(jTable2.getSelectedRow(), 1).toString());
        try {
            sentencia=(com.mysql.jdbc.Statement)conexion.createStatement();
            ResultSet rs = sentencia.executeQuery("SELECT * FROM vehiculo");
            while (rs.next()) {
                mod = rs.getInt("id_modelo");
                if(mod==nom){
                    ver2++;
                }
            }
        } catch (SQLException y) {
            msj = "Error con Vehiculo";
            LBL_estado.setText(msj);
        }
        try {
            sentencia=(com.mysql.jdbc.Statement)conexion.createStatement();
            ResultSet sr = sentencia.executeQuery("SELECT * FROM repuestos");
            while (sr.next()) {
                mod2 = sr.getInt("id_modelo");
                if(mod2==nom){
                    ver2++;
                }
            }
        } catch (SQLException s) {
            msj = "Error con Repuestos";
            LBL_estado.setText(msj);
        }
        return ver2;
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

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        JB_cancel = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        cmb_tipo = new javax.swing.JComboBox<>();
        cmb_marc = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        cmb_comb = new javax.swing.JComboBox<>();
        cmb_motor = new javax.swing.JComboBox<>();
        txt_nom = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        LBL_estado = new javax.swing.JLabel();

        jTable1.setModel(modeloTabla);
        jScrollPane1.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel9.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel9.setText("RACAD AUTOMOTRIZ - MODIFICAR O ELIMINAR MODELO");

        JB_cancel.setText("Salir");
        JB_cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JB_cancelActionPerformed(evt);
            }
        });

        jTable2.setModel(modeloTabla);
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable2);

        jLabel4.setText("Tipo :");

        jLabel5.setText("Combustible :");

        jLabel6.setText("Motor :");

        jLabel1.setText("Marca :");

        jLabel2.setText("Nombre Modelo Vehiculo :");

        cmb_comb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmb_combActionPerformed(evt);
            }
        });

        jButton1.setText("Modificar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Eliminar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        LBL_estado.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 495, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel2)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(txt_nom, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGroup(layout.createSequentialGroup()
                                                    .addComponent(jLabel1)
                                                    .addGap(18, 18, 18)
                                                    .addComponent(cmb_marc, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(20, 20, 20)
                                                .addComponent(LBL_estado, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel5)
                                            .addComponent(jLabel4)
                                            .addComponent(jLabel6)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jButton2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jButton1)
                                        .addGap(52, 52, 52)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(cmb_comb, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(cmb_motor, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(cmb_tipo, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(JB_cancel, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(40, 40, 40))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 405, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(40, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(cmb_marc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_nom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)
                        .addComponent(LBL_estado, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(cmb_tipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(cmb_comb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(cmb_motor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JB_cancel)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addGap(14, 14, 14))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void JB_cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JB_cancelActionPerformed
        this.dispose();
    }//GEN-LAST:event_JB_cancelActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (verificar()==0){
            String nom = txt_nom.getText().toUpperCase();
            String tipo = (String) cmb_tipo.getSelectedItem();
            String comb = (String) cmb_comb.getSelectedItem();
            String marc = (String) cmb_marc.getSelectedItem();
            String motor = (String) cmb_motor.getSelectedItem();
            int id = Integer.parseInt(jTable2.getValueAt(jTable2.getSelectedRow(), 1).toString());
            int motor1=0;
            int tipo1=0;
            int comb1=0;
            int marc1=0;
            try {
                sentencia=(com.mysql.jdbc.Statement)conexion.createStatement();
                ResultSet rs = sentencia.executeQuery("SELECT id_tipo_vehiculo FROM tipo_vehiculo WHERE  nombre = '" + tipo + "'");
                while (rs.next()) {
                    tipo1 = rs.getInt("id_tipo_vehiculo");
                }      
            } catch (SQLException s) {
                msj = "Error con tipo";
            }
            try {
                sentencia=(com.mysql.jdbc.Statement)conexion.createStatement();
                ResultSet rs = sentencia.executeQuery("SELECT id_tipo_motor FROM tipo_motor WHERE  nombre = '" + motor + "'");
                while (rs.next()) {
                    motor1 = rs.getInt("id_tipo_motor");
                }      
            } catch (SQLException s) {
                msj = "Error con motor";
            }
            try {
                sentencia=(com.mysql.jdbc.Statement)conexion.createStatement();
                ResultSet rs = sentencia.executeQuery("SELECT id_tipo_combustible FROM tipo_combustible WHERE  nombre = '" + comb + "'");
                while (rs.next()) {
                    comb1 = rs.getInt("id_tipo_combustible");
                }      
            } catch (SQLException s) {
                msj = "Error con combustible";
            }
            try {
                sentencia=(com.mysql.jdbc.Statement)conexion.createStatement();
                ResultSet rs = sentencia.executeQuery("SELECT id_marca FROM marca WHERE  nombre = '" + marc + "'");
                while (rs.next()) {
                    marc1 = rs.getInt("id_marca");
                }      
            } catch (SQLException s) {
                msj = "Error con Cliente";
            }
            String sql = "UPDATE modelo "
                    + "SET id_marca ='" + marc1 + "',"
                    + "nombre = '" + nom + "',"
                    + "id_tipo_vehiculo = '" + tipo1 + "',"
                    + "id_tipo_combustible = '" + comb1 + "',"
                    + "id_tipo_motor = '" + motor1 + "',"
                    + "id_marca = '" + marc1 +"'"
                    + "WHERE id_modelo = '" + id + "'";
            try {
                sentencia.executeUpdate(sql);
                msj = "Datos Guardados";
                LBL_estado.setText(msj);
                txt_nom.setText("");
            } catch (SQLException e) {
                msj = "Item no Ingresado";
                LBL_estado.setText(msj);
            }
        }
        else{
            msj = "Item no Ingresado";
            LBL_estado.setText(msj);
        }
        
        limpiaTabla();
        setFilas();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
          
        llenarcombos();
        
        int mar = 0;
        mar = Integer.parseInt(jTable2.getValueAt(jTable2.getSelectedRow(), 0).toString());
        mar--;
        cmb_marc.setSelectedIndex(mar);
        
        String nom = jTable2.getValueAt(jTable2.getSelectedRow(), 2).toString();
        txt_nom.setText(nom);
        
        int veh = 0;
        veh = Integer.parseInt(jTable2.getValueAt(jTable2.getSelectedRow(), 3).toString());
        veh--;
        cmb_tipo.setSelectedIndex(veh);
        
        int com = 0;
        com = Integer.parseInt(jTable2.getValueAt(jTable2.getSelectedRow(), 4).toString());
        com--;
        cmb_comb.setSelectedIndex(com);
        
        int mot = 0;
        mot = Integer.parseInt(jTable2.getValueAt(jTable2.getSelectedRow(), 5).toString());
        mot--;
        cmb_motor.setSelectedIndex(mot);
        
        
    }//GEN-LAST:event_jTable2MouseClicked

    private void cmb_combActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmb_combActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmb_combActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if (verificar()==0){
            if (verificar2()==0){
                int nom = Integer.parseInt(jTable2.getValueAt(jTable2.getSelectedRow(), 1).toString());
                String sql = "DELETE FROM modelo WHERE id_modelo =" + nom + "";
                try{
                    sentencia = (com.mysql.jdbc.Statement) conexion.createStatement();
                    sentencia.executeUpdate(sql);
                    LBL_estado.setText("Modelo de auto " + nom + " borrado con exito");
                }
                catch(SQLException e){
                    msj="Error al borrar modelo de auto" + nom + "";
                    LBL_estado.setText(msj);
                }
                limpiaTabla();
                setFilas();
            }else{
                msj="Error al borrar modelo, se encuentra en uso!";
                LBL_estado.setText(msj);
            }
        }else{
            msj="No ha seleccionado nada!";
            LBL_estado.setText(msj);
        }   
    }//GEN-LAST:event_jButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(ModEli_modelo_vehiculo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ModEli_modelo_vehiculo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ModEli_modelo_vehiculo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ModEli_modelo_vehiculo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
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
                new ModEli_modelo_vehiculo().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton JB_cancel;
    private javax.swing.JLabel LBL_estado;
    private javax.swing.JComboBox<String> cmb_comb;
    private javax.swing.JComboBox<String> cmb_marc;
    private javax.swing.JComboBox<String> cmb_motor;
    private javax.swing.JComboBox<String> cmb_tipo;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField txt_nom;
    // End of variables declaration//GEN-END:variables
}
