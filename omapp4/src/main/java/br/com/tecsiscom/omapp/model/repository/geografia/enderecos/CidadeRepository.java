package br.com.tecsiscom.omapp.model.repository.geografia.enderecos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.tecsiscom.omapp.model.entity.geografia.enderecos.Cidade;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Long> {
	
	
	List<Cidade> findByEstadoId(Long estadoId);

	List<Cidade> findByEstadoSigla(String estadoSigla);
	
}
