package org.winther.backend.wintherburguer.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

@Entity
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_pedido", nullable = false)
	private Long id;
	
	@NotNull
    @OneToMany
    @JoinColumn(name = "produto_pedido")
    private List<VendasItem> itens = new ArrayList<>();
	
	@ManyToOne
	@JoinColumn(name = "cliente_pedido")
	private Cliente cliente;
	
	@Column(name = "pedido_nota_fiscal")
	private Integer notaFiscal;
	
	@Column(name = "pedido_tipo_pedido")
	private String tipoPedido;
	
	@Column(name = "pedido_valor_total")
	private Double Total;

	public Pedido() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<VendasItem> getItens() {
		return itens;
	}

	public void setItens(List<VendasItem> itens) {
		this.itens = itens;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Integer getNotaFiscal() {
		return notaFiscal;
	}

	public void setNotaFiscal(Integer notaFiscal) {
		this.notaFiscal = notaFiscal;
	}

	public String getTipoPedido() {
		return tipoPedido;
	}

	public void setTipoPedido(String tipoPedido) {
		this.tipoPedido = tipoPedido;
	}

	public Double getTotal() {
		return Total;
	}

	public void setTotal(Double total) {
		Total = total;
	}
	
}
