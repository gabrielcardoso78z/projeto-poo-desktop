package br.com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.entities.Paciente;

/**
 *
 * @author gabrielcardoso
 */
public class PacienteDAO {

    private Connection conn;

    public PacienteDAO(Connection conn) {
        this.conn = conn;
    }

    public List<Paciente> buscarTodos() throws SQLException {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement("select * from paciente order by nome");
            rs = st.executeQuery();
            List<Paciente> listaPacientes = new ArrayList<>();
            while (rs.next()) {
                Paciente paciente = new Paciente();
                paciente.setId(rs.getInt("id"));
                paciente.setNome(rs.getString("nome"));
                paciente.setCpf(rs.getString("cpf"));
                paciente.setTelefone(rs.getString("telefone"));
                listaPacientes.add(paciente);
            }
            return listaPacientes;
        } finally {
            BancoDados.finalizarStatement(st);
            BancoDados.finalizarResultSet(rs);
            BancoDados.desconectar();
        }
    }

    public Paciente buscarPorCpf(String cpf) throws SQLException {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement("select * from paciente where cpf = ?");
            st.setString(1, cpf);
            rs = st.executeQuery();
            if (rs.next()) {
                Paciente paciente = new Paciente();
                paciente.setId(rs.getInt("id"));
                paciente.setNome(rs.getString("nome"));
                paciente.setCpf(rs.getString("cpf"));
                paciente.setTelefone(rs.getString("telefone"));
                return paciente;
            }
            return null;
        } finally {
            BancoDados.finalizarStatement(st);
            BancoDados.finalizarResultSet(rs);
            BancoDados.desconectar();
        }
    }
    
    public void cadastrar(Paciente paciente) throws SQLException {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                            "insert into paciente (id, nome, cpf, telefone) values (?, ?, ?, ?)");
            st.setInt(1, paciente.getId());
            st.setString(2, paciente.getNome());
            st.setString(3, paciente.getCpf());
            st.setString(4, paciente.getTelefone());
            st.executeUpdate();
        } finally {
            BancoDados.finalizarStatement(st);
            BancoDados.desconectar();
        }
    }

    public void atualizar(Paciente paciente) throws SQLException {
        PreparedStatement st = null;
        System.out.println(paciente.toString());
        try {
            st = conn.prepareStatement("update paciente set nome = ?, telefone = ? where cpf = ?");
            st.setString(1, paciente.getNome());
            st.setString(2, paciente.getTelefone());
            st.setString(3, paciente.getCpf());
            st.executeUpdate();
        } finally {
            BancoDados.finalizarStatement(st);
            BancoDados.desconectar();
        }
    }

    public int excluir(String cpf) throws SQLException {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement("delete from paciente where cpf = ?");
            st.setString(1, cpf);
            int linhasManipuladas = st.executeUpdate();
            return linhasManipuladas;
        } finally {
            BancoDados.finalizarStatement(st);
            BancoDados.desconectar();
        }
    }
}
