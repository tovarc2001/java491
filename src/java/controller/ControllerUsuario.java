/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
          
            Gson  objetoJson = new Gson();
            String ruta = request.getServletPath();
            String [] nombre= new String[3];
            UsuarioVO usuarioVO= new UsuarioVO();
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            
            ArrayList<Object> lista = new ArrayList<Object>();
            switch(ruta){
                case "/usuario/listar":
                    lista=usuarioDAO.listar();
                    break;
                case "/usuario/consultar":
                    long cedula= Long.parseLong(request.getParameter("nombre"));
                    usuarioVO.setCedula(cedula);
                    usuarioDAO.consultar();
                    break;                    
            }
            
            
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            out.print(objetoJson.toJson(lista));
            out.flush();
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
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
