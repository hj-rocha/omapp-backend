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
import br.com.tecsiscom.omapp.exception.CreditoNaoEncontradoException;
import br.com.tecsiscom.omapp.exception.EntidadeEmUsoException;
import br.com.tecsiscom.omapp.exception.EntidadeNaoEncontradaException;
import br.com.tecsiscom.omapp.exception.NegocioException;
import br.com.tecsiscom.omapp.model.entity.financeiro.caixa.Credito;
import br.com.tecsiscom.omapp.model.repository.financeiro.CreditoRepository;
import br.com.tecsiscom.omapp.model.service.financeiro.CreditoService;


@RestController
@RequestMapping(path = "/creditos")
public class CreditoController {


		@Autowired
		CreditoService service;
		
		@Autowired
		CreditoRepository repository;
		
		@CheckSecurity.Caixas.PodeConsultar
		@GetMapping()
		public List<Credito> listar() {
			List<Credito> creditos = repository.findAll();
			return creditos;
		}
		
		@CheckSecurity.Caixas.PodeConsultar
		@GetMapping("/{creditoId}")
		public Optional<Credito> buscar(@PathVariable Long creditoId) {
			Optional<Credito> credito = repository.findById(creditoId);
			return credito;
		}
			
		@CheckSecurity.Caixas.PodeEditar
		@PostMapping
		@ResponseStatus(HttpStatus.CREATED)
		public Credito salvar(@RequestBody @Valid Credito credito) {
			try {
				return service.salvar(credito);
			} catch (CreditoNaoEncontradoException e) {
				throw new NegocioException(e.getMessage());
			}
		}
		
		@CheckSecurity.Caixas.PodeEditar
		@DeleteMapping("/{creditoId}")
		public ResponseEntity<Credito> remover(@PathVariable("creditoId") Long id) {

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

