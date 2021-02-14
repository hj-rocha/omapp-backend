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
import br.com.tecsiscom.omapp.exception.ItemSaidaNaoEncontradoException;
import br.com.tecsiscom.omapp.exception.NegocioException;
import br.com.tecsiscom.omapp.model.entity.estoque.saida.ItemSaida;
import br.com.tecsiscom.omapp.model.repository.estoque.saida.ItemSaidaRepository;
import br.com.tecsiscom.omapp.model.service.estoque.saida.ItemSaidaService;


@RestController
@RequestMapping(path = "/estoques/itensSaidas")
public class ItemSaidaController {


		@Autowired
		ItemSaidaService service;
		
		@Autowired
		ItemSaidaRepository repository;
		
		@CheckSecurity.Saidas.PodeConsultar
		@GetMapping()
		public List<ItemSaida> listar() {
			List<ItemSaida> itemSaidas = repository.findAll();
			return itemSaidas;
		}
		
		@CheckSecurity.Saidas.PodeConsultar
		@GetMapping("/{itemSaidaId}")
		public Optional<ItemSaida> buscar(@PathVariable Long itemSaidaId) {
			Optional<ItemSaida> itemSaida = repository.findById(itemSaidaId);
			return itemSaida;
		}
			
		@CheckSecurity.Saidas.PodeEditar
		@PostMapping
		@ResponseStatus(HttpStatus.CREATED)
		public ItemSaida salvar(@RequestBody @Valid ItemSaida itemSaida) {
			try {
				return service.salvar(itemSaida);
			} catch (ItemSaidaNaoEncontradoException e) {
				throw new NegocioException(e.getMessage());
			}
		}
		
		@CheckSecurity.Saidas.PodeEditar
		@DeleteMapping("/{itemSaidaId}")
		public ResponseEntity<ItemSaida> remover(@PathVariable("itemSaidaId") Long id) {

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


