package br.com.tecsiscom.omapp.model.repository.financeiro.pagar;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.tecsiscom.omapp.model.entity.financeiro.pagar.ContaPagar;

public interface ContaPagarRepository extends JpaRepository<ContaPagar, Long>{

	ContaPagar findByCompraId(Long compraId);
	
}
