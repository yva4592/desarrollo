
const express = require('express');
const router = express.Router();
const paginate = require('express-paginate');

const pool = require('../database');
const { isLoggedIn } = require('../lib/auth');
 

router.get('/home', isLoggedIn, async (req, res, next) => {
    const datosUser = await pool.query('SELECT * FROM datosuser WHERE IDUSER = ?', [req.user.idUsuario]);
    const datosCursos = await pool.query('SELECT * FROM details_inscripcion WHERE IDUSER_INSCRITO = ?', [req.user.idUsuario]);
    res.render('home', { datosUser:datosUser, datosCursos:datosCursos });
});  

module.exports = router;