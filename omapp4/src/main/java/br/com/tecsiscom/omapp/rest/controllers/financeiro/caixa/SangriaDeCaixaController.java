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
import br.com.tecsiscom.omapp.exception.SangriaDeCaixaNaoEncontradoException;
import br.com.tecsiscom.omapp.model.entity.financeiro.caixa.SangriaDeCaixa;
import br.com.tecsiscom.omapp.model.repository.financeiro.caixa.SangriaDeCaixaRepositoy;
import br.com.tecsiscom.omapp.model.service.financeiro.caixa.SangriaDeCaixaService;
import br.com.tecsiscom.omapp.exception.NegocioException;

@RestController
@RequestMapping(path = "/sangriaDeCaixas")
public class SangriaDeCaixaController {


		@Autowired
		SangriaDeCaixaService service;
		
		@Autowired
		SangriaDeCaixaRepositoy repository;
		
		@CheckSecurity.Caixas.PodeConsultar
		@GetMapping()
		public List<SangriaDeCaixa> listar() {
			List<SangriaDeCaixa> sangriasDeCaixa = repository.findAll();
			return sangriasDeCaixa;
		}
		
		@CheckSecurity.Caixas.PodeConsultar
		@GetMapping("/{sangriaDeCaixaId}")
		public Optional<SangriaDeCaixa> buscar(@PathVariable Long sangriaDeCaixaId) {
			Optional<SangriaDeCaixa> sangriaDeCaixa = repository.findById(sangriaDeCaixaId);
			return sangriaDeCaixa;
		}
			
		@CheckSecurity.Caixas.PodeEditar
		@PostMapping
		@ResponseStatus(HttpStatus.CREATED)
		public SangriaDeCaixa salvar(@RequestBody @Valid SangriaDeCaixa sangriaDeCaixa) {
			try {
				return service.salvar(sangriaDeCaixa);
			} catch (SangriaDeCaixaNaoEncontradoException e) {
				throw new NegocioException(e.getMessage());
			}
		}
		
		@CheckSecurity.Caixas.PodeEditar
		@DeleteMapping("/{sangriaDeCaixaId}")
		public ResponseEntity<SangriaDeCaixa> remover(@PathVariable("sangriaDeCaixaId") Long id) {

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

