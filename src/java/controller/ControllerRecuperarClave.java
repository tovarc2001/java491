/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.google.gson.Gson;
import constantes.ConstanteMVC;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mensajes.RespuestaMVC;
import model.dao.RecuperarClaveDAO;

/**
 *
 * @author Nanimo
 */
@WebServlet(name = "ControllerRecuperarClave", urlPatterns = {"/recuperar/enviarCorreo", "/recuperar/restablecerClave"})
public class ControllerRecuperarClave extends HttpServlet {

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

            String ruta=request.getPathInfo();
            RespuestaMVC resp;
            RecuperarClaveDAO rcdao=new RecuperarClaveDAO();
            Gson gson = new Gson();
            
            
            switch(ruta){
                case "/recuperar/enviarCorreo":
                    String correo = request.getParameter("corre0" );
                    
                    resp= new RespuestaMVC();
                    
                    if(rcdao.recuperarClave(correo, "quitar parámetro")){
                        resp.parametrizarMensaje(ConstanteMVC.MENSAJE_CORREO_ENVIADO);
                    }else{
                        resp.parametrizarMensaje(ConstanteMVC.MENSAJE_CORREO_INVALIDO);
                    }
                    
                    break;
            }
            
            
            
            
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
