
package br.com.tecsiscom.omapp.model.service.manutencoes;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import br.com.tecsiscom.omapp.exception.ManutencaoConsolidadaException;
import br.com.tecsiscom.omapp.model.entity.manutencoes.Despesa;
import br.com.tecsiscom.omapp.model.repository.manutencoes.DespesaRepository;
import br.com.tecsiscom.omapp.model.repository.manutencoes.ManutencaoRepository;

@Primary
@Service
public class DespesaService {

	@Autowired
	DespesaRepository despesaRepository;
	
	@Autowired
	ManutencaoRepository manutencaoRepository;
	
	public Despesa salvar(Despesa despesa) {
		
		return despesaRepository.save(despesa);
	}
	
	public void remover(Long despesaId) {
		
		Optional<Despesa> p = despesaRepository.findById(despesaId);
		
		if ( p.isPresent()) {
			if(!p.get().getManutencao().isAtiva()) {
				throw new ManutencaoConsolidadaException("Não é possível apagar despesas da manutenção fechada.") ;
			}
		
		}
//		try {
//			despesaRepository.deleteById(despesaId);
//		} catch (EmptyResultDataAccessException e) {
//			throw new ProdutoNaoEncontradoException(despesaId);
//		} catch (DataIntegrityViolationException e) {
//			throw new EntidadeEmUsoException(
//					String.format("Despesa de código %d não pode ser removida, pois está em uso", despesaId));
//		}
	}
}
