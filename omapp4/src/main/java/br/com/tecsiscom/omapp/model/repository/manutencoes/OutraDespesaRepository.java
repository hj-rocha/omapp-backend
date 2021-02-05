package br.com.tecsiscom.omapp.model.repository.manutencoes;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.tecsiscom.omapp.model.entity.manutencoes.OutraDespesa;
import br.com.tecsiscom.omapp.model.entity.manutencoes.PecaUtilizada;

@Repository
public interface OutraDespesaRepository extends JpaRepository<OutraDespesa,Long> {

	List<OutraDespesa> findByManutencaoId(Long id);
	
}
