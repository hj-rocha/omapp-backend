package br.com.tecsiscom.omapp.rest.controllers.produtos;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.tecsiscom.omapp.core.security.CheckSecurity;
import br.com.tecsiscom.omapp.model.entity.produtos.Imposto;
import br.com.tecsiscom.omapp.model.entity.produtos.Produto;
import br.com.tecsiscom.omapp.model.repository.produtos.ProdutoRepository;
import br.com.tecsiscom.omapp.model.service.produtos.ProdutoService;

@RestController
@RequestMapping(path = "/produtos/{produtoId}/impostos")
public class ProdutoImpostoController {

	@Autowired
	private ProdutoService produtoService;

	@Autowired
	ProdutoRepository produtoRepository;

	@CheckSecurity.Produtos.PodeConsultar
	@GetMapping
	public Set<Imposto> listar(@PathVariable Long produtoId) {
		Produto produto = produtoService.buscarOuFalhar(produtoId);
		Set<Imposto> impostos = produto.getImpostos();

		return impostos;
	}

	@CheckSecurity.Produtos.PodeEditar
	@DeleteMapping("/{impostoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> desassociar(@PathVariable Long produtoId, @PathVariable Long impostoId) {
		produtoService.desassociarImposto(produtoId, impostoId);

		return ResponseEntity.noContent().build();
	}

	@CheckSecurity.Produtos.PodeEditar
	@PutMapping("/{impostoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> associar(@PathVariable Long produtoId, @PathVariable Long impostoId) {
		produtoService.associarImposto(produtoId, impostoId);

		return ResponseEntity.noContent().build();
	}

}
