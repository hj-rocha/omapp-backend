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
import br.com.tecsiscom.omapp.model.entity.produtos.Imposto;
import br.com.tecsiscom.omapp.model.repository.produtos.ImpostoRepository;
import br.com.tecsiscom.omapp.model.service.produtos.ImpostoService;

@RestController
@RequestMapping("/impostos")
public class ImpostoController {

	@Autowired
	private ImpostoRepository impostoRepository;

	@Autowired
	private ImpostoService impostoService;


	@CheckSecurity.Produtos.PodeConsultar
	@GetMapping
	public List<Imposto> listar() {
		List<Imposto> todosImpostos = impostoRepository.findAll();

		return todosImpostos;
	}

	
	@CheckSecurity.Produtos.PodeConsultar
	@GetMapping("/{impostoId}")
	public Imposto buscar(@PathVariable Long impostoId) {
		Imposto imposto = impostoService.buscarOuFalhar(impostoId);

		return imposto;
	}

	@CheckSecurity.Produtos.PodeEditar
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Imposto adicionar(@RequestBody @Valid Imposto imposto) {

		return imposto = impostoService.salvar(imposto);
	}

	@CheckSecurity.Produtos.PodeEditar
	@PutMapping("/{impostoId}")
	public Imposto atualizar(@PathVariable Long impostoId, @RequestBody @Valid Imposto impostoInput) {
		Imposto impostoAtual = impostoService.buscarOuFalhar(impostoId);

		impostoAtual.setNome(impostoInput.getNome());

		impostoAtual = impostoService.salvar(impostoAtual);

		return impostoAtual;
	}

	@CheckSecurity.Produtos.PodeEditar
	@DeleteMapping("/{impostoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long impostoId) {
		impostoService.excluir(impostoId);
	}

}
