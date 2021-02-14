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
import br.com.tecsiscom.omapp.exception.ItemEntradaNaoEncontradoException;
import br.com.tecsiscom.omapp.exception.NegocioException;
import br.com.tecsiscom.omapp.model.entity.estoque.entrada.ItemEntrada;
import br.com.tecsiscom.omapp.model.repository.estoque.entrada.ItemEntradaRepository;
import br.com.tecsiscom.omapp.model.service.estoque.entrada.ItemEntradaService;

@RestController
@RequestMapping(path = "/estoques/itensEntradas")
public class ItemEntradaController {


		@Autowired
		ItemEntradaService service;
		
		@Autowired
		ItemEntradaRepository repository;
		
		@CheckSecurity.Entradas.PodeConsultar
		@GetMapping()
		public List<ItemEntrada> listar() {
			List<ItemEntrada> itemEntradas = repository.findAll();
			return itemEntradas;
		}
		
		@CheckSecurity.Entradas.PodeConsultar
		@GetMapping("/{itemEntradaId}")
		public Optional<ItemEntrada> buscar(@PathVariable Long itemEntradaId) {
			Optional<ItemEntrada> itemEntrada = repository.findById(itemEntradaId);
			return itemEntrada;
		}
			
		@CheckSecurity.Entradas.PodeEditar
		@PostMapping
		@ResponseStatus(HttpStatus.CREATED)
		public ItemEntrada salvar(@RequestBody @Valid ItemEntrada itemEntrada) {
			try {
				return service.salvar(itemEntrada);
			} catch (ItemEntradaNaoEncontradoException e) {
				throw new NegocioException(e.getMessage());
			}
		}
		
		@CheckSecurity.Entradas.PodeEditar
		@DeleteMapping("/{itemEntradaId}")
		public ResponseEntity<ItemEntrada> remover(@PathVariable("itemEntradaId") Long id) {

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

