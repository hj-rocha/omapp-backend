package br.com.tecsiscom.omapp.model.repository.manutencoes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.tecsiscom.omapp.model.entity.manutencoes.Manutencao;

@Repository
public interface ManutencaoRepository extends JpaRepository<Manutencao, Long>{

	Manutencao findByVeiculoIdAndAtiva(Long veiculoId, Boolean ativa);
}
