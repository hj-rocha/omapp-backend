package br.com.tecsiscom.omapp.rest.controllers.financeiro;

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
import br.com.tecsiscom.omapp.exception.DebitoNaoEncontradoException;
import br.com.tecsiscom.omapp.exception.EntidadeEmUsoException;
import br.com.tecsiscom.omapp.exception.EntidadeNaoEncontradaException;
import br.com.tecsiscom.omapp.exception.NegocioException;
import br.com.tecsiscom.omapp.model.entity.financeiro.caixa.Debito;
import br.com.tecsiscom.omapp.model.repository.financeiro.DebitoRepository;
import br.com.tecsiscom.omapp.model.service.financeiro.DebitoService;

@RestController
@RequestMapping(path = "/debitos")
public class DebitoController {


		@Autowired
		DebitoService service;
		
		@Autowired
		DebitoRepository repository;
		
		@CheckSecurity.Caixas.PodeConsultar
		@GetMapping()
		public List<Debito> listar() {
			List<Debito> debitos = repository.findAll();
			return debitos;
		}
		
		@CheckSecurity.Caixas.PodeConsultar
		@GetMapping("/{debitoId}")
		public Optional<Debito> buscar(@PathVariable Long debitoId) {
			Optional<Debito> debito = repository.findById(debitoId);
			return debito;
		}
			
		@CheckSecurity.Caixas.PodeEditar
		@PostMapping
		@ResponseStatus(HttpStatus.CREATED)
		public Debito salvar(@RequestBody @Valid Debito debito) {
			try {
				return service.salvar(debito);
			} catch (DebitoNaoEncontradoException e) {
				throw new NegocioException(e.getMessage());
			}
		}
		
		@CheckSecurity.Caixas.PodeEditar
		@DeleteMapping("/{debitoId}")
		public ResponseEntity<Debito> remover(@PathVariable("debitoId") Long id) {

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
