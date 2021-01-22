package br.com.tecsiscom.omapp.model.entity.pessoas;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

import org.hibernate.validator.constraints.br.CNPJ;

import lombok.Data;
import lombok.EqualsAndHashCode;


@Data 
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
@Entity
@PrimaryKeyJoinColumn(name="id")
public class PessoaJuridica extends Pessoa{

	private String razaoSocial;

	@CNPJ(message = "CNPJ inválido")
	private String cnpj;
}
