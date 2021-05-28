<?php
header('Content-Type: text/html; charset=UTF-8');
header('Content-Type: text/html; charset=iso-8859-1');
// variables de configuración de la base de datos en hosting

define("DB_HOST", "localhost"); //ubicacion de la base de datos
define("DB_USER", "puntopro_felipe");//usuario de la base de datos
define("DB_PASSWORD", "Felipe@123");//pass de la base de datos
define("DB_DATABASE", "puntopro_rbsys");//Nombre de la base de datos 


// variables de configuración de la base de datos de moodle en hosting
 
define("DB_HOST_MOODLE", "localhost"); //ubicacion de la base de datos
define("DB_USER_MOODLE", "puntopro_moodle");//usuario de la base de datos
define("DB_PASSWORD_MOODLE", "Moodle@123");//pass de la base de datos
define("DB_DATABASE_MOODLE", "puntopro_puenteaprende");//Nombre de la base de datos 


/*************************************************/



/**registros log**/
define("LOG","1");
$raiz = $_SERVER['DOCUMENT_ROOT']; 
define("RUTA",$raiz."/rbsystem/registros_log");
define("ESCRITURA","a");
/**fin registros log**/


/**********************************************/

/*
* Encriptacion de las variables
*/
$encriptar = "false";
$llave_encript = "123";
define("llave","123");

?>
