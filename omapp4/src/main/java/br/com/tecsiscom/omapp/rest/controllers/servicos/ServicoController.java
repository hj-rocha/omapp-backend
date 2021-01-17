package br.com.tecsiscom.omapp.rest.controllers.servicos;

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
import br.com.tecsiscom.omapp.model.entity.servicos.Servico;
import br.com.tecsiscom.omapp.model.entity.produtos.Produto;
import br.com.tecsiscom.omapp.model.repository.servicos.ServicoRepository;
import br.com.tecsiscom.omapp.model.service.servicos.ServicoService;


@RestController
@RequestMapping("/servicos")
public class ServicoController {

	@Autowired
	ServicoService service;
	
	@Autowired
	ServicoRepository repository;
	
	@CheckSecurity.Produtos.PodeConsultar
	@GetMapping()
	public List<Servico> listar() {
		List<Servico> servicos = repository.findAll();
		
		return 
				servicos;
	}
	
	@CheckSecurity.Produtos.PodeConsultar
	@GetMapping("/{servicoId}")
	public Optional<Servico> buscar(@PathVariable Long servicoId) {
		Optional<Servico> servico =repository.findById(servicoId);
		return servico;
	}
	
	@CheckSecurity.Produtos.PodeConsultar
	@GetMapping("/nome/{servicoNome}")
	public List<Servico> buscarPorNome(@PathVariable String servicoNome) {
		List<Servico> servicos =repository.findByNomeStartingWith(servicoNome);
		return servicos;
	}
	
	
	@CheckSecurity.Produtos.PodeEditar
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Servico salvar(@RequestBody @Valid Servico servico) {
		try {
			return service.salvar(servico);
		} catch (ProdutoNaoEncontradoException e) {
			throw new NegocioException(e.getMessage());
		}
		
	}
	
	@CheckSecurity.Produtos.PodeEditar
	@DeleteMapping("/{servicoId}")
	public ResponseEntity<Produto> remover(@PathVariable("servicoId") Long id) {

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

