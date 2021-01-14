package br.com.tecsiscom.omapp.model.service.produtos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.tecsiscom.omapp.exception.EntidadeEmUsoException;
import br.com.tecsiscom.omapp.exception.GrupoFundamentalNaoApagavelException;
import br.com.tecsiscom.omapp.exception.GrupoNaoEncontradoException;
import br.com.tecsiscom.omapp.exception.ImpostoNaoEncontradoException;
import br.com.tecsiscom.omapp.exception.PessoaNaoEncontradaException;
import br.com.tecsiscom.omapp.model.entity.produtos.Imposto;
import br.com.tecsiscom.omapp.model.repository.produtos.ImpostoRepository;

@Service
public class ImpostoService {

	@Autowired
	ImpostoRepository impostoRepository;
	

	private static final String MSG_IMPOSTO_EM_USO 
		= "Imposto de código %d não pode ser removido, pois está em uso";

	public List<Imposto> listar() {

		List<Imposto> impostos = impostoRepository.findAll();
		return impostos;
	}

	public Imposto salvar(Imposto imposto) {
//		boolean exists = impostoRepository.existsByNome(imposto.getNome());
//		if (exists) {
//			throw new ImpostoCadastradoException(imposto.getNome());
//		}

		return impostoRepository.save(imposto);
	}

	public void remover(Long impostoId) {
		try {
			impostoRepository.deleteById(impostoId);
		} catch (EmptyResultDataAccessException e) {
			throw new PessoaNaoEncontradaException(impostoId);
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
					String.format("Imposto de código %d não pode ser removido, pois está em uso", impostoId));
		}
	}

	public Imposto buscarOuFalhar(Long impostoId) {
		return impostoRepository.findById(impostoId).orElseThrow(() -> new ImpostoNaoEncontradoException(impostoId));

	}

	@Transactional
	public void excluir(Long impostoId) {
		try {

			impostoRepository.deleteById(impostoId);
			impostoRepository.flush();

		} catch (EmptyResultDataAccessException e) {
			throw new GrupoNaoEncontradoException(impostoId);

		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format(MSG_IMPOSTO_EM_USO, impostoId));
		}
	}

}
