var express = require('express'),
  router = express.Router(),
  authorizationService = require('../services/authorization');

router.route('/authorize')
  .post(function (req, res, next) {
    if (!req.headers.authorization) {
      res.status(400).send("Authorization header not set");
    } else {
      const token = req.headers.authorization.substring("bearer ".length, req.headers.authorization.length);
      const decodedToken = authorizationService.authorize(token);
      if (decodedToken) {
        const playerId = decodedToken.payload.playerId;
        res.status(200).send({ playerId : playerId });
      } else {
        res.status(403).send("Token is invalid");
      }     
    }
  })

module.exports = router;