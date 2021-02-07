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
import br.com.tecsiscom.omapp.exception.CaixaNaoEncontradoException;
import br.com.tecsiscom.omapp.exception.EntidadeEmUsoException;
import br.com.tecsiscom.omapp.exception.EntidadeNaoEncontradaException;
import br.com.tecsiscom.omapp.exception.NegocioException;
import br.com.tecsiscom.omapp.model.entity.financeiro.caixa.Caixa;
import br.com.tecsiscom.omapp.model.repository.financeiro.caixa.CaixaRepository;
import br.com.tecsiscom.omapp.model.service.financeiro.caixa.CaixaService;


@RestController
@RequestMapping(path = "/caixas")
public class CaixaController {


		@Autowired
		CaixaService service;
		
		@Autowired
		CaixaRepository repository;
		
		@CheckSecurity.Caixas.PodeConsultar
		@GetMapping()
		public List<Caixa> listar() {
			List<Caixa> caixas = repository.findAll();
			return caixas;
		}
		
		@CheckSecurity.Caixas.PodeConsultar
		@GetMapping("/{caixaId}")
		public Optional<Caixa> buscar(@PathVariable Long caixaId) {
			Optional<Caixa> caixa = repository.findById(caixaId);
			return caixa;
		}
			
		@CheckSecurity.Caixas.PodeEditar
		@PostMapping
		@ResponseStatus(HttpStatus.CREATED)
		public Caixa salvar(@RequestBody @Valid Caixa caixa) {
			try {
				return service.salvar(caixa);
			} catch (CaixaNaoEncontradoException e) {
				throw new NegocioException(e.getMessage());
			}
		}
		
		@CheckSecurity.Caixas.PodeEditar
		@DeleteMapping("/{caixaId}")
		public ResponseEntity<Caixa> remover(@PathVariable("caixaId") Long id) {

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


