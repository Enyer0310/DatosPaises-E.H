package Modelo;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    //Configuración BD
    private String ip = "127.0.0.1"; //Ej: 127.0.0.1
    private String bd = "datos";
    private String url="jdbc:mysql://"+ip+":3306/"+bd;
    private String user="root"; //Acá debes incorporar el user del motor de bases de datos.
    private String pass="Bairon.03@"; //Acá debes incorporar la password del motor de bases de datos.  
    
    Connection con;
    public Connection getConnection(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection(url,user,pass);
        } catch (ClassNotFoundException | SQLException e) {            
        }
        return con;
    }
}
