<?php

require_once '../includes/DbOperations.php';

if($_SERVER['REQUEST_METHOD']=='POST'){


	$db = new DbOperations(); 
	$response = array(); 
	$response = $db->listaVaga();
	
	header('Content-Type: application/json');
	echo json_encode(array("vaga"=>$response));
	
}

?>