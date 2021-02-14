package br.com.tecsiscom.omapp.rest.controllers.produtos;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
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
import br.com.tecsiscom.omapp.exception.ProdutoNaoEncontradoException;
import br.com.tecsiscom.omapp.model.entity.produtos.Mercadoria;
import br.com.tecsiscom.omapp.model.entity.produtos.Produto;
import br.com.tecsiscom.omapp.model.repository.produtos.MercadoriaRepository;
import br.com.tecsiscom.omapp.model.repository.produtos.ProdutoRepository;
import br.com.tecsiscom.omapp.model.service.produtos.MercadoriaService;
import br.com.tecsiscom.omapp.model.service.produtos.ProdutoService;

@RestController
@RequestMapping("/mercadorias")
public class MercadoriaController {
	
	@Autowired
	MercadoriaService mercadoriaService;
	
	@Autowired
	MercadoriaRepository mercadoriaRepository;
	
	@CheckSecurity.Produtos.PodeConsultar
	@GetMapping()
	public List<Mercadoria> listar() {
		List<Mercadoria> mercadorias = mercadoriaService.listar();
		
		return mercadorias;
	}
	
	@CheckSecurity.Produtos.PodeConsultar
	@GetMapping("/{mercadoriaId}")
	public Optional<Mercadoria> buscar(@PathVariable Long mercadoriaId) {
		Optional<Mercadoria> mercadoria = mercadoriaRepository.findById(mercadoriaId);
		return mercadoria;
	}
	
/*	
	@CheckSecurity.Produtos.PodeEditar
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Produto salvar(@RequestBody @Valid Produto produto) {
		try {
			return produtoService.salvar(produto);
		} catch (ProdutoNaoEncontradoException e) {
			throw new NegocioException(e.getMessage());
		}
		
	}
	
	@CheckSecurity.Produtos.PodeEditar
	@DeleteMapping("/{produtoId}")
	public ResponseEntity<Produto> remover(@PathVariable("produtoId") Long id) {

		try {
			produtoService.remover(id);
			return ResponseEntity.noContent().build();
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.notFound().build();

		} catch (EntidadeEmUsoException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}
*/
}
