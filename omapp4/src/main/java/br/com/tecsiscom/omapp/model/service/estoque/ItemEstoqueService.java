package br.com.tecsiscom.omapp.model.service.estoque;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.tecsiscom.omapp.model.entity.estoque.ItemEstoque;
import br.com.tecsiscom.omapp.model.repository.estoque.ItemEstoqueRepository;

@Service
public class ItemEstoqueService {
	
	@Autowired
	private ItemEstoqueRepository repository;
	
	public ItemEstoque salvar(ItemEstoque itemEstoque) {
		return repository.save(itemEstoque);
	}
	
	public void remover(Long id) {
		repository.deleteById(id);
	}
	
}
