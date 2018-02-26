-- --------------------------------------------------------
-- Servidor:                     127.0.0.1
-- Versão do servidor:           5.7.12-log - MySQL Community Server (GPL)
-- OS do Servidor:               Win64
-- HeidiSQL Versão:              9.4.0.5125
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Copiando estrutura do banco de dados para testetecnospeed
CREATE DATABASE IF NOT EXISTS `testetecnospeed` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `testetecnospeed`;

-- Exportação de dados foi desmarcado.
-- Copiando estrutura para tabela testetecnospeed.vaga
CREATE TABLE IF NOT EXISTS `vaga` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `titulo` varchar(100) NOT NULL,
  `setor` varchar(250) DEFAULT NULL,
  `cargo` varchar(250) DEFAULT NULL,
  `atividade` text NOT NULL,
  `requisito` text,
  `escolaridade` varchar(100) DEFAULT NULL,
  `status_registro` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `FK_vaga_categoria` (`titulo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Copiando estrutura para tabela testetecnospeed.candidato
CREATE TABLE IF NOT EXISTS `candidato` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_vaga` int(11) NOT NULL,
  `nome` varchar(150) DEFAULT NULL,
  `telefone` varchar(15) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `endereco` varchar(100) DEFAULT NULL,
  `objetivo` text,
  `formacao_academica` text,
  `experiencia_profissional` text,
  `informacao_adicional` text,
  `status_registro` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `FK__vaga` (`id_vaga`),
  CONSTRAINT `FK__vaga` FOREIGN KEY (`id_vaga`) REFERENCES `vaga` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Exportação de dados foi desmarcado.
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
