/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.conexion.ConexionSingleton;
import model.conexion.InterfaceCRUD;
import model.vo.UsuarioVO;

/**
 *
 * @author Nanimo
 */
public class UsuarioDAO implements InterfaceCRUD {

    private UsuarioVO usuarioVO;
    private ArrayList<UsuarioVO> listaUsuarioVO;
    private Connection conn;
    //get y set

    public UsuarioVO getUsuarioVO() {
        return usuarioVO;
    }

    public void setUsuarioVO(UsuarioVO usuarioVO) {
        this.usuarioVO = usuarioVO;
    }

    public ArrayList<UsuarioVO> getListaUsuarioVO() {
        return listaUsuarioVO;
    }

    public void setListaUsuarioVO(ArrayList<UsuarioVO> listaUsuarioVO) {
        this.listaUsuarioVO = listaUsuarioVO;
    }

    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }

    //sobrecarga de constructores
    public UsuarioDAO() {
        ConexionSingleton conexionSingleton = null;
        conn = conexionSingleton.obtenerConexionSingleton();
        listaUsuarioVO = new ArrayList<UsuarioVO>();
    }

    public UsuarioDAO(UsuarioVO usuarioVO) {
        ConexionSingleton conexionSingleton = null;
        conn = conexionSingleton.obtenerConexionSingleton();
        usuarioVO = usuarioVO;
        listaUsuarioVO = new ArrayList<UsuarioVO>();
    }

    @Override
    public ArrayList<Object> listar() {
        try {
            String consulta = "SELECT * FROM usuario WHERE 1";
            PreparedStatement ps = conn.prepareStatement(consulta);
            ResultSet resultado = ps.executeQuery();

            while (resultado.next()) {
                UsuarioVO temporal = new UsuarioVO();
                temporal.setCedula(resultado.getLong("cedula"));
                temporal.setNombre(resultado.getString("nombre"));
                temporal.setApellido(resultado.getString("apellido"));
                temporal.setRol(resultado.getString("rol"));
                listaUsuarioVO.add(temporal);
            }
            return (ArrayList<Object>) (Object) listaUsuarioVO;
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public boolean eliminar() {
        try {
            String consulta = "DELETE * FROM usuario WHERE cedula=?";
            PreparedStatement ps = conn.prepareStatement(consulta);
            ps.setLong(1, usuarioVO.getCedula());
            ps.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean registrar() {
        try {
            Object consultar = consultar();
            if (consultar == null) {
                String consulta = "INSERT INTO usuario (cedula, nombre, apellido, rol) values(?,?,?,?)";
                PreparedStatement ps = conn.prepareStatement(consulta);
                ps.setLong(1, usuarioVO.getCedula());
                ps.setString(2, usuarioVO.getNombre());
                ps.setString(3, usuarioVO.getApellido());
                ps.setString(4, usuarioVO.getRol());
                ps.execute();
                return true;
            }else{
                return false;
            }

        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean editar() {
        try {
            String consulta = "UPDATE usuario SET  nombre=?, apellido=?, rol=?,correo=? WHERE cedula=?";
            PreparedStatement ps = conn.prepareStatement(consulta);
            ps.setLong(5, usuarioVO.getCedula());
            ps.setString(1, usuarioVO.getNombre());
            ps.setString(2, usuarioVO.getApellido());
            ps.setString(3, usuarioVO.getRol());
            ps.setString(4, usuarioVO.getCorreo());
            ps.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public Object consultar() {
        try {
            String consulta = "SELECT * FROM usuario WHERE cedula=?";
            PreparedStatement ps = conn.prepareStatement(consulta);
            ps.setLong(1, usuarioVO.getCedula());
            ResultSet resultado = ps.executeQuery();
            UsuarioVO usuarioTemp = null;
            while (resultado.next()) {
                usuarioTemp = new UsuarioVO();
                usuarioTemp.setCedula(resultado.getLong("cedula"));
                usuarioTemp.setNombre(resultado.getString("nombre"));
                usuarioTemp.setApellido(resultado.getString("apellido"));
                usuarioTemp.setRol(resultado.getString("rol"));
            }
            return (Object) usuarioTemp;
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static void main(String[] args) {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        ArrayList<Object> lista = usuarioDAO.listar();
        for (Object u : lista) {
            UsuarioVO d = (UsuarioVO) u;
            System.out.println(d.getNombre() + " " + d.getApellido());
        }
    }

}
