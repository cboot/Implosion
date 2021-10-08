var jwt = require('jsonwebtoken'),
    configJWT = require('../config/jwt.json'),
    connection = require('../db/mysql');

function authorize(jwtToken) {
    try {
      return jwt.verify(jwtToken, configJWT.secret, { complete : true});
    } catch (err) {
      console.log(err);
    }
    return false;
}

function buildJWT(playerId) {
  return jwt.sign( {
    playerId: playerId,
    iss: configJWT.issuer,
    aud: configJWT.audience,
    exp: Math.floor(Date.now() / 1000) + (60 * 5),
    alg: 'HS256'
  }, configJWT.secret);
}

async function authenticate(user, hashedPass) {
  const sql = 'SELECT id FROM player WHERE name = ' + connection.escape(user) + ' AND password = ' + connection.escape(hashedPass);
  return new Promise( (resolve, reject) => {
    connection.query(sql, function (error, results, fields) {
      if (error) {
        reject(error);
      } else {
        if (results.length == 1) {
          resolve({ token : buildJWT(results[0].id)});
        } else {
          resolve(false);
        }
      }
    });
  });
   
}

module.exports = {
    authorize : function(token) {
        return authorize(token);
    },
    authenticate : async function(user, hashedPass) {
      return await authenticate(user, hashedPass);
  }
}