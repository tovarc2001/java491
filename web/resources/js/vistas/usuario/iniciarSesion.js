/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var iniciarSesionUsuario = {
    init: function () {

        $("#formIniciarSesionUsuario").submit(function () {
            return false
        })
        $("#btnIngresarInicioSesion").click(function () {
            console.log('prueba')
            iniciarSesionUsuario.validar()
        })

    },
    validar: function () {
        console.log("desde validar en registrar Usuario")

        $("#formIniciarSesionUsuario").validate({
            rules: {
                cedula: {
                    required: true,
                    number: true,
                    rangelength: [7, 12]
                },
                clave: {
                    required: true,
                    min: 8
                },
            },
            messages: {
                cedula: {
                    required: "campo obligatorio",
                    number: 'solo números',
                    rangelength: "coloque entre 7 y 12 dígitos"
                },
                clave: {
                    required: "campo obligatorio",
                    min: "Digite al menos ocho carácteres"
                },
            },
            submitHandler: function () {
                iniciarSesionUsuario.iniciarSesion()
            }
        })
        $("#formIniciarSesionUsuario").submit()
    },
    iniciarSesion: function () {
        var cedula = $('input[name=cedula]').val()
        var clave = md5($('input[name=clave]').val())
        $.ajax({
            url: './usuario/iniciarSesion',
            data: {cedula: cedula, clave: clave},
            type: 'post',
            dataType: 'json',
            success: function (resultado) {
                console.log(resultado)
                if (resultado.codigo == 110) {
                    iniciarSesionUsuario.mostrarMensaje('Error', resultado.mensaje)
                } else if (resultado.codigo == 108) {
                    if (resultado.objeto.rol == 'admin') {
                        $('.menu').load('./componentes/menuAdmin.html')
                        $('.menu-lateral').load('./componentes/menuLateralAdmin.html', function () {
                            $(document).on('click','.btnDestruirSesion', function () {
                                console.log('destruir sesion usuario')
                                iniciarSesionUsuario.destruirSesion()
                            })
                        })
                        $('.contenedor-principal').load('./vista/usuario/listarUsuario.html')

                    } else if (resultado.objeto.rol == 'user') {
                        console.log('usuario desde inicio')
                        $('.menu').load('./componentes/menuUser.html')
                        $('.menu-lateral').load('./componentes/menuLateralUsuario.html')
                        $('.contenedor-principal').load('./vista/usuario/listarUsuario.html')

                    }

                }
            },
            error: function (error) {
                console.log(error)
            }
        })
    },
    destruirSesion: function () {

        $.ajax({
            url: './usuario/destruirSesion',
            type: 'post',
            dataType: 'json',
            success: function (resultado) {
                console.log(resultado)
                if (resultado.codigo == 109) {
                    //iniciarSesionUsuario.mostrarMensaje('Error',resultado.mensaje)
                } else if (resultado.codigo == 108) {

                }
            },
            error: function (error) {
                console.log(error)
            }
        })
    },
    mostrarMensaje: function (titulo, mensaje) {
        $("#modal").modal()
        $(".modal-body").html(mensaje)
        $(".modal-header").html(titulo)
    },
    cargarVista: function (ruta) {
        $(".contenedor-principal").load(ruta)
    },
}

iniciarSesionUsuario.init()
