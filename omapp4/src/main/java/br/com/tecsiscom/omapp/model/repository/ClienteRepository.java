package br.com.tecsiscom.omapp.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.tecsiscom.omapp.model.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
}
