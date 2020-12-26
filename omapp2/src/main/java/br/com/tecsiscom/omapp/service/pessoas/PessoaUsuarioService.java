package br.com.tecsiscom.omapp.service.pessoas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.tecsiscom.omapp.exception.UsuarioNaoEncontradoException;
import br.com.tecsiscom.omapp.model.entity.pessoas.Pessoa;
import br.com.tecsiscom.omapp.model.entity.pessoas.Usuario;
import br.com.tecsiscom.omapp.model.repository.pessoas.PessoaRepository;
import br.com.tecsiscom.omapp.model.repository.pessoas.UsuarioRepository;

@Service
public class PessoaUsuarioService {

	@Autowired
	UsuarioRepository usuarioRepository;
	
	@Autowired
	PessoaService pessoaService;
	
	@Autowired
	PessoaRepository pessoaRepository;
	
	@Transactional
	public void desassociarUsuario(Long usuarioId, Long pessoaId) {
		Usuario usuario = buscarOuFalhar(usuarioId);
		Pessoa pessoa = pessoaService.buscarOuFalhar(pessoaId);
		
		pessoa.setUsuario(null);
		
		pessoaRepository.save(pessoa);
	}
	
	@Transactional
	public void associarUsuario(Long usuarioId, Long pessoaId) {
		Usuario usuario = buscarOuFalhar(usuarioId);
		Pessoa pessoa = pessoaService.buscarOuFalhar(pessoaId);
		
		pessoa.setUsuario(usuario);
		
		pessoaRepository.save(pessoa);
	}
	
	public Usuario buscarOuFalhar(Long usuarioId) {
		return usuarioRepository.findById(usuarioId)
			.orElseThrow(() -> new UsuarioNaoEncontradoException(usuarioId));
	}
}
