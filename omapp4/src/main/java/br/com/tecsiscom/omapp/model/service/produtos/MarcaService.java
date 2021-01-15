package br.com.tecsiscom.omapp.model.service.produtos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.tecsiscom.omapp.exception.EntidadeEmUsoException;
import br.com.tecsiscom.omapp.exception.GrupoNaoEncontradoException;
import br.com.tecsiscom.omapp.exception.MarcaNaoEncontradaException;
import br.com.tecsiscom.omapp.exception.PessoaNaoEncontradaException;
import br.com.tecsiscom.omapp.model.entity.produtos.Marca;
import br.com.tecsiscom.omapp.model.repository.produtos.MarcaRepository;

@Service
public class MarcaService {

	@Autowired
	MarcaRepository marcaRepository;
	

	private static final String MSG_IMPOSTO_EM_USO 
		= "Marca de código %d não pode ser removido, pois está em uso";

	public List<Marca> listar() {

		List<Marca> marcas = marcaRepository.findAll();
		return marcas;
	}

	public Marca salvar(Marca marca) {
//		boolean exists = marcaRepository.existsByNome(marca.getNome());
//		if (exists) {
//			throw new MarcaCadastradoException(marca.getNome());
//		}

		return marcaRepository.save(marca);
	}

	public void remover(Long marcaId) {
		try {
			marcaRepository.deleteById(marcaId);
		} catch (EmptyResultDataAccessException e) {
			throw new PessoaNaoEncontradaException(marcaId);
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
					String.format("Marca de código %d não pode ser removido, pois está em uso", marcaId));
		}
	}

	public Marca buscarOuFalhar(Long marcaId) {
		return marcaRepository.findById(marcaId).orElseThrow(() -> new MarcaNaoEncontradaException(marcaId));

	}

	@Transactional
	public void excluir(Long marcaId) {
		try {

			marcaRepository.deleteById(marcaId);
			marcaRepository.flush();

		} catch (EmptyResultDataAccessException e) {
			throw new GrupoNaoEncontradoException(marcaId);

		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format(MSG_IMPOSTO_EM_USO, marcaId));
		}
	}

}
