<?php
header('Content-Type: text/html; charset=UTF-8');
header('Content-Type: text/html; charset=iso-8859-1');
class funciones_BD {
 
    private $db;
	
    // constructor
    function __construct() {
        require_once '../conexion/conexionbd_rbsystem.php';
		
        // conectando a la base de datos
        $this->db = new conexion();
        $this->db->connect();
		
    }    
 
    /**
     * agregar nuevo usuario
     */
    public function adduser($usuario,$rol,$password,$nombre,$apellido) {
    	$result = mysql_query("Insert into usuario(rut,rol,pass,nombre,apellido) values('$usuario',$rol,'$password','$nombre','$apellido')");
        if ($result) {
            return true;			
        } else {
            return false;
        }
    }
	  /**
     * Verificar si el usuario ya existe por el username
     */
    public function isuserexist($username) {

        $result = mysql_query("SELECT rut from usuario WHERE rut = '".mysql_real_escape_string($username)."'");
        $num_rows = mysql_num_rows($result); //numero de filas retornadas
        if ($num_rows > 0) {
            // el usuario existe 
            return true;
        } else {
            // no existe
            return false;
        }
    }
    
	/**
     * Verificar el login del usuario con username y pass
     */
	public function login($username,$password){
		/*Para encriptacion de pass	
		//$password_cry = md5($password);	
		//$existe=mysql_query("SELECT COUNT(*) FROM mdl_user WHERE username='$username' AND password='$password_cry' "); 
		*/
		$existe=mysql_query("SELECT COUNT(*) FROM usuario WHERE rut='".mysql_real_escape_string($username)."' AND pass='".mysql_real_escape_string($password)."' "); 
		$count = mysql_fetch_row($existe);
    	if ($count[0]==0){
			return true;
		}else{
			return false;
		}
	}

