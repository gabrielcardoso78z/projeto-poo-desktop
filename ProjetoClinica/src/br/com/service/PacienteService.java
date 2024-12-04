package br.com.service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.dao.PacienteDAO;
import br.com.dao.BancoDados;
import br.com.entities.Paciente;

/**
 *
 * @author gabrielcardoso
 */
public class PacienteService {
	
    public PacienteService() {}

    public List<Paciente> buscarTodos() throws SQLException, IOException {
        Connection conn = BancoDados.conectar();
        List <Paciente> pacientes = new PacienteDAO(conn).buscarTodos();
        return pacientes;
    }

    public void cadastrar(Paciente paciente) throws SQLException, IOException {
        Connection conn = BancoDados.conectar();
        new PacienteDAO(conn).cadastrar(paciente);
    }

    public void atualizarPaciente(Paciente paciente) throws SQLException, IOException {
        Connection conn = BancoDados.conectar();
        new PacienteDAO(conn).atualizar(paciente);   
    }

    public void excluirPaciente(String cpfPaciente) throws SQLException, IOException {
        Connection conn = BancoDados.conectar();
        new PacienteDAO(conn).excluir(cpfPaciente);
    }
}
