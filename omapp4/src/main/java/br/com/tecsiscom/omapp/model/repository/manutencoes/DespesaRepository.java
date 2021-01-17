package br.com.tecsiscom.omapp.model.repository.manutencoes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.tecsiscom.omapp.model.entity.manutencoes.Despesa;

@Repository
public interface DespesaRepository extends JpaRepository<Despesa, Long>{

}
