package org.winther.backend.wintherburguer.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.winther.backend.wintherburguer.dto.ItemDTO;
import org.winther.backend.wintherburguer.dto.PedidoDTO;
import org.winther.backend.wintherburguer.exception.CustomNoContentException;
import org.winther.backend.wintherburguer.model.Cliente;
import org.winther.backend.wintherburguer.model.Pedido;
import org.winther.backend.wintherburguer.model.VendasItem;
import org.winther.backend.wintherburguer.repository.ClienteRepository;
import org.winther.backend.wintherburguer.repository.PedidoRepository;
import org.winther.backend.wintherburguer.repository.ProdutoRepository;
import org.winther.backend.wintherburguer.repository.VendasItemRepository;

@Service
public class PedidoService {

	@Autowired
	PedidoRepository pedidoRepository;
	@Autowired
	ClienteRepository clienteRepository;
	@Autowired
	ProdutoRepository produtoRepository;
	@Autowired
	VendasItemRepository vendasItemRepository;
	@Autowired
	VendasItemService vendasItemService;
	@Autowired
	ProdutoService produtoService;
	
//	MODEL PARA DTO
	public PedidoDTO toDTO(Pedido model, PedidoDTO dto) {
		dto.setIdCliente(model.getCliente().getId());
		
		List<ItemDTO> itens = new ArrayList<>();
		Double valorTotal = 0.0;
		
		for(VendasItem item : model.getItens()) {
			ItemDTO itemDTO = new ItemDTO();
			itemDTO = vendasItemService.toDTO(itemDTO, item);
			itens.add(itemDTO);
			valorTotal += item.getPreco();
		}
		dto.setItens(itens);
		model.setTotal(valorTotal);
		
		return dto;
	}
	
//	DTO PARA MODEL
	public Pedido toModel(Pedido model, PedidoDTO dto) {
		Random rng = new Random();
		model.setNotaFiscal(rng.nextInt(99999));
		model.setTipoPedido("Compra");
		
		if(dto.getIdCliente() != null) {
			Optional<Cliente> cliente = clienteRepository.findById(dto.getIdCliente());
			if(cliente.isPresent()) {
				model.setCliente(cliente.get());
			}
		}
		
		List<VendasItem> itens = new ArrayList<>();
		Double valorTotal = 0.0;
		
		for(ItemDTO itemDTO : dto.getItens()){
			VendasItem item = new VendasItem();
			item.setPedido(model);
			vendasItemService.toModel(item, itemDTO);
			itens.add(item);
			valorTotal += item.getPreco();
		}
		
		model.setItens(itens);
		model.setTotal(valorTotal);
		return model;
	}
	
//	SALVAR LISTA DE ITENS VENDIDOS POR PEDIDO
	public void salvarListaVendidos(List<VendasItem> itens) {
		for(VendasItem item : itens) {
			vendasItemRepository.save(item);
		}
	}
	
//	SALVAR PEDIDO
	public Long salvar(PedidoDTO dto) {
		Pedido model = new Pedido();
		toModel(model, dto);
		pedidoRepository.save(model);
		salvarListaVendidos(model.getItens());
		produtoService.atualizarEstoqueVenda(model);
		return model.getId();
	}
	
//	LISTAR TODOS PEDIDOS
	public List<PedidoDTO> listar(){
		List<Pedido> pedidos = pedidoRepository.findAll();
		List<PedidoDTO> lista = new ArrayList<>();
		
		for(Pedido pedido : pedidos){
			PedidoDTO dto = new PedidoDTO();
			toDTO(pedido, dto);
			lista.add(dto);
		}
		return lista;
	}
	
//	BUSCAR PEDIDO POR ID
	public PedidoDTO buscarPorId(Long id) throws CustomNoContentException {
		Optional<Pedido> pedido = pedidoRepository.findById(id);
		Pedido model = new Pedido();
		PedidoDTO dto = new PedidoDTO();
		
		if(pedido.isPresent()) {
			model = pedido.get();
			toDTO(model, dto);
			return dto;
		}
		throw new CustomNoContentException("Pedido não encontrado");
	}
	
//	ATUALIZAR PEDIDO
	public String atualizar(Long id, PedidoDTO dto) throws CustomNoContentException {
		Optional<Pedido> pedido = pedidoRepository.findById(id);
		
		if(pedido.isPresent()) {
			Pedido model = pedido.get();
			if(!dto.getItens().isEmpty()) {
				List<VendasItem> itens = new ArrayList<>();
				for(ItemDTO itemDTO : dto.getItens()) {
					VendasItem item = new VendasItem(); 
					vendasItemService.toModel(item, itemDTO);
					itens.add(item);
				}
				model.setItens(itens);
				pedidoRepository.save(model);
			}
			return "Pedido atualizado";
		}
		throw new CustomNoContentException("pedido não encontrado");
	}
}
