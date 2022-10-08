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
import org.winther.backend.wintherburguer.dto.ProdutoDTO;
import org.winther.backend.wintherburguer.exception.CustomNoContentException;
import org.winther.backend.wintherburguer.service.ProdutoService;

import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

	@Autowired
	private ProdutoService produtoService;

	@PostMapping
	@ApiOperation(value = "Cadastra um novo produto")
	public ResponseEntity<ProdutoDTO> salvar(@RequestBody ProdutoDTO produtoDTO) throws CustomNoContentException {
		produtoService.salvar(produtoDTO);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@GetMapping
	@ApiOperation(value = "Lista todos os produtos")
	public ResponseEntity<List<ProdutoDTO>> listaTodos() {
		return new ResponseEntity<>(produtoService.listar(), 
				HttpStatus.ACCEPTED);
	}

	@GetMapping("/{id}")
	@ApiOperation(value = "Busca produto por ID")
	public ResponseEntity<ProdutoDTO> buscarPorId(@PathVariable Long id) throws CustomNoContentException {
		return ResponseEntity.ok(produtoService.buscarPorId(id));
	}

	@PutMapping("/{id}")
	@ApiOperation(value = "Atualiza produto")
	public ResponseEntity<String> atualizar(@PathVariable Long id, @RequestBody ProdutoDTO produtoDTO)
			throws CustomNoContentException {
		return new ResponseEntity<>(produtoService.atualizar(id, produtoDTO), 
				HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/{id}")
	@ApiOperation(value = "Deleta produto")
	public ResponseEntity<String> deletar(@PathVariable Long id) throws CustomNoContentException{
		return new ResponseEntity<>(produtoService.deletar(id),
				HttpStatus.ACCEPTED);
	}
}
