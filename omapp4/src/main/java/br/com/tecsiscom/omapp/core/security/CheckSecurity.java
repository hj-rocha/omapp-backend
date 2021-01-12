package br.com.tecsiscom.omapp.core.security;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.springframework.security.access.prepost.PreAuthorize;

public @interface CheckSecurity {

	public @interface Pessoas {

		@PreAuthorize("hasAuthority('SCOPE_WRITE') and hasAuthority('EDITAR_PESSOAS')")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeEditar {
		}

		@PreAuthorize("hasAuthority('SCOPE_READ') and isAuthenticated()")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeConsultar {
		}
	}
	
	public @interface Grupos {

		@PreAuthorize("hasAuthority('SCOPE_WRITE') and hasAuthority('EDITAR_GRUPOS_PERMISSOES')")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeEditar {
		}

		@PreAuthorize("hasAuthority('SCOPE_READ') and hasAuthority('CONSULTAR_GRUPOS_PERMISSOES')")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeConsultar {
		}
	}

	public @interface Cidades {

		@PreAuthorize("hasAuthority('SCOPE_WRITE') and hasAuthority('EDITAR_CIDADES')")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeEditar {
		}

		@PreAuthorize("@algaSecurity.podeConsultarCidades()")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeConsultar {
		}

	}

	public @interface Estados {

		@PreAuthorize("hasAuthority('SCOPE_WRITE') and hasAuthority('EDITAR_ESTADOS')")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeEditar {
		}

		@PreAuthorize("@algaSecurity.podeConsultarEstados()")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeConsultar {
		}

	}

	public @interface PessoasGrupos {

		@PreAuthorize("hasAuthority('SCOPE_WRITE') and " + "@algaSecurity.usuarioAutenticadoIgual(#usuarioId)")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeAlterarPropriaSenha {
		}

		@PreAuthorize("hasAuthority('SCOPE_WRITE') and (hasAuthority('EDITAR_PESSOAS_GRUPOS') or "
				+ "@algaSecurity.usuarioAutenticadoIgual(#usuarioId))")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeAlterarUsuario {
		}

		@PreAuthorize("@algaSecurity.podeEditarPessoasGrupos()")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeEditar {
		}

		@PreAuthorize("@algaSecurity.podeConsultarPessoasGrupos()")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeConsultar {
		}

	}
	public @interface Enderecos {

		@PreAuthorize("hasAuthority('SCOPE_WRITE') and hasAuthority('EDITAR_ENDERECOS')")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeEditar {
		}

		@PreAuthorize("@algaSecurity.podeConsultarEnderecos()")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeConsultar {
		}

	}
	
	public @interface Produtos {

		@PreAuthorize("hasAuthority('SCOPE_WRITE') and hasAuthority('EDITAR_PRODUTOS')")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeEditar {
		}

		@PreAuthorize("hasAuthority('SCOPE_READ') and hasAuthority('CONSULTAR_PRODUTOS')")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeConsultar {
		}
	}

}
