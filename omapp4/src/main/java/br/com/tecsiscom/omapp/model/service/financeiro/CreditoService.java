package br.com.tecsiscom.omapp.model.service.financeiro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.tecsiscom.omapp.model.entity.financeiro.caixa.Credito;
import br.com.tecsiscom.omapp.model.repository.financeiro.CreditoRepository;

@Service
public class CreditoService {

	
	@Autowired
	private CreditoRepository repository;
	
	public Credito salvar(Credito credito) {
		return repository.save(credito);
	}
	
	public void remover(Long id) {
		repository.deleteById(id);
	}
}

