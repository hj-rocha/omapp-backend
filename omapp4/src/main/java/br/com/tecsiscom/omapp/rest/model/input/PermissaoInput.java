package br.com.tecsiscom.omapp.rest.model.input;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PermissaoInput {

	private Long id;
	
	private String grupoNome;
	
	private String nome;
	
	private String descricao;
	
	private boolean pertence;
}
