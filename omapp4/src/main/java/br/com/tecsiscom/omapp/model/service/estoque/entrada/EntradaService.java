package br.com.tecsiscom.omapp.model.service.estoque.entrada;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.tecsiscom.omapp.model.entity.estoque.entrada.Entrada;
import br.com.tecsiscom.omapp.model.repository.estoque.entrada.EntradaRepository;

@Service
public class EntradaService {

	@Autowired
	private EntradaRepository repository;
	
	public Entrada salvar(Entrada entrada) {
		return repository.save(entrada);
	}
	
	public void remover(Long id) {
		repository.deleteById(id);
	}
}
