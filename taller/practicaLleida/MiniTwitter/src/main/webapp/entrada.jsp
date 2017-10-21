<%-- 
    Document   : entrada
    Created on : 20-jun-2017, 19:17:04
    Author     : khepherer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Entrada al sistema</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
        <script
            src="https://code.jquery.com/jquery-3.2.1.min.js"
            integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4="
        crossorigin="anonymous"></script>
        <script type="text/javascript">
            function ajaxPost() {
                $.ajax({
                    type: "POST",
                    url: "http://localhost:8090/nuevo",
                    data: JSON.stringify({
                        mensaje: $("#mensaje").val(),
                        usuarioId:${usuario.id}
                    }),
                    dataType: 'json',
                    contentType: "application/json",
                    success: function (data, textStatus, jqXHR) {
                        console.log(data);
                        const r = JSON.parse(data);
                        $("#hueco").html(r);
                    },
                    error: function (jqXHR, textStatus, errorThrown) {
                        console.log(jqXHR);
                        $("#hueco").html("Error: " + textStatus + " " + errorThrown);
                    }
                });
            }
            function verTodosLosTweets() {
                fetch("http://localhost:8090/tweets")
                        .then((resp) => resp.json())
                        .then(data => {
                            const d = JSON.stringify(data, null, 2);
                            console.table(data);
                            console.log('Id del primer usuario recibido:' + data[0].id);
                            $('#hueco').html(d);
                        })
                        .catch(function (error) {
                            console.log(error);
                        });
            }
            function verTweets(ident) {
                console.log('Id del usuario que quiere ver sus tweets: ' + ident);
                fetch("http://localhost:8090/tweets/" + ident)
                        .then((resp) => resp.json())
                        .then(data => {
                            const d = JSON.stringify(data, null, 2);
                            console.table(data);
                            console.log('Id del primer usuario recibido:' + data[0].id);
                            $('#hueco').html(d);
                        })
                        .catch(function (error) {
                            console.log(error);
                        });
            }
        </script>
    </head>
    <body>
        <span id="usuarioId" hidden>${usuario.id}</span>
        <div class="container">
            <h1>Abrir la consola del navegador</h1>
            <h2>Bienvenid@ a MiniTwitter, ${usuario.nombre} (Id: ${usuario.id}, Clave: ${usuario.clave})</h2>
            <div class="btn-group">
                <button class="btn btn-primary" onclick="verTodosLosTweets()">Todos los tweets</button>
                <button class="btn btn-primary" onclick="verTweets(${usuario.id})">Tus tweets</button>                
                <button class="btn btn-primary">Nuevo Tweet</button>
                <a href="index.html" class="btn btn-info" role="button">Volver</a
            </div> 

        </div>
        <div class="container">
            <pre id="hueco">Hueco para ver los datos</pre>
        </div>
        <div class="container">
            <input id="mensaje" type="text" name="texto" placeholder="Introduce un texto" autofocus/>
            <input type="button" value="Enviar" onclick="ajaxPost()"/>
        </div>
    </body>
</html>
