package Controlador;

import Modelo.Conexion;
import Modelo.Pais;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class PaisOperaciones {

    PreparedStatement ps;
    ResultSet rs;
    Connection con;
    Conexion conectar = new Conexion();
    Pais p = new Pais();

    public int agregar(Pais pais) {  
        int r=0;
        String sql="insert into pais(codigoPais,nombrePais,continentePais,poblacionPais, tipoGobierno)values(?,?,?,?,1)";
        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);            
            ps.setString(1,pais.getCodigo());
            ps.setString(2,pais.getNombre());
            ps.setString(3,pais.getContinente());
            ps.setInt(4,pais.getPoblacion());
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
    public int modificar(Pais pais) {  
    int r = 0;
    String sql = "UPDATE pais SET nombrePais=?, continentePais=?, poblacionPais=?, tipoGobierno=1 WHERE codigoPais=?";
    
    try {
        con = conectar.getConnection();
        ps = con.prepareStatement(sql);
        ps.setString(1, pais.getNombre());
        ps.setString(2, pais.getContinente());
        ps.setInt(3, pais.getPoblacion());
        ps.setString(4, pais.getCodigo());
        
        r = ps.executeUpdate();
        
        if (r == 1) {
            return 1;
        } else {
            return 0;
        }
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        // Cerrar recursos aquí si es necesario
    }
    
    return r;
}
    
    public int eliminar(String codigo) {
    int r = 0;
    String sql = "DELETE FROM pais WHERE codigoPais=?";
    
    try {
        con = conectar.getConnection();
        ps = con.prepareStatement(sql);
        ps.setString(1, codigo);
        
        r = ps.executeUpdate();
        
        if (r == 1) {
            return 1;
        } else {
            return 0;
        }
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        // Cerrar recursos aquí si es necesario
    }
    
    return r;
}
    
    private void cerrarRecursos() {
        try {
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void actualizarTabla(DefaultTableModel modelo) {
    // Obtén los datos actualizados de la base de datos
    List<Pais> listaPaises = obtenerListaPaises();

    // Limpia el modelo de la tabla
    modelo.setRowCount(0);

    // Llena la tabla con los datos actualizados
    for (Pais pais : listaPaises) {
        Object[] fila = {pais.getCodigo(), pais.getNombre(), pais.getContinente(), pais.getPoblacion()};
        modelo.addRow(fila);
    }
}

   List<Pais> obtenerListaPaises() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
