package br.com.tecsiscom.omapp.rest.pessoas;

import java.util.List;

import org.springframework.beans.BeanUtils;
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
import br.com.tecsiscom.omapp.exception.EstadoNaoEncontradoException;
import br.com.tecsiscom.omapp.exception.NegocioException;
import br.com.tecsiscom.omapp.model.entity.pessoas.Cidade;
import br.com.tecsiscom.omapp.model.repository.pessoas.CidadeRepository;
import br.com.tecsiscom.omapp.model.service.pessoas.CidadeService;

@RestController
@RequestMapping("cidades")
public class CidadeController {

	@Autowired
	private CidadeRepository cidadeRepository;

	@Autowired
	private CidadeService cadastroCidadeService;

	@CheckSecurity.Cidades.PodeConsultar
	@GetMapping
	public List<Cidade> listar() {

		return cidadeRepository.findAll();

	}

	@CheckSecurity.Cidades.PodeConsultar
	@GetMapping("/{cidadeId}")
	public Cidade buscar(@PathVariable("cidadeId") Long cidadeId) {
		return cadastroCidadeService.buscarOuFalhar(cidadeId);
	}

	@CheckSecurity.Cidades.PodeEditar
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cidade adicionar(@RequestBody Cidade cidade) {
		try {
			return cadastroCidadeService.salvar(cidade);
		} catch (EstadoNaoEncontradoException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}

	@CheckSecurity.Cidades.PodeEditar
	@PutMapping("/{cidadeId}")
	public Cidade atualizar(@PathVariable("cidadeId") Long cidadeId, @RequestBody Cidade cidade) {

		try {
			Cidade cidadeAtual = cadastroCidadeService.buscarOuFalhar(cidadeId);

			BeanUtils.copyProperties(cidade, cidadeAtual, "id");
			return cadastroCidadeService.salvar(cidadeAtual);
		} catch (EstadoNaoEncontradoException e) {
			throw new NegocioException(e.getMessage());
		}

	}

	@CheckSecurity.Cidades.PodeEditar
	@DeleteMapping("/{cidadeId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable("cidadeId") Long id) {
		cadastroCidadeService.remover(id);
	}

	/**
	@ExceptionHandler(EntidadeNaoEncontradaException.class)
	public ResponseEntity<?> tratarEntidadeNaoEncontradaException(
			EntidadeNaoEncontradaException e){
		Problema problema = Problema.builder()
				.dataHora(LocalDateTime.now())
				.mensagem(e.getMessage()).build();
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(problema);
	
	}
	
	@ExceptionHandler(NegocioException.class)
	public ResponseEntity<?> tratarNegocioException(
			NegocioException e){
		Problema problema = Problema.builder()
				.dataHora(LocalDateTime.now())
				.mensagem(e.getMessage()).build();
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(problema);
	}
	*/
}
