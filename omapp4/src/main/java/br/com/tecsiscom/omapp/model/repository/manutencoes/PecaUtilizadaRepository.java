package br.com.tecsiscom.omapp.model.repository.manutencoes;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.tecsiscom.omapp.model.entity.manutencoes.PecaUtilizada;
import br.com.tecsiscom.omapp.model.entity.manutencoes.ServicoPrestado;

@Repository
public interface PecaUtilizadaRepository extends JpaRepository<PecaUtilizada, Long> {

	List<PecaUtilizada> findByManutencaoId(Long id);
}
