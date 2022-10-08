package org.winther.backend.wintherburguer.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_produto", nullable = false)
	private Long id;
	
	@Column(name = "nome_produto", nullable = false, length = 20)
	private String nome;
	
	@Column(name = "descricao_produto", nullable = false, length = 50)
	private String descricao;
	
	@Column(name = "preco_produto", nullable = false, length = 50)
	private Double valor;
	
	@Column(name = "imagem_produto", nullable = false)
	private String imagem;
	
	@Column(name = "estoque_produto", nullable = false)
	private Integer estoque; 
	
	@NotNull
    @ManyToOne
    @JoinColumn(name = "categoria_produto")
    private Categoria categoria;
	
	public Produto() {
		
	}
	
	public void tirarEstoque(Integer qtd) {
		this.estoque -= qtd;
	}

	public void colocarEstoque(Integer qtd) {
		this.estoque += qtd;
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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public String getImagem() {
		return imagem;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Integer getEstoque() {
		return estoque;
	}

	public void setEstoque(Integer estoque) {
		this.estoque = estoque;
	}
	
}
