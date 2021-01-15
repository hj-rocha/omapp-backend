package br.com.tecsiscom.omapp.rest.controllers.produtos;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.tecsiscom.omapp.core.security.CheckSecurity;
import br.com.tecsiscom.omapp.model.entity.produtos.Marca;
import br.com.tecsiscom.omapp.model.repository.produtos.MarcaRepository;
import br.com.tecsiscom.omapp.model.service.produtos.MarcaService;

@RestController
@RequestMapping("/marcas")
public class MarcaController {

	@Autowired
	private MarcaRepository marcaRepository;

	@Autowired
	private MarcaService marcaService;


	@CheckSecurity.Produtos.PodeConsultar
	@GetMapping
	public List<Marca> listar() {
		List<Marca> todosMarcas = marcaRepository.findAll();

		return todosMarcas;
	}

	
	@CheckSecurity.Produtos.PodeConsultar
	@GetMapping("/{marcaId}")
	public Marca buscar(@PathVariable Long marcaId) {
		Marca marca = marcaService.buscarOuFalhar(marcaId);

		return marca;
	}

	@CheckSecurity.Produtos.PodeEditar
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Marca adicionar(@RequestBody @Valid Marca marca) {

		return marca = marcaService.salvar(marca);
	}

	@CheckSecurity.Produtos.PodeEditar
	@PutMapping("/{marcaId}")
	public Marca atualizar(@PathVariable Long marcaId, @RequestBody @Valid Marca marcaInput) {
		Marca marcaAtual = marcaService.buscarOuFalhar(marcaId);

		marcaAtual.setNome(marcaInput.getNome());

		marcaAtual = marcaService.salvar(marcaAtual);

		return marcaAtual;
	}

	@CheckSecurity.Produtos.PodeEditar
	@DeleteMapping("/{marcaId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long marcaId) {
		marcaService.excluir(marcaId);
	}

}
