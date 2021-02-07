package br.com.tecsiscom.omapp.model.service.estoque;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.tecsiscom.omapp.model.entity.estoque.Estoque;
import br.com.tecsiscom.omapp.model.repository.estoque.EstoqueRepository;

@Service
public class EstoqueService {
	
	@Autowired
	private EstoqueRepository repository;
	
	public Estoque salvar(Estoque estoque) {
		return repository.save(estoque);
	}
	
	public void remover(Long id) {
		repository.deleteById(id);
	}
}
