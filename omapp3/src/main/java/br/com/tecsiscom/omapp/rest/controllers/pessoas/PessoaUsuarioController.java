package br.com.tecsiscom.omapp.rest.controllers.pessoas;

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

import br.com.tecsiscom.omapp.model.entity.pessoas.Grupo;
import br.com.tecsiscom.omapp.model.entity.pessoas.Pessoa;
import br.com.tecsiscom.omapp.model.entity.pessoas.Usuario;
import br.com.tecsiscom.omapp.model.repository.pessoas.PessoaRepository;
import br.com.tecsiscom.omapp.model.service.pessoas.PessoaService;
import br.com.tecsiscom.omapp.model.service.pessoas.UsuarioService;

@RestController
@RequestMapping(path = "/pessoas/{pessoaId}/usuarios")
public class PessoaUsuarioController {

	@Autowired
	private PessoaService pessoaService;
	
	@Autowired
	PessoaRepository pessoaRepository;
	
	@Autowired
	UsuarioService usuarioService;
	
	@GetMapping
	public Usuario listar(@PathVariable Long pessoaId) {
		Pessoa pessoa = pessoaService.buscarOuFalhar(pessoaId);
		Usuario usuario = pessoa.getUsuario();
		
		
		return usuario;
	}
	

	@DeleteMapping("/{usuarioId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> desassociar(@PathVariable Long pessoaId, @PathVariable Long usuarioId) {

	Pessoa pessoa = pessoaService.buscarOuFalhar(pessoaId);
//		Usuario usuario  = usuarioService.buscarOuFalhar(usuarioId);
		
		pessoa.setUsuario(null);
		
		pessoaRepository.save(pessoa);
		
		return ResponseEntity.noContent().build();
	}
	
	
	@PutMapping("/{usuarioId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> associar(@PathVariable Long pessoaId, @PathVariable Long usuarioId) {
		//pessoaService.associarGrupo(pessoaId, usuarioId);
		Pessoa pessoa = pessoaService.buscarOuFalhar(pessoaId);
		Usuario usuario  = usuarioService.buscarOuFalhar(usuarioId);
		
		pessoa.setUsuario(usuario);
		
		pessoaRepository.save(pessoa);
		return ResponseEntity.noContent().build();
	}

}
