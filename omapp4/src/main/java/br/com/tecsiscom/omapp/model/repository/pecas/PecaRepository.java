package br.com.tecsiscom.omapp.model.repository.pecas;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.tecsiscom.omapp.model.entity.pecas.Peca;

@Repository
public interface PecaRepository extends JpaRepository<Peca, Long>{

    boolean existsByNome(String nome);
}
