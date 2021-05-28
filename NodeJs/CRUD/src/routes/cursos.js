const express = require('express');
const router = express.Router();

const pool = require('../database');
const { isLoggedIn } = require('../lib/auth');

router.get('/cursos', (req, res) => {
    res.render('cursos');
});

module.exports = router;