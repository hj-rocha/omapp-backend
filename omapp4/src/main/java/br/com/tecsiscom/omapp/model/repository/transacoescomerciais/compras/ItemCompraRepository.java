package br.com.tecsiscom.omapp.model.repository.transacoescomerciais.compras;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import br.com.tecsiscom.omapp.model.entity.transacoescomerciais.compras.ItemCompra;

public interface ItemCompraRepository extends JpaRepository<ItemCompra, Long>{

	ItemCompra findTop1ByCompraId(Long compraId);
	
	List<ItemCompra> findByCompraId(Long compraId);
}
