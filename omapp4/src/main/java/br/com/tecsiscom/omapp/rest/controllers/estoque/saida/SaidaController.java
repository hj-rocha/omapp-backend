package br.com.tecsiscom.omapp.rest.controllers.estoque.saida;

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
import br.com.tecsiscom.omapp.exception.SaidaNaoEncontradoException;
import br.com.tecsiscom.omapp.exception.NegocioException;
import br.com.tecsiscom.omapp.model.entity.estoque.saida.Saida;
import br.com.tecsiscom.omapp.model.repository.estoque.saida.SaidaRepository;
import br.com.tecsiscom.omapp.model.service.estoque.saida.SaidaService;

@RestController
@RequestMapping(path = "/estoques/saidas")
public class SaidaController {


		@Autowired
		SaidaService service;
		
		@Autowired
		SaidaRepository repository;
		
		@CheckSecurity.Saidas.PodeConsultar
		@GetMapping()
		public List<Saida> listar() {
			List<Saida> saidas = repository.findAll();
			return saidas;
		}
		
		@CheckSecurity.Saidas.PodeConsultar
		@GetMapping("/{saidaId}")
		public Optional<Saida> buscar(@PathVariable Long saidaId) {
			Optional<Saida> saida = repository.findById(saidaId);
			return saida;
		}
			
		@CheckSecurity.Saidas.PodeEditar
		@PostMapping
		@ResponseStatus(HttpStatus.CREATED)
		public Saida salvar(@RequestBody @Valid Saida saida) {
			try {
				return service.salvar(saida);
			} catch (SaidaNaoEncontradoException e) {
				throw new NegocioException(e.getMessage());
			}
		}
		
		@CheckSecurity.Saidas.PodeEditar
		@DeleteMapping("/{saidaId}")
		public ResponseEntity<Saida> remover(@PathVariable("saidaId") Long id) {

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


