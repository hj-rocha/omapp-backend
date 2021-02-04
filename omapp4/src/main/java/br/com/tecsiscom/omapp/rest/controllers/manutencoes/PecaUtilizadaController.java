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
import br.com.tecsiscom.omapp.model.entity.manutencoes.PecaUtilizada;
import br.com.tecsiscom.omapp.model.entity.produtos.Produto;
import br.com.tecsiscom.omapp.model.repository.manutencoes.PecaUtilizadaRepository;
import br.com.tecsiscom.omapp.model.service.manutencoes.PecaUtilizadaService;

@RestController
@RequestMapping("/pecas_utilizadas")
public class PecaUtilizadaController {

	@Autowired
	PecaUtilizadaService service;
	
	@Autowired
	PecaUtilizadaRepository repository;
	
	@CheckSecurity.Manutencoes.PodeConsultar
	@GetMapping()
	public List<PecaUtilizada> listar() {
		
		List<PecaUtilizada> pecaUtilizadas = repository.findAll();
		
		return pecaUtilizadas;
	}
	
	@CheckSecurity.Manutencoes.PodeConsultar
	@GetMapping("/manutencao/{manutencaoId}")
	public List<PecaUtilizada> listarPOrManutencao(@PathVariable Long manutencaoId) {
		
		List<PecaUtilizada> pecaUtilizadas = repository.findByManutencaoId(manutencaoId);
		
		return pecaUtilizadas;
	}
	
	
	
	@CheckSecurity.Manutencoes.PodeConsultar
	@GetMapping("/{pecaUtilizadaId}")
	public Optional<PecaUtilizada> buscar(@PathVariable Long pecaUtilizadaId) {
		Optional<PecaUtilizada> pecaUtilizada =repository.findById(pecaUtilizadaId);
		return pecaUtilizada;
	}
	
	
	@CheckSecurity.Manutencoes.PodeEditar
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public PecaUtilizada salvar(@RequestBody @Valid PecaUtilizada pecaUtilizada) {
		try {
			return service.salvar(pecaUtilizada);
		} catch (ProdutoNaoEncontradoException e) {
			throw new NegocioException(e.getMessage());
		}
		
	}
	
	@CheckSecurity.Manutencoes.PodeEditar
	@DeleteMapping("/{pecaUtilizadaId}")
	public ResponseEntity<Produto> remover(@PathVariable("pecaUtilizadaId") Long id) {

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

