package br.com.tecsiscom.omapp.model.repository.manutencoes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.tecsiscom.omapp.model.entity.manutencoes.PecaUtilizada;

@Repository
public interface PecaUtilizadaRepository extends JpaRepository<PecaUtilizada, Long> {

}
