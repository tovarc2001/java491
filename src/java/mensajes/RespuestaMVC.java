 // @author Administrador

package mensajes;

import com.google.gson.Gson;
import contantes.ConstanteMVC;
import java.util.ArrayList;
import model.vo.UsuarioVO;

public class RespuestaMVC {

    private int codigo;
    private String mensaje;
    private Object objeto;
    private ArrayList<Object> lista;

    public Object getObjeto() {
        return objeto;
    }

    public void setObjeto(Object objeto) {
        this.objeto = objeto;
    }

    public ArrayList<Object> getLista() {
        return lista;
    }

    public void setLista(ArrayList<Object> lista) {
        this.lista = lista;
    }
    
    
    
    public RespuestaMVC(){
        
    }
    public RespuestaMVC(ArrayList<Object> lista){
        this.lista=lista;
    }
    public RespuestaMVC(Object objeto){
        this.objeto=objeto;
    }
    public void parametrizarMensaje(ConstanteMVC cmvc){
        this.codigo=cmvc.getCodigo();
        this.mensaje=cmvc.getMensaje();
        cmvc.setLista(lista);
        cmvc.setObjeto(objeto);
    }
    
    
    public static void main(String[] args) {
        Gson gson= new Gson();
        RespuestaMVC rmvc = new RespuestaMVC();
        rmvc.parametrizarMensaje(ConstanteMVC.MENSAJE_ELIMINAR_USUARIO);
        System.out.println(gson.toJson(rmvc));
        
        UsuarioVO usuarioVO = new UsuarioVO();
        usuarioVO.setCedula(1111);
        usuarioVO.setNombre("brayan");
        usuarioVO.setApellido("Pallares");
        usuarioVO.setRol("admin");
      
        rmvc.setObjeto(usuarioVO);
        rmvc.parametrizarMensaje(ConstanteMVC.MENSAJE_CONSULTAR_USUARIO);
        System.out.println(gson.toJson(rmvc));
        
    }
    
} // Fin Clase RespuestaMVC 
