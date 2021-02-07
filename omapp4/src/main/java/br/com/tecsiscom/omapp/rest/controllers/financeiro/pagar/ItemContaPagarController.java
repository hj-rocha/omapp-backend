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
import br.com.tecsiscom.omapp.exception.EntidadeEmUsoException;
import br.com.tecsiscom.omapp.exception.EntidadeNaoEncontradaException;
import br.com.tecsiscom.omapp.exception.ItemContaPagarNaoEncontradoException;
import br.com.tecsiscom.omapp.exception.NegocioException;
import br.com.tecsiscom.omapp.model.entity.financeiro.pagar.ItemContaPagar;
import br.com.tecsiscom.omapp.model.repository.financeiro.pagar.ItemContaPagarRepository;
import br.com.tecsiscom.omapp.model.service.financeiro.pagar.ItemContaPagarService;

@RestController
@RequestMapping(path = "/itens_conta_pagar")
public class ItemContaPagarController {


		@Autowired
		ItemContaPagarService service;
		
		@Autowired
		ItemContaPagarRepository repository;
		
		@CheckSecurity.ContasPagar.PodeConsultar
		@GetMapping()
		public List<ItemContaPagar> listar() {
			List<ItemContaPagar> itensContaPagar = repository.findAll();
			return itensContaPagar;
		}
		
		@CheckSecurity.ContasPagar.PodeConsultar
		@GetMapping("/{itemContaPagarId}")
		public Optional<ItemContaPagar> buscar(@PathVariable Long itemContaPagarId) {
			Optional<ItemContaPagar> itemContaPagar = repository.findById(itemContaPagarId);
			return itemContaPagar;
		}
			
		@CheckSecurity.ContasPagar.PodeEditar
		@PostMapping
		@ResponseStatus(HttpStatus.CREATED)
		public ItemContaPagar salvar(@RequestBody @Valid ItemContaPagar itemContaPagar) {
			try {
				return service.salvar(itemContaPagar);
			} catch (ItemContaPagarNaoEncontradoException e) {
				throw new NegocioException(e.getMessage());
			}
		}
		
		@CheckSecurity.ContasPagar.PodeEditar
		@DeleteMapping("/{itemContaPagarId}")
		public ResponseEntity<ItemContaPagar> remover(@PathVariable("itemContaPagarId") Long id) {

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


