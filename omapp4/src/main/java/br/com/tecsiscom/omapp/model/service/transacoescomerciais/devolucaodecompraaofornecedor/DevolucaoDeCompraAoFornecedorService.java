package br.com.tecsiscom.omapp.model.service.transacoescomerciais.devolucaodecompraaofornecedor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.tecsiscom.omapp.exception.CompraNaoEncontradoException;
import br.com.tecsiscom.omapp.model.entity.transacoescomerciais.devolcucaodecompraaofornecedor.DevolucaoDeCompraAoFornecedor;
import br.com.tecsiscom.omapp.model.repository.transacoescomerciais.devolucaodecompraaofornecedor.DevolucaoDeCompraAoFornecedorRepository;

@Service
public class DevolucaoDeCompraAoFornecedorService {

	@Autowired
	private DevolucaoDeCompraAoFornecedorRepository repository;
	
	public DevolucaoDeCompraAoFornecedor salvar(DevolucaoDeCompraAoFornecedor dcf) {
		return repository.save(dcf);
	}
	
	public void remover(Long id) {
		repository.deleteById(id);
	}
	
	public DevolucaoDeCompraAoFornecedor buscarOuFalhar(Long dcfId) {
		return repository.findById(dcfId)
				.orElseThrow(() -> new CompraNaoEncontradoException(dcfId));
		
	}
}
