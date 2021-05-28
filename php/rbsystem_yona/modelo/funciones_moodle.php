<?php
header('Content-Type: text/html; charset=UTF-8');
header('Content-Type: text/html; charset=iso-8859-1');
class funciones_BD {
 
    private $db;
	private $l;
 
    // constructor
  	function __construct() {
        require_once '../conexion/conexionbd_moodle.php';
		require_once '../controlador/registros_log.php';
        // conectando a la base de datos de moodle  
		     
		$this->db = new conexion();
		$this->db->connect_moodle();
		
		$this->l = new registros_log();
    }   
	
	/**
     * Count de notas
     */
	 public function count_notas($username){
		try{		 
			$existe=mysql_query("SELECT  count(mdl_grade_items.itemname) 
								FROM mdl_user
								INNER JOIN mdl_grade_grades ON mdl_user.id = mdl_grade_grades.userid
								INNER JOIN mdl_grade_items ON mdl_grade_grades.itemid = mdl_grade_items.id
								INNER JOIN mdl_course ON mdl_grade_items.courseid = mdl_course.id
								WHERE mdl_user.username =  '".mysql_real_escape_string($username)."' and mdl_course.fullname  not like '%Asistencia%' and mdl_grade_items.itemname is not null "); 
			$count = mysql_fetch_row($existe);
			if ($count[0]==0){
				$this->l->registro_log_string("notas","Count_notas no ha encontrado registros","username = ".$username);
				return true;
			}else{
				$this->l->registro_log_string("notas","Count_notas a encontrado registros","username = ".$username);
				return false;
			}
		}catch (Exception $e) {
			$this->l->registro_log_string("notas","error en funcion count_notas",$e->getMessage());
			return false;
		}
	}
	
 
 	/**
     * Verificar las notas del usuario por el username
     */
	public function notas($username){
		try{
			$todalasnotas = array();   //Para cuando se necesite mas de un dato en la consulta
			//$notas;
			$sql = "SELECT mdl_course.id, mdl_course.fullname AS curso, mdl_grade_items.itemname AS item, mdl_grade_grades.rawgrade AS nota
					FROM mdl_user
					INNER JOIN mdl_grade_grades ON mdl_user.id = mdl_grade_grades.userid
					INNER JOIN mdl_grade_items ON mdl_grade_grades.itemid = mdl_grade_items.id
					INNER JOIN mdl_course ON mdl_grade_items.courseid = mdl_course.id
					WHERE mdl_user.username =  '".mysql_real_escape_string($username)."' and mdl_course.fullname  not like '%Asistencia%' and mdl_grade_items.itemname is not null 
					ORDER BY mdl_course.fullname ";
			$consulta = mysql_query($sql);
			while($fila = mysql_fetch_assoc($consulta))
			{
				$this->todalasnotas[]=array_map("utf8_encode", $fila); //Para cuando se necesite mas de un dato en la consulta
				//$this->todalasnotas[]=$fila; //Para cuando se necesite mas de un dato en la consulta
				
				//$this->usuario=$fila;
			}
			$this->l->registro_log_string("notas","funcion notas a encontrado resultados","username = ".$username);
			return $this->todalasnotas;
			//return $this->usuario;
			$this->db->__destructor();
		}catch (Exception $e) {
			$this->l->registro_log_string("notas","error en funcion notas",$e->getMessage());
			return false;
		}
	}  
	/**
     * Count parcipantes del curso
     */
	 public function count_participantes_curso($username){
		try{		 
			$existe=mysql_query("SELECT count(mdl_user.firstname)
								FROM mdl_user
								INNER JOIN mdl_role_assignments ON mdl_user.id = mdl_role_assignments.userid
								INNER JOIN mdl_context ON mdl_role_assignments.contextid = mdl_context.id
								INNER JOIN mdl_course ON mdl_context.instanceid = mdl_course.id
								where  mdl_course.fullname = '".mysql_real_escape_string($username)."' and mdl_context.contextlevel = '50'"); 
			$count = mysql_fetch_row($existe);
			if ($count[0]==0){
				$this->l->registro_log_string("participantes_curso","count_participantes_curso no a encontrado registros","curso = ".$username);
				return true;
			}else{
				$this->l->registro_log_string("participantes_curso","count_participantes_curso a encontrado registros","curso = ".$username);
				return false;
			}
		}catch (Exception $e) {
			$this->l->registro_log_string("participantes_curso","error en funcion count_participantes_curso",$e->getMessage());
			return false;
		}
	}
	/**
     * obtener los parcipantes del curso
     */ 
	public function participantes_curso($username){
		try{
			$todoslospart = array();   //Para cuando se necesite mas de un dato en la consulta
			//$todoslospart;
			$sql = "SELECT mdl_user.id , mdl_user.username, mdl_user.firstname, mdl_user.lastname, mdl_user.email 
					FROM mdl_user
					INNER JOIN mdl_role_assignments ON mdl_user.id = mdl_role_assignments.userid
					INNER JOIN mdl_context ON mdl_role_assignments.contextid = mdl_context.id
					INNER JOIN mdl_course ON mdl_context.instanceid = mdl_course.id
					where mdl_course.fullname = '".mysql_real_escape_string($username)."' and mdl_context.contextlevel = '50' and mdl_role_assignments.roleid = '5'
					";
			$consulta = mysql_query($sql);
			while($fila = mysql_fetch_assoc($consulta))
			{
				$this->todoslospart[]= array_map("utf8_encode", $fila); //Para cuando se necesite mas de un dato en la consulta
				//$this->usuario=$fila;
			}
			$this->l->registro_log_string("participantes_curso","funcion participantes_curso ha traido registros","curso= ".$username);
			return $this->todoslospart;
			//return $this->usuario;
			$this->db->__destructor();
		}catch (Exception $e) {
			$this->l->registro_log_string("participantes_curso","error en funcion participantes_curso",$e->getMessage());
			return false;
		}
	}  
	/**
     * Count parcipantes del curso opcion profesor
     */
	 public function count_profesor_del_curso($username){
		try{
			$existe=mysql_query("SELECT count(mdl_user.firstname)
								FROM mdl_user
								INNER JOIN mdl_role_assignments ON mdl_user.id = mdl_role_assignments.userid
								INNER JOIN mdl_context ON mdl_role_assignments.contextid = mdl_context.id
								INNER JOIN mdl_course ON mdl_context.instanceid = mdl_course.id
								where  mdl_course.fullname = '".mysql_real_escape_string($username)."' and mdl_context.contextlevel = '50' and mdl_role_assignments.roleid = '3'  "); 
			$count = mysql_fetch_row($existe);
			if ($count[0]==0){
				$this->l->registro_log_string("profesor_del_curso","funcion count_profesor_del_curso no a encontrado registros","curso= ".$username);
				return true;
			}else{
				$this->l->registro_log_string("profesor_del_curso","funcion count_profesor_del_curso a encontrado registros","curso= ".$username);
				return false;
			}
		}catch (Exception $e) {
			$this->l->registro_log_string("profesor_del_curso","error en funcion count_profesor_del_curso",$e->getMessage());
			return false;
		}
	}
	/**
     * obtener los parcipantes del curso opcion profesor
     */ 
	public function profesor_del_curso($username){
		try{
			$todoslospart = array();   //Para cuando se necesite mas de un dato en la consulta
			//$todoslospart;
			$sql = "SELECT mdl_user.id , mdl_user.username, mdl_user.firstname, mdl_user.lastname, mdl_user.email 
					FROM mdl_user
					INNER JOIN mdl_role_assignments ON mdl_user.id = mdl_role_assignments.userid
					INNER JOIN mdl_context ON mdl_role_assignments.contextid = mdl_context.id
					INNER JOIN mdl_course ON mdl_context.instanceid = mdl_course.id
					where mdl_course.fullname = '".mysql_real_escape_string($username)."' and mdl_context.contextlevel = '50' and mdl_role_assignments.roleid = '3'  
					";
			$consulta = mysql_query($sql);
			while($fila = mysql_fetch_assoc($consulta))
			{
				$this->todoslospart[]=array_map("utf8_encode", $fila); //Para cuando se necesite mas de un dato en la consulta
				//$this->usuario=$fila;
			}
			$this->l->registro_log_string("profesor_del_curso","funcion profesor_del_curso a traido registros","curso= ".$username);
			return $this->todoslospart;
			//return $this->usuario;
			$this->db->__destructor();
		}catch (Exception $e) {
			$this->l->registro_log_string("profesor_del_curso","error en funcion profesor_del_curso",$e->getMessage());
			return false;
		}
	} 
	/**
     * Count de cursos
     */
	 public function count_cursos($username){
		 try{
			$existe=mysql_query("SELECT count(mdl_course.fullname) 
								FROM mdl_user
								INNER JOIN mdl_role_assignments ON mdl_user.id = mdl_role_assignments.userid
								INNER JOIN mdl_context ON mdl_role_assignments.contextid = mdl_context.id
								INNER JOIN mdl_course ON mdl_context.instanceid = mdl_course.id
								where mdl_user.username = '".mysql_real_escape_string($username)."' and mdl_context.contextlevel = '50'"); 
			$count = mysql_fetch_row($existe);
			if ($count[0]==0){
				$this->l->registro_log_string("cursos","funcion count_cursos no ha encontrado registros","username= ".$username);
				return true;
			}else{
				$this->l->registro_log_string("cursos","funcion count_cursos ha encontrado registros","username= ".$username);
				return false;
			}
		}catch (Exception $e) {
			$this->l->registro_log_string("cursos","error en funcion count_cursos",$e->getMessage());
			return false;
		}
	}
	
	/**
     * Verificar los cursos del usuario por el username
     */
	public function cursos($username){
		try{
			$todoloscursos = array();   //Para cuando se necesite mas de un dato en la consulta
			//$usuario;
			$sql = "SELECT mdl_course.fullname
					FROM mdl_user
					INNER JOIN mdl_role_assignments ON mdl_user.id = mdl_role_assignments.userid
					INNER JOIN mdl_context ON mdl_role_assignments.contextid = mdl_context.id
					INNER JOIN mdl_course ON mdl_context.instanceid = mdl_course.id
					where mdl_user.username = '".mysql_real_escape_string($username)."' and mdl_context.contextlevel = '50' and mdl_course.fullname not 			like '%sistencia%'";
			$consulta = mysql_query($sql);
			while($fila = mysql_fetch_assoc($consulta))
			{
				$this->todoloscursos[]=array_map("utf8_encode", $fila); //Para cuando se necesite mas de un dato en la consulta
				//$this->usuario=$fila;
			}
			$this->l->registro_log_string("cursos","funcion cursos ha encontrado registros","username= ".$username);
			return $this->todoloscursos;
		}catch (Exception $e) {
			$this->l->registro_log_string("cursos","error en funcion cursos",$e->getMessage());
			return false;
		}	
	}
	 
	/**
     * Count mensajes sin leer del alumno
     */
	 public function count_mensajes_sin_leer($username){
		try{		 
			$existe=mysql_query("SELECT count(*) FROM mdl_message 
								INNER JOIN mdl_user ON mdl_user.id = mdl_message.useridto	
									where mdl_user.username = '".mysql_real_escape_string($username)."'"); 
			$count = mysql_fetch_row($existe);
			if ($count[0]==0){
				$this->l->registro_log_string("mensajes_sin_leer","funcion count_mensajes_sin_leer no a encontrado registros","username= ".$username);
				return true;
			}else{
				$this->l->registro_log_string("mensajes_sin_leer","funcion count_mensajes_sin_leer  a encontrado registros","username= ".$username);
				return false;
			}
		}catch (Exception $e) {
			$this->l->registro_log_string("mensajes_sin_leer","error en funcion count_mensajes_sin_leer",$e->getMessage());
			return false;
		}
	}
	/**
     * obtener los mensajes sin leer del alumno
     */
	public function mensajes_sin_leer($username){
		try{
			$todoslosmensajessinleer = array();   //Para cuando se necesite mas de un dato en la consulta
			//$todoslospart;
			$sql = "SELECT mdl_message.id, mdl_message.useridfrom, mdl_message.useridto, mdl_message.subject, mdl_message.fullmessage, mdl_message.smallmessage, DATE_FORMAT(FROM_UNIXTIME(mdl_message.timecreated), '%e %b %Y  %H:%i:%s') AS fecha_creacion  FROM mdl_user
					INNER JOIN mdl_message ON mdl_user.id = mdl_message.useridto
						where mdl_user.username = '".mysql_real_escape_string($username)."'order by mdl_message.timecreated DESC";
			$consulta = mysql_query($sql);
			while($fila = mysql_fetch_assoc($consulta))
			{
				$this->todoslosmensajessinleer[]=array_map("utf8_encode", $fila); //Para cuando se necesite mas de un dato en la consulta
				//$this->usuario=$fila;
			}
			$this->l->registro_log_string("mensajes_sin_leer","funcion mensajes_sin_leer a traido datos","username= ".$username);
			return $this->todoslosmensajessinleer;
			//return $this->usuario;
			$this->db->__destructor();
		}catch (Exception $e) {
			$this->l->registro_log_string("mensajes_sin_leer","error en funcion mensajes_sin_leer",$e->getMessage());
			return false;
		}
	}
	/**
     * ingresar mensaje a un compañero
     */
	public function ingresar_mensaje($username,$user_id_from,$user_id_to,$subject,$mensaje,$fecha_unix){
	    try{
			$mensaje_1 = "Mensaje nuevo de (";
			$mensaje_2 = ")";
			//$sql_select = "SELECT mdl_user.firstname FROM mdl_user WHERE mdl_user.id = '".mysql_real_escape_string($user_id_to)."'";
			$sql_select = "SELECT * FROM mdl_user WHERE mdl_user.id = '".mysql_real_escape_string($user_id_from)."'";			
			$sql1 = mysql_query($sql_select);
			while($fila = mysql_fetch_assoc($sql1))
			{
				//$this->todoslosusuarios[]=$fila; //Para cuando se necesite mas de un dato en la consulta
				$resultado2 = $fila['firstname']." ".$fila['lastname'];
			}
			//while ($registro = mysql_fetch_array($registros)) {
		//		$res2 = $registro['firstname'];
		//}
			
			
			if(is_numeric($user_id_from) && is_numeric($user_id_to)){
				$sql = "INSERT INTO mdl_message(`useridfrom`, `useridto`, `subject`, `fullmessage`, `fullmessageformat`, `fullmessagehtml`, `smallmessage`, `notification`, `contexturl`, `contexturlname`, `timecreated`) VALUES ('".mysql_real_escape_string($user_id_from)."','".mysql_real_escape_string($user_id_to)."','".mysql_real_escape_string($mensaje_1.$resultado2.$mensaje_2)."','','','','".mysql_real_escape_string($mensaje)."','','','','".mysql_real_escape_string($fecha_unix)."')
		";
				$consulta = mysql_query($sql);
				if($consulta){
					$this->l->registro_log_string("ingresar_mensaje"," funcion ingresar mensaje a ingresado datos","username= ".$username.",user_id_from= ".$user_id_from.", user_id_to= ".$user_id_to.", subject= ".$subject.", mensaje= ".$mensaje);
					return true;	
				}
				else{
					$this->l->registro_log_string("ingresar_mensaje"," funcion ingresar mensaje no a ingresado datos","username= ".$username.",user_id_from= ".$user_id_from.", user_id_to= ".$user_id_to.", subject= ".$subject.", mensaje= ".$mensaje);
					return false;
				}
			}else{
				$this->l->registro_log_string("ingresar_mensaje"," funcion ingresar mensaje tiene datos invalidos","user_id_from= ".$user_id_from.", user_id_to= ".$user_id_to);
					return false;
			}
		}catch (Exception $e) {
			$this->l->registro_log_string("ingresar_mensaje","error al ingresar mensaje",$e->getMessage());
			return false;
		}
	}  	  
	/**
     * Count ver mensajes leidos
     */
	 public function count_ver_mensajes_leidos($username){
		 try{
			$existe=mysql_query("SELECT count(*) FROM mdl_message_read
									INNER JOIN mdl_user ON mdl_user.id = mdl_message_read.useridto
										where mdl_user.username = '".mysql_real_escape_string($username)."'"); 
			$count = mysql_fetch_row($existe);
			if ($count[0]==0){
				$this->l->registro_log_string("ver_mensajes_leidos","funcion count_ver_mensajes_leidos no a encontrado registros","username= ".$username);
				return true;
			}else{
				$this->l->registro_log_string("ver_mensajes_leidos","funcion count_ver_mensajes_leidos  a encontrado registros","username= ".$username);
				return false;
			}
		}catch (Exception $e) {
			$this->l->registro_log_string("ver_mensajes_leidos","error en funcion count_ver_mensajes_leidos",$e->getMessage());
			return false;
		}
	}
	/**
     * obtener los mensajes leidos del alumno
     */
	public function ver_mensajes_leidos($username){
		try{
			$todoslosmensajesleidos = array();   //Para cuando se necesite mas de un dato en la consulta
			//$todoslospart;
			$sql = "SELECT mdl_message_read.id, mdl_message_read.useridfrom, mdl_message_read.useridto, mdl_message_read.subject, mdl_message_read.fullmessage, mdl_message_read.smallmessage, DATE_FORMAT(FROM_UNIXTIME(mdl_message_read.timecreated), '%e %b %Y  %H:%i:%s') AS creado_el, DATE_FORMAT(FROM_UNIXTIME(mdl_message_read.timeread), '%e %b %Y  %H:%i:%s') AS leido_el FROM mdl_user
						INNER JOIN mdl_message_read ON mdl_user.id = mdl_message_read.useridto
							where mdl_user.username = '".mysql_real_escape_string($username)."'and mdl_message_read.timecreated <= UNIX_TIMESTAMP(now()) and mdl_message_read.timecreated >= (UNIX_TIMESTAMP(now()) - 2629743) order by mdl_message_read.timecreated DESC";
			$consulta = mysql_query($sql);
			while($fila = mysql_fetch_assoc($consulta))
			{
				$this->todoslosmensajesleidos[]=array_map("utf8_encode", $fila); //Para cuando se necesite mas de un dato en la consulta
				//$this->usuario=$fila;
			}
			$this->l->registro_log_string("ver_mensajes_leidos","funcion ver_mensajes_leidos a traido datos","username= ".$username);
			return $this->todoslosmensajesleidos;
			//return $this->usuario;
			$this->db->__destructor();
		}catch (Exception $e) {
			$this->l->registro_log_string("ver_mensajes_leidos","error en funcion ver_mensajes_leidos",$e->getMessage());
			return false;
		}
	}
	/**
     * Count ver mensajes del curso
     */
	 public function count_ver_mensajes_curso($username){
		 try{
			$existe=mysql_query("SELECT count(mdl_forum_discussions.name),count(mdl_forum_posts.message) 
								FROM mdl_course
								INNER JOIN mdl_forum_discussions ON mdl_course.id = mdl_forum_discussions.course
								INNER JOIN mdl_forum_posts ON mdl_forum_discussions.id = mdl_forum_posts.discussion
									where mdl_course.fullname = '".mysql_real_escape_string($username)."'"); 
			$count = mysql_fetch_row($existe);
			if ($count[0]==0){
				$this->l->registro_log_string("ver_mensajes_curso","funcion count_ver_mensajes_curso no ha encontrado registros","curso= ".$username);
				return true;
			}else{
				$this->l->registro_log_string("ver_mensajes_curso","funcion count_ver_mensajes_curso ha encontrado registros","curso= ".$username);
				return false;
			}
		}catch (Exception $e) {
			$this->l->registro_log_string("ver_mensajes_curso","error en funcion count_ver_mensajes_curso",$e->getMessage());
			return false;
		}
	}
	/**
     * obtener los mensajes del curso
     */
	public function ver_mensajes_curso($username){
		try{
			$todoslosmensajescurso = array();   //Para cuando se necesite mas de un dato en la consulta
			//$todoslospart;
			$sql = "SELECT mdl_forum_discussions.id, mdl_forum_discussions.name,mdl_forum_posts.message 
					FROM mdl_course
					INNER JOIN mdl_forum_discussions ON mdl_course.id = mdl_forum_discussions.course
					INNER JOIN mdl_forum_posts ON mdl_forum_discussions.id = mdl_forum_posts.discussion
						where mdl_course.fullname = '".mysql_real_escape_string($username)."'";
			$consulta = mysql_query($sql);
			while($fila = mysql_fetch_assoc($consulta))
			{
				$this->todoslosmensajescurso[]=array_map("utf8_encode", $fila); //Para cuando se necesite mas de un dato en la consulta
				//$this->usuario=$fila;
			}
			$this->l->registro_log_string("ver_mensajes_leidos","funcion ver_mensajes_leidos ha traido registros","curso= ".$username);
			return $this->todoslosmensajescurso;
			//return $this->usuario;
			$this->db->__destructor();
		}catch (Exception $e) {
			$this->l->registro_log_string("ver_mensajes_leidos","error en funcion ver_mensajes_leidos",$e->getMessage());
			return false;
		}
	}
	/**
     * Count documentos del curso
     */
	 public function count_documentos_curso($username){
		try{		 
			$existe=mysql_query("SELECT COUNT( * ) FROM mdl_course
								INNER JOIN mdl_resource ON mdl_course.id = mdl_resource.course
									WHERE mdl_course.fullname =  '".mysql_real_escape_string($username)."'"); 
			$count = mysql_fetch_row($existe);
			if ($count[0]==0){
				$this->l->registro_log_string("documentos_curso","funcion count_documentos_curso no ha encontrado registros","curso= ".$username);
				return true;
			}else{
				$this->l->registro_log_string("documentos_curso","funcion count_documentos_curso ha encontrado registros","curso= ".$username);
				return false;
			}
		}catch (Exception $e) {
			$this->l->registro_log_string("documentos_curso","error en funcion count_documentos_curso",$e->getMessage());
			return false;
		}
	}
	/**
     * obtener los documentos del curso
     */
	public function documentos_curso($username){
		try{
			$todoslosdocumentoscurso = array();   //Para cuando se necesite mas de un dato en la consulta
			//$todoslospart;
			$sql = "SELECT mdl_resource.id,  mdl_course.category, mdl_course.fullname, mdl_resource.course as id_curso, mdl_resource.name, DATE_FORMAT(FROM_UNIXTIME(mdl_resource.timemodified), '%e %b %Y  %H:%i:%s') AS disponible_desde FROM mdl_course 
					INNER JOIN mdl_resource ON mdl_course.id = mdl_resource.course
						where mdl_course.fullname = '".mysql_real_escape_string($username)."'order by mdl_resource.timemodified DESC";
			$consulta = mysql_query($sql);
			while($fila = mysql_fetch_assoc($consulta))
			{
				$this->todoslosdocumentoscurso[]=array_map("utf8_encode", $fila); //Para cuando se necesite mas de un dato en la consulta
				//$this->usuario=$fila;
			}
			$this->l->registro_log_string("documentos_curso","funcion documentos_curso a traido registros","curso= ".$username);
			return $this->todoslosdocumentoscurso;
			//return $this->usuario;
			$this->db->__destructor();
		}catch (Exception $e) {
			$this->l->registro_log_string("documentos_curso","error en funcion documentos_curso",$e->getMessage());
			return false;
		}
	}
	/**
     * Count eliminar mensajes leidos
     */
	 public function count_eliminar_mensajes_sin_leer($username){
		try{		 
			$existe=mysql_query("SELECT count(*) FROM mdl_message 
								INNER JOIN mdl_user ON mdl_user.id = mdl_message.useridto	
									where mdl_user.username = '".mysql_real_escape_string($username)."'"); 
			$count = mysql_fetch_row($existe);
			if ($count[0]==0){
				$this->l->registro_log_string("eliminar_mensajes_leidos","funcion count_eliminar_mensajes_leidos no a encontrado registros","username= ".$username);
				return true;
			}else{
				$this->l->registro_log_string("eliminar_mensajes_leidos","funcion count_eliminar_mensajes_leidos  a encontrado registros","username= ".$username);
				return false;
			}
		}catch (Exception $e) {
			$this->l->registro_log_string("eliminar_mensajes_leidos","error en funcion count_eliminar_mensajes_leidos",$e->getMessage());
			return false;
		}
	}
	/**
     * obtener los documentos del curso
     */
	public function eliminar_mensajes_leidos($username){
		try{
			$todoslosdocumentoscurso = array();   //Para cuando se necesite mas de un dato en la consulta
			//$todoslospart;
			$sql = "SELECT mdl_resource.id,  mdl_course.category, mdl_course.fullname, mdl_resource.course as id_curso, mdl_resource.name, DATE_FORMAT(FROM_UNIXTIME(mdl_resource.timemodified), '%e %b %Y  %H:%i:%s') AS disponible_desde FROM mdl_course 
					INNER JOIN mdl_resource ON mdl_course.id = mdl_resource.course
						where mdl_course.fullname = '".mysql_real_escape_string($username)."'";
			$consulta = mysql_query($sql);
			while($fila = mysql_fetch_assoc($consulta))
			{
				$this->todoslosdocumentoscurso[]=array_map("utf8_encode", $fila); //Para cuando se necesite mas de un dato en la consulta
				//$this->usuario=$fila;
			}
			$this->l->registro_log_string("eliminar_mensajes_leidos","funcion eliminar_mensajes_leidos a traido registros","id_mensaje= ".$username);
			return $this->todoslosdocumentoscurso;
			//return $this->usuario;
			$this->db->__destructor();
		}catch (Exception $e) {
			$this->l->registro_log_string("eliminar_mensajes_leidos","error en funcion eliminar_mensajes_leidos",$e->getMessage());
			return false;
		}
	}
	/**
     * Count de eventos
     */
	 public function count_eventos($username){
		$existe=mysql_query("SELECT count(*) FROM mdl_event inner join mdl_course on mdl_event.courseid = mdl_course.id WHERE ( mdl_event.timeduration + mdl_event.timestart) > UNIX_TIMESTAMP(now()) and mdl_event.courseid in (SELECT mdl_course.id FROM mdl_user INNER JOIN mdl_role_assignments ON mdl_user.id = mdl_role_assignments.userid INNER JOIN mdl_context ON mdl_role_assignments.contextid = mdl_context.id INNER JOIN mdl_course ON mdl_context.instanceid = mdl_course.id where mdl_user.username = '".mysql_real_escape_string($username)."' and mdl_context.contextlevel = '50') order by mdl_event.timestart DESC"); 
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
	public function eventos($username){
	    $todoloseventos = array();   //Para cuando se necesite mas de un dato en la consulta
	    //$usuario;
		$sql = "SELECT mdl_course.fullname,DATE_FORMAT(FROM_UNIXTIME(mdl_event.timestart), '%e %b %Y  %H:%i') AS desde, DATE_FORMAT(FROM_UNIXTIME( mdl_event.timeduration + mdl_event.timestart),'%e %b %Y  %H:%i') as hasta ,mdl_event.name FROM mdl_event inner join mdl_course on mdl_event.courseid = mdl_course.id WHERE ( mdl_event.timeduration + mdl_event.timestart) > UNIX_TIMESTAMP(now()) and mdl_event.courseid in (SELECT mdl_course.id FROM mdl_user INNER JOIN mdl_role_assignments ON mdl_user.id = mdl_role_assignments.userid INNER JOIN mdl_context ON mdl_role_assignments.contextid = mdl_context.id INNER JOIN mdl_course ON mdl_context.instanceid = mdl_course.id where mdl_user.username = '".mysql_real_escape_string($username)."' and mdl_context.contextlevel = '50') ";
		$consulta = mysql_query($sql);
		while($fila = mysql_fetch_assoc($consulta))
		{
			$this->todoloseventos[]=array_map("utf8_encode", $fila); //Para cuando se necesite mas de un dato en la consulta
			//$this->usuario=$fila;
		}
		return $this->todoloseventos;
		//return $this->usuario;
	}
	/**
     * Count de eventos atrasados
     */
	 public function count_eventos_atrasados($username){
		$existe=mysql_query("SELECT count(*) FROM mdl_event inner join mdl_course on mdl_event.courseid = mdl_course.id 
WHERE ( mdl_event.timeduration + mdl_event.timestart) <= UNIX_TIMESTAMP(now()) and mdl_event.timestart >((UNIX_TIMESTAMP(now()))-2419200) and mdl_event.courseid in (SELECT mdl_course.id FROM mdl_user INNER JOIN mdl_role_assignments ON mdl_user.id = mdl_role_assignments.userid INNER JOIN mdl_context ON mdl_role_assignments.contextid = mdl_context.id INNER JOIN mdl_course ON mdl_context.instanceid = mdl_course.id where mdl_user.username = '".mysql_real_escape_string($username)."' and mdl_context.contextlevel = '50') order by mdl_event.timestart"); 
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
	public function eventos_atrasados($username){
	    $todoloseventos = array();   //Para cuando se necesite mas de un dato en la consulta
	    //$usuario;
		$sql = "SELECT mdl_course.fullname,DATE_FORMAT(FROM_UNIXTIME(mdl_event.timestart), '%e %b %Y  %H:%i') AS desde, DATE_FORMAT(FROM_UNIXTIME( mdl_event.timeduration + mdl_event.timestart),'%e %b %Y  %H:%i') as hasta ,mdl_event.name 
FROM mdl_event inner join mdl_course on mdl_event.courseid = mdl_course.id 
WHERE ( mdl_event.timeduration + mdl_event.timestart) <= UNIX_TIMESTAMP(now()) and mdl_event.timestart >((UNIX_TIMESTAMP(now()))-2419200) and mdl_event.courseid in (SELECT mdl_course.id FROM mdl_user INNER JOIN mdl_role_assignments ON mdl_user.id = mdl_role_assignments.userid INNER JOIN mdl_context ON mdl_role_assignments.contextid = mdl_context.id INNER JOIN mdl_course ON mdl_context.instanceid = mdl_course.id where mdl_user.username = '".mysql_real_escape_string($username)."' and mdl_context.contextlevel = '50') order by mdl_event.timestart DESC";
		$consulta = mysql_query($sql);
		while($fila = mysql_fetch_assoc($consulta))
		{
			$this->todoloseventos[]=array_map("utf8_encode", $fila); //Para cuando se necesite mas de un dato en la consulta
			//$this->usuario=$fila;
		}
		return $this->todoloseventos;
		//return $this->usuario;
	}
	/**
     * Count de asistencia
     */
	 public function count_asistencia($username){
		$existe=mysql_query("SELECT count(*) 
							FROM mdl_attendance inner join mdl_attendance_sessions on mdl_attendance.id = mdl_attendance_sessions.attendanceid
							INNER JOIN mdl_attendance_log ON mdl_attendance_sessions.id = mdl_attendance_log.sessionid
							INNER JOIN mdl_user on mdl_attendance_log.studentid = mdl_user.id
							WHERE mdl_user.username = '".mysql_real_escape_string($username)."' and 
							mdl_attendance.course in 
							(SELECT mdl_course.id 
							FROM mdl_user INNER JOIN mdl_role_assignments ON mdl_user.id = mdl_role_assignments.userid 
							INNER JOIN mdl_context ON mdl_role_assignments.contextid = mdl_context.id INNER JOIN mdl_course ON mdl_context.instanceid = mdl_course.id 
							where mdl_user.username = '".mysql_real_escape_string($username)."' and mdl_context.contextlevel = '50' and mdl_course.fullname like '%Asistencia%')ORDER BY mdl_attendance_sessions.sessdate DESC"); 
		$count = mysql_fetch_row($existe);
    	if ($count[0]==0){
			return true;
		}else{
			return false;
		}
	}
	/**
     * Verificar la asistencia y la fecha de esta
     */
	public function asistencia($username){
	    $todoloseventos = array();   //Para cuando se necesite mas de un dato en la consulta
	    //$usuario;
		$sql = "SELECT DATE_FORMAT(FROM_UNIXTIME(mdl_attendance_sessions.sessdate), '%e %b %Y  %H:%i') AS fecha , mdl_attendance_statuses.description as estado
					FROM mdl_attendance inner join mdl_attendance_sessions on mdl_attendance.id = mdl_attendance_sessions.attendanceid
					INNER JOIN mdl_attendance_log ON mdl_attendance_sessions.id = mdl_attendance_log.sessionid
					INNER JOIN mdl_user on mdl_attendance_log.studentid = mdl_user.id
					INNER JOIN mdl_attendance_statuses on mdl_attendance_log.statusid = mdl_attendance_statuses.id
					WHERE mdl_user.username = '".mysql_real_escape_string($username)."' and 
					mdl_attendance.course in 
					(SELECT mdl_course.id 
					FROM mdl_user INNER JOIN mdl_role_assignments ON mdl_user.id = mdl_role_assignments.userid INNER JOIN mdl_context ON mdl_role_assignments.contextid = mdl_context.id INNER JOIN mdl_course ON mdl_context.instanceid = mdl_course.id 
					where mdl_user.username = '".mysql_real_escape_string($username)."' and mdl_context.contextlevel = '50' and mdl_course.fullname like '%Asistencia%')ORDER BY mdl_attendance_sessions.sessdate DESC";
		$consulta = mysql_query($sql);
		while($fila = mysql_fetch_assoc($consulta))
		{
			$this->todolaasistencia[]=array_map("utf8_encode", $fila); //Para cuando se necesite mas de un dato en la consulta
			//$this->usuario=$fila;
		}
		return $this->todolaasistencia;
		//return $this->usuario;
	}
	/**
     * Verificar el porcentaje de asistencia presente
     */
	public function porcentaje_presente($username){
	    $todoloseventos = array();   //Para cuando se necesite mas de un dato en la consulta
	    //$usuario;
		$sql = "SELECT (sum(mdl_attendance_statuses.grade)/mdl_attendance.grade)*100 AS porcentaje_presente
					FROM mdl_attendance inner join mdl_attendance_sessions on mdl_attendance.id = mdl_attendance_sessions.attendanceid
					INNER JOIN mdl_attendance_log ON mdl_attendance_sessions.id = mdl_attendance_log.sessionid
					INNER JOIN mdl_user on mdl_attendance_log.studentid = mdl_user.id
					INNER JOIN mdl_attendance_statuses on mdl_attendance_log.statusid = mdl_attendance_statuses.id
					WHERE mdl_user.username = '".mysql_real_escape_string($username)."' and 
					mdl_attendance.course in 
					(SELECT mdl_course.id 
					FROM mdl_user INNER JOIN mdl_role_assignments ON mdl_user.id = mdl_role_assignments.userid INNER JOIN mdl_context ON mdl_role_assignments.contextid = mdl_context.id INNER JOIN mdl_course ON mdl_context.instanceid = mdl_course.id 
					where mdl_user.username = '".mysql_real_escape_string($username)."' and mdl_context.contextlevel = '50' and mdl_course.fullname like '%Asistencia%')";
		$consulta = mysql_query($sql);
		while($fila = mysql_fetch_assoc($consulta))
		{
			$this->todolaasistencia[]=array_map("utf8_encode", $fila); //Para cuando se necesite mas de un dato en la consulta
			//$this->usuario=$fila;
		}
		return $this->todolaasistencia;
		//return $this->usuario;
	}
	/**
     * Verifica los dias presentes
     */
	public function dias_presente($username){
	    $todoloseventos = array();   //Para cuando se necesite mas de un dato en la consulta
	    //$usuario;
		$sql = "SELECT count(mdl_attendance_statuses.grade) AS dias_presentes
					FROM mdl_attendance inner join mdl_attendance_sessions on mdl_attendance.id = mdl_attendance_sessions.attendanceid
					INNER JOIN mdl_attendance_log ON mdl_attendance_sessions.id = mdl_attendance_log.sessionid
					INNER JOIN mdl_user on mdl_attendance_log.studentid = mdl_user.id
					INNER JOIN mdl_attendance_statuses on mdl_attendance_log.statusid = mdl_attendance_statuses.id
					WHERE mdl_user.username = '".mysql_real_escape_string($username)."' and mdl_attendance_statuses.acronym = 'P'  and 
					mdl_attendance.course in 
					(SELECT mdl_course.id 
					FROM mdl_user INNER JOIN mdl_role_assignments ON mdl_user.id = 	mdl_role_assignments.userid INNER JOIN mdl_context ON mdl_role_assignments.contextid = mdl_context.id INNER JOIN mdl_course ON mdl_context.instanceid = mdl_course.id 
					where mdl_user.username = '".mysql_real_escape_string($username)."' and mdl_context.contextlevel = '50' and mdl_course.fullname like '%Asistencia%')";
		$consulta = mysql_query($sql);
		while($fila = mysql_fetch_assoc($consulta))
		{
			$this->todolaasistencia[]=array_map("utf8_encode", $fila); //Para cuando se necesite mas de un dato en la consulta
			//$this->usuario=$fila;
		}
		return $this->todolaasistencia;
		//return $this->usuario;
	}
	/**
     * Verifica los dias ausentes
     */
	public function dias_ausente($username){
	    $todoloseventos = array();   //Para cuando se necesite mas de un dato en la consulta
	    //$usuario;
		$sql = "SELECT count(mdl_attendance_statuses.grade) AS dias_ausente
					FROM mdl_attendance inner join mdl_attendance_sessions on mdl_attendance.id = mdl_attendance_sessions.attendanceid
					INNER JOIN mdl_attendance_log ON mdl_attendance_sessions.id = mdl_attendance_log.sessionid
					INNER JOIN mdl_user on mdl_attendance_log.studentid = mdl_user.id
					INNER JOIN mdl_attendance_statuses on mdl_attendance_log.statusid = mdl_attendance_statuses.id
					WHERE mdl_user.username = '".mysql_real_escape_string($username)."' and mdl_attendance_statuses.acronym = 'I'  and 
					mdl_attendance.course in 
					(SELECT mdl_course.id 
					FROM mdl_user INNER JOIN mdl_role_assignments ON mdl_user.id = mdl_role_assignments.userid INNER JOIN mdl_context ON mdl_role_assignments.contextid = mdl_context.id INNER JOIN mdl_course ON mdl_context.instanceid = mdl_course.id 
					where mdl_user.username = '".mysql_real_escape_string($username)."' and mdl_context.contextlevel = '50' and mdl_course.fullname like '%Asistencia%')";
		$consulta = mysql_query($sql);
		while($fila = mysql_fetch_assoc($consulta))
		{
			$this->todolaasistencia[]=array_map("utf8_encode", $fila); //Para cuando se necesite mas de un dato en la consulta
			//$this->usuario=$fila;
		}
		return $this->todolaasistencia;
		//return $this->usuario;
	}
	/**
     * Verifica los dias de ausente justificado
     */
	public function dias_ausente_justificado($username){
	    $todoloseventos = array();   //Para cuando se necesite mas de un dato en la consulta
	    //$usuario;
		$sql = "SELECT count(mdl_attendance_statuses.grade) AS dias_ausente_justificado
						FROM mdl_attendance inner join mdl_attendance_sessions on mdl_attendance.id = mdl_attendance_sessions.attendanceid
						INNER JOIN mdl_attendance_log ON mdl_attendance_sessions.id = mdl_attendance_log.sessionid
						INNER JOIN mdl_user on mdl_attendance_log.studentid = mdl_user.id
						INNER JOIN mdl_attendance_statuses on mdl_attendance_log.statusid = mdl_attendance_statuses.id
						WHERE mdl_user.username = '".mysql_real_escape_string($username)."' and mdl_attendance_statuses.acronym = 'J'  and 
						mdl_attendance.course in 
						(SELECT mdl_course.id 
						FROM mdl_user INNER JOIN mdl_role_assignments ON mdl_user.id = 	mdl_role_assignments.userid INNER JOIN mdl_context ON mdl_role_assignments.contextid = mdl_context.id INNER JOIN mdl_course ON mdl_context.instanceid = mdl_course.id 
						where mdl_user.username = '".mysql_real_escape_string($username)."' and mdl_context.contextlevel = '50' and mdl_course.fullname like '%Asistencia%')";
		$consulta = mysql_query($sql);
		while($fila = mysql_fetch_assoc($consulta))
		{
			$this->todolaasistencia[]=array_map("utf8_encode", $fila); //Para cuando se necesite mas de un dato en la consulta
			//$this->usuario=$fila;
		}
		return $this->todolaasistencia;
		//return $this->usuario;
	}
	/**
     * Count mensajes del profesor al apoderado
     */
	 public function count_mensaje_profesor($user_id_from,$user_id_to){
		try{		 
			$existe=mysql_query("SELECT count(*) FROM mdl_user INNER JOIN mdl_message ON mdl_user.id = mdl_message.useridto where mdl_message.useridfrom = '".mysql_real_escape_string($user_id_from)."' and mdl_message.useridto = '".mysql_real_escape_string($user_id_to)."' order by mdl_message.timecreated DESC"); 
			$count = mysql_fetch_row($existe);
			if ($count[0]==0){
				$this->l->registro_log_string("documentos_curso","funcion count_documentos_curso no ha encontrado registros","curso= ".$username);
				return true;
			}else{
				$this->l->registro_log_string("documentos_curso","funcion count_documentos_curso ha encontrado registros","curso= ".$username);
				return false;
			}
		}catch (Exception $e) {
			$this->l->registro_log_string("documentos_curso","error en funcion count_documentos_curso",$e->getMessage());
			return false;
		}
	}
	/**
     * obtener los mensajes del profesor al alumno
     */
	public function mensaje_profesor($user_id_from,$user_id_to){
		try{
			$todoslosdocumentoscurso = array();   //Para cuando se necesite mas de un dato en la consulta
			//$todoslospart;
			
			$sql = "SELECT mdl_message_read.id, mdl_message_read.useridfrom, mdl_message_read.useridto, mdl_message_read.subject, mdl_message_read.fullmessage, mdl_message_read.smallmessage, DATE_FORMAT(FROM_UNIXTIME(mdl_message_read.timecreated), '%e %b %Y  %H:%i:%s') AS fecha_creacion FROM mdl_user
						INNER JOIN mdl_message_read ON mdl_user.id = mdl_message_read.useridto where mdl_message_read.useridfrom = '".mysql_real_escape_string($user_id_from)."' and mdl_message_read.useridto = '".mysql_real_escape_string($user_id_to)."' and mdl_message_read.timecreated <= UNIX_TIMESTAMP(now()) and mdl_message_read.timecreated >= (UNIX_TIMESTAMP(now()) - 2629743)
union all
SELECT mdl_message.id, mdl_message.useridfrom, mdl_message.useridto, mdl_message.subject, mdl_message.fullmessage, mdl_message.smallmessage, DATE_FORMAT(FROM_UNIXTIME(mdl_message.timecreated), '%e %b %Y %H:%i:%s') AS fecha_creacion FROM mdl_user INNER JOIN mdl_message ON mdl_user.id = mdl_message.useridto where mdl_message.useridfrom = '".mysql_real_escape_string($user_id_from)."' and mdl_message.useridto = '".mysql_real_escape_string($user_id_to)."'";
			$consulta = mysql_query($sql);
			while($fila = mysql_fetch_assoc($consulta))
			{
				$this->todoslosdocumentoscurso[]=array_map("utf8_encode", $fila); //Para cuando se necesite mas de un dato en la consulta
				//$this->usuario=$fila;
			}
			$this->l->registro_log_string("documentos_curso","funcion documentos_curso a traido registros","curso= ".$username);
			return $this->todoslosdocumentoscurso;
			//return $this->usuario;
			$this->db->__destructor();
		}catch (Exception $e) {
			$this->l->registro_log_string("documentos_curso","error en funcion documentos_curso",$e->getMessage());
			return false;
		}
	}
	/**
     * marcar leido
     */
	public function marcar_leido($username){
	    try{
				
				$sql = "INSERT INTO `mdl_message_read`(`useridfrom`, `useridto`, `subject`, `fullmessage`, `fullmessageformat`, `fullmessagehtml`, `smallmessage`, `notification`, `contexturl`, `contexturlname`, `timecreated`, `timeread`) 
SELECT `useridfrom`, `useridto`, `subject`, `fullmessage`, `fullmessageformat`, `fullmessagehtml`, `smallmessage`, `notification`, `contexturl`, `contexturlname`, `timecreated`, UNIX_TIMESTAMP(current_timestamp) FROM `mdl_message` WHERE id = '".mysql_real_escape_string($username)."'";

				$sql2 = "DELETE FROM `mdl_message` WHERE id = '".mysql_real_escape_string($username)."'";
				$consulta = mysql_query($sql);
				if($consulta){
					$consulta2 = mysql_query($sql2);
					if($consulta2){
					   return true;
					}else{
					return false;	
					}
				}
				else{
					
					return false;
				}
			
		}catch (Exception $e) {
			
			return false;
		}
	}  	  

}
?>
