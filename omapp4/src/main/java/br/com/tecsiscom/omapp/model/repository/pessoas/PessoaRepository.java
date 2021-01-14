package br.com.tecsiscom.omapp.model.repository.pessoas;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.tecsiscom.omapp.model.entity.pessoas.Pessoa;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
	
	List<Pessoa> findByNomeStartingWith(String nome);

}
