package br.com.tecsiscom.omapp.core.security;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

import javax.crypto.spec.SecretKeySpec;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;

@Configuration
@EnableWebSecurity /*Essa anotação habilita a configuração do Spring Security*/
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http
//			.httpBasic()
//			
//			.and()
//			.authorizeRequests()
//				.antMatchers("/ola/porteira/**").permitAll()
//				.antMatchers("/oauth/token/**").permitAll()
//				.anyRequest().authenticated()
//			
//			.and()
//			.sessionManagement()
//				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//				
//			.and()
//				.csrf().disable();
//	}
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
//			.authorizeRequests()
//			.antMatchers(HttpMethod.POST, "/testes/**").hasAuthority("EDITAR_RESTAURANTES")
//			.antMatchers(HttpMethod.PUT, "/testes/**").hasAuthority("EDITAR_COZINHAS")
//			.antMatchers(HttpMethod.GET, "/testes/**").authenticated()
//			.anyRequest().denyAll()
//			.and()
			.csrf().disable()
			.cors().and()
			.oauth2ResourceServer().jwt()
			.jwtAuthenticationConverter(jwtAuthenticationConverter())
			;
	}
	
	@Bean
	public JwtDecoder jwtDecoder() {
		
		var secretKey = new SecretKeySpec("42748247497492749274294729dj2djdjjdffj".getBytes(), "HmacSHA256"); 
		return NimbusJwtDecoder.withSecretKey(secretKey).build();
	}
		
	
	private JwtAuthenticationConverter jwtAuthenticationConverter() {
		var jwtAuthenticationConverter = new JwtAuthenticationConverter();
		jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwt -> {
			var authorities = jwt.getClaimAsStringList("authorities");
			
			if (authorities == null) {
				authorities = Collections.emptyList();
			}
			
			var scopesAuthorityConverter = new JwtGrantedAuthoritiesConverter();
			Collection<GrantedAuthority> grantedAuthorities = scopesAuthorityConverter.convert(jwt);
			
			grantedAuthorities.addAll(authorities.stream()
					.map(SimpleGrantedAuthority::new)
					.collect(Collectors.toList()));
			return grantedAuthorities;
		});
		
		return jwtAuthenticationConverter;
	}
	
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.inMemoryAuthentication()
//			.withUser("thiago")
//				.password(passwordEncoder().encode("123"))
//				.roles("ADMIN")
//			.and()
//			.withUser("joao")
//				.password(passwordEncoder().encode("123"))
//				.roles("ADMIN");
//	}
	
	
	//Apagamos essa parte porque criamos uma classe, CryptConfig, apenas para isso.
	///No caso essa classes estariam em projetos separados. Como fizemos Authorization e Resource Server tudo junto, deu conflito. 
//	@Bean
//	public PasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();
//	}
	
	@Bean
	@Override
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}

//	@Bean
//	@Override
//		protected UserDetailsService userDetailsService() {
//			return super.userDetailsService();
//		}
}
