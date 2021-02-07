package br.com.tecsiscom.omapp.model.entity.financeiro.caixa;

import javax.persistence.Entity;

import br.com.tecsiscom.omapp.model.entity.financeiro.TransacaoCaixa;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data 
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
@Entity
public class SangriaDeCaixa extends TransacaoCaixa{/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
