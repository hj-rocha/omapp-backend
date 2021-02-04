package br.com.tecsiscom.omapp.model.service.pecas;

import java.sql.SQLIntegrityConstraintViolationException;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.tecsiscom.omapp.exception.CampoUnicoException;
import br.com.tecsiscom.omapp.exception.EntidadeEmUsoException;
import br.com.tecsiscom.omapp.exception.ProdutoNaoEncontradoException;
import br.com.tecsiscom.omapp.model.entity.pecas.Peca;
import br.com.tecsiscom.omapp.model.entity.produtos.Marca;
import br.com.tecsiscom.omapp.model.entity.veiculos.Veiculo;
import br.com.tecsiscom.omapp.model.repository.pecas.PecaRepository;
import br.com.tecsiscom.omapp.model.repository.veiculos.VeiculoRepository;

@Service
public class PecaService {

	@Autowired
	PecaRepository pecaRepository;
	
	
	public Peca salvar(Peca peca) {
//		if(peca.getMarca().getId()==null) {
//			Marca marca = new Marca();
//			marca.setId(1L);
//			peca.setMarca(marca);
//		}

		Peca p=null;
		try {
			p=pecaRepository.save(peca);
		} catch (Exception e) {
			if (e instanceof DataIntegrityViolationException) {
				System.out.println(e.toString());
				throw new CampoUnicoException("código interno e código de barras");
			}
		}
		return p;
	}
	
	public void remover(Long pecaId) {
		try {
			pecaRepository.deleteById(pecaId);
		} catch (EmptyResultDataAccessException e) {
			throw new ProdutoNaoEncontradoException(pecaId);
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
					String.format("Peça de código %d não pode ser removido, pois está em uso", pecaId));
		}
	}
	
}
