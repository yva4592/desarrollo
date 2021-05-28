<?php
header('Content-Type: text/html; charset=UTF-8');
header('Content-Type: text/html; charset=iso-8859-1');
class Encriptacion{

	/*
	* FUNCION ENCRIPTACION
	*/
	public function contructor(){
		require_once '../config.php';
		require_once 'acceso.php';
		
		}
	public function encrypt($string,$key)    {
		
  		$result = '';
   		for($i=0; $i<strlen($string); $i++) {
			$char = substr($string, $i, 1);
			$keychar = substr($key, ($i % strlen($key))-1, 1);
			$char = chr(ord($char)+ord($keychar));
			$result.=$char;
   		}
   		return base64_encode($result);
	}
	
	/*
	* FUNCION DESENCRIPTADORA
	*/
	
	public function decrypt($string, $key) {
	   $result = '';
	   $string = base64_decode($string);
	   for($i=0; $i<strlen($string); $i++) {
		  $char = substr($string, $i, 1);
		  $keychar = substr($key, ($i % strlen($key))-1, 1);
		  $char = chr(ord($char)-ord($keychar));
		  $result.=$char;
	   }
	   return $result;
	}
}
?>