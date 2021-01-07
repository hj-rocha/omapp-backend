package br.com.tecsiscom.omapp.model.service.pessoas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.tecsiscom.omapp.exception.EntidadeEmUsoException;
import br.com.tecsiscom.omapp.exception.PessoaNaoEncontradaException;
import br.com.tecsiscom.omapp.model.entity.pessoas.Cidade;
import br.com.tecsiscom.omapp.model.entity.pessoas.Endereco;
import br.com.tecsiscom.omapp.model.entity.pessoas.Estado;
import br.com.tecsiscom.omapp.model.entity.pessoas.Grupo;
import br.com.tecsiscom.omapp.model.entity.pessoas.Pessoa;
import br.com.tecsiscom.omapp.model.repository.pessoas.PessoaRepository;

@Service
public class PessoaService {

	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private GrupoService grupoService;
	
	public Pessoa salvar(Pessoa pessoa) {
		
		if(pessoa.getEndereco().getCidade().getId() == null) {
			Cidade cidade = new Cidade();
			cidade.setId(9999L);
			Estado estado = new Estado();
			estado.setId(28L);
			cidade.setEstado(estado);
			Endereco endereco = new Endereco();
			endereco.setCidade(cidade);
			pessoa.setEndereco(endereco);
			Grupo grupo = new Grupo();
			grupo.setId(2L);
			pessoa.getGrupos().add(grupo);
			
		}
		
		return pessoaRepository.save(pessoa);
	}
	
	@Transactional
	public void desassociarGrupo(Long pessoaId, Long grupoId) {
		Pessoa pessoa = buscarOuFalhar(pessoaId);
		Grupo grupo = grupoService.buscarOuFalhar(grupoId);
		
		pessoa.removerGrupo(grupo);
		pessoaRepository.save(pessoa);
	}
	
	@Transactional
	public void associarGrupo(Long pessoaId, Long grupoId) {
		Pessoa pessoa = buscarOuFalhar(pessoaId);
		Grupo grupo = grupoService.buscarOuFalhar(grupoId);
		
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
