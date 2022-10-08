package org.winther.backend.wintherburguer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.winther.backend.wintherburguer.dto.ClienteDTO;
import org.winther.backend.wintherburguer.exception.CustomNoContentException;
import org.winther.backend.wintherburguer.service.ClienteService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

	@Autowired
	private ClienteService clienteService;

	@PostMapping
	@ApiOperation(value = "Cadastra um novo cliente")
	public ResponseEntity<ClienteDTO> salvar(@RequestBody ClienteDTO clienteDTO) throws CustomNoContentException {
		clienteService.salvar(clienteDTO);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@GetMapping
	@ApiOperation(value = "Lista todos os clientes")
	public ResponseEntity<List<ClienteDTO>> listaTodos() {
		return new ResponseEntity<>(clienteService.listar(), 
				HttpStatus.ACCEPTED);
	}

	@GetMapping("/{id}")
	@ApiOperation(value = "Busca cliente por ID")
	public ResponseEntity<ClienteDTO> buscarPorId(@PathVariable Long id) throws CustomNoContentException {
		return ResponseEntity.ok(clienteService.buscarPorId(id));
	}
	
	@PutMapping("/{id}")
	@ApiOperation(value = "Atualiza cliente")
	public ResponseEntity<String> atualizar(@PathVariable Long id, @RequestBody ClienteDTO clienteDTO)
			throws CustomNoContentException {
		return new ResponseEntity<>(clienteService.atualizar(id, clienteDTO), 
				HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/{id}")
	@ApiOperation(value = "Deleta cliente")
	public ResponseEntity<String> deletar(@PathVariable Long id) throws CustomNoContentException{
		return new ResponseEntity<>(clienteService.deletar(id),
				HttpStatus.ACCEPTED);
	}

}
