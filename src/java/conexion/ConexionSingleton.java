 // @author Administrador
package conexion;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import static java.lang.Class.forName;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConexionSingleton {

    private static Connection conn;

    private ConexionSingleton() {
        try {
            //leer configuración del archivo properties
            Properties properties = new Properties();
            String ruta = getClass().getResource("config.properties").getPath();
            properties.load(new FileInputStream(ruta));
            //crear conexión
            forName(properties.getProperty("driver"));
            String dns = properties.getProperty("server") + properties.getProperty("bd");
            conn = DriverManager.getConnection(dns, properties.getProperty("user"), properties.getProperty("password"));

        } catch (IOException ex) {
            Logger.getLogger(ConexionSingleton.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConexionSingleton.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ConexionSingleton.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static Connection obtenerInstancia() {
        if(conn==null){
            new ConexionSingleton();
            System.out.println("Crear objeto");
        }else{
            System.out.println("Objeto creado");
        }
        return conn;
    }

    public static void main(String[] args){
        try {
            Connection conn = ConexionSingleton.obtenerInstancia();
            PreparedStatement ps= conn.prepareStatement("select * from usuario where 1");
            ResultSet resultSet = ps.executeQuery();
            while(resultSet.next()){
                System.out.println(resultSet.getString("nombre"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConexionSingleton.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

} // Fin Clase ConexionSingleton 
