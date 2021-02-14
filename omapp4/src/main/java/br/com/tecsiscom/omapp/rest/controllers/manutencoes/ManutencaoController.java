
package br.com.tecsiscom.omapp.rest.controllers.manutencoes;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
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
import br.com.tecsiscom.omapp.model.entity.manutencoes.Manutencao;
import br.com.tecsiscom.omapp.model.entity.produtos.Produto;
import br.com.tecsiscom.omapp.model.repository.manutencoes.ManutencaoRepository;
import br.com.tecsiscom.omapp.model.service.manutencoes.ManutencaoService;

@RestController
@RequestMapping("/manutencoes")
public class ManutencaoController {

	@Autowired
	ManutencaoService manutencaoService;
	
	@Autowired
	ManutencaoRepository repository;
	
	@CheckSecurity.Manutencoes.PodeConsultar
	@GetMapping()
	public List<Manutencao> listar() {
		
		List<Manutencao> manutencaos = repository.findAll();
		
		return manutencaos;
	}
	
	@CheckSecurity.Manutencoes.PodeConsultar
	@GetMapping("/{manutencaoId}")
	public Optional<Manutencao> buscar(@PathVariable Long manutencaoId) {
		Optional<Manutencao> manutencao =repository.findById(manutencaoId);
		return manutencao;
	}
	
//	@CheckSecurity.Manutencoes.PodeConsultar
//	@GetMapping("/{veiculoId}")
//	public Optional<Manutencao> buscarPorVeiculo(@PathVariable Long veiculoId) {
//		Optional<Manutencao> manutencao = Optional.ofNullable(this.repository.findByVeiculoIdAndStatus(veiculoId, true));
//		return manutencao;
//	}
	
	
	@CheckSecurity.Manutencoes.PodeEditar
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Manutencao salvar(@RequestBody @Valid Manutencao manutencao) {
		try {
			return manutencaoService.salvar(manutencao);
		} catch (ProdutoNaoEncontradoException e) {
			throw new NegocioException(e.getMessage());
		}
		catch (HttpMessageNotReadableException e) {
			throw new NegocioException("Deve ser informados uma placa e uma pessoa cadastradoas.");
		}
	}
	
	@CheckSecurity.Manutencoes.PodeEditar
	@DeleteMapping("/{manutencaoId}")
	public ResponseEntity<Produto> remover(@PathVariable("manutencaoId") Long id) {

		try {
			manutencaoService.remover(id);
			return ResponseEntity.noContent().build();
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.notFound().build();

		} catch (EntidadeEmUsoException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}
	
	@CheckSecurity.Manutencoes.PodeEditar
	@PostMapping("/{manutencaoId}/status/{status}")
	@ResponseStatus(HttpStatus.CREATED)
	public Manutencao alterarStatusManutencao(@PathVariable("manutencaoId") Long manutencaoId, @PathVariable("status") Boolean status) {
			return manutencaoService.alterarStatusManutencao(manutencaoId, status);
	}

}

