package br.com.tecsiscom.omapp.model.repository.estoque;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.tecsiscom.omapp.model.entity.estoque.Estoque;

public interface EstoqueRepository extends JpaRepository<Estoque, Long>{

	Estoque findByProdutoId(Long Id);
}
