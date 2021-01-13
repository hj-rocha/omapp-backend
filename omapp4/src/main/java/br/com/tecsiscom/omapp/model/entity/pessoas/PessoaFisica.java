package br.com.tecsiscom.omapp.model.entity.pessoas;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

import org.hibernate.validator.constraints.br.CPF;

import lombok.Data;
import lombok.EqualsAndHashCode;


@Data 
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
@Entity
@PrimaryKeyJoinColumn(name="id")
public class PessoaFisica extends Pessoa{

	private String identidade;

	@CPF(message = "CPF inválido")
	private String cpf;
}
