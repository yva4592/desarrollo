<?php
header('Content-Type: text/html; charset=UTF-8');
header('Content-Type: text/html; charset=iso-8859-1');
$usuario    = $_POST['username'];
$rol 	    = $_POST['rol'];
$password 	= $_POST['password'];
$nombre 	= $_POST['nombre'];
$apellido   = $_POST['apellido'];

require_once '../modelo/funciones_rbsystem.php';
$db = new funciones_BD();

	if($db->isuserexist($usuario,$password)){

	echo(" Este usuario ya existe ingrese otro diferente!");
	}else{

		if($db->adduser($usuario,$rol,$password,$nombre,$apellido))
		{	echo(" El usuario fue agregado a la Base de Datos correctamente.");			
			 }else{
			echo(" ha ocurrido un error al ingresar el usuario revisar adduser.php");
			}		

	}



?>



