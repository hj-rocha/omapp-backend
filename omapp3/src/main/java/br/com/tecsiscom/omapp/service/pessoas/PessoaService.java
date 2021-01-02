package br.com.tecsiscom.omapp.service.pessoas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.tecsiscom.omapp.exception.EntidadeEmUsoException;
import br.com.tecsiscom.omapp.exception.PessoaNaoEncontradaException;
import br.com.tecsiscom.omapp.model.entity.pessoas.Grupo;
import br.com.tecsiscom.omapp.model.entity.pessoas.Pessoa;
import br.com.tecsiscom.omapp.model.repository.pessoas.PessoaRepository;

@Service
public class PessoaService {

	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private PessoaGrupoService cadastroGrupo;
	
	public Pessoa salvar(Pessoa pessoa) {
		
		return pessoaRepository.save(pessoa);
	}
	
	@Transactional
	public void desassociarGrupo(Long pessoaId, Long grupoId) {
		Pessoa pessoa = buscarOuFalhar(pessoaId);
		Grupo grupo = cadastroGrupo.buscarOuFalhar(grupoId);
		
		pessoa.removerGrupo(grupo);
		pessoaRepository.save(pessoa);
	}
	
	@Transactional
	public void associarGrupo(Long pessoaId, Long grupoId) {
		Pessoa pessoa = buscarOuFalhar(pessoaId);
		Grupo grupo = cadastroGrupo.buscarOuFalhar(grupoId);
		
		pessoa.adicionarGrupo(grupo);
		
		pessoaRepository.save(pessoa);
	}
	
	public Pessoa buscarOuFalhar(Long pessoaId) {
		return pessoaRepository.findById(pessoaId)
				.orElseThrow(() -> new PessoaNaoEncontradaException(pessoaId));
		
	}
	
	public void remover(Long pessoaId) {
		try {
			pessoaRepository.deleteById(pessoaId);
		} catch (EmptyResultDataAccessException e) {
			throw new PessoaNaoEncontradaException(pessoaId);
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
					String.format("Pessoa de código %d não pode ser removida, pois está em uso", pessoaId));
		}
	}
}
