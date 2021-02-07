package br.com.tecsiscom.omapp.model.service.transacoescomerciais.vendas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.tecsiscom.omapp.exception.VendaNaoEncontradoException;
import br.com.tecsiscom.omapp.model.entity.transacoescomerciais.vendas.Venda;
import br.com.tecsiscom.omapp.model.repository.transacoescomerciais.vendas.VendaRepository;

@Service
public class VendasService {

	@Autowired
	private VendaRepository repository;
	
	public Venda salvar(Venda venda) {
		return repository.save(venda);
	}
	
	public void remover(Long id) {
		repository.deleteById(id);
	}
	
	public Venda buscarOuFalhar(Long vendaId) {
		return repository.findById(vendaId)
				.orElseThrow(() -> new VendaNaoEncontradoException(vendaId));
		
	}
}
