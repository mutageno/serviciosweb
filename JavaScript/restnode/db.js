console.log('Comienzo de db.js');
var mongoose = require('mongoose');
mongoose.connect('mongodb://formacion:formacion@ds125365.mlab.com:25365/testexpress', { useMongoClient: true });