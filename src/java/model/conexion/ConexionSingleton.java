/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.conexion;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import static java.lang.Class.forName;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nanimo
 */
public class ConexionSingleton {
    
    public static ConexionSingleton conexionSingleton;
    public static Connection conn;
    
    private ConexionSingleton() {      
        try {
            //Llamar al archivo propiedades
            //donde esta la configuración con la BD
            Properties properties = new Properties();            
            //properties.load(new FileInputStream("src/java/model/conexion/config.properties"));

//            String dns=properties.getProperty("server")+properties.getProperty("bd");
//            String user=properties.getProperty("user");
//            String password=properties.getProperty("password");
            
            
            String dns="jdbc:mysql://localhost:3306/mvcjava";
            String user="root";
            String password="";

            
            //crear conexión
            forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(dns,user,password);
            
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConexionSingleton.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ConexionSingleton.class.getName()).log(Level.SEVERE, null, ex);
        } //catch (FileNotFoundException ex) {
//            Logger.getLogger(ConexionSingleton.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (IOException ex) {
//            Logger.getLogger(ConexionSingleton.class.getName()).log(Level.SEVERE, null, ex);
//        }               
    }
    
    public static Connection obtenerConexionSingleton(){
        if(conexionSingleton==null){
            conexionSingleton = new ConexionSingleton();
            System.out.println("Hasta ahora se creo");
        }else{
           
            System.out.println("Ya se creó");
        }
        return conn;
    }
    public Connection obtenerConexion(){
        return conn;
    }
    
    public void cerrarConexion(){
        
    }
    
    public static void main(String[] args) {
        Connection connection = ConexionSingleton.obtenerConexionSingleton();
        ConexionSingleton.obtenerConexionSingleton();
        ConexionSingleton.obtenerConexionSingleton();
        
    }
    
}
