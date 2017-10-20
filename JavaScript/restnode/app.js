console.log('Comienzo de app.js');
var express = require('express');
// Una manera de permitir que sistemas clientes de otros dominios,
// o del mismo dominio en diferente puerto, puedan intercambiar datos
// con esta API
var cors = require('cors')

var app = express();
app.use(cors());

var db = require('./db');
// INCLUIR RUTAS
var UserController = require('./user/UserController');
app.use('/users', UserController);
module.exports = app;