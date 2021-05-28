<?php
header('Content-Type: text/html; charset=UTF-8');
header('Content-Type: text/html; charset=iso-8859-1'); 
class conexion {

    // Conexión a la base de datos
    public function connect() {
        require_once '../config.php';
        // conectando a mysql y dando las credenciales de acceso
        $con = mysql_connect(DB_HOST, DB_USER, DB_PASSWORD);
        // se selecciona la base de datos
        mysql_select_db(DB_DATABASE);
 
        // retorno de la respuesta desde la base de datos
        return $con;
		// Cerrando la conexion de la base de datos.  
		mysql_close($con);
    }
}
 
?>
