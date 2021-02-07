package br.com.tecsiscom.omapp.rest.controllers.financeiro.pagar;

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
import br.com.tecsiscom.omapp.exception.ContaPagarNaoEncontradoException;
import br.com.tecsiscom.omapp.exception.EntidadeEmUsoException;
import br.com.tecsiscom.omapp.exception.EntidadeNaoEncontradaException;
import br.com.tecsiscom.omapp.exception.NegocioException;
import br.com.tecsiscom.omapp.model.entity.financeiro.pagar.ContaPagar;
import br.com.tecsiscom.omapp.model.repository.financeiro.pagar.ContaPagarRepository;
import br.com.tecsiscom.omapp.model.service.financeiro.pagar.ContasPagarService;

@RestController
@RequestMapping(path = "/contas_pagar")
public class ContaPagarController {


		@Autowired
		ContasPagarService service;
		
		@Autowired
		ContaPagarRepository repository;
		
		@CheckSecurity.ContasPagar.PodeConsultar
		@GetMapping()
		public List<ContaPagar> listar() {
			List<ContaPagar> entradas = repository.findAll();
			return entradas;
		}
		
		@CheckSecurity.ContasPagar.PodeConsultar
		@GetMapping("/{entradaId}")
		public Optional<ContaPagar> buscar(@PathVariable Long entradaId) {
			Optional<ContaPagar> entrada = repository.findById(entradaId);
			return entrada;
		}
			
		@CheckSecurity.ContasPagar.PodeEditar
		@PostMapping
		@ResponseStatus(HttpStatus.CREATED)
		public ContaPagar salvar(@RequestBody @Valid ContaPagar entrada) {
			try {
				return service.salvar(entrada);
			} catch (ContaPagarNaoEncontradoException e) {
				throw new NegocioException(e.getMessage());
			}
		}
		
		@CheckSecurity.ContasPagar.PodeEditar
		@DeleteMapping("/{entradaId}")
		public ResponseEntity<ContaPagar> remover(@PathVariable("entradaId") Long id) {

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


