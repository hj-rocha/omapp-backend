package br.com.tecsiscom.omapp.model.repository.produtos;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.tecsiscom.omapp.model.entity.produtos.Imposto;

public interface ImpostoRepository extends JpaRepository<Imposto, Long>{

    boolean existsByNome(String nome);
}
