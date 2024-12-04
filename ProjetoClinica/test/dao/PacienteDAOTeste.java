package dao;

import br.com.dao.PacienteDAO;
import br.com.dao.BancoDados;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import br.com.entities.Paciente;

public class PacienteDAOTeste {

    // Teste de cadastro de paciente
    public static void cadastrarPacienteTeste() throws SQLException, IOException {
        Paciente paciente = new Paciente();
        paciente.setNome("João da Silva");
        paciente.setCpf("12345678901");
        paciente.setTelefone("998877665");

        Connection conn = BancoDados.conectar();
        PacienteDAO pacienteDAO = new PacienteDAO(conn);
        pacienteDAO.cadastrar(paciente);
        System.out.println("Cadastro de paciente efetuado com sucesso.");
    }

    // Teste de busca de todos os pacientes
    public static void buscarTodosPacientesTeste() throws SQLException, IOException {
        Connection conn = BancoDados.conectar();
        PacienteDAO pacienteDAO = new PacienteDAO(conn);
        List<Paciente> listaPacientes = pacienteDAO.buscarTodos();
        for (Paciente paciente : listaPacientes) {
            System.out.println(paciente.getId() + " - " + paciente.getNome() + " - " + paciente.getCpf() + " - " + paciente.getTelefone());
        }
    }

    // Teste de busca por CPF
    public static void buscarPorCpfTeste() throws SQLException, IOException {
        String cpf = "12345678901";  // CPF para busca
        Connection conn = BancoDados.conectar();
        PacienteDAO pacienteDAO = new PacienteDAO(conn);
        Paciente paciente = pacienteDAO.buscarPorCpf(cpf);
        if (paciente != null) {
            System.out.println(paciente.getId() + " - " + paciente.getNome() + " - " + paciente.getCpf() + " - " + paciente.getTelefone());
        } else {
            System.out.println("Paciente não encontrado.");
        }
    }

    // Teste de atualização de paciente
    public static void atualizarPacienteTeste() throws SQLException, IOException {
        Paciente paciente = new Paciente();
        paciente.setNome("João da Silva Atualizado");
        paciente.setCpf("12345678901");  // CPF não pode ser alterado, caso contrário, deve ser ajustado
        paciente.setTelefone("998877667");

        Connection conn = BancoDados.conectar();
        PacienteDAO pacienteDAO = new PacienteDAO(conn);
        pacienteDAO.atualizar(paciente);
        System.out.println("Dados do paciente atualizados com sucesso.");
    }

    // Teste de exclusão de paciente
    public static void excluirPacienteTeste() throws SQLException, IOException {
        String cpf = "12345678901";  // CPF do paciente a ser excluído
        Connection conn = BancoDados.conectar();
        PacienteDAO pacienteDAO = new PacienteDAO(conn);
        int linhasManipuladas = pacienteDAO.excluir(cpf);
        if (linhasManipuladas > 0) {
            System.out.println("Paciente excluído com sucesso.");
        } else {
            System.out.println("Nenhum registro foi encontrado.");
        }
    }

    // Método principal para rodar os testes
    public static void main(String[] args) throws ParseException {
        try {
            // Testes de CRUD para a classe PacienteDAO
//            cadastrarPacienteTeste();
            buscarTodosPacientesTeste();
//            buscarPorCpfTeste();
//            atualizarPacienteTeste();
//            excluirPacienteTeste();
        } catch (SQLException | IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
