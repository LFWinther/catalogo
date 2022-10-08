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
import org.winther.backend.wintherburguer.dto.CategoriaDTO;
import org.winther.backend.wintherburguer.exception.CustomNoContentException;
import org.winther.backend.wintherburguer.service.CategoriaService;

import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;



@RestController
@RequestMapping("/categoria")
public class CategoriaController {

	@Autowired
	private CategoriaService categoriaService;

	@PostMapping
	@ApiOperation(value = "Cadastra um novo cliente")
	public ResponseEntity<CategoriaDTO> salvar(@RequestBody CategoriaDTO categoriaDTO) throws CustomNoContentException {
		categoriaService.salvar(categoriaDTO);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@GetMapping
	@ApiOperation(value = "Lista todos os cliente")
	public ResponseEntity<List<CategoriaDTO>> listaTodos() {
		return new ResponseEntity<>(categoriaService.listar(), 
				HttpStatus.ACCEPTED);
	}

	@GetMapping("/{id}")
	@ApiOperation(value = "Busca cliente por ID")
	public ResponseEntity<CategoriaDTO> buscarPorId(@PathVariable Long id) throws CustomNoContentException {
		return ResponseEntity.ok(categoriaService.buscarPorId(id));
	}

	@PutMapping("/{id}")
	@ApiOperation(value = "Atualiza cliente")
	public ResponseEntity<String> atualizar(@PathVariable Long id, @RequestBody CategoriaDTO categoriaDTO)
			throws CustomNoContentException {
		return new ResponseEntity<>(categoriaService.atualizar(id, categoriaDTO), 
				HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/{id}")
	@ApiOperation(value = "Deleta cliente")
	public ResponseEntity<String> deletar(@PathVariable Long id) throws CustomNoContentException{
		return new ResponseEntity<>(categoriaService.deletar(id),
				HttpStatus.ACCEPTED);
	}
}
