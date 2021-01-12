package br.com.tecsiscom.omapp.model.repository.produtos;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.tecsiscom.omapp.model.entity.produtos.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{

    boolean existsByNome(String nome);
}
