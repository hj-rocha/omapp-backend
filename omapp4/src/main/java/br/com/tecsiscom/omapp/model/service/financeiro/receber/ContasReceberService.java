package br.com.tecsiscom.omapp.model.service.financeiro.receber;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.tecsiscom.omapp.model.entity.financeiro.receber.ContaReceber;
import br.com.tecsiscom.omapp.model.repository.financeiro.receber.ContaReceberRepository;


@Service
public class ContasReceberService {

	@Autowired
	private ContaReceberRepository repository;
	
	public ContaReceber salvar(ContaReceber contaReceber) {
		return repository.save(contaReceber);
	}
	
	public void remover(Long id) {
		repository.deleteById(id);
	}
}
