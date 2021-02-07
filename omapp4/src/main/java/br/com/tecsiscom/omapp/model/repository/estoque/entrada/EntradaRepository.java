package br.com.tecsiscom.omapp.model.repository.estoque.entrada;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.tecsiscom.omapp.model.entity.estoque.entrada.Entrada;

public interface EntradaRepository extends JpaRepository<Entrada, Long>{

}
