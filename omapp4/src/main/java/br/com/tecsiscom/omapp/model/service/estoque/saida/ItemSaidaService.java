package br.com.tecsiscom.omapp.model.service.estoque.saida;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.tecsiscom.omapp.model.entity.estoque.saida.ItemSaida;
import br.com.tecsiscom.omapp.model.repository.estoque.saida.ItemSaidaRepository;

@Service
public class ItemSaidaService {

	@Autowired
	private ItemSaidaRepository repository;
	
	public ItemSaida salvar(ItemSaida itemSaida) {
		return repository.save(itemSaida);
	}
	
	public void remover(Long id) {
		repository.deleteById(id);
	}
}

