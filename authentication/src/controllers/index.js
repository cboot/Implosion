var express = require('express'),
    router = express.Router();

router.use('/authorization', require('./authenticate'));
router.use('/authorization', require('./authorize'));

module.exports = router;