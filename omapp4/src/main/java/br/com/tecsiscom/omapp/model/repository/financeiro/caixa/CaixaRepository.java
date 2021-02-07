package br.com.tecsiscom.omapp.model.repository.financeiro.caixa;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.tecsiscom.omapp.model.entity.financeiro.caixa.Caixa;

public interface CaixaRepository extends JpaRepository<Caixa, Long>{

}
