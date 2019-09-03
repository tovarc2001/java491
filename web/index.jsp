<%-- 
    Document   : index
    Created on : 15/08/2019, 08:46:44 PM
    Author     : Administrador
--%>

<%@page import="model.vo.UsuarioVO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="resources/css/bootstrap.css" rel="stylesheet" type="text/css"/>  
        <link href="resources/css/style.css" rel="stylesheet" type="text/css"/>  

        <!--carga de jquery boostrap--> 
        <script src="resources/js/lib/jquery-3.4.1.js" type="text/javascript"></script>
        <script src="resources/js/lib/popper.min.js" type="text/javascript"></script>
        <script src="resources/js/lib/bootstrap.js" type="text/javascript"></script>

        <!--carga de librerías para validación--> 
        <script src="resources/js/lib/jquery.validate.js" type="text/javascript"></script>
        <script src="resources/js/lib/additional-methods.js" type="text/javascript"></script>
        
        <!--md5-->
        <script src="resources/js/lib/md5.js" type="text/javascript"></script>
        
        <title>JSP Page</title>
    </head>
    <body>



        <div class="menu"></div>
        <div class="container-fluid">
            <div class="row">
                <div class="col-sm-3 col-md-3  menu-lateral" >                   
                </div>
                <div class="col-sm-9 col-md-9 contenedor-principal" >                    
                </div>

            </div>            
        </div>

        <!--modal-->

        <div class="modal" id="modal" tabindex="-1" role="dialog">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title">Modal title</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <p>Modal body text goes here.</p>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-primary">Save changes</button>
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                    </div>
                </div>
            </div>
        </div>


        <script>

            <%
                HttpSession sesion = request.getSession(true);
                
                if (sesion.getAttribute("usuario") == null) {
                    out.println("$('.menu-lateral').hide()");
                    out.println("$('.contenedor-principal').load('./vista/usuario/iniciarSesion.html')");
                    out.println("$('.contenedor-principal').removeClass('col-sm-9 col-md-9').addClass('offset-md-3 col-sm-12 col-md-6')");

                }else{
                 
                    UsuarioVO  usu=(UsuarioVO)sesion.getAttribute("usuario");                    
                    if(usu.getRol().equals("admin")){     
                       out.println("$('.menu').load('./componentes/menuAdmin.html')"); 
                       out.println("$('.menu-lateral').load('./componentes/menuLateralAdmin.html')");
                       out.println("$('.contenedor-principal').load('./vista/usuario/listarUsuario.html')");
                    }else if(usu.getRol().equals("user")){
                       out.println("$('.menu').load('./componentes/menuUsuario.html')");    
                       out.println("$('.menu-lateral').load('./componentes/menuLateralUsuario.html')");
                       out.println("$('.contenedor-principal').load('./vista/usuario/listarUsuario.html')");
                   }
                }
            %>
        </script>

    </body>
</html>
