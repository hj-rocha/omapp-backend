package br.com.tecsiscom.omapp.model.repository.geografia.enderecos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.tecsiscom.omapp.model.entity.geografia.enderecos.Estado;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Long> {

}
