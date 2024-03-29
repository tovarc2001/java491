 // @author Administrador

package constantes;

import java.util.ArrayList;

public enum ConstanteMVC {
    
    //clase usuario
    MENSAJE_ACTUALIZAR_USUARIO(100,"usuario actualizado."),
    MENSAJE_ELIMINAR_USUARIO(101,"usuario eliminado exitosamente"),
    MENSAJE_REGISTRAR_USUARIO(103,"usuario registrado exitosamente"),
    MENSAJE_CONSULTAR_USUARIO(104,"consulta de usuario",new Object()),
    MENSAJE_LISTAR_USUARIO(105,"lista de usuarios", new ArrayList<Object>()),
    MENSAJE_LISTAR_PRODUCTO(106,"listar producto"),
    MENSAJE_USUARIO_REPETIDO(107,"usuario repetido."),
    MENSAJE_INICIAR_SESION(108,"sesión iniciada",new Object()),
    MENSAJE_DESTRUIR_SESION(109,"sesión destruida",new Object()),
    MENSAJE_SESION_FALLIDA(110,"credenciales inválidas, digite nuevamente"),
    
    //clase RecuperarClave
    MENSAJE_CORREO_INVALIDO(200,"correo invalido"),
    MENSAJE_CORREO_ENVIADO(201,"Contraseña recuparada. Revise su correo")
    
    ;
;
    
    //atributos
    private int codigo;
    private String mensaje;
    private Object objeto;
    private ArrayList<Object> lista;

    //get y set
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

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

    
    //constructores
    private ConstanteMVC(int codigo, String mensaje) {
        this.codigo = codigo;
        this.mensaje = mensaje;
    }

    private ConstanteMVC(int codigo, String mensaje, Object objeto) {
        this.codigo = codigo;
        this.mensaje = mensaje;
        this.objeto = objeto;
    }

    private ConstanteMVC(int codigo, String mensaje, ArrayList<Object> lista) {
        this.codigo = codigo;
        this.mensaje = mensaje;
        this.lista = lista;
    }
    
    
    
    
} // Fin Clase ConstanteMVC 
