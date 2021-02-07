package br.com.tecsiscom.omapp.model.entity.transacoescomerciais.retiradapordefeito;

import javax.persistence.Entity;

import br.com.tecsiscom.omapp.model.entity.transacoescomerciais.TransacaoComercialSaida;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data 
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
@Entity
public class RetiradaPorDefeito extends TransacaoComercialSaida {

    private String nota;

}
