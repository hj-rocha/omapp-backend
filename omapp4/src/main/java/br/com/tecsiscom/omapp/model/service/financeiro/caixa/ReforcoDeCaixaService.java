package br.com.tecsiscom.omapp.model.service.financeiro.caixa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.tecsiscom.omapp.model.entity.financeiro.caixa.ReforcoDeCaixa;
import br.com.tecsiscom.omapp.model.repository.financeiro.caixa.ReforcoDeCaixaRepository;


@Service
public class ReforcoDeCaixaService {

	@Autowired
	private ReforcoDeCaixaRepository repository;
	
	public ReforcoDeCaixa salvar(ReforcoDeCaixa reforcoDeCaixa) {
		return repository.save(reforcoDeCaixa);
	}
	
	public void remover(Long id) {
		repository.deleteById(id);
	}
}
