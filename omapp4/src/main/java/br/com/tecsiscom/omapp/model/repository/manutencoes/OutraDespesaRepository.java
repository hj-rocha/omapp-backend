package br.com.tecsiscom.omapp.model.repository.manutencoes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.tecsiscom.omapp.model.entity.manutencoes.OutraDespesa;

@Repository
public interface OutraDespesaRepository extends JpaRepository<OutraDespesa,Long> {

}
