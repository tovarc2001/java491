 // @author Administrador

package model.dao;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import model.vo.UsuarioVO;

public class RecuperarClaveDAO {

   private Properties properties;
    private Session session;
    private MimeMessage message;
   
    
    public boolean validarCorreo(String correo){
        
        UsuarioVO uVO = new UsuarioVO(); 
        uVO.setCorreo(correo);
        UsuarioDAO udao = new UsuarioDAO();
        if(udao.consultarCorreo()!= null){
            return true;
        }               
        return false;
    }
 
    
    
    public boolean recuperarClave(String correo, String mensaje){
       
        try {
            
            if(!this.validarCorreo(correo)){
                return false;
            }
            
            String txtEncabezado = getClass().getResource("encabezado.txt").getPath();
            String txtPieDePagina = getClass().getResource("pieDePagina.txt").getPath();
            
            
            BufferedReader leerEncabezado = new BufferedReader(new FileReader(txtEncabezado));
            BufferedReader leerPieDePagina = new BufferedReader(new FileReader(txtPieDePagina));
            
            String mensajeFinal="";
            String linea;
            while((linea=leerEncabezado.readLine())!= null){
                mensajeFinal+=linea;
            }
            mensajeFinal+="1wuhfgsdfkjghkdfg<br>";
            while((linea=leerPieDePagina.readLine())!= null){
                mensajeFinal+=linea;
            }
            
            
            String ruta = getClass().getResource("email.properties").getPath();
            properties = new Properties();
            properties.load(new FileInputStream(ruta));
           
            session = Session.getDefaultInstance(properties, null);
            message = new MimeMessage(session);
            message.setSubject("Recuperar clave");
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(correo));
            message.setContent(mensajeFinal, "text/html");
           
            Transport transport = session.getTransport("smtp");
            transport.connect(properties.getProperty("server"), properties.getProperty("correo"), properties.getProperty("clave"));
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
            
            return true;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(RecuperarClaveDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (IOException ioex) {
            Logger.getLogger(RecuperarClaveDAO.class.getName()).log(Level.SEVERE, null, ioex);
        } catch (MessagingException ex) {
            Logger.getLogger(RecuperarClaveDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
       return false;
    }
   
    public static void main (String[] args){
        RecuperarClaveDAO clavedao = new RecuperarClaveDAO();
        clavedao.recuperarClave("javamailersena@gmail.com", "que se dice");
    }
    
} // Fin Clase RecuperarClaveDAO 
