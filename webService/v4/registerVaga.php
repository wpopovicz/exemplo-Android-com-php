<?php 

require_once '../includes/DbOperations.php';

if($_SERVER['REQUEST_METHOD']=='POST'){
	if(isset($_POST['titulo']) and isset($_POST['atividade'])){

		$db = new DbOperations(); 

		$result = $db->createVaga($_POST['titulo'], $_POST['setor'], $_POST['cargo'], $_POST['atividade'], $_POST['requisito'], $_POST['escolaridade']);
		if($result == 1){
			$response['error'] = false; 
		}elseif($result == 2){
			$response['error'] = true; 
			$response['message'] = "Ocorreu algum erro, tente novamente";			
		}

	}else{
		$response['error'] = true; 
		$response['message'] = "Os campos obrigatórios estão faltando";
	}
}else{
	$response['error'] = true; 
	$response['message'] = "Cadastro inválido";
}

header('Content-Type: application/json');
echo json_encode($response);