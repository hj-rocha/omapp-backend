package br.com.tecsiscom.omapp.model.repository.produtos;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.tecsiscom.omapp.model.entity.produtos.Marca;

public interface MarcaRepository extends JpaRepository<Marca, Long>{

    boolean existsByNome(String nome);
}
