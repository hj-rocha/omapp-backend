package br.com.tecsiscom.omapp.rest.controllers.financeiro.caixa;

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
import br.com.tecsiscom.omapp.exception.ReforcoDeCaixaNaoEncontradoException;
import br.com.tecsiscom.omapp.model.entity.financeiro.caixa.ReforcoDeCaixa;
import br.com.tecsiscom.omapp.model.repository.financeiro.caixa.ReforcoDeCaixaRepository;
import br.com.tecsiscom.omapp.model.service.financeiro.caixa.ReforcoDeCaixaService;

@RestController
@RequestMapping(path = "/reforcoDeCaixas")
public class ReforcoDeCaixaController {


		@Autowired
		ReforcoDeCaixaService service;
		
		@Autowired
		ReforcoDeCaixaRepository repository;
		
		@CheckSecurity.Caixas.PodeConsultar
		@GetMapping()
		public List<br.com.tecsiscom.omapp.model.entity.financeiro.caixa.ReforcoDeCaixa> listar() {
			List<ReforcoDeCaixa> reforcosDeCaixa = repository.findAll();
			return reforcosDeCaixa;
		}
		
		@CheckSecurity.Caixas.PodeConsultar
		@GetMapping("/{reforcoDeCaixaId}")
		public Optional<ReforcoDeCaixa> buscar(@PathVariable Long reforcoDeCaixaId) {
			Optional<ReforcoDeCaixa> reforcoDeCaixa = repository.findById(reforcoDeCaixaId);
			return reforcoDeCaixa;
		}
			
		@CheckSecurity.Caixas.PodeEditar
		@PostMapping
		@ResponseStatus(HttpStatus.CREATED)
		public ReforcoDeCaixa salvar(@RequestBody @Valid ReforcoDeCaixa reforcoDeCaixa) {
			try {
				return service.salvar(reforcoDeCaixa);
			} catch (ReforcoDeCaixaNaoEncontradoException e) {
				throw new NegocioException(e.getMessage());
			}
		}
		
		@CheckSecurity.Caixas.PodeEditar
		@DeleteMapping("/{reforcoDeCaixaId}")
		public ResponseEntity<ReforcoDeCaixa> remover(@PathVariable("reforcoDeCaixaId") Long id) {

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

