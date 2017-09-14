<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ejemplo Spring con XML</title>
        <script type="text/javascript" src="js/prototype.js"></script>
        <script type="text/javascript" >
            actualizar = function () {
                new Ajax.Request('rest/cafe/java',
                {
                    method: 'get',
                    onSuccess: function(r){       
                        var objetoXml = r.responseXML;
                        alert(r.responseText);
                        var cafe = objetoXml.getElementsByTagName('nombre').item(0).firstChild.nodeValue;
                        var cantidad = objetoXml.getElementsByTagName('cantidad').item(0).firstChild.nodeValue;
                        var v = document.getElementById('detallePedido');
                        v.innerHTML = '<strong>' + cafe + ' ' + cantidad + '</strong>';
                    },
                    onFailure: function(r) {
                        throw new Error( 'Error al cargar los datos del pedido: ' +
                            r.statusText );
                    }
                }
            );
            }
        </script>
    </head>
    <body>
        <button type="button" onclick="actualizar();">Ver detalles del pedido</button>
        <span id="detallePedido"></span>
    </body>
</html>
