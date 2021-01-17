package br.com.tecsiscom.omapp.model.service.manutencoes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.tecsiscom.omapp.exception.EntidadeEmUsoException;
import br.com.tecsiscom.omapp.exception.ProdutoNaoEncontradoException;
import br.com.tecsiscom.omapp.model.entity.manutencoes.PecaUtilizada;
import br.com.tecsiscom.omapp.model.repository.manutencoes.PecaUtilizadaRepository;

@Service
public class PecaUtilizadaService {
	
	@Autowired
	PecaUtilizadaRepository pecaUtilizadaRepository;
	

	public PecaUtilizada salvar(PecaUtilizada pecaUtilizada) {
		
		return pecaUtilizadaRepository.save(pecaUtilizada);
	}
	
	public void remover(Long pecaUtilizadaId) {
		try {
			pecaUtilizadaRepository.deleteById(pecaUtilizadaId);
		} catch (EmptyResultDataAccessException e) {
			throw new ProdutoNaoEncontradoException(pecaUtilizadaId);
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
					String.format("Peça utilizada de código %d não pode ser removida, pois está em uso", pecaUtilizadaId));
		}
	}
}
