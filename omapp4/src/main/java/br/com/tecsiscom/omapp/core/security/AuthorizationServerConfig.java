package br.com.tecsiscom.omapp.core.security;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter{

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients
			.inMemory()
				.withClient("om-app")
					.secret(passwordEncoder.encode("@321"))
					.authorizedGrantTypes("password", "refresh_token" )
					.scopes("WRITE", "READ")
					.accessTokenValiditySeconds(60*60*1) // 6 horas (padrÃ£o Ã© 12 horas)
					.refreshTokenValiditySeconds(60*60*24) // padrão 30 dias
				.and()	
					.withClient("geom-app") // TODO: colocar o nome mais adequado
					.secret(passwordEncoder.encode("@321")) // TODO: criar um senha difícil. Se possível dinâmica.
					.authorizedGrantTypes("password") // TODO: Rever isso.
					.scopes("read", "write")
					.accessTokenValiditySeconds(60 * 30)
				.and()
					.withClient("checktoken")
						.secret(passwordEncoder.encode("check123"));
	}
	
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		var enhancerChain = new TokenEnhancerChain();
		enhancerChain.setTokenEnhancers(Arrays.asList(new JwtCustomClaimsTokenEnhancer(), jwtAccessTokenConverter()));
		
		endpoints
		.authenticationManager(authenticationManager)
		.userDetailsService(userDetailsService)
		.reuseRefreshTokens(false)
		.accessTokenConverter(jwtAccessTokenConverter())
		.tokenEnhancer(enhancerChain);
	}

	@Bean
	public JwtAccessTokenConverter jwtAccessTokenConverter() {
		JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
		
		//TODO A chave deve ter um nome mais difícil que algaworks
		jwtAccessTokenConverter.setSigningKey("42748247497492749274294729dj2djdjjdffj");
		
		return jwtAccessTokenConverter;
	}
	
	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		security.checkTokenAccess("isAuthenticated()");
		//security.checkTokenAccess("permitAll()");
	}
}
