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
import br.com.tecsiscom.omapp.exception.ValeNaoEncontradoException;
import br.com.tecsiscom.omapp.model.entity.financeiro.caixa.Vale;
import br.com.tecsiscom.omapp.model.repository.financeiro.caixa.ValeRepository;
import br.com.tecsiscom.omapp.model.service.financeiro.caixa.ValeService;


@RestController
@RequestMapping(path = "/vales")
public class ValeController {


		@Autowired
		ValeService service;
		
		@Autowired
		ValeRepository repository;
		
		@CheckSecurity.Caixas.PodeConsultar
		@GetMapping()
		public List<Vale> listar() {
			List<Vale> vales = repository.findAll();
			return vales;
		}
		
		@CheckSecurity.Caixas.PodeConsultar
		@GetMapping("/{valeId}")
		public Optional<Vale> buscar(@PathVariable Long valeId) {
			Optional<Vale> vale = repository.findById(valeId);
			return vale;
		}
			
		@CheckSecurity.Caixas.PodeEditar
		@PostMapping
		@ResponseStatus(HttpStatus.CREATED)
		public Vale salvar(@RequestBody @Valid Vale vale) {
			try {
				return service.salvar(vale);
			} catch (ValeNaoEncontradoException e) {
				throw new NegocioException(e.getMessage());
			}
		}
		
		@CheckSecurity.Caixas.PodeEditar
		@DeleteMapping("/{valeId}")
		public ResponseEntity<Vale> remover(@PathVariable("valeId") Long id) {

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


