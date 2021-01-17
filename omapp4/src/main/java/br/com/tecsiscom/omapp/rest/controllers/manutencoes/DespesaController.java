package br.com.tecsiscom.omapp.rest.controllers.manutencoes;

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
import br.com.tecsiscom.omapp.model.entity.manutencoes.Despesa;
import br.com.tecsiscom.omapp.model.entity.produtos.Produto;
import br.com.tecsiscom.omapp.model.repository.manutencoes.DespesaRepository;
import br.com.tecsiscom.omapp.model.service.manutencoes.DespesaService;

@RestController
@RequestMapping("/despesas")
public class DespesaController {
	
	@Autowired
	DespesaService service;
	
	@Autowired
	DespesaRepository repository;
	
	@CheckSecurity.Manutencoes.PodeConsultar
	@GetMapping()
	public List<Despesa> listar() {
		
		List<Despesa> despesas = repository.findAll();
		
		return despesas;
	}
	
	@CheckSecurity.Manutencoes.PodeConsultar
	@GetMapping("/{despesaId}")
	public Optional<Despesa> buscar(@PathVariable Long despesaId) {
		Optional<Despesa> despesa =repository.findById(despesaId);
		return despesa;
	}
	
	
	@CheckSecurity.Manutencoes.PodeEditar
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Despesa salvar(@RequestBody @Valid Despesa despesa) {
		try {
			return service.salvar(despesa);
		} catch (ProdutoNaoEncontradoException e) {
			throw new NegocioException(e.getMessage());
		}
		
	}
	
	@CheckSecurity.Manutencoes.PodeEditar
	@DeleteMapping("/{despesaId}")
	public ResponseEntity<Produto> remover(@PathVariable("despesaId") Long id) {

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


