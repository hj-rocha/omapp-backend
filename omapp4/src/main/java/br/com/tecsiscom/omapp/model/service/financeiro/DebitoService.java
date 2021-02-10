package br.com.tecsiscom.omapp.model.service.financeiro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.tecsiscom.omapp.model.entity.financeiro.caixa.Debito;
import br.com.tecsiscom.omapp.model.repository.financeiro.DebitoRepository;

@Service
public class DebitoService {

	
	@Autowired
	private DebitoRepository repository;
	
	public Debito salvar(Debito debito) {
		return repository.save(debito);
	}
	
	public void remover(Long id) {
		repository.deleteById(id);
	}
}

