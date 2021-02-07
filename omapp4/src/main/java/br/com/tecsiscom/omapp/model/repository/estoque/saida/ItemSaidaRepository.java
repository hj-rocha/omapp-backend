package br.com.tecsiscom.omapp.model.repository.estoque.saida;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.tecsiscom.omapp.model.entity.estoque.saida.ItemSaida;

public interface ItemSaidaRepository extends JpaRepository<ItemSaida, Long>{

}
