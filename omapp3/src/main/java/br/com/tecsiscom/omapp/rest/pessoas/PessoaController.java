package br.com.tecsiscom.omapp.rest.pessoas;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.tecsiscom.omapp.exception.EntidadeEmUsoException;
import br.com.tecsiscom.omapp.exception.EntidadeNaoEncontradaException;
import br.com.tecsiscom.omapp.exception.NegocioException;
import br.com.tecsiscom.omapp.exception.PessoaNaoEncontradaException;
import br.com.tecsiscom.omapp.model.entity.pessoas.Pessoa;
import br.com.tecsiscom.omapp.model.entity.pessoas.Usuario;
import br.com.tecsiscom.omapp.model.repository.pessoas.GrupoRepository;
import br.com.tecsiscom.omapp.model.repository.pessoas.PessoaRepository;
import br.com.tecsiscom.omapp.model.repository.pessoas.UsuarioRepository;
import br.com.tecsiscom.omapp.service.pessoas.PessoaService;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {

	@Autowired
	PessoaRepository pessoaRepository;

	@Autowired
	PessoaService pessoaService;

	@Autowired
	GrupoRepository grupoRepository;

	@Autowired
	UsuarioRepository usuarioRepository;

	@PreAuthorize("hasAuthority('EDITAR_PESSOAS')")
	@GetMapping
	public List<Pessoa> listar() {
		List<Pessoa> pessoas = pessoaRepository.findAll();
		return pessoas;
	}

	@Transactional
	@GetMapping("/{pessoaId}")
	public Optional<Pessoa> buscar(@PathVariable Long pessoaId) {

		Optional<Pessoa> pessoa = pessoaRepository.findById(pessoaId);

//		System.out.println(pessoa.get().getNome());
//
//		for (Grupo grupo : pessoa.get().getGrupos()) {
//			System.out.println(grupo.getNome());
//			for (Permissao permissao : grupo.getPermissoes()) {
//				System.out.println(permissao.getNome());
//			}
//
//		}

//		List<Grupo> grupos = new ArrayList<Grupo>();
//		grupos = grupoRepository.findAll();
//		for (Grupo grupo : grupos) {
//			System.out.println(grupo.getNome());
//		}
//		
//		Optional<Grupo> grupo = grupoRepository.findById(1L);
//		System.out.println(grupo.get().getNome());
//		List<Permissao> permissaos =grupo.get().getPermissoes();
//		for (Permissao permissao : permissaos) {
//			System.out.println(permissao.getNome());
//		}

		return pessoa;
		// return pessoaRepository.findById(pessoaId);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Pessoa salvar(@RequestBody @Valid Pessoa pessoa) {
		try {
			return pessoaService.salvar(pessoa);
		} catch (PessoaNaoEncontradaException e) {
			throw new NegocioException(e.getMessage());
		}
	}

	@DeleteMapping("/{pessoaId}")
	public ResponseEntity<Pessoa> remover(@PathVariable("pessoaId") Long id) {

		try {
			pessoaService.remover(id);
			return ResponseEntity.noContent().build();
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.notFound().build();

		} catch (EntidadeEmUsoException e) {
			// Se houver o problema com a chave estrangeira entre restaurante e produto
			// lança essa esceção e devolve o código http 409
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}

	@PutMapping("/{pessoaId}")
	public Pessoa atualizar(@PathVariable("pessoaId") Long pessoaId, @RequestBody Pessoa pessoa) {

		Pessoa pessoaAtual = pessoaService.buscarOuFalhar(pessoaId);

		try {
			return pessoaService.salvar(pessoa);
		} catch (PessoaNaoEncontradaException e) {
			throw new NegocioException(e.getMessage());
		}
	}

}
