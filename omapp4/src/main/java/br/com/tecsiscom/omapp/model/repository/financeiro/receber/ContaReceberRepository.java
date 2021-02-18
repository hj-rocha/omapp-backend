package br.com.tecsiscom.omapp.model.repository.financeiro.receber;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.tecsiscom.omapp.model.entity.financeiro.receber.ContaReceber;

public interface ContaReceberRepository extends JpaRepository<ContaReceber, Long>{
	
	ContaReceber findByVendaId(Long vendaId);

}
