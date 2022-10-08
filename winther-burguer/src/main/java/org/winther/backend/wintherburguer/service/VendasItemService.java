package org.winther.backend.wintherburguer.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.winther.backend.wintherburguer.dto.ItemDTO;
import org.winther.backend.wintherburguer.model.Produto;
import org.winther.backend.wintherburguer.model.VendasItem;
import org.winther.backend.wintherburguer.repository.ProdutoRepository;

@Service
public class VendasItemService {

	@Autowired
	ProdutoRepository produtoRepository;

	public VendasItem toModel(VendasItem item, ItemDTO itemDTO) {
		item.setQuantidade(itemDTO.getQuantidade());
		Optional<Produto> produto = produtoRepository.findById(itemDTO.getIdProduto());
		if (produto.isPresent()) {
			item.setProduto(produto.get());
			item.setPreco(produto.get().getValor() * item.getQuantidade());
		}
		return item;
	}

	public ItemDTO toDTO(ItemDTO itemDTO, VendasItem item) {
		itemDTO.setIdProduto(item.getProduto().getId());
		itemDTO.setQuantidade(item.getQuantidade());
		return itemDTO;
	}

}
