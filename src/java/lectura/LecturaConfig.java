 // @author Administrador

package lectura;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LecturaConfig {

    public void leerPropiedades(){
        try {
            Properties properties = new Properties();
            String ruta= getClass().getResource("config.properties").getPath();
            System.out.println(ruta);
            properties.load(new FileInputStream(ruta));
            System.out.println(properties.getProperty("driver"));
        } catch (IOException ex) {
            Logger.getLogger(LecturaConfig.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void main(String[] args) {
        LecturaConfig config = new LecturaConfig();
        config.leerPropiedades();
            
    }
    
} // Fin Clase LecturaConfig 
