package br.com.tecsiscom.omapp.rest.controllers.transacoescomerciais.devolucaovendaaocliente;

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
import org.springframework.web.bind.annotation.ResponseStatus;

import br.com.tecsiscom.omapp.core.security.CheckSecurity;
import br.com.tecsiscom.omapp.exception.DevolucaoClienteNaoEncontradoException;
import br.com.tecsiscom.omapp.exception.EntidadeEmUsoException;
import br.com.tecsiscom.omapp.exception.EntidadeNaoEncontradaException;
import br.com.tecsiscom.omapp.exception.NegocioException;
import br.com.tecsiscom.omapp.model.entity.pessoas.Pessoa;
import br.com.tecsiscom.omapp.model.entity.transacoescomerciais.devolucaovendaaocliente.DevolucaoDeVendaAoCliente;
import br.com.tecsiscom.omapp.model.repository.transacoescomerciais.devolucaovendaaocliente.DevolucaoVendaAoClienteRepository;
import br.com.tecsiscom.omapp.model.service.transacoescomerciais.devolucaovendaaocliente.DevolucaoDeVendaAoClienteService;

public class DevolucaoDeVendaAoClienteController {

	@Autowired
	DevolucaoDeVendaAoClienteService service;
	
	@Autowired
	DevolucaoVendaAoClienteRepository repository;
	
	@CheckSecurity.Vendas.PodeConsultar
	@GetMapping()
	public List<DevolucaoDeVendaAoCliente> listar() {
		List<DevolucaoDeVendaAoCliente> compras = repository.findAll();
		
		for (Iterator<DevolucaoDeVendaAoCliente> iterator = compras.iterator(); iterator.hasNext();) {
			DevolucaoDeVendaAoCliente compra = (DevolucaoDeVendaAoCliente) iterator.next();
			Pessoa pessoa = new Pessoa();
			pessoa.setId(compra.getConferente().getId());
			compra.setConferente(pessoa);
			compras.indexOf(compra);
		
		}
		return compras;
	}
	
	@CheckSecurity.Vendas.PodeConsultar
	@GetMapping("/{dvcId}")
	public Optional<DevolucaoDeVendaAoCliente> buscar(@PathVariable Long dvcId) {
		Optional<DevolucaoDeVendaAoCliente> dvc = repository.findById(dvcId);
		return dvc;
	}
		
	@CheckSecurity.Vendas.PodeEditar
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public DevolucaoDeVendaAoCliente salvar(@RequestBody @Valid DevolucaoDeVendaAoCliente dvc) {
		try {
			return service.salvar(dvc);
		} catch (DevolucaoClienteNaoEncontradoException e) {
			throw new NegocioException(e.getMessage());
		}
	}
	
	@CheckSecurity.Vendas.PodeEditar
	@DeleteMapping("/{dvcId}")
	public ResponseEntity<DevolucaoDeVendaAoCliente> remover(@PathVariable("dvcId") Long id) {

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
