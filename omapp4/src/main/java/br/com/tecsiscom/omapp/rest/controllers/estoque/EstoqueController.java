package br.com.tecsiscom.omapp.rest.controllers.estoque;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.tecsiscom.omapp.core.security.CheckSecurity;
import br.com.tecsiscom.omapp.exception.EntidadeEmUsoException;
import br.com.tecsiscom.omapp.exception.EntidadeNaoEncontradaException;
import br.com.tecsiscom.omapp.exception.EstoqueNaoEncontradoException;
import br.com.tecsiscom.omapp.exception.NegocioException;
import br.com.tecsiscom.omapp.model.entity.estoque.Estoque;
import br.com.tecsiscom.omapp.model.repository.estoque.EstoqueRepository;
import br.com.tecsiscom.omapp.model.service.estoque.EstoqueService;

@RestController
@RequestMapping(path = "/estoques")
public class EstoqueController {


	@Autowired
	EstoqueService service;
	
	@Autowired
	EstoqueRepository repository;
	
	@CheckSecurity.Estoques.PodeConsultar
	@GetMapping()
	public List<Estoque> listar() {
		List<Estoque> estoques = repository.findAll();
		return estoques;
	}
	
	@CheckSecurity.Estoques.PodeConsultar
	@GetMapping("/{estoqueId}")
	public Optional<Estoque> buscar(@PathVariable Long estoqueId) {
		Optional<Estoque> estoque = repository.findById(estoqueId);
		return estoque;
	}
		
	@CheckSecurity.Estoques.PodeEditar
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Estoque salvar(@RequestBody @Valid Estoque estoque) {
		try {
			return service.salvar(estoque);
		} catch (EstoqueNaoEncontradoException e) {
			throw new NegocioException(e.getMessage());
		}
	}
	
	@CheckSecurity.Estoques.PodeEditar
	@DeleteMapping("/{estoqueId}")
	public ResponseEntity<Estoque> remover(@PathVariable("estoqueId") Long id) {

		try {
			service.remover(id);
			return ResponseEntity.noContent().build();
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.notFound().build();

		} catch (EntidadeEmUsoException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}

}
