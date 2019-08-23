/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var listarUsuario = {
    init: function () {
        $("#btnVistaRegistrar").click(function () {
            listarUsuario.cargarVista('vista/usuario/registrarUsuario.html')
        })
        listarUsuario.cargarLista()
    },
    cargarVista: function (ruta) {
        $(".contenedor-principal").load(ruta)
    },
    cargarLista: function () {
        $.ajax({
            url: './usuario/listar',
            type: 'post',
            dataType: 'json',
            success: function (resultado) {
                
                if(resultado.codigo==105){
                    listarUsuario.crearTabla(resultado.lista)
                }

            },
            error: function (error) {
                console.log(error)
            }
        })
    },
    crearTabla:function(lista){
        var temp=lista.map(function(fila){
             return   `<tr>
                        <td> ${fila.cedula}</td>
                        <td> ${fila.nombre}</td>
                        <td> ${fila.apellido}</td>
                        <td> ${fila.rol}</td>                        
                        <td> ${fila.correo}</td>
                        <td> <button class='btn btn-success btnEditar' data-info=`+JSON.stringify(fila)+`>EDITAR</button></td>
                        <td> <button class='btn btn-danger btnEliminar' data-info=${fila.cedula}>ELIMINAR</button></td>
                     </tr>`
        })
        $("#tblListarUsuario tbody").html(temp)
        listarUsuario.agregarEventos()
    },
    agregarEventos:function(){
        $('.btnEditar').click(function(){
            listarUsuario.cargarModalEditar($(this).attr('data-info'))
        })
        $('.btnElimnar').click(function(){
            listarUsuario.cargarModalEditar($(this).attr('data-info'))
        })
    },
    cargarModalEditar:function(info){
        $("#modal").modal()
        $(".modal-title").html("Editar Usuario")
        $(".modal-body").load('vista/usuario/editarUsuario.html')
        sessionStorage.setItem('info-editar',info)
    },
    cargarModalEliminar:function(info){
        $("#modal").modal()
        $(".modal-title").html("Eliminar Usuario")
        $(".modal-body").load('vista/usuario/editarUsuario.html')
        sessionStorage.setItem('info-editar',info)
    }
}

listarUsuario.init()

