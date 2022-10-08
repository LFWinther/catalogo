package org.winther.backend.wintherburguer.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.winther.backend.wintherburguer.dto.ClienteDTO;
import org.winther.backend.wintherburguer.dto.EnderecoDTO;
import org.winther.backend.wintherburguer.exception.CustomNoContentException;
import org.winther.backend.wintherburguer.exception.EmailException;
import org.winther.backend.wintherburguer.model.Cliente;
import org.winther.backend.wintherburguer.model.Endereco;
import org.winther.backend.wintherburguer.repository.ClienteRepository;
import org.winther.backend.wintherburguer.repository.EnderecoRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
//	MODEL PARA DTO
	public ClienteDTO toDTO(Cliente model, ClienteDTO dto) {
		dto.setId(model.getId());
		dto.setNome(model.getNome());
		dto.setEmail(model.getEmail());
		dto.setDataNascimento(model.getDataNascimento());
		dto.setSenha(model.getSenha());
		dto.setTelefone(model.getTelefone());
		dto.setEndereco(toEnderecoDTO(model.getEndereco()));
		return dto;
	}

//	MODEL PARA DTO
	public Cliente toModel(Cliente model, ClienteDTO dto) {
		model.setDataNascimento(dto.getDataNascimento());
		model.setEmail(dto.getEmail());
		model.setNome(dto.getNome());
		model.setTelefone(dto.getTelefone());
		model.setSenha(dto.getSenha());
		model.setEndereco(dto.getEndereco().toEndereco());
		return model;
	}
	
//	ENDERECO PARA DTO
	public EnderecoDTO toEnderecoDTO(Endereco endereco) {
		EnderecoDTO dto = new EnderecoDTO();
		dto.setCep(endereco.getCep());
		dto.setNumero(endereco.getNumero());
		dto.setComplemento(endereco.getComplemento());
		return dto;
	}
	
//	ADICIONAR CLIENTE
	public ClienteDTO salvar(ClienteDTO dto) throws EmailException {
		if(clienteRepository.findByEmail(dto.getEmail()) != null){
            throw new EmailException("Email já cadastrado!");
        }
		Cliente cliente = new Cliente();
		cliente = toModel(cliente, dto);
		enderecoRepository.save(cliente.getEndereco());
		clienteRepository.save(cliente);
		return dto;
	}
	
//	LISTAR CLIENTES CADASTRADOS
	public List<ClienteDTO> listar(){
		List<Cliente> clientes = clienteRepository.findAll();
		List<ClienteDTO> dtos = new ArrayList<>();
		
		for (Cliente cliente : clientes) {
			ClienteDTO dto =  new ClienteDTO();
			dto = toDTO(cliente, dto);
			dtos.add(dto);
		}
		return dtos;
	}
	
//	BUSCAR POR ID
	public ClienteDTO buscarPorId(Long id) throws CustomNoContentException {
		Optional<Cliente> cliente = clienteRepository.findById(id);
		Cliente model = new Cliente();
		ClienteDTO dto = new ClienteDTO();
		
		if(cliente.isPresent()) {
			model = cliente.get();
			toDTO(model, dto);
			return dto;
		}
		throw new CustomNoContentException("Cliente não encontrado");
	}
	
//	ATUALIZAR CLIENTE
	public String atualizar(Long id, ClienteDTO dto) throws CustomNoContentException {
		Optional<Cliente> model = clienteRepository.findById(id);
		Cliente cliente = new Cliente();
		
		if(model.isPresent()) {
			cliente = model.get();
			
			if(dto.getDataNascimento() != null) {
				cliente.setDataNascimento(dto.getDataNascimento());
			} if(dto.getEmail() != null) {
				cliente.setEmail(dto.getEmail());
			} if(dto.getNome() != null) {
				cliente.setNome(dto.getNome());
			} if(dto.getSenha() != null) {
				cliente.setSenha(dto.getSenha());
			} if(dto.getTelefone() != null) {
				cliente.setTelefone(dto.getTelefone());
			} if(dto.getEndereco() != null) {
				cliente.setEndereco(dto.getEndereco().toEndereco());
			}
			clienteRepository.save(cliente);
			return "Cliente atualizado";
		}
		throw new CustomNoContentException("Erro ao atualizar o cliente");
	}
	
//	DELETAR CLIENTE
	public String deletar(Long id) throws CustomNoContentException {
		Optional<Cliente> cliente = clienteRepository.findById(id);
		
		if(cliente.isPresent()) {
			clienteRepository.deleteById(id);
			return "Cliente deletado!";
		}
		throw new CustomNoContentException("Cliente não encontrado");
	}
}
