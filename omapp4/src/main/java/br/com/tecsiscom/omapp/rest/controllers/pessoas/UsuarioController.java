package br.com.tecsiscom.omapp.rest.controllers.pessoas;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.tecsiscom.omapp.core.security.CheckSecurity;
import br.com.tecsiscom.omapp.exception.UsuarioCadastradoException;
import br.com.tecsiscom.omapp.model.entity.pessoas.Usuario;
import br.com.tecsiscom.omapp.model.repository.pessoas.UsuarioRepository;
import br.com.tecsiscom.omapp.model.service.pessoas.UsuarioService;
import br.com.tecsiscom.omapp.rest.model.UsuarioModel;
import br.com.tecsiscom.omapp.rest.model.input.SenhaInput;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

	@Autowired
	UsuarioRepository repository;

	private final UsuarioService service;

	@PostMapping
	@PreAuthorize("permitAll()")
	//@CheckSecurity.Pessoas.PodeEditar
	@ResponseStatus(HttpStatus.CREATED)
	public void salvar(@RequestBody @Valid Usuario usuario) {

		try {
			service.salvar(usuario);
		} catch (UsuarioCadastradoException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}

	}

	@CheckSecurity.PessoasGrupos.PodeAlterarPropriaSenha
	@PutMapping("/{usuarioId}/senha")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void alterarSenha(@PathVariable Long usuarioId, @RequestBody @Valid SenhaInput senha) {
		service.alterarSenha(usuarioId, senha.getSenhaAtual(), senha.getNovaSenha());
	}

	@CheckSecurity.PessoasGrupos.PodeConsultar
	@GetMapping
	public List<UsuarioModel> listar() {
		List<UsuarioModel> usuarios = new ArrayList<UsuarioModel>();
		
		for (Usuario usuario : repository.findAll()) {
		UsuarioModel usuarioModel= new UsuarioModel();
		usuarioModel.setId(usuario.getId());
		usuarioModel.setUsername(usuario.getUsername());
		usuarios.add(usuarioModel);
		}
		
		return usuarios;
	}

	@CheckSecurity.PessoasGrupos.PodeConsultar
	@GetMapping("/{usuarioId}")
	public UsuarioModel buscar(@PathVariable Long usuarioId) {
		Usuario usuario = service.buscarOuFalhar(usuarioId);
		UsuarioModel usuarioModel = new UsuarioModel();
		usuarioModel.setId(usuario.getId());
		usuarioModel.setUsername(usuario.getUsername());

		return usuarioModel;
	}
}
