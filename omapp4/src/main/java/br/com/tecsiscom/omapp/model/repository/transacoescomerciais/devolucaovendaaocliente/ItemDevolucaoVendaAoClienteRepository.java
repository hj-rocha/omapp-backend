package br.com.tecsiscom.omapp.model.repository.transacoescomerciais.devolucaovendaaocliente;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.tecsiscom.omapp.model.entity.transacoescomerciais.devolucaovendaaocliente.ItemDevolucaoDeVendaAoCliente;

public interface ItemDevolucaoVendaAoClienteRepository extends JpaRepository<ItemDevolucaoDeVendaAoCliente, Long>{

}
