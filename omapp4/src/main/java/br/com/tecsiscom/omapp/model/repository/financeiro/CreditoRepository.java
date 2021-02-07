package br.com.tecsiscom.omapp.model.repository.financeiro;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.tecsiscom.omapp.model.entity.financeiro.Credito;

public interface CreditoRepository extends JpaRepository<Credito, Long>{

}
