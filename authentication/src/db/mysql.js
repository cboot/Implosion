var mysql      = require('mysql');
var config = require('../config/mysql.json')
var connection = mysql.createConnection({
    host: config.url,
    user: config.user,
    password: config.password,
    database: config.database
});

connection.connect(function(err) {
  if (err) {
    console.error('error connecting: ' + err.stack);
    return;
  }

  console.log('connected as id ' + connection.threadId);
});

module.exports = connection;