package br.com.tecsiscom.omapp.model.entity.pessoas;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import br.com.tecsiscom.omapp.model.entity.geografia.enderecos.Cidade;
import lombok.Data;

@Data
@Embeddable  // Classe a ser incorporada.
public class EnderecoPessoa {

	@Column(name = "endereco_cep")
	private String cep;
	@Column(name = "endereco_logradouro")
	private String logradouro;
	@Column(name = "endereco_numero")
	private String numero;
	@Column(name = "endereco_complemento")
	private String complemento;
	@Column(name = "endereco_bairro")
	private String bairro;
	@Column(name = "endereco_referencia")
	private String referencia;
	
	//@ManyToOne(fetch = FetchType.LAZY)
	@ManyToOne
	@JoinColumn(name = "endereco_cidade_id")
	//@JoinColumn(columnDefinition="integer", name = "endereco_cidade_id", nullable = true)
	private Cidade cidade;
	
}
