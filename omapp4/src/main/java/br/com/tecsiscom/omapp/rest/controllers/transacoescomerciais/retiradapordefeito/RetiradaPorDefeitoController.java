package br.com.tecsiscom.omapp.rest.controllers.transacoescomerciais.retiradapordefeito;

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
import br.com.tecsiscom.omapp.exception.RetiradaPorDefeitoNaoEncontradoException;
import br.com.tecsiscom.omapp.exception.EntidadeEmUsoException;
import br.com.tecsiscom.omapp.exception.EntidadeNaoEncontradaException;
import br.com.tecsiscom.omapp.exception.NegocioException;
import br.com.tecsiscom.omapp.model.entity.pessoas.Pessoa;
import br.com.tecsiscom.omapp.model.entity.transacoescomerciais.retiradapordefeito.RetiradaPorDefeito;
import br.com.tecsiscom.omapp.model.repository.transacoescomerciais.retiradapordefeito.RetiradaPorDefeitoRepository;
import br.com.tecsiscom.omapp.model.service.transacoescomerciais.retiradapordefeito.RetiradaPorDefeitoService;


@RestController
@RequestMapping(path = "/rpds")
public class RetiradaPorDefeitoController {

	
	@Autowired
	RetiradaPorDefeitoService service;
	
	@Autowired
	RetiradaPorDefeitoRepository repository;
	
	@CheckSecurity.Compras.PodeConsultar
	@GetMapping()
	public List<RetiradaPorDefeito> listar() {
		List<RetiradaPorDefeito> rpds = repository.findAll();
		
		for (Iterator<RetiradaPorDefeito> iterator = rpds.iterator(); iterator.hasNext();) {
			RetiradaPorDefeito rpd = (RetiradaPorDefeito) iterator.next();
			Pessoa pessoa = new Pessoa();
			pessoa.setId(rpd.getConferente().getId());
			rpd.setConferente(pessoa);
			rpds.indexOf(rpd);
			
		}
		return rpds;
	}
	
	@CheckSecurity.Compras.PodeConsultar
	@GetMapping("/{rpdId}")
	public Optional<RetiradaPorDefeito> buscar(@PathVariable Long rpdId) {
		Optional<RetiradaPorDefeito> rpd = repository.findById(rpdId);
		return rpd;
	}
		
	@CheckSecurity.Compras.PodeEditar
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public RetiradaPorDefeito salvar(@RequestBody @Valid RetiradaPorDefeito rpd) {
		try {
			return service.salvar(rpd);
		} catch (RetiradaPorDefeitoNaoEncontradoException e) {
			throw new NegocioException(e.getMessage());
		}
	}
	
	@CheckSecurity.Compras.PodeEditar
	@DeleteMapping("/{rpdId}")
	public ResponseEntity<RetiradaPorDefeito> remover(@PathVariable("rpdId") Long id) {

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