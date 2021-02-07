package br.com.tecsiscom.omapp.model.service.transacoescomerciais.devolucaodecompraaofornecedor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.tecsiscom.omapp.exception.CompraNaoEncontradoException;
import br.com.tecsiscom.omapp.model.entity.transacoescomerciais.devolcucaodecompraaofornecedor.ItemDevolucaoDeCompraAoFornecedor;
import br.com.tecsiscom.omapp.model.repository.transacoescomerciais.devolucaodecompraaofornecedor.ItemDevolucaoDeCompraAoFornecedorRepository;

@Service
public class ItemDevolucaoDeCompraAoFornecedorService {

	@Autowired
	private ItemDevolucaoDeCompraAoFornecedorRepository repository;
	
	public ItemDevolucaoDeCompraAoFornecedor salvar(ItemDevolucaoDeCompraAoFornecedor idcf) {
		return repository.save(idcf);
	}
	
	public void remover(Long id) {
		repository.deleteById(id);
	}
	
	public ItemDevolucaoDeCompraAoFornecedor buscarOuFalhar(Long idcfId) {
		return repository.findById(idcfId)
				.orElseThrow(() -> new CompraNaoEncontradoException(idcfId));
		
	}
}
