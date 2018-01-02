/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author JTorres
 */
public class sqlTrabajador extends Conexion {

    Conexion con = new Conexion();
    Connection cn = (Connection) con.getConnection();

    public int existeTrabajador(String rut) {

        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "SELECT COUNT(rut_trabajador) FROM trabajador WHERE rut_trabajador = ?";

        try {
            ps = cn.prepareStatement(sql);
            ps.setString(1, rut);
            rs = ps.executeQuery();

            if (rs.next()) {
                
                return rs.getInt(1);
                
            }

            return 1;

        } catch (SQLException ex) {
            Logger.getLogger(sqlTrabajador.class.getName()).log(Level.SEVERE, null, ex);
            return 1;
        }
    }

    public boolean login(Trabajador usr) {

        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "SELECT t.rut_trabajador,t.nombre,t.ape_paterno,t.ape_materno,t.contraseña,t.id_cargo,c.nombre FROM trabajador t, cargo c "
                + "WHERE rut_trabajador = ? AND t.id_cargo = c.id_cargo";

        try {
            ps = cn.prepareStatement(sql);
            ps.setString(1, usr.getRut());
            rs = ps.executeQuery();

            if (rs.next()) {
                if (usr.getContraseña().equals(rs.getString(5))) {
                    usr.setRut(rs.getString(1));
                    usr.setNombre(rs.getString(2));
                    usr.setPaterno(rs.getString(3));
                    usr.setMaterno(rs.getString(4));
                    usr.setContraseña(rs.getString(5));
                    usr.setId_cargo(rs.getInt(6));
                    usr.setCargo(rs.getString(7));
                    return true;
                } else {
                    return false;
                }
            }

            return false;

        } catch (SQLException ex) {
            Logger.getLogger(sqlTrabajador.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
}