	public function rol($username,$password){
		
	    //$todoslosusuarios = array();   //Para cuando se necesite mas de un dato en la consulta
	    $usuario;
		$sql = "SELECT * FROM usuario WHERE rut='".mysql_real_escape_string($username)."' AND pass='".mysql_real_escape_string($password)."'";
		$consulta = mysql_query($sql);
		while($fila = mysql_fetch_assoc($consulta))
		{
			//$this->todoslosusuarios[]=$fila; //Para cuando se necesite mas de un dato en la consulta
			$this->usuario=array_map("utf8_encode", $fila);
		}
		//return $this->todoslosusuarios;
		return $this->usuario;
	}
		/**
     * Verificar el count del apoderado con username y pass
     */
	public function count_apoderado($username,$password){
		$existe=mysql_query("SELECT COUNT(*) FROM apoderado WHERE rut='".mysql_real_escape_string($username)."' AND pass='".mysql_real_escape_string($password)."'"); 
		$count = mysql_fetch_row($existe);
    	if ($count[0]==0){
			return true;
		}else{
			return false;
		}
	}
	public function rol_apoderado($username,$password){
		
	    //$todoslosusuarios = array();   //Para cuando se necesite mas de un dato en la consulta
	    $usuario;
		$sql = "select rut as RUT,rol as USER_ID,nombre as NOMBRE,apellido as APELLIDO, email
				from apoderado
				where apoderado.rut = '".mysql_real_escape_string($username)."' and apoderado.pass = '".mysql_real_escape_string($password)."'";
		$consulta = mysql_query($sql);
		while($fila = mysql_fetch_assoc($consulta))
		{
			//$this->todoslosusuarios[]=$fila; //Para cuando se necesite mas de un dato en la consulta
			$this->usuario=array_map("utf8_encode", $fila);
		}
		//return $this->todoslosusuarios;
		return $this->usuario;
	}	
	/**
     * Verificar el login del apoderado con username y pass
     */
	public function count_apoderado_alumno($username){
		$existe=mysql_query("select count(apoderado_alumno.idalumno)
								from apoderado_alumno
								where apoderado_alumno.idapoderado = '".mysql_real_escape_string($username)."'"); 
		$count = mysql_fetch_row($existe);
    	if ($count[0]==0){
			return true;
		}else{
			return false;
		}
	}	
	public function apoderado_alumno($username){
		
	    //$todoslosusuarios = array();   //Para cuando se necesite mas de un dato en la consulta
	    $usuario;
		$sql = "select alumno.rut as RUT,alumno.id as USER_ID,alumno.nombre AS NOMBRE,alumno.apellidos as APELLIDO,alumno.email
				from apoderado_alumno inner join alumno on apoderado_alumno.idalumno = alumno.rut
					where apoderado_alumno.idapoderado = '".mysql_real_escape_string($username)."'";
		$consulta = mysql_query($sql);
		while($fila = mysql_fetch_assoc($consulta))
		{
			$this->usuario[]=array_map("utf8_encode", $fila); //Para cuando se necesite mas de un dato en la consulta
			//$this->usuario=$fila;
		}
		//return $this->todoslosusuarios;
		return $this->usuario;
	}
	/**
     * Count de asistencia
     */
	 public function count_asistencia($username){
		$existe=mysql_query("SELECT count(curso_alumno.asistencia)
							FROM usuario
							INNER JOIN curso_alumno ON usuario.rut = curso_alumno.rut
							INNER JOIN cursohorario ON curso_alumno.idcursohorario = cursohorario.idcursohorario
							INNER JOIN curso ON cursohorario.idcurso = curso.idcurso
							WHERE usuario.rut =  '".mysql_real_escape_string($username)."';"); 
		$count = mysql_fetch_row($existe);
    	if ($count[0]==0){
			return true;
		}else{
			return false;
		}
	}
	/**
     * Verificar la asistencia del usuario por el username
     */
	public function asistencia($username){
	    $todalaasistencia = array();   //Para cuando se necesite mas de un dato en la consulta
	    //$usuario;
		$sql = "SELECT curso.fullname, curso_alumno.asistencia
				FROM usuario
				INNER JOIN curso_alumno ON usuario.rut = curso_alumno.rut
				INNER JOIN cursohorario ON curso_alumno.idcursohorario = cursohorario.idcursohorario
				INNER JOIN curso ON cursohorario.idcurso = curso.idcurso
				WHERE usuario.rut =  '".mysql_real_escape_string($username)."'";
		$consulta = mysql_query($sql);
		while($fila = mysql_fetch_assoc($consulta))
		{
			$this->todalaasistencia[]=$fila; //Para cuando se necesite mas de un dato en la consulta
			//$this->usuario=$fila;
		}
		return $this->todalaasistencia;
		//return $this->usuario;
	}
	
	/**
     * Count de horarios
     */
	 public function count_horarios($username){
		$existe=mysql_query("SELECT  count(hora_inicio)
							FROM usuario
							INNER JOIN curso_alumno ON usuario.rut = curso_alumno.rut
							INNER JOIN cursohorario ON curso_alumno.idcursohorario = cursohorario.idcursohorario
							INNER JOIN curso ON cursohorario.idcurso = curso.idcurso
							INNER JOIN horario ON cursohorario.idhorario = horario.idhorario
							INNER JOIN seccion ON cursohorario.idseccion = seccion.idseccion
							WHERE usuario.rut =  '".mysql_real_escape_string($username)."'"); 
		$count = mysql_fetch_row($existe);
    	if ($count[0]==0){
			return true;
		}else{
			return false;
		}
	}
	
	/**
     * Verificar los horarios del usuario por el username
     */
	public function horarios($username){
	    $todoloshorarios = array();   //Para cuando se necesite mas de un dato en la consulta
	    //$usuario;
		$sql = "SELECT curso.fullname, hora_inicio, hora_termino , horario.dia, seccion.nombre_seccion
				FROM usuario
				INNER JOIN curso_alumno ON usuario.rut = curso_alumno.rut
				INNER JOIN cursohorario ON curso_alumno.idcursohorario = cursohorario.idcursohorario
				INNER JOIN curso ON cursohorario.idcurso = curso.idcurso
				INNER JOIN horario ON cursohorario.idhorario = horario.idhorario
				INNER JOIN seccion ON cursohorario.idseccion = seccion.idseccion
				WHERE usuario.rut =  '".mysql_real_escape_string($username)."'";
		$consulta = mysql_query($sql);
		while($fila = mysql_fetch_assoc($consulta))
		{
			$this->todoloshorarios[]=$fila; //Para cuando se necesite mas de un dato en la consulta
			//$this->usuario=$fila;
		}
		return $this->todoloshorarios;
		//return $this->usuario;
	}
	
}
?>
