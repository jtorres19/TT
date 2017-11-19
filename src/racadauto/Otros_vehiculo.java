package racadauto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class Otros_vehiculo extends javax.swing.JFrame {
    private Statement sentencia;
    private Connection conexion;
    private String nomBD = "racad";
    private String usuario = "root";
    private String password = "";
    private String msj;
    
    public Otros_vehiculo() {
        initComponents();
        conectar();
        llenarcombos();
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
    
    public int verificar1(){
        int ver1 = 0;
        String nom = (String) cmb_tipo.getSelectedItem();
        int id = 0;
        int id2 = 0;
        try {
            sentencia = (com.mysql.jdbc.Statement) conexion.createStatement();
            ResultSet rs = sentencia.executeQuery("SELECT id_tipo_vehiculo FROM tipo_vehiculo WHERE nombre = '" + nom + "'");
            while (rs.next()) {
                id = rs.getInt("id_tipo_vehiculo");
            }
        } catch (SQLException eg) {
            msj = "Error con su Solicitud";
        }
        try {
            sentencia = (com.mysql.jdbc.Statement) conexion.createStatement();
            ResultSet rs = sentencia.executeQuery("SELECT id_tipo_vehiculo FROM modelo");
            while (rs.next()) {
                id2 = rs.getInt("id_tipo_vehiculo");
                if (id == id2) {
                    ver1 ++;
                }
            }
        } catch (SQLException t) {
            msj = "Error con su Solicitud";
        }
        return ver1;
    }
    
    public int verificar2(){
        int ver1 = 0;
        String nom = (String) cmb_comb.getSelectedItem();
        int id = 0;
        int id2 = 0;
        try {
            sentencia = (com.mysql.jdbc.Statement) conexion.createStatement();
            ResultSet rs = sentencia.executeQuery("SELECT id_tipo_combustible FROM tipo_combustible WHERE nombre = '" + nom + "'");
            while (rs.next()) {
                id = rs.getInt("id_tipo_combustible");
            }
        } catch (SQLException eg) {
            msj = "Error con su Solicitud";
        }
        try {
            sentencia = (com.mysql.jdbc.Statement) conexion.createStatement();
            ResultSet rs = sentencia.executeQuery("SELECT id_tipo_combustible FROM modelo");
            while (rs.next()) {
                id2 = rs.getInt("id_tipo_combustible");
                if (id == id2) {
                    ver1 ++;
                }
            }
        } catch (SQLException t) {
            msj = "Error con su Solicitud";
        }
        return ver1;
    }
    
    public int verificar3(){
        int ver1 = 0;
        String nom = (String) cmb_motor.getSelectedItem();
        int id = 0;
        int id2 = 0;
        try {
            sentencia = (com.mysql.jdbc.Statement) conexion.createStatement();
            ResultSet rs = sentencia.executeQuery("SELECT id_tipo_motor FROM tipo_motor WHERE nombre = '" + nom + "'");
            while (rs.next()) {
                id = rs.getInt("id_tipo_motor");
            }
        } catch (SQLException eg) {
            msj = "Error con su Solicitud";
        }
        try {
            sentencia = (com.mysql.jdbc.Statement) conexion.createStatement();
            ResultSet rs = sentencia.executeQuery("SELECT id_tipo_motor FROM modelo");
            while (rs.next()) {
                id2 = rs.getInt("id_tipo_motor");
                if (id == id2) {
                    ver1 ++;
                }
            }
        } catch (SQLException t) {
            msj = "Error con su Solicitud";
        }
        return ver1;
    }
    
    public int verificar4(){
        int ver1 = 0;
        String nom = (String) cmb_marc.getSelectedItem();
        int id = 0;
        int id2 = 0;
        int id3 = 0;
        try {
            sentencia = (com.mysql.jdbc.Statement) conexion.createStatement();
            ResultSet rs = sentencia.executeQuery("SELECT id_marca FROM marca WHERE nombre = '" + nom + "'");
            while (rs.next()) {
                id = rs.getInt("id_marca");
            }
        } catch (SQLException eg) {
            msj = "Error con su Solicitud";
        }
        try {
            sentencia = (com.mysql.jdbc.Statement) conexion.createStatement();
            ResultSet rs = sentencia.executeQuery("SELECT id_marca FROM modelo");
            while (rs.next()) {
                id2 = rs.getInt("id_marca");
                if (id == id2) {
                    ver1 ++;
                }
            }
        } catch (SQLException t) {
            msj = "Error con su Solicitud";
        }
        //aunque debería ser imposible que vehiculo lo tenga sin que modelo lo haya tenido antes
        try {
            sentencia = (com.mysql.jdbc.Statement) conexion.createStatement();
            ResultSet rs = sentencia.executeQuery("SELECT id_marca FROM vehiculo");
            while (rs.next()) {
                id3 = rs.getInt("id_marca");
                if (id == id2) {
                    ver1 ++;
                }
            }
        } catch (SQLException t) {
            msj = "Error con su Solicitud";
        }
        return ver1;
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

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField3 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txt_marc = new javax.swing.JTextField();
        cmb_marc = new javax.swing.JComboBox<>();
        cmb_tipo = new javax.swing.JComboBox<>();
        cmb_comb = new javax.swing.JComboBox<>();
        txt_comb = new javax.swing.JTextField();
        txt_tipo = new javax.swing.JTextField();
        JB_cancel = new javax.swing.JButton();
        btn_del_marc = new javax.swing.JButton();
        btn_add_marc = new javax.swing.JButton();
        btn_add_model = new javax.swing.JButton();
        btn_mod_del_model = new javax.swing.JButton();
        btn_del_tipo = new javax.swing.JButton();
        btn_add_tipo = new javax.swing.JButton();
        btn_del_comb = new javax.swing.JButton();
        btn_add_comb = new javax.swing.JButton();
        btn_del_motor = new javax.swing.JButton();
        btn_add_motor = new javax.swing.JButton();
        cmb_motor = new javax.swing.JComboBox<>();
        txt_motor = new javax.swing.JTextField();
        LBL_estado = new javax.swing.JLabel();

        jTextField3.setText("jTextField3");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel9.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel9.setText("RACAD AUTOMOTRIZ - ELIMINAR Y AGREGAR OTROS");

        JB_cancel.setText("Salir");
        JB_cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JB_cancelActionPerformed(evt);
            }
        });

        btn_del_marc.setText("Eliminar Marca ->");
        btn_del_marc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_del_marcActionPerformed(evt);
            }
        });

        btn_add_marc.setText("Agregar Marca ->");
        btn_add_marc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_add_marcActionPerformed(evt);
            }
        });

        btn_add_model.setText("Agregar Modelo");
        btn_add_model.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_add_modelActionPerformed(evt);
            }
        });

        btn_mod_del_model.setText("Modificar o Eliminar Modelo");
        btn_mod_del_model.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_mod_del_modelActionPerformed(evt);
            }
        });

        btn_del_tipo.setText("Eliminar Tipo ->");
        btn_del_tipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_del_tipoActionPerformed(evt);
            }
        });

        btn_add_tipo.setText("Agregar Tipo ->");
        btn_add_tipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_add_tipoActionPerformed(evt);
            }
        });

        btn_del_comb.setText("Eliminar Combustible ->");
        btn_del_comb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_del_combActionPerformed(evt);
            }
        });

        btn_add_comb.setText("Agregar Combustible ->");
        btn_add_comb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_add_combActionPerformed(evt);
            }
        });

        btn_del_motor.setText("Eliminar Motor ->");
        btn_del_motor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_del_motorActionPerformed(evt);
            }
        });

        btn_add_motor.setText("Agregar Motor ->");
        btn_add_motor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_add_motorActionPerformed(evt);
            }
        });

        txt_motor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_motorActionPerformed(evt);
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
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 476, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 70, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btn_add_model, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(btn_del_motor, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btn_add_marc, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE)
                                    .addComponent(btn_del_marc, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btn_add_motor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_motor)
                                    .addComponent(cmb_motor, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cmb_marc, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txt_marc)))
                            .addComponent(btn_mod_del_model, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(btn_add_comb, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(btn_del_comb, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(btn_add_tipo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(btn_del_tipo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txt_tipo)
                                            .addComponent(cmb_comb, 0, 100, Short.MAX_VALUE)
                                            .addComponent(txt_comb)
                                            .addComponent(cmb_tipo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                    .addComponent(LBL_estado, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap())
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(JB_cancel, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20))))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel9)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btn_add_comb)
                        .addComponent(txt_comb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btn_del_tipo)
                                    .addComponent(cmb_tipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btn_add_tipo)
                                    .addComponent(txt_tipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btn_del_marc)
                                    .addComponent(cmb_marc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btn_add_marc)
                                    .addComponent(txt_marc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btn_del_motor)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(btn_add_motor)
                                    .addComponent(txt_motor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btn_del_comb)
                                .addComponent(cmb_comb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cmb_motor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(LBL_estado, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btn_mod_del_model)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JB_cancel)
                    .addComponent(btn_add_model))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void JB_cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JB_cancelActionPerformed
        this.dispose();
    }//GEN-LAST:event_JB_cancelActionPerformed

    private void btn_del_tipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_del_tipoActionPerformed
        String nom = (String) cmb_tipo.getSelectedItem();
        String sql = "DELETE FROM tipo_vehiculo WHERE nombre = '" + nom + "'";
        if (verificar1() == 0){
            try {
                sentencia.executeUpdate(sql);
                msj = "Tipo " + nom + " borrado!";
                LBL_estado.setText(msj);
            } catch (SQLException e) {
                msj = "Error al Borrar";
                LBL_estado.setText(msj);
            }
        }else{
            msj = "No se puede borrar, tipo en uso!";
            LBL_estado.setText(msj);
        }    
        llenarcombos();
    }//GEN-LAST:event_btn_del_tipoActionPerformed

    private void btn_add_tipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_add_tipoActionPerformed
        int id =0,i=0;
        String nom = txt_tipo.getText().toUpperCase();
        String nom2 = "";
        
        try {
            sentencia=(com.mysql.jdbc.Statement)conexion.createStatement();
            ResultSet rs = sentencia.executeQuery("SELECT nombre FROM tipo_vehiculo");
            while (rs.next()) {
                nom2 = rs.getString("nombre");
            }  
        } catch (SQLException eg) {
            msj = "Error con su Solicitud";
        }
        if (txt_tipo.getText().equals(nom2)) {
                    JOptionPane.showMessageDialog(null,
                    "Error, ya existe Tipo!", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
                    i++;
                    }
        if (txt_tipo.getText().equals("")) {
                    JOptionPane.showMessageDialog(null,
                    "Error, Debe ingresar algo!", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
                    i++;
                    }
        if (txt_tipo.getText().length() >= 16) {
                    JOptionPane.showMessageDialog(null,
                    "Error, Tipo no puede tener más de 15 caracteres!", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
                    i++;
                    }
        if (i==0){
            try {
                sentencia=(com.mysql.jdbc.Statement)conexion.createStatement();
                ResultSet rs = sentencia.executeQuery("SELECT MAX(id_tipo_vehiculo) as id_tipo_vehiculo FROM tipo_vehiculo");
                while (rs.next()) {
                    id = rs.getInt("id_tipo_vehiculo");
                }
                id ++;
            } catch (SQLException f) {
                msj = "Error con Codigo";
            }
            String sql = "INSERT INTO tipo_vehiculo(id_tipo_vehiculo, nombre) VALUES (" + id + ",'" + nom + "')";
            try {
                sentencia.executeUpdate(sql);
                msj = "Datos Guardados";
                LBL_estado.setText(msj);
            } catch (SQLException e) {
                msj = "Item no Ingresado";
                LBL_estado.setText(msj);
            }
        }
        txt_tipo.setText("");
    }//GEN-LAST:event_btn_add_tipoActionPerformed

    private void btn_del_combActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_del_combActionPerformed
        String nom = (String) cmb_comb.getSelectedItem();
        String sql = "DELETE FROM tipo_combustible WHERE nombre = '" + nom + "'";
        if (verificar2() == 0){
            try {
                sentencia.executeUpdate(sql);
                msj = "Combustible " + nom + " borrado!";
                LBL_estado.setText(msj);
            } catch (SQLException e) {
                msj = "Error al Borrar";
                LBL_estado.setText(msj);
            }
        }else{
            msj = "No se puede borrar, combustible en uso!";
            LBL_estado.setText(msj);
        }
        llenarcombos();
    }//GEN-LAST:event_btn_del_combActionPerformed

    private void btn_add_combActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_add_combActionPerformed
        int id =0,i=0;
        String nom = txt_comb.getText().toUpperCase();
        String nom2 = "";
        
        try {
            sentencia=(com.mysql.jdbc.Statement)conexion.createStatement();
            ResultSet rs = sentencia.executeQuery("SELECT nombre FROM tipo_combustible");
            while (rs.next()) {
                nom2 = rs.getString("nombre");
            }  
        } catch (SQLException eg) {
            msj = "Error con su Solicitud";
        }
        if (txt_comb.getText().equals(nom2)) {
                    JOptionPane.showMessageDialog(null,
                    "Error, ya existe Combustible!", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
                    i++;
                    }
        if (txt_comb.getText().equals("")) {
                    JOptionPane.showMessageDialog(null,
                    "Error, Debe ingresar algo!", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
                    i++;
                    }
        if (txt_comb.getText().length() >= 16) {
                    JOptionPane.showMessageDialog(null,
                    "Error, Combustible no puede tener más de 15 caracteres!", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
                    i++;
                    }
        if (i==0){
            try {
                sentencia=(com.mysql.jdbc.Statement)conexion.createStatement();
                ResultSet rs = sentencia.executeQuery("SELECT MAX(id_tipo_combustible) as id_tipo_combustible FROM tipo_combustible");
                while (rs.next()) {
                    id = rs.getInt("id_tipo_combustible");
                }
                id ++;
            } catch (SQLException f) {
                msj = "Error con Codigo";
            }
            String sql = "INSERT INTO tipo_combustible(id_tipo_combustible, nombre) VALUES (" + id + ",'" + nom + "')";
            try {
                sentencia.executeUpdate(sql);
                msj = "Datos Guardados";
                LBL_estado.setText(msj);
            } catch (SQLException e) {
                msj = "Item no Ingresado";
                LBL_estado.setText(msj);
            }
        }
        txt_comb.setText("");
    }//GEN-LAST:event_btn_add_combActionPerformed

    private void btn_del_motorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_del_motorActionPerformed
        String nom = (String) cmb_motor.getSelectedItem();
        String sql = "DELETE FROM tipo_motor WHERE nombre = '" + nom + "'";
        if (verificar3() == 0){
            try {
                sentencia.executeUpdate(sql);
                msj = "Motor " + nom + " borrado!";
                LBL_estado.setText(msj);
            } catch (SQLException e) {
                msj = "Error al Borrar";
                LBL_estado.setText(msj);
            }
        }else{
            msj = "No se puede borrar, Motor en uso!";
            LBL_estado.setText(msj);
        }    
        llenarcombos();
    }//GEN-LAST:event_btn_del_motorActionPerformed

    private void txt_motorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_motorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_motorActionPerformed

    private void btn_mod_del_modelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_mod_del_modelActionPerformed
        ModEli_modelo_vehiculo v2=new ModEli_modelo_vehiculo();
        v2.setVisible(true);
    }//GEN-LAST:event_btn_mod_del_modelActionPerformed

    private void btn_del_marcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_del_marcActionPerformed
        String nom = (String) cmb_marc.getSelectedItem();
        String sql = "DELETE FROM marca WHERE nombre = '" + nom + "'";
        if (verificar4() == 0){
            try {
                sentencia.executeUpdate(sql);
                msj = "Marca " + nom + " borrada!";
                LBL_estado.setText(msj);
            } catch (SQLException e) {
                msj = "Error al Borrar";
                LBL_estado.setText(msj);
            }
        }else{
            msj = "No se puede borrar, Marca en uso!";
            LBL_estado.setText(msj);
        }
        llenarcombos();
    }//GEN-LAST:event_btn_del_marcActionPerformed

    private void btn_add_marcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_add_marcActionPerformed
        int id =0,i=0;
        String nom = txt_marc.getText().toUpperCase();
        String nom2 = "";
        
        try {
            sentencia=(com.mysql.jdbc.Statement)conexion.createStatement();
            ResultSet rs = sentencia.executeQuery("SELECT nombre FROM marca");
            while (rs.next()) {
                nom2 = rs.getString("nombre");
            }  
        } catch (SQLException eg) {
            msj = "Error con su Solicitud";
        }
        if (txt_marc.getText().equals(nom2)) {
                    JOptionPane.showMessageDialog(null,
                    "Error, ya existe Marca!", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
                    i++;
                    }
        if (txt_marc.getText().equals("")) {
                    JOptionPane.showMessageDialog(null,
                    "Error, Debe ingresar algo!", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
                    i++;
                    }
        if (txt_marc.getText().length() >= 16) {
                    JOptionPane.showMessageDialog(null,
                    "Error, Marca no puede tener más de 15 caracteres!", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
                    i++;
                    }
        if (i==0){
            try {
                sentencia=(com.mysql.jdbc.Statement)conexion.createStatement();
                ResultSet rs = sentencia.executeQuery("SELECT MAX(id_marca) as id_marca FROM marca");
                while (rs.next()) {
                    id = rs.getInt("id_marca");
                }
                id ++;
            } catch (SQLException f) {
                msj = "Error con Codigo";
            }
            String sql = "INSERT INTO marca(id_marca, nombre) VALUES (" + id + ",'" + nom + "')";
            try {
                sentencia.executeUpdate(sql);
                msj = "Datos Guardados";
                LBL_estado.setText(msj);
            } catch (SQLException e) {
                msj = "Item no Ingresado";
                LBL_estado.setText(msj);
            }
        }
        txt_marc.setText("");
    }//GEN-LAST:event_btn_add_marcActionPerformed

    private void btn_add_motorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_add_motorActionPerformed
        int id =0,i=0;
        String nom = txt_motor.getText().toUpperCase();
        String nom2 = "";
        
        try {
            sentencia=(com.mysql.jdbc.Statement)conexion.createStatement();
            ResultSet rs = sentencia.executeQuery("SELECT nombre FROM tipo_motor");
            while (rs.next()) {
                nom2 = rs.getString("nombre");
            }  
        } catch (SQLException eg) {
            msj = "Error con su Solicitud";
        }
        if (txt_motor.getText().equals(nom2)) {
                    JOptionPane.showMessageDialog(null,
                    "Error, ya existe Motor!", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
                    i++;
                    }
        if (txt_motor.getText().equals("")) {
                    JOptionPane.showMessageDialog(null,
                    "Error, Debe ingresar algo!", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
                    i++;
                    }
        if (txt_motor.getText().length() >= 16) {
                    JOptionPane.showMessageDialog(null,
                    "Error, Motor no puede tener más de 15 caracteres!", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
                    i++;
                    }
        if (i==0){
            try {
                sentencia=(com.mysql.jdbc.Statement)conexion.createStatement();
                ResultSet rs = sentencia.executeQuery("SELECT MAX(id_tipo_motor) as id_tipo_motor FROM tipo_motor");
                while (rs.next()) {
                    id = rs.getInt("id_tipo_motor");
                }
                id ++;
            } catch (SQLException f) {
                msj = "Error con Codigo";
            }
            String sql = "INSERT INTO tipo_motor(id_tipo_motor, nombre) VALUES (" + id + ",'" + nom + "')";
            try {
                sentencia.executeUpdate(sql);
                msj = "Datos Guardados";
                LBL_estado.setText(msj);
            } catch (SQLException e) {
                msj = "Item no Ingresado";
                LBL_estado.setText(msj);
            }
        }
        txt_motor.setText("");
    }//GEN-LAST:event_btn_add_motorActionPerformed

    private void btn_add_modelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_add_modelActionPerformed
        Ingresar_modelo_vehiculo v1=new Ingresar_modelo_vehiculo();
        v1.setVisible(true);
    }//GEN-LAST:event_btn_add_modelActionPerformed

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
            java.util.logging.Logger.getLogger(Otros_vehiculo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Otros_vehiculo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Otros_vehiculo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Otros_vehiculo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Otros_vehiculo().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton JB_cancel;
    private javax.swing.JLabel LBL_estado;
    private javax.swing.JButton btn_add_comb;
    private javax.swing.JButton btn_add_marc;
    private javax.swing.JButton btn_add_model;
    private javax.swing.JButton btn_add_motor;
    private javax.swing.JButton btn_add_tipo;
    private javax.swing.JButton btn_del_comb;
    private javax.swing.JButton btn_del_marc;
    private javax.swing.JButton btn_del_motor;
    private javax.swing.JButton btn_del_tipo;
    private javax.swing.JButton btn_mod_del_model;
    private javax.swing.JComboBox<String> cmb_comb;
    private javax.swing.JComboBox<String> cmb_marc;
    private javax.swing.JComboBox<String> cmb_motor;
    private javax.swing.JComboBox<String> cmb_tipo;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField txt_comb;
    private javax.swing.JTextField txt_marc;
    private javax.swing.JTextField txt_motor;
    private javax.swing.JTextField txt_tipo;
    // End of variables declaration//GEN-END:variables
}
