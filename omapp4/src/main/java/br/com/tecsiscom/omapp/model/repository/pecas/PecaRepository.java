package br.com.tecsiscom.omapp.model.repository.pecas;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.tecsiscom.omapp.model.entity.pecas.Peca;

public interface PecaRepository extends JpaRepository<Peca, Long>{

    boolean existsByNome(String nome);
}
