package br.com.tecsiscom.omapp.model.service.manutencoes;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.tecsiscom.omapp.exception.EntidadeEmUsoException;
import br.com.tecsiscom.omapp.exception.ManutencaoConsolidadaException;
import br.com.tecsiscom.omapp.exception.ManutencaoNaoEncontradaException;
import br.com.tecsiscom.omapp.exception.NegocioException;
import br.com.tecsiscom.omapp.exception.ProdutoNaoEncontradoException;
import br.com.tecsiscom.omapp.model.entity.manutencoes.Manutencao;
import br.com.tecsiscom.omapp.model.repository.manutencoes.ManutencaoRepository;

@Service
public class ManutencaoService {

	@Autowired
	ManutencaoRepository manutencaoRepository;

	public List<Manutencao> listar() {

		List<Manutencao> manutencoes = manutencaoRepository.findAll();
		return manutencoes;
	}

	public Manutencao salvar(Manutencao manutencao) {
//		boolean exists = manutencaoRepository.existsByNome(manutencao.getNome());
//		if (exists) {
//			throw new ManutencaoCadastradoException(manutencao.getNome());
//		}
		
		Manutencao m;
		try {
			m= manutencaoRepository.save(manutencao);			
		} catch (Exception e) {
			throw new NegocioException("Deve haver uma placa e uma pessoa previamente cadastradas no sistema.");
		}



		return m;
	}

	public void remover(Long manutencaoId) {

		Manutencao manutencao = this.buscarOuFalhar(manutencaoId);
		Boolean naoTemDespesa = manutencao.getDespesas().size() == 0;
		Boolean naoFoiFechada = manutencao.isAtiva();

		if (!naoTemDespesa || !naoFoiFechada) {

			throw new ManutencaoConsolidadaException(
					"A manutenção "+manutencaoId+ " não pode ser "
							+ "apagada, ela já foi fechada ou tem despesas associadas.");
		}
			
			try {
				manutencaoRepository.deleteById(manutencaoId);
			} catch (EmptyResultDataAccessException e) {
				throw new ProdutoNaoEncontradoException(manutencaoId);
			} catch (DataIntegrityViolationException e) {
				throw new EntidadeEmUsoException(
						String.format("Manutencao de código %d não pode ser removido, pois está em uso", manutencaoId));
			}

	}

	public Manutencao buscarOuFalhar(Long manutencaoId) {
		return manutencaoRepository.findById(manutencaoId)
				.orElseThrow(() -> new ManutencaoNaoEncontradaException(manutencaoId));

	}

	public Manutencao alterarStatusManutencao(Long id, Boolean status) {

		Manutencao manutencao = buscarOuFalhar(id);
		manutencao.setAtiva(status);
		if(status) {
			manutencao.setDataSaida(null);
		} else {
			manutencao.setDataSaida(LocalDateTime.now());
		}
		return manutencaoRepository.save(manutencao);
	}


}
