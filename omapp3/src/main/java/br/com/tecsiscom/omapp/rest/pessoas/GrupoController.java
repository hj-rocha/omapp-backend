package br.com.tecsiscom.omapp.rest.pessoas;

import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.tecsiscom.omapp.model.entity.pessoas.Grupo;
import br.com.tecsiscom.omapp.model.entity.pessoas.Permissao;
import br.com.tecsiscom.omapp.model.repository.pessoas.GrupoRepository;
import br.com.tecsiscom.omapp.service.pessoas.PessoaGrupoService;

@RestController
@RequestMapping("/grupos")
public class GrupoController {

	@Autowired
	private GrupoRepository grupoRepository;
	
	@Autowired
	private PessoaGrupoService cadastroGrupo;

	//@Transactional
	@GetMapping
	public List<Grupo> listar() {
		List<Grupo> todosGrupos = grupoRepository.findAll();
//		for (Grupo grupo : todosGrupos) {
//			for (Permissao permissao : grupo.getPermissoes()) {
//				System.out.println(permissao.getNome());
//			}
//		}
		return todosGrupos;
	}
	
	@GetMapping("/{grupoId}")
	public Grupo buscar(@PathVariable Long grupoId) {
		Grupo grupo = cadastroGrupo.buscarOuFalhar(grupoId);
		
		return grupo;
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Grupo adicionar(@RequestBody @Valid Grupo grupo) {
		
		return grupo = cadastroGrupo.salvar(grupo);
	}
	
//	@PutMapping("/{grupoId}")
//	public GrupoModel atualizar(@PathVariable Long grupoId,
//			@RequestBody @Valid GrupoInput grupoInput) {
//		Grupo grupoAtual = cadastroGrupo.buscarOuFalhar(grupoId);
//		
//		grupoInputDisassembler.copyToDomainObject(grupoInput, grupoAtual);
//		
//		grupoAtual = cadastroGrupo.salvar(grupoAtual);
//		
//		return grupoModelAssembler.toModel(grupoAtual);
//	}
//	
//	@DeleteMapping("/{grupoId}")
//	@ResponseStatus(HttpStatus.NO_CONTENT)
//	public void remover(@PathVariable Long grupoId) {
//		cadastroGrupo.excluir(grupoId);	
//	}
	
}
