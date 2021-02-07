package br.com.tecsiscom.omapp.model.service.transacoescomerciais.compras;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.tecsiscom.omapp.exception.ItemCompraNaoEncontradoException;
import br.com.tecsiscom.omapp.model.entity.transacoescomerciais.compras.ItemCompra;
import br.com.tecsiscom.omapp.model.repository.transacoescomerciais.compras.ItemCompraRepository;

@Service
public class ItemCompraService {

	@Autowired
	private ItemCompraRepository repository;
	
	public ItemCompra salvar(ItemCompra itemCompra) {
		return repository.save(itemCompra);
	}
	
	public void remover(Long id) {
		repository.deleteById(id);
	}
	
	public ItemCompra buscarOuFalhar(Long itemCompraId) {
		return repository.findById(itemCompraId)
				.orElseThrow(() -> new ItemCompraNaoEncontradoException(itemCompraId));
		
	}
}
