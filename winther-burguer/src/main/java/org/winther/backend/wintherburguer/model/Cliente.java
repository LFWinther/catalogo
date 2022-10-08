package org.winther.backend.wintherburguer.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_cliente", nullable = false)
	private Long id;
	
	@Column(name = "nome_cliente", nullable = false, length = 50)
	private String nome;
	
	@Column(name = "telefone_cliente", nullable = false, length = 11)
	private String telefone;
	
	@Email(message = "E-mail inválido")
	@Column(name = "email_cliente", nullable = false, length = 40)
	private String email;
	
	@Column(name = "senha_cliente", nullable = false, length = 20)
	private String senha;
	
	@JsonFormat(pattern = "dd-MM-yyyy")
	@Column(name = "nascimento_cliente", nullable = false)
	private LocalDate dataNascimento;
	
	@NotNull
    @ManyToOne
    @JoinColumn(name = "id_endereco")
    private Endereco endereco;
	
	public Cliente() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	
	
}
