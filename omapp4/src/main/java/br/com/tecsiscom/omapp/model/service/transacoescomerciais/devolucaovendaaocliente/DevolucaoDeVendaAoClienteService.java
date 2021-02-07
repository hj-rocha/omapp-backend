package br.com.tecsiscom.omapp.model.service.transacoescomerciais.devolucaovendaaocliente;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.tecsiscom.omapp.exception.DevolucaoClienteNaoEncontradoException;
import br.com.tecsiscom.omapp.model.entity.transacoescomerciais.devolucaovendaaocliente.DevolucaoDeVendaAoCliente;
import br.com.tecsiscom.omapp.model.repository.transacoescomerciais.devolucaovendaaocliente.DevolucaoVendaAoClienteRepository;

public class DevolucaoDeVendaAoClienteService {

	@Autowired
	private DevolucaoVendaAoClienteRepository repository;

	public DevolucaoDeVendaAoCliente salvar(DevolucaoDeVendaAoCliente dvc) {
		return repository.save(dvc);
	}

	public void remover(Long id) {
		repository.deleteById(id);
	}

	public DevolucaoDeVendaAoCliente buscarOuFalhar(Long dvcId) {
		return repository.findById(dvcId).orElseThrow(() -> new DevolucaoClienteNaoEncontradoException(dvcId));
	}
}