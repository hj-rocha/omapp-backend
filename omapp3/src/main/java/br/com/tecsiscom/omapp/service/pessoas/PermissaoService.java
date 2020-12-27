package br.com.tecsiscom.omapp.service.pessoas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.tecsiscom.omapp.exception.PermissaoNaoEncontradaException;
import br.com.tecsiscom.omapp.model.entity.pessoas.Permissao;
import br.com.tecsiscom.omapp.model.repository.pessoas.PermissaoRepository;

@Service
public class PermissaoService {

	@Autowired
	private PermissaoRepository permissaoRepository;
	
	public Permissao buscarOuFalhar(Long permissaoId) {
		return permissaoRepository.findById(permissaoId)
			.orElseThrow(() -> new PermissaoNaoEncontradaException(permissaoId));
	}
	
}
