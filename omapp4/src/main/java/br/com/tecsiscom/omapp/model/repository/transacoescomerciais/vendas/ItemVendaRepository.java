package br.com.tecsiscom.omapp.model.repository.transacoescomerciais.vendas;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import br.com.tecsiscom.omapp.model.entity.transacoescomerciais.vendas.ItemVenda;

public interface ItemVendaRepository extends JpaRepository<ItemVenda, Long>{

	ItemVenda findTop1ByVendaId(Long vendaId);
	
	List<ItemVenda> findByVendaId(Long vendaId);
	
}
