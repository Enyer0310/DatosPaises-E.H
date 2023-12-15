package Controlador;

import Modelo.Conexion;
import Modelo.Ciudad;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;

public class CiudadOperacion {

    PreparedStatement ps;
    ResultSet rs;
    Connection con;
    Conexion conectar = new Conexion();
    Ciudad p = new Ciudad();

    public int agregar(Ciudad ciudad) {  
        int r=0;
        String sql="insert into ciudad(nombreCiudad,paisPert,distritoCiudad,poblacionCiudad)values(?,?,?,?)";
        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);            
            ps.setString(1,ciudad.getNombre());
            ps.setString(2,ciudad.getPaisPert());
            ps.setString(3,ciudad.getDistrito());
            ps.setString(4,ciudad.getPoblacion());
            r=ps.executeUpdate();    
            if(r==1){
                return 1;
            }
            else{
                return 0;
            }
        } catch (Exception e) {
        }  
        return r;
    }
     public int modificar(Ciudad ciudad) {
        int r = 0;
        String sql = "UPDATE ciudad SET nombreCiudad=?, distritoCiudad=?, poblacionCiudad=? WHERE paisPert=?";
        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, ciudad.getNombre());
            ps.setString(2, ciudad.getDistrito());
            ps.setString(3, ciudad.getPoblacion());
            ps.setString(4, ciudad.getPaisPert());
            r = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
        return r;
    }
    public int eliminar(String nombreCiudad) {
        int r = 0;
        String sql = "DELETE FROM ciudad WHERE nombreCiudad=?";
        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, nombreCiudad);
            r = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Cerrar recursos si es necesario
        }
        return r;
    }

    void actualizarTabla(DefaultTableModel jTableCiudad) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
