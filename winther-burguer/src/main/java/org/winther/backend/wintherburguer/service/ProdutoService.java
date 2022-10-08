package org.winther.backend.wintherburguer.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.winther.backend.wintherburguer.dto.ProdutoDTO;
import org.winther.backend.wintherburguer.exception.CustomNoContentException;
import org.winther.backend.wintherburguer.exception.EmailException;
import org.winther.backend.wintherburguer.model.Categoria;
import org.winther.backend.wintherburguer.model.Pedido;
import org.winther.backend.wintherburguer.model.Produto;
import org.winther.backend.wintherburguer.model.VendasItem;
import org.winther.backend.wintherburguer.repository.CategoriaRepository;
import org.winther.backend.wintherburguer.repository.ProdutoRepository;

@Service
public class ProdutoService {
	
	@Autowired
	ProdutoRepository produtoRepository;
	
	@Autowired
	CategoriaRepository categoriaRepository;
	
//	MODEL PARA DTO
	public ProdutoDTO toDTO(Produto model, ProdutoDTO dto) {
		dto.setId(model.getId());
		dto.setNome(model.getNome());
		dto.setDescricao(model.getDescricao());
		dto.setValor(model.getValor());
		dto.setImagem(model.getImagem());
		dto.setEstoque(model.getEstoque());
		dto.setCategoria(model.getCategoria().getId());
		return dto;
	}

//	MODEL PARA DTO
	public Produto toModel(Produto model, ProdutoDTO dto) {
		model.setNome(dto.getNome());
		model.setDescricao(dto.getDescricao());
		model.setValor(dto.getValor());
		model.setImagem(dto.getImagem());
		model.setEstoque(dto.getEstoque());
		if(dto.getCategoria() != null) {
			Optional<Categoria> categoria = categoriaRepository.findById(dto.getCategoria());
			if(categoria.isPresent()) {
				model.setCategoria(categoria.get());
			}
		}
		return model;
	}
	
//	ADICIONAR CLIENTE
	public ProdutoDTO salvar(ProdutoDTO dto) throws EmailException {
		Produto produto = new Produto();
		produto = toModel(produto, dto);
		produtoRepository.save(produto);
		return dto;
	}
	
//	LISTAR CLIENTES CADASTRADOS
	public List<ProdutoDTO> listar(){
		List<Produto> produtos = produtoRepository.findAll();
		List<ProdutoDTO> dtos = new ArrayList<>();
		
		for (Produto produto : produtos) {
			ProdutoDTO dto =  new ProdutoDTO();
			dto = toDTO(produto, dto);
			dtos.add(dto);
		}
		return dtos;
	}
	
//	BUSCAR POR ID
	public ProdutoDTO buscarPorId(Long id) throws CustomNoContentException {
		Optional<Produto> produto = produtoRepository.findById(id);
		Produto model = new Produto();
		ProdutoDTO dto = new ProdutoDTO();
		
		if(produto.isPresent()) {
			model = produto.get();
			toDTO(model, dto);
			return dto;
		}
		throw new CustomNoContentException("Produto não encontrado");
	}
	
//	ATUALIZAR CLIENTE
	public String atualizar(Long id, ProdutoDTO dto) throws CustomNoContentException {
		Optional<Produto> model = produtoRepository.findById(id);
		Produto produto = new Produto();
		
		if(model.isPresent()) {
			produto = model.get();
			if(dto.getNome() != null) {
				produto.setNome(dto.getNome());
			} if(dto.getDescricao() != null) {
				produto.setDescricao(dto.getDescricao());
			} if(dto.getEstoque() != null) {
				produto.setEstoque(dto.getEstoque());
			} if(dto.getImagem() != null) {
				produto.setImagem(dto.getImagem());
			} if(dto.getValor() != null) {
				produto.setValor(dto.getValor());
			} if(dto.getCategoria() != null) {
				Optional<Categoria> categoria = categoriaRepository.findById(dto.getCategoria());
				if(categoria.isPresent()) {
					produto.setCategoria(categoria.get());
				}
			}
			produtoRepository.save(produto);
			return "Produto atualizada";
		}
		throw new CustomNoContentException("Erro ao atualizar a produto");
	}
	
//	DELETAR CLIENTE
	public String deletar(Long id) throws CustomNoContentException {
		Optional<Produto> produto = produtoRepository.findById(id);
		
		if(produto.isPresent()) {
			produtoRepository.deleteById(id);
			return "Produto deletado!";
		}
		throw new CustomNoContentException("Produto não encontrado");
	}
	
//	ATUALIZA ESTOQUE DO PRODUTO VENDIDO
	public void atualizarEstoqueVenda(Pedido pedido) throws CustomNoContentException {
		for(VendasItem item : pedido.getItens()) {
			Optional<Produto> produto = produtoRepository.findById(item.getProduto().getId());
			if(produto.isPresent()) {
				Produto model = produto.get();
				if(model.getEstoque() < item.getQuantidade()) {
					throw new CustomNoContentException("Estoque insuficiente.");
				}
				model.tirarEstoque(item.getQuantidade());
				produtoRepository.save(model);
			}
		}
	}
	
//	ATUALIZA ESTOQUE DO PRODUTO COMPRADO
	public void atualizaEstoqueCompra(Produto produto, Integer quantidade) {
		produto.colocarEstoque(quantidade);
		produtoRepository.save(produto);
	}

}
