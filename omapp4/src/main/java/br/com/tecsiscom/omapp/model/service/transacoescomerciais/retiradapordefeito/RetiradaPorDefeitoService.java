package br.com.tecsiscom.omapp.model.service.transacoescomerciais.retiradapordefeito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.tecsiscom.omapp.exception.RetiradaPorDefeitoNaoEncontradoException;
import br.com.tecsiscom.omapp.model.entity.transacoescomerciais.retiradapordefeito.RetiradaPorDefeito;
import br.com.tecsiscom.omapp.model.repository.transacoescomerciais.retiradapordefeito.RetiradaPorDefeitoRepository;

@Service
public class RetiradaPorDefeitoService {
	
	@Autowired
	private RetiradaPorDefeitoRepository repository;
	
	public RetiradaPorDefeito salvar(RetiradaPorDefeito rpd) {
		return repository.save(rpd);
	}
	
	public void remover(Long id) {
		repository.deleteById(id);
	}
	
	public RetiradaPorDefeito buscarOuFalhar(Long rpdId) {
		return repository.findById(rpdId)
				.orElseThrow(() -> new RetiradaPorDefeitoNaoEncontradoException(rpdId));
		
	}
}
