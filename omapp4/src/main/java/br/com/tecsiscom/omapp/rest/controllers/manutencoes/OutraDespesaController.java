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
import br.com.tecsiscom.omapp.model.entity.manutencoes.OutraDespesa;
import br.com.tecsiscom.omapp.model.entity.produtos.Produto;
import br.com.tecsiscom.omapp.model.repository.manutencoes.OutraDespesaRepository;
import br.com.tecsiscom.omapp.model.service.manutencoes.OutraDespesaService;

@RestController
@RequestMapping("/outras_despesas")
public class OutraDespesaController {

	
	@Autowired
	OutraDespesaService service;
	
	@Autowired
	OutraDespesaRepository repository;
	
	@CheckSecurity.Manutencoes.PodeConsultar
	@GetMapping()
	public List<OutraDespesa> listar() {
		
		List<OutraDespesa> outraDespesas = repository.findAll();
		
		return outraDespesas;
	}
	
	@CheckSecurity.Manutencoes.PodeConsultar
	@GetMapping("/{outraDespesaId}")
	public Optional<OutraDespesa> buscar(@PathVariable Long outraDespesaId) {
		Optional<OutraDespesa> outraDespesa =repository.findById(outraDespesaId);
		return outraDespesa;
	}
	
	
	@CheckSecurity.Manutencoes.PodeEditar
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public OutraDespesa salvar(@RequestBody @Valid OutraDespesa outraDespesa) {
		try {
			return service.salvar(outraDespesa);
		} catch (ProdutoNaoEncontradoException e) {
			throw new NegocioException(e.getMessage());
		}
		
	}
	
	@CheckSecurity.Manutencoes.PodeEditar
	@DeleteMapping("/{outraDespesaId}")
	public ResponseEntity<Produto> remover(@PathVariable("outraDespesaId") Long id) {

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

