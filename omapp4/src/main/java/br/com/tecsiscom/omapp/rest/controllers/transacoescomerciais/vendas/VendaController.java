package br.com.tecsiscom.omapp.rest.controllers.transacoescomerciais.vendas;

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
import br.com.tecsiscom.omapp.exception.VendaNaoEncontradoException;
import br.com.tecsiscom.omapp.model.entity.pessoas.Pessoa;
import br.com.tecsiscom.omapp.model.entity.transacoescomerciais.vendas.Venda;
import br.com.tecsiscom.omapp.model.repository.transacoescomerciais.vendas.VendaRepository;
import br.com.tecsiscom.omapp.model.service.transacoescomerciais.vendas.VendasService;

@RestController
@RequestMapping(path = "/vendas")
public class VendaController {

	
	
	@Autowired
	VendasService service;
	
	@Autowired
	VendaRepository repository;
	
	@CheckSecurity.Vendas.PodeConsultar
	@GetMapping()
	public List<Venda> listar() {
		List<Venda> vendas = repository.findAll();
		
		for (Iterator<Venda> iterator = vendas.iterator(); iterator.hasNext();) {
			Venda venda = (Venda) iterator.next();
			Pessoa pessoa = new Pessoa();
			pessoa.setId(venda.getConferente().getId());
			venda.setConferente(pessoa);
			pessoa.setId(venda.getVendedor().getId());
			venda.setVendedor(pessoa);
			pessoa.setId(venda.getCliente().getId());
			venda.setCliente(pessoa);
			vendas.indexOf(venda);
			
		}
		return vendas;
	}
	
	@CheckSecurity.Vendas.PodeConsultar
	@GetMapping("/{vendaId}")
	public Optional<Venda> buscar(@PathVariable Long vendaId) {
		Optional<Venda> venda = repository.findById(vendaId);
		return venda;
	}
		
	@CheckSecurity.Vendas.PodeEditar
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Venda salvar(@RequestBody @Valid Venda venda) {
		try {
			return service.salvar(venda);
		} catch (VendaNaoEncontradoException e) {
			throw new NegocioException(e.getMessage());
		}
	}
	
	@CheckSecurity.Vendas.PodeEditar
	@DeleteMapping("/{vendaId}")
	public ResponseEntity<Venda> remover(@PathVariable("vendaId") Long id) {

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
