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

import br.com.tecsiscom.omapp.core.security.CheckSecurity;
import br.com.tecsiscom.omapp.core.security.CheckSecurity.PessoasGrupos;
import br.com.tecsiscom.omapp.model.entity.pessoas.Grupo;
import br.com.tecsiscom.omapp.model.entity.pessoas.Pessoa;
import br.com.tecsiscom.omapp.model.repository.pessoas.PessoaRepository;
import br.com.tecsiscom.omapp.model.service.pessoas.PessoaService;

@RestController
@RequestMapping(path = "/pessoas/{pessoaId}/grupos")
public class PessoaGrupoController {

	@Autowired
	private PessoaService pessoaService;

	@Autowired
	PessoaRepository pessoaRepository;

	@CheckSecurity.PessoasGrupos.PodeConsultar
	@GetMapping
	public Set<Grupo> listar(@PathVariable Long pessoaId) {
		Pessoa pessoa = pessoaService.buscarOuFalhar(pessoaId);
		Set<Grupo> grupos = pessoa.getGrupos();

		return grupos;
	}

	@CheckSecurity.PessoasGrupos.PodeEditar
	@DeleteMapping("/{grupoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> desassociar(@PathVariable Long pessoaId, @PathVariable Long grupoId) {
		pessoaService.desassociarGrupo(pessoaId, grupoId);

		return ResponseEntity.noContent().build();
	}

	@CheckSecurity.PessoasGrupos.PodeEditar
	@PutMapping("/{grupoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> associar(@PathVariable Long pessoaId, @PathVariable Long grupoId) {
		pessoaService.associarGrupo(pessoaId, grupoId);

		return ResponseEntity.noContent().build();
	}

}
