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
import br.com.tecsiscom.omapp.model.entity.manutencoes.ServicoPrestado;
import br.com.tecsiscom.omapp.model.entity.produtos.Produto;
import br.com.tecsiscom.omapp.model.repository.manutencoes.ServicoPrestadoRepository;
import br.com.tecsiscom.omapp.model.service.manutencoes.ServicoPrestadoService;

@RestController
@RequestMapping("/servicos_prestados")
public class ServicoPrestadoController {

	@Autowired
	ServicoPrestadoService service;
	
	@Autowired
	ServicoPrestadoRepository repository;
	
	@CheckSecurity.Manutencoes.PodeConsultar
	@GetMapping()
	public List<ServicoPrestado> listar() {
		
		List<ServicoPrestado> servicoPrestados = repository.findAll();
		
		return servicoPrestados;
	}
	
	@CheckSecurity.Manutencoes.PodeConsultar
	@GetMapping("/manutencao/{manutencaoId}")
	public List<ServicoPrestado> listarPOrManutencao(@PathVariable Long manutencaoId) {
		
		List<ServicoPrestado> servicoPrestados = repository.findByManutencaoId(manutencaoId);
		
		return servicoPrestados;
	}
	
	
	
	@CheckSecurity.Manutencoes.PodeConsultar
	@GetMapping("/{servicoPrestadoId}")
	public Optional<ServicoPrestado> buscar(@PathVariable Long servicoPrestadoId) {
		Optional<ServicoPrestado> servicoPrestado =repository.findById(servicoPrestadoId);
		return servicoPrestado;
	}
	
	
	@CheckSecurity.Manutencoes.PodeEditar
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ServicoPrestado salvar(@RequestBody @Valid ServicoPrestado servicoPrestado) {
		try {
			return service.salvar(servicoPrestado);
		} catch (ProdutoNaoEncontradoException e) {
			throw new NegocioException(e.getMessage());
		}
		
	}
	
	@CheckSecurity.Manutencoes.PodeEditar
	@DeleteMapping("/{servicoPrestadoId}")
	public ResponseEntity<Produto> remover(@PathVariable("servicoPrestadoId") Long id) {

		try {
			service.remover(id);
			return ResponseEntity.noContent().build();
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.notFound().build();

		} catch (EntidadeEmUsoException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}
	
	@CheckSecurity.Manutencoes.PodeEditar
	@PostMapping("/entregar_servico/{servicoPrestadoId}")
	@ResponseStatus(HttpStatus.OK)
	public void entregarServico(@PathVariable("servicoPrestadoId") Long servicoPrestadoId) {
		
		
		try {
			service.entregarServico(servicoPrestadoId);
		} catch (ProdutoNaoEncontradoException e) {
			throw new NegocioException(e.getMessage());
		}
		
	}

}

