package br.com.tecsiscom.omapp.model.service.veiculos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.tecsiscom.omapp.exception.EntidadeEmUsoException;
import br.com.tecsiscom.omapp.exception.ProdutoNaoEncontradoException;
import br.com.tecsiscom.omapp.model.entity.produtos.Marca;
import br.com.tecsiscom.omapp.model.entity.veiculos.Veiculo;
import br.com.tecsiscom.omapp.model.repository.veiculos.VeiculoRepository;

@Service
public class VeiculoService {

	@Autowired
	VeiculoRepository veiculoRepository;
	
	
	public Veiculo salvar(Veiculo veiculo) {

//		if(veiculo.getMarca().getId()==null) {
//			Marca marca = new Marca();
//			marca.setId(1L);
//			veiculo.setMarca(marca);
//		}
		
		return veiculoRepository.save(veiculo);
	}
	
	public void remover(Long veiculoId) {
		try {
			veiculoRepository.deleteById(veiculoId);
		} catch (EmptyResultDataAccessException e) {
			throw new ProdutoNaoEncontradoException(veiculoId);
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
					String.format("Veículo de código %d não pode ser removido, pois está em uso", veiculoId));
		}
	}
	
}
