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
import org.springframework.web.bind.annotation.ResponseStatus;

import br.com.tecsiscom.omapp.core.security.CheckSecurity;
import br.com.tecsiscom.omapp.exception.DevolucaoClienteNaoEncontradoException;
import br.com.tecsiscom.omapp.exception.EntidadeEmUsoException;
import br.com.tecsiscom.omapp.exception.EntidadeNaoEncontradaException;
import br.com.tecsiscom.omapp.exception.NegocioException;
import br.com.tecsiscom.omapp.model.entity.transacoescomerciais.retiradapordefeito.ItemRetiradaPorDefeito;
import br.com.tecsiscom.omapp.model.entity.transacoescomerciais.retiradapordefeito.RetiradaPorDefeito;
import br.com.tecsiscom.omapp.model.repository.transacoescomerciais.retiradapordefeito.ItemRetiradaPorDefeitoRepository;
import br.com.tecsiscom.omapp.model.service.transacoescomerciais.retiradapordefeito.ItemRetiradaPorDefeitoService;

public class ItemRetiradaPorDefeitoController {


	@Autowired
	ItemRetiradaPorDefeitoService service;
	
	@Autowired
	ItemRetiradaPorDefeitoRepository repository;
	
	@CheckSecurity.Compras.PodeConsultar
	@GetMapping()
	public List<ItemRetiradaPorDefeito> listar() {
		List<ItemRetiradaPorDefeito> idcfs = repository.findAll();
		
		for (Iterator<ItemRetiradaPorDefeito> iterator = idcfs.iterator(); iterator.hasNext();) {
			ItemRetiradaPorDefeito idcf = (ItemRetiradaPorDefeito) iterator.next();
			RetiradaPorDefeito dvc = new RetiradaPorDefeito();
			dvc.setId(idcf.getSaidaPorDefeito().getId());
			idcf.setSaidaPorDefeito(dvc);
			idcfs.indexOf(idcf);
		}

		return idcfs;
	}
	
	@CheckSecurity.Compras.PodeConsultar
	@GetMapping("/{dvcId}")
	public Optional<ItemRetiradaPorDefeito> buscar(@PathVariable Long dvcId) {
		Optional<ItemRetiradaPorDefeito> dvc = repository.findById(dvcId);
		return dvc;
	}
		
	@CheckSecurity.Compras.PodeEditar
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ItemRetiradaPorDefeito salvar(@RequestBody @Valid ItemRetiradaPorDefeito dvc) {
		try {
			return service.salvar(dvc);
		} catch (DevolucaoClienteNaoEncontradoException e) {
			throw new NegocioException(e.getMessage());
		}
	}
	
	@CheckSecurity.Compras.PodeEditar
	@DeleteMapping("/{dvcId}")
	public ResponseEntity<ItemRetiradaPorDefeito> remover(@PathVariable("dvcId") Long id) {

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

