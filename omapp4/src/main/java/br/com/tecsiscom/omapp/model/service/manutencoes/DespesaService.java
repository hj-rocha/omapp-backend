
package br.com.tecsiscom.omapp.model.service.manutencoes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.tecsiscom.omapp.exception.EntidadeEmUsoException;
import br.com.tecsiscom.omapp.exception.ProdutoNaoEncontradoException;
import br.com.tecsiscom.omapp.model.entity.manutencoes.Despesa;
import br.com.tecsiscom.omapp.model.repository.manutencoes.DespesaRepository;

@Service
public class DespesaService {

	@Autowired
	DespesaRepository despesaRepository;
	
	public Despesa salvar(Despesa despesa) {
		
		return despesaRepository.save(despesa);
	}
	
	public void remover(Long despesaId) {
		try {
			despesaRepository.deleteById(despesaId);
		} catch (EmptyResultDataAccessException e) {
			throw new ProdutoNaoEncontradoException(despesaId);
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
					String.format("Despesa de código %d não pode ser removida, pois está em uso", despesaId));
		}
	}
}
