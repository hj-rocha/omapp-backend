package br.com.tecsiscom.omapp.model.service.estoque.saida;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.tecsiscom.omapp.model.entity.estoque.saida.Saida;
import br.com.tecsiscom.omapp.model.repository.estoque.saida.SaidaRepository;

@Service
public class SaidaService {
	
	@Autowired
	private SaidaRepository repository;
	
	public Saida salvar(Saida saida) {
		return repository.save(saida);
	}
	
	public void remover(Long id) {
		repository.deleteById(id);
	}
}
