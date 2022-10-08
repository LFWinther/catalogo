package org.winther.backend.wintherburguer.dto;

import java.util.List;

public class PedidoDTO {
	
	private Long idCliente;
	private List<ItemDTO> itens;

	public PedidoDTO(){}

	public Long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}

	public List<ItemDTO> getItens() {
		return itens;
	}

	public void setItens(List<ItemDTO> itens) {
		this.itens = itens;
	}
}
