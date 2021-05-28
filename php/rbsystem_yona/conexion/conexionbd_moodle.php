<?php
header('Content-Type: text/html; charset=UTF-8');
header('Content-Type: text/html; charset=iso-8859-1'); 
class conexion {
 	
    // Conexión a la base de datos
		public function connect_moodle() {
			try{
				require_once '../config.php';
				require_once '../controlador/registros_log.php';
				$this->l = new registros_log();
				
				// conectando a mysql y dando las credenciales de acceso
				$con = mysql_connect(DB_HOST_MOODLE, DB_USER_MOODLE, DB_PASSWORD_MOODLE);
				// se selecciona la base de datos
				mysql_select_db(DB_DATABASE_MOODLE);
				$this->l->registro_log_string("connect_moodle","conexion exitosa","ok");
				// retorno de la respuesta desde la base de datos
				return $con;
				//ejecuta el metodo __destructor
				$this->__destructor();	
			}catch (mysqli_sql_exception $e) {
				$this->l->registro_log_string("connect_moodle","no se encuentra la conexion",$e);
				return false;
			}
		}
		
		// ejecuta el metodo close
		function __destructor() {
			 $this->close();
		}
		
		// Cerrando la conexion de la base de datos.     
		public function close() {
			mysql_close();
		}
	
}
 
?>
