<?php
class registros_log_apoderado
{	
	/**************************************************************
	***************************************************************
	******* parametros de entrada  (String, String, ARRAY)*********
	***************************************************************
	***************************************************************/
	public function registro_log_array_apoderado($nombrelog,$mensaje,$datos)
	{
		require_once '../config.php';
		$nombrelog = str_replace(' ', '', $nombrelog);
		$log = LOG;
		$ruta = RUTA;
		$escritura = ESCRITURA;
		if($log == 1)
		{
			  $file=fopen($ruta."/".$nombrelog."-log.txt",$escritura) or die("Problemas al escribir el archivo");
			  $fechaServer = date("d/m/Y");
			  $horaServer = date("h:i:s a");
			  fputs($file,"\n");
			  fputs($file,"***********************FECHA = ".$fechaServer."  HORA = ".$horaServer."*************************");
			  fputs($file,"\n");
			  fputs($file,"FECHA = ".date("d/m/Y"));
			  fputs($file,"\n");
			  fputs($file,"HORA = ".date("h:i:s a"));
			  fputs($file,"\n");
			  fputs($file,"IP = ".$_SERVER['REMOTE_ADDR']);
			  fputs($file,"\n");
			  fputs($file,"Nombre de log:".$nombrelog);
			  fputs($file,"\n");
			  fputs($file,"Mensaje :".$mensaje);
			  fputs($file,"\n");
			  foreach($datos as $d){
				   $cabeceras = implode(",", array_keys($d)); //cabeceras del arreglo
				   $resultado = implode(",", $d); //datos  del arreglo
				   
				   fputs($file,"\n");
				   fputs($file,"CABECERAS :".$cabeceras);
				   fputs($file,"\n");
				   fputs($file,"DATOS :".$resultado);
				
				} 
			  fputs($file,"\n");
			  fputs($file,"***********************FECHA = ".$fechaServer."  HORA = ".$horaServer."*************************");
			  fputs($file,"\n");
			  fclose($file);			
		}
		if($log == 0)
		{
		  return "el archivo log no registra datos, la opcion se encuentra desactivada";
		}
		else
		{
		  return "error inesperado, no se encuentra ninguna de las dos opciones activadas";
		}	
	}
	/**************************************************************
	***************************************************************
	******* parametros de entrada  (String, String, STRING)*********
	***************************************************************
	***************************************************************/
	public function registro_log_string_apoderado($nombrelog,$mensaje,$datos)
	{
		require_once '../config.php';
		$nombrelog = str_replace(' ', '', $nombrelog);
		$log = LOG;
		$ruta = RUTA;
		$escritura = ESCRITURA;
		if($log == 1)
		{
			  $file=fopen($ruta."/".$nombrelog."-log.txt",$escritura) or die("Problemas al escribir el archivo");
			  $fechaServer = date("d/m/Y");
			  $horaServer = date("h:i:s a");
			  fputs($file,"\n");
			  fputs($file,"***********************FECHA = ".$fechaServer."  HORA = ".$horaServer."*************************");
			  fputs($file,"\n");
			  fputs($file,"FECHA = ".date("d/m/Y"));
			  fputs($file,"\n");
			  fputs($file,"HORA = ".date("h:i:s a"));
			  fputs($file,"\n");
			  fputs($file,"IP = ".$_SERVER['REMOTE_ADDR']);
			  fputs($file,"\n");
			  fputs($file,"Nombre de log:".$nombrelog);
			  fputs($file,"\n");
			  fputs($file,"Mensaje :".$mensaje);
			  fputs($file,"\n");
			  fputs($file,"Datos a mostrar :".$datos); 
			  fputs($file,"\n");
			  fputs($file,"***********************FECHA = ".$fechaServer."  HORA = ".$horaServer."*************************");
			  fputs($file,"\n");
			  fclose($file);			
		}
		if($log == 0)
		{
		  return "el archivo log no registra datos, la opcion se encuentra desactivada";
		}
		else
		{
		  return "error inesperado, no se encuentra ninguna de las dos opciones activadas";
		}	
	}
}
?>