package br.com.tecsiscom.omapp.rest.controllers.pecas;

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
import br.com.tecsiscom.omapp.exception.NegocioException;
import br.com.tecsiscom.omapp.exception.ProdutoNaoEncontradoException;
import br.com.tecsiscom.omapp.model.entity.pecas.Peca;
import br.com.tecsiscom.omapp.model.entity.produtos.Produto;
import br.com.tecsiscom.omapp.model.repository.pecas.PecaRepository;
import br.com.tecsiscom.omapp.model.service.pecas.PecaService;

@RestController
@RequestMapping("/pecas")
public class PecaController {
	
	@Autowired
	PecaService service;
	
	@Autowired
	PecaRepository repository;
	
	@CheckSecurity.Produtos.PodeConsultar
	@GetMapping()
	public List<Peca> listar() {
		List<Peca> pecas = repository.findAll();
		
		return 
				pecas;
	}
	
	@CheckSecurity.Produtos.PodeConsultar
	@GetMapping("/{pecaId}")
	public Optional<Peca> buscar(@PathVariable Long pecaId) {
		Optional<Peca> peca =repository.findById(pecaId);
		return peca;
	}
	
	
	@CheckSecurity.Produtos.PodeEditar
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Peca salvar(@RequestBody @Valid Peca peca) {
		try {
			return service.salvar(peca);
		} catch (ProdutoNaoEncontradoException e) {
			throw new NegocioException(e.getMessage());
		}
		
	}
	
	@CheckSecurity.Produtos.PodeEditar
	@DeleteMapping("/{pecaId}")
	public ResponseEntity<Produto> remover(@PathVariable("pecaId") Long id) {

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
