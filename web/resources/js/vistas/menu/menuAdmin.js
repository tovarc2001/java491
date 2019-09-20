/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(".cargar-archivo").click(function(){
    $(".contenedor-principal").load("./vista/evidencia/cargarArchivo.html")
})
$(".link-listar-usuario").click(function(){
    $(".contenedor-principal").load("./vista/usuario/listarUsuario.html")
})


