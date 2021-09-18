var express = require('express');
var config = require('./config/express.json');

var app = express();

require('./db/mysql')

app.use(require('./controllers'));

app.listen(config.port);