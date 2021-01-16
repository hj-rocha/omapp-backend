package br.com.tecsiscom.omapp.model.service.servicos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.tecsiscom.omapp.exception.EntidadeEmUsoException;
import br.com.tecsiscom.omapp.exception.PessoaNaoEncontradaException;
import br.com.tecsiscom.omapp.exception.ServicoNaoEncontradoException;
import br.com.tecsiscom.omapp.model.entity.servicos.Servico;
import br.com.tecsiscom.omapp.model.repository.servicos.ServicoRepository;


@Service
public class ServicoService {

	@Autowired
	ServicoRepository servicoRepository;
	
	public List<Servico> listar(){

		List<Servico> servicos =  servicoRepository.findAll();
		return servicos;
	}
	
	
	public Servico salvar(Servico servico) {
//		boolean exists = servicoRepository.existsByNome(servico.getNome());
//		if (exists) {
//			throw new ServicoCadastradoException(servico.getNome());
//		}
		
		return servicoRepository.save(servico);
	}
	
	public void remover(Long servicoId) {
		try {
			servicoRepository.deleteById(servicoId);
		} catch (EmptyResultDataAccessException e) {
			throw new ServicoNaoEncontradoException(servicoId);
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
					String.format("Servico de código %d não pode ser removido, pois está em uso", servicoId));
		}
	}

}
