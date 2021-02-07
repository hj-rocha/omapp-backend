package br.com.tecsiscom.omapp.model.service.financeiro.caixa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.tecsiscom.omapp.model.entity.financeiro.caixa.SangriaDeCaixa;

import br.com.tecsiscom.omapp.model.repository.financeiro.caixa.SangriaDeCaixaRepositoy;

@Service
public class SangriaDeCaixaService {

	@Autowired
	private SangriaDeCaixaRepositoy repository;
	
	public SangriaDeCaixa salvar(SangriaDeCaixa sangriaDeCaixa) {
		return repository.save(sangriaDeCaixa);
	}
	
	public void remover(Long id) {
		repository.deleteById(id);
	}
}
