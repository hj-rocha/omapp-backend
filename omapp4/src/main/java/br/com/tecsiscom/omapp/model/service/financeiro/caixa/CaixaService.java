package br.com.tecsiscom.omapp.model.service.financeiro.caixa;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.tecsiscom.omapp.exception.CaixaNaoPodeSerReabertoEncontradoException;
import br.com.tecsiscom.omapp.model.entity.financeiro.caixa.Caixa;
import br.com.tecsiscom.omapp.model.repository.financeiro.caixa.CaixaRepository;

@Service
public class CaixaService {

	@Autowired
	private CaixaRepository repository;
	
	public Caixa salvar(Caixa caixa) {
		
		if(this.caixaFechado(caixa.getId())) {
			throw new CaixaNaoPodeSerReabertoEncontradoException(caixa.getId());
		}
		
		return repository.save(caixa);
	}
	
	public void remover(Long id) {
		repository.deleteById(id);
	}
	
	private Boolean caixaFechado(Long id) {
		Optional<Caixa> c = repository.findById(id);
		
		if(c.get().getFechado()==true) {
			return true;
		}
		return false;
	}
}
