package br.com.tecsiscom.omapp.model.service.estoque.entrada;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.tecsiscom.omapp.model.entity.estoque.entrada.ItemEntrada;
import br.com.tecsiscom.omapp.model.repository.estoque.entrada.ItemEntradaRepository;

@Service
public class ItemEntradaService {

	@Autowired
	private ItemEntradaRepository repository;
	
	public ItemEntrada salvar(ItemEntrada itemEntrada) {
		return repository.save(itemEntrada);
	}
	
	public void remover(Long id) {
		repository.deleteById(id);
	}
}
