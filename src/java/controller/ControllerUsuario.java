/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.google.gson.Gson;
import contantes.ConstanteMVC;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mensajes.RespuestaMVC;
import model.dao.UsuarioDAO;
import model.vo.UsuarioVO;

/**
 *
 * @author Administrador
 */
@WebServlet(name = "ControllerUsuario", urlPatterns = {"/usuario/registrar", "/usuario/editar", "/usuario/eliminar", "/usuario/listar", "/usuario/consultar"})
public class ControllerUsuario extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */

            Gson objetoJson = new Gson();//crear objeto Gson
            String ruta = request.getServletPath();//obtener ruta para el filtrado 
            RespuestaMVC respuestaMVC;

            UsuarioVO usuarioVO = new UsuarioVO();
            UsuarioDAO usuarioDAO = new UsuarioDAO();

            switch (ruta) {
                case "/usuario/listar":
                    respuestaMVC = new RespuestaMVC(usuarioDAO.listar());
                    respuestaMVC.parametrizarMensaje(ConstanteMVC.MENSAJE_LISTAR_USUARIO);
                    break;
                case "/usuario/consultar":
                    long cedula = Long.parseLong(request.getParameter("cedula"));
                    usuarioVO.setCedula(cedula);
                    usuarioDAO.setUsuarioVO(usuarioVO);
                    respuestaMVC = new RespuestaMVC(usuarioDAO.consultar());
                    respuestaMVC.parametrizarMensaje(ConstanteMVC.MENSAJE_CONSULTAR_USUARIO);
                    break;
                case "/usuario/registrar":
                    usuarioVO = registrar(request, usuarioVO);
                    usuarioDAO.setUsuarioVO(usuarioVO);
                    respuestaMVC = new RespuestaMVC();
                    if (usuarioDAO.registrar()) {
                        respuestaMVC.parametrizarMensaje(ConstanteMVC.MENSAJE_REGISTRAR_USUARIO);
                    } else {
                        respuestaMVC.parametrizarMensaje(ConstanteMVC.MENSAJE_USUARIO_REPETIDO);
                    }
                    break;
                case "/usuario/editar":
                    usuarioVO = actualizar(request, usuarioVO);
                    usuarioDAO.setUsuarioVO(usuarioVO);
                    respuestaMVC = new RespuestaMVC(usuarioDAO.editar());
                    respuestaMVC.parametrizarMensaje(ConstanteMVC.MENSAJE_ACTUALIZAR_USUARIO);
                    break;
                case "/usuario/eliminar":
                    registrar(request, usuarioVO);
                    respuestaMVC = new RespuestaMVC(usuarioDAO.consultar());
                    respuestaMVC.parametrizarMensaje(ConstanteMVC.MENSAJE_CONSULTAR_USUARIO);
                    break;
                default:
                    respuestaMVC = null;
                    break;
            }

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            out.print(objetoJson.toJson(respuestaMVC));
            out.flush();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public UsuarioVO registrar(HttpServletRequest request, UsuarioVO usuarioVO) {
        usuarioVO.setCedula(Long.parseLong(request.getParameter("cedula")));
        usuarioVO.setNombre(request.getParameter("nombre"));
        usuarioVO.setApellido(request.getParameter("apellido"));
        usuarioVO.setRol(request.getParameter("rol"));
        usuarioVO.setCorreo(request.getParameter("correo"));
        return usuarioVO;
    }

    private UsuarioVO actualizar(HttpServletRequest request, UsuarioVO usuarioVO) {
        usuarioVO.setCedula(Long.parseLong(request.getParameter("cedula")));
        usuarioVO.setNombre(request.getParameter("nombre"));
        usuarioVO.setApellido(request.getParameter("apellido"));
        usuarioVO.setRol(request.getParameter("rol"));
        usuarioVO.setCorreo(request.getParameter("correo"));
        return usuarioVO;
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
