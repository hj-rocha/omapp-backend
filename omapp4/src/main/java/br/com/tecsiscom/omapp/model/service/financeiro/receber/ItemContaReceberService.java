package br.com.tecsiscom.omapp.model.service.financeiro.receber;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.tecsiscom.omapp.model.entity.financeiro.receber.ItemContaReceber;
import br.com.tecsiscom.omapp.model.repository.financeiro.receber.ItemContaReceberRepository;

@Service
public class ItemContaReceberService {

	@Autowired
	private ItemContaReceberRepository repository;
	
	public ItemContaReceber salvar(ItemContaReceber itemContaReceber) {
		return repository.save(itemContaReceber);
	}
	
	public void remover(Long id) {
		repository.deleteById(id);
	}
}
