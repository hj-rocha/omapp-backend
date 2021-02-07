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
import br.com.tecsiscom.omapp.exception.EntidadeEmUsoException;
import br.com.tecsiscom.omapp.exception.EntidadeNaoEncontradaException;
import br.com.tecsiscom.omapp.exception.ItemCompraNaoEncontradoException;
import br.com.tecsiscom.omapp.exception.NegocioException;
import br.com.tecsiscom.omapp.model.entity.transacoescomerciais.compras.Compra;
import br.com.tecsiscom.omapp.model.entity.transacoescomerciais.compras.ItemCompra;
import br.com.tecsiscom.omapp.model.repository.transacoescomerciais.compras.ItemCompraRepository;
import br.com.tecsiscom.omapp.model.service.transacoescomerciais.compras.ItemCompraService;

@RestController
@RequestMapping(path = "/itens_compras")
public class ItemCompraController {

	
	@Autowired
	ItemCompraService service;
	
	@Autowired
	ItemCompraRepository repository;
	
	@CheckSecurity.Compras.PodeConsultar
	@GetMapping()
	public List<ItemCompra> listar() {
		
		List<ItemCompra> itensCompra = repository.findAll();
		
		for (Iterator<ItemCompra> iterator = itensCompra.iterator(); iterator.hasNext();) {
			ItemCompra itemCompra = (ItemCompra) iterator.next();
			Compra compra = new Compra();
			compra.setId(itemCompra.getCompra().getId());
			itemCompra.setCompra(compra);
			itensCompra.indexOf(itemCompra);
		}
		return itensCompra;
	}
	
	@CheckSecurity.Compras.PodeConsultar
	@GetMapping("/{itemCompraId}")
	public Optional<ItemCompra> buscar(@PathVariable Long itemCompraId) {
		Optional<ItemCompra> itemCompra = repository.findById(itemCompraId);
		return itemCompra;
	}
		
	@CheckSecurity.Compras.PodeEditar
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ItemCompra salvar(@RequestBody @Valid ItemCompra itemCompra) {

		try {
			return service.salvar(itemCompra);
		} catch (ItemCompraNaoEncontradoException e) {
			throw new NegocioException(e.getMessage());
		}
	}
	
	@CheckSecurity.Compras.PodeEditar
	@DeleteMapping("/{itemCompraId}")
	public ResponseEntity<ItemCompra> remover(@PathVariable("itemCompraId") Long id) {

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
