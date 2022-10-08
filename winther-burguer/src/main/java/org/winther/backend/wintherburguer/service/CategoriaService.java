package org.winther.backend.wintherburguer.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.winther.backend.wintherburguer.dto.CategoriaDTO;
import org.winther.backend.wintherburguer.exception.CustomNoContentException;
import org.winther.backend.wintherburguer.exception.EmailException;
import org.winther.backend.wintherburguer.model.Categoria;
import org.winther.backend.wintherburguer.repository.CategoriaRepository;

@Service
public class CategoriaService {
	
	@Autowired
	CategoriaRepository categoriaRepository;
	
//	MODEL PARA DTO
	public CategoriaDTO toDTO(Categoria model, CategoriaDTO dto) {
		dto.setId(model.getId());
		dto.setNome(model.getNome());
		return dto;
	}

//	MODEL PARA DTO
	public Categoria toModel(Categoria model, CategoriaDTO dto) {
		model.setNome(dto.getNome());
		return model;
	}
	
//	ADICIONAR CLIENTE
	public CategoriaDTO salvar(CategoriaDTO dto) throws EmailException {
		Categoria categoria = new Categoria();
		categoria = toModel(categoria, dto);
		categoriaRepository.save(categoria);
		return dto;
	}
	
//	LISTAR CLIENTES CADASTRADOS
	public List<CategoriaDTO> listar(){
		List<Categoria> categorias = categoriaRepository.findAll();
		List<CategoriaDTO> dtos = new ArrayList<>();
		
		for (Categoria categoria : categorias) {
			CategoriaDTO dto =  new CategoriaDTO();
			dto = toDTO(categoria, dto);
			dtos.add(dto);
		}
		return dtos;
	}
	
//	BUSCAR POR ID
	public CategoriaDTO buscarPorId(Long id) throws CustomNoContentException {
		Optional<Categoria> categoria = categoriaRepository.findById(id);
		Categoria model = new Categoria();
		CategoriaDTO dto = new CategoriaDTO();
		
		if(categoria.isPresent()) {
			model = categoria.get();
			dto = toDTO(model, dto);
			return dto;
		}
		throw new CustomNoContentException("Categoria não encontrado");
	}
	
//	ATUALIZAR CLIENTE
	public String atualizar(Long id, CategoriaDTO dto) throws CustomNoContentException {
		Optional<Categoria> model = categoriaRepository.findById(id);
		Categoria categoria = new Categoria();
		
		if(model.isPresent()) {
			categoria = model.get();
			categoria.setNome(dto.getNome());
			categoriaRepository.save(categoria);
			return "Categoria atualizada";
		}
		throw new CustomNoContentException("Erro ao atualizar a categoria");
	}
	
//	DELETAR CLIENTE
	public String deletar(Long id) throws CustomNoContentException {
		Optional<Categoria> categoria = categoriaRepository.findById(id);
		
		if(categoria.isPresent()) {
			categoriaRepository.deleteById(id);
			return "Categoria deletada!";
		}
		throw new CustomNoContentException("Categoria não encontrado");
	}

}
