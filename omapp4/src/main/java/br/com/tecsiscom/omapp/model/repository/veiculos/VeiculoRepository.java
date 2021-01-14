package br.com.tecsiscom.omapp.model.repository.veiculos;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.tecsiscom.omapp.model.entity.veiculos.Veiculo;

public interface VeiculoRepository extends JpaRepository<Veiculo, Long>{

    boolean existsByNome(String nome);
}
