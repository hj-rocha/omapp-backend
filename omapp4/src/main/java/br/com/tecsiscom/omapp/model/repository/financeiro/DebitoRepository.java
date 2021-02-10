package br.com.tecsiscom.omapp.model.repository.financeiro;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.tecsiscom.omapp.model.entity.financeiro.caixa.Debito;

public interface DebitoRepository extends JpaRepository<Debito, Long> {

}
