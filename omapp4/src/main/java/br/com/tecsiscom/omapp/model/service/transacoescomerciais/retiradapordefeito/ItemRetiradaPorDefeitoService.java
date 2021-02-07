package br.com.tecsiscom.omapp.model.service.transacoescomerciais.retiradapordefeito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.tecsiscom.omapp.exception.ItemRetiradaPorDefeitoNaoEncontradoException;
import br.com.tecsiscom.omapp.model.entity.transacoescomerciais.retiradapordefeito.ItemRetiradaPorDefeito;
import br.com.tecsiscom.omapp.model.repository.transacoescomerciais.retiradapordefeito.ItemRetiradaPorDefeitoRepository;

@Service
public class ItemRetiradaPorDefeitoService {

	
	@Autowired
	private ItemRetiradaPorDefeitoRepository repository;

	public ItemRetiradaPorDefeito salvar(ItemRetiradaPorDefeito irds) {
		return repository.save(irds);
	}

	public void remover(Long id) {
		repository.deleteById(id);
	}

	public ItemRetiradaPorDefeito buscarOuFalhar(Long irdsId) {
		return repository.findById(irdsId).orElseThrow(() -> new ItemRetiradaPorDefeitoNaoEncontradoException(irdsId));
	}
}
