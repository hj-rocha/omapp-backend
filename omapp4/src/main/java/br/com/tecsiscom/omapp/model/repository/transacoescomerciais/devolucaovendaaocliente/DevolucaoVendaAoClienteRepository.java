package br.com.tecsiscom.omapp.model.repository.transacoescomerciais.devolucaovendaaocliente;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.tecsiscom.omapp.model.entity.transacoescomerciais.devolucaovendaaocliente.DevolucaoDeVendaAoCliente;

public interface DevolucaoVendaAoClienteRepository extends JpaRepository<DevolucaoDeVendaAoCliente, Long>{

}
