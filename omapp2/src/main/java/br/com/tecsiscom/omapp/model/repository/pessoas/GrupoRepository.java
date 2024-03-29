package br.com.tecsiscom.omapp.model.repository.pessoas;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.tecsiscom.omapp.model.entity.pessoas.Grupo;



@Repository
public interface GrupoRepository extends JpaRepository<Grupo, Long> {

}
