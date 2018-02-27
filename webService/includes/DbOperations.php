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
		
		

	}

	