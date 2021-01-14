package br.com.tecsiscom.omapp.model.service.pessoas;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.tecsiscom.omapp.exception.NegocioException;
import br.com.tecsiscom.omapp.exception.UsuarioCadastradoException;
import br.com.tecsiscom.omapp.exception.UsuarioNaoEncontradoException;
import br.com.tecsiscom.omapp.model.entity.geografia.enderecos.Cidade;
import br.com.tecsiscom.omapp.model.entity.geografia.enderecos.Estado;
import br.com.tecsiscom.omapp.model.entity.pessoas.EnderecoPessoa;
import br.com.tecsiscom.omapp.model.entity.pessoas.Grupo;
import br.com.tecsiscom.omapp.model.entity.pessoas.Pessoa;
import br.com.tecsiscom.omapp.model.entity.pessoas.PessoaFisica;
import br.com.tecsiscom.omapp.model.entity.pessoas.Usuario;
import br.com.tecsiscom.omapp.model.repository.pessoas.PessoaFisicaRepository;
import br.com.tecsiscom.omapp.model.repository.pessoas.PessoaRepository;
import br.com.tecsiscom.omapp.model.repository.pessoas.UsuarioRepository;

@Service
public class UsuarioService { // implements UserDetailsService {

	@Autowired
	private UsuarioRepository repository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private PessoaFisicaRepository pessoaFisicaRepository;

	@Transactional
	public Usuario salvar(Usuario usuario) {
		boolean exists = repository.existsByUsername(usuario.getUsername());
		if (exists) {
			throw new UsuarioCadastradoException(usuario.getUsername());
		}
		if (usuario.isNovo()) {
			usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));

			// TODO Refatorar isso, pois a regra de negócio está repetida aqui e em pessoa
			// service.
			// Início Adicionamos esse códgo para cadastrar uma pessoa para o usuário novo
			usuario = repository.save(usuario);
			PessoaFisica pessoa = new PessoaFisica();
			pessoa.setNome("Usuário Novo");
			pessoa.setEmail(usuario.getUsername());
			pessoa.setUsuario(usuario);
			Cidade cidade = new Cidade();
			cidade.setId(9999L);
			Estado estado = new Estado();
			estado.setId(28L);
			cidade.setEstado(estado);
			EnderecoPessoa endereco = new EnderecoPessoa();
			endereco.setCidade(cidade);
			pessoa.setEndereco(endereco);
			Grupo grupo = new Grupo();
			grupo.setId(2L);
			pessoa.getGrupos().add(grupo);
			this.pessoaFisicaRepository.save(pessoa);
			return null;
			// Fim

		}
		return repository.save(usuario);
	}

//    @Override
//    public UserDetails loadUserByUsername( String username ) throws UsernameNotFoundException {
//        Usuario usuario = repository
//                            .findByUsername(username)
//                            .orElseThrow(() -> new UsernameNotFoundException("Login não encontrado.") );
//
//        return User
//                .builder()
//                .username(usuario.getUsername())
//                .password(usuario.getPassword())
//                .roles("USER")
//                .build()
//                ;
//    }

	@Transactional
	public void alterarSenha(Long usuarioId, String senhaAtual, String novaSenha) {
		Usuario usuario = buscarOuFalhar(usuarioId);

		if (!passwordEncoder.matches(senhaAtual, usuario.getPassword())) {
			throw new NegocioException("Senha atual informada não coincide com a senha do usuário.");
		}

		usuario.setPassword(passwordEncoder.encode(novaSenha));
	}

	public Usuario buscarOuFalhar(Long usuarioId) {
		return repository.findById(usuarioId).orElseThrow(() -> new UsuarioNaoEncontradoException(usuarioId));

	}
}
