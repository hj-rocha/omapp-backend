package br.com.tecsiscom.omapp.model.service.transacoescomerciais.devolucaovendaaocliente;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.tecsiscom.omapp.exception.ItemDevolucaoClienteNaoEncontradoException;
import br.com.tecsiscom.omapp.model.entity.transacoescomerciais.devolucaovendaaocliente.ItemDevolucaoDeVendaAoCliente;
import br.com.tecsiscom.omapp.model.repository.transacoescomerciais.devolucaovendaaocliente.ItemDevolucaoVendaAoClienteRepository;

public class ItemDevolucaoDeVendaAoClienteService {

	@Autowired
	private ItemDevolucaoVendaAoClienteRepository repository;

	public ItemDevolucaoDeVendaAoCliente salvar(ItemDevolucaoDeVendaAoCliente idvc) {
		return repository.save(idvc);
	}

	public void remover(Long id) {
		repository.deleteById(id);
	}

	public ItemDevolucaoDeVendaAoCliente buscarOuFalhar(Long idvcId) {
		return repository.findById(idvcId).orElseThrow(() -> new ItemDevolucaoClienteNaoEncontradoException(idvcId));
	}
}
