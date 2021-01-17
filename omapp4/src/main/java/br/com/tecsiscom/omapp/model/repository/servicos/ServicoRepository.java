package br.com.tecsiscom.omapp.model.repository.servicos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.tecsiscom.omapp.model.entity.servicos.Servico;

public interface ServicoRepository extends JpaRepository<Servico, Long>{

	List<Servico> findByNomeStartingWith(String nome);
}
