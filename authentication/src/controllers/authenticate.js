var express = require('express'),
  router = express.Router(),
  crypto = require('crypto'),
  authorizationService = require('../services/authorization') ;

function extractUser(headerAuthorization) {
  var stripped = headerAuthorization.substring("Basic".length, headerAuthorization.length);
  var split = Buffer.from(stripped, 'base64').toString('ascii').split(":");
  return split[0];
}

function extractPassword(headerAuthorization) {
  var stripped = headerAuthorization.substring("Basic ".length, headerAuthorization.length);
  var split = Buffer.from(stripped, 'base64').toString('ascii').split(":");
  return crypto.createHash('sha1').update(split[1], 'utf8').digest('hex');
}

router.route('/authenticate')
  .post(function (req, res, next) {
    if (!req.headers.authorization) {
      res.status(400).send("Authorization header not set");
    } else {
      var hashedPass = extractPassword(req.headers.authorization);
      var user = extractUser(req.headers.authorization);
      authorizationService.authenticate(user, hashedPass)
        .then( serviceResponse => {
          if (serviceResponse) {
            return res.status(200).send(serviceResponse);
          } else {
            return res.status(400).send("Bad request");
          }
        })
        .catch(error => {
          res.status(500).send("Internal error");
        });
    }
  })

module.exports = router;