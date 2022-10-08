package org.winther.backend.wintherburguer.dto;

public class ItemDTO {
	
	private Long idProduto;
	private Integer quantidade;
	
	public ItemDTO() {
		
	}
	
	public Long getIdProduto() {
		return idProduto;
	}
	public void setIdProduto(Long idProduto) {
		this.idProduto = idProduto;
	}
	public Integer getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
}
