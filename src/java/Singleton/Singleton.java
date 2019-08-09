 // @author Administrador

package Singleton;

//única ejemplicación o instanciación
/*
1. Constructor debe ser privado
2. Atributo estático que se retorna
3. Validación si el objeto esta creado getInstance(), debe ser estático;
*/
public class Singleton {

    public static Singleton singleton;
    private Singleton(){
        System.out.println("se acaba de crear el objeto singleton");
    }
    public static Singleton getInstance(){
        if(singleton==null){
            singleton= new Singleton();            
        }else{
            System.out.println("objeto singleton creado");
        }
        return singleton;        
    }

    
} // Fin Clase Singleton 
