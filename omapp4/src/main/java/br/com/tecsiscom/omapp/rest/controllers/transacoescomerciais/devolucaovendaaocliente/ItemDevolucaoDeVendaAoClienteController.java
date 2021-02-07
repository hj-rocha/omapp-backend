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
import br.com.tecsiscom.omapp.model.entity.transacoescomerciais.devolucaovendaaocliente.DevolucaoDeVendaAoCliente;
import br.com.tecsiscom.omapp.model.entity.transacoescomerciais.devolucaovendaaocliente.ItemDevolucaoDeVendaAoCliente;
import br.com.tecsiscom.omapp.model.repository.transacoescomerciais.devolucaovendaaocliente.ItemDevolucaoVendaAoClienteRepository;
import br.com.tecsiscom.omapp.model.service.transacoescomerciais.devolucaovendaaocliente.ItemDevolucaoDeVendaAoClienteService;

public class ItemDevolucaoDeVendaAoClienteController {


	@Autowired
	ItemDevolucaoDeVendaAoClienteService service;
	
	@Autowired
	ItemDevolucaoVendaAoClienteRepository repository;
	
	@CheckSecurity.Vendas.PodeConsultar
	@GetMapping()
	public List<ItemDevolucaoDeVendaAoCliente> listar() {
		List<ItemDevolucaoDeVendaAoCliente> idcfs = repository.findAll();
		
		for (Iterator<ItemDevolucaoDeVendaAoCliente> iterator = idcfs.iterator(); iterator.hasNext();) {
			ItemDevolucaoDeVendaAoCliente idcf = (ItemDevolucaoDeVendaAoCliente) iterator.next();
			DevolucaoDeVendaAoCliente dvc = new DevolucaoDeVendaAoCliente();
			dvc.setId(idcf.getDevolucaoDeVendaAoCliente().getId());
			idcf.setDevolucaoDeVendaAoCliente(dvc);
			idcfs.indexOf(idcf);
		}

		return idcfs;
	}
	
	@CheckSecurity.Vendas.PodeConsultar
	@GetMapping("/{dvcId}")
	public Optional<ItemDevolucaoDeVendaAoCliente> buscar(@PathVariable Long dvcId) {
		Optional<ItemDevolucaoDeVendaAoCliente> dvc = repository.findById(dvcId);
		return dvc;
	}
		
	@CheckSecurity.Vendas.PodeEditar
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ItemDevolucaoDeVendaAoCliente salvar(@RequestBody @Valid ItemDevolucaoDeVendaAoCliente dvc) {
		try {
			return service.salvar(dvc);
		} catch (DevolucaoClienteNaoEncontradoException e) {
			throw new NegocioException(e.getMessage());
		}
	}
	
	@CheckSecurity.Vendas.PodeEditar
	@DeleteMapping("/{dvcId}")
	public ResponseEntity<ItemDevolucaoDeVendaAoCliente> remover(@PathVariable("dvcId") Long id) {

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

