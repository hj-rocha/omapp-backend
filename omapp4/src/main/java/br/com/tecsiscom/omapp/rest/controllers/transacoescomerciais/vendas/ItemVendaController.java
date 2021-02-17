package br.com.tecsiscom.omapp.rest.controllers.transacoescomerciais.vendas;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
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
import br.com.tecsiscom.omapp.exception.ItemVendaNaoEncontradoException;
import br.com.tecsiscom.omapp.exception.NegocioException;
import br.com.tecsiscom.omapp.model.entity.transacoescomerciais.vendas.ItemVenda;
import br.com.tecsiscom.omapp.model.entity.transacoescomerciais.vendas.Venda;
import br.com.tecsiscom.omapp.model.repository.transacoescomerciais.vendas.ItemVendaRepository;
import br.com.tecsiscom.omapp.model.service.transacoescomerciais.vendas.ItemVendaService;



@RestController
@RequestMapping(path = "/itens_venda")
public class ItemVendaController {

	
	@Autowired
	ItemVendaService service;
	
	@Autowired
	ItemVendaRepository repository;
	
	@CheckSecurity.Vendas.PodeConsultar
	@GetMapping()
	public List<ItemVenda> listar() {
		
		List<ItemVenda> itensVenda = repository.findAll();
		
		for (Iterator<ItemVenda> iterator = itensVenda.iterator(); iterator.hasNext();) {
			ItemVenda itemVenda = (ItemVenda) iterator.next();
			Venda venda = new Venda();
			venda.setId(itemVenda.getVenda().getId());
			itemVenda.setVenda(venda);
			itensVenda.indexOf(itemVenda);
		}
		return itensVenda;
	}
	
	@CheckSecurity.Vendas.PodeConsultar
	@GetMapping("/{itemVendaId}")
	public Optional<ItemVenda> buscar(@PathVariable Long itemVendaId) {
		Optional<ItemVenda> itemVenda = repository.findById(itemVendaId);
		return itemVenda;
	}
	
	@CheckSecurity.Vendas.PodeEditar
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ItemVenda salvar(@RequestBody @Valid ItemVenda itemVenda) {
		
		try {
			return service.salvar(itemVenda);
		} catch (ItemVendaNaoEncontradoException e) {
			throw new NegocioException(e.getMessage());
		}
	}
		
//	@CheckSecurity.Vendas.PodeEditar
//	@PostMapping("/{vendaId}")
//	@ResponseStatus(HttpStatus.CREATED)
//	public ItemVenda salvar(@RequestBody @Valid ItemVenda itemVenda, @PathVariable("vendaId") Long vendaId) {
//		
//		Venda v = new Venda();
//		v.setId(vendaId);
//		itemVenda.setVenda(v);
//		
//		try {
//			return service.salvar(itemVenda);
//		} catch (ItemVendaNaoEncontradoException e) {
//			throw new NegocioException(e.getMessage());
//		}
//	}
	
	@CheckSecurity.Vendas.PodeEditar
	@DeleteMapping("/{itemVendaId}")
	public ResponseEntity<ItemVenda> remover(@PathVariable("itemVendaId") Long id) {

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
