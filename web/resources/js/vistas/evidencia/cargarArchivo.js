/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var cargarArchivo={
    init: function(){
       
        
        $(".btnSubirArchivo").click(function(){
            cargarArchivo.cargar()
            console.log("desde btn subir")
        })
    },
    cargar:function(){
        var formulario = new FormData($("#formRegistrarUsuario")[0]);
        $.ajax({
            url:'./archivo/subir',
            data:formulario,
            type:'post',
            dataType:'json',
            contentType: false,
            processData: false,
            success:function(resultado){
                console.log(resultado)
            },
            error:function(error){
                console.log(error)
            }
        })
    }
    
}
cargarArchivo.init()
