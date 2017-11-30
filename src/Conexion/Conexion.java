package Conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

public class Conexion {
    
    Connection conexion;
    
    public  Connection getConnection(){
        try {
            
            Class.forName("com.mysql.jdbc.Driver");
            conexion = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/racad", "root", "");
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error al Conectar","Conexion",JOptionPane.ERROR_MESSAGE);
        }
        
        return conexion;
    }
    
}
