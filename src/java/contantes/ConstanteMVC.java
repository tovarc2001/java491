 // @author Administrador

package contantes;
public enum ConstanteMVC {
    MENSAJE_ACTUALIZAR_USUARIO(100,"usuario actualizado."),
    MENSAJE_ELIMINAR_USUARIO(101,"usuario eliminado exitosamente"),
    MENSAJE_REGISTRAR_USUARIO(102,"usuario registrado exitosamente");
    private int codigo;
    private String mensaje;

    private ConstanteMVC(int codigo, String mensaje) {
        this.codigo = codigo;
        this.mensaje = mensaje;
    }
    
    
} // Fin Clase ConstanteMVC 
