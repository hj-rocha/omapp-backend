package br.com.tecsiscom.omapp.model.service.manutencoes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.tecsiscom.omapp.exception.EntidadeEmUsoException;
import br.com.tecsiscom.omapp.exception.ProdutoNaoEncontradoException;
import br.com.tecsiscom.omapp.model.entity.manutencoes.OutraDespesa;
import br.com.tecsiscom.omapp.model.repository.manutencoes.OutraDespesaRepository;

@Service
public class OutraDespesaService extends DespesaService{

	@Autowired
	OutraDespesaRepository outraDespesaRepository;
	
	public OutraDespesa salvar(OutraDespesa outraDespesa) {
		
		return outraDespesaRepository.save(outraDespesa);
	}
	
	public void remover(Long outraDespesaId) {
		
		super.remover(outraDespesaId);
		
		try {
			outraDespesaRepository.deleteById(outraDespesaId);
		} catch (EmptyResultDataAccessException e) {
			throw new ProdutoNaoEncontradoException(outraDespesaId);
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
					String.format("Outra Despesa de código %d não pode ser removida, pois está em uso", outraDespesaId));
		}
	}
	
}
