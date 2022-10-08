package org.winther.backend.wintherburguer.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="vendas_item")
public class VendasItem { 

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_vendas_item")
	private Long idVendasItem;

	@ManyToOne
	@JoinColumn(name="id_produto")
	private Produto produto;

	@ManyToOne
	@JoinColumn(name="pedido_id")
	private Pedido pedido;
	
	@Column(name = "preco_vendas_item")
	private Double preco;
	
	@Column(name = "quantidade_vendas_item")
	private Integer quantidade;

	public VendasItem() {}

	public Long getIdVendasItem() {
		return idVendasItem;
	}

	public void setIdVendasItem(Long idVendasItem) {
		this.idVendasItem = idVendasItem;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	
}
