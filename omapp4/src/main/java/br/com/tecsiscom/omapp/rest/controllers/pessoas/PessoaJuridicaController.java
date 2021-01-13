package br.com.tecsiscom.omapp.rest.controllers.pessoas;

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

import br.com.tecsiscom.omapp.core.security.CheckSecurity;
import br.com.tecsiscom.omapp.exception.EntidadeEmUsoException;
import br.com.tecsiscom.omapp.exception.EntidadeNaoEncontradaException;
import br.com.tecsiscom.omapp.exception.NegocioException;
import br.com.tecsiscom.omapp.exception.PessoaNaoEncontradaException;
import br.com.tecsiscom.omapp.model.entity.pessoas.PessoaJuridica;
import br.com.tecsiscom.omapp.model.entity.pessoas.Usuario;
import br.com.tecsiscom.omapp.model.repository.pessoas.GrupoRepository;
import br.com.tecsiscom.omapp.model.repository.pessoas.PessoaJuridicaRepository;
import br.com.tecsiscom.omapp.model.repository.pessoas.UsuarioRepository;
import br.com.tecsiscom.omapp.model.service.pessoas.PessoaJuridicaService;

@RestController
@RequestMapping("/pessoas_juridicas")
public class PessoaJuridicaController {

	@Autowired
	PessoaJuridicaRepository pessoaRepository;

	@Autowired
	PessoaJuridicaService pessoaService;

	@Autowired
	GrupoRepository grupoRepository;

	@Autowired
	UsuarioRepository usuarioRepository;

	@CheckSecurity.Pessoas.PodeConsultar
	//@PreAuthorize("hasAuthority('LISTAR_PESSOAS')")
	@GetMapping
	public List<PessoaJuridica> listar() {
		List<PessoaJuridica> pessoas = pessoaRepository.findAll();
//		for (Pessoa pessoa : pessoas) {
//			System.out.println(pessoa.getNome());
//		}
		return pessoas;
	}

	@CheckSecurity.Pessoas.PodeConsultar
	//@PreAuthorize("hasAuthority('LISTAR_PESSOAS')")
	@Transactional
	@GetMapping("/{pessoaId}")
	public Optional<PessoaJuridica> buscar(@PathVariable Long pessoaId) {

		Optional<PessoaJuridica> pessoa = pessoaRepository.findById(pessoaId);

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

	@CheckSecurity.Pessoas.PodeEditar
	//@PreAuthorize("hasAuthority('EDITAR_PESSOAS')")
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public PessoaJuridica salvar(@RequestBody @Valid PessoaJuridica pessoa) {
		//System.out.println(pessoa);
		try {
			return pessoaService.salvar(pessoa);
			///return null;
		} catch (PessoaNaoEncontradaException e) {
			throw new NegocioException(e.getMessage());
		}
	}
	
	@CheckSecurity.Pessoas.PodeEditar
	//@PreAuthorize("hasAuthority('EDITAR_PESSOAS')")
	@DeleteMapping("/{pessoaId}")
	public ResponseEntity<PessoaJuridica> remover(@PathVariable("pessoaId") Long id) {

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

	@CheckSecurity.Pessoas.PodeEditar
	//@PreAuthorize("hasAuthority('EDITAR_PESSOAS')")
	@PutMapping("/{pessoaId}")
	public PessoaJuridica atualizar(@PathVariable("pessoaId") Long pessoaId, @RequestBody PessoaJuridica pessoa) {

		PessoaJuridica pessoaAtual = pessoaService.buscarOuFalhar(pessoaId);

		System.out.println(pessoa.getEmail());
		//System.out.println(pessoa.getEndereco().getCidade().getNome());
		System.out.println(pessoa.toString());
		//BeanUtils.copyProperties(pessoa, pessoaAtual, "id", "grupos", "dataCadastro", "usuario");

		try {
			return pessoaService.salvar(pessoa);
		} catch (PessoaNaoEncontradaException e) {
			throw new NegocioException(e.getMessage());
		}
	}
	
	@PreAuthorize("permitAll()")
	@PostMapping("/teste")
	public void teste() {
       
		PessoaJuridica pessoa = new PessoaJuridica();
        
		Usuario usuario =  new Usuario();
		
		usuario.setPassword("123");
		usuario.setUsername("clara@mail.com");
		
        
       pessoa.setNome("Ana Clara");
       pessoa.setEmail("anaclara@mail.com");
       pessoa.setUsuario(usuario); 
       
       this.pessoaRepository.save(pessoa);


	}

}
