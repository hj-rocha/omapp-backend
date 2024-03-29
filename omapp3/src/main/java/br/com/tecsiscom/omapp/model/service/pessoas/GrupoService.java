package br.com.tecsiscom.omapp.model.service.pessoas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.tecsiscom.omapp.exception.EntidadeEmUsoException;
import br.com.tecsiscom.omapp.exception.GrupoCadastradoException;
import br.com.tecsiscom.omapp.exception.GrupoNaoEncontradoException;
import br.com.tecsiscom.omapp.exception.NegocioException;
import br.com.tecsiscom.omapp.exception.GrupoFundamentalNaoApagavelException;
import br.com.tecsiscom.omapp.model.entity.pessoas.Grupo;
import br.com.tecsiscom.omapp.model.entity.pessoas.Permissao;
import br.com.tecsiscom.omapp.model.repository.pessoas.GrupoRepository;

@Service
public class GrupoService {

	private static final String MSG_GRUPO_EM_USO 
		= "Grupo de código %d não pode ser removido, pois está em uso";
	
	@Autowired
	private GrupoRepository grupoRepository;
	
	@Autowired
	private PermissaoService cadastroPermissao;
	
//	@Transactional
//	public Grupo salvar(Grupo grupo) {
//		return grupoRepository.save(grupo);
//	}

	@Transactional
	public Grupo salvar(Grupo grupo) {
		boolean exists = grupoRepository.existsByNome(grupo.getNome());
		if (exists) {
			throw new GrupoCadastradoException(grupo.getNome());
		}
		return grupoRepository.save(grupo);
	}
	
	@Transactional
	public void excluir(Long grupoId) {
		
		//TODO: Remover o hardcode 
		if(grupoId<=6) {
			throw new GrupoFundamentalNaoApagavelException("Esse é um grupo fundamental , não pode ser apagado.");
		}
		
		
		
		try {
			
			grupoRepository.deleteById(grupoId);
			grupoRepository.flush();
			
			
		} catch (EmptyResultDataAccessException e) {
			throw new GrupoNaoEncontradoException(grupoId);
		
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
				String.format(MSG_GRUPO_EM_USO, grupoId));
		}
	}

	@Transactional
	public void desassociarPermissao(Long grupoId, Long permissaoId) {
		
		if(grupoId==1) {
			throw new NegocioException("O grupo gestor não pode ter suas permissões desassociadas."); 
		}
		Grupo grupo = buscarOuFalhar(grupoId);
		Permissao permissao = cadastroPermissao.buscarOuFalhar(permissaoId);
		
		grupo.removerPermissao(permissao);
	}
	
	@Transactional
	public void associarPermissao(Long grupoId, Long permissaoId) {
		Grupo grupo = buscarOuFalhar(grupoId);
		Permissao permissao = cadastroPermissao.buscarOuFalhar(permissaoId);
		
		grupo.adicionarPermissao(permissao);
	}
	
	public Grupo buscarOuFalhar(Long grupoId) {
		return grupoRepository.findById(grupoId)
			.orElseThrow(() -> new GrupoNaoEncontradoException(grupoId));
	}
	
}
