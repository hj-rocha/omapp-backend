package br.com.tecsiscom.omapp.model.service.financeiro.caixa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.tecsiscom.omapp.model.entity.financeiro.caixa.Vale;
import br.com.tecsiscom.omapp.model.repository.financeiro.caixa.ValeRepository;


@Service
public class ValeService {

	@Autowired
	private ValeRepository repository;
	
	public Vale salvar(Vale vale) {
		return repository.save(vale);
	}
	
	public void remover(Long id) {
		repository.deleteById(id);
	}
}

