<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ejemplo Spring con JSON</title>
        <script type="text/javascript" src="js/prototype.js"></script>
        <script type="text/javascript" >
            actualizar = function () {
                new Ajax.Request('rest/curso/spring/empleado',
                        {
                            method: 'get',
                            onSuccess: function (r) {
                                alert(r.responseText);
//                                eval('var datos=' + r.responseText);
                                var datos = JSON.parse(r.responseText);
                                var v = document.getElementById('detalleGrupo');
                                v.innerHTML = '<strong>' + datos.comerciales + ' ' + datos.nombre + '</strong>';
                            },
                            onFailure: function (r) {
                                throw new Error('Error al cargar el detalle del grupo: ' +
                                        r.statusText);
                            }
                        }
                );
            }
        </script>
    </head>
    <body>
        <button type="button" onclick="actualizar();">Ver detalles del grupo</button>
        <span id="detalleGrupo"></span>
    </body>
</html>
