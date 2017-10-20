console.log('Comienzo de server.js');
var app = require('./app');
var port = process.env.PORT || 3000;
console.log('El API emplea una base datos Mongo desplegada en mLab');
var server = app.listen(port, function() {
  console.log('Servidor Express arrancado en el puerto ' + port);
});