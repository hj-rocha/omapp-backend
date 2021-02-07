package br.com.tecsiscom.omapp.model.repository.financeiro.pagar;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.tecsiscom.omapp.model.entity.financeiro.pagar.ItemContaPagar;

public interface ItemContaPagarRepository extends JpaRepository<ItemContaPagar, Long>{

}
