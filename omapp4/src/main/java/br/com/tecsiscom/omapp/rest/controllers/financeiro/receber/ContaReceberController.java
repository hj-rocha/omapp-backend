package br.com.tecsiscom.omapp.rest.controllers.financeiro.receber;

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
import br.com.tecsiscom.omapp.exception.ContaReceberNaoEncontradoException;
import br.com.tecsiscom.omapp.exception.EntidadeEmUsoException;
import br.com.tecsiscom.omapp.exception.EntidadeNaoEncontradaException;
import br.com.tecsiscom.omapp.exception.NegocioException;
import br.com.tecsiscom.omapp.model.entity.financeiro.receber.ContaReceber;
import br.com.tecsiscom.omapp.model.repository.financeiro.receber.ContaReceberRepository;
import br.com.tecsiscom.omapp.model.service.financeiro.receber.ContasReceberService;


@RestController
@RequestMapping(path = "/contasReceber")
public class ContaReceberController {


		@Autowired
		ContasReceberService service;
		
		@Autowired
		ContaReceberRepository repository;
		
		@CheckSecurity.ContasReceber.PodeConsultar
		@GetMapping()
		public List<ContaReceber> listar() {
			List<ContaReceber> contaRecebers = repository.findAll();
			return contaRecebers;
		}
		
		@CheckSecurity.ContasReceber.PodeConsultar
		@GetMapping("/{contaReceberId}")
		public Optional<ContaReceber> buscar(@PathVariable Long contaReceberId) {
			Optional<ContaReceber> contaReceber = repository.findById(contaReceberId);
			return contaReceber;
		}
			
		@CheckSecurity.ContasReceber.PodeEditar
		@PostMapping
		@ResponseStatus(HttpStatus.CREATED)
		public ContaReceber salvar(@RequestBody @Valid ContaReceber contaReceber) {
			try {
				return service.salvar(contaReceber);
			} catch (ContaReceberNaoEncontradoException e) {
				throw new NegocioException(e.getMessage());
			}
		}
		
		@CheckSecurity.ContasReceber.PodeEditar
		@DeleteMapping("/{contaReceberId}")
		public ResponseEntity<ContaReceber> remover(@PathVariable("contaReceberId") Long id) {

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

