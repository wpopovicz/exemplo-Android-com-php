<?php 

	class DbOperations{

		private $con; 

		function __construct(){

			require_once dirname(__FILE__).'/DbConnect.php';

			$db = new DbConnect();

			$this->con = $db->connect();

		}
				
		public function createVaga($titulo, $setor, $cargo, $atividade, $requisito, $escolaridade){
			$stmt = $this->con->prepare("INSERT INTO vaga (titulo, setor, cargo, atividade, requisito, escolaridade) VALUES (?, ?, ?, ?, ?, ?);");
			$stmt->bind_param("ssssss",$titulo,$setor,$cargo,$atividade,$requisito, $escolaridade);

			if($stmt->execute()){
				return 1; 
			}else{
				return 2; 
			}
			
		}
		
		public function listaVaga(){
			$stmt = $this->con->prepare("SELECT * FROM vaga WHERE status_registro = 1;");
			if($stmt->execute()){
				$stmt->bind_result($id, $titulo, $setor, $cargo, $atividade, $requisito, $escolaridade, $status_registro);
				$stmt->store_result();
				while($stmt->fetch()) {
			        	$response[] = array("id" => $id, "titulo" => $titulo, "setor" => $setor, "cargo" => $cargo, "atividade" => $atividade, "requisito" => $requisito, "escolaridade" => $escolaridade, "error" => false, "message" => "ok");
			    	}
		    	}else{
		    		$response[] =array("error" => true, "message" => "Erro na execução");
		    	}
			return $response;
		}
		
		public function listaCandidato($idVaga){
			$stmt = $this->con->prepare("SELECT * FROM candidato WHERE id_vaga = ? AND status_registro = 1;");
			$stmt->bind_param("i",$idVaga);
			if($stmt->execute()){
				$stmt->bind_result($id, $id_vaga, $nome, $telefone, $email, $endereco, $objetivo, $formacao_academica, $experiencia_profissional, $informacao_adicional, $status_registro);
				$stmt->store_result();
				while($stmt->fetch()) {
			        	$response[] = array("id" => $id, "id_vaga" => $id_vaga, "nome" => $nome, "telefone" => $telefone, "email" => $email, "endereco" => $endereco, "objetivo" => $objetivo, "formacao_academica" => $formacao_academica, "experiencia_profissional" => $experiencia_profissional, "informacao_adicional" => $informacao_adicional, "error" => false, "message" => "ok");
			    	}
		    	}else{
		    		$response[] =array("error" => true, "message" => "Erro na execução");
		    	}
			return $response;
		}
				
		public function createCandidato($nome, $id_vaga, $telefone, $email, $endereco, $objetivo, $formacao_academica, $experiencia_profissional, $informacao_adicional){
			$stmt = $this->con->prepare("INSERT INTO candidato (id_vaga, nome, telefone, email, endereco, objetivo, formacao_academica, experiencia_profissional, informacao_adicional) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);");
			$stmt->bind_param("issssssss",$id_vaga,$nome,$telefone,$email,$endereco,$objetivo,$formacao_academica,$experiencia_profissional,$informacao_adicional);

			if($stmt->execute()){
				return 1; 
			}else{
				return 2; 
			}
			
		}

	}

	