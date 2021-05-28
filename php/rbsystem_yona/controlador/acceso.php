<?php
header('Content-Type: text/html; charset=UTF-8');
header('Content-Type: text/html; charset=iso-8859-1');
$opcion   = $_REQUEST['opcion'];
session_start();
switch($opcion){
	
	case "login":	
		/*$username = trim($_REQUEST['username']);
		$password = trim($_REQUEST['password']);
		require_once '../modelo/funciones_rbsystem.php';
		require_once 'registros_log.php';
		$log =  new registros_log();
		$db = new funciones_BD();
			if($db->login($username,$password)){
				$resultado[]=array("logstatus"=>"0");
				//$resultado[]=array("logstatus"=>"0","logstatus2"=>"1"); // si se quiere mostrar otro mensaje en el mismo arreglo
				$resultado[]=array("mensaje"=>"error al ingresar datos");
			}else{
				$rol = new funciones_BD();
				$persona = $rol->rol($username,$password);
				
				$resultado[]=array("logstatus"=>"1");
				$resultado[]=$persona;
				
			}*/
			//Config de moodle
			require('../../../config.php');
            $username = trim($_REQUEST['username']);
			$password = trim($_REQUEST['password']);
			$dashboard = $CFG->wwwroot;
			if($user = authenticate_user_login($username, $password))
			{
			//require_login();
				if(complete_user_login($user))
				{
				$resultado[]=array("logstatus"=>"1");
				$resultado[]=array("RUT"=>$USER->username,"USER_ID"=>$USER->id,"NOMBRE"=>$USER->firstname,"APELLIDO"=>$USER->lastname,"email"=>$USER->email);
				$token = time();
				$resultado[]=array("token"=>$token+1800);
				}
				else
				{
				   $resultado[]=array("logstatus"=>"0");
				   $resultado[]=array("mensaje"=>"error al conectarse con moodle");
				   
				  // echo json_encode($resultado);
				}
			}
			else
			{
				require_once '../modelo/funciones_rbsystem.php';
				require_once 'registros_log_apoderado.php';
				$log_apoderado =  new registros_log_apoderado();
				$db = new funciones_BD();
				if($db->count_apoderado($username,$password)){
					$resultado[]=array("logstatus"=>"0");
					$resultado[]=array("mensaje"=>"error al ingresar datos");
				}else{
				if($db->count_apoderado_alumno($username)){
					$resultado[]=array("logstatus"=>"2");
					$resultado[]=array("mensaje"=>"usted no cuenta con alumnos regitrados");
				}else{
					$rol = new funciones_BD();
					$persona = $rol->rol_apoderado($username,$password);
					
					$resultado[]=array("logstatus"=>"3");
					$token = time();
					$resultado[] = $persona;
					$resultado[]=array("token"=>$token+1800);	
				}
			}
			}
			//$log->registro_log_array("login","ingreso a login",$resultado);
			echo json_encode($resultado);
		break;
	
	case "login_apoderado":	
		$username = trim($_REQUEST['username']);
		$password = trim($_REQUEST['password']);
		require_once '../modelo/funciones_rbsystem.php';
		require_once 'registros_log_apoderado.php';
		$log_apoderado =  new registros_log_apoderado();
		$db = new funciones_BD();
			if($db->count_apoderado($username,$password)){
				$resultado[]=array("logstatus"=>"0");
				$resultado[]=array("mensaje"=>"error al ingresar datos");
			}else{
				if($db->count_apoderado_alumno($username)){
					$resultado[]=array("logstatus"=>"0");
					$resultado[]=array("mensaje"=>"usted no cuenta con alumnos regitrados");
				}else{
					$rol = new funciones_BD();
					$persona = $rol->rol_apoderado($username,$password);
					
					$resultado[]=array("logstatus"=>"1");
					$resultado[] = $persona;	
				}
			}
			$log_apoderado->registro_log_array_apoderado("login_apoderado","login_apoderado",$resultado);
			echo json_encode($resultado);
		break;
	
	case "alumno_apoderado":	
		$username = trim($_REQUEST['username']);
		$token = trim($_REQUEST['token']);
		if(time() >= $token) { 
			$asistenciapersona[]=array("RUT"=>"error_sesion","mensaje"=>"no se a encontrado la sesion del usuario");
			echo json_encode($asistenciapersona);
			
		}else{ 
			
			require_once '../modelo/funciones_rbsystem.php';
			require_once 'registros_log_apoderado.php';
			$log_apoderado =  new registros_log_apoderado();
			$db = new funciones_BD();
				if($db->count_apoderado_alumno($username)){
						$resultado[]=array("logstatus"=>"0");
						$resultado[]=array("mensaje"=>"usted no cuenta con alumnos regitrados");
				}else{
					$apoderado = new funciones_BD();		
					$resultado = $apoderado->apoderado_alumno($username);	
				}
		}
			$log_apoderado->registro_log_array_apoderado("alumno_apoderado","login_apoderado",$resultado);
			echo json_encode($resultado);
		break;
	
	
	case "asistencia":	
		 	$username = trim($_REQUEST['username']);
			/////////////
			$token = trim($_REQUEST['token']);
	        if(time() >= $token) { 
				$asistenciapersona[]=array("porcentaje_presente"=>"error_sesion","mensaje"=>"no se a encontrado la sesion del usuario");
				echo json_encode($asistenciapersona);
				
			}else{ 
			///////////
				require_once '../modelo/funciones_moodle.php';
				require_once '../config.php';
				require_once 'registros_log.php';
				$log =  new registros_log();
				$db = new funciones_BD();
					if($db->count_asistencia($username)){
						$asistenciapersona[]=array("logstatus"=>"0");
					}else{	
							$asistencia = new funciones_BD();
							$asistenciapersona = $asistencia->porcentaje_presente($username);
							$asistenciapersona = $asistencia->dias_presente($username);
							$asistenciapersona = $asistencia->dias_ausente($username);
							$asistenciapersona = $asistencia->dias_ausente_justificado($username);
							$asistenciapersona = $asistencia->asistencia($username);						
					}
			}
				$log->registro_log_array("asistencia","ingreso a asistencia",$asistenciapersona);
				echo json_encode($asistenciapersona);
				
		break;
		
	case "notas":	
		$username = trim($_REQUEST['username']);
		$token = trim($_REQUEST['token']);
		if(time() >= $token) { 
			$asistenciapersona[]=array("id"=>"error_sesion","mensaje"=>"no se a encontrado la sesion del usuario");
			echo json_encode($asistenciapersona);
			
		}else{ 
			require_once '../modelo/funciones_moodle.php';
			require_once '../config.php';
			require_once 'registros_log.php';
			$log =  new registros_log();
			$db = new funciones_BD();
				if($db->count_notas($username)){
					$notapersona[]=array("logstatus"=>"0");
				}else{
					$notas = new funciones_BD();
					$notapersona = $notas->notas($username);
				}
		}
			$log->registro_log_array("notas","ingreso a notas",$notapersona);
			echo json_encode($notapersona);
			
		break;
		
	case "participantes_curso":	
		$username = trim($_REQUEST['username']);
		$token = trim($_REQUEST['token']);
	        if(time() >= $token) { 
				$asistenciapersona[]=array("id"=>"error_sesion","mensaje"=>"no se a encontrado la sesion del usuario");
				echo json_encode($asistenciapersona);
				
			}else{ 
				require_once '../modelo/funciones_moodle.php';
				require_once '../config.php';
				require_once 'registros_log.php';
				$log =  new registros_log();
				$db = new funciones_BD();
					if($db->count_participantes_curso($username)){
						$participantes_curso_persona[]=array("logstatus"=>"0");
					}else{
						$participantes_curso = new funciones_BD();
						$participantes_curso_persona = $participantes_curso->participantes_curso($username);
					}
			}
			$log->registro_log_array("participantes_curso","ingreso a participantes_curso",$participantes_curso_persona);
			echo json_encode($participantes_curso_persona);
		break;
	
	case "profesor_del_curso":	
		$username = trim($_REQUEST['username']);
		$token = trim($_REQUEST['token']);
		if(time() >= $token) { 
			$cursoperson[]=array("id"=>"error_sesion","mensaje"=>"no se a encontrado la sesion del usuario");
			echo json_encode($cursoperson);
			
		}else{
			require_once '../modelo/funciones_moodle.php';
			require_once '../config.php';
			require_once 'registros_log.php';
			$log =  new registros_log();
			$db = new funciones_BD();
				if($db->count_profesor_del_curso($username)){
					$participante_profesor_del_curso[]=array("logstatus"=>"0");
				}else{
					$participantes_curso = new funciones_BD();
					$participante_profesor_del_curso = $participantes_curso->profesor_del_curso($username);
				}
		}
			$log->registro_log_array("participantes_curso_profesor","ingreso a participantes_curso_profesor",$participante_profesor_del_curso);
			echo json_encode($participante_profesor_del_curso);
		break;
		
	case "cursos":	
		$username = trim($_REQUEST['username']);
		$token = trim($_REQUEST['token']);
	        if(time() >= $token) { 
				$cursoperson[]=array("fullname"=>"error_sesion","mensaje"=>"no se a encontrado la sesion del usuario");
				echo json_encode($cursoperson);
				
			}else{ 			
			require_once '../modelo/funciones_moodle.php';
			require_once 'registros_log.php';
			$log =  new registros_log();
			$db = new funciones_BD();
				if($db->count_cursos($username)){
					$cursopersona[]=array("logstatus"=>"0");
				}else{
					$cursos = new funciones_BD();
					$cursopersona = $cursos->cursos($username);
				}
			}
			$log->registro_log_array("cursos","ingreso a cursos",$cursopersona);
			echo json_encode($cursopersona);
		break;
		
	case "horarios":	
		$username = trim($_REQUEST['username']);
		require_once '../modelo/funciones_rbsystem.php';
		require_once 'registros_log.php';
		$log =  new registros_log();
		$db = new funciones_BD();
			if($db->count_horarios($username)){
				$horariopersona[]=array("logstatus"=>"0");
			}else{
				$horarios = new funciones_BD();
				$horariopersona = $horarios->horarios($username);
			}
			$log->registro_log_array("horarios","ingreso a horarios",$horariopersona);
			echo json_encode($horariopersona);
		break;
		
	/*mensajes recibidos sin leer*/	
	case "mensajes_sin_leer":	
		$username = trim($_REQUEST['username']);
		$token = trim($_REQUEST['token']);
		if(time() >= $token) { 
			$cursoperson[]=array("id"=>"error_sesion","mensaje"=>"no se a encontrado la sesion del usuario");
			echo json_encode($cursoperson);
			
		}else{ 
			require_once '../modelo/funciones_moodle.php';
			require_once '../config.php';
			require_once 'registros_log.php';
			$log =  new registros_log();
			$db = new funciones_BD();
				if($db->count_mensajes_sin_leer($username)){
					$mensajessinleerpersona[]=array("logstatus"=>"0");
				}else{
					$mensajes = new funciones_BD();
					$mensajessinleerpersona = $mensajes->mensajes_sin_leer($username);
				}
		}
			$log->registro_log_array("mensajes_sin_leer","ingreso a mensajes_sin_leer",$mensajessinleerpersona);
			echo json_encode($mensajessinleerpersona);
		break;
		
	/*ingresar mensaje*/	
	case "ingresar_mensaje":	
		$username 	  = trim($_REQUEST['username']);
		$user_id_from = trim($_REQUEST['user_id_from']);
		$user_id_to   = trim($_REQUEST['user_id_to']);
		$subject   	  = trim($_REQUEST['subject']);
		$mensaje      = trim($_REQUEST['mensaje']);
		$fecha_unix   = time();
        $token = trim($_REQUEST['token']);
		if(time() >= $token) { 
			$cursoperson[]=array("logstatus"=>"error_sesion","mensaje"=>"no se a encontrado la sesion del usuario");
			echo json_encode($cursoperson);
			
		}else{ 
			require_once '../modelo/funciones_moodle.php';
			require_once '../config.php';
			require_once 'registros_log.php';
			$log =  new registros_log();
			$mensajes = new funciones_BD();
				$ingresarmensajepersona = $mensajes->ingresar_mensaje($username,$user_id_from,$user_id_to,$subject,$mensaje,$fecha_unix);
			if($ingresarmensajepersona){
				$resultado[]=array("logstatus"=>"1");
				$resultado[]=array("resultado"=>"mensaje ingresado");
			}
			else{
				$resultado[]=array("logstatus"=>"0");
			}
		}
		$log->registro_log_array("ingresar_mensaje","ingreso a ingresar_mensaje",$resultado);
		echo json_encode($resultado);
		
		break;
		
		/*mensajes recibidos ya leidos*/	
	case "ver_mensajes_leidos":	
		$username = trim($_REQUEST['username']);
		$token = trim($_REQUEST['token']);
		if(time() >= $token) { 
			$cursoperson[]=array("id"=>"error_sesion","mensaje"=>"no se a encontrado la sesion del usuario");
			echo json_encode($cursoperson);
			
		}else{
			require_once '../modelo/funciones_moodle.php';
			require_once '../config.php';
			require_once 'registros_log.php';
			$log =  new registros_log();
			$db = new funciones_BD();
				if($db->count_ver_mensajes_leidos($username)){
					$mensajesleidospersona[]=array("logstatus"=>"0");
				}else{
					$mensajesleidos = new funciones_BD();
					$mensajesleidospersona = $mensajesleidos->ver_mensajes_leidos($username);
				}
		}
			$log->registro_log_array("ver_mensajes_leidos","ingreso a ver_mensajes_leidos",$mensajesleidospersona);
			echo json_encode($mensajesleidospersona);
		break;
	/*ver los mensajes que tiene el curso*/
	case "ver_mensajes_curso":	
		$username = trim($_REQUEST['username']);
        
		require_once '../modelo/funciones_moodle.php';
		require_once '../config.php';
		require_once 'registros_log.php';
		$log =  new registros_log();
		$db = new funciones_BD();
			if($db->count_ver_mensajes_curso($username)){
				$mensajescurso[]=array("logstatus"=>"0");
			}else{
				$mensajes = new funciones_BD();
				$mensajescurso = $mensajes->ver_mensajes_curso($username);
			}
			$log->registro_log_array("ver_mensajes_curso","ingreso a ver_mensajes_curso",$mensajescurso);
			echo json_encode($mensajescurso);
		break;
		
	/*ver los documentos del curso*/
	case "documentos_curso":	
		$username = trim($_REQUEST['username']);
        $token = trim($_REQUEST['token']);
		if(time() >= $token) { 
			$cursoperson[]=array("id"=>"error_sesion","mensaje"=>"no se a encontrado la sesion del usuario");
			echo json_encode($cursoperson);
			
		}else{
			require_once '../modelo/funciones_moodle.php';
			require_once '../config.php';
			require_once 'registros_log.php';
			$log =  new registros_log();
			$db = new funciones_BD();
				if($db->count_documentos_curso($username)){
					$documentoscurso[]=array("logstatus"=>"0");
				}else{
					$documento = new funciones_BD();
					$documentoscurso = $documento->documentos_curso($username);
				}
			}
			$log->registro_log_array("documentos_curso","ingreso a documentos_curso",$documentoscurso);
			echo json_encode($documentoscurso);
		break;
		
	/*eliminar los mensajes leidos , recibe el id del mensaje a eliminar*/
	case "eliminar_mensajes_leidos":	
		$username = trim($_REQUEST['username']);
        
		require_once '../modelo/funciones_moodle.php';
		require_once '../config.php';
		require_once 'registros_log.php';
		$log =  new registros_log();
		$db = new funciones_BD();
			if($db->count_eliminar_mensajes_sin_leer($username)){
				$eliminar_mensajes_sin_leer[]=array("logstatus"=>"0");
			}else{
				$mensaje = new funciones_BD();
				$eliminar_mensajes_sin_leer = $mensaje->eliminar_mensajes_sin_leer($username);
			}
			$log->registro_log_array("eliminar_mensajes_leidos","ingreso a eliminar_mensajes_leidos",$eliminar_mensajes_sin_leer);
			echo json_encode($eliminar_mensajes_sin_leer);
		break;
	
	/*Muestra los eventos publicados por el profesor, al alumno */	
	case "eventos":	
		$username = trim($_REQUEST['username']);
		/////////////
		$token = trim($_REQUEST['token']);
		if(time() >= $token) { 
			$asistenciapersona[]=array("fullname"=>"error_sesion","mensaje"=>"no se a encontrado la sesion del usuario");
			echo json_encode($asistenciapersona);
			
		}else{ 
		///////////			
		require_once '../modelo/funciones_moodle.php';
		require_once 'registros_log.php';
		$log		   =  new registros_log();
		$db = new funciones_BD();
			if($db->count_eventos($username)){
				$eventoAlumno[]=array("logstatus"=>"0");
				$eventoAlumno[]=array("mensaje"=>"no se encontraron eventos");
			}else{
				$evento = new funciones_BD();
				$eventoAlumno = $evento->eventos($username);
			}
		}
			$log ->registro_log_array("eventos","mostrando_los_eventos",$eventoAlumno);
			echo json_encode($eventoAlumno);
		break;
		
	/*Muestra los eventos atrasados publicados por el profesor, al alumno */	
	case "eventos_atrasados":	
		$username = trim($_REQUEST['username']);
		$token = trim($_REQUEST['token']);
		if(time() >= $token) { 
			$asistenciapersona[]=array("fullname"=>"error_sesion","mensaje"=>"no se a encontrado la sesion del usuario");
			echo json_encode($asistenciapersona);
			
		}else{ 
			require_once '../modelo/funciones_moodle.php';
			require_once 'registros_log.php';
			$log		   =  new registros_log();
			$db = new funciones_BD();
				if($db->count_eventos_atrasados($username)){
					$eventoAlumno[]=array("logstatus"=>"0");
					$eventoAlumno[]=array("mensaje"=>"no se encontraron eventos atrasados");
				}else{
					$evento = new funciones_BD();
					$eventoAlumno = $evento->eventos_atrasados($username);
				}
		}
			$log ->registro_log_array("eventos_atrasados","mostrando_los_eventos_atrasados",$eventoAlumno);
			echo json_encode($eventoAlumno);
		break;
	/*Muestra los eventos atrasados publicados por el profesor, al alumno */	
	 case "mensajes_profesor":	
		$user_id_from = trim($_REQUEST['user_id_from']);
		$user_id_to   = trim($_REQUEST['user_id_to']);
		$token = trim($_REQUEST['token']);
		if(time() >= $token) { 
			$asistenciapersona[]=array("id"=>"error_sesion","mensaje"=>"no se a encontrado la sesion del usuario");
			echo json_encode($asistenciapersona);
			
		}else{ 
			require_once '../modelo/funciones_moodle.php';
			require_once 'registros_log.php';
			$log		   =  new registros_log();
			$db = new funciones_BD();
				if($db->count_mensaje_profesor($user_id_from,$user_id_to)){
					$eventoAlumno[]=array("logstatus"=>"0");
					$eventoAlumno[]=array("mensaje"=>"no se encontraron mensajes");
				}else{
					$evento = new funciones_BD();
					$eventoAlumno = $evento->mensaje_profesor($user_id_from,$user_id_to);
				}
		}
			$log ->registro_log_array("eventos_atrasados","mostrando_los_eventos_atrasados",$eventoAlumno);
			echo json_encode($eventoAlumno);
		break;
		/*Marca como leido el mensaje recibido */	
	/*ingresar mensaje*/	
	case "marcar_leido":	
		$username 	  = trim($_REQUEST['username']);
		$token = trim($_REQUEST['token']);
		if(time() >= $token) { 
			$cursoperson[]=array("logstatus"=>"error_sesion","mensaje"=>"no se a encontrado la sesion del usuario");
			echo json_encode($cursoperson);
			
		}else{ 
			require_once '../modelo/funciones_moodle.php';
			require_once '../config.php';
			require_once 'registros_log.php';
			$log =  new registros_log();
			$mensajes = new funciones_BD();
				$ingresarmensajepersona = $mensajes->marcar_leido($username);
			if($ingresarmensajepersona){
				$resultado[]=array("logstatus"=>"1");
				$resultado[]=array("resultado"=>"mensaje ingresado");
			}
			else{
				$resultado[]=array("logstatus"=>"0");
			}
		}
		$log->registro_log_array("ingresar_mensaje","ingreso a ingresar_mensaje",$resultado);
		echo json_encode($resultado);
		
		break;
}
?>
