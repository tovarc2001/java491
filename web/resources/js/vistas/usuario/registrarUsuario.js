/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var registrarUsuario={
    init: function(){
        $("#formRegistrarUsuario").submit(function(){
            return false
        })
        $("#btnAceptarRegistrarUsuario").click(function(){
            registrarUsuario.validar()
        })
        console.log("desde init en registrar Usuario")
    },
    validar:function(){
        console.log("desde validar en registrar Usuario")
        
        $("#formRegistrarUsuario").validate({
            submit:true,
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
                registrarUsuario.registrar()
            }
        })
        $("#formRegistrarUsuario").submit()
    },
    registrar:function(){
       $.ajax({
           url:'./usuario/registrar',
           data:$("#formRegistrarUsuario").serialize(),
           type:'post',
           dataType:'json',
           success:function(resultado){
               console.log(resultado)
               if(resultado.codigo==103){
                   registrarUsuario.mostrarMensaje(resultado.mensaje)
               }else if(resultado.codigo==107){
                   registrarUsuario.mostrarMensajeUsuarioRepetido(resultado.mensaje)
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
    mostrarMensajeUsuarioRepetido:function(mensaje){
        $("#modal").modal()
        $(".modal-body").html(mensaje)
        $(".modal-header").html("ERROR")
    }
}

registrarUsuario.init()
