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
import br.com.tecsiscom.omapp.exception.EntidadeEmUsoException;
import br.com.tecsiscom.omapp.exception.EntidadeNaoEncontradaException;
import br.com.tecsiscom.omapp.exception.ItemContaReceberNaoEncontradoException;
import br.com.tecsiscom.omapp.exception.NegocioException;
import br.com.tecsiscom.omapp.model.entity.financeiro.receber.ItemContaReceber;
import br.com.tecsiscom.omapp.model.repository.financeiro.receber.ItemContaReceberRepository;
import br.com.tecsiscom.omapp.model.service.financeiro.receber.ItemContaReceberService;

@RestController
@RequestMapping(path = "/itens_conta_receber")
public class ItemContaReceberController {


		@Autowired
		ItemContaReceberService service;
		
		@Autowired
		ItemContaReceberRepository repository;
		
		@CheckSecurity.ContasReceber.PodeConsultar
		@GetMapping()
		public List<ItemContaReceber> listar() {
			List<ItemContaReceber> itensContaReceber = repository.findAll();
			return itensContaReceber;
		}
		
		@CheckSecurity.ContasReceber.PodeConsultar
		@GetMapping("/{itemContaReceberId}")
		public Optional<ItemContaReceber> buscar(@PathVariable Long itemContaReceberId) {
			Optional<ItemContaReceber> itemContaReceber = repository.findById(itemContaReceberId);
			return itemContaReceber;
		}
			
		@CheckSecurity.ContasReceber.PodeEditar
		@PostMapping
		@ResponseStatus(HttpStatus.CREATED)
		public ItemContaReceber salvar(@RequestBody @Valid ItemContaReceber itemContaReceber) {
			try {
				return service.salvar(itemContaReceber);
			} catch (ItemContaReceberNaoEncontradoException e) {
				throw new NegocioException(e.getMessage());
			}
		}
		
		@CheckSecurity.ContasReceber.PodeEditar
		@DeleteMapping("/{itemContaReceberId}")
		public ResponseEntity<ItemContaReceber> remover(@PathVariable("itemContaReceberId") Long id) {

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


