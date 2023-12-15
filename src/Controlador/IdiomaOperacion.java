package Controlador;

import Modelo.Conexion;
import Modelo.Idioma;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;

public class IdiomaOperacion {

    PreparedStatement ps;
    ResultSet rs;
    Connection con;
    Conexion conectar = new Conexion();
    Idioma p = new Idioma();

    public int agregar(Idioma idioma) {  
        int r=0;
        String sql = "INSERT INTO idioma(nombreIdioma, oficial, porcentajePoblacion) VALUES (?, ?, ?)";
        try {
        con = conectar.getConnection();
        ps = con.prepareStatement(sql);

        // Convertir el booleano a 1 o 0 para almacenar en la base de datos
        int idiomaOf = idioma.isIdiomaOficial() ? 1 : 0;
        ps.setString(1, idioma.getIdioma());
        ps.setInt(2, idiomaOf);
        ps.setDouble(3, idioma.getPorcPoblacion()); 

        int resultado = ps.executeUpdate();    
        return resultado == 1 ? 1 : 0;
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        try {
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    return 0;
}
    
   public int modificar(Idioma idioma) {
        int r = 0;
        String sql = "UPDATE idioma SET porcentajePob=?, idiomaOficial=? WHERE nombreIdioma=?";
        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            ps.setDouble(1, idioma.getPorcPoblacion());
            ps.setBoolean(2, idioma.isIdiomaOficial());
            ps.setString(3, idioma.getIdioma());
            r = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Cerrar recursos si es necesario
        }
        return r;
    }
   
    public int eliminar(String nombreIdioma) {
        int r = 0;
        String sql = "DELETE FROM idioma WHERE nombreIdioma=?";
        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, nombreIdioma);
            r = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Cerrar recursos si es necesario
        }
        return r;
    }
    
    void actualizarTabla(DefaultTableModel jTableIdioma) {
       
    }
}
