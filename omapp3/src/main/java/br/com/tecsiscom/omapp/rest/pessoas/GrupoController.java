package br.com.tecsiscom.omapp.rest.pessoas;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
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
import br.com.tecsiscom.omapp.model.entity.pessoas.Grupo;
import br.com.tecsiscom.omapp.model.entity.pessoas.Permissao;
import br.com.tecsiscom.omapp.model.repository.pessoas.GrupoRepository;
import br.com.tecsiscom.omapp.model.repository.pessoas.PermissaoRepository;
import br.com.tecsiscom.omapp.model.service.pessoas.GrupoService;
import br.com.tecsiscom.omapp.model.service.pessoas.PermissaoService;
import br.com.tecsiscom.omapp.rest.model.GrupoModel;
import br.com.tecsiscom.omapp.rest.model.input.GrupoInput;
import br.com.tecsiscom.omapp.rest.model.input.PermissaoInput;

@RestController
@RequestMapping("/grupos")
public class GrupoController {

	@Autowired
	private GrupoRepository grupoRepository;

	@Autowired
	private GrupoService grupoService;

	@Autowired
	private PermissaoRepository permissaoRepository;

	@CheckSecurity.Grupos.PodeConsultar
	@GetMapping
	public List<Grupo> listar() {
		List<Grupo> todosGrupos = grupoRepository.findAll();

		return todosGrupos;
	}

	@CheckSecurity.Grupos.PodeConsultar
	@GetMapping("/{grupoId}/permissoes_input")
	public List<PermissaoInput> todasPermissoes(@PathVariable Long grupoId) {

		List<Permissao> permissoes = permissaoRepository.findAll();
		Optional<Grupo> grupo = grupoRepository.findById(grupoId);
		List<PermissaoInput> permissoesInput = new ArrayList<PermissaoInput>();


		Boolean achou = false;
		for (Permissao permissao : permissoes) {
			achou = false;
			for (Permissao permissao2 : grupo.get().getPermissoes()) {
				if (permissao.getId() == permissao2.getId()) {
					achou = true;
				}
			}
			PermissaoInput permissaoInput = new PermissaoInput();
			permissaoInput.setId(permissao.getId());
			permissaoInput.setGrupoNome(grupo.get().getNome());
			permissaoInput.setNome(permissao.getNome());
			permissaoInput.setDescricao(permissao.getDescricao());
			permissaoInput.setPertence(achou);
			permissoesInput.add(permissaoInput);

		}
		return permissoesInput;
	}

	@CheckSecurity.Grupos.PodeConsultar
	@GetMapping("/{grupoId}")
	public Grupo buscar(@PathVariable Long grupoId) {
		Grupo grupo = grupoService.buscarOuFalhar(grupoId);

		return grupo;
	}

	@CheckSecurity.Grupos.PodeEditar
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Grupo adicionar(@RequestBody @Valid Grupo grupo) {

		return grupo = grupoService.salvar(grupo);
	}

	@CheckSecurity.Grupos.PodeEditar
	@PutMapping("/{grupoId}")
	public Grupo atualizar(@PathVariable Long grupoId, @RequestBody @Valid GrupoInput grupoInput) {
		Grupo grupoAtual = grupoService.buscarOuFalhar(grupoId);

		grupoAtual.setNome(grupoInput.getNome());

		grupoAtual = grupoService.salvar(grupoAtual);

		return grupoAtual;
	}

	@CheckSecurity.Grupos.PodeEditar
	@DeleteMapping("/{grupoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long grupoId) {
		grupoService.excluir(grupoId);
	}

}
