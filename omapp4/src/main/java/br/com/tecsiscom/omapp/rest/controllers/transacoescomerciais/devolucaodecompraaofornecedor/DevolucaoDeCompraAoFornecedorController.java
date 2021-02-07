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
import br.com.tecsiscom.omapp.exception.CompraNaoEncontradoException;
import br.com.tecsiscom.omapp.exception.EntidadeEmUsoException;
import br.com.tecsiscom.omapp.exception.EntidadeNaoEncontradaException;
import br.com.tecsiscom.omapp.exception.NegocioException;
import br.com.tecsiscom.omapp.model.entity.pessoas.Pessoa;
import br.com.tecsiscom.omapp.model.entity.transacoescomerciais.compras.Compra;
import br.com.tecsiscom.omapp.model.entity.transacoescomerciais.devolcucaodecompraaofornecedor.DevolucaoDeCompraAoFornecedor;
import br.com.tecsiscom.omapp.model.repository.transacoescomerciais.devolucaodecompraaofornecedor.DevolucaoDeCompraAoFornecedorRepository;
import br.com.tecsiscom.omapp.model.service.transacoescomerciais.devolucaodecompraaofornecedor.DevolucaoDeCompraAoFornecedorService;

@RestController
@RequestMapping(path = "/dcf")
public class DevolucaoDeCompraAoFornecedorController {

	@Autowired
	DevolucaoDeCompraAoFornecedorService service;
	
	@Autowired
	DevolucaoDeCompraAoFornecedorRepository repository;
	
	@CheckSecurity.Compras.PodeConsultar
	@GetMapping()
	public List<DevolucaoDeCompraAoFornecedor> listar() {
		List<DevolucaoDeCompraAoFornecedor> dcfs = repository.findAll();
		
		for (Iterator<DevolucaoDeCompraAoFornecedor> iterator = dcfs.iterator(); iterator.hasNext();) {
			DevolucaoDeCompraAoFornecedor dcf = (DevolucaoDeCompraAoFornecedor) iterator.next();
			Pessoa pessoa = new Pessoa();
			pessoa.setId(dcf.getConferente().getId());
			dcf.setConferente(pessoa);
			dcfs.indexOf(dcf);
			
		}
		return dcfs;
	}
	
	@CheckSecurity.Compras.PodeConsultar
	@GetMapping("/{dcfId}")
	public Optional<DevolucaoDeCompraAoFornecedor> buscar(@PathVariable Long dcfId) {
		Optional<DevolucaoDeCompraAoFornecedor> dcf = repository.findById(dcfId);
		return dcf;
	}
		
	@CheckSecurity.Compras.PodeEditar
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public DevolucaoDeCompraAoFornecedor salvar(@RequestBody @Valid DevolucaoDeCompraAoFornecedor dcf) {
		try {
			return service.salvar(dcf);
		} catch (CompraNaoEncontradoException e) {
			throw new NegocioException(e.getMessage());
		}
	}
	
	@CheckSecurity.Compras.PodeEditar
	@DeleteMapping("/{dcfId}")
	public ResponseEntity<Compra> remover(@PathVariable("dcfId") Long id) {

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
