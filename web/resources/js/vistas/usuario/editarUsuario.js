/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var editarUsuario={
    init: function(){
        
        if(typeof(sessionStorage.getItem('info-editar'))!='undefined'){
            var info=JSON.parse(sessionStorage.getItem('info-editar'))
            $("input[name=cedula]").val(info.cedula)
            $("input[name=nombre]").val(info.nombre)
            $("input[name=apellido]").val(info.apellido)
            $("input[name=correo]").val(info.correo)
            $("select[name=rol]").val(info.rol)
        }
        
        $("#formEditarUsuario").submit(function(){
            return false
        })
        $("#btnAceptarEditarUsuario").click(function(){
            editarUsuario.validar()
        })
        $("#btnVistaListar").click(function(){
            editarUsuario.cargarVista('vista/usuario/listarUsuario.html')
        })
        console.log("desde init en registrar Usuario")
    },
    validar:function(){
        console.log("desde validar en registrar Usuario")
        
        $("#formEditarUsuario").validate({
            rules:{
                cedula:{
                    required:true,
                    number:true,
                    rangelength:[7,12]
                },
                nombre:{
                    required:true
                },
                apellido:{
                    required:true
                },
                correo:{
                    required:true,
                    email:true
                },
                rol:{
                    required:true
                }
            },
            messages:{
                cedula:{
                    required:"campo obligatorio",
                    number:'solo números',
                    rangelength:"coloque entre 7 y 12 dígitos"
                },
                nombre:{
                    required:"campo obligatorio"
                },
                apellido:{
                    required:"campo obligatorio"
                },
                correo:{
                    required:"campo obligatorio",
                    email:'digite un correo válido'
                },
                rol:{
                    required:"campo obligatorio"
                }              
            },
            submitHandler:function(){
                editarUsuario.registrar()
            }
        })
        $("#formEditarUsuario").submit()
    },
    registrar:function(){
       $.ajax({
           url:'./usuario/editar',
           data:$("#formEditarUsuario").serialize(),
           type:'post',
           dataType:'json',
           success:function(resultado){
               console.log(resultado)
               if(resultado.codigo==100){
                   editarUsuario.mostrarMensaje('Usuario',resultado.mensaje)
                   editarUsuario.cargarVista('vista/usuario/listarUsuario.html')
               }else if(resultado.codigo==107){
                   editarUsuario.mostrarMensaje('Error',resultado.mensaje)
               }
               
           },
           error:function(error){
               console.log(error)
           }
       })
    },
    mostrarMensaje:function(mensaje){
        $("#modal").modal()
        $(".modal-body").html(mensaje)
        $(".modal-header").html("USUARIO")
    },
    cargarVista: function (ruta) {
        $(".contenedor-principal").load(ruta)
    },
   
}

editarUsuario.init()
