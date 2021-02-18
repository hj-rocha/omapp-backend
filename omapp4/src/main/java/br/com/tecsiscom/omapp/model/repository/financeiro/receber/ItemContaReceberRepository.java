package br.com.tecsiscom.omapp.model.repository.financeiro.receber;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.tecsiscom.omapp.model.entity.financeiro.receber.ItemContaReceber;

public interface ItemContaReceberRepository extends JpaRepository<ItemContaReceber, Long>{

	Set<ItemContaReceber> findByContaReceberId(Long contaReceberId);
}
