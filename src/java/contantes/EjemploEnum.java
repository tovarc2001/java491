 // @author Administrador

package contantes;

import com.google.gson.Gson;

public enum EjemploEnum {
    C1("mensaje bienvenida"),
    C2(new String[2],100,false),
    C3;
    //atributos
    private String mensaje;
    private boolean confirmar;
    private int codigo;
    private String[] listaDia;
    //get y set

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public boolean isConfirmar() {
        return confirmar;
    }

    public void setConfirmar(boolean confirmar) {
        this.confirmar = confirmar;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String[] getListaDia() {
        return listaDia;
    }

    public void setListaDia(String[] listaDia) {
        this.listaDia = listaDia;
    }
    
    
    //constructores
    private EjemploEnum(){
        
    }
    private EjemploEnum(String mensaje){
        this.mensaje=mensaje;
    }
    private EjemploEnum( String[] listaDia, int codigo, boolean confirmar){
        this.listaDia=listaDia;
        this.codigo=codigo;
        this.confirmar=confirmar;
    }

    public static void main(String[] args){
        System.out.println(EjemploEnum.C1.mensaje);
        System.out.println(EjemploEnum.C3);
        
        
        String[] lista ={"lunes","jueves"};
        
        EjemploEnum.C2.setListaDia(lista);
        System.out.println(EjemploEnum.C2.listaDia[1]);
        
        Gson gson = new Gson();
        Prueba p = new Prueba(EjemploEnum.C2);
        p.setLista(lista);
        String resultado = gson.toJson(p);
        System.out.println(resultado);
    }
    
} // Fin Clase ejemploEnum 


class Prueba {
    private String [] lista;
    private int codigo;
    private boolean confirmar;

    public String[] getLista() {
        return lista;
    }

    public void setLista(String[] lista) {
        this.lista = lista;
    }
    
    public Prueba(EjemploEnum e){
        this.codigo=e.getCodigo();
        this.confirmar=e.isConfirmar();
        e.setListaDia(lista);
    }
}