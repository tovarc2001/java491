package model.conexion;

import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Nanimo
 */
public interface InterfaceCRUD {

    
    public ArrayList<Object> listar();
    public boolean eliminar();
    public boolean registrar();
    public boolean editar();
    public Object consultar();
    
}
