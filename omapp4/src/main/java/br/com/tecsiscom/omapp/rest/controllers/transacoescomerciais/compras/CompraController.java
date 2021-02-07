package br.com.tecsiscom.omapp.rest.controllers.transacoescomerciais.compras;

import java.util.Iterator;
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
import br.com.tecsiscom.omapp.exception.CompraNaoEncontradoException;
import br.com.tecsiscom.omapp.exception.EntidadeEmUsoException;
import br.com.tecsiscom.omapp.exception.EntidadeNaoEncontradaException;
import br.com.tecsiscom.omapp.exception.NegocioException;
import br.com.tecsiscom.omapp.model.entity.pessoas.Pessoa;
import br.com.tecsiscom.omapp.model.entity.transacoescomerciais.compras.Compra;
import br.com.tecsiscom.omapp.model.repository.transacoescomerciais.compras.CompraRepository;
import br.com.tecsiscom.omapp.model.service.transacoescomerciais.compras.CompraService;

@RestController
@RequestMapping(path = "/compras")
public class CompraController {

	
	@Autowired
	CompraService service;
	
	@Autowired
	CompraRepository repository;
	
	@CheckSecurity.Compras.PodeConsultar
	@GetMapping()
	public List<Compra> listar() {
		List<Compra> compras = repository.findAll();
		
		for (Iterator<Compra> iterator = compras.iterator(); iterator.hasNext();) {
			Compra compra = (Compra) iterator.next();
			Pessoa pessoa = new Pessoa();
			pessoa.setId(compra.getConferente().getId());
			compra.setConferente(pessoa);
			pessoa.setId(compra.getComprador().getId());
			compra.setComprador(pessoa);
			pessoa.setId(compra.getFornecedor().getId());
			compra.setFornecedor(pessoa);
			compras.indexOf(compra);
			
		}
		return compras;
	}
	
	@CheckSecurity.Compras.PodeConsultar
	@GetMapping("/{compraId}")
	public Optional<Compra> buscar(@PathVariable Long compraId) {
		Optional<Compra> compra = repository.findById(compraId);
		return compra;
	}
		
	@CheckSecurity.Compras.PodeEditar
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Compra salvar(@RequestBody @Valid Compra compra) {
		try {
			return service.salvar(compra);
		} catch (CompraNaoEncontradoException e) {
			throw new NegocioException(e.getMessage());
		}
	}
	
	@CheckSecurity.Compras.PodeEditar
	@DeleteMapping("/{compraId}")
	public ResponseEntity<Compra> remover(@PathVariable("compraId") Long id) {

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
