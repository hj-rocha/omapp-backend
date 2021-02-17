package br.com.tecsiscom.omapp.rest.controllers.veiculos;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
import br.com.tecsiscom.omapp.model.entity.produtos.Produto;
import br.com.tecsiscom.omapp.model.entity.veiculos.Veiculo;
import br.com.tecsiscom.omapp.model.repository.veiculos.VeiculoRepository;
import br.com.tecsiscom.omapp.model.service.veiculos.VeiculoService;

@RestController
@RequestMapping("/veiculos")
public class VeiculoController {
	
	@Autowired
	VeiculoService service;
	
	@Autowired
	VeiculoRepository repository;
	
	@CheckSecurity.Produtos.PodeConsultar
	@GetMapping()
	public List<Veiculo> listar() {
		List<Veiculo> veiculos = repository.findAll();
		
		return veiculos;
	}

	@CheckSecurity.Produtos.PodeConsultar
	@GetMapping("/{veiculoId}")
	public Optional<Veiculo> buscar(@PathVariable Long veiculoId) {
		Optional<Veiculo> veiculo =repository.findById(veiculoId);
		return veiculo;
	}
	
	@GetMapping("/placa/{veiculoPlaca}")
	public List<Veiculo> buscarPorPlaca(@PathVariable String veiculoPlaca) {
		List<Veiculo> veiculos = repository.findByPlacaStartingWith(veiculoPlaca);
		return veiculos;
	}
	
	@GetMapping("/renavam/{veiculoRenavam}")
	public List<Veiculo> listarrPorRenavam(@PathVariable String veiculoRenavam) {
		List<Veiculo> veiculos = repository.findByRenavamStartingWith(veiculoRenavam);
		return veiculos;
	}
	
	@CheckSecurity.Produtos.PodeConsultar
	@GetMapping("/renavam/noestoque/{renavam}")
	public List<Veiculo> listarVeiculosNoEstoquePorRenavam(@PathVariable String renavam) {
		List<Veiculo> veiculos = service.listarPorRenavamEEstoquePositivo(renavam);

		return veiculos;
	}
	
	
	@CheckSecurity.Produtos.PodeEditar
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Veiculo salvar(@RequestBody @Valid Veiculo veiculo) {
		try {
			return service.salvar(veiculo);
		} catch (ProdutoNaoEncontradoException e) {
			throw new NegocioException(e.getMessage());
			/*RN:32*/
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format("RENAVAM %s já está cadastrado.", veiculo.getRenavam()));
		}
		
	}
	
	@CheckSecurity.Produtos.PodeEditar
	@DeleteMapping("/{veiculoId}")
	public ResponseEntity<Produto> remover(@PathVariable("veiculoId") Long id) {

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
