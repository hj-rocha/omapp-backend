package br.com.tecsiscom.omapp.model.service.financeiro.caixa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.tecsiscom.omapp.model.entity.financeiro.caixa.Caixa;
import br.com.tecsiscom.omapp.model.repository.financeiro.caixa.CaixaRepository;

@Service
public class CaixaService {

	@Autowired
	private CaixaRepository repository;
	
	public Caixa salvar(Caixa caixa) {
		return repository.save(caixa);
	}
	
	public void remover(Long id) {
		repository.deleteById(id);
	}
}
