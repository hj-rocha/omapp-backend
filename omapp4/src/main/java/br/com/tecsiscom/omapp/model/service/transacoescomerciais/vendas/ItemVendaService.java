package br.com.tecsiscom.omapp.model.service.transacoescomerciais.vendas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.tecsiscom.omapp.exception.ItemVendaNaoEncontradoException;
import br.com.tecsiscom.omapp.model.entity.transacoescomerciais.vendas.ItemVenda;
import br.com.tecsiscom.omapp.model.repository.transacoescomerciais.vendas.ItemVendaRepository;

@Service
public class ItemVendaService {

	@Autowired
	private ItemVendaRepository repository;
	
	public ItemVenda salvar(ItemVenda itemVenda) {
		return repository.save(itemVenda);
	}
	
	public void remover(Long id) {
		repository.deleteById(id);
	}
	
	public ItemVenda buscarOuFalhar(Long itemVendaId) {
		return repository.findById(itemVendaId)
				.orElseThrow(() -> new ItemVendaNaoEncontradoException(itemVendaId));
		
	}
}
