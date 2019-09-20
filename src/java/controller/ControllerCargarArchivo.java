/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.google.gson.Gson;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import model.vo.UsuarioVO;

/**
 *
 * @author Administrador
 */
@WebServlet(name = "ControllerCargarArchivo", urlPatterns = {"/archivo/subir", "/archivo/listar"})
@MultipartConfig(maxFileSize = 5000000)
public class ControllerCargarArchivo extends HttpServlet {

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

            String ruta = request.getServletPath();
            Gson json = new Gson();
            String mensaje = null;
            UsuarioVO usuVO;
            final String ARCHIVO = "C:\\Users\\Administrador\\Documents\\NetBeansProjects\\java491\\evidencia";

            switch (ruta) {
                case "/archivo/subir":

                    Part archivo = request.getPart("archivo");
                    HttpSession sesion = request.getSession(true);
                    usuVO = (UsuarioVO) sesion.getAttribute("usuario");

                    File crearCarpeta = new File(ARCHIVO + File.separator + usuVO.getCedula());
                    if (!crearCarpeta.exists()) {
                        crearCarpeta.mkdir();
                        if (archivo.getSize() > 0) {
                            mensaje = json.toJson("es mayor de cero");
                            InputStream inputStream = archivo.getInputStream();
                            File salvarArchivo = new File(ARCHIVO + File.separator + usuVO.getCedula()+File.separator+archivo.getSubmittedFileName());
                            Files.copy(inputStream, salvarArchivo.toPath(), StandardCopyOption.REPLACE_EXISTING);

                        }
                    } else {
                        mensaje = json.toJson("es mayor de cero");
                        InputStream inputStream = archivo.getInputStream();
                        File salvarArchivo = new File(ARCHIVO + File.separator + usuVO.getCedula()+File.separator+archivo.getSubmittedFileName());
                        Files.copy(inputStream, salvarArchivo.toPath(), StandardCopyOption.REPLACE_EXISTING);
                    }

                    //mensaje=json.toJson("desde subir");
                    break;
                case "/archivo/listar":

                    mensaje = json.toJson("desde listar");
                    break;
                default:
                    mensaje = json.toJson("no es valido");
                    break;

            }

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            out.print(mensaje);
            out.flush();

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
