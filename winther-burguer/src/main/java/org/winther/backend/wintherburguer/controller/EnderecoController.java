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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.winther.backend.wintherburguer.dto.EnderecoDTO;
import org.winther.backend.wintherburguer.exception.CustomNoContentException;
import org.winther.backend.wintherburguer.model.Endereco;
import org.winther.backend.wintherburguer.service.EnderecoService;

import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/endereco")
public class EnderecoController {

    @Autowired
    EnderecoService enderecoService;

    @PostMapping
    @ApiOperation(value = "Cadastra um novo Endereço")
	public ResponseEntity<Void> salvar(@RequestBody EnderecoDTO enderecoDTO) throws CustomNoContentException{
		enderecoService.salvar(enderecoDTO);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
    
    @GetMapping
	@ApiOperation(value = "Lista todos os Endereços")
	public ResponseEntity<List<EnderecoDTO>> listaTodos(){
		return new ResponseEntity<>(enderecoService.todosEnderecos(), 
				HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/{id}")
	@ApiOperation(value = "Busca Endereço por ID")
	public ResponseEntity<EnderecoDTO> buscarPorId(@PathVariable Long id) throws CustomNoContentException {
		return ResponseEntity.ok(enderecoService.buscarPorId(id));
	}
	
	@PutMapping("/{id}")
	@ApiOperation(value = "Atualiza Endereço")
    public ResponseEntity<Void> atualizar(@PathVariable Long id,@RequestBody EnderecoDTO enderecoDTO) throws CustomNoContentException{
        enderecoService.atualizar(id, enderecoDTO);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/{id}")
	@ApiOperation(value = "Deleta Endereço")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		enderecoService.delete(id);
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}
	
}
