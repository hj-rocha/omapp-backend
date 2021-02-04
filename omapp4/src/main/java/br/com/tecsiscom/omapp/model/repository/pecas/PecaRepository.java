package br.com.tecsiscom.omapp.model.repository.pecas;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.tecsiscom.omapp.model.entity.pecas.Peca;
import br.com.tecsiscom.omapp.model.entity.servicos.Servico;

@Repository
public interface PecaRepository extends JpaRepository<Peca, Long>{

    boolean existsByNome(String nome);
    
	List<Peca> findByNomeStartingWith(String nome);
}
