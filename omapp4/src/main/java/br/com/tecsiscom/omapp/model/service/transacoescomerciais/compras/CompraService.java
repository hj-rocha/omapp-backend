package br.com.tecsiscom.omapp.model.service.transacoescomerciais.compras;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.tecsiscom.omapp.exception.CompraNaoEncontradoException;
import br.com.tecsiscom.omapp.model.entity.transacoescomerciais.compras.Compra;
import br.com.tecsiscom.omapp.model.repository.transacoescomerciais.compras.CompraRepository;

@Service
public class CompraService {

	@Autowired
	private CompraRepository repository;
	
	public Compra salvar(Compra compra) {
		return repository.save(compra);
	}
	
	public void remover(Long id) {
		repository.deleteById(id);
	}
	
	public Compra buscarOuFalhar(Long compraId) {
		return repository.findById(compraId)
				.orElseThrow(() -> new CompraNaoEncontradoException(compraId));
		
	}
}
