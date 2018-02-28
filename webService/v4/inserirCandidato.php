<?php 

require_once '../includes/DbOperations.php';

if($_SERVER['REQUEST_METHOD']=='POST'){
	if(isset($_POST['nome']) and isset($_POST['telefone'])){
	
		$db = new DbOperations(); 
		$result = $db->createCandidato($_POST['nome'], $_POST['id_vaga'], $_POST['telefone'], $_POST['email'], $_POST['endereco'], $_POST['objetivo'], $_POST['formacao_academica'], $_POST['experiencia_profissional'], $_POST['informacao_adicional']);
		if($result == 1){
			$response['message'] = "Curriculum enviado com sucesso!";
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
