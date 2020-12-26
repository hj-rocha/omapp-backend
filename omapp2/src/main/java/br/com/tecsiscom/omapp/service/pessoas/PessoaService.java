package br.com.tecsiscom.omapp.service.pessoas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.tecsiscom.omapp.exception.PessoaNaoEncontradaException;
import br.com.tecsiscom.omapp.model.entity.pessoas.Grupo;
import br.com.tecsiscom.omapp.model.entity.pessoas.Pessoa;
import br.com.tecsiscom.omapp.model.entity.pessoas.Usuario;
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
}
