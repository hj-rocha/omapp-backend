package br.com.tecsiscom.omapp.model.service.financeiro.pagar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.tecsiscom.omapp.model.entity.financeiro.pagar.ContaPagar;
import br.com.tecsiscom.omapp.model.repository.financeiro.pagar.ContaPagarRepository;

@Service
public class ContasPagarService {

	@Autowired
	private ContaPagarRepository repository;
	
	public ContaPagar salvar(ContaPagar contaPagar) {
		return repository.save(contaPagar);
	}
	
	public void remover(Long id) {
		repository.deleteById(id);
	}
}
