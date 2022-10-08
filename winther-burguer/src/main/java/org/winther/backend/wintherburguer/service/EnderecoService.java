package org.winther.backend.wintherburguer.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.winther.backend.wintherburguer.dto.EnderecoDTO;
import org.winther.backend.wintherburguer.exception.CustomNoContentException;
import org.winther.backend.wintherburguer.model.Endereco;
import org.winther.backend.wintherburguer.repository.EnderecoRepository;

@Service
public class EnderecoService {
	
	@Autowired
	EnderecoRepository enderecoRepository;
	
//	DTO PARA MODEL
	public Endereco toModel(Endereco endereco, EnderecoDTO enderecoDTO) {
		endereco.setCep(enderecoDTO.getCep());
		endereco.setComplemento(enderecoDTO.getComplemento());
		endereco.setNumero(enderecoDTO.getNumero());
		return endereco;
	}

//	MODEL PARA DTO
	public EnderecoDTO toDTO(EnderecoDTO enderecoDTO, Endereco endereco) {
		enderecoDTO.setCep(endereco.getCep());
		enderecoDTO.setComplemento(endereco.getComplemento());
		enderecoDTO.setNumero(endereco.getNumero());
		return enderecoDTO;
	}

//	SALVAR ENDEREÇO
	public String salvar(EnderecoDTO enderecoDTO) {
		Endereco endereco = new Endereco();
		toModel(endereco, enderecoDTO);
		enderecoRepository.save(endereco);
		return "Novo endereco cadastrado.";
	}
	
//	LISTAR ENDEREÇOS
	public List<EnderecoDTO> todosEnderecos() {
		List<Endereco> lisEnderecos = enderecoRepository.findAll();
		List<EnderecoDTO> enderecoDTOs = new ArrayList<>();

		for (Endereco endereco : lisEnderecos) {
			EnderecoDTO enderecoDTO = new EnderecoDTO();
			toDTO(enderecoDTO, endereco);
			enderecoDTOs.add(enderecoDTO);
		}
		return enderecoDTOs;
	}

//	BUSCAR ENREDEÇO POR ID
	public EnderecoDTO buscarPorId(Long id) throws CustomNoContentException {
		Optional<Endereco> endereco = enderecoRepository.findById(id);
		Endereco model= new Endereco();
		EnderecoDTO dto = new EnderecoDTO();

		if (endereco.isPresent()) {
			model = endereco.get();
			toDTO(dto, model);
			return dto;
		}
		throw new CustomNoContentException("Endereco não encontrado.");
	}

//	ATUALUZAR ENDEREÇO
	public String atualizar(Long idEndereco, EnderecoDTO dto) throws CustomNoContentException {
		Optional<Endereco> model = enderecoRepository.findById(idEndereco);
		Endereco endereco = new Endereco();

		if (model.isPresent()) {
			endereco = model.get();
			if (dto.getCep() != null) {
				endereco.setCep(dto.getCep());
			} if (dto.getComplemento() != null) {
				endereco.setComplemento(dto.getComplemento());
			} if (dto.getNumero() != null) {
				endereco.setNumero(dto.getNumero());
			}
			enderecoRepository.save(endereco);
			return "Endereco atualizado.";
		}
		throw new CustomNoContentException("O endereco não foi atualizado");
	}
	
//	DELETAR ENDEREÇO POR ID
	public void delete(Long id) {
		Optional<Endereco> endereco = enderecoRepository.findById(id);
		
		if(endereco.isPresent()) {
			enderecoRepository.deleteById(id);
		}
	}
}
