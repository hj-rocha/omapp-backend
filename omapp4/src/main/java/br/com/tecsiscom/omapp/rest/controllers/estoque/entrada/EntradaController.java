package br.com.tecsiscom.omapp.rest.controllers.estoque.entrada;

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
import br.com.tecsiscom.omapp.exception.EntradaNaoEncontradoException;
import br.com.tecsiscom.omapp.exception.NegocioException;
import br.com.tecsiscom.omapp.model.entity.estoque.entrada.Entrada;
import br.com.tecsiscom.omapp.model.repository.estoque.entrada.EntradaRepository;
import br.com.tecsiscom.omapp.model.service.estoque.entrada.EntradaService;


@RestController
@RequestMapping(path = "/estoques/entradas")
public class EntradaController {


		@Autowired
		EntradaService service;
		
		@Autowired
		EntradaRepository repository;
		
		@CheckSecurity.Entradas.PodeConsultar
		@GetMapping()
		public List<Entrada> listar() {
			List<Entrada> entradas = repository.findAll();
			return entradas;
		}
		
		@CheckSecurity.Entradas.PodeConsultar
		@GetMapping("/{entradaId}")
		public Optional<Entrada> buscar(@PathVariable Long entradaId) {
			Optional<Entrada> entrada = repository.findById(entradaId);
			return entrada;
		}
			
		@CheckSecurity.Entradas.PodeEditar
		@PostMapping
		@ResponseStatus(HttpStatus.CREATED)
		public Entrada salvar(@RequestBody @Valid Entrada entrada) {
			try {
				return service.salvar(entrada);
			} catch (EntradaNaoEncontradoException e) {
				throw new NegocioException(e.getMessage());
			}
		}
		
		@CheckSecurity.Entradas.PodeEditar
		@DeleteMapping("/{entradaId}")
		public ResponseEntity<Entrada> remover(@PathVariable("entradaId") Long id) {

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

