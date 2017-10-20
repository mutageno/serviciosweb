console.log('Comienzo de usercontroller.js');
var express = require('express');
var router = express.Router();
var bodyParser = require('body-parser');
router.use(bodyParser.urlencoded({ extended: true }));
var User = require('./User');

// NUEVO USUARIO
router.post('/', function (req, res) {
    User.create({
            name : req.body.name,
            email : req.body.email,
            password : req.body.password
        }, 
        function (err, user) {
            if (err) return res.status(500).send("Ha ocurrido un error al añadir información a la base de datos.");
            res.status(200).send(user);
        });
});
// TODOS LOS USUARIOS
router.get('/', function (req, res) {
    User.find({}, function (err, users) {
        if (err) return res.status(500).send("Ha ocurrido un error intentando encontrar usuarios.");
        res.status(200).send(users);
    });
});
// USUARIO POR IDENTIFICADOR
router.get('/:id', function (req, res) {
    User.findById(req.params.id, function (err, user) {
        if (err) return res.status(500).send("Ha ocurrido un error al buscar al usuario.");
        if (!user) return res.status(404).send("No se encuentra el usuario.");
        res.status(200).send(user);
    });
});
// BORRAR UN USUARIO
router.delete('/:id', function (req, res) {
    User.findByIdAndRemove(req.params.id, function (err, user) {
        if (err) return res.status(500).send("Ha ocurrido un error al borrar al usuario.");
        res.status(200).send("Usuario "+ user.name +" BORRADO.");
    });
});
// ACTUALIZAR USUARIO
router.put('/:id', function (req, res) {
    
    User.findByIdAndUpdate(req.params.id, req.body, {new: true}, function (err, user) {
        if (err) return res.status(500).send("Ha ocurrido un error al actualizar al usuario.");
        res.status(200).send(user);
    });
});
module.exports = router;