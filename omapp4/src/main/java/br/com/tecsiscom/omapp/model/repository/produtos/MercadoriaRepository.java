package br.com.tecsiscom.omapp.model.repository.produtos;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.tecsiscom.omapp.model.entity.produtos.Mercadoria;

public interface MercadoriaRepository extends JpaRepository<Mercadoria, Long>{

}
