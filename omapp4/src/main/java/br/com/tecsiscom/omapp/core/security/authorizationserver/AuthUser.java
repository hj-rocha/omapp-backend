package br.com.tecsiscom.omapp.core.security.authorizationserver;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import br.com.tecsiscom.omapp.model.entity.pessoas.Usuario;
import lombok.Getter;

@Getter
public class AuthUser extends User {

	private static final long serialVersionUID = 1L;
	
	private Long userId;
	private String fullName;
	
	public AuthUser(Usuario usuario, Collection<? extends GrantedAuthority> authorities) {
		super(usuario.getUsername(), usuario.getPassword(), authorities);
		
		this.userId = usuario.getId();
		this.fullName = usuario.getUsername();
	}
	
}
