package br.com.tecsiscom.omapp.model.service.financeiro.pagar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.tecsiscom.omapp.model.entity.financeiro.pagar.ItemContaPagar;
import br.com.tecsiscom.omapp.model.repository.financeiro.pagar.ItemContaPagarRepository;

@Service
public class ItemContaPagarService {
	
	@Autowired
	private ItemContaPagarRepository repository;
	
	public ItemContaPagar salvar(ItemContaPagar itemContaPagar) {
		return repository.save(itemContaPagar);
	}
	
	public void remover(Long id) {
		repository.deleteById(id);
	}
}

