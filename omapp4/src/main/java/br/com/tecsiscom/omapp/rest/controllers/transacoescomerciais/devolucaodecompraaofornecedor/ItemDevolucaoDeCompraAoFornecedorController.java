package br.com.tecsiscom.omapp.rest.controllers.transacoescomerciais.devolucaodecompraaofornecedor;

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
import br.com.tecsiscom.omapp.exception.NegocioException;
import br.com.tecsiscom.omapp.model.entity.transacoescomerciais.compras.Compra;
import br.com.tecsiscom.omapp.model.entity.transacoescomerciais.devolcucaodecompraaofornecedor.DevolucaoDeCompraAoFornecedor;
import br.com.tecsiscom.omapp.model.entity.transacoescomerciais.devolcucaodecompraaofornecedor.ItemDevolucaoDeCompraAoFornecedor;
import br.com.tecsiscom.omapp.model.repository.transacoescomerciais.devolucaodecompraaofornecedor.ItemDevolucaoDeCompraAoFornecedorRepository;
import br.com.tecsiscom.omapp.model.service.transacoescomerciais.devolucaodecompraaofornecedor.ItemDevolucaoDeCompraAoFornecedorService;

@RestController
@RequestMapping(path = "/idcf")
public class ItemDevolucaoDeCompraAoFornecedorController {

	@Autowired
	ItemDevolucaoDeCompraAoFornecedorService service;
	
	@Autowired
	ItemDevolucaoDeCompraAoFornecedorRepository repository;
	
	@CheckSecurity.Compras.PodeConsultar
	@GetMapping()
	public List<ItemDevolucaoDeCompraAoFornecedor> listar() {
		
		List<ItemDevolucaoDeCompraAoFornecedor> idcfs = repository.findAll();
		
		for (Iterator<ItemDevolucaoDeCompraAoFornecedor> iterator = idcfs.iterator(); iterator.hasNext();) {
			ItemDevolucaoDeCompraAoFornecedor idcf = (ItemDevolucaoDeCompraAoFornecedor) iterator.next();
			DevolucaoDeCompraAoFornecedor dcf = new DevolucaoDeCompraAoFornecedor();
			dcf.setId(idcf.getDevolucaoDeCompraAoFornecedor().getId());
			idcf.setDevolucaoDeCompraAoFornecedor(dcf);
			idcfs.indexOf(idcf);
		}
		return idcfs;
	}
	
	@CheckSecurity.Compras.PodeConsultar
	@GetMapping("/{idcfId}")
	public Optional<ItemDevolucaoDeCompraAoFornecedor> buscar(@PathVariable Long idcfId) {
		Optional<ItemDevolucaoDeCompraAoFornecedor> idcf = repository.findById(idcfId);
		return idcf;
	}
		
	@CheckSecurity.Compras.PodeEditar
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ItemDevolucaoDeCompraAoFornecedor salvar(@RequestBody @Valid ItemDevolucaoDeCompraAoFornecedor idcf) {
		try {
			return service.salvar(idcf);
		} catch (EntidadeNaoEncontradaException e) {
			throw new NegocioException(e.getMessage());
		}
	}
	
	@CheckSecurity.Compras.PodeEditar
	@DeleteMapping("/{idcfId}")
	public ResponseEntity<Compra> remover(@PathVariable("idcfId") Long id) {

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
