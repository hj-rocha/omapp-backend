package br.com.tecsiscom.omapp.model.repository.pessoas;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.tecsiscom.omapp.model.entity.pessoas.Pessoa;
import br.com.tecsiscom.omapp.model.entity.pessoas.PessoaFisica;
import br.com.tecsiscom.omapp.model.entity.pessoas.PessoaJuridica;
import br.com.tecsiscom.omapp.model.entity.pessoas.PessoaJuridica;

@Repository
public interface PessoaFisicaRepository extends JpaRepository<PessoaFisica, Long> {

}
