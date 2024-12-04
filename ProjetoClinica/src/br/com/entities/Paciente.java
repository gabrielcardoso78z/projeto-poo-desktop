package br.com.entities;

/**
 *
 * @author gabrielcardoso
 */
public class Paciente {

	private int id;
        private String nome;
	private String cpf;
	private String telefone;
	
	public Paciente() {
	
	}

	public Paciente(int id, String nome, String cpf, String telefone) {
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.telefone = telefone;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getTelefone() {
		return this.telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	@Override
	public String toString() {
		return "Aluno [id=" + id + ", nome=" + nome + ", cpf=" + cpf + ","
			 + ", telefone=" + telefone;
	}
}
