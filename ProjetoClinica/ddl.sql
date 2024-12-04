SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";

-- Criação do Banco de Dados
CREATE DATABASE IF NOT EXISTS projeto_clinica_db;

-- Seleção do Banco de Dados
USE projeto_clinica_db;

-- Criação da Tabela 'paciente'
CREATE TABLE IF NOT EXISTS `paciente` (
  `id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `nome` VARCHAR(255) NOT NULL,
  `cpf` VARCHAR(255) NOT NULL UNIQUE,
  `telefone` VARCHAR(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


